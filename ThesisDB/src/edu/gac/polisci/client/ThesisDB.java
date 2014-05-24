package edu.gac.polisci.client;

/*
 * Controller class for  app.
 * Handles communications between View (client side) and Model (server side) 
 */

import java.util.ArrayList;
import java.util.List;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.FormPanel;
import com.google.gwt.user.client.ui.VerticalPanel;

import edu.gac.polisci.server.ThesisDBModel;
import edu.gac.polisci.shared.Thesis;

public class ThesisDB implements EntryPoint {
	private final ThesisDBView thesisView = new ThesisDBView();
	// Create the RPC services for client-server communication
	private final clientModelServiceAsync clientModelService = GWT
			.create(clientModelService.class);
	private final BlobServiceAsync blobService = GWT
			.create(BlobService.class);

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
				else thesisView.setWindow("../ThesisDBLogin.html");;
			}
		});
	}
	public void handleSubmitForm(final FormPanel submitFormPanel) {
		blobService.getBlobStoreUploadUrl(
		  new AsyncCallback<String>() {
			public void onSuccess(String blobURL) {
				// Set the form action to the newly created
				// blobstore upload URL
				submitFormPanel.setAction(blobURL.toString());
				// *** Submit the form to complete the upload
				// This causes a doPost to sever from the HTML Form (FormPanel)
				submitFormPanel.submit();
				// This POST will be captured by SubmitpostHTTPServiceImpl.java
			}

			public void onFailure(Throwable caught) {
				thesisView.sendErrorMessage("Upload Failed");
			}
		});
	}
	
	public void handleSubmitEditForm(final Thesis thesis, final Thesis changedThesis) {
		//Submits an edited Thesis to the server
		clientModelService.submitEditPostToServer(thesis, changedThesis, 
				new AsyncCallback <Void>() {
			public void onFailure(Throwable caught) {
				thesisView.sendErrorMessage("Edit Failed");
			} 
			@Override
			public void onSuccess(Void v) {
				//View the main page on successful submission
				thesisView.viewWelcomePage();
			}
		});
	}
	
	public void viewSearchThesisDataFromServer(final FlowPanel fp, final VerticalPanel panel, String search, boolean isFeatured) {
		System.out.println(isFeatured);
		////Handles the request to get the search data based on user's input
		clientModelService.getSearchThesesDataFromServer(search, isFeatured,
				new AsyncCallback<List<Thesis>>() {
			public void onFailure(Throwable caught) {
				thesisView.sendErrorMessage("Search Failed");
			}
			
			@Override
			public void onSuccess(List<Thesis> data) {
				//When the search data is returned, display a new table with the search results
				fp.clear();
				thesisView.makeThesisTable(data, fp, panel);
			}
		});
	}
	
	public void viewFilterThesisDataFromServer(final FlowPanel fp, final VerticalPanel panel, 
			List<String> tagFilters, List<String> yearFilters, List<String> profFilters, List<String> classFilters, boolean isFeatured) {
		//Handles the request to the server to return filtered data after the user input
		clientModelService.getFilterThesesDataFromServer(tagFilters, yearFilters, profFilters, classFilters, isFeatured, 
				new AsyncCallback<List<Thesis>>() {
			public void onFailure(Throwable caught) {
				thesisView.sendErrorMessage("Filtering Failed");
			}
			@Override
			public void onSuccess(List<Thesis> data) {
				//On successful return of filtered data, the view is updated with the results
				fp.clear();
				thesisView.makeThesisTable(data, fp, panel);
			}
		});
	}
	
	public void viewThesisDataFromServer(final FlowPanel fp, final VerticalPanel panel, boolean isFeatured){
		//Handles the request to return all the data of currently persistent Theses in the datastore
		clientModelService.getThesesDataFromServer(isFeatured,
				new AsyncCallback<List<Thesis>>() {
					public void onFailure(Throwable caught) {
						return;
					}

					@Override
					public void onSuccess(List<Thesis> data) {
						//On Success, update the view with the results
						fp.clear();
						thesisView.makeThesisTable(data, fp, panel);
					}
				});
	}
	
	public void deleteThesisDataFromServer(Thesis thesis) {
		//Handle the request to delete a Thesis object from the server
		clientModelService.deleteThesisFromServer(thesis, 
				new AsyncCallback<String>() {
			public void onFailure(Throwable caught) {
				thesisView.sendErrorMessage("Delete Failed");
				return;
			}
			
			@Override
			public void onSuccess(String success) {
				//On success, refresh the main page
				thesisView.viewWelcomePage();
				return;
			}
		});
	}
	
	public void getTagFilterDataFromServer (final VerticalPanel filterDestination) {
		//Handles the request to return the list of tag filters
		clientModelService.getTagFilterListFromServer(
				new AsyncCallback<List<String>> () {
					public void onFailure (Throwable caught) {
						thesisView.sendErrorMessage("Failed to get tag values from server");
						return;
					}
					@Override
					public void onSuccess(List<String> tags) {
						//On success, add the tag filter to the view
						thesisView.addTagFilter(filterDestination, tags);
					}
				});
	}
	
	public void getYearFilterDataFromServer(final VerticalPanel filterDestination) {
		//Handles the request to return the list of year filters
		clientModelService.getYearFilterListFromServer(
				new AsyncCallback<List<String>> () {
					public void onFailure(Throwable caught) {
						thesisView.sendErrorMessage("Failed to get year values from server");
						return;
					}
					@Override
					public void onSuccess(List<String> years) {
						//On success, add the year filter to the view
						thesisView.addYearFilter(filterDestination, years);
					}
				});
	}

	public void getProfFilterDataFromServer(final VerticalPanel filterDestination) {
		//Handles the request to return the list of professor filters
		clientModelService.getProfFilterListFromServer(
				new AsyncCallback<List<String>> () {
					public void onFailure(Throwable caught) {
						thesisView.sendErrorMessage("Failed to get professor values from server");
						return;
					}
					@Override
					public void onSuccess(List<String> profs) {
						//On success, add the professor filter to the view
						thesisView.addProfessorFilter(filterDestination, profs);
					}
				});
	}
	
	public void getClassFilterDataFromServer(final VerticalPanel filterDestination) {
		//Handles the request to return the list of class filters
		clientModelService.getClassFilterListFromServer(
				new AsyncCallback<List<String>> () {
					public void onFailure(Throwable caught) {
						thesisView.sendErrorMessage("Failed to get class values from server");
						return;
					}
					@Override
					public void onSuccess(List<String> classes) {
						//On success, add the class filter to the view
						thesisView.addClassFilter(filterDestination, classes);
					}
				});
	}
	
	public void getProfDropDownDataFromServer(final VerticalPanel dropDownDestination) {
		//Handles the request to return the professor data for use in the submission/edit dropdown box 
		clientModelService.getProfFilterListFromServer(
				new AsyncCallback<List<String>> () {
					public void onFailure(Throwable caught) {
						thesisView.sendErrorMessage("Failed to get existing professors from server");
						return;
					}
					@Override
					public void onSuccess(List<String> profs) {
						//On success, add the dropdown box to the view
						thesisView.addProfDropDown(dropDownDestination, profs);
					}
				});
	}
	
	public void getClassDropDownDataFromServer(final VerticalPanel dropDownDestination) {
		//Handles the request to return the class data for the submission/edit dropdown box
		clientModelService.getClassFilterListFromServer(
				new AsyncCallback<List<String>> () {
					public void onFailure(Throwable caught) {
						thesisView.sendErrorMessage("Failed to get existing professors from server");
						return;
					}
					@Override
					public void onSuccess(List<String> classes) {
						//On success, add the class dropdown box to the view
						thesisView.addClassDropDown(dropDownDestination, classes);
					}
				});
	}
	
	public void handleLogOutRequest() {
		//Handles the request from the user to log out of the application
		clientModelService.getLogOutUrl(
				new AsyncCallback<String>() {
					public void onSuccess(String url) {
						// If logout was successful, we set the window
						//  to the logout url returned by clientModelService.
						//      (../ThesisDBLogout.html)
						thesisView.setWindow(url);
					}
					public void onFailure(Throwable caught) {
						thesisView.sendErrorMessage("Cannot find LogOut URL");
					}
				});
	}
	
	public void ifAdminShowButton(final Button button){
		//Handles the request to check if the signed in user is an administrator
		clientModelService.isUserAdmin(
				new AsyncCallback<Boolean>() {
					@Override
					public void onFailure(Throwable caught) {
					}

					@Override
					public void onSuccess(Boolean result) {
						//On success, display the delete and edit buttons
						thesisView.setButton(button, result);
					}
				});
	}
}
