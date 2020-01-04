package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import com.model.Model;
import com.model.Question;
import com.views.MainView;

public class RightBtnAction {

	JButton btnRight;
	MainView frame;
	Model model;
	
	public RightBtnAction(JButton btnRight, MainView frame, Model model) {
		this.btnRight = btnRight;
		this.frame = frame;
		this.model = model;
		addAction();
	}
	
	private void addAction() {
		btnRight.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Question current = model.getCurrentQuestion();
				current.setAnswer(0); /* 0 is used as NO most often */
				frame.prepareNextQuestion(current);
			}
		});
	}

}