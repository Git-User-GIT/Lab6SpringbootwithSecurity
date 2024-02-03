package com.greatLearning.studentManagement.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.greatLearning.studentManagement.entity.Student;
import com.greatLearning.studentManagement.repository.StudentRepository;

@Service
public class StudentServiceImplemetation implements StudentService{

	
	@Autowired 
	StudentRepository studentRepository;
	
	@Override
	public List<Student> findAll() {
		// TODO Auto-generated method stub
		
		return studentRepository.findAll();
	}
	
	public Student findById(int id) {
		// TODO Auto-generated method stub
		return studentRepository.findById(id).get();		
	}

	

	@Override
	public void save(Student thestudent) {
		// TODO Auto-generated method stub
		studentRepository.save(thestudent);
		
	}

	
	@Override
	public void deleteById(int id) {
		// TODO Auto-generated method stub
		studentRepository.deleteById(id);
		
	}
	
	
	

}
