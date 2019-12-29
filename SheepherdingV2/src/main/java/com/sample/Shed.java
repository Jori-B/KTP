package com.sample;

import com.sample.Fact;

public class Shed implements VariableDefinitions {
	public int hasShed;
	public int curShedSize;
	public int roomForShed; 
	public int isAllowedToBuild;
	public int isTallerThan3;
	public int isPathWiderThan12;
	public int hasCementFloor;
	public int hasFertilizerPlate;
	public int hasAdjustableFences;
	public int hasLamps;
	public double goalSize; 
	public double goalCurSizeDiff;
	public boolean shedTooSmall;
	public int problem;
	public String advice = "<html>";
	
	public Shed() {
		
	}
	
	/* Calculations */
	public void calcGoalSize(int totalNSheepWanted) { 
		this.goalSize = totalNSheepWanted * 2.5;
	}
	
	public void calcGoalCurSizeDiff() { 
		this.goalCurSizeDiff = goalSize - curShedSize;
		if(goalCurSizeDiff > 0) {
			this.shedTooSmall = true;
		} else {
			this.shedTooSmall = false;
		}
	}
	
	public boolean getShedTooSmall() {
		return shedTooSmall;
	}
	
	/* Getters and setters */
	public int getHasShed() {
		return hasShed;
	}

	public void setHasShed(Fact hasShed) {
		this.hasShed = hasShed.getAnswer();
	}

	public int getCurShedSize() {
		return curShedSize;
	}

	public void setCurShedSize(Fact curShedSize) {
		this.curShedSize = curShedSize.getAnswer();
		
	}

	public int getRoomForShed() {
		return roomForShed;
	}

	public void setRoomForShed(Fact roomForShed) {
		this.roomForShed = roomForShed.getAnswer();
	}

	public int getIsAllowedToBuild() {
		return isAllowedToBuild;
	}

	public void setIsAllowedToBuild(Fact isAllowedToBuild) {
		this.isAllowedToBuild = isAllowedToBuild.getAnswer();
	}

	public int getIsTallerThan3() {
		return isTallerThan3;
	}

	public void setIsTallerThan3(Fact isTallerThan3) {
		this.isTallerThan3 = isTallerThan3.getAnswer();
	}

	public int getIsPathWiderThan12() {
		return isPathWiderThan12;
	}

	public void setIsPathWiderThan12(Fact isPathWiderThan12) {
		this.isPathWiderThan12 = isPathWiderThan12.getAnswer();
	}

	public int getHasCementFloor() {
		return hasCementFloor;
	}

	public void setHasCementFloor(Fact hasCementFloor) {
		this.hasCementFloor = hasCementFloor.getAnswer();
	}

	public int getHasFertilizerPlate() {
		return hasFertilizerPlate;
	}

	public void setHasFertilizerPlate(Fact hasFertilizerPlate) {
		this.hasFertilizerPlate = hasFertilizerPlate.getAnswer();
	}

	public int getHasAdjustableFences() {
		return hasAdjustableFences;
	}

	public void setHasAdjustableFences(Fact hasAdjustableFences) {
		this.hasAdjustableFences = hasAdjustableFences.getAnswer();
	}

	public int getHasLamps() {
		return hasLamps;
	}

	public void setHasLamps(Fact hasLamps) {
		this.hasLamps = hasLamps.getAnswer();
	}

	public double getGoalSize() {
		return goalSize;
	}

	public void setGoalSize(double goalSize) {
		this.goalSize = goalSize;
	}
	
	public double getGoalCurSizeDiff() {
		return goalCurSizeDiff;
	}
	
	public void setGoalCurSizeDiff(double goalCurSizeDiff) {
		this.goalCurSizeDiff = goalCurSizeDiff;
	}

	public int getProblem() {
		return problem;
	}

	public void setProblem(Fact problem) {
		this.problem = problem.getAnswer();
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
