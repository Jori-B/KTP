package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
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
	              /* Empty text area here, because you might go to the current questions, which has no answer */
	              frame.emptyTextArea();
	              Question current = model.getSelectedQuestion(list.getSelectedValue());
	              model.setCurrentQuestion(current);
	              frame.setCurQuestion(current);
	          }
	    });
	}

}