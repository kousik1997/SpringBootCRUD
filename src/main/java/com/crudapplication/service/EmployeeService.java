package com.crudapplication.service;

import java.util.List;

import com.crudapplication.dto.EmployeeRequestDto;
import com.crudapplication.dto.EmployeeResponseDto;

public interface EmployeeService {

    EmployeeResponseDto createEmployee(EmployeeRequestDto requestDto);

    EmployeeResponseDto getEmployeeById(Long id);

    List<EmployeeResponseDto> getAllEmployees();

    EmployeeResponseDto updateEmployee(Long id, EmployeeRequestDto requestDto);

    void deleteEmployee(Long id);
}
