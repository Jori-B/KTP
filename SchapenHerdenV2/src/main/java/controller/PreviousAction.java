package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import com.model.Model;
import com.model.Question;
import com.views.MainView;

public class PreviousAction {

	JButton btnPrev;
	MainView frame;
	Model model;
	
	public PreviousAction(JButton btnPrev, MainView frame, Model model) {
		this.btnPrev = btnPrev;
		this.frame = frame;
		this.model = model;
		addAction();
	}
	
	private void addAction() {
		btnPrev.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Question prev = model.getPrevQuestion();
				model.setCurrentQuestion(prev);
				/* find the question before the previous question, to set it */
				model.findPrevQuestion(prev);
				frame.updateGUI(prev, false);
			}
		});
	}

}
