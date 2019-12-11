package com.sample;

public class Cost {
	   public static float numberOfSheepMore(float land, int sheep){
		   	return (float) (land - (sheep * 2.85) * 2.85);
		   }
	   public static float shedSize(float shedsize, int sheep){
		   	return (float) (sheep* 10 - shedsize);
		   }
	   public static float numberOfAcresMore(float land, int sheep){
		   	return (float) (sheep * 2.85 - land);
		   }
	   public static float neededMoneyForLand(float land, int sheep){
		   	return (float)  (sheep * 2.85 - land* 50000);
		   }
	   public static float toeslagrechten(float land, int sheep){
		   	return (float)  (sheep * 2.85 - land* 300);
		   }

}
