package com.model;

public class Category implements VariableDefinitions {
	public String advice = "<html>&emsp;";
	
	public static boolean doubleToBoolean(double answer) {
    	if(answer == YES) {
    		return true;
    	} else { 
    		return false;
    	}
    }
	
	public String getAdvice() {
		return advice;
	}

	public void setAdvice(String advice) {
		if(this.advice.equals("<html>&emsp;")) {
			this.advice = this.advice + advice;
		} else {
			this.advice = this.advice + "<br>&emsp;" + advice;
		}
	}
}
