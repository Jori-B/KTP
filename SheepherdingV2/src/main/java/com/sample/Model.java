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
	    return facts.get(index);
	}
    
    public void createQuestions(StatefulKnowledgeSession ksession, Model model) {
    	
    	ksession.setGlobal("gvalues", new Values());

        facts.add(new Fact("mcHobProf", MC, ksession, "<html> Do you want do farming as a (0) hobby or (1) professionally <br> lange zin lange zin lange zin lange zin lange zin? <html>", ASK, model));
        factListMap.put("mcHobProf", 0);
        
        business.setHobSemiPro(facts.get(0));
        setCurrentQuestion(facts.get(0));
        
        facts.add(new Fact("ynShed", YESNO, ksession, "Do you have a shed? No (0) Yes (1)", ASK, model));    
        factListMap.put("ynShed", 1);
        
        /* This should work as well as the below one */
        shed.setHasShed(getSelectedQuestion("ynShed"));
        //shed.setHasShed(facts.get(1));
        setNextQuestion(facts.get(1));
        

//        facts[3] = new Fact("hasFertilizer", YESNO, ksession, "Do you have a fertilizer plate", model);
        
        facts.add(new Fact("nmShedSize", NUMB, ksession, "How big is your shed (in meters squared)?", model));
        factListMap.put("nmShedSize", 2);
        
        shed.setCurShedSize(facts.get(2));
        facts.add(new Fact("ynLand", YESNO, ksession, "Do you have land (including the land you lease)? No (0) Yes (1)", ASK, model));
        factListMap.put("ynLand", 3);
        
        land.setHasLand(facts.get(3));
        
        facts.add(new Fact("nmLandSize", NUMB, ksession, "How big is your land (in acres)?", model));
        factListMap.put("nmLandSize", 4);
        
        land.setOwnedLandSize(facts.get(4));
        
        facts.add(new Fact("nmSheep", NUMB, ksession, "How many sheep would you like to have?", ASK, model));
        factListMap.put("nmSheep", 5);
        sheep.setDesiresNMoreSheep(facts.get(5));
        
        facts.add(new Fact("mcBirth", MC, ksession, "Do you want do birthing (0) yourself or (1) let someone else do it?", ASK, model));
        factListMap.put("mcBirth", 6);
        care.setWantsSelfBirth(facts.get(6));
        
        
//        facts[6] = new Fact("ynNeigbourLease", YESNO, ksession, "Are you leasing land? No (0) Yes (1)", ASK, model);    
//        land.setHasLeasedLand(facts[6]);
        
        facts.add(new Fact("ynTractor", YESNO, ksession, "Do you have have a tractor? No (0) Yes (1)", ASK, model));
        factListMap.put("ynTractor", 7);
        materials.setHasTractor(facts.get(7));
        
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
        
        
        ksession.fireAllRules();
        System.out.println("The Result is ");
        ((Values) ksession.getGlobal("gvalues")).test();
        //Fact.scanner.close();
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
	
