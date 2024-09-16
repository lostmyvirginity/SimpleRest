package com.rest.simplerest.controller;

import com.rest.simplerest.dto.request.CompanyCreateDTO;
import com.rest.simplerest.dto.request.EmptyCompanyCreateDTO;
import com.rest.simplerest.projection.CompanyProjection;
import com.rest.simplerest.service.CompanyService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/company")
@Tag(name = "Company controller")
public class CompanyController {
    private final CompanyService companyService;

    public CompanyController(CompanyService companyService) {
        this.companyService = companyService;
    }

    @Operation(summary = "Get All Companies")
    @GetMapping("/getCompanies")
    public ResponseEntity<String> getCompanies() {
        return companyService.getWhole();
    }

    @Operation(summary = "Get whole data for company")
    @GetMapping("/getCompany/{id}")
    public ResponseEntity<CompanyProjection> getCompanyById(@PathVariable("id") Long id) {
        return companyService.getCompaniesWholeData(id);
    }

    @Operation(summary = "Get whole companies with related data")
    @GetMapping("/getCompaniesData")
    public ResponseEntity<List<CompanyProjection>> getCompaniesWholeData() {
        return companyService.getCompaniesWholeData();
    }

    @Operation(summary = "Get Companies By Id")
    @GetMapping("/getById")
    public ResponseEntity<String> getCompanyById(@RequestParam int id) {
        return companyService.getById(id);
    }

    @Operation(summary = "Create empty Company with given name")
    @PostMapping("/createEmptyCompany")
    public ResponseEntity<String> createEmptyCompany(@RequestBody EmptyCompanyCreateDTO createDTO) {
        return companyService.createEmptyCompany(createDTO);
    }

    @Operation(summary = "Create company with data")
    @PostMapping("/createCompany")
    public ResponseEntity<String> createCompany(@RequestBody CompanyCreateDTO createDTO) {
        return companyService.createCompany(createDTO);
    }

    @Operation(summary = "Update An Existing Company")
    @PostMapping("/update")
    public ResponseEntity<String> updateCompany(@RequestParam int id, @RequestParam String name) {
        return companyService.updateCompany(id, name);
    }

    @Operation(summary = "Delete Company By Id")
    @DeleteMapping("/delete")
    public ResponseEntity<String> deleteCompany(@RequestParam int id) {
        return companyService.deleteCompany(id);
    }

}
