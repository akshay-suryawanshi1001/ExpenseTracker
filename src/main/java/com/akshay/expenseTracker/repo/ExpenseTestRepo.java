package com.akshay.expenseTracker.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import com.akshay.expenseTracker.models.ExpenseTest;

public interface ExpenseTestRepo extends JpaRepository<ExpenseTest, Long>{

}
