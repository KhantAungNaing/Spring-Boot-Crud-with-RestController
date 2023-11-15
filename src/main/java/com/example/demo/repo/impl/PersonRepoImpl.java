package com.example.demo.repo.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.example.demo.dbconfig.JdbcConfig;
import com.example.demo.entity.Person;
import com.example.demo.repo.PersonRepo;

@Component
public class PersonRepoImpl implements PersonRepo{
	

	@Override
	public boolean addPerson(Person person){
		try {
			String sql = "INSERT INTO person_tbl (name,age) VALUES (?,?)";
			Connection connection = JdbcConfig.getInstance().getDataSource().getConnection();
			PreparedStatement pstmt = connection.prepareStatement(sql);
			pstmt.setString(1, person.getName());
			pstmt.setInt(2, person.getAge());
			if(pstmt.executeUpdate() > 0) {
				return true;
			}
			return false;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public Person findPersonById(long id){
		Person person = new Person();
		try {
			String sql = "SELECT * FROM person_tbl where id = ?";
			Connection conn =  JdbcConfig.getInstance().getDataSource().getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setLong(1, id);
			ResultSet rs = pstmt.executeQuery();
			if(rs.next()) {
				person.setId(id);
				person.setName(rs.getString("name"));
				person.setAge(rs.getInt("age"));
			}
			return person;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return person;
	}

	@Override
	public boolean deletePersonById(long id){
		try {
			String sql = "DELETE FROM person_tbl WHERE id = ?";
			Connection connection =  JdbcConfig.getInstance().getDataSource().getConnection();
			PreparedStatement pstmt = connection.prepareStatement(sql);
			pstmt.setLong(1, id);
			if(pstmt.executeUpdate() > 0) {
				return true;
			}
			return false;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean updatePersonById(long id,Person person){
		try {
			String sql = "UPDATE person_tbl SET name = ?,age = ? where id = ?";
			Connection connection =  JdbcConfig.getInstance().getDataSource().getConnection();
			PreparedStatement pstmt = connection.prepareStatement(sql);
			pstmt.setString(1, person.getName());
			pstmt.setInt(2, person.getAge());
			pstmt.setLong(3, id);
			if(pstmt.executeUpdate() > 0) {
				return true;
			}
			return false;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public List<Person> getAllPersons(){
		try {
			List<Person> persons = new ArrayList<Person>();
			String sql = "SELECT * FROM person_tbl";
			Connection connection =  JdbcConfig.getInstance().getDataSource().getConnection();
			PreparedStatement pstmt = connection.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()) {
				Person person = new Person();
				person.setId(rs.getLong("id"));
				person.setName(rs.getString("name"));
				person.setAge(rs.getInt("age"));
				persons.add(person);
			}
			return persons;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

}
