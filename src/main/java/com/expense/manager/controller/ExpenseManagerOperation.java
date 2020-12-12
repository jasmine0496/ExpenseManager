package com.expense.manager.controller;

import java.util.List;
import java.util.Map;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.expense.manager.model.ExpenseResponse;
import com.expense.manager.model.UserDebt;

public interface ExpenseManagerOperation {

	@PostMapping(value = "/createuser", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody UserDebt createUser(@RequestBody UserDebt user);
	
	@PostMapping(value = "/addexpense", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody List<ExpenseResponse> addExpense(@RequestBody Map<String, String> expense);

}
