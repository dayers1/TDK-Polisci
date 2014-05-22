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

	@Override
	public String submitThesisToServer(Thesis thesis) {
		ThesisDBModel.storeThesis(thesis);
		return null;
	}

	@Override
	public boolean isUserLoggedIn() {
		UserService userService = UserServiceFactory.getUserService();
		User user = userService.getCurrentUser();
		return new Boolean(user!=null);
	}

	@Override
	public String setAppBaseURL(String homeURL) {
		ThesisDBModel.setAppBaseURL(homeURL);
		return null;
	}

	@Override
	public List<Thesis> getThesesDataFromServer() {
		List <Thesis> theses = ThesisDBModel.getThesisData();
		return theses;
	}

	@Override
	public List<Thesis> getSearchThesesDataFromServer(String search) {
		List <Thesis> theses = ThesisDBModel.getThesisData();
		List <Thesis> thesesToReturn = new ArrayList<Thesis>();
		for (Thesis thesis: theses) {
			if (thesis.isInSearchForThesisEntry(search)) thesesToReturn.add(thesis);
		}
		return thesesToReturn;
	}

	@Override
	public String deleteThesisFromServer(Thesis thesis) {
		ThesisDBModel.deletePost(thesis);
		return "Entry Deleted";
	}
	
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

	@Override
	public List<Thesis> getFilterThesesDataFromServer(List<String> tagFilters, List<String> yearFilters,
			List<String> profFilters, List<String> classFilters) {
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
		return theses;
	}

	@Override
	public void submitEditPostToServer(Thesis thesis, Thesis changedThesis) {
		ThesisDBModel.updateEditedPost(thesis, changedThesis);
	}
	
	public String getLogOutUrl(){
		UserService userService = UserServiceFactory.getUserService();
		return userService.createLogoutURL("../ThesisDBLogout.html");
	}
	
	public Boolean isUserAdmin(){
		UserService userService = UserServiceFactory.getUserService();
		User user = userService.getCurrentUser();
		return user.getEmail().contentEquals("tdkpolsci@gmail.com");
	}
		
}
