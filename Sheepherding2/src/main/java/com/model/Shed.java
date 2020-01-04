package com.model;

import com.model.Question;

public class Shed implements VariableDefinitions {
	public int hasShed;
	public int curShedSize; 
	public double roomForShed; 
	public int isAllowedToBuild;
	public double shedHeight;
	public int isTallerThan3;
	public double pathWidth;
	public int isPathWiderThan3;
	public int widthShed;
	public int lengthShed;
	public int hasCementFloor;
	public int hasFertilizerPlate;
	public int hasAdjustableFences;
	public int hasElectricity;
	public int hasWater;
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
		System.out.println("Calculated shed size: " + curShedSize);
		this.goalCurSizeDiff = goalSize - curShedSize;
		if(goalCurSizeDiff > 0) {
			this.shedTooSmall = true;
		} else {
			this.shedTooSmall = false;
		}
	}
	
	public void setWidthAndLength(int width, int length) {
    	int temp = width;
    	/* Always set the long side as the length */
    	if(width > length) {
    		width = length;
    		length = temp;
    	}
    	System.out.println("Width: " + width);
    	System.out.println("Length: " + length);
    	this.lengthShed = length;
    	this.widthShed = width;
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
		this.shedHeight = height.getAnswer();
		if(height.getAnswer() >= 3) {
			this.isTallerThan3 = YES;
			System.out.println("this.tall = YES;");
		} else {
			this.isTallerThan3 = NO;
			System.out.println("this.tall = NO;");
		}
	}

	public int getisPathWiderThan3() {
		return isPathWiderThan3;
	}

	public void setIsPathWiderThan3(Question pathWidth) {
		this.pathWidth = pathWidth.getAnswer();
		if(pathWidth.getAnswer() >= 3) {
			System.out.println("this.isPathWiderThan3 = YES;");
			this.isPathWiderThan3 = YES;
		} else {
			this.isPathWiderThan3 = NO;
			System.out.println("this.isPathWiderThan3 = NO;");
		}
		
	}

	public double getPathWidth() {
		return pathWidth;
	}

	public void setPathWidth(double pathWidth) {
		this.pathWidth = pathWidth;
	}
	
	public int getWidthShed() {
		return widthShed;
	}

	public void setWidthShed(int widthShed) {
		this.widthShed = widthShed;
	}
	
	public int getLengthShed() {
		return lengthShed;
	}

	public void setLengthShed(int lengthShed) {
		this.lengthShed = lengthShed;
	}

	public int getHasFertilizerPlate() {
		return hasFertilizerPlate;
	}

	public void setHasFertilizerPlate(Question hasFertilizerPlate) {
		this.hasFertilizerPlate = hasFertilizerPlate.getAnswer();
	}
	
	public int getHasCementFloor() {
		return hasCementFloor;
	}

	public void setHasCementFloor(Question hasCementFloor) {
		this.hasCementFloor = hasCementFloor.getAnswer();
	}

	public int getHasAdjustableFences() {
		return hasAdjustableFences;
	}

	public void setHasAdjustableFences(Question hasAdjustableFences) {
		this.hasAdjustableFences = hasAdjustableFences.getAnswer();
	}

	public int getHasElectricity() {
		return hasElectricity;
	}

	public void setHasElectricity(Question hasElectricity) {
		this.hasElectricity = hasElectricity.getAnswer();
	}
	
	public int getHasWater() {
		return hasWater;
	}
	public void setHasWater(Question hasWater) {
		this.hasWater = hasWater.getAnswer();
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
