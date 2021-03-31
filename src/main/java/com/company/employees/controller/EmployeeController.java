package com.company.employees.controller;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.company.employees.model.Company;
import com.company.employees.model.Department;
import com.company.employees.model.Employee;
import com.company.employees.repository.CompanyRepository;
import com.company.employees.repository.DepartmentRepository;
import com.company.employees.repository.EmployeeRepository;
import com.company.employees.repository.SkillRepository;

import org.springframework.data.domain.Sort;

@RestController
@RequestMapping("/employee")
public class EmployeeController {
	
	@Autowired
    private EmployeeRepository employeeRepository;
	@Autowired
	private SkillRepository skillRepository;
	@Autowired
	private DepartmentRepository departmentRepository;
	@Autowired
	private CompanyRepository companyRepository;
	
	@GetMapping("/{id}")
    public ResponseEntity<Employee> getById(@PathVariable Long id) {
        Optional<Employee> optionalLibrary = employeeRepository.findById(id);
        if (!optionalLibrary.isPresent()) {
            return ResponseEntity.unprocessableEntity().build();
        }

        return ResponseEntity.ok(optionalLibrary.get());
    }

    @GetMapping
    public ResponseEntity<List<Employee>> getAll() {
    	// List all employees based on Salary(Highest on top)
        return ResponseEntity.ok(employeeRepository.findAll(Sort.by(Sort.Direction.DESC, "salary")));
    }

    @PostMapping("/add")
    public ResponseEntity<Employee> createEmployee(@Valid @RequestBody Employee employee){
    	// Create an employee with department and skills
    	Optional<Company> optionalCompany = companyRepository.findById(employee.getCompany().getCompanyId());
        if (!optionalCompany.isPresent()) {
            return ResponseEntity.unprocessableEntity().build();
        }

        employee.setCompany(optionalCompany.get());
        
        Optional<Department> optionalDepartment = departmentRepository.findById(employee.getDepartment().getDepartmentId());
        if (!optionalDepartment.isPresent()) {
            return ResponseEntity.unprocessableEntity().build();
        }

        employee.setDepartment(optionalDepartment.get());
        
        Employee newEmployee = employeeRepository.save(employee);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(newEmployee.getEmployeeId()).toUri();

           return ResponseEntity.created(location).body(newEmployee);

    }
    
    @PutMapping("/{id}")
    public ResponseEntity<Employee> update(@PathVariable Long id, @Valid @RequestBody Employee employee) {
    	// Update an empoyee
        Optional<Employee> optionalEmployee = employeeRepository.findById(id);
        if (!optionalEmployee.isPresent()) {
            return ResponseEntity.unprocessableEntity().build();
        }

        employee.setEmployeeId(optionalEmployee.get().getEmployeeId());
        
        Optional<Company> optionalCompany = companyRepository.findById(employee.getCompany().getCompanyId());
        if (!optionalCompany.isPresent()) {
            return ResponseEntity.unprocessableEntity().build();
        }

        employee.setCompany(optionalCompany.get());
        
        Optional<Department> optionalDepartment = departmentRepository.findById(employee.getDepartment().getDepartmentId());
        if (!optionalDepartment.isPresent()) {
            return ResponseEntity.unprocessableEntity().build();
        }

        employee.setDepartment(optionalDepartment.get());
        
        employeeRepository.save(employee);

        return ResponseEntity.ok(employee);
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Employee> delete(@PathVariable Long id) {
    	// Delete an employee
        Optional<Employee> optionalEmployee = employeeRepository.findById(id);
        if (!optionalEmployee.isPresent()) {
            return ResponseEntity.unprocessableEntity().build();
        }

        Employee employee = optionalEmployee.get();
        employee.setCompany(null);
        employee.setDepartment(null);
        employeeRepository.delete(optionalEmployee.get());

        return ResponseEntity.noContent().build();
    }

}
