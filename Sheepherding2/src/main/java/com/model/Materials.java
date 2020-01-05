package com.model;

import com.model.Question;

public class Materials implements VariableDefinitions {
	public int hasTractor;
	public int horsePowerTractor;
	public boolean needsBigTractor;
	public boolean needsNewTractor;
	public int hasMower;
	public int hasShaker;
	public int hasRaker;
	public int hasHayPacker;
	public int hasFertilizerSpreader;
	public int hasMestSpreader;
	public int hasMestGatherer;
	public String advice = "<html>&emsp;";
	
	public Materials() {
		
	}
	
	public void setNeedsBigTractor(int totalNSheepWanted) {
		if (totalNSheepWanted >= 100) {
			this.needsBigTractor = true;
		} else {
			this.needsBigTractor = false;
		}
		setNeedsNewTractor(totalNSheepWanted);
	}
	
	public void setNeedsNewTractor(int totalNSheepWanted) {
		if (hasTractor == YES) {
			if (needsBigTractor && horsePowerTractor < 50) {
				System.out.println("Needs a tractor less than 50 hp ");
				this.needsNewTractor = true;
			} else { this.needsNewTractor = false; System.out.println("No need for a tractor "); }	
		
		} else if (totalNSheepWanted > 10) {
			System.out.println("Needs a tractor ");
			this.needsNewTractor = true;
		} else {
			/* So little sheep wanted, no tractor needed */
			this.needsNewTractor = false;
			System.out.println("No need for a tractor < 10 sheep");
		}

	}

	public boolean getNeedsNewTractor() {
		return needsNewTractor;
	}
	
	public boolean getNeedsBigTractor() {
		return needsBigTractor;
	}
	
	public int getHasTractor() {
		return hasTractor;
	}

	public void setHasTractor(Question hasTractor) {
		this.hasTractor = hasTractor.getAnswer();
	}

	public int getHorsePowerTractor() {
		return horsePowerTractor;
	}

	public void setHorsePowerTractor(Question horsePowerTractor) {
		this.horsePowerTractor = horsePowerTractor.getAnswer();
	}

	public int getHasMower() {
		return hasMower;
	}

	public void setHasMower(Question hasMower) {
		this.hasMower = hasMower.getAnswer();
	}

	public int getHasShaker() {
		return hasShaker;
	}

	public void setHasShaker(Question hasShaker) {
		this.hasShaker = hasShaker.getAnswer();
	}

	public int getHasRaker() {
		return hasRaker;
	}

	public void setHasRaker(Question hasRaker) {
		this.hasRaker = hasRaker.getAnswer();
	}

	public int getHasHayPacker() {
		return hasHayPacker;
	}

	public void setHasHayPacker(Question hasHayPacker) {
		this.hasHayPacker = hasHayPacker.getAnswer();
	}

	public int getHasFertilizerSpreader() {
		return hasFertilizerSpreader;
	}

	public void setHasFertilizerSpreader(Question hasFertilizerSpreader) {
		this.hasFertilizerSpreader = hasFertilizerSpreader.getAnswer();
	}

	public int getHasMestGatherer() {
		return hasMestGatherer;
	}
	
	public void setHasMestSpreader(Question hasMestSpreader) {
		this.hasMestSpreader = hasMestSpreader.getAnswer();
	}
	
	public int getHasMestSpreader() {
		return hasMestSpreader;
	}
	

	public void setHasMestGatherer(Question hasMestGatherer) {
		this.hasMestGatherer = hasMestGatherer.getAnswer();
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
