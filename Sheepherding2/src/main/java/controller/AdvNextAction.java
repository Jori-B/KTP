package controller;

import java.awt.event.ActionEvent;	
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JButton;

import com.views.AdviceWindow;

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
