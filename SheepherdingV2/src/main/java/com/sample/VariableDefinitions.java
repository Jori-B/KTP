package com.sample;

public interface VariableDefinitions {
	/* All question types have a name. As of right now we don't really use these */ 
	public static final int YESNO = 0;
    public static final int MC = 1;
    public static final int NUMB = 2;
    public static final int OPEN = 3;
    
    /* This might be used for the status field */
    public static final int NOANSWER = 0;
    public static final int HASANSWER = 1;
	
    public static final int NO = 0;
    public static final int YES = 1;
    
    /* For multiple choice questions we use these variable names to make the code more human readable in the Rules.dlr file */
    public static final int HOBBY = 0;
    public static final int PRO = 1;
    
    public static final int SELF = 0;
    public static final int OTHER = 1;
    
    public static final boolean ASK = true;
    public static final boolean DONTASK = false;
}
