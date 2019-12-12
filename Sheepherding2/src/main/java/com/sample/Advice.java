package com.sample;

public class Advice {
	
	public static void advice(String advice) {
		/* Made this System.err instead of System.out so that advice is easily recognized. */
		System.err.println(advice);
   }
}