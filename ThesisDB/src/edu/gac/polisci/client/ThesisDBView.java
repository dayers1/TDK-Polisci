package edu.gac.polisci.client;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Command;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Hyperlink;
import com.google.gwt.user.client.ui.MenuBar;
import com.google.gwt.user.client.ui.MenuItem;
import com.google.gwt.user.client.ui.MenuItemSeparator;
import com.google.gwt.user.client.ui.PopupPanel;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.VerticalPanel;

import edu.gac.polisci.*;

public class ThesisDBView {
	
	private ThesisDB control;
	// Popup panels need to be instantiated as final - just once
	final PopupPanel searchPopup = new PopupPanel(false);
	final PopupPanel mapPopup = new PopupPanel(false);

	public void viewWelcomePage() {
		RootPanel rootPanel = RootPanel.get();
		rootPanel.clear();
		makeMenuBar(rootPanel);
		
		HorizontalPanel horizontalPanel = new HorizontalPanel();
		rootPanel.add(horizontalPanel, 10, 79);
		horizontalPanel.setSize("412px", "211px");
		
		makeSideBar(horizontalPanel);
	}
	
	// Helper method to make row of menuss on top of View
		public void makeMenuBar(RootPanel rp){
			MenuBar menuBar = new MenuBar(false);
			rp.add(menuBar, 94, 39);
			
			MenuItem menuHomeItem = new MenuItem("Home", false, new Command() {
				public void execute() {
					viewWelcomePage();
				}
			});
			menuHomeItem.setHTML("Home");
			menuBar.addItem(menuHomeItem);
			menuBar.addSeparator(new MenuItemSeparator());
			
			//MenuItem menuSearchItem = new MenuItem("Search", false, new Command() {
				//public void execute() {
					//doPostSearch();
				//}
			//});
			//menuSearchItem.setHTML("Search");
			//menuBar.addItem(menuSearchItem);
			menuBar.addSeparator(new MenuItemSeparator());
			
			//MenuItem menuSignOutItem = new MenuItem("Log Out", false, new Command() {
				//public void execute() {
					// control.handleSignOutRequest();
				//}
			//});
			//menuSignOutItem.setHTML("Log Out");
			//menuBar.addItem(menuSignOutItem);
			menuBar.addSeparator(new MenuItemSeparator());
			
			MenuItem menuContactItem = new MenuItem("Contact", false, (Command) null);
			menuContactItem.setHTML("Contact");
			menuBar.addItem(menuContactItem);
			menuBar.addSeparator(new MenuItemSeparator());
			
			MenuItem menuHelpItem = new MenuItem("Help", false, (Command) null);
			menuHelpItem.setHTML("Help");
			menuBar.addItem(menuHelpItem);
		}

		public void makeSideBar(HorizontalPanel hp){
			VerticalPanel sidePanel = new VerticalPanel();
			hp.add(sidePanel);
			sidePanel.setSize("72px", "98px");
			
			Button postAdButton = new Button("Post Ad");
			postAdButton.setStyleName("sideBarButton");
			postAdButton.setText("Post Ad");
			//add a clickListener to the button
			//postAdButton.addClickHandler(new ClickHandler() {
				//@Override
				//public void onClick(ClickEvent event) {
				//	viewPostAdForm();
			//	}
		    //  });
			sidePanel.add(postAdButton);
			
			Button viewAdsButton = new Button("View Ads");
			viewAdsButton.setStyleName("sideBarButton");
			viewAdsButton.setText("View Ads");
			//add a clickListener to the button
		//	viewAdsButton.addClickHandler(new ClickHandler() {
			//	@Override
			//	public void onClick(ClickEvent event) {
			//		control.viewAdDataFromServer();
			//	}
		    //  });
			sidePanel.add(viewAdsButton);
			
			Hyperlink adminHyperlink = new Hyperlink("Admin Page", false, "newHistoryToken");
			sidePanel.add(adminHyperlink);
			
		}

	public void setWindow(String url) {
		Window.Location.replace(url);
	}

	public void setController(ThesisDB thesisDB) {
		control = thesisDB;
		
	}

}
