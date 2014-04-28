package edu.gac.polisci.client;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Command;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.CheckBox;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.MenuBar;
import com.google.gwt.user.client.ui.MenuItem;
import com.google.gwt.user.client.ui.MenuItemSeparator;
import com.google.gwt.user.client.ui.PopupPanel;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.ScrollPanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;

public class ThesisDBView {

	private ThesisDB controller;
	
	public ThesisDB getController () {
		return controller;
	}
	
	public void setController (ThesisDB controller) {
		this.controller = controller;
	}
	
	public void viewWelcomePage(){
		RootPanel rootPanel = RootPanel.get();
		rootPanel.clear();
		
		HorizontalPanel horizontalPanel = new HorizontalPanel();
		rootPanel.add(horizontalPanel, 10, 79);
		horizontalPanel.setSize("1000px", "211px");
		
			
		
		makeFilterBar(rootPanel);
	}
	
	public void makeFilterBar (RootPanel rp) {
//		MenuBar menuBar = new MenuBar(false);
//		rp.add(menuBar, 0, 39);
//		menuBar.setSize("1000px", "60px");	
		
		VerticalPanel allOptions = new VerticalPanel ();
		
		//Search Panel
		
		HorizontalPanel searchPanel = new HorizontalPanel ();
		Label searchLabel = new Label ("Search:");
		final TextBox searchBox = new TextBox();
		searchPanel.add(searchLabel);
		searchPanel.add(searchBox);
		
		Button searchButton = new Button("Search");
		searchButton.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
//				controller.searchThesisEntries(searchBox.getText());
			}
	      });
		
		searchPanel.add(new Label (""));
		searchPanel.add(searchButton);
		
		allOptions.add(searchPanel);
		
		//Tag Filter
		
		VerticalPanel tagPanel = new VerticalPanel();
		ScrollPanel tagFilter = new ScrollPanel();
		
		//
		// Will need persistence for tags, and for loop to add them all here
		//
		CheckBox option = new CheckBox ("option");
		if (option.getValue()) {
//			System.out.println(option.getText());
		}
	
		tagFilter.add(option);
		
		Button tagFilterBtn = new Button ("Filter Tags");
		tagFilterBtn.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				//
				//
				//
			}
		});
		tagPanel.add(tagFilter);
		tagPanel.add(tagFilterBtn);
		
		allOptions.add(tagPanel);
		
		//Year Filter
		
		VerticalPanel yearPanel = new VerticalPanel();
		ScrollPanel yearFilter = new ScrollPanel();
		
		//
		// Will need persistence for tage, and for loop to add them all here
		//
		CheckBox yearoption = new CheckBox ("option");
		tagFilter.add(yearoption);
		
		Button yearFilterBtn = new Button ("Filter Year");
		yearFilterBtn.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				//
				//
				//
			}
		});
		yearPanel.add(yearFilter);
		yearPanel.add(yearFilterBtn);
		
		allOptions.add(yearPanel);
		
		//Class Filter
		
		VerticalPanel classPanel = new VerticalPanel();
		ScrollPanel classFilter = new ScrollPanel();
		
		//
		// Will need persistence for tage, and for loop to add them all here
		//
		CheckBox classoption = new CheckBox ("option");
		tagFilter.add(classoption);
		
		Button classFilterBtn = new Button ("Filter Class");
		classFilterBtn.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				//
				//
				//
			}
		});
		classPanel.add(classFilter);
		classPanel.add(classFilterBtn);
		
		allOptions.add(classPanel);
				
		rp.add(allOptions);
	}
}
