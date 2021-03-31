package com.company.employees.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="skill")
public class Skill {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="skill_id")
    private Long skillId;
	
    @Column(name="skill_name",nullable = false)
    private String skillName;
    
    @ManyToOne(cascade=CascadeType.ALL)
    private Employee employee;

	public Long getSkillId() {
		return skillId;
	}

	public void setSkillId(Long skillId) {
		this.skillId = skillId;
	}

	public String getSkillName() {
		return skillName;
	}

	public void setSkillName(String skillName) {
		this.skillName = skillName;
	}
}
