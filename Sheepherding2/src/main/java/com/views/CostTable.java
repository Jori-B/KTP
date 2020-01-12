package com.views;

import java.awt.Color;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;

import java.awt.SystemColor;

import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.border.Border;

import org.apache.poi.hssf.util.HSSFColor.GREEN;

import com.model.Cost;
import com.model.Materials;
import com.model.Model;
import com.model.VariableDefinitions;

import com.views.FrameLocationSetter;

import javax.swing.JScrollPane;
import java.awt.Toolkit;

public class CostTable implements VariableDefinitions {

	private JFrame frmCosts;
	private JTable table;

	/**
	 * Create the application.
	 */
	public CostTable(Cost costs, Model model) {
		initialize(costs, model);
	}

	private int booleanToInt(boolean answer) {
		if(answer == true) {
			return 1;
		} else {
			return 0;
		}
		
	}
	
	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize(Cost costs, Model model) {
		frmCosts = new JFrame();
		frmCosts.setIconImage(Toolkit.getDefaultToolkit().getImage(CostTable.class.getResource("/com/resources/icon_sheep.png")));
		frmCosts.setTitle("Costs");
		frmCosts.setVisible(true);
		frmCosts.getContentPane().setBackground(new Color(168, 191, 174));
		frmCosts.getContentPane().setLayout(null);
		
		JLabel lblCosts = new JLabel("Cost table");
		lblCosts.setHorizontalAlignment(SwingConstants.CENTER);
		lblCosts.setVerticalAlignment(SwingConstants.TOP);
		lblCosts.setFont(new Font("Arial Black", Font.BOLD, 16));
		lblCosts.setBounds(26, 13, 114, 30);
		lblCosts.setBorder(raisedbevel);
		lblCosts.setOpaque(true);
		lblCosts.setBackground(new Color(168, 191, 174));
		frmCosts.getContentPane().add(lblCosts);
		
		Materials materials = model.getMaterials();
		
		int getMower = 1 - booleanToInt(materials.getHasMower());
		int getShaker = 1 - booleanToInt(materials.getHasShaker());
		int getRaker = 1 - booleanToInt(materials.getHasRaker());
		int getFertilizerSpreader = 1 - booleanToInt(materials.getHasFertilizerSpreader());
		int getShaver = 1 - booleanToInt(materials.getHasShaver());
		int getFertilizerPlate = 1 - booleanToInt(model.getShed().getHasFertilizerPlate());
		
		int getTractor = 0;
		if(materials.getNeedsNewTractor()) {
			getTractor = 1;
		}
		
		double shaveCost = 0;
		if(!model.getCare().getWantsSelfShave()) {
			shaveCost = costs.getShaveOtherCost();
		}

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
		
		/* Make money needed red or green, same for moneyDiff */
		double moneyNeeded = costs.getMoneyNeeded();
		String colMoneyNeeded;
		if(moneyNeeded < 0) {
			colMoneyNeeded = "<html><p style=\"background:#8FBC8F;\">" + "€"+twoDigits.format(-1 * moneyNeeded) +  "</p><html>";
		} else {
			colMoneyNeeded = "<html><p style=\"background:#FFC0CB;\">" + "€"+twoDigits.format(-1 * moneyNeeded) +  "</p><html>";
		}
		
		double moneyDiff = costs.getMoneyToSpend() - moneyNeeded;
		String spendMinNeed;
		if(moneyDiff >= 0) {
			spendMinNeed = "<html><p style=\"background:#8FBC8F;\">" + "€"+twoDigits.format(moneyDiff) +  "</p><html>";
		} else {
			spendMinNeed = "<html><p style=\"background:#FFC0CB;\">" + "€"+twoDigits.format(moneyDiff) +  "</p><html>";
		}
		
		String[] colNames = {"Item",		"Count", 	"Price"};
		Object[][] costsArray = {
						{"<html><b>&emsp; COSTS <html>","",""},
						//{"------------------ COSTS --------------------","-------------------------------------------------","-------------------------------------------------"},
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
						{"<html><b>&emsp; EARNINGS <html>","",""},
						//{"--------------- EARNINGS -------------------","-------------------------------------------------","-------------------------------------------------"},
						{"Sheep sold",					(totalNSheepWanted*2)+" lambs",			"€"+twoDigits.format(costs.getSheepSoldEarnings())},
						{"Wool Sold",					totalNSheepWanted+" coats of fur",		"€"+twoDigits.format(costs.getWoolEarnings())},
						{"Toeslagrechten", 				model.getLand().getLandSizeToeslag()+" hectares",	"€"+twoDigits.format(costs.getToeslagrechtEarnings())},
						{"<html><b>&emsp; TOTALS <html>","",""},
						//{"---------------- TOTALS ---------------------","-------------------------------------------------","-------------------------------------------------"},
						{"Total costs",					"",										"€"+twoDigits.format(costs.getTotalCost())},
						{"Total earnings this year",	"",										"€"+twoDigits.format(costs.getTotalEarnings())},
						{"Projected earnings (Earnings minus cost)",	"",						colMoneyNeeded},
						{"<html><b>&emsp; YOUR BUSINESS <html> ","",""},
						//{"----------- YOUR BUSINESS ---------------","-------------------------------------------------","-------------------------------------------------"},
						{"Money to spend",				"",										"€"+twoDigits.format(costs.getMoneyToSpend())},
						{"Your spending minus projected earnings",		"",						spendMinNeed},
		};
		

		table = new JTable(costsArray, colNames);
		table.setFont(new Font("Tahoma", Font.PLAIN, 15));
		table.setBackground(new Color(242, 242, 242));
		
		frmCosts.getContentPane().add(table);
		
		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setBounds(10, 56, 772, 510);
		frmCosts.getContentPane().add(scrollPane);
		
		JLabel lblBackground = new JLabel("");
		lblBackground.setBounds(10, 0, 772, 56);
		lblBackground.setOpaque(true);
		lblBackground.setBackground(new Color(208, 217, 209));
		lblBackground.setBorder(raisedbevel);
		frmCosts.getContentPane().add(lblBackground);

		frmCosts.setBounds(100, 100, 814, 626);
		frmCosts.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE );
		
		FrameLocationSetter.setLocationToRight(frmCosts);
		FrameLocationSetter.setLocationToTop(frmCosts);
	}
}
