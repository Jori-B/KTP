package com.sample;

import org.drools.runtime.StatefulKnowledgeSession;
import org.drools.runtime.rule.FactHandle;

public class Fact implements VariableDefinitions {
	public StatefulKnowledgeSession ksession;
	public Model model;
    
	/* Name variable is very important for referencing the questions in the Rules.dlr file */
	private String name;
   
	private int questionType;
	private String question;
	/* We don't use open answer, since it is hard to create rules for that */
	private String openAnswer;
	private int answer;
	private String warning;
	public boolean askNow;
	public FactHandle factHandle;
	
	private int status = NOANSWER;
	   
	/* This instantiation of a Fact is only used for shedTooSmall, MAYBE CONVERTING TO AN INT IS NOT THE CORRECT WAY */   
	public Fact(String name, double answer) {
		this.name = name;
		this.answer = (int)answer;
	}
	
	/*
	 * This instantiation is used for second order questions that need to be asked
	 * only when certain questions have certain answers
	 */
	public Fact(String name, int questionType, StatefulKnowledgeSession ksession, String question, Model model) {
		this.name = name;
		this.questionType = questionType;
		this.ksession = ksession;
		this.question = question;
		this.status = NOANSWER;
		this.model = model;
		this.factHandle = ksession.insert(this);
	}
   
	/* This instantiation is used for questions that always need to be asked */
	public Fact(String name, int questionType, StatefulKnowledgeSession ksession, String question, boolean askNow, Model model) {
		this.name = name;
		this.questionType = questionType;
		this.ksession = ksession;
		this.question = question;
		this.model = model;
		this.askNow = askNow;
		this.status = NOANSWER;
//		ksession.insert(this);
	}
   
	/* These answers are used in the Rules.dlr file */
	public void setAnswer(int numbUserIn) {
		this.answer = numbUserIn;
		this.setStatus(HASANSWER);
		/* Updating here so that previous questions with changed answers update in the rules file */
		this.factHandle = ksession.insert(this);
		ksession.update(factHandle, this);
		ksession.fireAllRules();
	}
	
	public int getAnswer() {
		return answer;
	}
   
	public void askQuestion(boolean askNow) {
		model.setCurrentQuestion(this);
		this.setAskNow(askNow);
	}
   
	public void setAskNow(boolean askNow) {
		this.askNow = askNow;
		/* 
		 * If the user changed his answer, askNow will be set to false. The frame needs to know which question is
		 * no no longer to be asked
		 */
		if(!askNow) {
			model.setRemoveFromList(this.getName());
		}
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
  
	public void setWarning(String warning){
		this.warning = warning;
	}
	
	public String getWarning(){
		return warning;
	}

}
