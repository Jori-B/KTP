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

import com.views.AdviceWindow;
import com.views.MainView;

public class Model implements VariableDefinitions {
   
	/* This isn't used, because we didn't figure out how to access the frame outside of it using WindowBuilder */
	public MainView frame; 
	 
    public ArrayList<Fact> facts = new ArrayList<Fact>();
    HashMap<String, Integer> factListMap = new HashMap<String, Integer>();
    /* When a user changes an answer, making its second order question(s) inapplicable, those questions are entered in this list */
    public ArrayList<String> itemsToRemove = new ArrayList<String>();
    
    public Fact currentQuestion;
    public Fact nextQuestion;
    public Fact prevQuestion;
    public boolean allQuestionsAsked;   
    
    public StatefulKnowledgeSession ksession;
    
    public Land land = new Land();
	public Materials materials = new Materials();
	public Sheep sheep = new Sheep();
	public Business business = new Business();
	public Shed shed = new Shed();
	public Care care = new Care();
    
    public Model() {
    	createKnowledgeBase();
    	createWindow(this);
    } 
    
	public void createKnowledgeBase() {
	    try {
			/* Load up the knowledge base */
			KnowledgeBase kbase = readKnowledgeBase();
			StatefulKnowledgeSession ksession = kbase.newStatefulKnowledgeSession();
			setKSession(ksession);
			createQuestions(ksession, this);
		} catch (Throwable t) {
			t.printStackTrace();
	  	}
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
    
	/*
	 * Questions are generally ordered. Sometimes questions are inapplicable (when
	 * askNow == false), so these need to be skipped
	 */
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
    
	/*
	 * Entering all the facts in a HashMap, so they can be accessed easily with
	 * their name with the getSelectedQuestion(name) method
	 */
	public void enterFactsInHash() {
		for (int i = 0; i < facts.size(); i++) {
		    factListMap.put(facts.get(i).getName(), i);
		}
	}
	
	public Fact getSelectedQuestion(String name) {
	    int index = factListMap.get(name);
	    System.out.println(index);
	    return facts.get(index);
	}
	
	/*
	 * In this method all the questions are created. All questions have a name, a
	 * type (multiple choice, yes/no or number), and a question When ASK is set the
	 * question is asked independent of the users answers, while if ASK is not set,
	 * it is a second order question, which is only asked when certain answers are
	 * given by the users. After setting all the questions, they are entered in a
	 * HashMap, for easy access based on their identifying name.
	 */
    public void createQuestions(StatefulKnowledgeSession ksession, Model model) {		
    	/*Business Questions*/
    	facts.add(new MCFact("Hobby or Pro", MC, ksession, "Do you want do farming as a (0) hobby or (1) professionally", ASK, model, "Hobby", "Professional"));
        setCurrentQuestion(facts.get(0));
        facts.add(new Fact("timeWillingToSpend", NUMB, ksession, "<html> How many days are you willing to spend per week <br> on sheep herding? <html>", ASK, model));
        facts.add(new Fact("moneyToSpend", NUMB, ksession, "How much money do you have to spend on sheep herding?", ASK, model));
        facts.add(new Fact("isUBNRegistered", YESNO, ksession, "Does your farm already have a Unique Business Number (UBN)?", ASK, model));
        
        /*Sheep questions*/
        facts.add(new Fact("hasSheep", YESNO, ksession, "Do you already own any sheep? No (0) Yes (1)", ASK, model));
    		// If yes
        	facts.add(new Fact("ownsNSheep", NUMB, ksession, "How many sheep do you own?", model));
        facts.add(new Fact("Number of Sheep", NUMB, ksession, "How many sheep would you like to have in total?", ASK, model));
    		// if professional OR if hobby and Number of Sheep wanted > 10
    		facts.add(new Fact("isKVKRegistered", YESNO, ksession, "Are you registered at the Kamer van Koophandel?", model));
        // Purely for slaughter, breeding or both
        
    	/*Land questions*/
        facts.add(new Fact("Has Land", YESNO, ksession, "Do you own any land (excluding land you lease)? No (0) Yes (1)", ASK, model));
        	// If yes
        	facts.add(new Fact("Land Size", NUMB, ksession, "How big is your land (in acres)?", model));
        facts.add(new Fact("hasLeasedLand", YESNO, ksession, "Are you leasing land? No (0) Yes (1)", ASK, model));  
    		// If yes
        	facts.add(new Fact("leasedLandSize", NUMB, ksession, "How big is the land you lease (in acres)?", model));
        
        /*Shed questions*/	
        facts.add(new Fact("Has Shed", YESNO, ksession, "Do you have a shed? No (0) Yes (1)", ASK, model));    
        	// If yes
        	facts.add(new Fact("Shed Size", NUMB, ksession, "How big is your shed (in meters squared)?", model));
        	facts.add(new Fact("heightShed", NUMB, ksession, "How high is your shed (in meters)?", model));
        	facts.add(new Fact("widthShed", NUMB, ksession, "How wide is the walking space of shed (in meters)?", model));
        	facts.add(new Fact("hasFertilizer", YESNO, ksession, "Do you have a fertilizer plate in your shed?", model));
    		facts.add(new Fact("hasFlatFloor", YESNO, ksession, "Does your have a flat floor?", model));
    		facts.add(new Fact("hasLamps", YESNO, ksession, "Does your shed have small lamps where the sheep should birth?", model));
    		// If shedTooSmall
    		facts.add(new Fact("isAllowedToBuild", YESNO, ksession, "Are you allowed to build a shed or expand your shed somewhere?", model));
    		facts.add(new Fact("roomForShed", NUMB, ksession, "How much room do you have to build a shed (in meters squared)?", model));
        
        /*Materials questions*/
        facts.add(new Fact("Has Tractor", YESNO, ksession, "Do you have have a tractor? No (0) Yes (1)", ASK, model));
    		// If yes
        	facts.add(new Fact("horsePowerTractor", NUMB, ksession, "How much horsepower does your tractor have", model));
        	// If more than 10 sheep
        	facts.add(new Fact("hasMower", YESNO, ksession, "Do you have have a mower? No (0) Yes (1)", model));
        	facts.add(new Fact("hasShaker", YESNO, ksession, "Do you have have a shaker? No (0) Yes (1)", model));
        	facts.add(new Fact("hasRaker", YESNO, ksession, "Do you have have a raker? No (0) Yes (1)", model));
        	facts.add(new Fact("hasHayPacker", YESNO, ksession, "Do you have have a hay packer? No (0) Yes (1)", model));
        	facts.add(new Fact("hasFertilizerSpreader", YESNO, ksession, "Do you have have a fertilizer spreader? No (0) Yes (1)", model));
        	facts.add(new Fact("hasMestGatherer", YESNO, ksession, "Do you have have a mest gatherer? No (0) Yes (1)", model));
       
		/* Care questions */
    	facts.add(new Fact("wantsLambs", YESNO, ksession, "Do you want the sheep to get lambs?", ASK, model));
    	//factListMap.put("wantsLambs", 29);
    		facts.add(new MCFact("Self Birth", MC, ksession, "Do you want do birthing (0) yourself or (1) let someone else do it?", model, "Self", "Someone else"));
        	/* How much time does it cost to shave yourself? */
        facts.add(new Fact("wantsSelfShave", YESNO, ksession, "Do you want to shave yourself? No (0) Yes (1)", ASK, model)); 
        
        enterFactsInHash();
    }
    
    
	/*
	 * When all the questions are asked, the user cannot change their answers
	 * anymore, so then the answers can be put in the knowledge base. The classes are filled up and 
	 * in the Rules.dlr file these classes are entered.
	 */
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
		care.setWantsLambs(getSelectedQuestion("wantsLambs"));
		care.setWantsSelfBirth(getSelectedQuestion("Self Birth"));
		care.setWantsSelfShave(getSelectedQuestion("wantsSelfShave"));
		
		createAdviceWindow();
	}
    
    private void createAdviceWindow() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AdviceWindow frame = new AdviceWindow(business.getAdvice(), sheep.getAdvice(), land.getAdvice(), shed.getAdvice(), materials.getAdvice(), care.getAdvice());
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
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
		kbuilder.add(ResourceFactory.newClassPathResource("ShedRules.drl"), ResourceType.DRL);
		kbuilder.add(ResourceFactory.newClassPathResource("SheepRules.drl"), ResourceType.DRL);
		kbuilder.add(ResourceFactory.newClassPathResource("BusinessRules.drl"), ResourceType.DRL);
		kbuilder.add(ResourceFactory.newClassPathResource("CareRules.drl"), ResourceType.DRL);
		kbuilder.add(ResourceFactory.newClassPathResource("LandRules.drl"), ResourceType.DRL);
		kbuilder.add(ResourceFactory.newClassPathResource("MaterialsRules.drl"), ResourceType.DRL);
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
	
    public void setKSession(StatefulKnowledgeSession ksession) {
    	this.ksession = ksession;
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
		/* Needs to be inserted in the knowledge base, so the Rules.dlr file knows about it */
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
	
