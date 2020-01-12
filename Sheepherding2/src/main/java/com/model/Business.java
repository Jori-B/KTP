package com.model;

import com.model.Question;

public class Business extends Category implements VariableDefinitions {
	public double hobSemiPro;
	public boolean isUBNRegistered;
	public boolean isKvKRegistered;
	public double moneyToSpend; 
	public int timeWillingToSpend;
	public int timeRequired; 
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
	
	public void setTimeRequired(int totalNSheepWanted) {
		/* Johan said with 200 sheep you'd need to spend 7 days a week, but you're a pro herder */
		if (totalNSheepWanted < 200) {
			/* For 100 sheep you'd roughly spend 4 days, which this calculation encompasses 
			 * The system therefore assumes you need 1 day per 25 sheep. */
			this.timeRequired = (int)(totalNSheepWanted / 25);
			if(timeRequired < 1) {
				this.timeRequired = 1;
			}
		} else {
			this.timeRequired = 7;
		}
		System.out.println(timeRequired + " days ");
		
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
	
	public double getTimeRequired() {
		return timeRequired;
	}
}
