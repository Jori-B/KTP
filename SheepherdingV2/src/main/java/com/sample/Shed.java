package com.sample;

import com.sample.Question;

public class Shed implements VariableDefinitions {
	public int hasShed;
	public int curShedSize;
	public double roomForShed; 
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

	public void setHasShed(Question hasShed) {
		this.hasShed = hasShed.getAnswer();
	}

	public int getCurShedSize() {
		return curShedSize;
	}

	public void setCurShedSize(Question curShedSize) {
		this.curShedSize = curShedSize.getAnswer();
		
	}

	public double getRoomForShed() {
		return roomForShed;
	}

	public void setRoomForShed(Question roomForShed) {
		this.roomForShed = (double)roomForShed.getAnswer();
	}

	public int getIsAllowedToBuild() {
		return isAllowedToBuild;
	}

	public void setIsAllowedToBuild(Question isAllowedToBuild) {
		this.isAllowedToBuild = isAllowedToBuild.getAnswer();
	}

	public int getIsTallerThan3() {
		return isTallerThan3;
	}

	public void setIsTallerThan3(Question height) {
		if(height.getAnswer() >= 3) {
			this.isTallerThan3 = YES;
			System.out.println("this.tall = YES;");
		} else {
			this.isTallerThan3 = NO;
			System.out.println("this.tall = NO;");
		}
	}

	public int getIsPathWiderThan12() {
		return isPathWiderThan12;
	}

	public void setIsPathWiderThan12(Question width) {
		if(width.getAnswer() >= 12) {
			System.out.println("this.isPathWiderThan12 = YES;");
			this.isPathWiderThan12 = YES;
		} else {
			this.isPathWiderThan12 = NO;
			System.out.println("this.isPathWiderThan12 = NO;");
		}
		
	}

	public int getHasCementFloor() {
		return hasCementFloor;
	}

	public void setHasCementFloor(Question hasCementFloor) {
		this.hasCementFloor = hasCementFloor.getAnswer();
	}

	public int getHasFertilizerPlate() {
		return hasFertilizerPlate;
	}

	public void setHasFertilizerPlate(Question hasFertilizerPlate) {
		this.hasFertilizerPlate = hasFertilizerPlate.getAnswer();
	}

	public int getHasAdjustableFences() {
		return hasAdjustableFences;
	}

	public void setHasAdjustableFences(Question hasAdjustableFences) {
		this.hasAdjustableFences = hasAdjustableFences.getAnswer();
	}

	public int getHasLamps() {
		return hasLamps;
	}

	public void setHasLamps(Question hasLamps) {
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

	public void setProblem(Question problem) {
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
