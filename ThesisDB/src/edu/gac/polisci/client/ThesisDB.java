package edu.gac.polisci.client;

/*
 * Controller class for  app.
 * Handles communications between View (client side) and Model (server side) 
 */

import java.util.List;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.FormPanel;

public class ThesisDB implements EntryPoint {
	private final ThesisDBView thesisView = new ThesisDBView();
	// Create the RPC services for client-server communication
	private final clientModelServiceAsync clientModelService = GWT
			.create(clientModelService.class);
	//private final BlobServiceAsync blobService = GWT
		//	.create(BlobService.class);

	// Needed to keep track of the URL that is loaded when the app first loads
	private String homeURL ="";

	// Entry point for web app 
	public void onModuleLoad() {
		// Wire up the View-Controller interface
		thesisView.setController(ThesisDB.this);
		// Store the url for where the app starts
		homeURL = Window.Location.getHref();

		// Tell the Model where the startup url is 
		// Note: Meat of implementation is in ClientModelServiceImpl.java
		clientModelService.setAppBaseURL(homeURL,
				new AsyncCallback<String>() {
					public void onFailure(Throwable caught) {
						return;
					}
					public void onSuccess(String result) {
					}
				});

		//  Ask Model is a user is logged in 
		clientModelService.isUserLoggedIn(
				new AsyncCallback<Boolean>() {
			public void onFailure(Throwable caught) {
				return;
			}

			public void onSuccess(Boolean result) {
				if(result){ // If user is logged in 
					// Show welcome page
					thesisView.viewWelcomePage();
				}// Otherwise, set page to login page
				else thesisView.setWindow("../ThesisDBLogin.html");
			}
		});
	}
}