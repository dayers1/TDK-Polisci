package edu.gac.polisci.shared;

import java.io.Serializable;
import java.util.Comparator;

import javax.jdo.annotations.IdentityType;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

import com.google.gwt.user.client.rpc.AsyncCallback;

@PersistenceCapable(identityType=IdentityType.APPLICATION)
public class Thesis implements Serializable {
	
	private String id;
	
	@PrimaryKey
	@Persistent
	private String title;
	private String author="no author";
	@Persistent
	private String professor="empty";
	@Persistent
	private String year="no year";
	@Persistent
	private String semester="no semester";
	@Persistent
	private String URL="no URL";
	@Persistent
	private String className = "no class";
	
	// Need to define the Tags as "Unowned" child objects, 
	//  as they do not disappear when Thesis object is deleted.  
	//@Persistent
// @Unowned
	//private Tag tag;
		
	// GWT serializable Objects need a no=argument constructor
		public Thesis() {}

		public Thesis(String t, String a, String p, String y, String s, String c,
				         String url){
			title = t;
			author = a;
			professor = p;
			year = y;
			semester = s;
			className = c;
			URL = url;
		}

		public String getTitle() {
			return title;
		}

		public void setTitle(String title) {
			this.title = title;
		}

		public String getAuthor() {
			return author;
		}

		public void setAuthor(String author) {
			this.author = author;
		}

		public String getProfessor() {
			return professor;
		}

		public void setProfessor(String professor) {
			this.professor = professor;
		}

		public String getYear() {
			return year;
		}

		public void setYear(String year) {
			this.year = year;
		}

		public String getSemester() {
			return semester;
		}

		public void setSemester(String semester) {
			this.semester = semester;
		}

		public String getClassName() {
			return className;
		}
		
		public void setClassName(String className) {
			this.className = className;
		}
		
		public String getURL() {
			return URL;
		}

		public void setURL(String uRL) {
			URL = uRL;
		}

		public String getID() {
			return id;
		}

	
	
	
	
}