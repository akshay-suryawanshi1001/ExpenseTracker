package com.akshay.expenseTracker.repo;

import org.springframework.stereotype.Repository;
import com.akshay.expenseTracker.models.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee,Long>{

}
