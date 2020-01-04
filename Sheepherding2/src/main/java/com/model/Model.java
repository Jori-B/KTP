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
	public Cost costs; 
    
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
    	/*Business Questions*/ //&emsp; is the tab character
    	questions.add(new MCQuestion("Hobby or Pro", MC, ksession, "Do you want do farming as a hobby or professionally?", 
    			"<html>&emsp;This system considers someone a professional when they are trying <br>&emsp;to make money doing sheep herding.<html>", ASK, model, "Hobby", "Professional"));
        setCurrentQuestion(questions.get(0));
        questions.add(new Question("Time willing to spend", NUMB, ksession, "<html>How many days are you willing to spend per week <br> on sheep herding? <html>", 
        		"<html>&emsp;Keep in mind that everyone who has animals needs to <br>&emsp;check on those animals every day. However, in this case it is meant: <br>&emsp;how many days would you fully want to spend on herding.<html>", 
        		ASK, model));
        questions.add(new Question("Money to spend", NUMB, ksession, "How much money do you have to spend on sheep herding?", 
        		"<html>&emsp;What is your starting capital? <br>&emsp;Sheep herding is expensive at first, since there are many <br>&emsp;materials, a shed and land that you need to have<html>", ASK, model));
        questions.add(new Question("Is UBN registered", YESNO, ksession, "Does your farm already have a Unique Business Number (UBN)?", 
        		"<html>&emsp;Everyone who has farm animals needs to be registered at the UBN.<html>", ASK, model));
        // SLAUGHTER
        /*Sheep questions*/
        	// IF isUBNRegistered
        	questions.add(new Question("Has sheep", YESNO, ksession, "Do you already own any sheep?", 
        			"<html>&emsp;If you already own sheep it is interesting for this system to know how many.<html>", DONTASK, model));
    			// If yes
        		questions.add(new Question("Owned number of sheep", NUMB, ksession, "How many sheep do you own?", 
        				"<html>&emsp;If you already own sheep it is interesting for this system to know how many.<html>", DONTASK, model));
        questions.add(new Question("Number of Sheep", NUMB, ksession, "How many sheep would you like to have in total?", 
        		"<html>&emsp;Someone who works 7 days on the farm and wants to make money would <br>&emsp;be recommended to get at least 200 sheep.<html>", ASK, model));
    		// if professional OR if hobby and Number of Sheep wanted > 10
    		questions.add(new Question("Is KVK registered", YESNO, ksession, "Are you registered at the Kamer van Koophandel?", 
    				"<html>&emsp;Every business needs to be registered at the KvK.<html>", DONTASK, model));
        // Purely for slaughter, breeding or both
        
    	/*Land questions*/
        questions.add(new Question("Has Land", YESNO, ksession, "Do you own any land (excluding land you lease)?", 
        		"<html>&emsp;Some people lease land. Here we are first only interested in owned land.<html>", ASK, model));
        	// If yes
        	questions.add(new Question("Land Size", NUMB, ksession, "How big is your land (in acres)?", 
        			"<html>&emsp;If you don't know exactly, then answer an estimation.", DONTASK, model));
        	questions.add(new Question("Toeslagrechten", NUMB, ksession, "<html>How many acres of land have <br>payment entitlements (\"toeslagrechten\")?<html>", 
        			"<html>&emsp;Toeslagrechten cannot be bought anymore. <br>&emsp;However, some people still own land with toeslagrechten. <br>&emsp;The system will calculate how much you get for you payment entitlements <br>&emsp;per year, based on the estimate of 350 euros per acre.<html>", DONTASK, model));
        questions.add(new Question("Has leased land", YESNO, ksession, "Are you leasing land?", 
        		"<html>&emsp;Leasing land is much cheaper than buying it.<html>", ASK, model));  
    		// If yes
        	questions.add(new Question("leasedLandSize", NUMB, ksession, "How big is the land you lease (in acres)?", 
        			"<html>&emsp;Leasing land is much cheaper than buying it.<html>", DONTASK, model));
        
        /*Shed questions*/	
        questions.add(new Question("Has Shed", YESNO, ksession, "Do you have a shed?", 
        		"<html>&emsp;A shed is essential for sheep when they are birthing.", ASK, model));    
        	// If yes
        	questions.add(new Question("Shed Size", NUMB, ksession, "How big is your shed (in meters squared)?", 
        			"<html>&emsp;2.5 squared meters per sheep is recommended.<html>", DONTASK, model));
        	questions.add(new Question("heightShed", NUMB, ksession, "How high is your shed (in meters)?", 
        			"<html>&emsp;The shed needs to be high enough for a tractor to go through (3m).<html>", DONTASK, model));
        	questions.add(new Question("pathWidthShed", NUMB, ksession, "How wide is the path in your shed (in meters)?", 
        			"<html>&emsp;The path needs to be wide enough for a tractor to go through (3m).<html>", DONTASK, model));
        	questions.add(new Question("hasFertilizer", YESNO, ksession, "Do you have a fertilizer plate outside your shed?", 
        			"<html>&emsp;It is mandatory to have a fertilizer plate.<html>", DONTASK, model));
        	questions.add(new Question("hasAdjustableFences", YESNO, ksession, "Does your shed have fences already?", 
        			"<html>&emsp;Sheep need to be divided in small pens. <br>&emsp;Also fences for eating are necessary.<html>", DONTASK, model));
    		questions.add(new Question("hasFlatFloor", YESNO, ksession, "Does your shed have a flat floor?", 
    				"<html>&emsp;Flat floors are handy.<html>", DONTASK, model));
    		questions.add(new Question("hasElectricity", YESNO, ksession, "Does your shed have electricity?", 
    				"<html>&emsp;Lamps should be hung in the shed, for you and lambs.<html>", DONTASK, model));
    		questions.add(new Question("hasWater", YESNO, ksession, "Does your shed have water works?", 
    				"<html>&emsp;Sheep need water.<html>", DONTASK, model));
    		// If shedTooSmall
    		questions.add(new Question("isAllowedToBuild", YESNO, ksession, "Are you allowed to build a shed or expand your shed somewhere?", 
    				"<html>&emsp;Your shed is considered too small. <br>&emsp;According to the nuisance law are you allowed to build more shed? <br>&emsp;Approval needs to be asked for this in case you dont<html>", DONTASK, model));
    		questions.add(new Question("roomForShed", NUMB, ksession, "How much room do you have to build a shed (in meters squared)?", 
    				"<html>&emsp;Your shed is considered too small.<html>", DONTASK, model));
        
        /*Materials questions*/
        questions.add(new Question("Has Tractor", YESNO, ksession, "Do you have a tractor?", 
        		"<html>&emsp;For any professional a tractor is recommended to work the land.<html>", ASK, model));
    		// If yes
        	questions.add(new Question("horsePowerTractor", NUMB, ksession, "How much horsepower does your tractor have?", 
        			"<html>&emsp;Between 50 and 70 horsepower is enough.<html>", DONTASK, model));
        	// If more than 10 sheep
        	questions.add(new Question("hasMower", YESNO, ksession, "Do you have a mower?", 
        			"<html>&emsp;One of the essential materials for working the grassland<html>", DONTASK, model));
        	questions.add(new Question("hasShaker", YESNO, ksession, "Do you have a shaker?", 
        			"<html>&emsp;One of the essential materials for working the grassland<html>", DONTASK, model));
        	questions.add(new Question("hasRaker", YESNO, ksession, "Do you have a raker?", 
        			"<html>&emsp;One of the essential materials for working the grassland<html>", DONTASK, model));
        	questions.add(new Question("hasHayPacker", YESNO, ksession, "Do you have a hay packer?", 
        			"<html>&emsp;Expensive, but nice to have. <br>&emsp;One can hire someone to do this for them.<html>", DONTASK, model));
        	questions.add(new Question("hasFertilizerSpreader", YESNO, ksession, "Do you have a fertilizer spreader?", 
        			"<html>&emsp;One of the essential materials for working the grassland", DONTASK, model));
        	questions.add(new Question("hasMestGatherer", YESNO, ksession, "Do you have a mest gatherer?", 
        			"<html>&emsp;Expensive, but nice to have. <br>&emsp;One can hire someone to do this for them.<html>", DONTASK, model));
       
		/* Care questions */
    	questions.add(new Question("wantsLambs", YESNO, ksession, "Do you want the sheep to get lambs?", 
    			"<html>&emsp;Lambing is the main source of income for sheep herders.<html>", ASK, model));
    	//factListMap.put("wantsLambs", 29);
    		questions.add(new MCQuestion("Self Birth", MC, ksession, "Do you want do birthing yourself or let someone else do it?", 
    				"<html>&emsp;Every sheep herder should learn this.<html>", DONTASK, model, "Self", "Someone else"));
        	/* How much time does it cost to shave yourself? */
        questions.add(new Question("wantsSelfShave", YESNO, ksession, "Do you want to shave the sheep yourself?", 
        		"<html>&emsp;Shaving doesn't earn you much money. <br>&emsp;One can hire someone to do this for them.<html>", ASK, model)); 
        
        enterFactsInHash();
    }
    
    
	/*
	 * When all the questions are asked, the user cannot change their answers
	 * anymore, so then the answers can be put in the knowledge base. The classes are filled up and 
	 * in the Rules.dlr file these classes are entered.
	 */
    public void setAllAnswers() {
		business.setHobSemiPro(getSelectedQuestion("Hobby or Pro"));
		business.setIsKvKRegistered(getSelectedQuestion("Is KVK registered"));
		business.setTimeWillingToSpend(getSelectedQuestion("Time willing to spend"));
		business.setMoneyToSpend(getSelectedQuestion("Money to spend"));
		business.setIsUBNRegistered(getSelectedQuestion("Is UBN registered"));
		sheep.setHasSheep(getSelectedQuestion("Has sheep"));
		sheep.setOwnsNSheep(getSelectedQuestion("Owned number of sheep"));
		sheep.setTotalNSheepWanted(getSelectedQuestion("Number of Sheep"));
		land.setHasLand(getSelectedQuestion("Has Land"));
		land.setOwnedLandSize(getSelectedQuestion("Land Size"));
		land.setLandSizeToeslag(getSelectedQuestion("Toeslagrechten"));
		land.setHasLeasedLand(getSelectedQuestion("Has leased land"));
		land.setLeasedLandSize(getSelectedQuestion("leasedLandSize"));
		shed.setHasShed(getSelectedQuestion("Has Shed"));
		shed.setCurShedSize(getSelectedQuestion("Shed Size"));
		shed.setIsTallerThan3(getSelectedQuestion("heightShed"));
		shed.setIsPathWiderThan3(getSelectedQuestion("pathWidthShed"));
		shed.setHasFertilizerPlate(getSelectedQuestion("hasFertilizer"));
		shed.setHasAdjustableFences(getSelectedQuestion("hasAdjustableFences"));
		shed.setHasCementFloor(getSelectedQuestion("hasFlatFloor"));
		shed.setHasElectricity(getSelectedQuestion("hasElectricity"));
		shed.setHasWater(getSelectedQuestion("hasWater"));
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
		
	}
    
    public void enterCosts() {
    	this.costs = new Cost(sheep.totalNSheepWanted, sheep.ownsNSheep);
    	/* Materials */
    	costs.setTractorCost(materials.getNeedsNewTractor(), materials.getNeedsBigTractor());
    	costs.setGrassMaterialCost(materials.hasMower, materials.hasShaker, materials.hasRaker, materials.hasFertilizerSpreader);
    	/* Land */
    	costs.setToeslagrechtEarnings(land.toeslagrecht);
    	costs.setLandNeededCost(land.landNeeded); 
    	/* Care */
    	costs.setShaveOtherCost(care.shaveOtherCost);
    	costs.setWoolEarnings(care.woolEarnings);
    	/* Sheep */
    	//costs.setPhosphateRightsCost(sheep.costPhosphateRights);
    	/* Calculate totals */
    	costs.setTotalCost();
    	costs.setTotalEarnings();
    	costs.setMoneyNeeded();
    	business.setMoneyNeeded(costs.getMoneyNeeded());
    	costs.setMoneyToSpend(business.getMoneyToSpend());
    }
    
    public void giveAdviceAndTable() {
    	createAdviceWindow(costs, this);
    }
    
    private void createAdviceWindow(Cost costs, Model model) {
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
		//kbuilder.add(ResourceFactory.newClassPathResource("SheepRules.drl"), ResourceType.DRL);
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
	
