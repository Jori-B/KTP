package com.sample;

import org.drools.runtime.StatefulKnowledgeSession;

//import java.math.BigDecimal;

import com.sample.Main;		
import com.sample.Question;

public class Question {
	public StatefulKnowledgeSession ksession;
	
	/* All question types have a name. As of right now we don't really use these */ 
	public static final int YESNO = 0;
    public static final int MC = 1;
    public static final int NUMB = 2;
    public static final int OPEN = 3;
    
    /* This might be used for the status field */
    public static final int UNUSED = 0;
    public static final int USED = 1;
	
    public static final int NO = 0;
    public static final int YES = 1;
    
    /* For multiple choice questions we use these variable names to make the code more human readable in the Rules.dlr file */
    public static final int HOBBY = 0;
    public static final int PRO = 1;
    
    public static final int SELF = 0;
    public static final int OTHER = 1;
    
	   /* Name variable is very important for referencing the questions in the Rules.dlr file */
	   private String name;
	   
	   private int numbofSheep; /* NOT USED RIGHT NOW */
	   private int questionType;
	   private String openAnswer;
	   private int answer;
	   /* We don't use the status variable at the moment. But it might be useful to prevent rules from firing twice */
	   private int status = UNUSED;
	   
	   public Question(String name, int questionType, StatefulKnowledgeSession ksession) {
		   this.name = name;
		   this.questionType = questionType;
		   this.ksession = ksession;
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
