package com.sample;

import com.sample.Model;


/* 
 * This class initializes the model
 */

public class Main {
    
	public static final void main(String[] args) {
		Model model = new Model();
		model.createKnowledgeBase(model);
	}
}
