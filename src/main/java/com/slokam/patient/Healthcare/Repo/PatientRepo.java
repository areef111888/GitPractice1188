package com.slokam.patient.Healthcare.Repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.slokam.patient.Healthcare.Entity.PatientEntity;

@Repository
public interface PatientRepo extends JpaRepository<PatientEntity, Integer>  {
	public List<PatientEntity> findByName(String name);
	
	@Query("from PatientEntity p where p.name like ?1 ")
	public List<PatientEntity> findByNameLike(String name);
 
}
