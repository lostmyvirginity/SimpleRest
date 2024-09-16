package com.rest.simplerest.projection;

import java.util.List;

public interface DepartmentProjection {
    Long getId();
    String getName();
    List<TeamProjection> getTeams();
}
