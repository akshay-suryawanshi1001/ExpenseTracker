package com.akshay.expenseTracker.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.akshay.expenseTracker.models.Employee;

@Controller
@RequestMapping("/base")
public class BaseController {
	
	@GetMapping("/abc")
    public String viewHomePage(Model model) {
		
		Employee emp1 = new Employee(1, "akshay", "akshay.suryawanshi@gmail.com");
		Employee emp2 = new Employee(1, "rohit", "rohit.suryawanshi@gmail.com");
		
		List<Employee> empList = new ArrayList<Employee>();
		empList.add(emp2);
		empList.add(emp1);
		
		model.addAttribute("allemplist", empList);
        model.addAttribute("myname", "Akshay");
        return "index";
    }
}
