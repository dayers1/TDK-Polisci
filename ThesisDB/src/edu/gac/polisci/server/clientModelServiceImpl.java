package edu.gac.polisci.server;

import java.util.ArrayList;
import java.util.List;

import com.google.appengine.api.users.User;
import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;

import edu.gac.polisci.client.*;
import edu.gac.polisci.shared.Thesis;

@SuppressWarnings("serial")
public class clientModelServiceImpl extends RemoteServiceServlet implements
									clientModelService {

	//Calls on the model to store a Thesis that is submitted from the sunmission page
	@Override
	public String submitThesisToServer(Thesis thesis) {
		ThesisDBModel.storeThesis(thesis);
		return null;
	}

	//Performs the check to see whether or not a user is logged in.
	@Override
	public boolean isUserLoggedIn() {
		UserService userService = UserServiceFactory.getUserService();
		User user = userService.getCurrentUser();
		return new Boolean(user!=null);
	
	//Returns the list of currently persistent Theses from the server
	@Override
	public List<Thesis> getThesesDataFromServer(boolean isFeatured) {
		int i = 0;
		List <Thesis> theses = ThesisDBModel.getThesisData();
		List <Thesis> thesesToReturn = new ArrayList<Thesis>();
		for (Thesis thesis: theses) {
			if (isFeatured(thesis.getTitle())) {
				thesesToReturn.add(i,thesis);
				i++;
			}
			else {
				thesesToReturn.add(thesis);
			}
			}
		List<Thesis> result = new ArrayList<Thesis>();
		if (isFeatured) {
			for (Thesis thesis: thesesToReturn) {
				if (thesis.getFeatured()) result.add(thesis);
			}
		} else {result = thesesToReturn;}
		return result;
	}

	//Returns the list of currently persistent Theses that match user input search data
	@Override
	public List<Thesis> getSearchThesesDataFromServer(String search, boolean isFeatured) {
		List <Thesis> theses = ThesisDBModel.getThesisData();
		List <Thesis> thesesToReturn = new ArrayList<Thesis>();
		for (Thesis thesis: theses) {
			if (thesis.isInSearchForThesisEntry(search)) thesesToReturn.add(thesis);
		}
		List<Thesis> result = new ArrayList<Thesis>();
		if (isFeatured) {
			for (Thesis thesis: thesesToReturn) {
				if (thesis.getFeatured()) result.add(thesis);
			}
		} else {result = thesesToReturn;}
		return result;
	}

	//Calls on the model to delete the respective Thesis object from the server
	@Override
	public String deleteThesisFromServer(Thesis thesis) {
		ThesisDBModel.deletePost(thesis);
		return "Entry Deleted";
	}
	
	//Returns the list of year filter data from the server
	public List<String> getYearFilterListFromServer() {
		List <String> years = new ArrayList<String>();
		List <Thesis> theses = ThesisDBModel.getThesisData();
		for (Thesis thesis: theses) {
			String year = thesis.getYear();
			if (!years.contains(year)) {
				years.add(year);
			}
		}
		return years;
	}


	//Returns the list of tag filter data from the server
	@Override
	public List<String> getTagFilterListFromServer() {
		List <String> tags = new ArrayList<String>();
		List <Thesis> theses = ThesisDBModel.getThesisData();
		for (Thesis thesis: theses) {
			String[] tagList = thesis.getTags().split(",");
			for (String tag: tagList){
				tag = tag.trim();
				if (!tags.contains(tag)) {
					tags.add(tag);
				}
			}
		}
		return tags;
	}

	//Returns the list of professors from the server
	@Override
	public List<String> getProfFilterListFromServer() {
		List <String> profs = new ArrayList<String>();
		List <Thesis> theses = ThesisDBModel.getThesisData();
		for (Thesis thesis: theses) {
			String prof = thesis.getProfessor();
			if (!profs.contains(prof)) {
				profs.add(prof);
			}
		}
		return profs;
	}

	//Returns the list of classes from the server
	@Override
	public List<String> getClassFilterListFromServer() {
		List <String> classes = new ArrayList<String>();
		List <Thesis> theses = ThesisDBModel.getThesisData();
		for (Thesis thesis: theses) {
			String className = thesis.getClassName();
			if (!classes.contains(className)) {
				classes.add(className);
			}
		}
		return classes;
	}

	//Returns the list of filtered Thesis object data from the server
	@Override
	public List<Thesis> getFilterThesesDataFromServer(List<String> tagFilters, List<String> yearFilters,
			List<String> profFilters, List<String> classFilters, boolean isFeatured) {
		List<Thesis> temp = new ArrayList<Thesis>();
		List<Thesis> theses = ThesisDBModel.getThesisData();
		
		if (!tagFilters.isEmpty()) {
			for (Thesis thesis: theses) {
				for (String filter: tagFilters) {
					if (thesis.getTags().toLowerCase().contains(filter.toLowerCase()) && !temp.contains(thesis)) temp.add(thesis);
				}
			}
			theses = temp;
			temp = new ArrayList<Thesis>();
		}
		if (!yearFilters.isEmpty()){
			for (Thesis thesis: theses) {
				for (String filter: yearFilters) {
					if (thesis.getYear().toLowerCase().contains(filter.toLowerCase()) && !temp.contains(thesis)) temp.add(thesis);
				}
			}
			theses = temp;
			temp = new ArrayList<Thesis>();
		}
		if (!profFilters.isEmpty()) {
			for (Thesis thesis: theses) {
				for (String filter: profFilters) {
					if (thesis.getProfessor().toLowerCase().contains(filter.toLowerCase()) && !temp.contains(thesis)) temp.add(thesis);
				}
			}
			theses = temp;
			temp = new ArrayList<Thesis>();
		}
		if (!classFilters.isEmpty()) {
			for (Thesis thesis: theses) {
				for (String filter: classFilters) {
					if (thesis.getClassName().toLowerCase().contains(filter.toLowerCase()) && !temp.contains(thesis)) temp.add(thesis);
				}
			}
			theses = temp;
		}
		List<Thesis> result = new ArrayList<Thesis>();
		if (isFeatured) {
			for (Thesis thesis: theses) {
				if (thesis.getFeatured()) result.add(thesis);
			}
		} else {result = theses;}
		return result;
	}

	//Submits the edited Thesis from the edit thesis page
	@Override
	public void submitEditPostToServer(Thesis thesis, Thesis changedThesis) {
		ThesisDBModel.updateEditedPost(thesis, changedThesis);
	}
	
	//Gets the logout URL for use by the logout button
	public String getLogOutUrl(){
		UserService userService = UserServiceFactory.getUserService();
		return userService.createLogoutURL("../ThesisDBLogout.html");
	}
	
	//Performs a check to see if the currently logged in user is an admin
	public Boolean isUserAdmin(){
		UserService userService = UserServiceFactory.getUserService();
		User user = userService.getCurrentUser();
		return user.getEmail().contentEquals("tdkpolsci@gmail.com");
	}
	
	//Returns whether or not the respective thesis is a featured thesis
	public boolean isFeatured(final String title) {
		final char c = title.charAt(0);
		return (c == '*');
		
	}

		
}
