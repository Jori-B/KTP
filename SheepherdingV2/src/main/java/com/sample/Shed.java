package com.sample;

import com.sample.Fact;

public class Shed implements VariableDefinitions {
	public Fact hasShed;
	public Fact curShedSize;
	public Fact roomForShed; 
	public Fact isAllowedToBuild;
	public Fact isTallerThan3;
	public Fact isPathWiderThan12;
	public Fact hasCementFloor;
	public Fact hasFertilizerPlate;
	public Fact hasAdjustableFences;
	public Fact hasLamps;
	public Fact goalSize; 
	public Fact problem;
	public Fact advice;
	
	public Shed() {
		
	}
	
	public Fact getHasShed() {
		return hasShed;
	}

	public void setHasShed(Fact hasShed) {
		this.hasShed = hasShed;
	}

	public Fact getCurShedSize() {
		return curShedSize;
	}

	public void setCurShedSize(Fact curShedSize) {
		this.curShedSize = curShedSize;
		
	}

	public Fact getRoomForShed() {
		return roomForShed;
	}

	public void setRoomForShed(Fact roomForShed) {
		this.roomForShed = roomForShed;
	}

	public Fact getIsAllowedToBuild() {
		return isAllowedToBuild;
	}

	public void setIsAllowedToBuild(Fact isAllowedToBuild) {
		this.isAllowedToBuild = isAllowedToBuild;
	}

	public Fact getIsTallerThan3() {
		return isTallerThan3;
	}

	public void setIsTallerThan3(Fact isTallerThan3) {
		this.isTallerThan3 = isTallerThan3;
	}

	public Fact getIsPathWiderThan12() {
		return isPathWiderThan12;
	}

	public void setIsPathWiderThan12(Fact isPathWiderThan12) {
		this.isPathWiderThan12 = isPathWiderThan12;
	}

	public Fact getHasCementFloor() {
		return hasCementFloor;
	}

	public void setHasCementFloor(Fact hasCementFloor) {
		this.hasCementFloor = hasCementFloor;
	}

	public Fact getHasFertilizerPlate() {
		return hasFertilizerPlate;
	}

	public void setHasFertilizerPlate(Fact hasFertilizerPlate) {
		this.hasFertilizerPlate = hasFertilizerPlate;
	}

	public Fact getHasAdjustableFences() {
		return hasAdjustableFences;
	}

	public void setHasAdjustableFences(Fact hasAdjustableFences) {
		this.hasAdjustableFences = hasAdjustableFences;
	}

	public Fact getHasLamps() {
		return hasLamps;
	}

	public void setHasLamps(Fact hasLamps) {
		this.hasLamps = hasLamps;
	}

	public Fact getGoalSize() {
		return goalSize;
	}

	public void setGoalSize(Fact goalSize) {
		this.goalSize = goalSize;
	}

	public Fact getProblem() {
		return problem;
	}

	public void setProblem(Fact problem) {
		this.problem = problem;
	}

	public Fact getAdvice() {
		return advice;
	}

	public void setAdvice(Fact advice) {
		this.advice = advice;
	}
	
}
