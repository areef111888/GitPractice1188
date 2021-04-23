package com.slokam.patient.Healthcare.Controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.slokam.patient.Healthcare.Entity.PatientEntity;
import com.slokam.patient.Healthcare.Repo.PatientRepo;

@RestController
public class PatientController {
	@Autowired
	private PatientRepo repo;

	@PostMapping("savePatientsDetails")
	public ResponseEntity<PatientEntity> savePatient(@RequestBody PatientEntity entity) {

		repo.save(entity);
		return new ResponseEntity<PatientEntity>(HttpStatus.CREATED);

	}

	@GetMapping("getPatientDetails/{id}")
	public ResponseEntity<PatientEntity> getPatientById(@PathVariable Integer id) {
		Optional<PatientEntity> option = repo.findById(id);
		if (option.isPresent()) {
			return new ResponseEntity<PatientEntity>(option.get(), HttpStatus.OK);

		}
		return new ResponseEntity<PatientEntity>(HttpStatus.NOT_FOUND);

	}

	@GetMapping("patients")
	public ResponseEntity<List<PatientEntity>> getPatients() {
		List<PatientEntity> entities = repo.findAll();
		if (entities.isEmpty()) {
			return new ResponseEntity<List<PatientEntity>>(HttpStatus.NOT_FOUND);

		}
		return new ResponseEntity<List<PatientEntity>>(entities, HttpStatus.OK);

	}

	@PutMapping("updatePatient")
	public ResponseEntity<PatientEntity> updatePatient(@RequestBody PatientEntity patientEntity) {
		repo.save(patientEntity);

		return new ResponseEntity<PatientEntity>(HttpStatus.OK);

	}
  @DeleteMapping("deletePatientById/{id}")
  public ResponseEntity<PatientEntity> deletePatient(@PathVariable  Integer id){
	repo.deleteById(id);
	return new ResponseEntity<PatientEntity>(HttpStatus.OK);
	
	}
  
  @GetMapping("getPatientByName/{name}")
   public ResponseEntity<List<PatientEntity>> findByName(@PathVariable String name){
	  List<PatientEntity> patients =repo.findByName(name);
	return new ResponseEntity<>(patients,HttpStatus.OK);
	   
   }
  
  @GetMapping("getPatientsByNameLikes/{name}")
  public ResponseEntity<List<PatientEntity>> findByNamelike(@PathVariable String name){
	  List<PatientEntity> patients =repo.findByNameLike("%"+name+"%");
	return new ResponseEntity<>(patients,HttpStatus.OK);
	  
  }



}
