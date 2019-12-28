package com.sample;

import com.sample.Land;	
import com.sample.Materials;
import com.sample.Sheep;
import com.sample.Business;
import com.sample.Shed;
import com.sample.Care;

import java.awt.EventQueue;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.UIManager;

import org.drools.KnowledgeBase;
import org.drools.KnowledgeBaseFactory;
import org.drools.builder.KnowledgeBuilder;
import org.drools.builder.KnowledgeBuilderError;
import org.drools.builder.KnowledgeBuilderErrors;
import org.drools.builder.KnowledgeBuilderFactory;
import org.drools.builder.ResourceType;
import org.drools.io.ResourceFactory;
import org.drools.runtime.StatefulKnowledgeSession;

import com.views.MainView;

public class Model implements VariableDefinitions {
   
    public boolean allQuestionsAsked;    
    public ArrayList<Fact> facts = new ArrayList<Fact>();
    public MainView frame;  
    public Fact currentQuestion;
    public Fact nextQuestion;
    public Fact prevQuestion;
    public StatefulKnowledgeSession ksession;
	public Land land = new Land();
	public Materials materials = new Materials();
	public Sheep sheep = new Sheep();
	public Business business = new Business();
	public Shed shed = new Shed();
	public Care care = new Care();
	
	HashMap<String, Integer> factListMap = new HashMap<String, Integer>();
	
	public ArrayList<String> itemsToRemove = new ArrayList<String>();
    
    public Model() {

    } 
    
    public void createKnowledgeBase(Model model) {
	    try {
			// Load up the knowledge base
			KnowledgeBase kbase = readKnowledgeBase();
			StatefulKnowledgeSession ksession = kbase.newStatefulKnowledgeSession();
			setKSession(ksession);
			createQuestions(ksession, model);
		} catch (Throwable t) {
			t.printStackTrace();
	  	}	
    }
    
    public void setKSession(StatefulKnowledgeSession ksession) {
    	this.ksession = ksession;
    }
    
    public void findPrevQuestion(Fact current) {
    	for (int i = (facts.indexOf(current) - 1); i >= 0; i--) {
    		Fact prevFact = facts.get(i);
    		if(prevFact.askNow == true) {
    			setPrevQuestion(prevFact);
    			return;
    		}
    	}
    }
    
    public void findNextQuestion(Fact previous) {
    	setPrevQuestion(previous);
    	for (int i = (facts.indexOf(previous) + 1); i < facts.size(); i++) {
    		Fact nextFact = facts.get(i);
    		if(nextFact.askNow == true) {
    			setCurrentQuestion(nextFact);
    			return;
    		}
    	}
    	/* If no fact is found then return the current fact and inform the model all questions are asked */
    	System.out.println("all asked");
    	setAllQuestionsAsked(true);
    }
    
	public Fact getSelectedQuestion(String name) {
	    int index = factListMap.get(name);
	    System.out.println(index);
	    return facts.get(index);
	}
	
	public void setAllAnswers() {
		business.setHobSemiPro(getSelectedQuestion("Hobby or Pro"));
		business.setIsKvKRegistered(getSelectedQuestion("isKVKRegistered"));
		business.setTimeWillingToSpend(getSelectedQuestion("timeWillingToSpend"));
		business.setMoneyToSpend(getSelectedQuestion("moneyToSpend"));
		business.setIsUBNRegistered(getSelectedQuestion("isUBNRegistered"));
		sheep.setHasSheep(getSelectedQuestion("hasSheep"));
		sheep.setOwnsNSheep(getSelectedQuestion("ownsNSheep"));
		sheep.setDesiresNMoreSheep(getSelectedQuestion("Number of Sheep"));
		land.setHasLand(getSelectedQuestion("Has Land"));
		land.setOwnedLandSize(getSelectedQuestion("Land Size"));
		land.setHasLeasedLand(getSelectedQuestion("hasLeasedLand"));
		land.setLeasedLandSize(getSelectedQuestion("leasedLandSize"));
		shed.setHasShed(getSelectedQuestion("Has Shed"));
		shed.setCurShedSize(getSelectedQuestion("Shed Size"));
		shed.setIsTallerThan3(getSelectedQuestion("heightShed"));
		shed.setIsPathWiderThan12(getSelectedQuestion("widthShed"));
		shed.setHasFertilizerPlate(getSelectedQuestion("hasFertilizer"));
		shed.setHasCementFloor(getSelectedQuestion("hasFlatFloor"));
		shed.setHasLamps(getSelectedQuestion("hasLamps"));
		shed.setIsAllowedToBuild(getSelectedQuestion("isAllowedToBuild"));
		shed.setRoomForShed(getSelectedQuestion("roomForShed"));
		materials.setHasTractor(getSelectedQuestion("Has Tractor"));
		materials.setHorsePowerTractor(getSelectedQuestion("horsePowerTractor"));
		materials.setHasShaker(getSelectedQuestion("hasShaker"));
		materials.setHasRaker(getSelectedQuestion("hasRaker"));
		materials.setHasMower(getSelectedQuestion("hasMower"));
		materials.setHasMestGatherer(getSelectedQuestion("hasMestGatherer"));
		materials.setHasHayPacker(getSelectedQuestion("hasHayPacker"));
		materials.setHasFertilizerSpreader(getSelectedQuestion("hasFertilizerSpreader"));
		care.setWantsSelfBirth(getSelectedQuestion("Self Birth"));
		care.setWantsSelfShave(getSelectedQuestion("wantsSelfShave"));
	}
    
    public void createQuestions(StatefulKnowledgeSession ksession, Model model) {
    	
//    	ksession.setGlobal("gvalues", new Values());
		
    	/*Business Questions*/
    	facts.add(new MCFact("Hobby or Pro", MC, ksession, "Do you want do farming as a (0) hobby or (1) professionally", ASK, model, "Hobby", "Professional"));
        factListMap.put("Hobby or Pro", 0);
        setCurrentQuestion(facts.get(0));
        	// if professional
        	facts.add(new Fact("isKVKRegistered", YESNO, ksession, "Are you registered at the Kamer van Koophandel?", model));
        	factListMap.put("isKVKRegistered", 1);
        facts.add(new Fact("timeWillingToSpend", NUMB, ksession, "<html> How many days are you willing to spend per week <br> on sheep herding? <html>", ASK, model));
        factListMap.put("timeWillingToSpend", 2);
        facts.add(new Fact("moneyToSpend", NUMB, ksession, "How much money do you have to spend on sheep herding?", ASK, model));
        factListMap.put("moneyToSpend", 3);
        facts.add(new Fact("isUBNRegistered", YESNO, ksession, "Does your farm already have a Unique Business Number (UBN)?", ASK, model));
        factListMap.put("isUBNRegistered", 4);
        
        /*Sheep questions*/
        facts.add(new Fact("hasSheep", YESNO, ksession, "Do you already own any sheep? No (0) Yes (1)", ASK, model));
        factListMap.put("hasSheep", 5);
    		// If yes
        	facts.add(new Fact("ownsNSheep", NUMB, ksession, "How many sheep do you own?", model));
        	factListMap.put("ownsNSheep", 6);
        facts.add(new Fact("Number of Sheep", NUMB, ksession, "How many sheep would you like to have in total?", ASK, model));
        factListMap.put("Number of Sheep", 7);
        
        /*Land questions*/
        facts.add(new Fact("Has Land", YESNO, ksession, "Do you have land (including the land you lease)? No (0) Yes (1)", ASK, model));
        factListMap.put("Has Land", 8);
        	// If yes
        	facts.add(new Fact("Land Size", NUMB, ksession, "How big is your land (in acres)?", model));
        	factListMap.put("Land Size", 9);
        facts.add(new Fact("hasLeasedLand", YESNO, ksession, "Are you leasing land? No (0) Yes (1)", ASK, model));  
        factListMap.put("hasLeasedLand", 10);
    		// If yes
        	facts.add(new Fact("leasedLandSize", NUMB, ksession, "How big is the land you lease (in acres)?", model));
        	factListMap.put("leasedLandSize", 11);
        
        /*Shed questions*/	
        facts.add(new Fact("Has Shed", YESNO, ksession, "Do you have a shed? No (0) Yes (1)", ASK, model));    
        factListMap.put("Has Shed", 12);
        	// If yes
        	facts.add(new Fact("Shed Size", NUMB, ksession, "How big is your shed (in meters squared)?", model));
        	factListMap.put("Shed Size", 13);
        	facts.add(new Fact("heightShed", NUMB, ksession, "How high is your shed (in meters)?", model));
        	factListMap.put("heightShed", 14);
        	facts.add(new Fact("widthShed", NUMB, ksession, "How wide is the walking space of shed (in meters)?", model));
        	factListMap.put("widthShed", 15);
        	facts.add(new Fact("hasFertilizer", YESNO, ksession, "Do you have a fertilizer plate in your shed?", model));
        	factListMap.put("hasFertilizer", 16);
    		facts.add(new Fact("hasFlatFloor", YESNO, ksession, "Does your have a flat floor?", model));
    		factListMap.put("hasFlatFloor", 17);
    		facts.add(new Fact("hasLamps", YESNO, ksession, "Does your shed have small lamps where the sheep should birth?", model));
    		factListMap.put("hasLamps", 18);
    		// If shedTooSmall
    		facts.add(new Fact("isAllowedToBuild", YESNO, ksession, "Are you allowed to build a shed or expand your shed somewhere?", model));
    		factListMap.put("isAllowedToBuild", 19);
    		facts.add(new Fact("roomForShed", NUMB, ksession, "How much room do you have to build a shed (in meters squared)?", model));
    		factListMap.put("roomForShed", 20);
        
        /*Materials questions*/
        facts.add(new Fact("Has Tractor", YESNO, ksession, "Do you have have a tractor? No (0) Yes (1)", ASK, model));
        factListMap.put("Has Tractor", 21);
    		// If yes
        	facts.add(new Fact("horsePowerTractor", NUMB, ksession, "How much horsepower does your tractor have", model));
        	factListMap.put("horsePowerTractor", 22);
    	facts.add(new Fact("hasMower", YESNO, ksession, "Do you have have a mower? No (0) Yes (1)", ASK, model));
    	factListMap.put("hasMower", 23);
    	facts.add(new Fact("hasShaker", YESNO, ksession, "Do you have have a shaker? No (0) Yes (1)", ASK, model));
    	factListMap.put("hasShaker", 24);
    	facts.add(new Fact("hasRaker", YESNO, ksession, "Do you have have a raker? No (0) Yes (1)", ASK, model));
    	factListMap.put("hasRaker", 25);
    	facts.add(new Fact("hasHayPacker", YESNO, ksession, "Do you have have a hay packer? No (0) Yes (1)", ASK, model));
    	factListMap.put("hasHayPacker", 26);
    	facts.add(new Fact("hasFertilizerSpreader", YESNO, ksession, "Do you have have a fertilizer spreader? No (0) Yes (1)", ASK, model));
    	factListMap.put("hasFertilizerSpreader", 27);
    	facts.add(new Fact("hasMestGatherer", YESNO, ksession, "Do you have have a mest gatherer? No (0) Yes (1)", ASK, model));
    	factListMap.put("hasMestGatherer", 28);
        
		/* Care questions */
        facts.add(new MCFact("Self Birth", MC, ksession, "Do you want do birthing (0) yourself or (1) let someone else do it?", ASK, model, "Self", "Someone else"));
        factListMap.put("Self Birth", 29);
        facts.add(new Fact("wantsSelfShave", YESNO, ksession, "Do you want to shave yourself? No (0) Yes (1)", ASK, model)); 
        factListMap.put("wantsSelfShave", 30);
//        care.setWantsSelfBirth(*/facts.get(6));
        
        
//        facts[6] = new Fact("ynNeigbourLease", YESNO, ksession, "Are you leasing land? No (0) Yes (1)", ASK, model);    
//        land.setHasLeasedLand(facts[6]);
        

//        materials.setHasTractor(facts.get(7));
        
        createWindow(model);
        

    
        
        
        /* Probably should add a class that saves all the needed money, so we can subtract this from the capitol */       
//        facts[9] = new Fact("nmCapitol", NUMB, ksession, "How much capitol do you have to spend on the sheep business?", ASK, model);
//        business.setMoneyToSpend(facts[9]);
        
        /* How much time does it cost to shave yourself? */
//        facts[10] = new Fact("ynShaveYourself", YESNO, ksession, "Do you want to shave yourself? No (0) Yes (1)", ASK, model);     
//        care.setWantsSelfShave(facts[10]);        
        
        /* Simple rules like if you don't have one of these two "get them" could be added */
//        facts[11] = new Fact("ynRegisteredUBN", YESNO, ksession, "Does your farm already have a unique business number (UBN)? No (0) Yes (1)", ASK, model);
//        business.setIsUBNRegistered(facts[11]);
        
//        facts[12] = new Fact("ynRegisteredKvK", MC, ksession, "Is your farm already registered at the Kamer van Koophandel (KvK)? No (0) Yes (1)", ASK, model);
//        business.setIsKvKRegistered(facts[12]);
        
        
//        ksession.fireAllRules();
//        System.out.println("The Result is ");
//        ((Values) ksession.getGlobal("gvalues")).test();
    }

	private static void createWindow(Model model) {
		try {
				UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
			} catch (Throwable e) {
				e.printStackTrace();
			}
			EventQueue.invokeLater(new Runnable() {
				public void run() {
					try {
						MainView frame = new MainView(model);
						frame.setVisible(true);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			});
	}

	private static KnowledgeBase readKnowledgeBase() throws Exception {
		KnowledgeBuilder kbuilder = KnowledgeBuilderFactory.newKnowledgeBuilder();
		kbuilder.add(ResourceFactory.newClassPathResource("Rules.drl"), ResourceType.DRL);
		KnowledgeBuilderErrors errors = kbuilder.getErrors();
	  
		if (errors.size() > 0) {
			for (KnowledgeBuilderError error: errors) {
				System.err.println(error);
			}
			throw new IllegalArgumentException("Could not parse knowledge.");
		}
		KnowledgeBase kbase = KnowledgeBaseFactory.newKnowledgeBase();
		kbase.addKnowledgePackages(kbuilder.getKnowledgePackages());
		return kbase;
	}
	
    public void setFacts(ArrayList<Fact> facts) {
    	this.facts = facts;
    }
    
    public ArrayList<Fact> getFacts() {
    	return facts;
    }
    
	public void setRemoveFromList(String itemToRemove) {
		this.itemsToRemove.add(itemToRemove);
	}
	
	public ArrayList<String> getRemoveFromList() {
		return itemsToRemove;
	}
	
	public void clearRemoveFromList() {
		if(!itemsToRemove.isEmpty()) {
			itemsToRemove.clear();
		}
	}
    
    public void setCurrentQuestion(Fact fact) {
    	this.currentQuestion = fact;
    }
    
    public Fact getCurrentQuestion() {
    	return currentQuestion;
    }
    
    public void setPrevQuestion(Fact fact) {
    	this.prevQuestion = fact;
    }
    
    public Fact getPrevQuestion() {
    	return prevQuestion;
    }
    
    public void setNextQuestion(Fact fact) {
    	this.nextQuestion = fact;
    }
    
    public Fact getNextQuestion() {
    	return nextQuestion;
    }
    
    public boolean getAllQuestionsAsked() {
    	return allQuestionsAsked;
    }
    
    public void setAllQuestionsAsked(boolean allAsked) {
    	this.allQuestionsAsked = allAsked;
    	ksession.insert(this);
    	ksession.fireAllRules();
    }
    
	public Land getLand() {
		return land;
	}
	
	public Materials getMaterials() {
		return materials;
	}
	
	public Sheep getSheep() {
		return sheep;
	}
	
	public Business getBusiness() {
		return business;
	}
	public Shed getShed() {
		return shed;
	}
	
	public Care getCare() {
		return care;
	}
}
	
