package edu.gac.polisci.shared;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import javax.jdo.annotations.Extension;
import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.IdentityType;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

import com.google.gwt.user.client.rpc.AsyncCallback;

@PersistenceCapable(identityType=IdentityType.APPLICATION)
public class Thesis implements Serializable {
	
	@PrimaryKey
	@Persistent(valueStrategy=IdGeneratorStrategy.IDENTITY)
	private Long id;
	@Persistent
	private String title="no title";
	@Persistent
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
	@Persistent 
	private String textAbstract = "no abstract";
	@Persistent
	private String tags = "no tags";
	
	// Need to define the Tags as "Unowned" child objects, 
	//  as they do not disappear when Thesis object is deleted.  
	//@Persistent
// @Unowned
	//private Tag tag;
		
	// GWT serializable Objects need a no=argument constructor
		public Thesis() {}

		public Thesis(String t, String a, String p, String y, String s, String c, String ta,
				         String url, String tgs){
			title = t;
			author = a;
			professor = p;
			year = y;
			semester = s;
			className = c;
			textAbstract = ta;
			URL = url;
			tags = tgs;
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
		
		public String getTextAbstract() {
			return textAbstract;
		}
		
		public void setTextAbstract(String textAbstract) {
			this.textAbstract = textAbstract;
		}
		public String getURL() {
			return URL;
		}

		public void setURL(String uRL) {
			URL = uRL;
		}

		public String getTags() {
			return tags;
		}
		
		public void setTags(String tgs) {
			tags = tgs;
		}
		
		public Long getID() {
			return id;
		}

	
		public boolean isInSearchForThesisEntry (String searchPhrase) {
			String tCheck = title.toLowerCase();
			String aCheck = author.toLowerCase();
			String pCheck = professor.toLowerCase();
			String yCheck = year.toLowerCase();
			String sCheck = semester.toLowerCase();
			String cCheck = className.toLowerCase();
			String taCheck = textAbstract.toLowerCase();
			String tagCheck = tags.toLowerCase();
			
			String[] checks = {tCheck, aCheck, pCheck, yCheck, sCheck, cCheck, taCheck, tagCheck};
			
			String compare = searchPhrase.toLowerCase();
			String[] comparisons = compare.split(" ");
			
			for (String word: comparisons) {
				boolean currentRun = false;
				for (String check: checks) if (check.contains(word)) {currentRun = true; break;}
				if (!currentRun) return false;
			}
			
			return true;
			
		}
	
	
}
