package com.model;

import com.model.Question;

public class Care extends Category implements VariableDefinitions {
	public boolean wantsLambs;
	public boolean wantsSelfShave;
	public boolean wantsSelfBirth;
	public double shaveOtherCost;
	public double woolEarnings;
	public boolean wantsSlaughter;
	// public int sheepBorn;
	// public int sheepSoldEarnings;
	// public int costOtherBirth;
//	public String advice = "<html>&emsp;";
	
	public Care() {
		
	}
	
	public void calcShaveOtherCost(int totalNSheepWanted) {
		this.shaveOtherCost = totalNSheepWanted * 3.5;
	}
	
	public void calcWoolEarning(int totalNSheepWanted) {
		this.woolEarnings = totalNSheepWanted * 1.5;
	}
	
	public boolean getWantsLambs() {
		return wantsLambs;
	}

	public void setWantsLambs(Question wantsLambs) {
		this.wantsSelfShave = doubleToBoolean(wantsLambs.getAnswer());
	}
	
	public boolean getWantsSelfShave() {
		return wantsSelfShave;
	}

	public void setWantsSelfShave(Question wantsSelfShave) {
		this.wantsSelfShave = doubleToBoolean(wantsSelfShave.getAnswer());
	}

	public boolean getWantsSelfBirth() {
		return wantsSelfBirth;
	}

	public void setWantsSelfBirth(Question wantsSelfBirth) {
		this.wantsSelfBirth = doubleToBoolean(wantsSelfBirth.getAnswer());
	}
	
	public void setWantsSlaughter(Question wantsSlaughter) {
		if(wantsSlaughter.getAnswer() == YES) {
			this.wantsSlaughter = true;
		} else {
			this.wantsSlaughter = false;
		}
		
	}
	
	public boolean getWantsSlaughter() {
		return wantsSlaughter;
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

}
