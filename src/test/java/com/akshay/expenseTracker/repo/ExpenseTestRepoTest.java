package com.akshay.expenseTracker.repo;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDateTime;
import java.util.Date;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.akshay.expenseTracker.models.ExpenseTest;
import com.akshay.expenseTracker.models.SalaryType;

@SpringBootTest
class ExpenseTestRepoTest {

	@Autowired
	private ExpenseTestRepo expenseTestRepo;
	
	@Test
	void saveExpenseTest() {
		Date currentDate = new Date();
		LocalDateTime dateTime = LocalDateTime.now();
		ExpenseTest expenseTest = ExpenseTest.builder()
				.expenseAmount(200.00)
				.SalaryType(SalaryType.Tentative.toString())
				.categoryId(1L)
				.categoryName("MISCELLENEOUS_1")
				.totalAmount(90000.00)
				.expensePercentage(2.00)
				.addedDate(currentDate)
				.addedDateTime(LocalDateTime.now())
				.build(); 
		
		expenseTestRepo.save(expenseTest);
	}

}
