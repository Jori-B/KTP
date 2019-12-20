package com.sample;

import com.sample.Fact;

public class Care implements VariableDefinitions {
	public Fact wantsSelfShave;
	public Fact wantsSelfBirth;
	public Fact shaveOtherCost;
	public Fact woolEarnings;
	public Fact advice;
	
	public Care() {
		
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

	public Fact getShaveOtherCost() {
		return shaveOtherCost;
	}

	public void setShaveOtherCost(Fact shaveOtherCost) {
		this.shaveOtherCost = shaveOtherCost;
	}

	public Fact getWoolEarnings() {
		return woolEarnings;
	}

	public void setWoolEarnings(Fact woolEarnings) {
		this.woolEarnings = woolEarnings;
	}

	public Fact getAdvice() {
		return advice;
	}

	public void setAdvice(Fact advice) {
		this.advice = advice;
	}
	
	
	
}
