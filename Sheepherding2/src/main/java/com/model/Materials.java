package com.model;

import com.model.Question;

public class Materials extends Category implements VariableDefinitions {
	public boolean hasTractor;
	public int horsePowerTractor;
	public boolean needsBigTractor;
	public boolean needsNewTractor;
	public boolean hasMower;
	public boolean hasShaker;
	public boolean hasRaker;
	public boolean hasHayPacker;
	public boolean hasFertilizerSpreader;
	public boolean hasMestSpreader;
	public boolean hasMestGatherer;
	public boolean hasShaver;
	
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
		if (hasTractor) {
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
	
	public boolean getHasTractor() {
		return hasTractor;
	}

	public void setHasTractor(Question hasTractor) {
		this.hasTractor = doubleToBoolean(hasTractor.getAnswer());
	}

	public int getHorsePowerTractor() {
		return horsePowerTractor;
	}

	public void setHorsePowerTractor(Question horsePowerTractor) {
		this.horsePowerTractor = (int) horsePowerTractor.getAnswer();
	}

	public boolean getHasMower() {
		return hasMower;
	}

	public void setHasMower(Question hasMower) {
		this.hasMower = doubleToBoolean(hasMower.getAnswer());
	}

	public boolean getHasShaker() {
		return hasShaker;
	}

	public void setHasShaker(Question hasShaker) {
		this.hasShaker = doubleToBoolean(hasShaker.getAnswer());
	}

	public boolean getHasRaker() {
		return hasRaker;
	}

	public void setHasRaker(Question hasRaker) {
		this.hasRaker = doubleToBoolean(hasRaker.getAnswer());
	}

	public boolean getHasHayPacker() {
		return hasHayPacker;
	}

	public void setHasHayPacker(Question hasHayPacker) {
		this.hasHayPacker = doubleToBoolean(hasHayPacker.getAnswer());
	}

	public boolean getHasFertilizerSpreader() {
		return hasFertilizerSpreader;
	}

	public void setHasFertilizerSpreader(Question hasFertilizerSpreader) {
		this.hasFertilizerSpreader = doubleToBoolean(hasFertilizerSpreader.getAnswer());
	}

	public boolean getHasMestGatherer() {
		return hasMestGatherer;
	}
	
	public void setHasMestSpreader(Question hasMestSpreader) {
		this.hasMestSpreader = doubleToBoolean(hasMestSpreader.getAnswer());
	}
	
	public boolean getHasMestSpreader() {
		return hasMestSpreader;
	}
	
	public void setHasShaver(Question hasShaver) {
		this.hasShaver = doubleToBoolean(hasShaver.getAnswer());
	}
	
	public boolean getHasShaver() {
		return hasShaver;
	}

	public void setHasMestGatherer(Question hasMestGatherer) {
		this.hasMestGatherer = doubleToBoolean(hasMestGatherer.getAnswer());
	}
	
}
