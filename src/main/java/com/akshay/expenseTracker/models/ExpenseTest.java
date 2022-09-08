package com.akshay.expenseTracker.models;

import java.time.LocalDateTime;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ExpenseTest {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private Double expenseAmount;
	private String SalaryType;
	private Long categoryId;
	private String categoryName;
	private Double totalAmount;
	private Double expensePercentage;
	private Date addedDate;
	private LocalDateTime addedDateTime;
	
	/*public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public Double getExpenseAmount() {
		return expenseAmount;
	}
	public void setExpenseAmount(Double expenseAmount) {
		this.expenseAmount = expenseAmount;
	}
	public String getSalaryType() {
		return SalaryType;
	}
	public void setSalaryType(String salaryType) {
		SalaryType = salaryType;
	}
	public long getCategoryId() {
		return categoryId;
	}
	public void setCategoryId(long categoryId) {
		this.categoryId = categoryId;
	}
	public String getCategoryName() {
		return categoryName;
	}
	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}
	public Double getTotalAmount() {
		return totalAmount;
	}
	public void setTotalAmount(Double totalAmount) {
		this.totalAmount = totalAmount;
	}
	public Double getExpensePercentage() {
		return expensePercentage;
	}
	public void setExpensePercentage(Double expensePercentage) {
		this.expensePercentage = expensePercentage;
	} */
}
