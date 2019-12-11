

import java.util.Scanner;

import org.drools.runtime.StatefulKnowledgeSession;

import com.sample.Fact;



public class ReadInput {
 
	public static final int YESNO = 0;
    public static final int MC = 1;
    public static final int NUMB = 2;
	public static final int OPEN = 3;	
	
	/* Create a scanner that can be closed in the Main.java file */ 
	public static Scanner scanner = new Scanner( System.in );
	
	public static void read(String name, int questionType, StatefulKnowledgeSession ksession){
		
		String userInput = scanner.nextLine();
		Fact answer = new Fact(name, questionType, ksession);
        /* Check if user inputs a number */
        try {       	 	
       	 	if(questionType != OPEN) { 
       	 		int numbUserIn = Integer.parseInt(userInput); 
	       	 	answer.setAnswer(numbUserIn);
       	 	} else { /* It's a YESNO, MC or NUMB question */    	 	
       	 		answer.setOpenAnswer(userInput);
       	 	}
       	 	
        } catch (NumberFormatException e) {
        	// AT THIS MOMENT THE USER DOESNT GET A CHANCE TO TRY AGAIN
            System.out.println("Not a number, please try again.");
        }
        
        ksession.insert(answer);
	}
}
