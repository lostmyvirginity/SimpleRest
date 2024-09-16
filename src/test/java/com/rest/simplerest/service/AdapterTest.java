package com.rest.simplerest.service;

import com.google.gson.Gson;
import com.rest.simplerest.adapter.CompanyAdapter;
import com.rest.simplerest.dto.request.CompanyCreateDTO;
import com.rest.simplerest.entity.Company;
import com.rest.simplerest.entity.Department;
import com.rest.simplerest.entity.Manager;
import com.rest.simplerest.entity.Team;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AdapterTest {
    public CompanyAdapter adapter;
    @BeforeEach
    public void init(){
        adapter = new CompanyAdapter();
    }
    public final String JSON = "{\n" +
            "  \"name\": \"Company Name\",\n" +
            "  \"departments\": [\n" +
            "    {\n" +
            "      \"name\": \"Department 1\",\n" +
            "      \"teams\": [\n" +
            "        {\n" +
            "          \"name\": \"Team 1\",\n" +
            "          \"project\": {\n" +
            "            \"manager\": {\n" +
            "              \"name\": \"Manager Name 1\",\n" +
            "              \"surname\": \"Manager Surname 1\",\n" +
            "              \"phoneNumber\": \"123-456-7890\",\n" +
            "              \"email\": \"manager1@example.com\"\n" +
            "            }\n" +
            "          }\n" +
            "        }\n" +
            "      ]\n" +
            "    },\n" +
            "    {\n" +
            "      \"name\": \"Department 2\",\n" +
            "      \"teams\": [\n" +
            "        {\n" +
            "          \"name\": \"Team 2\",\n" +
            "          \"project\": {\n" +
            "            \"manager\": {\n" +
            "              \"name\": \"Manager Name 2\",\n" +
            "              \"surname\": \"Manager Surname 2\",\n" +
            "              \"phoneNumber\": \"098-765-4321\",\n" +
            "              \"email\": \"manager2@example.com\"\n" +
            "            }\n" +
            "          }\n" +
            "        }\n" +
            "      ]\n" +
            "    }\n" +
            "  ]\n" +
            "}\n";

    @Test
    public void testAdapter(){
        CompanyCreateDTO companyCreateDTO = new Gson().fromJson(JSON, CompanyCreateDTO.class);
        Company c = adapter.adaptCompany(companyCreateDTO);
        Optional<Department> d = c.getDepartments().stream().findFirst();
        Optional<Team> t = d.get().getTeams().stream().findFirst();
        Optional<Manager> m = Optional.ofNullable(t.get().getProject().getManager());
        assertEquals(2, c.getDepartments().size());
        assertEquals("Department 2", d.get().getName());
        assertEquals("Team 2", t.get().getName());
        assertEquals("Manager Name 2", m.get().getName());
    }
}
