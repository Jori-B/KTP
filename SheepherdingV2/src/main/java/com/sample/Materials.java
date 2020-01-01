package com.sample;

import com.sample.Question;

public class Materials implements VariableDefinitions {
	public int hasTractor;
	public int horsePowerTractor;
	public int hasMower;
	public int hasShaker;
	public int hasRaker;
	public int hasHayPacker;
	public int hasFertilizerSpreader;
	public int hasMestGatherer;
	public String advice = "<html>";
	
	public Materials() {
		
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

	public void setHasMestGatherer(Question hasMestGatherer) {
		this.hasMestGatherer = hasMestGatherer.getAnswer();
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
