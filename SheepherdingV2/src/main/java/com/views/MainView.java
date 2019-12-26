package com.views;

import com.sample.Fact;		
/* This import can be used to reference the Main class, however, there should be another way to initialize the program */
import com.sample.Model;
import com.sample.VariableDefinitions;

import java.awt.BorderLayout;		
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import java.awt.Toolkit;
import javax.swing.UIManager;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.ImageIcon;
import javax.swing.JList;
import javax.swing.JTree;
import javax.swing.JButton;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JProgressBar;
import javax.swing.JLabel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;
import javax.swing.JTable;
import java.awt.FlowLayout;
import javax.swing.BoxLayout;
import javax.swing.DefaultListModel;

import java.awt.GridLayout;
import java.awt.Image;
import java.awt.SystemColor;
import java.awt.Color;
import java.awt.Component;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;

public class MainView extends JFrame implements VariableDefinitions {

	private JPanel contentPane;
	private JLabel lblQuestion;
	public Model model;
	private JButton btnYes;
	private JButton btnNo;
	private JButton enterInput;
	private JTextArea textArea;
	private JButton btnPrevious;
	private JButton btnNext;
	private JList<String> list;
	private DefaultListModel<String> answeredQs;
	/**
	 * Create the frame.
	 */
	public MainView(Model model) {
		setModel(model);
		initComponents();
		createEvents();
	}
	
	private void setVisibilityBtns(boolean textFields, boolean yesNoBtns) {
		textArea.setVisible(textFields);
		enterInput.setVisible(textFields);
		btnYes.setVisible(yesNoBtns);
		btnNo.setVisible(yesNoBtns);
	}
	
	private void setButtons(Fact current) {
		/* If it's the first fact, there is no previous question, so button disabled */
		if (model.getFacts().indexOf(current) == 0) {
			btnPrevious.setEnabled(false);
		/* Else enable the button */
		} else {
			btnPrevious.setEnabled(true);
		}
		/* Depending on the question type certain buttons need to be visible or have their text adjusted */
		switch(current.getQuestionType()) {
			case YESNO:
				setVisibilityBtns(false, true);
				btnYes.setText("Yes");
				btnNo.setText("No");
				break;
			case NUMB:
				setVisibilityBtns(true, false);
				break;
			case MC: // THIS METHOD SHOULD INCLUDE ALL MULTIPLE CHOICE ANSWERS
				setVisibilityBtns(false, true);
				btnNo.setText("Hobby");
				btnYes.setText("Professional");
				break;
		}
		setAnsweredButtonColors(current);
		
	}
	
	private void preparePrevQuestion() {
		Fact prev = model.getPrevQuestion();
		
		/* Remove the previous question element to the left hand side list, since it's going to be answered again */
		answeredQs.removeElement(prev.getName());
		model.setCurrentQuestion(prev);
		model.findPrevQuestion(prev);
		//model.setPrevQuestion(model.getPrevQuestion());
		setButtons(prev);
//		if(prev.getStatus() == HASANSWER) {
//			
//		}
		lblQuestion.setText(prev.getQuestion());
	}
	
	private void setAnsweredButtonColors(Fact current) {
		// If the fact has an answer, color that answer green
		if(current.getStatus() == HASANSWER) {
			// If the current question has an answer, the user should be able to go to the next question
			btnNext.setEnabled(true);
			// If one of these conditions holds, color the answer button green
			if(current.getAnswer() == YES) {
				btnYes.setBackground(Color.GREEN);
				// When a user has changed its answer and tries previous again, only one button should be green
			} else {
				btnYes.setBackground(Color.WHITE);
			} 
			if(current.getAnswer() == NO) {
				btnNo.setBackground(Color.GREEN); 
			} else {
				btnNo.setBackground(Color.WHITE);
			}
			/*If we're going to do MC with three answer options, then this needs to change */
			// For number questions, assuming the user generally answers with higher than 1
			// Might also be possible to just say else???
			if(current.getAnswer() > 1) {
				enterInput.setBackground(Color.GREEN);
				textArea.setText(Integer.toString(current.getAnswer()));
			} else {
				// empty the text area when there isn't an answer ( this else is used when previous question order is changed )
				textArea.setText("");
			}
		// Unanswered questions, color buttons white
		} else {
			btnYes.setBackground(Color.WHITE);
			enterInput.setBackground(Color.WHITE);
			btnNo.setBackground(Color.WHITE);
			// When the next question has no answer the user shouldn't be able to press next
			btnNext.setEnabled(false);
		}
	}
	
	private void prepareNextQuestion(Fact previous) {
		/* No insert here because we're already doing it in  */
		//previous.getKSession().insert(previous);
		/* Add the element to the left hand side list */
		answeredQs.addElement(previous.getName());
		model.findNextQuestion(previous);
		Fact current = model.getCurrentQuestion();
		if(current != previous) {
			setButtons(current);
			lblQuestion.setText(current.getQuestion());
		} else { 
			setVisibilityBtns(false, false);
			lblQuestion.setText("ALL QUESTIONS ASKED");
		}
	}
	
	private void initComponents() {
		//SETTING UP FRAME
		setIconImage(Toolkit.getDefaultToolkit().getImage(MainView.class.getResource("/com/resources/icon_sheep.png")));
		setTitle("Sheep Herder System");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 970, 621);
		contentPane = new JPanel();
		contentPane.setBackground(SystemColor.activeCaption);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);		
		setResizable(false);
		
		//QUESTION PART OF VIEW
		lblQuestion = new JLabel(model.getCurrentQuestion().getQuestion()); // THIS IS NOT THE BEST WAY TO DO IT BUT IT WORKS
		lblQuestion.setHorizontalAlignment(SwingConstants.CENTER);
		lblQuestion.setAlignmentX(Component.CENTER_ALIGNMENT);
//		lblQuestion.setAlignmentY(Component.CENTER_ALIGNMENT); // Does not align the question in the middle sadly :( 
		lblQuestion.setBackground(new Color(47, 79, 79));
		lblQuestion.setForeground(SystemColor.controlLtHighlight);
		lblQuestion.setFont(new Font("Verdana", Font.PLAIN, 20));		
		
		JButton btnYes = new JButton("Yes");
		setYesBtn(btnYes);
		btnYes.setFont(new Font("Verdana", Font.PLAIN, 20));
		
		JButton btnNo = new JButton("No");
		setNoBtn(btnNo);
		btnNo.setFont(new Font("Verdana", Font.PLAIN, 20));
		
		//BELOW THIS BELONGS TO THE RIGHTHAND PANEL, FOR SELECTING PREVIOUS QUESTIONS
		JPanel panel = new JPanel();
		panel.setBorder(null);
		panel.setBackground(new Color(47, 79, 79));
		
		JTextArea textArea = new JTextArea();
		setTextArea(textArea);
		textArea.setFont(new Font("SansSerif", Font.PLAIN, 20));
		textArea.setText("");
		
		JButton enterInput = new JButton("Enter");
		setInputBtn(enterInput);
		enterInput.setFont(new Font("Dialog", Font.PLAIN, 20));
		
		JButton btnPrevious = new JButton("Previous");
		setPrevBtn(btnPrevious);
		
		JButton btnNext = new JButton("Next");
		setNextBtn(btnNext);
		
		setButtons(model.getCurrentQuestion());		
		
		/* Tried to import an image here. It did not work to get the size small.
		 * This should be a way to enter a scaled down image, however I can't get it to work */
//		lblSheepimg.setBounds(20,20,330, 204);
//		ImageIcon sheepPic = new ImageIcon("resources/sheep_pic.jpg");
//		Image img = sheepPic.getImage();
//		Image newImg = img.getScaledInstance(lblSheepimg.getWidth(), lblSheepimg.getHeight(), Image.SCALE_SMOOTH);
//		ImageIcon image = new ImageIcon(newImg);
//		lblSheepimg.setIcon(image);

		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addComponent(panel, GroupLayout.PREFERRED_SIZE, 240, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(1)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
								.addGroup(gl_contentPane.createSequentialGroup()
									.addGap(60)
									.addComponent(btnYes, GroupLayout.PREFERRED_SIZE, 200, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.RELATED, 193, Short.MAX_VALUE)
									.addComponent(btnNo, GroupLayout.PREFERRED_SIZE, 200, GroupLayout.PREFERRED_SIZE)
									.addGap(60))
								.addGroup(gl_contentPane.createSequentialGroup()
									.addComponent(lblQuestion, GroupLayout.DEFAULT_SIZE, 692, Short.MAX_VALUE)
									.addContainerGap())))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(210)
							.addComponent(textArea, GroupLayout.DEFAULT_SIZE, 294, Short.MAX_VALUE)
							.addGap(210))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(260)
							.addComponent(enterInput, GroupLayout.DEFAULT_SIZE, 194, Short.MAX_VALUE)
							.addGap(260))))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(180)
					.addComponent(lblQuestion, GroupLayout.PREFERRED_SIZE, 81, GroupLayout.PREFERRED_SIZE)
					.addGap(38)
					.addComponent(textArea, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnYes, GroupLayout.DEFAULT_SIZE, 40, Short.MAX_VALUE)
						.addComponent(btnNo, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE))
					.addGap(2)
					.addComponent(enterInput, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE)
					.addGap(199))
				.addGroup(gl_contentPane.createSequentialGroup()
					.addComponent(panel, GroupLayout.PREFERRED_SIZE, 560, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		gl_contentPane.setHonorsVisibility(false);
		gl_contentPane.setAutoCreateGaps(true);
		
		JLabel lblAnsweredQuestions = new JLabel("Answered Questions:");
		lblAnsweredQuestions.setForeground(new Color(255, 255, 255));
		lblAnsweredQuestions.setFont(new Font("Verdana", Font.PLAIN, 15));
		
//		JList list_1 = new JList();
		DefaultListModel<String> answeredQs = new DefaultListModel<String>();
		setAnsweredQs(answeredQs);
		JList<String> list_1 = new JList<String>(answeredQs);
		setList(list_1);
		
		list_1.setBackground(new Color(112, 128, 144));
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addGap(20)
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addComponent(list_1, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 200, Short.MAX_VALUE)
						.addComponent(lblAnsweredQuestions, GroupLayout.DEFAULT_SIZE, 200, Short.MAX_VALUE)
						.addGroup(gl_panel.createSequentialGroup()
							.addComponent(btnPrevious, GroupLayout.PREFERRED_SIZE, 80, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED, 40, Short.MAX_VALUE)
							.addComponent(btnNext, GroupLayout.PREFERRED_SIZE, 80, GroupLayout.PREFERRED_SIZE)))
					.addGap(20))
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_panel.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblAnsweredQuestions)
					.addGap(18)
					.addComponent(list_1, GroupLayout.DEFAULT_SIZE, 419, Short.MAX_VALUE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnPrevious, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnNext, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE))
					.addContainerGap())
		);
		gl_panel.setHonorsVisibility(false);
		panel.setLayout(gl_panel);
		contentPane.setLayout(gl_contentPane);
	}
	
	private void setModel(Model model) {
		this.model = model;
	}
	
	private Model getModel() {
		return model;
	}
	
	private JButton getYesBtn() {
		return btnYes;
	}

	private void setYesBtn(JButton btnYes) {
		this.btnYes = btnYes;
	}
	
	private JButton getNoBtn() {
		return btnNo;
	}

	private void setNoBtn(JButton btnNo) {
		this.btnNo = btnNo;
	}
	
	private JButton getInputBtn() {
		return enterInput;
	}

	private void setInputBtn(JButton enterInput) {
		this.enterInput = enterInput;
	}

	private JTextArea getTextArea() {
		return textArea;
	}

	private void setTextArea(JTextArea textArea) {
		this.textArea = textArea;
	}

	private JButton getPrevBtn() {
		return btnPrevious;
	}

	private void setPrevBtn(JButton btnPrevious) {
		this.btnPrevious = btnPrevious;
	}
	
	private JButton getNextBtn() {
		return btnPrevious;
	}

	private void setNextBtn(JButton btnNext) {
		this.btnNext = btnNext;
	}
	
	private void setList(JList<String> list) {
		this.list = list;
	}
	
	private void setAnsweredQs(DefaultListModel<String> answeredQs) {
		this.answeredQs = answeredQs;
	}
	
	private void createEvents() {
		// TODO Auto-generated method stub
		btnYes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Fact current = model.getCurrentQuestion();
				current.setAnswer(YES);
				prepareNextQuestion(current);
			}
		});
		
		btnNo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Fact current = model.getCurrentQuestion();
				current.setAnswer(NO);
				prepareNextQuestion(current);
			}
		});
		
		btnPrevious.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				preparePrevQuestion();
			}
		});
		
		btnNext.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				prepareNextQuestion(model.getCurrentQuestion());
			}
		});
		
		enterInput.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Fact current = model.getCurrentQuestion();
				current.setAnswer(textArea.getText());
				/* Empty the user input text in the field after 'enter' is pressed */
				textArea.setText("");
				prepareNextQuestion(current);
			}
		});
	}
}
