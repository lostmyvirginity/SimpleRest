package com.rest.simplerest.repository;

import com.rest.simplerest.entity.Company;
import com.rest.simplerest.projection.CompanyProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CompanyRepository extends JpaRepository<Company, Long> {

    @Query("SELECT c FROM Company c WHERE c.id = :id")
    Optional<CompanyProjection> findByIdWithDepartmentsAndTeamsAndProjectAndManager(@Param("id") Long id);
    @Query("select c from Company c")
    Optional<List<CompanyProjection>> findByIdWithDepartmentsAndTeamsAndProjectAndManagerWhole();
}

