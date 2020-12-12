package com.expense.manager.repositry;

import org.springframework.data.jpa.repository.JpaRepository;

import com.expense.manager.model.UserDebt;

public interface ExpenseRepositry extends JpaRepository<UserDebt, String> {

}
