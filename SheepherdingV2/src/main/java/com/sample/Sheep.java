package com.sample;

import com.sample.Fact;

public class Sheep implements VariableDefinitions {
	public Fact hasSheep;
	public Fact ownsNSheep;
	public Fact desiresNMoreSheep;
	public int totalNSheepWanted; 
	public int sheepCost;
	public Fact maxAmountSheep; // We still need a functions for maxAmountOfSheep
	public int nSheepMore;
	public Fact advice;
	
	public Sheep() {
		
	}
	
	/* Calculations */
	public void calcTotalSheep() {
		if (ownsNSheep == null) {
			System.out.println("owns sheep is null");
			this.totalNSheepWanted = desiresNMoreSheep.getAnswer();
		} else {
			System.out.println("owns sheep is not empty");
			this.totalNSheepWanted = desiresNMoreSheep.getAnswer() + ownsNSheep.getAnswer();
		}
	}
	
	public void calcPriceSheep() {
		this.sheepCost = totalNSheepWanted * 100;
	}
	
	/* Getters and setters */
	public void calcNumberOfSheepMore(int totalLandSize) {
		if (ownsNSheep == null) {
			System.out.println("owns sheep is null");
			this.nSheepMore = (int)(totalLandSize / 2.85);
		} else {
			this.nSheepMore = (int)((totalLandSize / 2.85) - ownsNSheep.getAnswer()) ; // I think this is correct, but haven't checked
		}
	}

	public Fact getHasSheep() {
		return hasSheep;
	}

	public void setHasSheep(Fact hasSheep) {
		this.hasSheep = hasSheep;
	}

	public Fact getOwnsNSheep() {
		return ownsNSheep;
	}

	public void setOwnsNSheep(Fact ownsNSheep) {
		this.ownsNSheep = ownsNSheep;
	}

	public Fact getDesiresNMoreSheep() {
		return desiresNMoreSheep;
	}

	public void setDesiresNMoreSheep(Fact desiresNMoreSheep) {
		this.desiresNMoreSheep = desiresNMoreSheep;
	}

	public int getTotalNSheepWanted() {
		return totalNSheepWanted;
	}

	public void setTotalNSheepWanted(int totalNSheepWanted) {
		this.totalNSheepWanted = totalNSheepWanted;
	}

	public int getSheepCost() {
		return sheepCost;
	}

	public void setSheepCost(int sheepCost) {
		this.sheepCost = sheepCost;
	}

	public Fact getMaxAmountSheep() {
		return maxAmountSheep;
	}

	public void setMaxAmountSheep(Fact maxAmountSheep) {
		this.maxAmountSheep = maxAmountSheep;
	}

	public int getnSheepMore() {
		return nSheepMore;
	}

	public void setnSheepMore(int nSheepMore) {
		this.nSheepMore = nSheepMore;
	}

	public Fact getAdvice() {
		return advice;
	}

	public void setAdvice(Fact advice) {
		this.advice = advice;
	}
	
	
	
}
