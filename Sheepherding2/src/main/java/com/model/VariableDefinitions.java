package com.model;

import java.text.DecimalFormat;

import javax.swing.BorderFactory;
import javax.swing.border.Border;

public interface VariableDefinitions {
	/* All question types have a name. As of right now we don't really use these */ 
	public static final int YESNO = 0;
    public static final int MC = 1;
    public static final int NUMB = 2;
    
    /* This might be used for the status field */
    public static final int NOANSWER = 0;
    public static final int HASANSWER = 1;
	
    public static final double NO = 0;
    public static final double YES = 1;
    
    /* For multiple choice questions we use these variable names to make the code more human readable in the Rules.dlr file */
    public static final double HOBBY = 0;
    public static final double PRO = 1;
    
    public static final boolean SELF = false; // it was 0
    public static final boolean OTHER = true; // it was 1
    
    public static final boolean ASK = true;
    public static final boolean DONTASK = false;
    
    DecimalFormat twoDigits = new DecimalFormat("##.00");

    Border loweredbevel = BorderFactory.createLoweredBevelBorder();
	Border raisedbevel = BorderFactory.createRaisedBevelBorder();
    
}
