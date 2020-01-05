package com.model;

public class Cost implements VariableDefinitions {
	/* Materials */
	public int tractorCost;
	public int mowerCost;
	public int shakerCost;
	public int rakerCost;
	public int fertilizerSpreaderCost;
	//public int haypackerRentCost;
	
	/* Land */
	public double toeslagrechtEarnings;
	public double landNeededCost; 
	/* Pachtovereenkomsten. Regionorm is 600 euros / acre on average 
	 * https://www.rvo.nl/onderwerpen/agrarisch-ondernemen/grond/pachten-en-verpachten/pachtnormen-en-pachtprijzen-berekenen */

	
	/* Care */
	public double shaveOtherCost;
	//public int birthOtherCost; // maybe just remove this? 
	public double woolEarnings;
	public double myasTreatmentCost; 
	public double wormCost;
	
	/* Business */
	public int earMarkCost;
	public double RVOAdminCost;
	public double slaughterCost;
	
	/* Sheep */
	public int sheepBoughtCost;
	public int sheepSoldEarnings;
	public int phosphateRightsCost;
	
	/* Shed */
	public double shedCost;
	/* Bouwkosten Stal voor vleeskalveren (traditioneel metselwerk) 350,90 per m2 
	 * http://decentrale.regelgeving.overheid.nl/cvdr/Images/Haaren/i263531.pdf */
	public int fertilizerPlateCost = 20000;
	
	public int totalCost;
	public int totalEarnings;
	public int moneyNeeded;
	public int moneyToSpend;
	
	public Cost(int totalNSheepWanted, int ownsNSheep, boolean wantsSlaugter) {
    	setMyasTreatmentCost(totalNSheepWanted, true);
    	/* Business */
    	setAdminastrativeCost(totalNSheepWanted, ownsNSheep, wantsSlaugter); 
    	/* Sheep */
    	setSheepBoughtCost(totalNSheepWanted, ownsNSheep);
    	setSheepSoldEarnings(totalNSheepWanted);
    	/* Care */
    	setWormCost(totalNSheepWanted);
	}
	
	public void setTractorCost(boolean needsNewTractor, boolean needsBigTractor) {
		/* In both cases a small tractor and big tractor will cost roughly the same */
		if (needsNewTractor) {
			if (needsBigTractor) {
				System.out.println("Needs a big tractor ");
				this.tractorCost = 20000;
			} else { this.tractorCost = 20000; System.out.println("Needs a small tractor ");}
		} else { this.tractorCost = 0; System.out.println("Needs no tractor ");}
	}
	
	public void setGrassMaterialCost(int hasMower, int hasShaker, int hasRaker, int hasFertilizerSpreader) {
		/*
		 * All of these materials together cost roughly 20000 secondhand. So one of them
		 * is 20000/3
		 */
		int costOne = 20000 / 3;
		if(hasMower == NO) {
			this.mowerCost = costOne;
		}
		if(hasShaker == NO) {
			this.shakerCost = costOne;
		}
		if(hasRaker == NO) {
			this.rakerCost = costOne;
		}
		/* spreader costs roughly 1000€*/
		if(hasFertilizerSpreader == NO) {
			this.fertilizerSpreaderCost = 1000;
		}
	}
	
	public void setLandNeededCost(double landNeeded) {
		if (landNeeded > 0) {
			this.landNeededCost = landNeeded * 600;
		} else { this.landNeededCost = 0; }
	}
	
	public void setMyasTreatmentCost(int totalNSheepWanted, boolean isBeginner) {
		if (isBeginner) {
			/* 3.50 euros per sheep, 3 treatments per year */
			this.myasTreatmentCost = totalNSheepWanted * 3.5 * 3;
		} else {
			/*
			 * This should take into account the neocodol treatments which are more
			 * individualized
			 */
			this.myasTreatmentCost = totalNSheepWanted * 3.5 * 3;
		}
	}
	
	public void setAdminastrativeCost(int totalNSheepWanted, int ownsNSheep, boolean wantsSlaughter) {
		/* costs are roughly 1 euros per sheep, so total amount of sheep as the cost. */
		this.earMarkCost = totalNSheepWanted - ownsNSheep; /* When you own the sheep you already have them registered */
		this.RVOAdminCost = ((totalNSheepWanted - ownsNSheep) * 1.5);
		if (wantsSlaughter) {
			this.slaughterCost = (totalNSheepWanted * 0.5);
		} else { this.slaughterCost = 0; }
	}
	
	public void setShedCost(double goalCurSizeDiff) {
		if (goalCurSizeDiff > 0) {
			/* Building a shed costs roughly 350 euros per square meter */
			this.shedCost = goalCurSizeDiff * 350;
		} else { this.shedCost = 0; }
	}
	
	/* Something should probably be done with slaughter and breed here */
	public void setSheepSoldEarnings(int totalNSheepWanted) {
		/* On average 3 lambs per ewe. http://hoeveackerdijk.nl/hoeveel-lammetjes-krijgt-een-schaap/ 
		 * A sheep nets you roughly 95 euros. https://www.veemarkt.nu/leeuwarden/schapen/?wpv_aux_current_post_id=50&wpv_view_count=418-TCPID50  */
		this.sheepSoldEarnings = totalNSheepWanted * 2 * 95;
	}
	
	public int getSheepBoughtCost() {
		return sheepBoughtCost;
	}

	public void setSheepBoughtCost(int totalNSheepWanted, int ownsNSheep) {
		this.sheepBoughtCost = (totalNSheepWanted - ownsNSheep) * 95;
	}
	
	public void setTotalEarnings() {
		this.totalEarnings = (int)woolEarnings + sheepSoldEarnings + (int)toeslagrechtEarnings;
	}
	
	public int getTotalEarnings() {
		return totalEarnings;
	}
	
	public void setTotalCost() {
		this.totalCost = tractorCost + mowerCost + shakerCost + rakerCost + (int)landNeededCost + (int)shaveOtherCost + (int)myasTreatmentCost + (int)wormCost + earMarkCost 
				+ (int)RVOAdminCost + (int)slaughterCost + sheepBoughtCost + (int)shedCost;
	}
	
	public int getTotalCost() {
		return totalCost;
	}
	
	public void setMoneyNeeded() {
		this.moneyNeeded = totalCost - totalEarnings;
	}
	
	public int getMoneyNeeded() {
		return moneyNeeded;
	}
	
	public void setMoneyToSpend(int moneyToSpend) {
		this.moneyToSpend = moneyToSpend;
	}

	public int getMoneyToSpend() {
		return moneyToSpend;
	}
	
	public int getMowerCost() {
		return mowerCost;
	}
	
	public int getFertilizerSpreaderCost() {
		return fertilizerSpreaderCost;
	}

	public void setMowerCost(int mowerCost) {
		this.mowerCost = mowerCost;
	}

	public int getShakerCost() {
		return shakerCost;
	}

	public void setShakerCost(int shakerCost) {
		this.shakerCost = shakerCost;
	}

	public int getRakerCost() {
		return rakerCost;
	}

	public void setRakerCost(int rakerCost) {
		this.rakerCost = rakerCost;
	}

	public double getToeslagrechtEarnings() {
		return toeslagrechtEarnings;
	}

	public void setToeslagrechtEarnings(double toeslagrechtEarnings) {
		this.toeslagrechtEarnings = toeslagrechtEarnings;
	}

	public double getShaveOtherCost() {
		return shaveOtherCost;
	}

	public void setShaveOtherCost(double shaveOtherCost) {
		this.shaveOtherCost = shaveOtherCost;
	}

//	public int getBirthOtherCost() {
//		return birthOtherCost;
//	}

//	public void setBirthOtherCost(int birthOtherCost) {
//		this.birthOtherCost = birthOtherCost;
//	}

	public double getWoolEarnings() {
		return woolEarnings;
	}

	public void setWoolEarnings(double woolEarnings) {
		this.woolEarnings = woolEarnings;
	}

	public double getMyasTreatmentCost() {
		return myasTreatmentCost;
	}

	public void setMyasTreatmentCost(double myasTreatmentCost) {
		this.myasTreatmentCost = myasTreatmentCost;
	}

	public double getWormCost() {
		return wormCost;
	}

	/* for 2.5 liters you can worm 250 lambs. 2.5 liters costs roughly 150€. Therefore, 150/250 = 0.60€ is needed per lamb */
	public void setWormCost(int totalNSheepWanted) {
		/* ewes need to be wormed only once a year, while lambs need to be wormed 2-3 times a year 
		 * Ewes get roughly two lambs.
		 */
		int lambs = totalNSheepWanted * 2;
		int ewes = totalNSheepWanted;
		this.wormCost = (lambs * 0.60) + (ewes * 0.60);
	}

	public int getEarMarkCost() {
		return earMarkCost;
	}

	public void setEarMarkCost(int earMarkCost) {
		this.earMarkCost = earMarkCost;
	}

	public double getRVOAdminCost() {
		return RVOAdminCost;
	}

	public void setRVOAdminCost(double RVOAdminCost) {
		this.RVOAdminCost = RVOAdminCost;
	}

	public double getSlaughterCost() {
		return slaughterCost;
	}

	public void setSlaughterCost(double slaughterCost) {
		this.slaughterCost = slaughterCost;
	}

	public int getPhosphateRightsCost() {
		return phosphateRightsCost;
	}

	public void setPhosphateRightsCost(int phosphateRightsCost) {
		this.phosphateRightsCost = phosphateRightsCost;
	}

	public int getTractorCost() {
		return tractorCost;
	}

	public double getLandNeededCost() {
		return landNeededCost;
	}

	public int getSheepSoldEarnings() {
		return sheepSoldEarnings;
	}

	public double getShedCost() {
		return shedCost;
	}
	
	public int getFertilizerPlateCost() {
		return fertilizerPlateCost;
	}
	
	public void setFertilizerPlateCost(int fertilizerPlateCost) {
		this.fertilizerPlateCost = fertilizerPlateCost;
	}
	
}
