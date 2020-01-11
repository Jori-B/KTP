package com.model;

import org.drools.runtime.StatefulKnowledgeSession;
import org.drools.runtime.rule.FactHandle;

public class Question implements VariableDefinitions {
	public StatefulKnowledgeSession ksession;
	public Model model;
    
	/* Name variable is very important for referencing the questions in the Rules.dlr file */
	private String name;
   
	private int questionType;
	private String question;
	private String explanation;
	/* We don't use open answer, since it is hard to create rules for that */
	private String openAnswer;
	private double answer;
	private String warning;
	public boolean askNow;
	public FactHandle factHandle;
	
	private int status = NOANSWER;
	private boolean isModified = false;
   
	/* This instantiation is used for questions that always need to be asked */
	public Question(String name, int questionType, StatefulKnowledgeSession ksession, String question, String explanation, boolean askNow, Model model) {
		this.name = name;
		this.questionType = questionType;
		this.ksession = ksession;
		this.question = question;
		this.explanation = explanation;
		this.model = model;
		this.askNow = askNow;
		this.status = NOANSWER;
		/* Second order questions need to be entered into the knowledge base directly, so the QuestionRules.dlr file knows about 
		 * these questions and can ask them (only) when certain questions have certain answers*/
		if(!askNow) {
			this.factHandle = ksession.insert(this);
		}
	}
	
	/* This instantiation of a Fact is only used for shedTooSmall, MAYBE CONVERTING TO AN INT IS NOT THE CORRECT WAY */   
	public Question(String name, double answer) {
		this.name = name;
		this.answer = (int)answer;
	}
   
	/* These answers are used in the Rules.dlr file */
	public void setAnswer(double numbUserIn) {
		this.answer = numbUserIn;
		this.setStatus(HASANSWER);
		/* Updating here so that previous questions with changed answers update in the rules file */
		this.factHandle = ksession.insert(this);
		if(name == "shedLength") {
			System.out.println("shed length is: " + numbUserIn);
		}
		ksession.update(factHandle, this);
		ksession.fireAllRules();
	}
	
	
	public double getAnswer() {
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
	
	public String getExplanation() {
		return explanation;
	}

	public void setExplanation(String explanation) {
		this.explanation = explanation;
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
	
	public boolean getIsModified() {
		return isModified;
	}
	
	public void setIsModified(boolean isModified) {
		this.isModified = isModified;
	}
	
	public Model getModel() {
		return model;
	}

}
