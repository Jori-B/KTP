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
	              System.out.println("Mouse click.");
	              int index = list.getSelectedIndex();
	              System.out.println("Index Selected: " + index);
	              String s = (String) list.getSelectedValue();
	              System.out.println("Value Selected: " + s.toString()); 
	              Question current = model.getSelectedQuestion(list.getSelectedValue());
	              model.setCurrentQuestion(current);
	              frame.updateGUI(current, true);
	          }
	    });
	}

}