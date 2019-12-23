package com.sample;

import com.sample.Fact;

public class Business {
	public Fact hobSemiPro;
	public Fact level;
	public Fact isUBNRegistered;
	public Fact isKvKRegistered;
	public Fact moneyToSpend; // We still need a total calculation for if money to spend is smaller than money needed
	public Fact timeWillingToSpend;
	public Fact advice;
	
	public Business() {
		
	}

	public Fact getHobSemiPro() {
		return hobSemiPro;
	}

	public void setHobSemiPro(Fact hobSemiPro) {
		this.hobSemiPro = hobSemiPro;
	}

	public Fact getLevel() {
		return level;
	}

	public void setLevel(Fact level) {
		this.level = level;
	}

	public Fact getIsUBNRegistered() {
		return isUBNRegistered;
	}

	public void setIsUBNRegistered(Fact isUBNRegistered) {
		this.isUBNRegistered = isUBNRegistered;
	}

	public Fact getIsKvKRegistered() {
		return isKvKRegistered;
	}

	public void setIsKvKRegistered(Fact isKvKRegistered) {
		this.isKvKRegistered = isKvKRegistered;
	}

	public Fact getMoneyToSpend() {
		return moneyToSpend;
	}

	public void setMoneyToSpend(Fact moneyToSpend) {
		this.moneyToSpend = moneyToSpend;
	}

	public Fact getTimeWillingToSpend() {
		return timeWillingToSpend;
	}

	public void setTimeWillingToSpend(Fact timeWillingToSpend) {
		this.timeWillingToSpend = timeWillingToSpend;
	}

	public Fact getAdvice() {
		return advice;
	}

	public void setAdvice(Fact advice) {
		this.advice = advice;
	}
	
	
	
}
