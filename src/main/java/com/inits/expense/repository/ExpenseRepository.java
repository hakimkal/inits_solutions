package com.inits.expense.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.inits.expense.model.Expense;

@Repository
public interface ExpenseRepository extends JpaRepository<Expense, Long>{

}
