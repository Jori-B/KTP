package com.views;

import com.model.MCQuestion;		
import com.model.Model;
import com.model.Question;
import com.model.VariableDefinitions;

import controller.InfoClickAction;
import controller.LeftBtnAction;
import controller.ListClickAction;
import controller.NextAction;
import controller.PreviousAction;
import controller.RightBtnAction;
import controller.TextAreaAction;


import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import java.awt.Toolkit;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.ImageIcon;
import javax.swing.JList;

import javax.swing.JButton;
import javax.swing.LayoutStyle.ComponentPlacement;

import javax.swing.JLabel;

import java.util.ArrayList;

import java.awt.Font;

import javax.swing.DefaultListModel;
import java.awt.SystemColor;
import java.awt.Color;
import java.awt.Component;

import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class MainView extends JFrame implements VariableDefinitions {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JLabel lblQuestion;
	public Model model;
	
	private JButton btnLeft;
	private JButton btnRight;
	
	private JButton btnEnterInput;
	private JTextField textArea;
	private JButton btnPrevious;
	private JButton btnNext;
	
	private JList<String> list;
	private DefaultListModel<String> answeredQs;
	
	private JTextField lengthArea;
	private JLabel lblWidth;
	private JLabel lblLength;
	
	private JLabel lblInformationText;
	private JLabel lblInfo;
	
	/**
	 * Create the frame.
	 */
	public MainView(Model model) {
		setModel(model);
		initComponents();
		createEvents();
		this.setLocationRelativeTo(null);
	}
	
	public void prepareNextQuestion(Question previous) {
		model.findNextQuestion(previous);
		Question current = model.getCurrentQuestion();
		if(current != previous) {
			updateGUI(current, true);
		} else { 
			setVisibilityBtns(false, false);
			lblQuestion.setText("ALL QUESTIONS ASKED");
		}
	}
	
	private void setVisibilityBtns(boolean textFields, boolean yesNoBtns) {
		textArea.setVisible(textFields);
		btnEnterInput.setVisible(textFields);
		btnLeft.setVisible(yesNoBtns);
		btnRight.setVisible(yesNoBtns);
	}
	
	public void updateGUI(Question current, boolean questionNotInList) {
		emptyTextArea();
		setButtons(current);
		setQuestion(current);
		setInformationText(current);
		removeRedundantItemsFromList();
		if(questionNotInList) {
			addToList(current);
		}
	}
	
	public void emptyTextArea() {
		textArea.setText("");
	}
	
	private void setButtons(Question current) {
		/* If it's the first question, there is no previous question, so button disabled */
		if (model.getFacts().indexOf(current) == 0) {
			btnPrevious.setEnabled(false);
			/* First list element is added when the first question is asked. The rest of them are added in setCurrentQuestion() */
			addToList(current);
		/* Else enable the button */
		} else {
			btnPrevious.setEnabled(true);
		}
		/* Depending on the question type certain buttons need to be visible or have their text adjusted */
		switch(current.getQuestionType()) {
			case YESNO:
				setVisibilityBtns(false, true);
				btnLeft.setText("Yes");
				btnRight.setText("No");
				break;
			case NUMB:
				setVisibilityBtns(true, false);
				/* Request focus to the textarea so the user can start typing straight away */
				textArea.requestFocus();
				break;
			case MC: 
				setVisibilityBtns(false, true);
				/* Cast the current fact to a MCFact so the getAnswer methods can be used */
				MCQuestion currentMC = (MCQuestion)model.getSelectedQuestion(current.getName());
				btnRight.setText(currentMC.getAnswerZero());
				btnLeft.setText(currentMC.getAnswerOne());
				break;
		}
		if (current.getName() == "Shed Size") {
			setUpTwoTextFields();
		} else { 
			setLengthAreaVisible(false);
		}
		setAnsweredButtonColors(current);
	}
	
	private void setUpTwoTextFields() {
		setLengthAreaVisible(true);
		lengthArea.requestFocus();
	}
	
	private void setShedAnswers() {
		double lengthShed = model.getShed().getLengthShed();
		if (lengthShed > 0) { /* when one of them has an answer, the other has an answer as well */
			lengthArea.setText(Double.toString(lengthShed));
			textArea.setText(Double.toString(model.getShed().getWidthShed()));
		}
	}
	
	private void setAnsweredButtonColors(Question current) {
		double answer = current.getAnswer();
		// If the fact has an answer, color that answer green
		if(current.getStatus() == HASANSWER) {
			// If the current question has an answer, the user should be able to go to the next question
			btnNext.setEnabled(true);
			// If one of these conditions holds, color the answer button red
			if(answer == YES) {
				btnLeft.setBackground(Color.RED);
				// When a user has changed its answer and tries previous again, only one button should be green
			} else {
				btnLeft.setBackground(Color.WHITE);
			} 
			if(answer == NO) {
				btnRight.setBackground(Color.RED); 
			} else {
				btnRight.setBackground(Color.WHITE);
			}
			if(current.getQuestionType() == NUMB && answer > 0) {
				btnEnterInput.setBackground(Color.RED);
				textArea.setText(Double.toString(current.getAnswer()));
				if(current.getName() == "Shed Size") {
					setShedAnswers();
				}
			} else {
				// empty the text area when there isn't an answer ( this else is used when previous question order is changed )
				emptyTextArea();
			}
		// For unanswered questions, color buttons white
		} else {
			btnLeft.setBackground(Color.WHITE);
			btnEnterInput.setBackground(Color.WHITE);
			btnRight.setBackground(Color.WHITE);
			// When the next question has no answer the user shouldn't be able to press next
			btnNext.setEnabled(false);
		}
	}
	
	public void setQuestion(Question current) {
		lblQuestion.setText(current.getQuestion());
	}
	
	public void setInformationText(Question current) {
		lblInformationText.setText(current.getExplanation());
	}
	
	/* Add the element to the left hand side list, only if the item is not in the list yet */
	private void addToList(Question current) {
		if(!answeredQs.contains(current.getName())) {
			answeredQs.addElement(current.getName());
		}
	}
	
	private void removeRedundantItemsFromList() {
		/* Sometimes the user changes answers, making a question item in the list redundant. These need to be removed from the list */
		ArrayList<String> removeFromList = model.getRemoveFromList();
		if(!removeFromList.isEmpty()) {
			for (String item : removeFromList) {
				if(answeredQs.contains(item)) {
					answeredQs.removeElement(item);
				}
			}
			model.clearRemoveFromList();
		}
	}
	
	public void setLengthAreaVisible(boolean setVisible) {
		lengthArea.setVisible(setVisible);
		lblWidth.setVisible(setVisible);
		lblLength.setVisible(setVisible);
	}
	
	private void initComponents() {
		//SETTING UP FRAME
		setIconImage(Toolkit.getDefaultToolkit().getImage(MainView.class.getResource("/com/resources/icon_sheep.png")));
		setTitle("Sheep Herder System");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 974, 833);
		contentPane = new JPanel();
		contentPane.setBackground(SystemColor.activeCaption);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);		
		setResizable(true);
		
		//QUESTION PART OF VIEW
		lblQuestion = new JLabel(model.getCurrentQuestion().getQuestion()); // THIS IS NOT THE BEST WAY TO DO IT BUT IT WORKS
		lblQuestion.setHorizontalAlignment(SwingConstants.CENTER);
		lblQuestion.setAlignmentX(Component.CENTER_ALIGNMENT);
		lblQuestion.setBackground(new Color(47, 79, 79));
		lblQuestion.setForeground(Color.BLACK);
		lblQuestion.setFont(new Font("Verdana", Font.PLAIN, 20));		
		
		JButton btnLeft = new JButton("Yes");
		setYesBtn(btnLeft);
		btnLeft.setFont(new Font("Verdana", Font.PLAIN, 20));
		
		JButton btnRight = new JButton("No");
		setNoBtn(btnRight);
		btnRight.setFont(new Font("Verdana", Font.PLAIN, 20));
		
		//BELOW THIS BELONGS TO THE RIGHTHAND PANEL, FOR SELECTING PREVIOUS QUESTIONS
		JPanel panel = new JPanel();
		panel.setBorder(null);
		panel.setBackground(new Color(47, 79, 79));
		
		JTextField textArea = new JTextField();
		setTextArea(textArea);
		textArea.setFont(new Font("SansSerif", Font.PLAIN, 20));
		emptyTextArea();
		
		JButton btnEnterInput = new JButton("Enter");
		setInputBtn(btnEnterInput);
		btnEnterInput.setFont(new Font("Dialog", Font.PLAIN, 20));
		
		JButton btnPrevious = new JButton("Previous");
		setPrevBtn(btnPrevious);
		
		JButton btnNext = new JButton("Next");
		setNextBtn(btnNext);
		
		/* Tried to import an image here. It did not work to get the size small.
		 * This should be a way to enter a scaled down image, however I can't get it to work */
		
		JTextField lengthArea = new JTextField();
		lengthArea.setText("");
		lengthArea.setFont(new Font("SansSerif", Font.PLAIN, 20));
		setLengthArea(lengthArea);
		
		JLabel lblWidth = new JLabel("Width:");
		lblWidth.setFont(new Font("Tahoma", Font.PLAIN, 16));
		setLblWidth(lblWidth);
		
		JLabel lblLength = new JLabel("Length:");
		lblLength.setFont(new Font("Tahoma", Font.PLAIN, 16));
		setlblLength(lblLength);
		
		JLabel lblInfo = new JLabel("");
		lblInfo.setToolTipText("If you want more information about the question, click here.");
		lblInfo.setIcon(new ImageIcon(MainView.class.getResource("/com/resources/infoBubble2.png")));
		setLblInfo(lblInfo);
		
		JLabel lblInformationText = new JLabel(model.getCurrentQuestion().getExplanation());
		lblInformationText.setFont(new Font("Tahoma", Font.PLAIN, 15));
		
		lblInformationText.setForeground(Color.BLACK);
		lblInformationText.setOpaque(true);
		lblInformationText.setBackground(new Color(119, 136, 153));
		lblInformationText.setVisible(true);
		setLblInformationText(lblInformationText);
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
					.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
						.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING, false)
							.addGroup(Alignment.TRAILING, gl_contentPane.createSequentialGroup()
								.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
									.addGroup(gl_contentPane.createSequentialGroup()
										.addGap(48)
										.addComponent(lblInformationText, GroupLayout.PREFERRED_SIZE, 555, GroupLayout.PREFERRED_SIZE)
										.addGap(18)
										.addComponent(lblInfo))
									.addGroup(gl_contentPane.createSequentialGroup()
										.addGap(110)
										.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
											.addGroup(gl_contentPane.createSequentialGroup()
												.addComponent(lblLength, GroupLayout.PREFERRED_SIZE, 61, GroupLayout.PREFERRED_SIZE)
												.addGap(45)
												.addComponent(lengthArea, GroupLayout.PREFERRED_SIZE, 280, GroupLayout.PREFERRED_SIZE))
											.addGroup(gl_contentPane.createSequentialGroup()
												.addComponent(lblWidth, GroupLayout.PREFERRED_SIZE, 61, GroupLayout.PREFERRED_SIZE)
												.addGap(42)
												.addComponent(textArea, GroupLayout.PREFERRED_SIZE, 281, GroupLayout.PREFERRED_SIZE)))))
								.addGap(66))
							.addGroup(gl_contentPane.createSequentialGroup()
								.addGap(54)
								.addComponent(btnLeft, GroupLayout.PREFERRED_SIZE, 226, GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(btnRight, GroupLayout.PREFERRED_SIZE, 224, GroupLayout.PREFERRED_SIZE)
								.addGap(57))
							.addGroup(gl_contentPane.createSequentialGroup()
								.addGap(252)
								.addComponent(btnEnterInput, GroupLayout.PREFERRED_SIZE, 211, GroupLayout.PREFERRED_SIZE)
								.addContainerGap()))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(lblQuestion, GroupLayout.PREFERRED_SIZE, 697, GroupLayout.PREFERRED_SIZE)
							.addContainerGap())))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addComponent(panel, GroupLayout.DEFAULT_SIZE, 703, Short.MAX_VALUE)
					.addContainerGap())
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap(50, Short.MAX_VALUE)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
						.addComponent(lblInfo)
						.addComponent(lblInformationText, GroupLayout.PREFERRED_SIZE, 121, GroupLayout.PREFERRED_SIZE))
					.addGap(55)
					.addComponent(lblQuestion, GroupLayout.PREFERRED_SIZE, 81, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(lblLength, GroupLayout.PREFERRED_SIZE, 26, GroupLayout.PREFERRED_SIZE)
						.addComponent(lengthArea, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE))
					.addGap(27)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(textArea, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblWidth, GroupLayout.PREFERRED_SIZE, 26, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnRight, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnLeft, GroupLayout.DEFAULT_SIZE, 40, Short.MAX_VALUE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(btnEnterInput, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE)
					.addGap(210))
		);
		gl_contentPane.setHonorsVisibility(false);
		gl_contentPane.setAutoCreateGaps(true);
		
		JLabel lblAnsweredQuestions = new JLabel("Questions:");
		lblAnsweredQuestions.setForeground(new Color(255, 255, 255));
		lblAnsweredQuestions.setFont(new Font("Verdana", Font.PLAIN, 15));

		DefaultListModel<String> answeredQs = new DefaultListModel<String>();
		setAnsweredQs(answeredQs);
		JList<String> list_1 = new JList<String>(answeredQs);
		list_1.setFont(new Font("Tahoma", Font.PLAIN, 13));
		list_1.setBackground(new Color(112, 128, 144));
		setList(list_1);
		
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
		
		setButtons(model.getCurrentQuestion());	
	}
	
	private void createEvents() {
		new LeftBtnAction(btnLeft, this, model);
		new RightBtnAction(btnRight, this, model);
		new PreviousAction(btnPrevious, this, model);
		new NextAction(btnNext, this, model);
		new TextAreaAction(btnEnterInput, textArea, lengthArea, this, model);
		new ListClickAction(list, this, model);
		new InfoClickAction(lblInfo, lblInformationText, this, model);
	}
	
	private void setModel(Model model) {
		this.model = model;
	}
	
//	private Model getModel() {
//		return model;
//	}
//	
//	private JButton getYesBtn() {
//		return btnLeft;
//	}

	private void setYesBtn(JButton btnLeft) {
		this.btnLeft = btnLeft;
	}
	
//	private JButton getNoBtn() {
//		return btnRight;
//	}

	private void setNoBtn(JButton btnRight) {
		this.btnRight = btnRight;
	}
	
//	private JButton getInputBtn() {
//		return btnEnterInput;
//	}

	private void setInputBtn(JButton btnEnterInput) {
		this.btnEnterInput = btnEnterInput;
	}

//	private JTextField getTextArea() {
//		return textArea;
//	}

	private void setTextArea(JTextField textArea) {
		this.textArea = textArea;
	}

//	private JButton getPrevBtn() {
//		return btnPrevious;
//	}

	private void setPrevBtn(JButton btnPrevious) {
		this.btnPrevious = btnPrevious;
	}
	
//	private JButton getNextBtn() {
//		return btnPrevious;
//	}

	private void setNextBtn(JButton btnNext) {
		this.btnNext = btnNext;
	}
	
	private void setList(JList<String> list) {
		this.list = list;
	}
	
	private void setAnsweredQs(DefaultListModel<String> answeredQs) {
		this.answeredQs = answeredQs;
	}
	
	private void setLengthArea(JTextField lengthArea) {
		this.lengthArea = lengthArea;
		lengthArea.setVisible(false);
	}
	
	private void setLblWidth(JLabel lblWidth) {
		this.lblWidth= lblWidth;
		lblWidth.setVisible(false);
	}
	
	private void setlblLength(JLabel lblLength) {
		this.lblLength = lblLength;
		lblLength.setVisible(false);
	}
	
	private void setLblInfo(JLabel lblInfo) {
		this.lblInfo = lblInfo;
	}
	
	private void setLblInformationText(JLabel lblInformationText) {
		this.lblInformationText = lblInformationText;
	}
}
