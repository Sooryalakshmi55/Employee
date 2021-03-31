package com.company.employees.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.company.employees.model.Skill;

public interface SkillRepository extends JpaRepository<Skill, Long> {

}
