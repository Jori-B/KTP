package com.sample;

import org.drools.runtime.StatefulKnowledgeSession;

/* THIS DOESN'T WORK YET!!! */

public class MCFact extends Fact {
	public String answerZero;
	public String answerOne;
	
	public MCFact(String name, int questionType, StatefulKnowledgeSession ksession, String question, boolean askNow, Model model) {
		super(question, questionType, ksession, question, model);
	}
	
	public MCFact(String name, int questionType, StatefulKnowledgeSession ksession, String question, boolean askNow, Model model, String answerZero, String answerOne) {
		super(question, questionType, ksession, question, model);
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
