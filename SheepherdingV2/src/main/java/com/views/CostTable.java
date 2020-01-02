package com.views;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JLabel;

import java.awt.SystemColor;
import javax.swing.JTable;
import javax.swing.SwingConstants;

import com.model.Cost;
import com.model.Sheep;

import javax.swing.JScrollBar;
import javax.swing.JScrollPane;

public class CostTable {

	private JFrame frame;
	private JTable table;

	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					CostTable window = new CostTable();
//					window.frame.setVisible(true);
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		});
//	}

	/**
	 * Create the application.
	 */
	public CostTable(Cost costs) {
		initialize(costs);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize(Cost costs) {
		frame = new JFrame();
		frame.setVisible(true);
		frame.getContentPane().setBackground(SystemColor.activeCaption);
		frame.getContentPane().setLayout(null);
		
//		JLabel lblCosts = new JLabel("Cost table");
//		lblCosts.setHorizontalAlignment(SwingConstants.LEFT);
//		lblCosts.setVerticalAlignment(SwingConstants.TOP);
//		lblCosts.setFont(new Font("Arial Black", Font.BOLD, 16));
//		lblCosts.setBounds(12, 0, 965, 30);
//		frame.getContentPane().add(lblCosts);
		
		
		String[] colNames = {"Item",		"Count", 	"Price"};
		Object[][] costsArray = {
						{"---------------------------------------------------------------","COSTS","---------------------------------------------------------------"},
						{"Tractor", 									1,		costs.getTractorCost()},
						{"Mower", 										1,		costs.getMowerCost()},
						{"Shaker",	 									1,		costs.getShakerCost()},
						{"Raker",	 									1,		costs.getRakerCost()},
						{"Toeslagrechten", 								1,		costs.getToeslagrechtCost()},
						{"Land needed", 								1,		costs.getLandNeededCost()},
						{"Shave other", 								1,		costs.getShaveOtherCost()},
						{"Myas Treatment", 								1,		costs.getMyasTreatmentCost()},
						{"Worming",		 								1,		costs.getWormCost()},
						{"Ear marks",	 								1,		costs.getEarMarkCost()},
						{"RVO administration",							1,		costs.getRVOAdminCost()},
						{"Slaughter registration",						1,		costs.getSlaughterCost()},
						{"Buying sheep",								1,		costs.getSheepBoughtCost()},
						{"Phosphate rights",							1,		costs.getPhosphateRightsCost()},
						{"Shed building",								1,		costs.getShedCost()},
						{"---------------------------------------------------------------","EARNINGS","---------------------------------------------------------------"},
						{"Sheep sold",									1,		costs.getSheepSoldEarnings()},
						{"Wool Sold",									1,		costs.getWoolEarnings()},
						{"TOTALS",										"",		""},
						{"Total costs",									"",		costs.getTotalCost()},
						{"Total earnings this year",					"",		costs.getTotalEarnings()},
						{"Costs minus earnings",						"",		costs.getMoneyNeeded()},
						{"---------------------------------------------------------------","YOUR BUSINESS","---------------------------------------------------------------"},
						{"Money to spend",								"",		costs.getMoneyToSpend()},
						{"Your spending minus total needed",			"",		costs.getMoneyToSpend() - costs.getMoneyNeeded()},
		};
		
		table = new JTable(costsArray, colNames);
//		table.setBounds(12, 24, 816, 293);
		

//		mowerCost;
//		shakerCost;
//		rakerCost;
//		toeslagrechtCost;
//		landNeededCost; 

		
		frame.getContentPane().add(table);
		
		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setBounds(10, 56, 772, 447);
		frame.getContentPane().add(scrollPane);
//		frame.getContentPane().add(table.getTableHeader(), BorderLayout.PAGE_START);
		frame.setBounds(100, 100, 814, 563);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}
