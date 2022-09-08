package com.akshay.expenseTracker.repo;

import com.akshay.expenseTracker.models.ExpenseInfo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExpenseInfoRepo extends JpaRepository<ExpenseInfo, Long>{

}
