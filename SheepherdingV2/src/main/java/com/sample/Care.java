package com.sample;

import com.sample.Fact;

public class Care implements VariableDefinitions {
	public int wantsLambs;
	public int wantsSelfShave;
	public int wantsSelfBirth;
	public double shaveOtherCost;
	public double woolEarnings;
	// public int sheepBorn;
	// public int sheepSoldEarnings;
	public Fact advice;
	
	public Care() {
		
	}
	
	public void calcShaveOtherCost(int totalNSheepWanted) {
		this.shaveOtherCost = totalNSheepWanted * 3.5;
	}
	
	public void calcWoolEarning(int totalNSheepWanted) {
		this.woolEarnings = totalNSheepWanted * 1.5;
	}
	
	public int getWantsLambs() {
		return wantsLambs;
	}

	public void setWantsLambs(Fact wantsLambs) {
		this.wantsSelfShave = wantsLambs.getAnswer();
	}
	
	public int getWantsSelfShave() {
		return wantsSelfShave;
	}

	public void setWantsSelfShave(Fact wantsSelfShave) {
		this.wantsSelfShave = wantsSelfShave.getAnswer();
	}

	public int getWantsSelfBirth() {
		return wantsSelfBirth;
	}

	public void setWantsSelfBirth(Fact wantsSelfBirth) {
		this.wantsSelfBirth = wantsSelfBirth.getAnswer();
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
