package com.example.demo.repo;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.example.demo.entity.Person;
@Repository
public interface PersonRepo {
	
	public boolean addPerson(Person person);
	
	public Person findPersonById(long id);
	
	public boolean deletePersonById(long id);
	
	public boolean updatePersonById(long id,Person person);
	
	public List<Person> getAllPersons();

}
