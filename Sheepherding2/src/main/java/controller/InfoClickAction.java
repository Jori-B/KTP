package controller;

import java.awt.Cursor;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JLabel;
import javax.swing.JList;

import com.model.Model;
import com.model.Question;
import com.views.MainView;

public class InfoClickAction {

	JLabel lblInfo;
	JLabel lblInformationText;
	MainView frame;
	Model model;
	
	public InfoClickAction(JLabel lblInfo, JLabel lblInformationText, MainView frame, Model model) {
		this.lblInfo = lblInfo;
		this.lblInformationText = lblInformationText;
		this.frame = frame;
		this.model = model;
		addAction();
	}
	
	private void addAction() {
		lblInfo.setCursor(Cursor.getPredefinedCursor((Cursor.HAND_CURSOR)));
		lblInfo.addMouseListener(new MouseAdapter(){
	          @Override
	          public void mouseClicked(MouseEvent e) {
	              if(lblInformationText.isVisible()) {
	            	  lblInformationText.setVisible(false);
	              } else {
	            	  lblInformationText.setVisible(true);
	              }

	          }
	    });
	}

}