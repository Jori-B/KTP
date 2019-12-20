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
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.SystemColor;
import java.awt.Color;
import java.awt.Component;
import javax.swing.JTextArea;

public class MainView extends JFrame implements VariableDefinitions {

	private JPanel contentPane;
	private JLabel lblQuestion;
	public Model model;
	private JButton btnYes;
	private JButton btnNo;
	private JButton enterInput;
	private JTextArea textArea;
	/**
	 * Create the frame.
	 */
	public MainView(Model model) {
		setModel(model);
		initComponents();
		createEvents();
	}
	
	private void setButtons(Fact current) {
		if (current.getQuestionType() == YESNO) {
			textArea.setVisible(false);
			enterInput.setVisible(false);
			btnYes.setVisible(true);
			btnNo.setVisible(true);
		};
		if (current.getQuestionType() == NUMB) {
			textArea.setVisible(true);
			enterInput.setVisible(true);
			btnYes.setVisible(false);
			btnNo.setVisible(false);
		};
	}
	
	private void initComponents() {
		//SETTING UP FRAME
		setIconImage(Toolkit.getDefaultToolkit().getImage(MainView.class.getResource("/com/resources/icon_sheep.png")));
		setTitle("Sheep Herder System");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 896, 649);
		contentPane = new JPanel();
		contentPane.setBackground(SystemColor.activeCaption);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);		
		
		//QUESTION PART OF VIEW
		lblQuestion = new JLabel(model.facts[0].getQuestion()); // THIS IS NOT THE BEST WAY TO DO IT BUT IT WORKS
		lblQuestion.setAlignmentX(Component.CENTER_ALIGNMENT);
//		lblQuestion.setAlignmentY(Component.CENTER_ALIGNMENT); // Does not align the question in the middle sadly :( 
		lblQuestion.setBackground(new Color(47, 79, 79));
		lblQuestion.setForeground(SystemColor.controlLtHighlight);
		lblQuestion.setFont(new Font("Roboto", Font.PLAIN, 20));
//		lblQuestion.setText();
		
		
		
		JButton btnYes = new JButton("Yes");
		setYesBtn(btnYes);
		btnYes.setFont(new Font("Roboto", Font.PLAIN, 20));
		
		JButton btnNo = new JButton("No");
		setNoBtn(btnNo);
		btnNo.setFont(new Font("Roboto", Font.PLAIN, 25));
		
		//BELOW THIS BELONGS TO THE RIGHTHAND PANEL, FOR SELECTING PREVIOUS QUESTIONS
		JPanel panel = new JPanel();
		panel.setBackground(new Color(47, 79, 79));
		/* Tried to import an image here. It did not work to get the size small */
		
		JTextArea textArea = new JTextArea();
		setTextArea(textArea);
		textArea.setFont(new Font("SansSerif", Font.PLAIN, 25));
		textArea.setText("");
		
		JButton enterInput = new JButton("Enter");
		setInputBtn(enterInput);
		enterInput.setFont(new Font("Dialog", Font.PLAIN, 20));
		
		enterInput.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Fact current = model.getCurrentQuestion();
				//current.setAnswer(textArea.getText());
				System.out.println(textArea.getText());
				setButtons(current);
			}
		});
		
		btnNo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Fact current = model.getCurrentQuestion();
				current.setAnswer(NO);
				lblQuestion.setText(current.getQuestion());
				setButtons(current);
			}
		});
		
		btnYes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Fact current = model.getCurrentQuestion();
				current.setAnswer(YES);
				lblQuestion.setText(current.getQuestion());
				setButtons(current);
			}
		});
		
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
					.addComponent(panel, GroupLayout.PREFERRED_SIZE, 271, GroupLayout.PREFERRED_SIZE)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(18)
							.addComponent(lblQuestion, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
							.addGap(0))
						.addGroup(Alignment.TRAILING, gl_contentPane.createSequentialGroup()
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_contentPane.createSequentialGroup()
									.addComponent(btnYes, GroupLayout.PREFERRED_SIZE, 155, GroupLayout.PREFERRED_SIZE)
									.addGap(96)
									.addComponent(btnNo, GroupLayout.PREFERRED_SIZE, 158, GroupLayout.PREFERRED_SIZE))
								.addGroup(gl_contentPane.createSequentialGroup()
									.addGap(127)
									.addComponent(enterInput, GroupLayout.PREFERRED_SIZE, 155, GroupLayout.PREFERRED_SIZE))
								.addGroup(gl_contentPane.createSequentialGroup()
									.addGap(102)
									.addComponent(textArea, GroupLayout.PREFERRED_SIZE, 201, GroupLayout.PREFERRED_SIZE)))
							.addGap(82))))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.TRAILING)
				.addGroup(Alignment.LEADING, gl_contentPane.createSequentialGroup()
					.addGap(128)
					.addComponent(lblQuestion, GroupLayout.PREFERRED_SIZE, 81, GroupLayout.PREFERRED_SIZE)
					.addGap(91)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING, false)
						.addComponent(btnYes, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(btnNo))
					.addGap(41)
					.addComponent(textArea, GroupLayout.PREFERRED_SIZE, 44, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(enterInput, GroupLayout.DEFAULT_SIZE, 42, Short.MAX_VALUE)
					.addGap(105))
				.addComponent(panel, GroupLayout.DEFAULT_SIZE, 592, Short.MAX_VALUE)
		);
		
		JButton btnPrevious = new JButton("Previous");
		btnPrevious.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		
		JButton btnNext = new JButton("Next");
		
		JLabel lblAnsweredQuestions = new JLabel("Answered Questions:");
		lblAnsweredQuestions.setForeground(new Color(255, 255, 255));
		lblAnsweredQuestions.setFont(new Font("Roboto", Font.PLAIN, 15));
		
		JList list_1 = new JList();
		list_1.setBackground(new Color(112, 128, 144));
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addComponent(list_1, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 247, Short.MAX_VALUE)
						.addComponent(lblAnsweredQuestions, GroupLayout.DEFAULT_SIZE, 247, Short.MAX_VALUE)
						.addGroup(gl_panel.createSequentialGroup()
							.addComponent(btnPrevious, GroupLayout.PREFERRED_SIZE, 92, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED, 65, Short.MAX_VALUE)
							.addComponent(btnNext, GroupLayout.PREFERRED_SIZE, 90, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap())
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_panel.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblAnsweredQuestions)
					.addGap(18)
					.addComponent(list_1, GroupLayout.DEFAULT_SIZE, 478, Short.MAX_VALUE)
					.addGap(18)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnPrevious, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnNext, GroupLayout.PREFERRED_SIZE, 28, GroupLayout.PREFERRED_SIZE))
					.addContainerGap())
		);
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

	private void createEvents() {
		// TODO Auto-generated method stub
		
	}
}
