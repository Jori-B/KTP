package com.views;

/* This import can be used to reference the Main class, however, there should be another way to initialize the program */
import com.sample.Main;	

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

public class MainView extends JFrame {

	private JPanel contentPane;
	private JLabel lblQuestion;

//	private static Main startProgram = new Main();
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Throwable e) {
			e.printStackTrace();
		}
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainView frame = new MainView();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public MainView() {

		initComponents();
		createEvents();
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
		lblQuestion = new JLabel("QUESTION");
		lblQuestion.setForeground(SystemColor.controlLtHighlight);
		lblQuestion.setFont(new Font("Roboto", Font.PLAIN, 30));
//		lblQuestion.setText();
		
		
		
		JButton btnYes = new JButton("Yes");
		btnYes.setFont(new Font("Roboto", Font.PLAIN, 25));
		btnYes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				/* This is not the correct way to do this, but something like this should be possible */
//				lblQuestion.setText(startProgram.facts[0].getQuestion());
			}
		});
		
		
		JButton btnNo = new JButton("No");
		btnNo.setFont(new Font("Roboto", Font.PLAIN, 25));
		
		//BELOW THIS BELONGS TO THE RIGHTHAND PANEL, FOR SELECTING PREVIOUS QUESTIONS
		JPanel panel = new JPanel();
		panel.setBackground(new Color(47, 79, 79));
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
							.addGap(226)
							.addComponent(lblQuestion, GroupLayout.DEFAULT_SIZE, 306, Short.MAX_VALUE)
							.addGap(60))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(83)
							.addComponent(btnYes, GroupLayout.PREFERRED_SIZE, 155, GroupLayout.PREFERRED_SIZE)
							.addGap(96)
							.addComponent(btnNo, GroupLayout.PREFERRED_SIZE, 158, GroupLayout.PREFERRED_SIZE)
							.addContainerGap())))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap(184, Short.MAX_VALUE)
					.addComponent(lblQuestion, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)
					.addGap(108)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING, false)
						.addComponent(btnNo, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(btnYes, GroupLayout.PREFERRED_SIZE, 42, GroupLayout.PREFERRED_SIZE))
					.addGap(219))
				.addComponent(panel, GroupLayout.DEFAULT_SIZE, 583, Short.MAX_VALUE)
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

	private void createEvents() {
		// TODO Auto-generated method stub
		
	}
}
