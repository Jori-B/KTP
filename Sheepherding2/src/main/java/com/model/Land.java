package com.model;

import com.model.Question;

public class Land implements VariableDefinitions {
	public int hasLand;
	public int ownedLandSize;
	public int hasLeasedLand;
	public int leasedLandSize;
	public int totalLandSize;
	public int landSizeToeslag;
	public int toeslagrecht;
	public double landNeeded;
	public double costLandNeeded; /* This is the cost when you buy instead of lease */
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
	
//	public void calcToeslagRecht() {
//		/* One can expect to gain 350€ a year per acre of land with toeslagrechten */
//		this.toeslagrecht = (int)landSizeToeslag * 350;
//		
//	}

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
	
	public int getLandSizeToeslag() {
		return landSizeToeslag;
	}

	public void setLandSizeToeslag(Question landSizeToeslag) {
		this.landSizeToeslag = landSizeToeslag.getAnswer();
		/* One can expect to gain 350€ a year per acre of land with toeslagrechten */
		this.toeslagrecht = landSizeToeslag.getAnswer() * 350;
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
