package com.rest.simplerest.service;

import com.rest.simplerest.dto.request.CompanyCreateDTO;
import com.rest.simplerest.repository.CompanyRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.transaction.TransactionSystemException;
import org.springframework.transaction.UnexpectedRollbackException;
import org.springframework.transaction.annotation.Transactional;
import org.testcontainers.containers.PostgreSQLContainer;

@SpringBootTest
public class CompanyServiceTest {
    @Autowired
    CompanyRepository companyRepository;
    @Autowired
    CompanyService companyService;


    private static PostgreSQLContainer<?> postgreSQLContainer = new PostgreSQLContainer<>("postgres:latest")
            .withDatabaseName("testdb")
            .withUsername("testuser")
            .withPassword("testpass");


    @DynamicPropertySource
    static void properties(DynamicPropertyRegistry registry) {
        registry.add("spring.datasource.url", postgreSQLContainer::getJdbcUrl);
        registry.add("spring.datasource.username", postgreSQLContainer::getUsername);
        registry.add("spring.datasource.password", postgreSQLContainer::getPassword);
    }

    @BeforeAll
    public static void setUp() {
        postgreSQLContainer.start();
    }

    @Test
    public void testDependencies() {
        Assertions.assertNotNull(companyRepository);
        Assertions.assertNotNull(companyService);
    }

    @Test
    public void testGetWholeCompanies() {
        ResponseEntity<String> response = companyService.getWhole();
        Assertions.assertEquals(200, response.getStatusCode().value());
        Assertions.assertEquals("[{\"companyName\":\"Tech Innovations\"},{\"companyName\":\"Creative Solutions\"},{\"companyName\":\"NextGen Enterprises\"}]", response.getBody());
    }

    @Test
    public void testGetCompanyByIdThatExist() {
        ResponseEntity<String> response = companyService.getById(1);
        Assertions.assertEquals(200, response.getStatusCode().value());
        Assertions.assertEquals("{\"companyName\":\"Tech Innovations\"}", response.getBody());
    }

    @Test
    public void testGetCompanyByIdThatDoesntExist() {
        ResponseEntity<String> response = companyService.getById(0);
        Assertions.assertEquals(400, response.getStatusCode().value());
        Assertions.assertEquals("Company not found", response.getBody());
    }

    @Test
    public void testUpdateValidCompany() {
        ResponseEntity<String> response = companyService.updateCompany(1, "test");
        Assertions.assertEquals(200, response.getStatusCode().value());
        Assertions.assertEquals("Updated Successfully", response.getBody());
    }

    @Test
    public void testUpdateCompanyThatDoesntExist() {
        ResponseEntity<String> response = companyService.updateCompany(0, "test");
        Assertions.assertEquals("Company not found", response.getBody());
    }

    @Test
    public void testUpdateCompanyWithNullOrEmptyName() {
        Assertions.assertThrows(TransactionSystemException.class, () -> companyService.updateCompany(1, ""));
        Assertions.assertThrows(TransactionSystemException.class, () -> companyService.updateCompany(1, null));
    }

    @Test
    public void testCreateNullCompany() {
        ResponseEntity<String> response = companyService.createCompany(null);
        Assertions.assertEquals(400, response.getStatusCode().value());
        Assertions.assertEquals("Given company is null", response.getBody());
    }

    @Test
    public void testCreateValidCompany() {
        ResponseEntity<String> response = companyService.createCompany(new CompanyCreateDTO("Test"));
        Assertions.assertEquals(200, response.getStatusCode().value());
        Assertions.assertEquals("Created Successfully", response.getBody());
    }

    @Test
    public void testCreateEmptyCompany() {
        Assertions.assertThrows(UnexpectedRollbackException.class, () -> companyService.createCompany(new CompanyCreateDTO("")));
        Assertions.assertThrows(UnexpectedRollbackException.class, () -> companyService.createCompany(new CompanyCreateDTO(null)));
    }

    @Test
    @Transactional
    @Rollback
    public void testDeleteCompany() {
        ResponseEntity<String> response = companyService.deleteCompany(1);
        ResponseEntity<String> afterDelete = companyService.getWhole();
        Assertions.assertEquals(200, response.getStatusCode().value());
        Assertions.assertEquals("Deleted Successfully", response.getBody());
        Assertions.assertEquals("[{\"companyName\":\"Creative Solutions\"},{\"companyName\":\"NextGen Enterprises\"}]", afterDelete.getBody());
    }

    @Test
    public void testDeleteCompanyThatDoesntExist() {
        ResponseEntity<String> response = companyService.deleteCompany(0);
        Assertions.assertEquals(400, response.getStatusCode().value());
        Assertions.assertEquals("Company not found", response.getBody());
    }
}