package com.sample;
import java.util.Scanner;

import org.drools.runtime.StatefulKnowledgeSession;

//import java.math.BigDecimal;


public class Fact {
	public StatefulKnowledgeSession ksession;
	
	/* All question types have a name. As of right now we don't really use these */ 
	public static final int YESNO = 0;
    public static final int MC = 1;
    public static final int NUMB = 2;
    public static final int OPEN = 3;
    
    /* This might be used for the status field */
    public static final int NOANSWER = 0;
    public static final int HASANSWER = 1;
	
    public static final int NO = 0;
    public static final int YES = 1;
    
    /* For multiple choice questions we use these variable names to make the code more human readable in the Rules.dlr file */
    public static final int HOBBY = 0;
    public static final int PRO = 1;
    
    public static final int SELF = 0;
    public static final int OTHER = 1;
    
	/* Create a scanner that can be closed in the Main.java file */ 
	public static Scanner scanner = new Scanner( System.in );
    
	   /* Name variable is very important for referencing the questions in the Rules.dlr file */
	   private String name;
	   
	   private int numbofSheep; /* NOT USED RIGHT NOW */
	   private int questionType;
	   private String question;
	   private String openAnswer;
	   private int answer;
	   /* We don't use the status variable at the moment. But it might be useful to prevent rules from firing twice */
	   private int status = NOANSWER;
	   
	   
	   public Fact(String name, int questionType, StatefulKnowledgeSession ksession, String question) {
		   this.name = name;
		   this.questionType = questionType;
		   this.ksession = ksession;
		   this.question = question;
		   this.status = NOANSWER;
		   ksession.insert(this);
	   }
	   
	   public Fact(String name, int questionType, StatefulKnowledgeSession ksession, String question, boolean askNow) {
		   this.name = name;
		   this.questionType = questionType;
		   this.ksession = ksession;
		   this.question = question;
		   if(askNow) {
			   askQuestion(true);
		   }
	   }
	   
	   /* Name of the question */
	   public String getName() {
		   return name;
	   }
	   
	   public void setName(String name) {
		   this.name = name;
	   }
	   
	   /* These answers are used in the Rules.dlr file */
	   public void setAnswer(int numbUserIn) {
		   this.answer = numbUserIn;
	   }
	   
	   
	   public int getAnswer() {
		   return answer;
	   }
	   
	   public void askQuestion(boolean doInsert) {
		   System.out.println(question);
		   String userInput = scanner.nextLine();
	        /* Check if user inputs a number */
	        try {       	 	
	       	 	if(questionType != OPEN) { 
	       	 		int numbUserIn = Integer.parseInt(userInput); 
		       	 	setAnswer(numbUserIn);
	       	 	} else { /* It's a YESNO, MC or NUMB question */    	 	
	       	 		setOpenAnswer(userInput);
	       	 	}
	       	 	
	        } catch (NumberFormatException e) {
	        	// AT THIS MOMENT THE USER DOESNT GET A CHANCE TO TRY AGAIN
	            System.out.println("Not a number, please try again.");
	        }
	        setStatus(HASANSWER);
	        /* SOMETHING WEIRD IS HAPPENING WITH THE KSESSION */
	        if(doInsert) {
	        	ksession.insert(this);
	        } 
	   }
	   
	   public String getQuestion() {
		   return question;
	   }

	   public void setQuestion(String question) {
		   this.question = question;
	   }
	
	   /* To insert the fact in the knowledge session from the Rules.dlr file, this field is placed here */
	   public StatefulKnowledgeSession getKSession() {
		   return ksession;
	   }
	   
	   public void setKSession(StatefulKnowledgeSession ksession) {
		   this.ksession = ksession;
	   }
	   
	   public int getStatus() {
		   return status;
	   }
	   
	   public void setStatus(int status) {
		   this.status = status;
	   }
	   
	   public void setQuestionType(int questionType) {
		   this.questionType = questionType;
	   }
	   
	   public int getQuestionType() {
		   return questionType;
	   }
	   
	   /* There are no open questions yet. But in case there are any, this would be the way to deal with them*/
	   public void setOpenAnswer(String userInput) {
		   this.openAnswer = userInput;
	   }
	   
	   public String setOpenAnswer() {
		   return openAnswer;
	   }  
	   
	   /* We don't know yet how big a shed should be for xx sheep but for now we just multiply by 10. However we don't use this right now */
	   public int shedSize() { /* NOT USED RIGHT NOW */
		   return numbofSheep * 10; 
	   }
	  

}
