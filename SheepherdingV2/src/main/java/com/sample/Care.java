package com.sample;

import com.sample.Fact;

public class Care implements VariableDefinitions {
	public Fact wantsSelfShave;
	public Fact wantsSelfBirth;
	public double shaveOtherCost;
	public double woolEarnings;
	public Fact advice;
	
	public Care() {
		
	}
	
	public void calcShaveOtherCost(int totalNSheepWanted) {
		this.shaveOtherCost = totalNSheepWanted * 3.5;
	}
	
	public void calcWoolEarning(int totalNSheepWanted) {
		this.woolEarnings = totalNSheepWanted * 1.5;
	}

	public Fact getWantsSelfShave() {
		return wantsSelfShave;
	}

	public void setWantsSelfShave(Fact wantsSelfShave) {
		this.wantsSelfShave = wantsSelfShave;
	}

	public Fact getWantsSelfBirth() {
		return wantsSelfBirth;
	}

	public void setWantsSelfBirth(Fact wantsSelfBirth) {
		this.wantsSelfBirth = wantsSelfBirth;
	}

	public double getShaveOtherCost() {
		return shaveOtherCost;
	}

	public void setShaveOtherCost(double shaveOtherCost) {
		this.shaveOtherCost = shaveOtherCost;
	}

	public double getWoolEarnings() {
		return woolEarnings;
	}

	public void setWoolEarnings(double woolEarnings) {
		this.woolEarnings = woolEarnings;
	}

	public Fact getAdvice() {
		return advice;
	}

	public void setAdvice(Fact advice) {
		this.advice = advice;
	}

}
