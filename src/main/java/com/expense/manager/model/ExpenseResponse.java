package com.expense.manager.model;

public class ExpenseResponse {

	String creditTo;
	String debitFrom;
	Float amount;

	public String getCreditTo() {
		return creditTo;
	}

	public void setCreditTo(String creditTo) {
		this.creditTo = creditTo;
	}

	public String getDebitFrom() {
		return debitFrom;
	}

	public void setDebitFrom(String debitFrom) {
		this.debitFrom = debitFrom;
	}

	public Float getAmount() {
		return amount;
	}

	public void setAmount(Float amount) {
		this.amount = amount;
	}

}
