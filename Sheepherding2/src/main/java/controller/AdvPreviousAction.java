package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import com.model.Model;
import com.model.Question;
import com.views.AdviceWindow;
import com.views.MainView;

public class AdvPreviousAction {

	JButton btnPrev;
	AdviceWindow frame;
	
	public AdvPreviousAction(JButton btnPrev,  AdviceWindow frame) {
		
		this.btnPrev = btnPrev;
		this.frame = frame;
		addAction();
	}
		
		private void addAction() {
			btnPrev.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					frame.setPage(frame.getPageNumber() - 1);
				}
			});
		}

}
