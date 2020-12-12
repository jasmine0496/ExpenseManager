package com.expense.manager.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import com.expense.manager.model.ExpenseResponse;
import com.expense.manager.model.UserDebt;
import com.expense.manager.service.ExpenseManagerService;

@RestController
public class ExpenseManagerController implements ExpenseManagerOperation {

	public ExpenseManagerService expenseManagerService;

	@Autowired
	public ExpenseManagerController(ExpenseManagerService expenseManagerService) {
		this.expenseManagerService = expenseManagerService;
	}

	@Override
	public UserDebt createUser(UserDebt user) {
		return expenseManagerService.createUser(user);
	}

	@Override
	public List<ExpenseResponse> addExpense(Map<String, String> expense) {
		return expenseManagerService.addExpense(expense);
	}

}
