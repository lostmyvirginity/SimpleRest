package com.rest.simplerest.projection;

public interface TeamProjection {
    Long getId();
    String getName();
    ProjectProjection getProject();
}
