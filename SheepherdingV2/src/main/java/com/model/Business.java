package com.model;

import com.model.Question;

public class Business implements VariableDefinitions {
	public int hobSemiPro;
	public int isUBNRegistered;
	public int isKvKRegistered;
	public int moneyToSpend; // We still need a total calculation for if money to spend is smaller than money needed
	public int timeWillingToSpend;
	public int timeRequired; // This is not calculated yet
	//public int slaugBreedBoth;
	public int moneyNeeded; // This is not calculated yet
	//public int hasEnoughMoney;
	public String advice = "<html>";
	
	public Business() {
		
	}

	public int getHobSemiPro() {
		return hobSemiPro;
	}

	public void setHobSemiPro(Question hobSemiPro) {
		this.hobSemiPro = hobSemiPro.getAnswer();
	}

	public int getIsUBNRegistered() {
		return isUBNRegistered;
	}

	public void setIsUBNRegistered(Question isUBNRegistered) {
		this.isUBNRegistered = isUBNRegistered.getAnswer();
	}

	public int getIsKvKRegistered() {
		return isKvKRegistered;
	}

	public void setIsKvKRegistered(Question isKvKRegistered) {
		this.isKvKRegistered = isKvKRegistered.getAnswer();
	}

	public int getMoneyToSpend() {
		return moneyToSpend;
	}

	public void setMoneyToSpend(Question moneyToSpend) {
		this.moneyToSpend = moneyToSpend.getAnswer();
	}

	public int getTimeWillingToSpend() {
		return timeWillingToSpend;
	}

	public void setTimeWillingToSpend(Question timeWillingToSpend) {
		this.timeWillingToSpend = timeWillingToSpend.getAnswer();
	}

	public String getAdvice() {
		return advice;
	}

	public void setAdvice(String advice) {
		if(this.advice.equals("<html>")) {
			this.advice = this.advice + advice;
		} else {
			this.advice = this.advice + "<br>" +  advice;
		}
	}
	
	
	
}
