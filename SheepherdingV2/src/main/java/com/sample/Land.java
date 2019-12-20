package com.sample;

import com.sample.Fact;

public class Land implements VariableDefinitions {
	public Fact hasLand;
	public Fact ownedLandSize;
	public Fact hasLeasedLand;
	public Fact leasedLandSize;
	public Fact totalLandSize;
	public Fact landNeeded;
	public Fact costPhosphateRights;
	public Fact costLandNeeded;
	public Fact advice;
	
	public Land() {
		
	}

	public Fact getHasLand() {
		return hasLand;
	}

	public void setHasLand(Fact hasLand) {
		this.hasLand = hasLand;
	}

	public Fact getOwnedLandSize() {
		return ownedLandSize;
	}

	public void setOwnedLandSize(Fact ownedLandSize) {
		this.ownedLandSize = ownedLandSize;
	}

	public Fact getHasLeasedLand() {
		return hasLeasedLand;
	}

	public void setHasLeasedLand(Fact hasLeasedLand) {
		this.hasLeasedLand = hasLeasedLand;
	}

	public Fact getLeasedLandSize() {
		return leasedLandSize;
	}

	public void setLeasedLandSize(Fact leasedLandSize) {
		this.leasedLandSize = leasedLandSize;
	}

	public Fact getTotalLandSize() {
		return totalLandSize;
	}

	public void setTotalLandSize(Fact totalLandSize) {
		this.totalLandSize = totalLandSize;
	}

	public Fact getLandNeeded() {
		return landNeeded;
	}

	public void setLandNeeded(Fact landNeeded) {
		this.landNeeded = landNeeded;
	}

	public Fact getCostPhosphateRights() {
		return costPhosphateRights;
	}

	public void setCostPhosphateRights(Fact costPhosphateRights) {
		this.costPhosphateRights = costPhosphateRights;
	}

	public Fact getCostLandNeeded() {
		return costLandNeeded;
	}

	public void setCostLandNeeded(Fact costLandNeeded) {
		this.costLandNeeded = costLandNeeded;
	}

	public Fact getAdvice() {
		return advice;
	}

	public void setAdvice(Fact advice) {
		this.advice = advice;
	}
	
	
}
