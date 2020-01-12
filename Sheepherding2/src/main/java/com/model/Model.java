package com.model;

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

import com.model.Business;
import com.model.Care;
import com.model.Land;
import com.model.Materials;
import com.model.Shed;
import com.model.Sheep;
import com.views.AdviceWindow;
import com.views.MainView;

public class Model implements VariableDefinitions {
   
	/* This isn't used, because we didn't figure out how to access the frame outside of it using WindowBuilder */
	public MainView frame; 
	 
    public ArrayList<Question> questions = new ArrayList<Question>();
    HashMap<String, Integer> factListMap = new HashMap<String, Integer>();
    /* When a user changes an answer, making its second order question(s) inapplicable, those questions are entered in this list */
    public ArrayList<String> itemsToRemove = new ArrayList<String>();
    
    public Question currentQuestion;
    public Question nextQuestion;
    public Question prevQuestion;
    public boolean allQuestionsAsked;   
    
    public StatefulKnowledgeSession ksession;
    
    public Land land = new Land();
	public Materials materials = new Materials();
	public Sheep sheep = new Sheep();
	public Business business = new Business();
	public Shed shed = new Shed();
	public Care care = new Care();
	public Cost costs = new Cost(); 
    
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
    
    public void findPrevQuestion(Question current) {
    	for (int i = (questions.indexOf(current) - 1); i >= 0; i--) {
    		Question prevFact = questions.get(i);
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
    public void findNextQuestion(Question previous) {
    	setPrevQuestion(previous);
    	for (int i = (questions.indexOf(previous) + 1); i < questions.size(); i++) {
    		Question nextFact = questions.get(i);
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
		for (int i = 0; i < questions.size(); i++) {
		    factListMap.put(questions.get(i).getName(), i);
		}
	}
	
	public Question getSelectedQuestion(String name) {
	    int index = factListMap.get(name);
	    return questions.get(index);
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
    	/*Business Questions*/ // is the tab character
    	questions.add(new MCQuestion("Hobby or Professional", MC, ksession, "Do you want do farming as a hobby or professionally?", 
    			"<html>This system considers someone a professional when they are trying <br>to make money doing sheep herding.<html>", ASK, model, "Hobby", "Professional"));
        setCurrentQuestion(questions.get(0));
        questions.add(new Question("Time Willing To Spend", NUMB, ksession, "<html>How many days are you willing to spend per week <br> on sheep herding? <html>", 
        		"<html>Keep in mind that everyone who has animals needs to <br>check on those animals every day. However, in this case it is meant: <br>how many days would you fully want to spend on herding.<html>", 
        		ASK, model));
        questions.add(new Question("Money To Spend", NUMB, ksession, "How much money do you have to spend on sheep herding?", 
        		"<html>What is your starting capital? <br>Sheep herding is expensive at first, since there are many <br>materials, a shed and land that you need to have<html>", ASK, model));
        questions.add(new Question("Is UBN Registered", YESNO, ksession, "Does your farm already have a Unique Business Number (UBN)?", 
        		"<html>Everyone who has farm animals needs to be registered at the UBN.<html>", ASK, model));
        // SLAUGHTER
        /*Sheep questions*/
        	// IF isUBNRegistered
        	questions.add(new Question("Has Sheep", YESNO, ksession, "Do you already own any sheep?", 
        			"<html>If you already own sheep it is interesting for this system to know how many.<html>", DONTASK, model));
    			// If yes
        		questions.add(new Question("Owned Number Of Sheep", NUMB, ksession, "How many sheep do you own?", 
        				"<html>If you already own sheep it is interesting for this system to know how many.<html>", DONTASK, model));
        /* TOTAL SHOULD BE BIGGER THAN OWNED (JUST LIKE TOESLAGRECHTEN)*/
        questions.add(new Question("Number Of Sheep", NUMB, ksession, "How many grown up sheep would you like to have in total?", 
        		"<html>Someone who works 7 days on the farm and wants to make money would <br>be recommended to get at least 200 female sheep; ewes.<br>Ewes get two lambs on average, but these are sold quickly after birth.<html>", ASK, model));
    		// if professional OR if hobby and Number of Sheep wanted > 10
    		questions.add(new Question("Is KVK Registered", YESNO, ksession, "Are you registered at the Kamer van Koophandel?", 
    				"<html>Every business needs to be registered at the KvK.<html>", DONTASK, model));
        // Purely for slaughter, breeding or both
        
    	/*Land questions*/
        questions.add(new Question("Has Land", YESNO, ksession, "Do you own any land (excluding land you lease)?", 
        		"<html>Some people lease land. Here we are first only interested in owned land.<html>", ASK, model));
        	// If yes
        	questions.add(new Question("Land Size", NUMB, ksession, "How big is your land (in hectares)?", 
        			"<html>If you don't know exactly, then answer an estimation.", DONTASK, model));
        	questions.add(new Question("Toeslagrechten", NUMB, ksession, "<html>How many hectares of land have <br>payment entitlements (\"toeslagrechten\")?<html>", 
        			"<html>Toeslagrechten cannot be bought anymore. <br>However, some people still own land with toeslagrechten. <br>The system will calculate how much you get for you payment entitlements <br>per year, based on the estimate of 350 euros per hectare.<html>", DONTASK, model));
        questions.add(new Question("Has Leased Land", YESNO, ksession, "Are you leasing land?", 
        		"<html>Leasing land is much cheaper than buying it.<html>", ASK, model));  
    		// If yes
        	questions.add(new Question("Leased Land Size", NUMB, ksession, "How big is the land you lease (in hectares)?", 
        			"<html>Leasing land is much cheaper than buying it.<html>", DONTASK, model));
        
        /*Shed questions*/	
        questions.add(new Question("Has Shed", YESNO, ksession, "Do you have a shed?", 
        		"<html>A shed is essential for sheep when they are birthing.", ASK, model));    
        	// If yes
        	questions.add(new Question("Shed Size", NUMB, ksession, "How big is your shed in length and width?", 
        			"<html>2.5 squared meters per sheep is recommended. <br>It doesn't matter which side you enter as length or width.<html>", DONTASK, model));
        	questions.add(new Question("Shed Height", NUMB, ksession, "How high is your shed (in meters)?", 
        			"<html>The shed needs to be high enough for a tractor to go through (3m).<html>", DONTASK, model));
        	questions.add(new Question("Shed Path Width", NUMB, ksession, "How wide is the path in your shed (in meters)?", 
        			"<html>The path needs to be wide enough for a tractor to go through (3m).<html>", DONTASK, model));
        	questions.add(new Question("Has Fertilizer", YESNO, ksession, "Do you have a mest plate outside your shed?", 
        			"<html>It is mandatory to have a mest plate.<html>", DONTASK, model));
        	questions.add(new Question("Has Adjustable Fences", YESNO, ksession, "Does your shed have enough movable fences already?", 
        			"<html>Sheep need to be divided in small pens when they have lambs. <br>This system assumes roughly one movable fence (1.5m) per sheep.<html>", DONTASK, model));
        	questions.add(new Question("Has Eating Fences", YESNO, ksession, "Does your shed have fences for feeding with food trays?", 
        			"<html>Sheep stick their head through the feeding fences to eat orderly.<html>", DONTASK, model));
        	questions.add(new Question("Has Flat Floor", YESNO, ksession, "Does your shed have a flat floor?", 
    				"<html>Flat floors are handy.<html>", DONTASK, model));
    		questions.add(new Question("Has Electricity", YESNO, ksession, "Does your shed have electricity?", 
    				"<html>Lamps should be hung in the shed, for you and lambs.<html>", DONTASK, model));
    		questions.add(new Question("hasWater", YESNO, ksession, "Does your shed have water works?", 
    				"<html>Sheep need water.<html>", DONTASK, model));
    		// If shedTooSmall
    		questions.add(new Question("Is Allowed To Build", YESNO, ksession, "Are you allowed to build a shed or expand your shed somewhere?", 
    				"<html>Your shed is considered too small. <br>According to the nuisance law are you allowed to build more shed? <br>Approval needs to be asked for this in case you dont<html>", DONTASK, model));
    		questions.add(new Question("Room For Shed", NUMB, ksession, "How much room do you have to build a shed (in meters squared)?", 
    				"<html>Your shed is considered too small.<html>", DONTASK, model));
        
        /*Materials questions*/
        questions.add(new Question("Has Tractor", YESNO, ksession, "Do you have a tractor?", 
        		"<html>For any professional a tractor is recommended to work the land.<html>", ASK, model));
    		// If yes
        	questions.add(new Question("Horse Power Tractor", NUMB, ksession, "How much horsepower does your tractor have?", 
        			"<html>Between 50 and 70 horsepower is enough.<html>", DONTASK, model));
        	// If more than 10 sheep
        	questions.add(new Question("Has Mower", YESNO, ksession, "Do you have a mower?", 
        			"<html>A mower mows the grass that will be converted to bales of hay.<br>It is one of the essential materials for working the grassland<html>", DONTASK, model));
        	questions.add(new Question("Has Shaker", YESNO, ksession, "Do you have a shaker?", 
        			"<html>A shaker spreads the mown grass accross the land so it can dry.<br>It is one of the essential materials for working the grassland<html>", DONTASK, model));
        	questions.add(new Question("Has Raker", YESNO, ksession, "Do you have a raker?", 
        			"<html>A raker is used for piling the grass for easy packaging. <br>It is one of the essential materials for working the grassland<html>", DONTASK, model));
        	questions.add(new Question("Has Hay Packer", YESNO, ksession, "Do you have a hay packer?", 
        			"<html>Expensive, but nice to have. <br>One can hire someone to do this for them.<html>", DONTASK, model));
        	questions.add(new Question("Has Fertilizer Spreader", YESNO, ksession, "Do you have a fertilizer spreader?", 
        			"<html>To spread fertilizer on the land for the grass to grow a fertilizer spreader is used. <br>It is one of the essential materials for working the grassland", DONTASK, model));
        	questions.add(new Question("Has Mest Gatherer", YESNO, ksession, "Do you have a mest gatherer?", 
        			"<html>Expensive, but nice to have. <br>One can hire someone to do this for them.<html>", DONTASK, model));
        	questions.add(new Question("Has Mest Spreader", YESNO, ksession, "Do you have a mest spreader and/or mestwagon?", 
        			"<html>Very expensive and needs a tractor with 200 hp. <br>One should hire someone to do this for them.<html>", DONTASK, model));
        questions.add(new Question("Has Shaver", YESNO, ksession, "Do you have a shaving machine?", 
        			"<html>A shaving machine is only 400� (with three knives for a year). <br>It is adviced that everyone should get one for when there are <br>for example worms and the area of skin needs cleaning.<html>", ASK, model));
		/* Care questions */
        questions.add(new Question("Wants Self Shave", YESNO, ksession, "Do you want to shave the sheep yourself?", 
    		"<html>Shaving doesn't earn you much money. <br>One can hire someone to do this for them.<html>", ASK, model)); 
        questions.add(new Question("Wants Slaughter", YESNO, ksession, "Do you want to sell sheep for slaughter?", 
        			"<html>Selling the sheep for slaughter is a main source of income.<html>", ASK, model));
        questions.add(new Question("Wants Lambs", YESNO, ksession, "Do you want the sheep to get lambs?", 
    			"<html>Lambing is the main source of income for sheep herders.<html>", ASK, model));
    		questions.add(new MCQuestion("Self Birth", MC, ksession, "Do you want do birthing yourself or let someone else do it?", 
    				"<html>Every sheep herder should learn this.<html>", DONTASK, model, "Self", "Someone else"));
        
        enterFactsInHash();
    }
    
    
	/*
	 * When all the questions are asked, the user cannot change their answers
	 * anymore, so then the answers can be put in the knowledge base. The classes are filled up and 
	 * in the Rules.dlr file these classes are entered.
	 */
    public void setAllAnswers() {
		business.setHobSemiPro(getSelectedQuestion("Hobby or Professional"));
		business.setIsKvKRegistered(getSelectedQuestion("Is KVK Registered"));
		business.setTimeWillingToSpend(getSelectedQuestion("Time Willing To Spend"));
		business.setMoneyToSpend(getSelectedQuestion("Money To Spend"));
		business.setIsUBNRegistered(getSelectedQuestion("Is UBN Registered"));
		sheep.setHasSheep(getSelectedQuestion("Has Sheep"));
		sheep.setOwnsNSheep(getSelectedQuestion("Owned Number Of Sheep"));
		sheep.setTotalNSheepWanted(getSelectedQuestion("Number Of Sheep"));
		land.setHasLand(getSelectedQuestion("Has Land"));
		land.setOwnedLandSize(getSelectedQuestion("Land Size"));
		land.setLandSizeToeslag(getSelectedQuestion("Toeslagrechten"));
		land.setHasLeasedLand(getSelectedQuestion("Has Leased Land"));
		land.setLeasedLandSize(getSelectedQuestion("Leased Land Size"));
		shed.setHasShed(getSelectedQuestion("Has Shed"));
		shed.setCurShedSize(getSelectedQuestion("Shed Size"));
		shed.setIsTallerThan3(getSelectedQuestion("Shed Height"));
		shed.setIsPathWiderThan3(getSelectedQuestion("Shed Path Width"));
		shed.setHasFertilizerPlate(getSelectedQuestion("Has Fertilizer"));
		shed.setHasAdjustableFences(getSelectedQuestion("Has Adjustable Fences"));
		shed.setHasEatingFences(getSelectedQuestion("Has Eating Fences"));
		shed.setHasCementFloor(getSelectedQuestion("Has Flat Floor"));
		shed.setHasElectricity(getSelectedQuestion("Has Electricity"));
		shed.setHasWater(getSelectedQuestion("hasWater"));
		shed.setIsAllowedToBuild(getSelectedQuestion("Is Allowed To Build"));
		shed.setRoomForShed(getSelectedQuestion("Room For Shed"));
		materials.setHasTractor(getSelectedQuestion("Has Tractor"));
		materials.setHorsePowerTractor(getSelectedQuestion("Horse Power Tractor"));
		materials.setHasShaker(getSelectedQuestion("Has Shaker"));
		materials.setHasRaker(getSelectedQuestion("Has Raker"));
		materials.setHasMower(getSelectedQuestion("Has Mower"));
		materials.setHasMestGatherer(getSelectedQuestion("Has Mest Gatherer"));
		materials.setHasHayPacker(getSelectedQuestion("Has Hay Packer"));
		materials.setHasMestSpreader(getSelectedQuestion("Has Mest Spreader"));
		materials.setHasFertilizerSpreader(getSelectedQuestion("Has Fertilizer Spreader"));
		materials.setHasShaver(getSelectedQuestion("Has Shaver"));
		care.setWantsSlaughter(getSelectedQuestion("Wants Slaughter"));
		care.setWantsLambs(getSelectedQuestion("Wants Lambs"));
		care.setWantsSelfBirth(getSelectedQuestion("Self Birth"));
		care.setWantsSelfShave(getSelectedQuestion("Wants Self Shave"));
		
	}
    
    public void enterCosts() {
    	int totalNSheepWanted = sheep.getTotalNSheepWanted(); 	
    	/* Business */
    	costs.setAdminastrativeCost(totalNSheepWanted, sheep.ownsNSheep, care.getWantsSlaughter()); 
    	/* Sheep */
    	costs.setSheepSoldEarnings(totalNSheepWanted);
    	/* Care */
    	costs.setWormCost(totalNSheepWanted);
    	costs.setMyasTreatmentCost(totalNSheepWanted, care.getWantsSlaughter());
    	/* Materials */
    	costs.setTractorCost(materials.getNeedsNewTractor(), materials.getNeedsBigTractor());
    	costs.setGrassMaterialCost(materials.hasMower, materials.hasShaker, materials.hasRaker, materials.hasFertilizerSpreader);
    	costs.setShaverCost(materials.getHasShaver());
    	/* Land */
    	costs.setToeslagrechtEarnings(land.toeslagrecht);
    	/* Shed */
    	costs.setShedCost(shed.getGoalCurSizeDiff());
    	costs.setFertilizerPlateCost(shed.getHasFertilizerPlate());
    	costs.setEatingFenceCost(shed.getLengthShed(), shed.getShedTooSmall(), shed.getCurShedSize(), shed.getGoalSize(), shed.getHasEatingFences());
    	costs.setAdjFenceCost(totalNSheepWanted, shed.getHasAdjustableFences());
    	/* Calculate totals */
    	costs.setTotalCost();
    	costs.setTotalEarnings();
    	costs.setMoneyNeeded();
    	business.setMoneyNeeded(costs.getMoneyNeeded());
    	costs.setMoneyToSpend(business.getMoneyToSpend());

    	ksession.insert(costs);
    }
    
    public void giveAdviceAndTable() {
    	createAdviceWindow(costs, this);
    }
    
    private void createAdviceWindow(final Cost costs, final Model model) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AdviceWindow frame = new AdviceWindow(costs, model, business.getAdvice(), sheep.getAdvice(), land.getAdvice(), shed.getAdvice(), materials.getAdvice(), care.getAdvice());
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
    }

	private static void createWindow(final Model model) {
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
    
    public StatefulKnowledgeSession getKSession() {
    	return ksession;
    }
	
    public void setFacts(ArrayList<Question> questions) {
    	this.questions = questions;
    }
    
    public ArrayList<Question> getFacts() {
    	return questions;
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
    
    public void setCurrentQuestion(Question question) {
    	this.currentQuestion = question;
    }
    
    public Question getCurrentQuestion() {
    	return currentQuestion;
    }
    
    public void setPrevQuestion(Question question) {
    	this.prevQuestion = question;
    }
    
    public Question getPrevQuestion() {
    	return prevQuestion;
    }
    
    public void setNextQuestion(Question question) {
    	this.nextQuestion = question;
    }
    
    public Question getNextQuestion() {
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
    
    public void setCost(Cost costs) {
    	this.costs = costs;
    }
    
    public Cost getCost() {
    	return costs;
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
	
