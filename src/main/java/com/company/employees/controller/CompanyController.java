package com.company.employees.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.company.employees.model.Company;
import com.company.employees.model.Employee;
import com.company.employees.repository.CompanyRepository;
import com.company.employees.repository.EmployeeRepository;

@RestController
@RequestMapping("/company")
public class CompanyController {
	@Autowired
    private CompanyRepository companyRepository;

	@GetMapping
	public List<Company> getAllCompanies(){
		return companyRepository.findAll();
	}

    @PostMapping("/add")
    public Company createCompany(@Valid @RequestBody Company company) {
        return companyRepository.save(company);
    }

}
