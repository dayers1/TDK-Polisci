package edu.gac.polisci.client;

import java.util.Arrays;
import java.util.List;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Command;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.CheckBox;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.HTML;
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

import edu.gac.polisci.shared.Thesis;

public class ThesisDBView {

	private ThesisDB controller;
	
	public ThesisDB getController () {
		return controller;
	}
	
	public void setController (ThesisDB controller) {
		this.controller = controller;
	}
	
	//
	//May change a few things here, such as method arrangement and arguments
	//
	
	public void viewWelcomePage(){
		RootPanel rootPanel = RootPanel.get();
		rootPanel.clear();
		
		Thesis test = new Thesis("Title","Author","Professor","Year","Semester", "Class Name","URL");
		Thesis test2 = new Thesis("The great Escape","Kevin Dexter","Choong-Soo","2014","SP","Object-Oriented Software Development","lololol");

		List<Thesis> thesis = Arrays.asList(test, test2);
		//thesis.add(test);
		
//		VerticalPanel verticalPanel = new VerticalPanel();
//		verticalPanel.setSize("200px", "200px");
//		rootPanel.add(verticalPanel);
//		
		
		VerticalPanel thesisPanel = new VerticalPanel();
		thesisPanel.setWidth("1000px");
		rootPanel.add(thesisPanel);
		FlowPanel thesisFlow = new FlowPanel();
		thesisPanel.add(thesisFlow);
		
		
		makeThesisTable(thesis, thesisFlow, thesisPanel);	
		
		makeFilterBar(rootPanel);
	}
	
	public void makeFilterBar (RootPanel rp) {
//		MenuBar menuBar = new MenuBar(false);
//		rp.add(menuBar, 0, 39);
//		menuBar.setSize("1000px", "60px");	
		
		VerticalPanel allOptions = new VerticalPanel();
		//HorizontalPanel hp = new HorizontalPanel();
		//hp.add(allOptions);
		rp.add(allOptions, 1200, 140);
		
		//Search Panel
		
		HorizontalPanel searchPanel = new HorizontalPanel();
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
		
		searchPanel.add(searchButton);
		allOptions.add(searchPanel);
		
		//Tag Filter
		// Will need persistence for tags, and for loop to add them all here
		//
		VerticalPanel tagPanel = new VerticalPanel();
		
		String[] tagTest = {"War","Comp Sci","Drugs","Unicorn","Virus","Gravity","Large Unknown Flying Objects","Pokemon"};
		for (int i = 0; i < tagTest.length; i++){
			CheckBox option = new CheckBox (tagTest[i]);
			tagPanel.add(option);
		}

		ScrollPanel tagFilterScrollPanel = new ScrollPanel(tagPanel);
		tagFilterScrollPanel.setSize("200px", "100px");
		
		allOptions.add(tagFilterScrollPanel);
		

		Button tagFilterBtn = new Button ("Filter Tags");
		tagFilterBtn.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				//
				//
				//
			}
		});
		allOptions.add(tagFilterBtn);
		
		
		//Year Filter
		
		VerticalPanel yearPanel = new VerticalPanel();
		
		//
		// Will need persistence for tags, and for loop to add them all here
		//
		int[] yearTest = {2014, 2013, 2012, 2011, 2010, 2009, 2008, 2007, 2006, 2005, 2004, 2003, 2002, 2001, 2000,
				1999, 1998, 1997, 1996, 1995, 1994, 1993, 1992, 1991, 1990};
		for (int i = 0; i < yearTest.length; i++){
			CheckBox option = new CheckBox (String.valueOf(yearTest[i]));
			yearPanel.add(option);
		}
		
		ScrollPanel yearFilter = new ScrollPanel(yearPanel);
		yearFilter.setSize("200px", "100px");
		
		Button yearFilterBtn = new Button ("Filter Year");
		yearFilterBtn.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				//
				//
				//
			}
		});
		
		
		
		allOptions.add(yearFilter);
		allOptions.add(yearFilterBtn);
		
		//Author Filter
		
		VerticalPanel authorPanel = new VerticalPanel();
		
		//
		// Will need persistence for tage, and for loop to add them all here
		//
		String[] authorTest = {"Kevin Dexter", "Todd Ruble", "David Ayers", "Tom Neuman", "Bill Gates"};
		for (int i = 0; i < authorTest.length; i++){
			CheckBox option = new CheckBox (String.valueOf(authorTest[i]));
			authorPanel.add(option);
		}
		
		ScrollPanel authorFilter = new ScrollPanel(authorPanel);
		authorFilter.setSize("200px", "100px");
		
		Button authorFilterBtn = new Button ("Filter Author");
		authorFilterBtn.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				//
				//
				//
			}
		});
		
		allOptions.add(authorFilter);
		allOptions.add(authorFilterBtn);
		
	}

	public void makeThesisTable (List<Thesis> thesis, FlowPanel fp, VerticalPanel panel) {
		HorizontalPanel row = new HorizontalPanel();
		Label author = new Label("AUTHOR");
		Label title = new Label ("TITLE");
		Label year = new Label ("SEM/YEAR");
		Label professor = new Label ("PROFESSOR");
		Label className = new Label ("CLASS");
		
		row.add(title); title.setWidth("300px"); row.add(author); author.setWidth("200px"); 
		row.add(year); year.setWidth("100px"); row.add(professor); professor.setWidth("200px");
		row.add(className); className.setWidth("200px");
		
		fp.add(row);
		
		for (Thesis entry: thesis) {
			HorizontalPanel thesisRow = makeThesisEntryRow(entry);
			fp.add(thesisRow);
		}
		Label footer = new Label("COPYRIGHT HERE");
		footer.addStyleName("footer");
		footer.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_CENTER);
		panel.add(footer);
	}
	
	public HorizontalPanel makeThesisEntryRow(Thesis entry) {
		HorizontalPanel row = new HorizontalPanel();
		Label author = new Label(entry.getAuthor());
		author.addStyleName("entryLabel");
		Label title = new Label (entry.getTitle());
		Label year = new Label (entry.getSemester() + entry.getYear());
		Label professor = new Label (entry.getProfessor());
		Label className = new Label (entry.getClassName());
		
		Button infoButton = new Button("Info"); 
		infoButton.setText("Info");
		infoButton.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				
			}
		});
		
		row.add(title);
		title.setWidth("300px");
		row.add(author);
		author.setWidth("200px");
		row.add(year);
		year.setWidth("100px");
		row.add(professor);
		professor.setWidth("200px");
		row.add(className);
		className.setWidth("200px");
		row.add(infoButton);
		
		return row;
	}
	
	public void setWindow(String url) {
		Window.Location.replace(url);
	}

}
