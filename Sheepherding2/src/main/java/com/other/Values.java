package com.other;

	public class Values {
		private float sheep ;
		private float shedSize;
		private float availableLand ;
		private float neededLand;
		private float moneyCost ;
		private float moneyIncome ;
	
		
		public Values() {
			   this.sheep = 0;
			   this.shedSize = 0;
			   this.availableLand = 0;
			   this.neededLand = 0;
			   this.moneyCost = 0;
		   }
	public float getSheepNumber() {
		   return sheep;
	}
	public void addSheep(float sheep) {
		   this.sheep = this.sheep + sheep;
	}
	
	public float getShedSize() {
		   return shedSize;
	}
	public void addShedSize(float  shedSize) {
		   this.shedSize = this.shedSize + shedSize;
	}
	
	public float getAvailableLand() {
		   return availableLand;
	}
	public void addAvailableLand(float availableLand) {
		   this.availableLand =   this.availableLand + availableLand;
	}
	public float getMoneyCost() {
		   return moneyCost;
	}
	public void addMoneyCost(float moneyCost ) {
		   this.moneyCost = this.moneyCost+moneyCost;
	}
	public float getMoneyIncome() {
		   return moneyIncome;
	}
	public void addMoneyIncome(float moneyIncome) {
		   this.moneyIncome = this.moneyIncome + moneyIncome ;
	}
	public float getNeededLand() {
		   return neededLand;
	}
	public void addNeededLand(float neededLand) {
		   this.neededLand =   this.neededLand + neededLand;
	}
	
	public void test() {
			System.out.println("sheep "+sheep);
			System.out.println("shedSize "+shedSize);
			System.out.println("availableLand "+availableLand);
			System.out.println("neededLand "+neededLand);
			System.out.println("moneyCost "+moneyCost);
			System.out.println("moneyIncome "+moneyIncome);
	}
}