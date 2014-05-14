package edu.gac.polisci.client;

import java.util.Arrays;
import java.util.List;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Command;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Anchor;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.CheckBox;
import com.google.gwt.user.client.ui.FileUpload;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.FormPanel;
import com.google.gwt.user.client.ui.FormPanel.SubmitCompleteEvent;
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
import com.google.gwt.user.client.ui.TextArea;
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
//		controller.getThesesFromServer();
		
		RootPanel rootPanel = RootPanel.get();
		rootPanel.clear();
				
		viewMainPage(rootPanel);
	}
	
//	public void viewThesesEntries (List<Thesis> theses) {	
//
//	}
	
	public void viewAddNewThesisPage() {
		RootPanel root = RootPanel.get();
		root.clear();
		
		HorizontalPanel horizontalPanel = new HorizontalPanel();
		root.add(horizontalPanel, 10, 79);
		
		VerticalPanel dataListPanel = new VerticalPanel();
		horizontalPanel.add(dataListPanel);
		
		FlowPanel flowPanel = new FlowPanel();
		dataListPanel.add(flowPanel);
		
		Label progTitlebar = new Label("New Entry");
		progTitlebar.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_CENTER);
		flowPanel.add(progTitlebar);
		
		FormPanel submitPanel = createAddNewThesisForm();
		flowPanel.add(submitPanel);
	}
	
	public FormPanel createAddNewThesisForm() {
				
				final Thesis thesis = new Thesis();
		
		
				final FormPanel submitFormPanel = new FormPanel();
				VerticalPanel thesisFormPanel = new VerticalPanel();
				submitFormPanel.add(thesisFormPanel);
				
		// Title TextBox
				HorizontalPanel titlePanel = new HorizontalPanel();
				Label titleLabel = new Label("Title");
				titleLabel.addStyleName("entryLabel");
				titlePanel.add(titleLabel);
				thesisFormPanel.add(titlePanel);
				final TextBox titleTextBox = new TextBox();
				thesisFormPanel.add(titleTextBox);
		
		// Author TextBox
				HorizontalPanel authorPanel = new HorizontalPanel();
				Label authorLabel = new Label("Author");
				authorLabel.addStyleName("entryLabel");
				authorPanel.add(authorLabel);
				thesisFormPanel.add(authorPanel);
				final TextBox authorTextBox = new TextBox();
				thesisFormPanel.add(authorTextBox);
				
		// Professor TextBox
				HorizontalPanel professorPanel = new HorizontalPanel();
				Label professorLabel = new Label("Professor");
				professorLabel.addStyleName("entryLabel");
				professorPanel.add(professorLabel);
				thesisFormPanel.add(professorPanel);
				final TextBox professorTextBox = new TextBox();
				thesisFormPanel.add(professorTextBox);
		
		// Year TextBox
				HorizontalPanel yearPanel = new HorizontalPanel();
				Label yearLabel = new Label("Year");
				yearLabel.addStyleName("entryLabel");
				yearPanel.add(yearLabel);
				thesisFormPanel.add(yearPanel);
				final TextBox yearTextBox = new TextBox();
				thesisFormPanel.add(yearTextBox);		
				
		// Semester TextBox
				HorizontalPanel semsesterPanel = new HorizontalPanel();
				Label semesterLabel = new Label("Semester");
				semesterLabel.addStyleName("entryLabel");
				semsesterPanel.add(semesterLabel);
				thesisFormPanel.add(semsesterPanel);
				final TextBox semesterTextBox = new TextBox();
				thesisFormPanel.add(semesterTextBox);

		// Class TextBox
				HorizontalPanel classPanel = new HorizontalPanel();
				Label classLabel = new Label("Class");
				classLabel.addStyleName("entryLabel");
				classPanel.add(classLabel);
				thesisFormPanel.add(classPanel);
				final TextBox classTextBox = new TextBox();
				thesisFormPanel.add(classTextBox);
				
				
		// Text Abstract TextArea
				HorizontalPanel abstractPanel = new HorizontalPanel();
				Label abstractLabel = new Label("Text Abstract");
				abstractLabel.addStyleName("entryLabel");
				abstractPanel.add(abstractLabel);
				thesisFormPanel.add(abstractPanel);
				final TextArea abstractTextArea = new TextArea();
				thesisFormPanel.add(abstractTextArea);
				
		// New widget for file upload
				HorizontalPanel fileRow = new HorizontalPanel();
				final FileUpload upload = new FileUpload();
				
				upload.setTitle("Upload a Thesis");
				fileRow.add(upload);
				thesisFormPanel.add(fileRow);
				
		HorizontalPanel submitPanel = new HorizontalPanel();
		Button submitButton = new Button("Submit Entry");
		
		// From Hvidsten's gustlist-gae
		// The submitFormPanel, when submitted, will trigger an HTTP call to the
		// servlet.  The following parameters must be set

		submitFormPanel.setEncoding(FormPanel.ENCODING_MULTIPART);
		submitFormPanel.setMethod(FormPanel.METHOD_POST);
		
		submitButton.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				titleTextBox.setName("title");
				authorTextBox.setName("author");
				professorTextBox.setName("professor");
				yearTextBox.setName("year");
				semesterTextBox.setName("semester");
				classTextBox.setName("class");
				abstractTextArea.setName("abstract");
				upload.setName("upload");
				
				controller.handleSubmitForm(submitFormPanel);
				
			
			}
		});
		submitPanel.add(submitButton);
		
		thesisFormPanel.add(submitPanel);
		
		// The doPost message itself is sent from the Form and not intercepted
		//  by GWT.  

		// This event handler is "fired" just after the Form causes a doPost 
		//  message to server
		submitFormPanel.addSubmitCompleteHandler(new FormPanel.SubmitCompleteHandler() {
				public void onSubmitComplete(SubmitCompleteEvent event) {
						if(thesis.getURL()=="No URL") {
							submitFormPanel.reset();
							titleTextBox.setFocus(true);
						}
						else viewWelcomePage();
					}

				

				});
		return submitFormPanel;
	}
	
	public void viewMainPage (RootPanel rp) {
		

		final VerticalPanel thesisPanel = new VerticalPanel();
		thesisPanel.setWidth("1000px");
		rp.add(thesisPanel);
		final FlowPanel thesisFlow = new FlowPanel();
		thesisPanel.add(thesisFlow);
		
		controller.viewThesisDataFromServer(thesisFlow, thesisPanel);	
		
		VerticalPanel allOptions = new VerticalPanel();
		//HorizontalPanel hp = new HorizontalPanel();
		//hp.add(allOptions);
		rp.add(allOptions, 1200, 140);
		
		//Search Panel
		
		Button addEntryButton = new Button ("Add Thesis");
		addEntryButton.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				viewAddNewThesisPage();
			}
		});
		
		allOptions.add(addEntryButton);
		
		HorizontalPanel searchPanel = new HorizontalPanel();
		final Label searchLabel = new Label ("Search:");
		final TextBox searchBox = new TextBox();
		searchPanel.add(searchLabel);
		searchPanel.add(searchBox);
		
		Button searchButton = new Button("Search");
		searchButton.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				if (searchBox.getText() == "") controller.viewThesisDataFromServer(thesisFlow, thesisPanel);
				else controller.viewSearchThesisDataFromServer(thesisFlow, thesisPanel, searchBox.getText());
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

	public void makeThesisTable (List<Thesis> theses, FlowPanel fp, VerticalPanel panel) {
		HorizontalPanel row = new HorizontalPanel();
		Label author = new Label("AUTHOR");
		Label title = new Label ("TITLE");
		Label year = new Label ("SEM/YEAR");
		Label professor = new Label ("PROFESSOR");
		Label className = new Label ("CLASS");
//		Label textAbstract = new Label ("ABSTRACT");
		
		row.add(title); title.setWidth("300px"); row.add(author); author.setWidth("200px"); 
		row.add(year); year.setWidth("100px"); row.add(professor); professor.setWidth("200px");
		row.add(className); className.setWidth("200px");// row.add(textAbstract); textAbstract.setWidth("300px");
		
		fp.add(row);
		
		if (theses != null){
			for (Thesis entry: theses) {
				HorizontalPanel thesisRow = makeThesisEntryRow(entry);
				fp.add(thesisRow);
			}
		}
		Label footer = new Label("COPYRIGHT HERE");
		footer.addStyleName("footer");
		footer.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_CENTER);
		fp.add(footer);
	}
	
	public HorizontalPanel makeThesisEntryRow(Thesis entry) {
		HorizontalPanel row = new HorizontalPanel();
		Label author = new Label(entry.getAuthor());
		author.addStyleName("entryLabel");
		Label title = new Label (entry.getTitle());
		Label year = new Label (entry.getSemester() + entry.getYear());
		Label professor = new Label (entry.getProfessor());
		Label className = new Label (entry.getClassName());
//		Label textAbstract = new Label (entry.getTextAbstract());
		Anchor link = new Anchor("Download PDF", entry.getURL());
		link.setTarget("_blank");
		
//		Button infoButton = new Button("Info"); 
//		infoButton.setText("Info");
//		infoButton.addClickHandler(new ClickHandler() {
//			@Override
//			public void onClick(ClickEvent event) {
//				
//			}
//		});
		
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
//		row.add(textAbstract);
//		textAbstract.setWidth("300px");
//		row.add(infoButton);
		row.add(link);
		
		return row;
	}
	
	public void setWindow(String url) {
		Window.Location.replace(url);
	}
	public void sendErrorMessage(String msg) {
		Window.alert(msg);  
	}

}
