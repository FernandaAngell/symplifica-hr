package com.symplifica.backend.employee.mapper;

import com.symplifica.backend.employee.dto.request.CreateEmployeeRequest;
import com.symplifica.backend.employee.dto.response.EmployeeResponse;
import com.symplifica.backend.employee.entity.Employee;

public class EmployeeMapper {

    private EmployeeMapper() {
    }

    public static Employee toEntity(CreateEmployeeRequest request) {
        return Employee.builder()
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .email(request.getEmail())
                .phone(request.getPhone())
                .city(request.getCity())
                .position(request.getPosition())
                .build();
    }

    public static EmployeeResponse toResponse(Employee employee) {
        return EmployeeResponse.builder()
                .id(employee.getId())
                .firstName(employee.getFirstName())
                .lastName(employee.getLastName())
                .email(employee.getEmail())
                .phone(employee.getPhone())
                .city(employee.getCity())
                .position(employee.getPosition())
                .latitude(employee.getLatitude())
                .longitude(employee.getLongitude())
                .displayName(employee.getDisplayName())
                .build();
    }
}