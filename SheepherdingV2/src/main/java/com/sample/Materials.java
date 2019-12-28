package com.sample;

import com.sample.Fact;

public class Materials implements VariableDefinitions {
	public int hasTractor;
	public int horsePowerTractor;
	public int hasMower;
	public int hasShaker;
	public int hasRaker;
	public int hasHayPacker;
	public int hasFertilizerSpreader;
	public int hasMestGatherer;
	public int advice;
	
	public Materials() {
		
	}

	public int getHasTractor() {
		return hasTractor;
	}

	public void setHasTractor(Fact hasTractor) {
		this.hasTractor = hasTractor.getAnswer();
	}

	public int getHorsePowerTractor() {
		return horsePowerTractor;
	}

	public void setHorsePowerTractor(Fact horsePowerTractor) {
		this.horsePowerTractor = horsePowerTractor.getAnswer();
	}

	public int getHasMower() {
		return hasMower;
	}

	public void setHasMower(Fact hasMower) {
		this.hasMower = hasMower.getAnswer();
	}

	public int getHasShaker() {
		return hasShaker;
	}

	public void setHasShaker(Fact hasShaker) {
		this.hasShaker = hasShaker.getAnswer();
	}

	public int getHasRaker() {
		return hasRaker;
	}

	public void setHasRaker(Fact hasRaker) {
		this.hasRaker = hasRaker.getAnswer();
	}

	public int getHasHayPacker() {
		return hasHayPacker;
	}

	public void setHasHayPacker(Fact hasHayPacker) {
		this.hasHayPacker = hasHayPacker.getAnswer();
	}

	public int getHasFertilizerSpreader() {
		return hasFertilizerSpreader;
	}

	public void setHasFertilizerSpreader(Fact hasFertilizerSpreader) {
		this.hasFertilizerSpreader = hasFertilizerSpreader.getAnswer();
	}

	public int getHasMestGatherer() {
		return hasMestGatherer;
	}

	public void setHasMestGatherer(Fact hasMestGatherer) {
		this.hasMestGatherer = hasMestGatherer.getAnswer();
	}

	public int getAdvice() {
		return advice;
	}

	public void setAdvice(Fact advice) {
		this.advice = advice.getAnswer();
	}
	
	
	
}
