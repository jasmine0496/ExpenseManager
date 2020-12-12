package com.expense.manager.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.DisplayName;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import com.expense.manager.model.ExpenseResponse;
import com.expense.manager.model.UserDebt;
import com.expense.manager.repositry.ExpenseRepositry;

@DisplayName("Test Expense Manager Service")
@ExtendWith({MockitoExtension.class})
public class ExpenseManagerServiceImplTest {

	@InjectMocks
	private ExpenseManagerServiceImpl expenseManagerServiceImpl;
	
	@Mock
	ExpenseRepositry expenseRepositry;

	@DisplayName("Test Create User")
	@Test
	void testCreateUser() {
		UserDebt userDebt = new UserDebt();
		userDebt.setUserName("xyz");
		Mockito.when(expenseRepositry.save(Mockito.any())).thenReturn(userDebt);
		userDebt = expenseManagerServiceImpl.createUser(userDebt);
		Mockito.verify(expenseRepositry).save(userDebt);
	}
	
	@DisplayName("Test Add Expense")
	@Test
	void testAddExpense() {
		Map<String, String> expense = new HashMap<>();
		expense.put("name", "Room rent");
		expense.put("amount", "10000");
		expense.put("paid_by", "xyz");
		expense.put("split_by", "equal");
		UserDebt userDebt1 = new UserDebt();
		userDebt1.setUserName("xyz");
		userDebt1.setDebtAmount(10000);
		UserDebt userDebt2 = new UserDebt();
		userDebt2.setUserName("abc");
		userDebt2.setDebtAmount(-10000);
		List<UserDebt> userDebtList = new ArrayList<>();
		userDebtList.add(userDebt1);
		userDebtList.add(userDebt2);
		Mockito.when(expenseRepositry.findAll()).thenReturn(userDebtList);
		Mockito.when(expenseRepositry.save(Mockito.any())).thenReturn(userDebt1).thenReturn(userDebt2);
		List<ExpenseResponse> expenseResponseList = expenseManagerServiceImpl.addExpense(expense);
		Mockito.verify(expenseRepositry).findAll();
		Mockito.verify(expenseRepositry, times(2)).save(Mockito.any());
		assertEquals("15000.0", expenseResponseList.get(0).getAmount().toString());
	}
}