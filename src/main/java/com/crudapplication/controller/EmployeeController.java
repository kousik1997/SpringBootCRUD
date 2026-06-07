package com.crudapplication.controller;

import java.util.List;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.crudapplication.dto.EmployeeRequestDto;
import com.crudapplication.dto.EmployeeResponseDto;
import com.crudapplication.service.EmployeeService;

import java.util.List;
@RestController
@RequestMapping("/api/employees")
@RequiredArgsConstructor
public class EmployeeController {
	
   private final EmployeeService employeeService;
   
   @PostMapping
   public ResponseEntity<EmployeeResponseDto> createEmployee(
           @Valid @RequestBody EmployeeRequestDto requestDto) {
       EmployeeResponseDto response = employeeService.createEmployee(requestDto);
       return new ResponseEntity<>(response, HttpStatus.CREATED);
   }
   
   @GetMapping("/{id}")
   public ResponseEntity<EmployeeResponseDto> getEmployeeById(@PathVariable Long id) {
       EmployeeResponseDto response = employeeService.getEmployeeById(id);
       return ResponseEntity.ok(response);
   }
   
   @GetMapping
   public ResponseEntity<List<EmployeeResponseDto>> getAllEmployees() {
       List<EmployeeResponseDto> response = employeeService.getAllEmployees();
       return ResponseEntity.ok(response);
   }
   
   @PutMapping("/{id}")
   public ResponseEntity<EmployeeResponseDto> updateEmployee(
           @PathVariable Long id,
           @Valid @RequestBody EmployeeRequestDto requestDto) {
       EmployeeResponseDto response = employeeService.updateEmployee(id, requestDto);
       return ResponseEntity.ok(response);
   }
   
   @DeleteMapping("/{id}")
   public ResponseEntity<String> deleteEmployee(@PathVariable Long id) {
       employeeService.deleteEmployee(id);
       return ResponseEntity.ok("Employee deleted successfully");
   }
   //tt
}