package com.akshay.expenseTracker.models;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.stereotype.Component;

import lombok.Data;

@Component
@Data
public class ExpenditureResponse {

	private BigDecimal totalExpense;
	private List<ExpenseInfo> expenseList; 
}
