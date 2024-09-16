package com.rest.simplerest.projection;

import java.util.List;

public interface CompanyProjection {
    Long getId();
    String getName();
    List<DepartmentProjection> getDepartments();
}