package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import com.model.Model;
import com.views.MainView;

public class NextAction {

	JButton btnNext;
	MainView frame;
	Model model;
	
	public NextAction(JButton btnNext, MainView frame, Model model) {
		this.btnNext = btnNext;
		this.frame = frame;
		this.model = model;
		addAction();
	}
	
	private void addAction() {
		btnNext.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				frame.prepareNextQuestion(model.getCurrentQuestion());
			}
		});
	}

}
