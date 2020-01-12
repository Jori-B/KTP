package com.views;
	
import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;

import com.model.Cost;
import com.model.Model;

import com.views.FrameLocationSetter;

import controller.AdvNextAction;
import controller.AdvPreviousAction;
import controller.NextAction;
import controller.PreviousAction;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import java.awt.Font;

import javax.swing.SwingConstants;
import java.awt.SystemColor;
import java.awt.Toolkit;
import javax.swing.JButton;
import javax.swing.ImageIcon;

public class AdviceWindow extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	
	JLabel lblBusiness;
	JLabel lblSheep;
	JLabel lblLand;
	JLabel lblShed;
	JLabel lblMaterials;
	JLabel lblCare;
	JLabel lblBusinessAdvice;
	JLabel lblSheepAdvice;
	JLabel lblLandAdvice;
	JLabel lblShedAdvice;
	JLabel lblMaterialsAdvice;
	JLabel lblCareAdvice;
	JButton btnPrev;
	JButton btnNext;
	JLabel lblShedimg;
	JLabel lblSheepimg;
	JLabel lblBusinessimg;
	JLabel lblCareimg;
	JLabel lblMaterialsimg;
	JLabel lblLandimg;
	
	public int pageNumber;
	/**
	 * Create the frame.
	 */
	public AdviceWindow(Cost costs, Model model, String businessAdvice, String sheepAdvice, String landAdvice, String shedAdvice, String materialsAdvice, String careAdvice) {
		setTitle("Advice");
		setIconImage(Toolkit.getDefaultToolkit().getImage(AdviceWindow.class.getResource("/com/resources/icon_sheep.png")));
		
		Border loweredbevel = BorderFactory.createLoweredBevelBorder();
		Border raisedbevel = BorderFactory.createRaisedBevelBorder();
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1137, 998);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(149, 172, 191));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.X_AXIS));
		
		JLayeredPane layeredPane = new JLayeredPane();
		contentPane.add(layeredPane);
		
		// Centre the window
//		this.setLocationRelativeTo(null);
		FrameLocationSetter.setLocationToLeft(this);
		FrameLocationSetter.setLocationToTop(this);
		
		createCostTable(costs, model);
		
		this.lblBusiness = new JLabel("Business");
		lblBusiness.setHorizontalAlignment(SwingConstants.CENTER);
		lblBusiness.setVerticalAlignment(SwingConstants.TOP);
		lblBusiness.setFont(new Font("Arial Black", Font.BOLD, 16));
		lblBusiness.setBounds(130, 19, 110, 30);
		lblBusiness.setBorder(raisedbevel);
		lblBusiness.setOpaque(true);
		lblBusiness.setBackground(new Color(217, 217, 217));
		layeredPane.add(lblBusiness);
		
		this.lblSheep = new JLabel("Sheep");
		lblSheep.setHorizontalAlignment(SwingConstants.CENTER);
		lblSheep.setVerticalAlignment(SwingConstants.TOP);
		lblSheep.setFont(new Font("Arial Black", Font.BOLD, 16));
		lblSheep.setBounds(130, 19, 110, 30);
		lblSheep.setBorder(raisedbevel);
		lblSheep.setOpaque(true);
		lblSheep.setBackground(new Color(217, 217, 217));
		layeredPane.add(lblSheep);
		
		this.lblLand = new JLabel("Land");
		lblLand.setHorizontalAlignment(SwingConstants.CENTER);
		lblLand.setVerticalAlignment(SwingConstants.TOP);
		lblLand.setFont(new Font("Arial Black", Font.BOLD, 16));
		lblLand.setBounds(130, 19, 110, 30);
		lblLand.setBorder(raisedbevel);
		lblLand.setOpaque(true);
		lblLand.setBackground(new Color(217, 217, 217));
		layeredPane.add(lblLand);
		
		this.lblShed = new JLabel("Shed");
		lblShed.setHorizontalAlignment(SwingConstants.CENTER);
		lblShed.setVerticalAlignment(SwingConstants.TOP);
		lblShed.setFont(new Font("Arial Black", Font.BOLD, 16));
		lblShed.setBounds(130, 496, 110, 30);
		lblShed.setBorder(raisedbevel);
		lblShed.setOpaque(true);
		lblShed.setBackground(new Color(217, 217, 217));
		layeredPane.add(lblShed);
		
		this.lblMaterials = new JLabel("Materials");
		lblMaterials.setHorizontalAlignment(SwingConstants.CENTER);
		lblMaterials.setVerticalAlignment(SwingConstants.TOP);
		lblMaterials.setFont(new Font("Arial Black", Font.BOLD, 16));
		lblMaterials.setBounds(130, 496, 110, 30);
		lblMaterials.setBorder(raisedbevel);
		lblMaterials.setOpaque(true);
		lblMaterials.setBackground(new Color(217, 217, 217));
		layeredPane.add(lblMaterials);
		
		this.lblCare = new JLabel("Care");
		lblCare.setHorizontalAlignment(SwingConstants.CENTER);
		lblCare.setVerticalAlignment(SwingConstants.TOP);
		lblCare.setFont(new Font("Arial Black", Font.BOLD, 16));
		lblCare.setBounds(130, 496, 110, 30);
		lblCare.setBorder(raisedbevel);
		lblCare.setOpaque(true);
		lblCare.setBackground(new Color(217, 217, 217));
		layeredPane.add(lblCare);
		
		this.lblBusinessAdvice = new JLabel("Business advice");
		lblBusinessAdvice.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblBusinessAdvice.setVerticalAlignment(SwingConstants.TOP);
		layeredPane.setLayer(lblBusinessAdvice, 0);
		lblBusinessAdvice.setBounds(247, 13, 718, 438);
		lblBusinessAdvice.setOpaque(true);
		lblBusinessAdvice.setBackground(Color.WHITE);
		lblBusinessAdvice.setText(businessAdvice);

		Border margin = new EmptyBorder(10,10,10,10);
		lblBusinessAdvice.setBorder(new CompoundBorder(loweredbevel, margin));
		layeredPane.add(lblBusinessAdvice);
		
		this.lblSheepAdvice = new JLabel("Sheep advice");
		lblSheepAdvice.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblSheepAdvice.setVerticalAlignment(SwingConstants.TOP);
		lblSheepAdvice.setBounds(247, 13, 718, 438);
		lblSheepAdvice.setOpaque(true);
		lblSheepAdvice.setBackground(Color.WHITE);
		lblSheepAdvice.setText(sheepAdvice);
		
		lblSheepAdvice.setBorder(new CompoundBorder(loweredbevel, margin));
		layeredPane.add(lblSheepAdvice);
		/* Roughly 130 characters fit on a line and 7 lines fit in the advice label */
		
		this.lblLandAdvice = new JLabel("Land advice");
		lblLandAdvice.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblLandAdvice.setVerticalAlignment(SwingConstants.TOP);
		lblLandAdvice.setText("<html> hhhhhhhhhhhhhhhhhhhhh<br> h<br> h<br> h<br> h<br> h <html>");
		lblLandAdvice.setOpaque(true);
		lblLandAdvice.setBackground(Color.WHITE);
		lblLandAdvice.setText(landAdvice);
		lblLandAdvice.setBounds(247, 13, 718, 438);

		lblLandAdvice.setBorder(new CompoundBorder(loweredbevel, margin));
		layeredPane.add(lblLandAdvice);
		
		this.lblShedAdvice = new JLabel("Shed advice");
		lblShedAdvice.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblShedAdvice.setVerticalAlignment(SwingConstants.TOP);
		lblShedAdvice.setBounds(247, 490, 718, 438);
		lblShedAdvice.setOpaque(true);
		lblShedAdvice.setBackground(Color.WHITE);
		lblShedAdvice.setText(shedAdvice);

		lblShedAdvice.setBorder(new CompoundBorder(loweredbevel, margin));
		layeredPane.add(lblShedAdvice);
		
		this.lblMaterialsAdvice = new JLabel("Materials advice");
		lblMaterialsAdvice.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblMaterialsAdvice.setVerticalAlignment(SwingConstants.TOP);
		lblMaterialsAdvice.setBounds(247, 488, 718, 440);
		lblMaterialsAdvice.setOpaque(true);
		lblMaterialsAdvice.setBackground(Color.WHITE);
		lblMaterialsAdvice.setText(materialsAdvice);

		lblMaterialsAdvice.setBorder(new CompoundBorder(loweredbevel, margin));
		layeredPane.add(lblMaterialsAdvice);
		
		this.lblCareAdvice = new JLabel("Care advice");
		lblCareAdvice.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblCareAdvice.setVerticalAlignment(SwingConstants.TOP);
		lblCareAdvice.setBounds(247, 488, 718, 440);
		lblCareAdvice.setOpaque(true);
		lblCareAdvice.setBackground(Color.WHITE);
		lblCareAdvice.setText(careAdvice);

		lblCareAdvice.setBorder(new CompoundBorder(loweredbevel, margin));
		layeredPane.add(lblCareAdvice);
		
		this.btnPrev = new JButton("Previous");
		btnPrev.setBounds(12, 452, 100, 100);
		layeredPane.add(btnPrev);
		
		this.btnNext = new JButton("Next");
		btnNext.setBounds(997, 452, 100, 100);
		layeredPane.add(btnNext);
		
		this.lblShedimg = new JLabel("shedImg");
		lblShedimg.setIcon(new ImageIcon(AdviceWindow.class.getResource("/com/resources/shedImg.png")));
		lblShedimg.setBounds(140, 539, 100, 100);
		layeredPane.add(lblShedimg);
		
		this.lblSheepimg = new JLabel("sheepImg");
		lblSheepimg.setIcon(new ImageIcon(AdviceWindow.class.getResource("/com/resources/sheepImg.png")));
		lblSheepimg.setBounds(140, 62, 100, 100);
		layeredPane.add(lblSheepimg);
		
		this.lblBusinessimg = new JLabel("businessImg");
		lblBusinessimg.setIcon(new ImageIcon(AdviceWindow.class.getResource("/com/resources/businessImg.png")));
		lblBusinessimg.setBounds(140, 62, 100, 100);
		layeredPane.add(lblBusinessimg);
		
		this.lblCareimg = new JLabel("careImg");
		lblCareimg.setIcon(new ImageIcon(AdviceWindow.class.getResource("/com/resources/careImg.png")));
		lblCareimg.setBounds(140, 539, 100, 100);
		layeredPane.add(lblCareimg);
		
		this.lblMaterialsimg = new JLabel("materialsImg");
		lblMaterialsimg.setIcon(new ImageIcon(AdviceWindow.class.getResource("/com/resources/materialsImg.png")));
		lblMaterialsimg.setBounds(140, 539, 100, 100);
		layeredPane.add(lblMaterialsimg);
		
		this.lblLandimg = new JLabel("landImg");
		lblLandimg.setIcon(new ImageIcon(AdviceWindow.class.getResource("/com/resources/landImg.png")));
		lblLandimg.setBounds(140, 62, 100, 100);
		layeredPane.add(lblLandimg);
		
		JLabel lblBack2 = new JLabel("back2");
		lblBack2.setBounds(124, 490, 841, 438);
		lblBack2.setOpaque(true);
		lblBack2.setBackground(Color.LIGHT_GRAY);
		layeredPane.add(lblBack2);
		
		JLabel lblBack1 = new JLabel("back1");
		lblBack1.setOpaque(true);
		lblBack1.setBackground(Color.LIGHT_GRAY);
		lblBack1.setBounds(124, 13, 841, 438);
		layeredPane.add(lblBack1);
		
		setPage(0);
		createEvents();
		
	}
	
	public void setPage(int pageNumber) {
		this.pageNumber = pageNumber;
		
		/* PAGE NUMBER: 0		1				2
		 * upper holds: land, 	materials 	and business
		 * lower holds: shed, 	sheep 		and care */
		if(pageNumber == 0) {
			lblShed.setVisible(true);
			lblShedAdvice.setVisible(true);
			lblShedimg.setVisible(true);
			lblLand.setVisible(true);
			lblLandAdvice.setVisible(true);
			lblLandimg.setVisible(true);
			
			btnNext.setEnabled(true);
			btnPrev.setEnabled(false);
			
			lblSheep.setVisible(false);
			lblSheepAdvice.setVisible(false);
			lblSheepimg.setVisible(false);
			lblMaterials.setVisible(false);
			lblMaterialsAdvice.setVisible(false);
			lblMaterialsimg.setVisible(false);
			
			lblBusiness.setVisible(false);
			lblBusinessAdvice.setVisible(false);
			lblBusinessimg.setVisible(false);
			lblCare.setVisible(false);
			lblCareAdvice.setVisible(false);
			lblCareimg.setVisible(false);	

		}
		
		if(pageNumber == 1) { 
			lblShed.setVisible(false);
			lblShedAdvice.setVisible(false);
			lblShedimg.setVisible(false);
			lblLand.setVisible(false);
			lblLandAdvice.setVisible(false);
			lblLandimg.setVisible(false);
			
			btnNext.setEnabled(true);
			btnPrev.setEnabled(true);
			
			lblSheep.setVisible(true);
			lblSheepAdvice.setVisible(true);
			lblSheepimg.setVisible(true);
			lblMaterials.setVisible(true);
			lblMaterialsAdvice.setVisible(true);
			lblMaterialsimg.setVisible(true);
			
			lblBusiness.setVisible(false);
			lblBusinessAdvice.setVisible(false);
			lblBusinessimg.setVisible(false);
			lblCare.setVisible(false);
			lblCareAdvice.setVisible(false);
			lblCareimg.setVisible(false);	
		}
		
		if(pageNumber == 2) { 
			lblShed.setVisible(false);
			lblShedAdvice.setVisible(false);
			lblShedimg.setVisible(false);
			lblLand.setVisible(false);
			lblLandAdvice.setVisible(false);
			lblLandimg.setVisible(false);
			
			btnNext.setEnabled(false);
			btnPrev.setEnabled(true);
			
			lblSheep.setVisible(false);
			lblSheepAdvice.setVisible(false);
			lblSheepimg.setVisible(false);
			lblMaterials.setVisible(false);
			lblMaterialsAdvice.setVisible(false);
			lblMaterialsimg.setVisible(false);
			
			lblBusiness.setVisible(true);
			lblBusinessAdvice.setVisible(true);
			lblBusinessimg.setVisible(true);
			lblCare.setVisible(true);
			lblCareAdvice.setVisible(true);
			lblCareimg.setVisible(true);	
		}
		
		
	}
	
	public int getPageNumber() {
		return pageNumber;
	}
	
	private void createEvents() {
		new AdvPreviousAction(btnPrev, this);
		new AdvNextAction(btnNext, this);
	}
	
	private void createCostTable(final Cost costs, final Model model) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					new CostTable(costs, model);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}
