package com.other;

import java.util.Scanner;

public class Answer {

        public static final int YES = 1;
        public static final int NO = 0;
        public static final int UNDETERMINED = -1;
        private String name;

        private int status = UNDETERMINED;
        
        public Answer(String question) {
        	this.name = question;
        }
        
        public String getName() {
            return this.name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getStatus() {
            return this.status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public void setStatus() {
        	int answer;
        	Scanner scanner = new Scanner( System.in );
        	answer= Integer.parseInt(scanner.nextLine()) ;
        	this.status = answer;	
        
        }
}
