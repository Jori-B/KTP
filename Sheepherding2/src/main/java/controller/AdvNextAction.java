package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import com.model.Model;
import com.views.AdviceWindow;
import com.views.MainView;

public class AdvNextAction {

	JButton btnNext;
	AdviceWindow frame;
	
	public AdvNextAction(JButton btnNext, AdviceWindow frame) {
		this.btnNext = btnNext;
		this.frame = frame;
		addAction();
	}
	
	private void addAction() {
		btnNext.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				frame.setPage(frame.getPageNumber() + 1);
			}
		});
	}

}
