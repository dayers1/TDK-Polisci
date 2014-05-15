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
		// TODO Auto-generated method stub
		return null;
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
	public List<Thesis> getFilterThesesDataFromServer(List<String> filters) {
		List<Thesis> returnToClient = new ArrayList<Thesis>();
		List<Thesis> theses = ThesisDBModel.getThesisData();
		for (Thesis thesis: theses) {
			for (String filter: filters) {
				if (thesis.isInSearchForThesisEntry(filter)) returnToClient.add(thesis);
			}
		}
		return returnToClient;
	}
		
}
