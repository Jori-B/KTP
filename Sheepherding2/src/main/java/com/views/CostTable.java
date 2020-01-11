package com.views;

import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JLabel;

import java.awt.SystemColor;

import javax.swing.JTable;
import javax.swing.SwingConstants;

import com.model.Cost;
import com.model.Materials;
import com.model.Model;
import com.model.VariableDefinitions;

import javax.swing.JScrollPane;

public class CostTable implements VariableDefinitions {

	private JFrame frame;
	private JTable table;

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
		
		JLabel lblCosts = new JLabel("Cost table");
		lblCosts.setHorizontalAlignment(SwingConstants.LEFT);
		lblCosts.setVerticalAlignment(SwingConstants.TOP);
		lblCosts.setFont(new Font("Arial Black", Font.BOLD, 16));
		lblCosts.setBounds(10, 13, 965, 30);
		frame.getContentPane().add(lblCosts);
		Materials materials = model.getMaterials();
		int getTractor = 0;
		if(materials.getNeedsNewTractor()) {
			getTractor = 1;
		}
		int getMower = 1 - materials.getHasMower();
		int getShaker = 1 - materials.getHasShaker();
		int getRaker = 1 - materials.getHasRaker();
		int getFertilizerSpreader = 1 - materials.getHasFertilizerSpreader();
		int getShaver = 1 - materials.getHasShaver();
		double shaveCost = 0;
		if(model.getCare().getWantsSelfShave() == NO) {
			shaveCost = costs.getShaveOtherCost();
		}
		int getFertilizerPlate = 1 - model.getShed().getHasFertilizerPlate();

		double shedDiff = model.getShed().getGoalCurSizeDiff();
		if(shedDiff <= 0) {
			shedDiff = 0;
		}
		
		int totalNSheepWanted = model.getSheep().getTotalNSheepWanted();
		int desiresNMoreSheep = model.getSheep().getDesiresNMoreSheep();
		
		int shaveCount = 0;
		if(costs.shaveOtherCost != 0) {
			shaveCount = totalNSheepWanted;
		} 
		
		double landNeeded = model.getLand().getLandNeeded();
		if(landNeeded < 0) {
			landNeeded = 0;
		}

		
		int slaughterSheep = 0;
		if(model.getCare().getWantsSlaughter()) {
			slaughterSheep = totalNSheepWanted;
		}
		
		String[] colNames = {"Item",		"Count", 	"Price"};
		Object[][] costsArray = {
						{"------------------ COSTS --------------------","-------------------------------------------------","-------------------------------------------------"},
						{"Tractor", 					getTractor+" tractor",					"€"+twoDigits.format(costs.getTractorCost())},
						{"Mower", 						getMower+" mower",						"€"+twoDigits.format(costs.getMowerCost())},
						{"Shaker",	 					getShaker+" shaker",					"€"+twoDigits.format(costs.getShakerCost())},
						{"Raker",	 					getRaker+" raker",						"€"+twoDigits.format(costs.getRakerCost())},
						{"Fertilizer spreader",			getFertilizerSpreader+" spreader",		"€"+twoDigits.format(costs.getFertilizerSpreaderCost())},
						{"Land needed", 				twoDigits.format(landNeeded)+" hectares leased",	"€"+twoDigits.format(costs.getLandNeededCost())},
						{"Shave other", 				shaveCount+" sheep",					"€"+twoDigits.format(shaveCost)},
						{"Shaving machine",				getShaver+" shaving machine",			"€"+twoDigits.format(costs.getShaverCost())},
						{"Myas Treatment", 				(totalNSheepWanted*3)+" treatments",	"€"+twoDigits.format(costs.getMyasTreatmentCost())},
						{"Worming",		 				totalNSheepWanted+" treatments",		"€"+twoDigits.format(costs.getWormCost())},
						{"Ear marks",	 				desiresNMoreSheep+" ear marks",			"€"+twoDigits.format(costs.getEarMarkCost())},
						{"RVO administration",			desiresNMoreSheep+" sheep",				"€"+twoDigits.format(costs.getRVOAdminCost())},
						{"Slaughter registration",		slaughterSheep+" sheep",				"€"+twoDigits.format(costs.getSlaughterCost())},
						{"Buying sheep",				desiresNMoreSheep+" sheep",				"€"+twoDigits.format(costs.getSheepBoughtCost())},
						{"Shed building",				twoDigits.format(shedDiff)+" square meters",		"€"+twoDigits.format(costs.getShedCost())},
						{"Movable fences",				twoDigits.format(costs.getLengthAdj())+" meters",	"€"+twoDigits.format(costs.getAdjFenceCost())},
						{"Eating fences",				twoDigits.format(costs.getLengthEat())+" meters",	"€"+twoDigits.format(costs.getEatFenceCost())},
						{"Mest plate",					getFertilizerPlate+" plate",			"€"+twoDigits.format(costs.getFertilizerPlateCost())},
						{"--------------- EARNINGS -------------------","-------------------------------------------------","-------------------------------------------------"},
						{"Sheep sold",					(totalNSheepWanted*2)+" lambs",			"€"+twoDigits.format(costs.getSheepSoldEarnings())},
						{"Wool Sold",					totalNSheepWanted+" coats of fur",		"€"+twoDigits.format(costs.getWoolEarnings())},
						{"Toeslagrechten", 				model.getLand().getLandSizeToeslag()+" hectares",	"€"+twoDigits.format(costs.getToeslagrechtEarnings())},
						{"---------------- TOTALS ---------------------","-------------------------------------------------","-------------------------------------------------"},
						{"Total costs",					"",										"€"+twoDigits.format(costs.getTotalCost())},
						{"Total earnings this year",	"",										"€"+twoDigits.format(costs.getTotalEarnings())},
						{"Costs minus earnings",		"",										"€"+twoDigits.format(costs.getMoneyNeeded())},
						{"----------- YOUR BUSINESS ---------------","-------------------------------------------------","-------------------------------------------------"},
						{"Money to spend",				"",										"€"+twoDigits.format(costs.getMoneyToSpend())},
						{"Your spending minus total needed",	"",								"€"+twoDigits.format((costs.getMoneyToSpend() - costs.getMoneyNeeded()))},
		};
		

		table = new JTable(costsArray, colNames);
		table.setFont(new Font("Tahoma", Font.PLAIN, 15));
//		table.setBounds(12, 24, 816, 293);
		
		frame.getContentPane().add(table);
		
		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setBounds(10, 56, 772, 510);
		frame.getContentPane().add(scrollPane);
//		frame.getContentPane().add(table.getTableHeader(), BorderLayout.PAGE_START);
		frame.setBounds(100, 100, 814, 626);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}
