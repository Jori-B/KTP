package com.sample;

import com.sample.Fact;

public class Land implements VariableDefinitions {
	public int hasLand;
	public int ownedLandSize;
	public int hasLeasedLand;
	public int leasedLandSize;
	public int totalLandSize;
	public double landNeeded;
	public int costPhosphateRights;
	public double costLandNeeded;
	public boolean landIsBigEnough;
	public Fact advice;
	
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
	
	public void calcPhosphateRights(int totalNSheepWanted) { 
		this.costPhosphateRights = 90 * totalNSheepWanted; 
	} 
	
	public void calcCostLandNeeded() { 
		this.costLandNeeded = 50000 * landNeeded; 
	} 
	
//	public void calcToeslagRecht() {
//		
//	}

	/* Getters and setters */
	public int getHasLand() {
		return hasLand;
	}

	public void setHasLand(Fact hasLand) {
		this.hasLand = hasLand.getAnswer();
	}

	public int getOwnedLandSize() {
		return ownedLandSize;
	}

	public void setOwnedLandSize(Fact ownedLandSize) {
		this.ownedLandSize = ownedLandSize.getAnswer();
	}

	public int getHasLeasedLand() {
		return hasLeasedLand;
	}

	public void setHasLeasedLand(Fact hasLeasedLand) {
		this.hasLeasedLand = hasLeasedLand.getAnswer();
	}

	public int getLeasedLandSize() {
		return leasedLandSize;
	}

	public void setLeasedLandSize(Fact leasedLandSize) {
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

	public int getCostPhosphateRights() {
		return costPhosphateRights;
	}

	public void setCostPhosphateRights(int costPhosphateRights) {
		this.costPhosphateRights = costPhosphateRights;
	}

	public double getCostLandNeeded() {
		return costLandNeeded;
	}

	public void setCostLandNeeded(double costLandNeeded) {
		this.costLandNeeded = costLandNeeded;
	}

	public Fact getAdvice() {
		return advice;
	}

	public void setAdvice(Fact advice) {
		this.advice = advice;
	}
	
	
}
