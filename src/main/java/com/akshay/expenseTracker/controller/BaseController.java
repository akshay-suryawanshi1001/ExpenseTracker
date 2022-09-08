package com.akshay.expenseTracker.controller;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.akshay.expenseTracker.models.Category;
import com.akshay.expenseTracker.models.Employee;
import com.akshay.expenseTracker.repo.ExpenseInfoRepo;

@Controller
@RequestMapping("/expense")
public class BaseController {
	
	@Autowired
	private ExpenseInfoRepo expenseInfoRepo;
	
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
	
	@PostMapping("/save")
	public Category saveCategory(@RequestBody Category category) {
		category.setAddedDateTime(LocalDateTime.now());
		categoryRepo.save(category);
		return category;
	}
	
	@GetMapping("/getAll")
	public List<Category> getCategoryList(){
		List<Category> categoryList = categoryRepo.findAll();
		return categoryList;
	}
	
	@GetMapping("/get/{id}")
	public Category getCategory(@PathVariable("id") String categoryId) {
		Category category = categoryRepo.findById(Long.parseLong(categoryId)).get();
		return category;
	}
	
	@PutMapping("/update/{id}")
	public Category updateCategory(@PathVariable("id") String categoryId, @RequestBody Category category) {
		Category updatedCategory = new Category();
		Category dbCategory = categoryRepo.findById(Long.parseLong(categoryId)).get();
		
		updatedCategory.setName(dbCategory.getName());
		updatedCategory.setDescription(dbCategory.getDescription());
		updatedCategory.setUpdatedDatetime(LocalDateTime.now());
		return updatedCategory;
	}
	
	@DeleteMapping("/delete/{id}")
	public String deleteCategory(@PathVariable("id") String categoryId) {
		categoryRepo.deleteById(Long.parseLong(categoryId));
		return "categoryId :" + categoryId + " deleted";
	}
}
