package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import com.model.Model;
import com.model.Question;
import com.views.MainView;

public class LeftBtnAction {

	JButton btnLeft;
	MainView frame;
	Model model;
	
	public LeftBtnAction(JButton btnLeft, MainView frame, Model model) {
		this.btnLeft = btnLeft;
		this.frame = frame;
		this.model = model;
		addAction();
	}
	
	private void addAction() {
		btnLeft.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Question current = model.getCurrentQuestion();
				current.setAnswer(1); /* 1 is used as YES most often */
				frame.prepareNextQuestion(current);
			}
		});
	}

}
