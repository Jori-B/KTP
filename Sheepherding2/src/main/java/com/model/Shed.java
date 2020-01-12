package com.model;

import com.model.Question;

public class Shed extends Category implements VariableDefinitions {
	public boolean hasShed;
	public double curShedSize; 
	public double roomForShed; 
	public boolean isAllowedToBuild;
	public double shedHeight;
	public boolean isTallerThan3;
	public double pathWidth;
	public boolean isPathWiderThan3;
	public double widthShed;
	public double lengthShed;
	public boolean hasCementFloor;
	public boolean hasFertilizerPlate;
	public boolean hasAdjustableFences;
	public boolean hasEatingFences;
	public boolean hasElectricity;
	public boolean hasWater;
	public double goalSize; 
	public double lengthGoal;
	public double goalCurSizeDiff;
	public boolean shedTooSmall;
	
	public Shed() {
		
	}
	
	/* Calculations */
	public double calcGoalSize(int totalNSheepWanted) { 
		/* A shed should have roughly 3 meters of path in the middle
		 * the width of the shed besides the path needs to be 4 meters on both sides of it to hold sheep there
		 * Adding this up yields a general width of 11 meters (4+3+4)
		 * 2.5 square meters per sheep is needed in a shed. Dividing this by 11 yields the required length
		 */
		double sheepRoomRequired = (totalNSheepWanted * 2.5);
		this.lengthGoal =  sheepRoomRequired / 11;
		/* After this the length needs to be multiplied by the path width needed 
		 * This is then added to find the final goal size
		 */
		double goalSize = (sheepRoomRequired + (lengthGoal * 3));
		this.goalSize = goalSize;
		/* Return is used for the rule file */
		return goalSize;
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
	
	public void setWidthAndLength(double width, double length) {
    	double temp = width;
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
	public boolean getHasShed() {
		return hasShed;
	}

	public void setHasShed(Question hasShed) {
		this.hasShed = doubleToBoolean(hasShed.getAnswer());
	}

	public double getCurShedSize() {
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

	public boolean getIsAllowedToBuild() {
		return isAllowedToBuild;
	}

	public void setIsAllowedToBuild(Question isAllowedToBuild) {
		this.isAllowedToBuild = doubleToBoolean(isAllowedToBuild.getAnswer());
	}

	public boolean getIsTallerThan3() {
		return isTallerThan3;
	}

	public void setIsTallerThan3(Question height) {
		this.shedHeight = height.getAnswer();
		if(height.getAnswer() >= 3) {
			this.isTallerThan3 = true;
			System.out.println("this.tall = YES;");
		} else {
			this.isTallerThan3 = false;
			System.out.println("this.tall = NO;");
		}
	}

	public boolean getisPathWiderThan3() {
		return isPathWiderThan3;
	}

	public void setIsPathWiderThan3(Question pathWidth) {
		this.pathWidth = pathWidth.getAnswer();
		if(pathWidth.getAnswer() >= 3) {
			System.out.println("this.isPathWiderThan3 = YES;");
			this.isPathWiderThan3 = true;
		} else {
			this.isPathWiderThan3 = false;
			System.out.println("this.isPathWiderThan3 = NO;");
		}
		
	}

	public double getPathWidth() {
		return pathWidth;
	}

	public void setPathWidth(double pathWidth) {
		this.pathWidth = pathWidth;
	}
	
	public double getWidthShed() {
		return widthShed;
	}

	public void setWidthShed(int widthShed) {
		this.widthShed = widthShed;
	}
	
	public double getLengthShed() {
		return lengthShed;
	}

	public void setLengthShed(int lengthShed) {
		this.lengthShed = lengthShed;
	}

	public boolean getHasFertilizerPlate() {
		return hasFertilizerPlate;
	}

	public void setHasFertilizerPlate(Question hasFertilizerPlate) {
		this.hasFertilizerPlate = doubleToBoolean(hasFertilizerPlate.getAnswer());
	}
	
	public boolean getHasCementFloor() {
		return hasCementFloor;
	}

	public void setHasCementFloor(Question hasCementFloor) {
		this.hasCementFloor = doubleToBoolean(hasCementFloor.getAnswer());
	}

	public boolean getHasAdjustableFences() {
		return hasAdjustableFences;
	}

	public void setHasAdjustableFences(Question hasAdjustableFences) {
		this.hasAdjustableFences = doubleToBoolean(hasAdjustableFences.getAnswer());
	}
	
	public boolean getHasEatingFences() {
		return hasEatingFences;
	}
	
	public void setHasEatingFences(Question hasEatingFences) {
		this.hasEatingFences = doubleToBoolean(hasEatingFences.getAnswer());
	}

	public boolean getHasElectricity() {
		return hasElectricity;
	}

	public void setHasElectricity(Question hasElectricity) {
		this.hasElectricity = doubleToBoolean(hasElectricity.getAnswer());
	}
	
	public boolean getHasWater() {
		return hasWater;
	}
	public void setHasWater(Question hasWater) {
		this.hasWater = doubleToBoolean(hasWater.getAnswer());
	}

	public double getGoalSize() {
		return goalSize;
	}

	public void setGoalSize(double goalSize) {
		this.goalSize = goalSize;
	}
	
	public double getLengthGoal() {
		return lengthGoal;
	}
	
	public double getGoalCurSizeDiff() {
		return goalCurSizeDiff;
	}
	
	public void setGoalCurSizeDiff(double goalCurSizeDiff) {
		this.goalCurSizeDiff = goalCurSizeDiff;
	}

	public String getAdvice() {
		return advice;
	}
	
}
