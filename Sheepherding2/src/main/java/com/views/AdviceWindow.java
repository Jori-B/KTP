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

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import java.awt.Font;

import javax.swing.SwingConstants;
import java.awt.SystemColor;
import java.awt.Toolkit;

public class AdviceWindow extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	
	/**
	 * Create the frame.
	 */
	public AdviceWindow(Cost costs, Model model, String businessAdvice, String sheepAdvice, String landAdvice, String shedAdvice, String materialsAdvice, String careAdvice) {
		setTitle("Advice");
		setIconImage(Toolkit.getDefaultToolkit().getImage(AdviceWindow.class.getResource("/com/resources/icon_sheep.png")));
		
		Border loweredbevel = BorderFactory.createLoweredBevelBorder();
		Border raisedbevel = BorderFactory.createRaisedBevelBorder();
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1113, 998);
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
		
		JLabel lblBusiness = new JLabel("Business");
		lblBusiness.setHorizontalAlignment(SwingConstants.CENTER);
		lblBusiness.setVerticalAlignment(SwingConstants.TOP);
		lblBusiness.setFont(new Font("Arial Black", Font.BOLD, 16));
		lblBusiness.setBounds(1, 6, 110, 30);
		lblBusiness.setBorder(raisedbevel);
		lblBusiness.setOpaque(true);
		lblBusiness.setBackground(new Color(217, 217, 217));
		layeredPane.add(lblBusiness);
		
		JLabel lblSheep = new JLabel("Sheep");
		lblSheep.setHorizontalAlignment(SwingConstants.CENTER);
		lblSheep.setVerticalAlignment(SwingConstants.TOP);
		lblSheep.setFont(new Font("Arial Black", Font.BOLD, 16));
		lblSheep.setBounds(1, 160, 110, 30);
		lblSheep.setBorder(raisedbevel);
		lblSheep.setOpaque(true);
		lblSheep.setBackground(new Color(217, 217, 217));
		layeredPane.add(lblSheep);
		
		JLabel lblLand = new JLabel("Land");
		lblLand.setHorizontalAlignment(SwingConstants.CENTER);
		lblLand.setVerticalAlignment(SwingConstants.TOP);
		lblLand.setFont(new Font("Arial Black", Font.BOLD, 16));
		lblLand.setBounds(1, 322, 110, 30);
		lblLand.setBorder(raisedbevel);
		lblLand.setOpaque(true);
		lblLand.setBackground(new Color(217, 217, 217));
		layeredPane.add(lblLand);
		
		JLabel lblShed = new JLabel("Shed");
		lblShed.setHorizontalAlignment(SwingConstants.CENTER);
		lblShed.setVerticalAlignment(SwingConstants.TOP);
		lblShed.setFont(new Font("Arial Black", Font.BOLD, 16));
		lblShed.setBounds(1, 481, 110, 30);
		lblShed.setBorder(raisedbevel);
		lblShed.setOpaque(true);
		lblShed.setBackground(new Color(217, 217, 217));
		layeredPane.add(lblShed);
		
		JLabel lblMaterials = new JLabel("Materials");
		lblMaterials.setHorizontalAlignment(SwingConstants.CENTER);
		lblMaterials.setVerticalAlignment(SwingConstants.TOP);
		lblMaterials.setFont(new Font("Arial Black", Font.BOLD, 16));
		lblMaterials.setBounds(1, 634, 110, 30);
		lblMaterials.setBorder(raisedbevel);
		lblMaterials.setOpaque(true);
		lblMaterials.setBackground(new Color(217, 217, 217));
		layeredPane.add(lblMaterials);
		
		JLabel lblCare = new JLabel("Care");
		lblCare.setHorizontalAlignment(SwingConstants.CENTER);
		lblCare.setVerticalAlignment(SwingConstants.TOP);
		lblCare.setFont(new Font("Arial Black", Font.BOLD, 16));
		lblCare.setBounds(1, 787, 110, 30);
		lblCare.setBorder(raisedbevel);
		lblCare.setOpaque(true);
		lblCare.setBackground(new Color(217, 217, 217));
		layeredPane.add(lblCare);
		
		JLabel lblBusinessAdvice = new JLabel("Business advice");
		lblBusinessAdvice.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblBusinessAdvice.setVerticalAlignment(SwingConstants.TOP);
		layeredPane.setLayer(lblBusinessAdvice, 0);
		lblBusinessAdvice.setBounds(116, 0, 965, 145);
		lblBusinessAdvice.setOpaque(true);
		lblBusinessAdvice.setBackground(Color.WHITE);
		lblBusinessAdvice.setText(businessAdvice);

		Border margin = new EmptyBorder(10,10,10,10);
		lblBusinessAdvice.setBorder(new CompoundBorder(loweredbevel, margin));
		layeredPane.add(lblBusinessAdvice);
		
//		String text = "<html>h";
//		text = text + "<br>hi";
//		text = text + "<html>";
		
		JLabel lblSheepAdvice = new JLabel("Sheep advice");
		lblSheepAdvice.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblSheepAdvice.setVerticalAlignment(SwingConstants.TOP);
		lblSheepAdvice.setBounds(116, 154, 965, 151);
		lblSheepAdvice.setOpaque(true);
		lblSheepAdvice.setBackground(Color.WHITE);
		lblSheepAdvice.setText(sheepAdvice);
		
		lblSheepAdvice.setBorder(new CompoundBorder(loweredbevel, margin));
		layeredPane.add(lblSheepAdvice);
		/* Roughly 130 characters fit on a line and 7 lines fit in the advice label */
		
		JLabel lblLandAdvice = new JLabel("Land advice");
		lblLandAdvice.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblLandAdvice.setVerticalAlignment(SwingConstants.TOP);
		lblLandAdvice.setText("<html> hhhhhhhhhhhhhhhhhhhhh<br> h<br> h<br> h<br> h<br> h <html>");
		lblLandAdvice.setOpaque(true);
		lblLandAdvice.setBackground(Color.WHITE);
		lblLandAdvice.setText(landAdvice);
		lblLandAdvice.setBounds(116, 316, 965, 151);

		lblLandAdvice.setBorder(new CompoundBorder(loweredbevel, margin));
		layeredPane.add(lblLandAdvice);
		
		JLabel lblShedAdvice = new JLabel("Shed advice");
		lblShedAdvice.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblShedAdvice.setVerticalAlignment(SwingConstants.TOP);
		lblShedAdvice.setBounds(116, 475, 965, 145);
		lblShedAdvice.setOpaque(true);
		lblShedAdvice.setBackground(Color.WHITE);
		lblShedAdvice.setText(shedAdvice);

		lblShedAdvice.setBorder(new CompoundBorder(loweredbevel, margin));
		layeredPane.add(lblShedAdvice);
		
		JLabel lblMaterialsAdvice = new JLabel("Materials advice");
		lblMaterialsAdvice.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblMaterialsAdvice.setVerticalAlignment(SwingConstants.TOP);
		lblMaterialsAdvice.setBounds(116, 628, 965, 151);
		lblMaterialsAdvice.setOpaque(true);
		lblMaterialsAdvice.setBackground(Color.WHITE);
		lblMaterialsAdvice.setText(materialsAdvice);

		lblMaterialsAdvice.setBorder(new CompoundBorder(loweredbevel, margin));
		layeredPane.add(lblMaterialsAdvice);
		
		JLabel lblCareAdvice = new JLabel("Care advice");
		lblCareAdvice.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblCareAdvice.setVerticalAlignment(SwingConstants.TOP);
		lblCareAdvice.setBounds(116, 787, 965, 154);
		lblCareAdvice.setOpaque(true);
		lblCareAdvice.setBackground(Color.WHITE);
		lblCareAdvice.setText(careAdvice);

		lblCareAdvice.setBorder(new CompoundBorder(loweredbevel, margin));
		layeredPane.add(lblCareAdvice);
		
	}
	
//    static Rectangle getMaxWindowBounds(JFrame frame) {
//        GraphicsConfiguration config = frame.getGraphicsConfiguration();
//        Rectangle bounds = config.getBounds();
//        Insets insets = Toolkit.getDefaultToolkit().getScreenInsets(config);
//        bounds.x += insets.left;
//        bounds.y += insets.top;
//        bounds.width -= insets.left + insets.right;
//        bounds.height -= insets.top + insets.bottom;
//        return bounds;
//    }
//    
//    static void setLocationToTop(JFrame frame) {
//        frame.setLocation(frame.getX(), getMaxWindowBounds(frame).y);
//    }
//    
//    static void setLocationToRight(JFrame frame) {
//        Rectangle bounds = getMaxWindowBounds(frame);
//        frame.setLocation(bounds.x + bounds.width - frame.getWidth(), frame.getY());
//    }
//	
//    static void setLocationToLeft(JFrame frame) {
//        frame.setLocation(getMaxWindowBounds(frame).x, frame.getY());
//    }
	
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
