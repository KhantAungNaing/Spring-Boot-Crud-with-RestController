package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.Person;
import com.example.demo.repo.PersonRepo;

@RestController
public class PersonController {
	

	@Autowired
	private final PersonRepo personRepo;
	
	public PersonController(PersonRepo personRepo) {
		this.personRepo = personRepo;
	}
	
//	@GetMapping
//	public List<Person> getAllPersons(){
//		return personRepo.getAllPersons();
//	}
	
	@GetMapping("/getP/{id}")
	public Person getPersonById(@PathVariable Long id){
		return personRepo.findPersonById(id);
	}
	
	@PostMapping("/add")
	public boolean addPerson(@RequestBody Person person){
		return personRepo.addPerson(person);
	}
	
	@PutMapping("/update/{id}")
	public boolean updatePerson(@PathVariable Long id, @RequestBody Person person) {
		person.setId(id);
		return personRepo.updatePersonById(id, person);
	}
	
	@DeleteMapping("/delete/{id}")
	public boolean deletePerson(@PathVariable Long id) {
		return personRepo.deletePersonById(id);
	}
	
	
	
}
