

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
	public static final void main(String[] args) {
	   
	   try {
         // Load up the knowledge base
         KnowledgeBase kbase = readKnowledgeBase();
         StatefulKnowledgeSession ksession = kbase.newStatefulKnowledgeSession();
         
         // Questions go here. For every question the input from the user is read. 
         // The question name, type and ksession (for inserting the fact) are passed to the ReadInput function
         
         //First Order Facts
         Fact facts[] = new Fact [100];
         facts[0] = new Fact("mcHobProf", MC, ksession, "Do you want do farming as a (0) hobby or (1) professionally?",ASK);
         facts[1] = new Fact("hasShed", YESNO, ksession,"Do you have a shed? No (0) Yes (1)",ASK);    
         facts[2] = new Fact("hasLand", YESNO, ksession,"Do you have land (including the land you lease)? No (0) Yes (1)",ASK);
         facts[3] = new Fact("hasTractor", YESNO, ksession,"Do you have have a tractor? No (0) Yes (1)",ASK);
         facts[4] = new Fact("nmSheep", NUMB, ksession,"How many sheep would you like to have?",ASK);
         facts[5] = new Fact("mcBirth", MC, ksession,"Do you want do birthing (0) yourself or (1) let someone else do it?",ASK);
         //No rules         
         facts[6] = new Fact("nmCapitol", NUMB, ksession,"How much capitol do you have to spend?",ASK);
         facts[7] = new Fact("ynNeigbourLease", YESNO, ksession,"Do your neighbours have a land that you can lease? No (0) Yes (1)",ASK);
         facts[8] = new Fact("ynShaveYourself", YESNO, ksession, "Do you want to shave yourself",ASK);     
         facts[9] = new Fact("ynShaveWhool", YESNO, ksession,"Do you want to sell wool? No (0) Yes (1)",ASK);
         facts[10] = new Fact("ynRegisteredUBNandKvK", MC, ksession,"Are you UBN and KvK registered? UBN (0) KvK (1)",ASK);
         
         //Second Order Facts
         

         
         
         
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

