package com.akshay.expenseTracker.controller;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.akshay.expenseTracker.models.Category;
import com.akshay.expenseTracker.models.Employee;
import com.akshay.expenseTracker.models.ExpenditureResponse;
import com.akshay.expenseTracker.models.ExpenseInfo;
import com.akshay.expenseTracker.models.SalaryType;
import com.akshay.expenseTracker.repo.CategoryRepo;
import com.akshay.expenseTracker.repo.ExpenseInfoRepo;

@RestController
@RequestMapping("/expense")
public class ExpenseController {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(ExpenseController.class);
	
	@Autowired
	private ExpenseInfoRepo expenseInfoRepo;
	
	@Autowired
	private CategoryRepo categoryRepo;
	
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
	public ExpenseInfo saveExpense(@RequestBody ExpenseInfo expenseInfo) {
		LOGGER.info("inside saveExpense with values " + expenseInfo.toString());
		expenseInfo.setAddedDateTime(LocalDateTime.now());
		expenseInfo.setSalaryType(SalaryType.Tentative.toString());
		expenseInfo.setExpenseDay(LocalDateTime.now().getDayOfMonth());
		expenseInfo.setExpenseMonth(LocalDateTime.now().getMonthValue());
		expenseInfo.setExpenseYear(LocalDateTime.now().getYear());
		LOGGER.info("saving ExpenseInfo with values " + expenseInfo.toString());
		expenseInfoRepo.save(expenseInfo);
		
		return expenseInfo;
	}
	
	@GetMapping("/getAll")
	public List<ExpenseInfo> getCategoryList(){
		LOGGER.info("inside getCategoryList... fetching all expenses...");
		List<ExpenseInfo> categoryList = expenseInfoRepo.findAll();
		return categoryList;
	}
	
	@GetMapping("/get/{id}")
	public ExpenseInfo getExpenseInfo(@PathVariable("id") String expenseId) {
		LOGGER.info("inside getExpenseInfo...");
		ExpenseInfo expense = expenseInfoRepo.findById(Long.parseLong(expenseId)).get();
		LOGGER.info("fetched ExpenseInfo..." + expense.toString());
		return expense;
	}
	
	@PutMapping("/update/{id}")
	public ExpenseInfo updateExpenseInfo(@PathVariable("id") String expenseId, @RequestBody ExpenseInfo expenseInfo) {
		LOGGER.info("inside updateExpenseInfo...");
		ExpenseInfo dbExpenseInfo = expenseInfoRepo.findById(Long.parseLong(expenseId)).get();
		
		dbExpenseInfo.setUpdatedDateTime(LocalDateTime.now());
		if(expenseInfo.getCategoryId() !=null)
			dbExpenseInfo.setCategoryId(expenseInfo.getCategoryId());
		if(expenseInfo.getExpenseDescription() !=null)
			dbExpenseInfo.setExpenseDescription(expenseInfo.getExpenseDescription());
		
		expenseInfoRepo.save(dbExpenseInfo);
		LOGGER.info("updating ExpenseInfo to " + dbExpenseInfo.toString());
		return dbExpenseInfo;
	}
	
	@DeleteMapping("/delete/{id}")
	public String deleteExpenseInfo(@PathVariable("id") String expenseId) {
		LOGGER.info("inside deleteExpenseInfo...");
		expenseInfoRepo.deleteById(Long.parseLong(expenseId));
		LOGGER.info("expenseId :" + expenseId + " deleted...");
		return "expenseId :" + expenseId + " deleted...";
	}
	
	@GetMapping("/calculate")
	public ExpenditureResponse calculateExpenditure(@RequestParam("month") Integer month, @RequestParam("year") Integer year) {
		LOGGER.info("inside calculateExpenditure...month:" + month + ", year:"+ year);
		ExpenditureResponse expenditure = new ExpenditureResponse();
		if(year == null)
			year = LocalDateTime.now().getYear();
		List<ExpenseInfo> expenseList = expenseInfoRepo.getExpenseByExpenseMonthAndExpenseYear(month, year);
		//List<BigDecimal> expenseAmountList = expenseList.stream().map(ExpenseInfo::getExpenseAmount).collect(Collectors.toList());
		//BigDecimal totalExpenseAmount = expenseAmountList.stream().reduce(BigDecimal.ZERO, BigDecimal::add);
		BigDecimal totalExpenseAmount2 = expenseList
				.stream().map(ExpenseInfo::getExpenseAmount)
				.reduce(BigDecimal.ZERO, BigDecimal::add);
		LOGGER.info("totalExpenseAmount:" + totalExpenseAmount2);
		//System.out.println(expenseList);
		//System.out.println(expenseList);
		//System.out.println(totalExpenseAmount2);
		List<ExpenseInfo> percentCalculatedList = expenseList.stream().map((e) -> calculateExpensePercent(e,totalExpenseAmount2)).collect(Collectors.toList());
		LOGGER.info("percentCalculatedList:" + percentCalculatedList);
		expenditure.setTotalExpense(totalExpenseAmount2);
		expenditure.setExpenseList(percentCalculatedList);
		//System.out.println("###" + percentCalculatedList);
		return expenditure;
	}
	
	public ExpenseInfo calculateExpensePercent(ExpenseInfo expenseInfo, BigDecimal totalExpenseAmount) {
		BigDecimal divideResult = expenseInfo.getExpenseAmount().divide(totalExpenseAmount,2, RoundingMode.HALF_UP);
		BigDecimal expensePercent = divideResult.multiply(new BigDecimal(100));
		LOGGER.info("expenseInfo.getExpenseAmount():" + expenseInfo.getExpenseAmount() + " of " + totalExpenseAmount + " is "+ expensePercent + "%");
		expenseInfo.setPercentExpense(expensePercent);
		return expenseInfo;
	}
}
