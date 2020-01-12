package controller;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JList;

import com.model.Model;
import com.model.Question;
import com.views.MainView;

public class ListClickAction {

	private JList<String> list;
	MainView frame;
	Model model;
	
	public ListClickAction(JList<String> list, MainView frame, Model model) {
		this.list = list;
		this.frame = frame;
		this.model = model;
		addAction();
	}
	
	private void addAction() {
		list.addMouseListener(new MouseAdapter(){
	          @Override
	          public void mouseClicked(MouseEvent e) { 
	              Question current = model.getSelectedQuestion(list.getSelectedValue());
	              model.setCurrentQuestion(current);
	              frame.updateGUI(current, true);
	          }
	    });
	}

}