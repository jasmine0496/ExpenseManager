package com.expense.manager.service;

import java.util.List;
import java.util.Map;

import com.expense.manager.model.ExpenseResponse;
import com.expense.manager.model.UserDebt;

public interface ExpenseManagerService {
	
	public UserDebt createUser(UserDebt user);
	
	public List<ExpenseResponse> addExpense(Map<String, String> expense);

}
