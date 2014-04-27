package edu.gac.polisci.client;

import com.google.gwt.user.client.rpc.AsyncCallback;

import edu.gac.polisci.shared.Thesis;

public interface clientModelServiceAsync {

	void setAppBaseURL(String homeURL, AsyncCallback<String> asyncCallback);
	void isUserLoggedIn(AsyncCallback<Boolean> asyncCallback);
	void submitThesisToServer(Thesis thesis, AsyncCallback<String> callback);

}
