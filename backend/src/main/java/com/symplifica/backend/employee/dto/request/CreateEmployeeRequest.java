package com.symplifica.backend.employee.dto.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateEmployeeRequest {

    private String firstName;
    private String lastName;
    private String email;
    private String phone;
    private String city;
    private String position;
}