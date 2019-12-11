package com.sample;
import org.drools.spi.KnowledgeHelper;

/* This class is used to tell us that a rule has fired. It is useful for debugging */
public class Utility {
   public static void help(final KnowledgeHelper drools, final String message){
      System.out.println(message);
      System.out.println("\nrule triggered: " + drools.getRule().getName());
   }
   public static void helper(final KnowledgeHelper drools){
      System.out.println("\nrule triggered: " + drools.getRule().getName());
   }
}