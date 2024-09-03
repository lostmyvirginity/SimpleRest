package com.rest.simplerest.service;

import com.google.gson.Gson;
import com.rest.simplerest.dto.request.CompanyCreateDTO;
import com.rest.simplerest.dto.response.CompanyDTO;
import com.rest.simplerest.entity.Company;
import com.rest.simplerest.repository.CompanyRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
public class CompanyService {
    private final CompanyRepository companyRepository;

    @Autowired
    public CompanyService(CompanyRepository companyRepository) {
        this.companyRepository = companyRepository;
    }

    public ResponseEntity<String> getWhole() {
        List<CompanyDTO> companyDTOList = companyRepository.findAll().stream().map(company -> new CompanyDTO(company.getName())).collect(Collectors.toUnmodifiableList());
        Gson gson = new Gson();
        log.debug("The number of companies: {}", companyDTOList.size());
        log.debug("Companies: {}", companyDTOList);
        return ResponseEntity.ok(gson.toJson(companyDTOList));
    }

    public ResponseEntity<String> getById(int id) {
        Optional<Company> company = companyRepository.findById((long) id);
        if (company.isPresent()) {
            CompanyDTO companyDTO = new CompanyDTO(company.get().getName());
            Gson gson = new Gson();
            log.debug("Company was found: {}", companyDTO);
            return ResponseEntity.ok(gson.toJson(companyDTO));
        }
        return ResponseEntity.badRequest().body("Company not found");
    }

    @Transactional(rollbackFor = Exception.class)
    public ResponseEntity<String> createCompany(CompanyCreateDTO companyCreateDTO) {
        try {
            if (companyCreateDTO == null) {
                return ResponseEntity.badRequest().body("Given company is null");
            }
            Company company = new Company();
            company.setName(companyCreateDTO.getName());
            companyRepository.save(company);
            log.debug("Company was saved: {}", company);
            return ResponseEntity.ok("Created Successfully");
        } catch (Exception e) {
            log.error("Error creating company {}", e.getMessage());
            return ResponseEntity.internalServerError().body("An unexpected error occurred");
        }
    }

    @Transactional(rollbackFor = Exception.class)
    public ResponseEntity<String> updateCompany(int id, String name) {
        try {
            Optional<Company> company = companyRepository.findById((long) id);
            if (company.isPresent()) {
                company.get().setName(name);
                return ResponseEntity.ok("Updated Successfully");
            }
            return ResponseEntity.internalServerError().body("Company not found");
        } catch (Exception e) {
            log.error("Error update company: {}", e.getMessage());
            return ResponseEntity.internalServerError().body("Failed to update company");
        }
    }


    @Transactional
    public ResponseEntity<String> deleteCompany(int id) {
        Optional<Company> company = companyRepository.findById((long) id);
        if (company.isPresent()) {
            companyRepository.delete(company.get());
            log.debug("Company was deleted: {}", company);
            return ResponseEntity.ok("Deleted Successfully");
        }
        return ResponseEntity.badRequest().body("Company not found");
    }

}
