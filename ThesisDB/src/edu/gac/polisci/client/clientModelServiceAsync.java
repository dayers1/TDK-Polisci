package edu.gac.polisci.client;

import java.util.List;

import com.google.gwt.user.client.rpc.AsyncCallback;

import edu.gac.polisci.shared.Thesis;

public interface clientModelServiceAsync {

	void setAppBaseURL(String homeURL, AsyncCallback<String> asyncCallback);
	void isUserLoggedIn(AsyncCallback<Boolean> asyncCallback);
	void submitThesisToServer(Thesis thesis, AsyncCallback<String> callback);
	void getThesesDataFromServer(AsyncCallback<List<Thesis>> callback);
	void getSearchThesesDataFromServer(String search, AsyncCallback<List<Thesis>> callback);
	void deleteThesisFromServer(Thesis thesis, AsyncCallback<String> callback);
	void getYearFilterListFromServer(AsyncCallback<List<String>> callback);
	void getProfFilterListFromServer(AsyncCallback<List<String>> callback);
	void getClassFilterListFromServer(AsyncCallback<List<String>> callback);
	void getTagFilterListFromServer(AsyncCallback<List<String>> callback);
	void getFilterThesesDataFromServer(List<String> tagFilters, List<String> yearFilters,
			List<String> profFilters, List<String> classFilters,AsyncCallback<List<Thesis>> callback);
	void submitEditPostToServer(Thesis thesis, Thesis chengedThesis, AsyncCallback<Void> callback);
	void getLogOutUrl(AsyncCallback<String> asyncCallback);
	void isUserAdmin(AsyncCallback<Boolean> callback);
}
