package com.company.employees.model;


import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="company")
public class Company {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="company_id")
    private Long companyId;
	
    @Column(name="company_name",nullable = false)
    private String companyName;
    
    @OneToMany(cascade = CascadeType.ALL, targetEntity=Department.class)
    List<Department> departments;
    
    @OneToMany(cascade = CascadeType.ALL, targetEntity=Employee.class)
    List<Employee> employees;

	public Long getCompanyId() {
		return companyId;
	}

	public void setCompanyId(Long companyId) {
		this.companyId = companyId;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public List<Department> getDepartments() {
		return departments;
	}

	public void setDepartments(List<Department> departments) {
		this.departments = departments;
	}

	public List<Employee> getEmployees() {
		return employees;
	}

	public void setEmployees(List<Employee> employees) {
		this.employees = employees;
	}

}
