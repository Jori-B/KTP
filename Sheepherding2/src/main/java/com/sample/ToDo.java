package com.sample;

/*
 * [ ] After entering something which is 'not a number' in Fact.java askQuestion() the user should be given the opportunity to try again
 * [ ] Create a class that stores all the calculated numbers?
 * [ ] Implement the question/answers/advice from the flowchart
 * [ ] Extend program and flowchart with the summary from the interview
 * [ ] Connect the questions and answers to the window/GUI
 * 		I do have some ideas: 
 * (1) 	[ ] making facts[100] a variable that can be accessed from the WindowBuilder class 
 * (2) 	[ ] saving the next question and previous question in the Fact, so you can use the previous button 
 * (3) 	[ ] using the array index for looping over the questions that have an ASKED label 
 * (4) 	[ ] making MainWindow a class that can be accessed instead of a separate main(argv ... bla bla ) { } 
 * 			(Don't know if it still works with windowbuilder then). 
 * (5) 	[ ] using the askQuestion() function to enter the Fact.question and answers. 
 * (6) 	[ ] Using the labels YESNO, OPEN, MC and NUMB to create the answer buttons.
 * 			[ ] The YESNO questions all have the same layout as well. Moreover, MC questions with two answers have the same 
 * 				layout as YESNO as well, only their button text needs to be changed e.g. button.setText("hobby"). 
 * 			[ ] So in the Fact class there should probably be a field where you can create an array of answerOptions[]. 
 * 				Then the MainWindow can check what the length of that array is and create length amount of buttons.    
 * 			[ ] Create OPEN and NUMB layout. OPEN and NUMB questions have the same layout, i.e. only a single JTextField 
 * 				plus a button that says something like "Answer". 
 * 				[ ] Create input requirement for NUMB questions (i.e. try{ is this a number } catch (numberFormateException) { "not a number" } 
 */

public class ToDo {
	
}
