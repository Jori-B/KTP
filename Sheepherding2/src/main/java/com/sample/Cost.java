package com.sample;

public class Cost {
	   
	public static float numberOfSheepMore(float land, int sheep){
		   	return (float) (land - (landNeeded(sheep) * 2.85));
	   }
	   
	   public static float shedSize(int sheep, float shedsize){
		   	return (float) (sheep * 10 - shedsize);
	   }
	   
	   public static float numberOfAcresMore(int sheep, float land){
		   	return (float) (landNeeded(sheep) - land);
	   }
	   /* This is wrong */
	   public static float neededMoneyForLand(int sheep, float land){
		   	return (float)  (sheep * 2.85 - land * 50000);
	   }
	   /* This is wrong */
	   public static float toeslagrechten(int sheep, float land){
		   	return (float)  (sheep * 2.85 - land * 300);
	   }
	   public static float landNeeded(int sheep) {
		   return (float) (sheep * 2.85);
	   }

}
