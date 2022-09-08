package com.akshay.expenseTracker.controller;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.akshay.expenseTracker.models.Category;
import com.akshay.expenseTracker.repo.CategoryRepo;

@RestController
@RequestMapping("/category")
public class CategoryController {
	
	@Autowired
	private CategoryRepo categoryRepo;
	
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
