package com.rest.simplerest.repository;

import com.rest.simplerest.entity.Company;
import org.springframework.data.repository.ListCrudRepository;

public interface CompanyRepository extends ListCrudRepository<Company, Long> {
    
}
