package com.company.employees.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.company.employees.model.Department;
import com.company.employees.repository.DepartmentRepository;


@RestController
@RequestMapping("/department")
public class DepartmentController {
	@Autowired
    private DepartmentRepository departmentRepository;
	
	@GetMapping
	public List<Department> getAllDepartments(){
		return departmentRepository.findAll();
	}
}
