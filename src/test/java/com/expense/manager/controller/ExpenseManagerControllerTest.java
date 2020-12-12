package com.expense.manager.controller;

import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import com.expense.manager.model.UserDebt;
import com.expense.manager.service.ExpenseManagerService;

@DisplayName("Test Expense Manager Controller")
@ExtendWith({MockitoExtension.class})
public class ExpenseManagerControllerTest{

	@InjectMocks
	private ExpenseManagerController expenseManagerController; 
	@Mock
	private ExpenseManagerService expenseManagerService;
	
	@BeforeEach
	public void beforeEach() {
		expenseManagerController = new ExpenseManagerController(expenseManagerService);
	}
	
	@DisplayName("Test Create User")
	@Test
	void testCreateUser() {
		UserDebt userDebt = new UserDebt();
		userDebt.setUserName("xyz");
		expenseManagerController.createUser(userDebt);
		Mockito.verify(expenseManagerService).createUser(userDebt);
	}
	
	@DisplayName("Test Add Expense")
	@Test
	void testAddExpense() {
		Map<String, String> expense = new HashMap<>();
		expense.put("name", "Room rent");
		expense.put("amount", "10000");
		expense.put("paid_by", "ajay");
		expense.put("split_by", "equal");
		expenseManagerController.addExpense(expense);
		Mockito.verify(expenseManagerService).addExpense(expense);
	}


}