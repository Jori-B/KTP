package com.views;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import java.awt.Font;
import javax.swing.SwingConstants;
import java.awt.SystemColor;
import java.awt.Toolkit;

public class AdviceWindow extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					AdviceWindow frame = new AdviceWindow();
//					frame.setVisible(true);
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		});
//	}


	/**
	 * Create the frame.
	 */
	public AdviceWindow(String businessAdvice, String sheepAdvice, String landAdvice, String shedAdvice, String materialsAdvice, String careAdvice) {
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1017, 1029);
		contentPane = new JPanel();
		contentPane.setBackground(SystemColor.activeCaption);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.X_AXIS));
		
		JLayeredPane layeredPane = new JLayeredPane();
		contentPane.add(layeredPane);
		
		centreWindow(this);
		
		JLabel lblBusiness = new JLabel("Business");
		lblBusiness.setHorizontalAlignment(SwingConstants.LEFT);
		lblBusiness.setVerticalAlignment(SwingConstants.TOP);
		lblBusiness.setFont(new Font("Arial Black", Font.BOLD, 16));
		lblBusiness.setBounds(12, 0, 965, 30);
		layeredPane.add(lblBusiness);
		
		JLabel lblSheep = new JLabel("Sheep");
		lblSheep.setHorizontalAlignment(SwingConstants.LEFT);
		lblSheep.setVerticalAlignment(SwingConstants.TOP);
		lblSheep.setFont(new Font("Arial Black", Font.BOLD, 16));
		lblSheep.setBounds(12, 158, 965, 30);
		layeredPane.add(lblSheep);
		
		JLabel lblLand = new JLabel("Land");
		lblLand.setHorizontalAlignment(SwingConstants.LEFT);
		lblLand.setVerticalAlignment(SwingConstants.TOP);
		lblLand.setFont(new Font("Arial Black", Font.BOLD, 16));
		lblLand.setBounds(12, 318, 965, 30);
		layeredPane.add(lblLand);
		
		JLabel lblShed = new JLabel("Shed");
		lblShed.setHorizontalAlignment(SwingConstants.LEFT);
		lblShed.setVerticalAlignment(SwingConstants.TOP);
		lblShed.setFont(new Font("Arial Black", Font.BOLD, 16));
		lblShed.setBounds(12, 475, 965, 30);
		layeredPane.add(lblShed);
		
		JLabel lblMaterials = new JLabel("Materials");
		lblMaterials.setHorizontalAlignment(SwingConstants.LEFT);
		lblMaterials.setVerticalAlignment(SwingConstants.TOP);
		lblMaterials.setFont(new Font("Arial Black", Font.BOLD, 16));
		lblMaterials.setBounds(12, 630, 965, 30);
		layeredPane.add(lblMaterials);
		
		JLabel lblCare = new JLabel("Care");
		lblCare.setHorizontalAlignment(SwingConstants.LEFT);
		lblCare.setVerticalAlignment(SwingConstants.TOP);
		lblCare.setFont(new Font("Arial Black", Font.BOLD, 16));
		lblCare.setBounds(12, 786, 965, 30);
		layeredPane.add(lblCare);
		
		JLabel lblBusinessAdvice = new JLabel("Business advice");
		lblBusinessAdvice.setVerticalAlignment(SwingConstants.TOP);
		layeredPane.setLayer(lblBusinessAdvice, 0);
		lblBusinessAdvice.setBounds(12, 31, 965, 125);
		lblBusinessAdvice.setOpaque(true);
		lblBusinessAdvice.setBackground(Color.WHITE);
		lblBusinessAdvice.setText(businessAdvice);
		layeredPane.add(lblBusinessAdvice);
		
//		String text = "<html>h";
//		text = text + "<br>hi";
//		text = text + "<html>";
		
		JLabel lblSheepAdvice = new JLabel("Sheep advice");
		lblSheepAdvice.setVerticalAlignment(SwingConstants.TOP);
		lblSheepAdvice.setBounds(12, 185, 965, 125);
		lblSheepAdvice.setOpaque(true);
		lblSheepAdvice.setBackground(Color.WHITE);
		lblSheepAdvice.setText(sheepAdvice);
		layeredPane.add(lblSheepAdvice);
		/* Roughly 130 characters fit on a line and 7 lines fit in the advice label */
		
		JLabel lblLandAdvice = new JLabel("Land advice");
		lblLandAdvice.setVerticalAlignment(SwingConstants.TOP);
		lblLandAdvice.setText("<html> hhhhhhhhhhhhhhhhhhhhh<br> h<br> h<br> h<br> h<br> h <html>");
		lblLandAdvice.setOpaque(true);
		lblLandAdvice.setBackground(Color.WHITE);
		lblLandAdvice.setText(landAdvice);
		lblLandAdvice.setBounds(12, 347, 965, 125);
		layeredPane.add(lblLandAdvice);
		
		JLabel lblShedAdvice = new JLabel("Shed advice");
		lblShedAdvice.setVerticalAlignment(SwingConstants.TOP);
		lblShedAdvice.setBounds(12, 506, 965, 125);
		lblShedAdvice.setOpaque(true);
		lblShedAdvice.setBackground(Color.WHITE);
		lblShedAdvice.setText(shedAdvice);
		layeredPane.add(lblShedAdvice);
		
		JLabel lblMaterialsAdvice = new JLabel("Materials advice");
		lblMaterialsAdvice.setVerticalAlignment(SwingConstants.TOP);
		lblMaterialsAdvice.setBounds(12, 659, 965, 125);
		lblMaterialsAdvice.setOpaque(true);
		lblMaterialsAdvice.setBackground(Color.WHITE);
		lblMaterialsAdvice.setText(materialsAdvice);
		layeredPane.add(lblMaterialsAdvice);
		
		JLabel lblCareAdvice = new JLabel("Care advice");
		lblCareAdvice.setVerticalAlignment(SwingConstants.TOP);
		lblCareAdvice.setBounds(12, 818, 965, 125);
		lblCareAdvice.setOpaque(true);
		lblCareAdvice.setBackground(Color.WHITE);
		lblCareAdvice.setText(careAdvice);
		layeredPane.add(lblCareAdvice);
	}
	
	/* Center the frame in the center of the screen */
	public static void centreWindow(AdviceWindow frame) {
	    Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
	    int x = (int) ((dimension.getWidth() - frame.getWidth()) / 2);
	    int y = (int) ((dimension.getHeight() - frame.getHeight()) / 2);
	    frame.setLocation(x, y);
	}
}
