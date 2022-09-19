package com.akshay.expenseTracker.repo;

import com.akshay.expenseTracker.models.ExpenseInfo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ExpenseInfoRepo extends JpaRepository<ExpenseInfo, Long>{

	@Query("select e from ExpenseInfo e where (:expenseMonth is null or e.expenseMonth = :expenseMonth) and (:expenseYear is null or e.expenseYear = :expenseYear)")
	List<ExpenseInfo> getExpenseByExpenseMonthAndExpenseYear(@Param("expenseMonth") Integer expenseMonth, @Param("expenseYear") Integer expenseYear);
}
