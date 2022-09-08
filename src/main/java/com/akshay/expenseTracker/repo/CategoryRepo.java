package com.akshay.expenseTracker.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.akshay.expenseTracker.models.Category;

@Repository
public interface CategoryRepo extends JpaRepository<Category, Long>{

}
