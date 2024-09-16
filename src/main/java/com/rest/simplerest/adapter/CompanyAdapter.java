package com.rest.simplerest.adapter;

import com.rest.simplerest.dto.request.*;
import com.rest.simplerest.entity.*;
import org.springframework.stereotype.Component;

import java.util.Set;
import java.util.stream.Collectors;

@Component
public class CompanyAdapter {

    public Company adaptCompany(CompanyCreateDTO createDTO) {
        Set<Department> departments = createDTO.getDepartments().stream().map(this::createDepartment).collect(Collectors.toSet());

        Company company = Company.builder().name(createDTO.getName()).departments(departments).build();

        company.getDepartments().forEach(department -> department.setCompany(company));

        return company;
    }

    private Department createDepartment(DepartmentDTO departmentDTO) {
        Set<Team> teams = departmentDTO.getTeams().stream().map(this::createTeam).collect(Collectors.toSet());

        Department department = Department.builder().name(departmentDTO.getName()).teams(teams).build();

        department.getTeams().forEach(team -> team.setDepartment(department));

        return department;
    }

    private Team createTeam(TeamDTO teamDTO) {
        Project project = createProject(teamDTO.getProject());

        Team team = Team.builder().name(teamDTO.getName()).project(project).build();

        project.setTeam(team);

        return team;
    }

    private Project createProject(ProjectDTO projectDTO) {
        Manager manager = createManager(projectDTO.getManager());

        return Project.builder().manager(manager).build();
    }

    private Manager createManager(ManagerDTO managerDTO) {
        return Manager.builder().name(managerDTO.getName()).surname(managerDTO.getSurname()).phoneNumber(managerDTO.getPhoneNumber()).email(managerDTO.getEmail()).build();
    }
}
