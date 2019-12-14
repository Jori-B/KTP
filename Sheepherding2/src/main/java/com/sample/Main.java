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
/* 
 * This class creates all the questions and asks them
 */

public class Main {
    
	public static final int YESNO = 0; //ynXXX, hasXXX, isXXX...
    public static final int MC = 1; // mcXXX
    public static final int NUMB = 2; // nmXXX
    public static final int OPEN = 3; // opXXX
    
    public static final boolean ASK = true;
    public static final boolean DONTASK = false;
    
    /* if we make this static it probably isn't able to change facts?*/
    
	public static final void main(String[] args) {
	   
	   try {
         // Load up the knowledge base
         KnowledgeBase kbase = readKnowledgeBase();
         StatefulKnowledgeSession ksession = kbase.newStatefulKnowledgeSession();
         
         // Questions go here. For every question the input from the user is read. 
         // The question name, type and ksession (for inserting the fact) are passed to the Fact function
         
         //First Order Facts
         Fact facts[] = new Fact [100];
         facts[0] = new Fact("mcHobProf", MC, ksession, "Do you want do farming as a (0) hobby or (1) professionally?", ASK);
         facts[1] = new Fact("hasShed", YESNO, ksession, "Do you have a shed? No (0) Yes (1)", ASK);    
         facts[2] = new Fact("shedSize", NUMB, ksession, "How big is your shed (in meters squared)?");
         /* Fire all rules directly after shedSize question is in the fact base, so it fires instantly */
         ksession.fireAllRules();
         
         facts[3] = new Fact("hasLand", YESNO, ksession, "Do you have land (including the land you lease)? No (0) Yes (1)", ASK);
         facts[4] = new Fact("landSize", NUMB, ksession, "How big is your land (in acres)?");
         ksession.fireAllRules();
         /* We can make a land size + possible lease size function */
         facts[5] = new Fact("ynNeigbourLease", YESNO, ksession, "Do your neighbours have a land that you can lease? No (0) Yes (1)", ASK);
         
         
         facts[6] = new Fact("hasTractor", YESNO, ksession, "Do you have have a tractor? No (0) Yes (1)", ASK);
         facts[7] = new Fact("nmSheep", NUMB, ksession, "How many sheep would you like to have?", ASK);
         facts[8] = new Fact("mcBirth", MC, ksession, "Do you want do birthing (0) yourself or (1) let someone else do it?", ASK);
         /* Probably should add a class that saves all the needed money, so we can subtract this from the capitol */       
         facts[9] = new Fact("nmCapitol", NUMB, ksession, "How much capitol do you have to spend on the sheep business?", ASK);
         
         facts[10] = new Fact("ynShaveYourself", YESNO, ksession, "Do you want to shave yourself? No (0) Yes (1)", ASK);     
         /* How much time does it cost to shave yourself? How much money do you gain from selling wool per sheep */
         facts[11] = new Fact("ynShaveWool", YESNO, ksession, "Do you want to sell wool? No (0) Yes (1)", ASK);
         /* Simple rules like if you don't have one of these two "get them" could be added */
         facts[12] = new Fact("ynRegisteredUBN", YESNO, ksession, "Does your farm already have a unique business number (UBN)? No (0) Yes (1)", ASK);
         facts[13] = new Fact("ynRegisteredKvK", MC, ksession, "Is your farm already registered at the Kamer van Koophandel (KvK)? No (0) Yes (1)", ASK);
                  
         ksession.fireAllRules();
         
         Fact.scanner.close();

                            
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

