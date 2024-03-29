package com.model;

import org.drools.runtime.StatefulKnowledgeSession;

/* Multiple choice facts need different text in their buttons. Therefore this class saves these. */
public class MCQuestion extends Question {
	public String answerZero;
	public String answerOne;
	
	public MCQuestion(String name, int questionType, StatefulKnowledgeSession ksession, String question, String explanation, boolean askNow, Model model) {
		super(name, questionType, ksession, question, explanation, askNow, model);
	}
	
	public MCQuestion(String name, int questionType, StatefulKnowledgeSession ksession, String question, String explanation, boolean askNow, Model model, String answerZero, String answerOne) {
		super(name, questionType, ksession, question, explanation, askNow, model);
		this.answerZero = answerZero;
		this.answerOne = answerOne;
	}

	public String getAnswerZero() {
		return answerZero;
	}

	public void setAnswerZero(String answerZero) {
		this.answerZero = answerZero;
	}

	public String getAnswerOne() {
		return answerOne;
	}

	public void setAnswerOne(String answerOne) {
		this.answerOne = answerOne;
	}
	
}
