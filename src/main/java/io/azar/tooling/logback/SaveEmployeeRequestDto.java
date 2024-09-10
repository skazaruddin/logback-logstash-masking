package io.azar.tooling.logback;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class SaveEmployeeRequestDto {

    private String employeeId;
    private String firstName;
    private String lastName;
    private String email;
    private String department;
    private String position;
    private double salary;
    private String ssn;
    private String password;

    private Address address;
}

