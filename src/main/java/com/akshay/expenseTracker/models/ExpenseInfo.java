package com.akshay.expenseTracker.models;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Transient;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ExpenseInfo {
	
	@Id
	@SequenceGenerator(name = "expense_info_seq", sequenceName = "expense_info_seq", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "expense_info_seq")
	private Long expenseId;
	
	private BigDecimal expenseAmount;
	private String SalaryType;
	private Long categoryId;
	private String categoryName;
	private String expenseDescription;
	//private BigDecimal totalAmount;
	//private BigDecimal expensePercentage;
	private Integer expenseDay;
	private Integer expenseMonth;
	private Integer expenseYear;
	private LocalDateTime addedDateTime;
	private LocalDateTime updatedDateTime;
	
	@Transient
	private BigDecimal percentExpense;
}
