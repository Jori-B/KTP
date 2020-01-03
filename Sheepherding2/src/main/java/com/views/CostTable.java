package com.views;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JLabel;

import java.awt.SystemColor;
import java.text.DecimalFormat;

import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;

import com.model.Cost;
import com.model.Materials;
import com.model.Model;
import com.model.Sheep;
import com.model.VariableDefinitions;

import javax.swing.JScrollBar;
import javax.swing.JScrollPane;

public class CostTable implements VariableDefinitions {

	private JFrame frame;
	private JTable table;
	DecimalFormat twoDigits = new DecimalFormat("##.00");

	/**
	 * Create the application.
	 */
	public CostTable(Cost costs, Model model) {
		initialize(costs, model);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize(Cost costs, Model model) {
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
		Materials materials = model.getMaterials();
		int getTractor = 0;
		if(materials.getNeedsNewTractor()) {
			getTractor = 1;
		}
		int getMower = 1;
		int getShaker = 1;
		int getRaker = 1;
		if(materials.getHasMower() == YES) {
			getMower = 0;
		} 
		if(materials.getHasShaker() == YES) {
			getShaker = 0;
		} 
		if(materials.getHasRaker() == YES) {
			getRaker = 0;
		} 
		
		int getFertilizerPlate = 1;
		if(model.getShed().getHasFertilizerPlate() == YES) {
			getFertilizerPlate = 0;
		}
		
		double landNeeded = model.getLand().getLandNeeded();
		int totalNSheepWanted = model.getSheep().getTotalNSheepWanted();
		int desiresNMoreSheep = model.getSheep().getDesiresNMoreSheep();
		
		String[] colNames = {"Item",		"Count", 	"Price"};
		Object[][] costsArray = {
						{"--------------------------- COSTS -------------------------","---------------------------------------------------------------","---------------------------------------------------------------"},
						{"Trekker", 					getTractor+" tractor",					"€"+twoDigits.format(costs.getTractorCost())},
						{"Maaier", 						getMower+" maaier",						"€"+twoDigits.format(costs.getMowerCost())},
						{"Schudder",	 					getShaker+" schudder",					"€"+twoDigits.format(costs.getShakerCost())},
						{"Harker",	 					getRaker+" harker",						"€"+twoDigits.format(costs.getRakerCost())},
						{"Toeslagrechten", 				twoDigits.format(landNeeded)+" hectare",	"€"+twoDigits.format(costs.getToeslagrechtCost())},
						{"Land benodigt", 				twoDigits.format(landNeeded)+" hectare pachten",			"€"+twoDigits.format(costs.getLandNeededCost())},
						{"Scheren door ander", 				totalNSheepWanted+" schapen",				"€"+twoDigits.format(costs.getShaveOtherCost())},
						{"Myas behandelingen", 				(totalNSheepWanted*3)+" behandelingen",	"€"+twoDigits.format(costs.getMyasTreatmentCost())},
						{"Wormen",		 				desiresNMoreSheep+" behandelingen",		"€"+twoDigits.format(costs.getWormCost())},
						{"Oor merken",	 				desiresNMoreSheep+" oormerken",			"€"+twoDigits.format(costs.getEarMarkCost())},
						{"RVO administratie",			desiresNMoreSheep+" schapen",				"€"+twoDigits.format(costs.getRVOAdminCost())},
						{"Slacht registratie",		totalNSheepWanted+" schapen",				"€"+twoDigits.format(costs.getSlaughterCost())},
						{"Schapen kopen",				desiresNMoreSheep+" schapen",				"€"+twoDigits.format(costs.getSheepBoughtCost())},
						{"Fosfaatrechten",			totalNSheepWanted+" rights",			"€"+twoDigits.format(costs.getPhosphateRightsCost())},
						{"Schuur bouwen/uitbreiden",				model.getShed().getGoalCurSizeDiff()+" vierkante meters",	"€"+twoDigits.format(costs.getShedCost())},
						{"Schuur mestplaat",		getFertilizerPlate+" plaat",			"€"+twoDigits.format(costs.getFertilizerPlateCost())},
						{"------------------------ EARNINGS ------------------------","---------------------------------------------------------------","---------------------------------------------------------------"},
						{"Schapen verkocht",					(totalNSheepWanted*2)+" lammeren",			"€"+twoDigits.format(costs.getSheepSoldEarnings())},
						{"Wol verkocht",					totalNSheepWanted+" wolvachten",		"€"+twoDigits.format(costs.getWoolEarnings())},
						{"------------------------- TOTALS --------------------------","---------------------------------------------------------------","---------------------------------------------------------------"},
						{"Totale kosten",					"",										"€"+twoDigits.format(costs.getTotalCost())},
						{"Totale inkomsten dit jaar",	"",										"€"+twoDigits.format(costs.getTotalEarnings())},
						{"Kosten minus inkomsten",		"",										"€"+twoDigits.format(costs.getMoneyNeeded())},
						{"-------------------- YOUR BUSINESS ---------------------","---------------------------------------------------------------","---------------------------------------------------------------"},
						{"Geld te besteden door u",				"",										"€"+twoDigits.format(costs.getMoneyToSpend())},
						{"Te besteden minus kosten plus inkomsten",	"",								"€"+twoDigits.format((costs.getMoneyToSpend() - costs.getMoneyNeeded()))},
		};
		

		table = new JTable(costsArray, colNames);
//		table.setBounds(12, 24, 816, 293);
		
		frame.getContentPane().add(table);
		
		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setBounds(10, 56, 772, 466);
		frame.getContentPane().add(scrollPane);
//		frame.getContentPane().add(table.getTableHeader(), BorderLayout.PAGE_START);
		frame.setBounds(100, 100, 814, 582);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}
