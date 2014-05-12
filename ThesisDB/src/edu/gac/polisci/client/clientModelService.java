package edu.gac.polisci.client;

import java.util.List;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

import edu.gac.polisci.shared.Thesis;

@RemoteServiceRelativePath("clientmodelservice")
public interface clientModelService extends RemoteService {

	public String submitThesisToServer(Thesis thesis);
	boolean isUserLoggedIn();
	String setAppBaseURL(String homeURL);
	List<Thesis> getThesesDataFromServer();

}
