package com.model;

import com.model.Question;

public class Land implements VariableDefinitions {
	public int hasLand;
	public int ownedLandSize;
	public int hasLeasedLand;
	public int leasedLandSize;
	public int totalLandSize;
	public int toeslagrecht;
	public double landNeeded;
	public double costLandNeeded;
	public boolean landIsBigEnough;
	public String advice = "<html>";
	
	public Land() {
		
	}
	
	/* Calculations */
	public void calcLandSize() { 
		
		this.totalLandSize = 0;
		if(hasLand == YES) {
			this.totalLandSize += ownedLandSize;
		} 
		/* When these questions are in the system uncomment this ! */
		if (hasLeasedLand == YES) {
			this.totalLandSize += leasedLandSize;
		}
	}
	
	public void calcLandNeeded(int totalNSheepWanted) { 
		this.landNeeded = (totalNSheepWanted * 2.85 - totalLandSize);
		if(landNeeded < 0) {
			this.landIsBigEnough = true;
		} else {
			this.landIsBigEnough = false;
		}
		/* 2.5 times the amount of sheep is arbitrary*/
	}
	
	public void calcCostLandNeeded() { 
		this.costLandNeeded = 50000 * landNeeded; 
	} 
	
	public void calcToeslagRecht() {
		/*
		 * This doesn't reflect the fact that people might already have certain pieces
		 * of land with toeslagrechten
		 */
		this.toeslagrecht = (int)landNeeded * 300;
		
	}

	/* Getters and setters */
	public int getHasLand() {
		return hasLand;
	}

	public void setHasLand(Question hasLand) {
		this.hasLand = hasLand.getAnswer();
	}

	public int getOwnedLandSize() {
		return ownedLandSize;
	}

	public void setOwnedLandSize(Question ownedLandSize) {
		this.ownedLandSize = ownedLandSize.getAnswer();
	}

	public int getHasLeasedLand() {
		return hasLeasedLand;
	}

	public void setHasLeasedLand(Question hasLeasedLand) {
		this.hasLeasedLand = hasLeasedLand.getAnswer();
	}

	public int getLeasedLandSize() {
		return leasedLandSize;
	}

	public void setLeasedLandSize(Question leasedLandSize) {
		this.leasedLandSize = leasedLandSize.getAnswer();
	}

	public int getTotalLandSize() {
		return totalLandSize;
	}

	public void setTotalLandSize(int totalLandSize) {
		this.totalLandSize = totalLandSize;
	}

	public double getLandNeeded() {
		return landNeeded;
	}

	public void setLandNeeded(double landNeeded) {
		this.landNeeded = landNeeded;
	}

	public double getCostLandNeeded() {
		return costLandNeeded;
	}

	public void setCostLandNeeded(double costLandNeeded) {
		this.costLandNeeded = costLandNeeded;
	}
	
	public int getToeslagrecht() {
		return toeslagrecht;
	}

	public void setToeslagrecht(int toeslagrecht) {
		this.toeslagrecht = toeslagrecht;
	}


	public String getAdvice() {
		return advice;
	}

	public void setAdvice(String advice) {
		if(this.advice.equals("<html>")) {
			this.advice = this.advice + advice;
		} else {
			this.advice = this.advice + "<br>" + advice;
		}
	}
	
	
}
