package com.crudapplication.util;

import com.crudapplication.dto.EmployeeRequestDto;
import com.crudapplication.dto.EmployeeResponseDto;
import com.crudapplication.entity.Employee;

public class EmployeeMapper {

    public static Employee toEntity(EmployeeRequestDto dto) {

        return Employee.builder()
                .name(dto.getName())
                .email(dto.getEmail())
                .department(dto.getDepartment())
                .salary(dto.getSalary())
                .build();
    }

    public static EmployeeResponseDto toDto(Employee employee) {

        return EmployeeResponseDto.builder()
                .id(employee.getId())
                .name(employee.getName())
                .email(employee.getEmail())
                .department(employee.getDepartment())
                .salary(employee.getSalary())
                .build();
    }
}