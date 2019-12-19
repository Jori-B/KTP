package com.sample;

public class ValueCalculations {
	   
	public static float numberOfSheepMore(float land, int sheep) {
		   	return (float) (land - (landNeeded(sheep) * 2.85));
	   }
	   
	   public static float shedSize(int sheep, float shedsize) {
		   	return (float) (sheep * 10 - shedsize);
	   }
	   
	   public static float numberOfAcresMore(int sheep, float land) {
		   	return (float) (landNeeded(sheep) - land);
	   }

	   public static float neededMoneyForLand(int sheep, float land) {
		   	return (float)  (numberOfAcresMore(sheep, land) * 50000);
	   }

	   public static float toeslagrechten(int sheep, float land) {
		   // This doesn't reflect the fact that people might already
		   // have certain pieces of land with toeslagrechten
		   return (float)  (landNeeded(sheep) * 300);
	   }
	   
	   public static float landNeeded(int sheep) {
		   return (float) (sheep * 2.85);
	   }

}
