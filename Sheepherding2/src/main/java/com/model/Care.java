package com.model;

import com.model.Question;

public class Care extends Category implements VariableDefinitions {
	public boolean wantsLambs;
	public boolean wantsSelfShave;
	public boolean wantsSelfBirth;
	public boolean wantsSlaughter;

	
	public Care() {
		
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

}
