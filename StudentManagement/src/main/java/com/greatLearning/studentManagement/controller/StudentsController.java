package com.greatLearning.studentManagement.controller;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.greatLearning.studentManagement.entity.Student;
import com.greatLearning.studentManagement.service.StudentService;


@Controller
@RequestMapping("/student")
public class StudentsController {
	
	@Autowired
	private StudentService studentService;
	
	@GetMapping("/list")
	public String listOfStudents(Model theModel) {
		List<Student> studentsList=studentService.findAll();
		theModel.addAttribute("Students", studentsList);
		
		return "list-Students";
		
	}
	@GetMapping("/showFormForAdd")
	public String showFormForAdd(Model theModel){
		Student theStudent=new Student();
		theModel.addAttribute("Student", theStudent);
		
		return "Student-form";		
	}
	
	@GetMapping("/showFormForUpdate")
	public String showFormForUpdate(@RequestParam("studentId") int id , Model theModel) {
		Student theStudent=studentService.findById(id);
		theModel.addAttribute("Student", theStudent);
				
		return "Student-form";
		
	}
	
	@PostMapping("/save")
	public String save(@ModelAttribute Student student) {
		studentService.save(student);
				
		return "redirect:/student/list";
		
	}

	@GetMapping("/delete")
	public String delete(@RequestParam("studentId") int id ) {
		studentService.deleteById(id);
					
		return "redirect:/student/list";
		
	}
	
	@GetMapping("/403")
	public ModelAndView accessDenied(Principal user) {
		ModelAndView model=new ModelAndView();
		
		if(user !=null)
			model.addObject("msg", "Hi"+ user.getName() +" You don't have permission to access this page");
		else 
			model.addObject("msg", "Hi You don't have permission to access this page");
		
		model.setViewName("403");
		return model;
		
	}

}

