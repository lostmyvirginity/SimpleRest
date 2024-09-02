package com.rest.simplerest.service;

import com.google.gson.Gson;
import com.rest.simplerest.dto.request.CompanyCreateDTO;
import com.rest.simplerest.dto.response.CompanyDTO;
import com.rest.simplerest.entity.Company;
import com.rest.simplerest.repository.CompanyRepository;
import jakarta.validation.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CompanyService {
    private final CompanyRepository companyRepository;

    @Autowired
    public CompanyService(CompanyRepository companyRepository) {
        this.companyRepository = companyRepository;
    }

    public ResponseEntity<String> getWhole() {
        List<CompanyDTO> companyDTOList = companyRepository.findAll().stream().map(company -> new CompanyDTO(company.getName())).collect(Collectors.toUnmodifiableList());
        Gson gson = new Gson();
        return ResponseEntity.ok(gson.toJson(companyDTOList));
    }

    public ResponseEntity<String> getById(int id) {
        Optional<Company> company = companyRepository.findById((long) id);
        if (company.isPresent()) {
            CompanyDTO companyDTO = new CompanyDTO(company.get().getName());
            Gson gson = new Gson();
            return ResponseEntity.ok(gson.toJson(companyDTO));
        }
        return ResponseEntity.badRequest().body("Company not found");
    }

    public ResponseEntity<String> createCompany(CompanyCreateDTO companyCreateDTO) {
        if (companyCreateDTO == null) {
            return ResponseEntity.badRequest().body("Given company is null");
        }
        try {
            Company company = new Company();
            company.setName(companyCreateDTO.getName());
            Optional<Company> saved = Optional.of(companyRepository.save(company));
            if (saved.isPresent()) {
                return ResponseEntity.ok("Created Successfully");
            } else {
                return ResponseEntity.internalServerError().body("Failed to create company");
            }
        } catch (ConstraintViolationException e) {
            return ResponseEntity.badRequest().body("Validation failed");
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("An unexpected error occurred");
        }
    }

    public ResponseEntity<String> updateCompany(int id, String name) {
        Optional<Company> company = companyRepository.findById((long) id);
        if (company.isPresent()) {
            try {
                company.get().setName(name);
                Optional<Company> saved = Optional.of(companyRepository.save(company.get()));
                if (saved.isPresent()) {
                    return ResponseEntity.ok("Updated Successfully");
                }
            } catch (Exception e) {
                return ResponseEntity.internalServerError().body("Failed to update company");
            }
        }
        return ResponseEntity.internalServerError().body("Company not found");
    }

    public ResponseEntity<String> deleteCompany(int id) {
        Optional<Company> company = companyRepository.findById((long) id);
        if (company.isPresent()) {
            companyRepository.delete(company.get());
            return ResponseEntity.ok("Deleted Successfully");
        }
        return ResponseEntity.badRequest().body("Company not found");
    }
}
