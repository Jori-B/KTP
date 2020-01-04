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
import com.model.Shed;
import com.views.MainView;

public class TextAreaAction {

	JButton btnEnterInput;
	JTextField textArea;
	JTextField lengthArea;
	MainView frame;
	Model model;
	
	public TextAreaAction(JButton btnEnterInput, JTextField textArea, JTextField lengthArea, MainView frame, Model model) {
		this.btnEnterInput = btnEnterInput;
		this.textArea = textArea;
		this.lengthArea = lengthArea;
		this.frame = frame;
		this.model = model;
		addBtnAction();
		addEnterPressAction();
	}
	
	private void enterTextAreaAnswer() {
		Question current = model.getCurrentQuestion();
		if (lengthArea.isVisible()) {
			enterWidthAndHeight(current);
		} else {
			String input = textArea.getText().replace("\n", "");
			/* Replacing the \n since sometimes, after 2 text area questions, the \n is inserted */ 
			try {       
	        	int numbUserIn = Integer.parseInt(input); 
	        	current.setAnswer(numbUserIn);
	    		frame.prepareNextQuestion(current);
	        } catch (NumberFormatException e) {
	        	JOptionPane.showMessageDialog(new JFrame(), "\'" + input + "\' is not a number.\nPlease try again", "Incorrect input", JOptionPane.PLAIN_MESSAGE);
	        	frame.emptyTextArea();
	        }
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
	
	private void enterWidthAndHeight(Question current) {
		String inputWidth = textArea.getText().replace("\n", "");
		String inputLength = lengthArea.getText().replace("\n", "");
		/* Replacing the \n since sometimes, after 2 text area questions, the \n is inserted */ 
		try {       
        	int width = Integer.parseInt(inputWidth); 
        	int length = Integer.parseInt(inputLength); 
        	Shed shed = model.getShed();
        	shed.setWidthAndLength(width, length);
        	/* The fact needs to be entered in the ksession, so the rules can access it */
        	Question qLength = new Question("shedLength", 0);
        	qLength.setKSession(model.getKSession());
        	qLength.setAnswer(shed.getLengthShed());
        	model.getFacts().add(qLength);

        	/* current question in this case is size of shed */
        	current.setAnswer(width * length);
        	frame.setLengthAreaVisible(false);
    		frame.prepareNextQuestion(current);
        } catch (NumberFormatException e) {
        	JOptionPane.showMessageDialog(new JFrame(), "One of the inputs is incorrect. Please try again", "Incorrect input", JOptionPane.PLAIN_MESSAGE);
        	frame.emptyTextArea();
        }
		
	}
}