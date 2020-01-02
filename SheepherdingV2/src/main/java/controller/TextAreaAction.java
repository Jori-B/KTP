package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import com.model.Model;
import com.model.Question;
import com.views.MainView;

public class TextAreaAction {

	JButton btnEnterInput;
	JTextField textArea;
	MainView frame;
	Model model;
	
	public TextAreaAction(JButton btnEnterInput, JTextField textArea, MainView frame, Model model) {
		this.btnEnterInput = btnEnterInput;
		this.textArea = textArea;
		this.frame = frame;
		this.model = model;
		addBtnAction();
		addEnterPressAction();
	}
	
	private void enterTextAreaAnswer() {
		Question current = model.getCurrentQuestion();
		String input = textArea.getText().replace("\n", "");
		/* Replacing the \n since sometimes, after 2 text area questions, the \n is inserted */ 
		try {       
        	int numbUserIn = Integer.parseInt(input); 
        	current.setAnswer(numbUserIn);
    		/* Empty the user input text in the field after 'enter' is pressed */
        	frame.emptyTextArea();
    		frame.prepareNextQuestion(current);
        } catch (NumberFormatException e) {
        	JOptionPane.showMessageDialog(new JFrame(), "\'" + input + "\' is not a number.\nPlease try again", "Incorrect input", JOptionPane.PLAIN_MESSAGE);
        	frame.emptyTextArea();
        }
	}
	
	private void addBtnAction() {
		btnEnterInput.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				enterTextAreaAnswer();
			}
		});
	}
	
	private void addEnterPressAction() {
		textArea.addKeyListener(new KeyListener() {

			@Override
			public void keyPressed(KeyEvent e) {
				/* When the user presses enter while the textArea is on screen, enter the answer */
				if(e.getKeyChar() == KeyEvent.VK_ENTER && textArea.isVisible()) {
					enterTextAreaAnswer();
                }    
				
			}

			@Override
			public void keyReleased(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void keyTyped(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
	}
}