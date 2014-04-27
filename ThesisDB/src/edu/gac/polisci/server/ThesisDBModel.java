package edu.gac.polisci.server;

import javax.jdo.PersistenceManagerFactory;

import edu.gac.polisci.client.*;
import edu.gac.polisci.server.*;

public class ThesisDBModel {
	static final PersistenceManagerFactory pmf = PMF.get();
	static String appBaseURL="";
	
	// Getters and setters of globally needed values
		public static void setAppBaseURL(String url){
			ThesisDBModel.appBaseURL = url;
		}
		
		public static String getAppBaseURL(){
			return ThesisDBModel.appBaseURL;
		}

		public static void storeThesis() {
			// TODO Auto-generated method stub
			
		}
}
