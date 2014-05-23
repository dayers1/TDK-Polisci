/*
 * Model class for GusList app. 
 * Note: Model is on server side. Messages passed to Model
 *  from Controller must be done through RPC methods. 
 *  Model has static methods to simplify RPC calls 
 *    (see AdDataServiceImpl.java)
 */
package edu.gac.polisci.server;

import java.util.ArrayList;
import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.PersistenceManagerFactory;
import javax.jdo.Query;

import edu.gac.polisci.shared.*;

public class ThesisDBModel {
	static final PersistenceManagerFactory pmf = PMF.get();
	static String appBaseURL="";
	static Thesis editingPost =null;

	public static List<Thesis> getThesisData() {
		PersistenceManager pm = pmf.getPersistenceManager();
		Query query = pm.newQuery(Thesis.class);
		List<Thesis> theses = (List<Thesis>) query.execute();
		// Child classes are loaded "lazily" - not until they are accessed.
		// To make sure they are loaded before the PersistenceManager closes,
		// we reference them here so they are forced to load. 

		return new ArrayList<Thesis>(theses);
	}
  
	public static void storeThesis(Thesis thesis) {
		PersistenceManager pm = pmf.getPersistenceManager();
		// Transactions lock all records in a datastore and keep them locked 
		//  until they are ready to commit their changes. This eliminates
		//  possibility of conflict of access
		try {
			pm.currentTransaction().begin();
			pm.makePersistent(thesis);
			pm.currentTransaction().commit();
		}
		finally {
		    if (pm.currentTransaction().isActive())
		      pm.currentTransaction().rollback();
		    if (!pm.isClosed())
		      pm.close();
		   }
	}
	
//	public static List<Professor> getProfessorData() {

//	}
	
	public static void updateEditedPost(Thesis thesis, Thesis changedThesis) {
		
		// If we change a classes field values while the 
		//  Persistencemanager is open, the fields are automatically 
		//  updated in the Datastore
		PersistenceManager pm = pmf.getPersistenceManager();
		// Get the datastore object so that we can update it.  
		Thesis storedThesis = pm.getObjectById(Thesis.class, thesis.getID());

		// Keep alterations in a Transaction, so records are locked until commit
		try {
			pm.currentTransaction().begin();
			storedThesis.setTitle(changedThesis.getTitle());
			storedThesis.setAuthor(changedThesis.getAuthor());
			storedThesis.setProfessor(changedThesis.getProfessor());
			storedThesis.setYear(changedThesis.getYear());
			storedThesis.setSemester(changedThesis.getSemester());
			storedThesis.setClassName(changedThesis.getClassName());
			storedThesis.setTextAbstract(changedThesis.getTextAbstract());
			storedThesis.setTags(changedThesis.getTags());
			pm.currentTransaction().commit();
		}
		finally {
		    if (pm.currentTransaction().isActive())
		      pm.currentTransaction().rollback();
		    if (!pm.isClosed())
		      pm.close();
		   }
		
		// reset editing state
		ThesisDBModel.setEditingPost(null);
	}

	//public static List<Thesis> getTitleMatchData(String titleStr) {
		//PersistenceManager pm = pmf.getPersistenceManager();
		//Declare a Query to be for matches to the title field of a PostData object
		//  "titleVal" will be the parameter type for the field
		//Query query = pm.newQuery(PostData.class,
			//	"this.title == titleVal");
		// declare the parameter type to be a String
		//query.declareParameters("String titleVal");
		// Execute the query with the String given by titleStr
		// Query returns a list of PostData objects matching the quesry
		//List<PostData> posts = (List<PostData>) query.execute(titleStr);
		//return new ArrayList<PostData>(posts);
	//}

	public static void deletePost(Thesis thesis) {
		PersistenceManager pm = pmf.getPersistenceManager();
		// Keep alterations in a Transaction, so records are locked until done
		try {
			pm.currentTransaction().begin();
			// Find the object in the datastore that matches the 
			//  class and ID of the post. Then, delete it. 
			pm.deletePersistent( pm.getObjectById(Thesis.class, thesis.getID() ) );
			pm.currentTransaction().commit();
		}
		finally {
		    if (pm.currentTransaction().isActive())
		      pm.currentTransaction().rollback();
		    if (!pm.isClosed())
		      pm.close();
		   }
	}
	
	// Getters and setters of globally needed values
	public static void setAppBaseURL(String url){
		ThesisDBModel.appBaseURL = url;
	}
	
	public static String getAppBaseURL(){
		return ThesisDBModel.appBaseURL;
	}
	
	public static void setEditingPost(Thesis thesis){
		ThesisDBModel.editingPost = thesis;
	}
	
	public static Thesis getEditingPost() {
		return ThesisDBModel.editingPost;
	}

}
