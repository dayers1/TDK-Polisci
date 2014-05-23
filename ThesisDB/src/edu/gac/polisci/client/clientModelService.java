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
	public void submitEditPostToServer(Thesis thesis, Thesis changedThesis);
	List<Thesis> getThesesDataFromServer(boolean isFeatured);
	List<Thesis> getSearchThesesDataFromServer(String search, boolean isFeatured);
	String deleteThesisFromServer (Thesis thesis);
	List<String> getYearFilterListFromServer ();
	List<String> getTagFilterListFromServer ();
	List<String> getProfFilterListFromServer ();
	List<String> getClassFilterListFromServer ();
	List<Thesis> getFilterThesesDataFromServer(List<String> tagFilters, List<String> yearFilters,
			List<String> profFilters, List<String> classFilters, boolean isFeatured);
	public String getLogOutUrl();
	public Boolean isUserAdmin();

}
