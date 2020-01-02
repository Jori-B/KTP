package com.model;

import com.model.Question;

public class Sheep implements VariableDefinitions {
	public int hasSheep;
	public int ownsNSheep;
	public int desiresNMoreSheep;
	public int totalNSheepWanted; 
	public int costPhosphateRights;
	public int sheepCost;
	public int maxAmountSheep; // We still need a functions for maxAmountOfSheep
	public int nSheepMore;
	public String advice = "<html>";
	
	public Sheep() {
		
	}
	
	/* Calculations */
	public void calcTotalSheep() {
		if (ownsNSheep == 0) {
			System.out.println("owns sheep is null");
			this.totalNSheepWanted = desiresNMoreSheep;
		} else {
			System.out.println("owns sheep is not empty");
			this.totalNSheepWanted = desiresNMoreSheep + ownsNSheep;
		}
	}
	
	public void calcPriceSheep() {
		this.sheepCost = totalNSheepWanted * 100;
	}
	
	/* Getters and setters */
	public void calcNumberOfSheepMore(int totalLandSize) {
		if (ownsNSheep == 0) {
			System.out.println("owns sheep is null");
			this.nSheepMore = (int)(totalLandSize / 2.85);
		} else {
			this.nSheepMore = (int)((totalLandSize / 2.85) - ownsNSheep) ; // I think this is correct, but haven't checked
		}
	}
	
	public void calcPhosphateRights() { 
		this.costPhosphateRights = 90 * totalNSheepWanted; 
	} 

	public int getHasSheep() {
		return hasSheep;
	}

	public void setHasSheep(Question hasSheep) {
		this.hasSheep = hasSheep.getAnswer();
	}

	public int getOwnsNSheep() {
		return ownsNSheep;
	}

	public void setOwnsNSheep(Question ownsNSheep) {
		this.ownsNSheep = ownsNSheep.getAnswer();
	}

	public int getDesiresNMoreSheep() {
		return desiresNMoreSheep;
	}

	public void setDesiresNMoreSheep(Question desiresNMoreSheep) {
		this.desiresNMoreSheep = desiresNMoreSheep.getAnswer();
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

	public int getMaxAmountSheep() {
		return maxAmountSheep;
	}

	public void setMaxAmountSheep(Question maxAmountSheep) {
		this.maxAmountSheep = maxAmountSheep.getAnswer();
	}

	public int getnSheepMore() {
		return nSheepMore;
	}

	public void setnSheepMore(int nSheepMore) {
		this.nSheepMore = nSheepMore;
	}

	public int getCostPhosphateRights() {
		return costPhosphateRights;
	}

	public void setCostPhosphateRights(int costPhosphateRights) {
		this.costPhosphateRights = costPhosphateRights;
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
