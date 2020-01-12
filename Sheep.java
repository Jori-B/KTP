package com.model;

import com.model.Question;

public class Sheep extends Category implements VariableDefinitions {
	public boolean hasSheep;
	public int ownsNSheep;
	public int desiresNMoreSheep;
	public int totalNSheepWanted; 
	//public double sheepCost;
	public int maxAmountOfSheep; 
	public int nSheepMore;
	
	public Sheep() {
		
	}
	
	/* Calculations */
	public void setDesiresNMoreSheep() { //calcPriceSheep() {
		if (totalNSheepWanted-ownsNSheep>0){
		this.desiresNMoreSheep = totalNSheepWanted - ownsNSheep;
		System.out.println("he wants " + desiresNMoreSheep);
		//this.sheepCost = desiresNMoreSheep * 100;
		} else this.desiresNMoreSheep = 0;
	}
	
	public void calcNumberOfSheepMore(double totalLandSize) {
		/* https://toverleven.cultu.be/hoeveel-dieren-m2-grasland 
		 * 10 to 15 sheep (with lambs) per hectare. Therefore one needs 1/12.5 = 0.08 hectare per sheep */ 
		this.maxAmountOfSheep = (int)(totalLandSize / 0.08);
		this.nSheepMore = (maxAmountOfSheep - totalNSheepWanted); // I think this is correct, but haven't checked
	}
	
	/* Getters and setters */
	public boolean getHasSheep() {
		return hasSheep;
	}

	public void setHasSheep(Question hasSheep) {
		this.hasSheep = doubleToBoolean(hasSheep.getAnswer());
	}

	public int getOwnsNSheep() {
		return ownsNSheep;
	}

	public void setOwnsNSheep(Question ownsNSheep) {
		this.ownsNSheep = (int) ownsNSheep.getAnswer();
	}

	public int getDesiresNMoreSheep() {
		return desiresNMoreSheep;
	}

	public void setDesiresNMoreSheep(Question desiresNMoreSheep) {
		this.desiresNMoreSheep = (int) desiresNMoreSheep.getAnswer();
	}

	public int getTotalNSheepWanted() {
		return totalNSheepWanted;
	}

	public void setTotalNSheepWanted(Question totalNSheepWanted) {
		this.totalNSheepWanted = (int) totalNSheepWanted.getAnswer();
	}

//	public double getSheepCost() {
//		return sheepCost;
//	}
//
//	public void setSheepCost(int sheepCost) {
//		this.sheepCost = sheepCost;
//	}

	public int getMaxAmountOfSheep() {
		return maxAmountOfSheep;
	}

	public void setMaxAmountOfSheep(Question maxAmountOfSheep) {
		this.maxAmountOfSheep = (int) maxAmountOfSheep.getAnswer();
	}

	public int getnSheepMore() {
		return nSheepMore;
	}

	public void setnSheepMore(int nSheepMore) {
		this.nSheepMore = nSheepMore;
	}
	
}
