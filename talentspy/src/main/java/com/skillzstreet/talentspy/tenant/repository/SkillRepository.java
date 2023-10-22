package com.skillzstreet.talentspy.tenant.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.skillzstreet.talentspy.tenant.entity.Skill;


public interface SkillRepository extends JpaRepository<Skill, UUID> {
	
	@Query("SELECT DISTINCT " +
	           "    s " +
	           "FROM " +
	           "    Skill s INNER JOIN FETCH s.goals g ORDER BY s.created, g.name")
	List<Skill> findAll();

}
