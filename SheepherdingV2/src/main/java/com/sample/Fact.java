package com.sample;
import java.util.ArrayList;
import java.util.Scanner;	

import org.drools.runtime.StatefulKnowledgeSession;

public class Fact implements VariableDefinitions {
	public StatefulKnowledgeSession ksession;
	public Model model;
	
	    
	/* Create a scanner that can be closed in the Main.java file */ 
	public static Scanner scanner = new Scanner( System.in );
    
	/* Name variable is very important for referencing the questions in the Rules.dlr file */
	private String name;
   
	private int numbOfSheep; /* NOT USED RIGHT NOW */
	private int questionType;
	private String question;
	private String openAnswer;
	private int answer;
	private String warning;
	public boolean askNow;
	
	private int status = NOANSWER;
	   
	   
	public Fact(String name, int questionType, StatefulKnowledgeSession ksession, String question, Model model) {
		this.name = name;
		this.questionType = questionType;
		this.ksession = ksession;
		this.question = question;
		this.status = NOANSWER;
		this.model = model;
		ksession.insert(this);
	}
   
	public Fact(String name, int questionType, StatefulKnowledgeSession ksession, String question, boolean askNow, Model model) {
		this.name = name;
		this.questionType = questionType;
		this.ksession = ksession;
		this.question = question;
		this.model = model;
		this.askNow = askNow;
//		ksession.insert(this);
//		if(askNow) {
//			askQuestion(true);
//		}
	}
	
	public void setAskNow(boolean askNow) {
		this.askNow = askNow;
	}
	
	public boolean getAskNow() {
		return askNow;
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
		getKSession().insert(this);
		this.setStatus(HASANSWER);
		getKSession().fireAllRules();
	}

	public void setAnswer(String userInput) {
		try {       	 	
        	if(questionType != OPEN) { 
        		int numbUserIn = Integer.parseInt(userInput); 
	       	 	setAnswer(numbUserIn);
       	 	} else { /* It's a YESNO, MC or NUMB question */    	 	
       	 		setOpenAnswer(userInput);
       	 	}
       	 	
        } catch (NumberFormatException e) {
        	setWarning("Not a number, please try again.");
            System.out.println(getWarning());
        }
		getKSession().insert(this);
		this.setStatus(HASANSWER);
		getKSession().fireAllRules();
//		this.answer = numbUserIn;
	}
	
	public int getAnswer() {
		return answer;
	}
   
	public void askQuestion(boolean askNow) {
		//System.out.println(question);
		model.setCurrentQuestion(this);
		this.setAskNow(askNow);
//		ArrayList<Fact> factsArr = model.getFacts();
//		System.out.println(factsArr.indexOf(this));
////		model.setNextQuestion();
//        /* Check if user inputs a number */
//		while (true) { 
//			String userInput = scanner.nextLine();
//	        try {       	 	
//	        	if(questionType != OPEN) { 
//	        		int numbUserIn = Integer.parseInt(userInput); 
//		       	 	setAnswer(numbUserIn);
//		       	 	break;
//	       	 	} else { /* It's a YESNO, MC or NUMB question */    	 	
//	       	 		setOpenAnswer(userInput);
//	       	 		break;
//	       	 	}
//	       	 	
//	        } catch (NumberFormatException e) {
//	        	setWarning("Not a number, please try again.");
//	            System.out.println(getWarning());
//	        }
//		}
//        setStatus(HASANSWER);
//
//        if(doInsert) {
//        	ksession.insert(this);
//        } 
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
		return numbOfSheep * 10; 
	}
  
	public void setWarning(String warning){
		this.warning = warning;
	}
	
	public String getWarning(){
		return warning;
	}
}
