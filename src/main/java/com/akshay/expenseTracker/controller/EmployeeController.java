package com.akshay.expenseTracker.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.akshay.expenseTracker.models.Employee;
import com.akshay.expenseTracker.repo.EmployeeRepository;

@Controller
@RequestMapping("/employee")
public class EmployeeController {
	
	@Autowired 
	public EmployeeRepository empRepo;
	
	@GetMapping("/listAll")
    public String viewHomePage(Model model) {
		
		//Employee emp1 = new Employee(1, "akshay", "akshay.suryawanshi@gmail.com");
		//Employee emp2 = new Employee(1, "rohit", "rohit.suryawanshi@gmail.com");
		
		List<Employee> empList = empRepo.findAll();
		//empList.add(emp2);
		//empList.add(emp1);
		
		model.addAttribute("allemplist", empList);
        model.addAttribute("myname", "Akshay");
        return "employeeIndex";
    }
	
	@GetMapping("/addnew")
    public String addNewEmployee(Model model) {
        Employee employee = new Employee();
        model.addAttribute("employee", employee);
        return "newemployee";
    }
 
    @PostMapping("/save")
    public String saveEmployee(@ModelAttribute("employee") Employee employee) {
    	empRepo.save(employee);
        return "redirect:/employee/listAll";
    }
 
    @GetMapping("/showFormForUpdate/{id}")
    public String updateForm(@PathVariable(value = "id") long id, Model model) {
        Optional<Employee> employee = empRepo.findById(id);
        model.addAttribute("employee", employee.get());
        return "updateEmployee";
    }
 
    @GetMapping("/deleteEmployee/{id}")
    public String deleteThroughId(@PathVariable(value = "id") long id) {
        //employeeServiceImpl.deleteViaId(id);
        empRepo.deleteById(id);
        return "redirect:/employee/listAll";
 
    }
}
