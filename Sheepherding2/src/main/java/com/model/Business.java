package com.model;

import com.model.Question;

public class Business extends Category implements VariableDefinitions {
	public double hobSemiPro;
	public boolean isUBNRegistered;
	public boolean isKvKRegistered;
	public double moneyToSpend; // We still need a total calculation for if money to spend is smaller than money needed
	public int timeWillingToSpend;
	public int timeRequired; // This is not calculated yet
	//public int slaugBreedBoth;
	public double moneyNeeded; 
	public boolean hasEnoughMoney;
	
	public Business() {
		
	}
	
	public void setMoneyNeeded(int moneyNeeded) {
		this.moneyNeeded = moneyNeeded;
		if (moneyNeeded >= moneyToSpend) {
			this.hasEnoughMoney = true;
		} else { this.hasEnoughMoney = false; }
	}

	public double getHobSemiPro() {
		return hobSemiPro;
	}

	public void setHobSemiPro(Question hobSemiPro) {
		this.hobSemiPro = hobSemiPro.getAnswer();
	}

	public boolean getIsUBNRegistered() {
		return isUBNRegistered;
	}

	public void setIsUBNRegistered(Question isUBNRegistered) {
		this.isUBNRegistered = doubleToBoolean(isUBNRegistered.getAnswer());
	}

	public boolean getIsKvKRegistered() {
		return isKvKRegistered;
	}

	public void setIsKvKRegistered(Question isKvKRegistered) {
		this.isKvKRegistered = doubleToBoolean(isKvKRegistered.getAnswer());
	}

	public double getMoneyToSpend() {
		return moneyToSpend;
	}

	public void setMoneyToSpend(Question moneyToSpend) {
		this.moneyToSpend = moneyToSpend.getAnswer();
	}

	public int getTimeWillingToSpend() {
		return timeWillingToSpend;
	}

	public void setTimeWillingToSpend(Question timeWillingToSpend) {
		this.timeWillingToSpend = (int) timeWillingToSpend.getAnswer();
	}
	
}
