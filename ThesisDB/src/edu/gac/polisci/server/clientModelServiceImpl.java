package edu.gac.polisci.server;

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
	public List<Thesis> getThesesFromServer() {
		ThesisDBModel.getThesisData();
		return null;
	}

		
}
