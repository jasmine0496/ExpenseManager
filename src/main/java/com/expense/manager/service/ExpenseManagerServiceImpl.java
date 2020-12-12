package com.expense.manager.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.expense.manager.model.ExpenseResponse;
import com.expense.manager.model.UserDebt;
import com.expense.manager.repositry.ExpenseRepositry;

@Service
public class ExpenseManagerServiceImpl implements ExpenseManagerService {

	@Autowired
	ExpenseRepositry expenseRepositry;

	@Override
	public UserDebt createUser(UserDebt user) {
		return expenseRepositry.save(user);
	}

	@Override
	public List<ExpenseResponse> addExpense(Map<String, String> expense) {
		String splitBy = (String) expense.get("split_by");
		List<ExpenseResponse> expenseResponseList = new ArrayList<>();
		if (splitBy.equalsIgnoreCase("equal")) {
			expenseResponseList = addEqualExpense(expense);
		}
		return expenseResponseList;
	}

	private List<ExpenseResponse> addEqualExpense(Map<String, String> expense) {
		List<ExpenseResponse> expenseResponseList = new ArrayList<>();
		List<UserDebt> userDebtList = expenseRepositry.findAll();
		int userCount = userDebtList.size();
		String paidUser = expense.get("paid_by");
		float paidAmount = Float.parseFloat(expense.get("amount"));
		float individualAmount = paidAmount / userCount;
		for (int i = 0; i < userCount; i++) {
			float pastAmount = userDebtList.get(i).getDebtAmount();
			if (userDebtList.get(i).getUserName().equalsIgnoreCase(paidUser)) {
				userDebtList.get(i).setDebtAmount(pastAmount + paidAmount - individualAmount);
			} else {
				userDebtList.get(i).setDebtAmount(pastAmount - individualAmount);
			}
			expenseRepositry.save(userDebtList.get(i));
		}
		Collections.sort(userDebtList, new UserDebt());

		int i = 0;
		int j = userCount - 1;
		float debt;
		while (i < j) {
			float pastAmount1 = userDebtList.get(i).getDebtAmount();
			float pastAmount2 = userDebtList.get(j).getDebtAmount();
			debt = Math.min(-(pastAmount1), pastAmount2);
			userDebtList.get(i).setDebtAmount(pastAmount1 + debt);
			userDebtList.get(j).setDebtAmount(pastAmount2 - debt);
			ExpenseResponse expenseResponse = new ExpenseResponse();
			expenseResponse.setDebitFrom(userDebtList.get(i).getUserName());
			expenseResponse.setCreditTo(userDebtList.get(j).getUserName());
			expenseResponse.setAmount(debt);
			expenseResponseList.add(expenseResponse);
			System.out.println(expenseResponse.getDebitFrom() + " owes " + expenseResponse.getCreditTo() +" "+ expenseResponse.getAmount());

			if (userDebtList.get(i).getDebtAmount() == 0.0) {
				i++;
			}
			if (userDebtList.get(j).getDebtAmount() == 0.0) {
				j--;
			}
		}
		return expenseResponseList;

	}

}
