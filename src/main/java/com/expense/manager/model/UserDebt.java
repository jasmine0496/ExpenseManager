package com.expense.manager.model;

import java.util.Comparator;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "userdebt")
public class UserDebt implements Comparator<UserDebt>{

	@Id
	private String username;
	private float debtamount;

	public UserDebt() {
		super();
	}

	public UserDebt(String userName, float debtAmount) {
		super();
		this.username = userName;
		this.debtamount = debtAmount;
	}

	public String getUserName() {
		return username;
	}

	public void setUserName(String userName) {
		this.username = userName;
	}

	public float getDebtAmount() {
		return debtamount;
	}

	public void setDebtAmount(float debtAmount) {
		this.debtamount = debtAmount;
	}

	@Override
	public int compare(UserDebt arg0, UserDebt arg1) {
		return (int) (arg0.getDebtAmount() - arg1.getDebtAmount()) ;
	}


}
