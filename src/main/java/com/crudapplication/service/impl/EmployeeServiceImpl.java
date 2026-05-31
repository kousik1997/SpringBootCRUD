package com.crudapplication.service.impl;

import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;

import com.crudapplication.dto.EmployeeRequestDto;
import com.crudapplication.dto.EmployeeResponseDto;
import com.crudapplication.entity.Employee;
import com.crudapplication.exception.EmployeeNotFoundException;
import com.crudapplication.repository.EmployeeRepository;
import com.crudapplication.service.EmployeeService;
import com.crudapplication.util.EmployeeMapper;

import lombok.RequiredArgsConstructor;



@Service
@RequiredArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {

	private final EmployeeRepository employeeRepository;

    @Override
    public EmployeeResponseDto createEmployee(EmployeeRequestDto requestDto) {

        Employee employee = EmployeeMapper.toEntity(requestDto);

        Employee savedEmployee = employeeRepository.save(employee);

        return EmployeeMapper.toDto(savedEmployee);
    }

    @Override
    public EmployeeResponseDto getEmployeeById(Long id) {

        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() -> new EmployeeNotFoundException("Employee not found with id: " + id));

        return EmployeeMapper.toDto(employee);
    }

    @Override
    public List<EmployeeResponseDto> getAllEmployees() {

        List<Employee> employees = employeeRepository.findAll();

        return employees.stream()
                .map(EmployeeMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public EmployeeResponseDto updateEmployee(Long id, EmployeeRequestDto requestDto) {

        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() -> new EmployeeNotFoundException("Employee not found with id: " + id));
        
        employee.setName(requestDto.getName());
        employee.setEmail(requestDto.getEmail());
        employee.setDepartment(requestDto.getDepartment());
        employee.setSalary(requestDto.getSalary());

        Employee updatedEmployee = employeeRepository.save(employee);

        return EmployeeMapper.toDto(updatedEmployee);
    }

    @Override
    public void deleteEmployee(Long id) {

        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() -> new EmployeeNotFoundException("Employee not found with id: " + id));

        employeeRepository.delete(employee);
    }
}
    