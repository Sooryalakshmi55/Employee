package com.company.employees.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.company.employees.model.Department;

public interface DepartmentRepository extends JpaRepository<Department, Long> {
	
}
