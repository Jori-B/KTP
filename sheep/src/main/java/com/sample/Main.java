package com.sample;

import java.util.Scanner;
import org.drools.KnowledgeBase;
import org.drools.KnowledgeBaseFactory;
import org.drools.builder.KnowledgeBuilder;
import org.drools.builder.KnowledgeBuilderError;
import org.drools.builder.KnowledgeBuilderErrors;
import org.drools.builder.KnowledgeBuilderFactory;
import org.drools.builder.ResourceType;
import org.drools.io.ResourceFactory;
import org.drools.runtime.StatefulKnowledgeSession;

//import com.sample.Answer.Hobby;
//import com.sample.Answer.Shed;
//import com.sample.Answer.Birth;
//import com.sample.Answer;
import com.sample.ReadInput;

/* 
 * This class creates all the questions and asks them
 */

public class Main {
    
	public static final int YESNO = 0;
    public static final int MC = 1;
    public static final int NUMB = 2;
    public static final int OPEN = 3;
	
	public static final void main(String[] args) {
	   
	   try {
         // Load up the knowledge base
         KnowledgeBase kbase = readKnowledgeBase();
         StatefulKnowledgeSession ksession = kbase.newStatefulKnowledgeSession();
         
         // Questions go here. For every question the input from the user is read. 
         System.out.println("Do you want do farming as a (0) hobby or (1) professionally?");
         // The question name, type and ksession (for inserting the fact) are passed to the ReadInput function
         ReadInput.read("hobProf", MC, ksession);

         System.out.println("Do you have a shed? No (0) Yes (1)");
         ReadInput.read("hasShed", YESNO, ksession);
         
      // Fire rules now so that the question: "How big is your shed?" is asked in case of having one.
         ksession.fireAllRules();
         
         System.out.println("Do you have land (including the land you lease)? No (0) Yes (1)");
         ReadInput.read("hasLand", YESNO, ksession);
         
         // Fire rules now so that the question: "How much land do you have?" is asked in case of having land.
         ksession.fireAllRules();
         
         System.out.println("Do you have have a tractor? No (0) Yes (1)");
         ReadInput.read("hasTractor", YESNO, ksession);
         
         System.out.println("How many sheep would you like to have?");
         ReadInput.read("nSheep", NUMB, ksession);
         
         System.out.println("Do you want do birthing (0) yourself or (1) let someone else do it?");
         ReadInput.read("birth", MC, ksession);
         

         ksession.fireAllRules();
         
         ReadInput.scanner.close();

                            
      } catch (Throwable t) {
         t.printStackTrace();
      }
   }
   private static KnowledgeBase readKnowledgeBase() throws Exception {
      KnowledgeBuilder kbuilder = KnowledgeBuilderFactory.newKnowledgeBuilder();
      kbuilder.add(ResourceFactory.newClassPathResource("Rules.drl"), ResourceType.DRL);
      KnowledgeBuilderErrors errors = kbuilder.getErrors();
      
      if (errors.size() > 0) {
         for (KnowledgeBuilderError error: errors) {
            System.err.println(error);
         }
         throw new IllegalArgumentException("Could not parse knowledge.");
      }
      KnowledgeBase kbase = KnowledgeBaseFactory.newKnowledgeBase();
      kbase.addKnowledgePackages(kbuilder.getKnowledgePackages());
      return kbase;
   }
   
}

