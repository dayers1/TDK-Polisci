package edu.gac.polisci.client;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

import com.google.gwt.cell.client.ActionCell;
import com.google.gwt.cell.client.ButtonCell;
import com.google.gwt.cell.client.ClickableTextCell;
import com.google.gwt.cell.client.FieldUpdater;
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.cellview.client.CellTable;
import com.google.gwt.user.cellview.client.Column;
import com.google.gwt.user.cellview.client.ColumnSortEvent.ListHandler;
import com.google.gwt.user.cellview.client.TextColumn;
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
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.view.client.ListDataProvider;

import edu.gac.polisci.shared.Thesis;

public class ThesisDBView {

	private ThesisDB controller;
	final PopupPanel moreInfoPopup = new PopupPanel(false);
	
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
		root.add(horizontalPanel,450, 410);
		
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
	
	public void viewEditThesisPage(Thesis entry) {
		RootPanel root = RootPanel.get();
		root.clear();
		
		HorizontalPanel horizontalPanel = new HorizontalPanel();
		root.add(horizontalPanel, 450, 410);
		
		VerticalPanel dataListPanel = new VerticalPanel();
		horizontalPanel.add(dataListPanel);
		
		FlowPanel flowPanel = new FlowPanel();
		dataListPanel.add(flowPanel);
		
		Label progTitlebar = new Label("New Entry");
		progTitlebar.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_CENTER);
		flowPanel.add(progTitlebar);
		
		FormPanel submitPanel = createEditThesisForm(entry);
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
				final ToggleButton semesterToggle = new ToggleButton("Spring", "Fall");
				semesterToggle.setWidth("50px");
				semesterTextBox.setVisible(false);
				thesisFormPanel.add(semesterTextBox);
				thesisFormPanel.add(semesterToggle);

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
				
		// Existing Tags ScrollBox
				HorizontalPanel eTagsPanel = new HorizontalPanel();
				Label eTagsLabel = new Label("");
				eTagsLabel.addStyleName("entryLabel");
				eTagsPanel.add(eTagsLabel);
				thesisFormPanel.add(eTagsPanel);
				final VerticalPanel eTagsVertical = new VerticalPanel ();
				controller.getTagFilterDataFromServer(eTagsVertical);
				thesisFormPanel.add(eTagsVertical);
						
		// Tags TextArea
				HorizontalPanel tagsPanel = new HorizontalPanel();
				Label tagsLabel = new Label("Create New Tags");
				tagsLabel.addStyleName("entryLabel");
				tagsPanel.add(tagsLabel);
				thesisFormPanel.add(tagsPanel);
				final TextArea tagsTextArea = new TextArea();
				thesisFormPanel.add(tagsTextArea);
				
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
				semesterTextBox.setText(semesterToggle.getText());
				if (titleTextBox.getText().trim().isEmpty() || authorTextBox.getText().trim().isEmpty() 
						|| professorTextBox.getText().trim().isEmpty() || yearTextBox.getText().trim().isEmpty() 
						|| semesterTextBox.getText().trim().isEmpty() || classTextBox.getText().trim().isEmpty() 
						|| abstractTextArea.getText().trim().isEmpty() ) {
					sendErrorMessage("Please fill out all boxes");
				} else if (upload.getFilename().length() == 0) {
					sendErrorMessage("Please submit a pdf");
				} else if (!(semesterTextBox.getText().equals("Spring") || semesterTextBox.getText().equals("Fall"))) {
					sendErrorMessage("Semester must be either 'Spring' or 'Fall'");
				} else if (yearTextBox.getText().length() != 4) {
					sendErrorMessage("Year must be a 4 digit number");
				}
				else {
					try {int i = Integer.parseInt(yearTextBox.getText());}
					catch (NumberFormatException nfe) {sendErrorMessage("Year must be a 4 digit number"); return;}
					String tagFilters = "";
					for (Widget widget: eTagsVertical) {
						try {
							ScrollPanel scroll = (ScrollPanel) widget;
							VerticalPanel vert = (VerticalPanel) scroll.getWidget();
							for (Widget check: vert) {
								try {
								CheckBox tag = (CheckBox) check;
								if (tag.getValue()) {
									System.out.println(tag.getText());
									tagsTextArea.setText(tagsTextArea.getText() + " " + tag.getText());
								}
								} catch (ClassCastException cee) {}
							}
						} catch (ClassCastException cce) {}
					}
					
					if (tagsTextArea.getText().trim().isEmpty()) {sendErrorMessage("Please mark or add one or more tags"); return;}
					
					titleTextBox.setName("title");
					authorTextBox.setName("author");
					professorTextBox.setName("professor");
					yearTextBox.setName("year");
					semesterTextBox.setName("semester");
					classTextBox.setName("class");
					tagsTextArea.setName("tags");
					abstractTextArea.setName("abstract");
					upload.setName("upload");
					
					controller.handleSubmitForm(submitFormPanel);
				}
			
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
		
		Button cancel = new Button ("Cancel");
		cancel.addClickHandler(new ClickHandler () {
			@Override
			public void onClick(ClickEvent event) {
				viewWelcomePage();
			}
		});
		thesisFormPanel.add(cancel);
		
		return submitFormPanel;
	}
	
	public FormPanel createEditThesisForm (final Thesis thesis) {
			
			final FormPanel submitEditFormPanel = new FormPanel();
			VerticalPanel thesisEditFormPanel = new VerticalPanel();
			submitEditFormPanel.add(thesisEditFormPanel);
			
	// Title TextBox
			HorizontalPanel titlePanel = new HorizontalPanel();
			Label titleLabel = new Label("Title");
			titleLabel.addStyleName("entryLabel");
			titlePanel.add(titleLabel);
			thesisEditFormPanel.add(titlePanel);
			final TextBox titleTextBox = new TextBox();
			titleTextBox.setText(thesis.getTitle());
			thesisEditFormPanel.add(titleTextBox);
	
	// Author TextBox
			HorizontalPanel authorPanel = new HorizontalPanel();
			Label authorLabel = new Label("Author");
			authorLabel.addStyleName("entryLabel");
			authorPanel.add(authorLabel);
			thesisEditFormPanel.add(authorPanel);
			final TextBox authorTextBox = new TextBox();
			authorTextBox.setText(thesis.getAuthor());
			thesisEditFormPanel.add(authorTextBox);
			
	// Professor TextBox
			HorizontalPanel professorPanel = new HorizontalPanel();
			Label professorLabel = new Label("Professor");
			professorLabel.addStyleName("entryLabel");
			professorPanel.add(professorLabel);
			thesisEditFormPanel.add(professorPanel);
			final TextBox professorTextBox = new TextBox();
			professorTextBox.setText(thesis.getProfessor());
			thesisEditFormPanel.add(professorTextBox);
	
	// Year TextBox
			HorizontalPanel yearPanel = new HorizontalPanel();
			Label yearLabel = new Label("Year");
			yearLabel.addStyleName("entryLabel");
			yearPanel.add(yearLabel);
			thesisEditFormPanel.add(yearPanel);
			final TextBox yearTextBox = new TextBox();
			yearTextBox.setText(thesis.getYear());
			thesisEditFormPanel.add(yearTextBox);		
			
	// Semester TextBox
			HorizontalPanel semsesterPanel = new HorizontalPanel();
			Label semesterLabel = new Label("Semester");
			semesterLabel.addStyleName("entryLabel");
			semsesterPanel.add(semesterLabel);
			thesisEditFormPanel.add(semsesterPanel);
			final TextBox semesterTextBox = new TextBox();
			semesterTextBox.setText(thesis.getSemester());
			final ToggleButton semesterToggle = new ToggleButton("Spring", "Fall");
			semesterToggle.setWidth("50px");
			if (thesis.getSemester().contains("Fall")) {
				semesterToggle.setDown(true);
			}
			semesterTextBox.setVisible(false);
			thesisEditFormPanel.add(semesterTextBox);
			thesisEditFormPanel.add(semesterToggle);
	
	// Class TextBox
			HorizontalPanel classPanel = new HorizontalPanel();
			Label classLabel = new Label("Class");
			classLabel.addStyleName("entryLabel");
			classPanel.add(classLabel);
			thesisEditFormPanel.add(classPanel);
			final TextBox classTextBox = new TextBox();
			classTextBox.setText(thesis.getClassName());
			thesisEditFormPanel.add(classTextBox);
			
			
	// Text Abstract TextArea
			HorizontalPanel abstractPanel = new HorizontalPanel();
			Label abstractLabel = new Label("Text Abstract");
			abstractLabel.addStyleName("entryLabel");
			abstractPanel.add(abstractLabel);
			thesisEditFormPanel.add(abstractPanel);
			final TextArea abstractTextArea = new TextArea();
			abstractTextArea.setText(thesis.getTextAbstract());
			thesisEditFormPanel.add(abstractTextArea);
			
	// Existing Tags ScrollBox
			HorizontalPanel eTagsPanel = new HorizontalPanel();
			Label eTagsLabel = new Label("");
			eTagsLabel.addStyleName("entryLabel");
			eTagsPanel.add(eTagsLabel);
			thesisEditFormPanel.add(eTagsPanel);
			final VerticalPanel eTagsVertical = new VerticalPanel ();
			controller.getTagFilterDataFromServer(eTagsVertical);
			thesisEditFormPanel.add(eTagsVertical);
			
	// Tags TextArea
			HorizontalPanel tagsPanel = new HorizontalPanel();
			Label tagsLabel = new Label("Create New Tags");
			tagsLabel.addStyleName("entryLabel");
			tagsPanel.add(tagsLabel);
			thesisEditFormPanel.add(tagsPanel);
			final TextArea tagsTextArea = new TextArea();
			tagsTextArea.setText(thesis.getTags());
			thesisEditFormPanel.add(tagsTextArea);
			
	// Can only view original PDF
			Anchor link = new Anchor("Download PDF", thesis.getURL());
			link.setTarget("_blank");
			thesisEditFormPanel.add(link);
			
	HorizontalPanel submitPanel = new HorizontalPanel();
	Button submitButton = new Button("Submit Entry");
	
	// From Hvidsten's gustlist-gae
	// The submitFormPanel, when submitted, will trigger an HTTP call to the
	// servlet.  The following parameters must be set
	
	submitEditFormPanel.setEncoding(FormPanel.ENCODING_MULTIPART);
	submitEditFormPanel.setMethod(FormPanel.METHOD_POST);
	
	submitButton.addClickHandler(new ClickHandler() {
		@Override
		public void onClick(ClickEvent event) {
			semesterTextBox.setText(semesterToggle.getText());
			if (titleTextBox.getText().trim().isEmpty() || authorTextBox.getText().trim().isEmpty() 
					|| professorTextBox.getText().trim().isEmpty() || yearTextBox.getText().trim().isEmpty() 
					|| semesterTextBox.getText().trim().isEmpty() || classTextBox.getText().trim().isEmpty() 
					|| abstractTextArea.getText().trim().isEmpty() ) {
				sendErrorMessage("Please fill out all boxes");
			} else if (!(semesterTextBox.getText().equals("Spring") || semesterTextBox.getText().equals("Fall"))) {
				sendErrorMessage("Semester must be either 'Spring' or 'Fall'");
			} else if (yearTextBox.getText().length() != 4) {
				sendErrorMessage("Year must be a 4 digit number");
			}
			else {
				try {int i = Integer.parseInt(yearTextBox.getText());}
				catch (NumberFormatException nfe) {sendErrorMessage("Year must be a 4 digit number"); return;}
				String tagFilters = "";
				for (Widget widget: eTagsVertical) {
					try {
						ScrollPanel scroll = (ScrollPanel) widget;
						VerticalPanel vert = (VerticalPanel) scroll.getWidget();
						for (Widget check: vert) {
							try {
							System.out.println("Hit");
							CheckBox tag = (CheckBox) check;
							if (tag.getValue()) {
								System.out.println(tag.getText());
								tagsTextArea.setText(tagsTextArea.getText() + " " + tag.getText());
							}
							} catch (ClassCastException cee) {}
						}
					} catch (ClassCastException cce) {}
				}
				
				if (tagsTextArea.getText().trim().isEmpty()) {sendErrorMessage("Please mark or add one or more tags"); return;}
				Thesis changedThesis = new Thesis(titleTextBox.getText(), authorTextBox.getText(),
						professorTextBox.getText(), yearTextBox.getText(), semesterTextBox.getText(),
						classTextBox.getText(), abstractTextArea.getText(), thesis.getURL(), tagsTextArea.getText());
				
				controller.handleSubmitEditForm(thesis, changedThesis);
			}
		
		}
	});
	submitPanel.add(submitButton);
	
	thesisEditFormPanel.add(submitPanel);
	
	Button cancel = new Button ("Cancel");
	cancel.addClickHandler(new ClickHandler () {
		@Override
		public void onClick(ClickEvent event) {
			viewWelcomePage();
		}
	});
	thesisEditFormPanel.add(cancel);
	
	return submitEditFormPanel;
	}
	
	public void viewMainPage (RootPanel rp) {
		

		final VerticalPanel thesisPanel = new VerticalPanel();
		thesisPanel.setWidth("899px");
		rp.add(thesisPanel);
		final FlowPanel thesisFlow = new FlowPanel();
		thesisPanel.add(thesisFlow);
		
		controller.viewThesisDataFromServer(thesisFlow, thesisPanel);	
		
		VerticalPanel allOptions = new VerticalPanel();
		allOptions.setStyleName("sort-panel");
		//HorizontalPanel hp = new HorizontalPanel();
		//hp.add(allOptions);
		rp.add(allOptions,900,404);
		
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
//		final Label searchLabel = new Label ("Search:");
		final TextBox searchBox = new TextBox();
//		searchPanel.add(searchLabel);
		
		
		Button searchButton = new Button("Search");
		searchButton.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				if (searchBox.getText() == "") controller.viewThesisDataFromServer(thesisFlow, thesisPanel);
				else controller.viewSearchThesisDataFromServer(thesisFlow, thesisPanel, searchBox.getText());
			}
	      });
		
		searchPanel.add(searchButton);
		searchPanel.add(searchBox);
		allOptions.add(searchPanel);
		
		final VerticalPanel allFilters = new VerticalPanel();
		
		
		controller.getTagFilterDataFromServer(allFilters);
		controller.getYearFilterDataFromServer(allFilters);
		controller.getProfFilterDataFromServer(allFilters);
		controller.getClassFilterDataFromServer(allFilters);
		
		Button filterButton = new Button("Filter");
		filterButton.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				List<String> tagFilters = new ArrayList<String>();
				List<String> yearFilters = new ArrayList<String>();
				List<String> profFilters = new ArrayList<String>();
				List<String> classFilters = new ArrayList<String>();
				List[] filterArray = {tagFilters, yearFilters, profFilters, classFilters};
				int index = 0; 
				for (Widget option: allFilters) {
					try {
						ScrollPanel filterOptions = (ScrollPanel) option;
						
						VerticalPanel contents = (VerticalPanel) filterOptions.getWidget();
						for (Widget check: contents) {
							CheckBox mark = (CheckBox) check;
							if (mark.getValue()) {
								filterArray[index].add(mark.getText());
							}
						}
						index ++;
					} catch (ClassCastException cce) {
						continue;
					}
				}
				controller.viewFilterThesisDataFromServer(thesisFlow, thesisPanel, tagFilters, yearFilters, profFilters, classFilters);
			}
		});
		
		allOptions.add(allFilters);
		allOptions.add(filterButton);
		
		
//		for (Widget option: tagPanel) {
//			CheckBox mark = (CheckBox) option;
//			System.out.println(mark.getText());
//			
//		}
		
	}
	
	public void addTagFilter (VerticalPanel allOptions, List<String> tags) {
		
		//Tag Filter
		// Will need persistence for tags, and for loop to add them all here
		//
		
		VerticalPanel tagPanel = new VerticalPanel();
		
		Label tagLabel = new Label("Tag Filters");
		allOptions.add(tagLabel);
		
		if (tags.contains("Honor")) {
			tags.remove("Honor");
		}
		
		CheckBox honor = new CheckBox("Honor");
		tagPanel.add(honor);
		
		int index = 0;
		while (index < tags.size()) {
			if (index == 0) {
				index++;
			}
			else if (tags.get(index).compareTo(tags.get(index - 1)) < 0) {
				String temp = tags.get(index);
				tags.set(index, tags.get(index - 1));
				tags.set(index-1, temp);
				index --;
			}
			else {
				index ++;
			}
		}
		
		for (String tag: tags){
			CheckBox option = new CheckBox (tag);
			tagPanel.add(option);
		}

		ScrollPanel tagFilterScrollPanel = new ScrollPanel(tagPanel);
		tagFilterScrollPanel.setSize("200px", "100px");
		
		allOptions.add(tagFilterScrollPanel);		
		
	}
	
	public void addYearFilter (VerticalPanel allOptions, List<String> years) {
		
		//Year Filter
		
		Label yearLabel = new Label("Year Filters");
		allOptions.add(yearLabel);
		
		VerticalPanel yearPanel = new VerticalPanel();
		
		int index = 0;
		while (index < years.size()) {
			if (index == 0) {
				index++;
			}
			else if (years.get(index).compareTo(years.get(index - 1)) > 0) {
				String temp = years.get(index);
				years.set(index, years.get(index - 1));
				years.set(index-1, temp);
				index --;
			}
			else {
				index ++;
			}
		}
		
		for (String year: years){
			CheckBox option = new CheckBox (year);
			yearPanel.add(option);
		}
		
		ScrollPanel yearFilter = new ScrollPanel(yearPanel);
		yearFilter.setSize("200px", "100px");
		
		allOptions.add(yearFilter);
		
	}
	
	public void addProfessorFilter (VerticalPanel allOptions, List<String> profs) {
		//Author Filter
		
		Label profLabel = new Label("Professor Filters");
		allOptions.add(profLabel);
		
		VerticalPanel profPanel = new VerticalPanel();
		
		int index = 0;
		while (index < profs.size()) {
			if (index == 0) {
				index++;
			}
			else if (profs.get(index).compareTo(profs.get(index - 1)) < 0) {
				String temp = profs.get(index);
				profs.set(index, profs.get(index - 1));
				profs.set(index-1, temp);
				index --;
			}
			else {
				index ++;
			}
		}
		
		for (String prof: profs){
			CheckBox option = new CheckBox (prof);
			profPanel.add(option);
		}
		
		ScrollPanel authorFilter = new ScrollPanel(profPanel);
		authorFilter.setSize("200px", "100px");
		
		allOptions.add(authorFilter);
		
	}
	
	public void addClassFilter (VerticalPanel allOptions, List<String> classes) {
		
		Label classLabel = new Label("Class Filters");
		allOptions.add(classLabel);
		
		VerticalPanel classPanel = new VerticalPanel();
		
		int index = 0;
		while (index < classes.size()) {
			if (index == 0) {
				index++;
			}
			else if (classes.get(index).compareTo(classes.get(index - 1)) < 0) {
				String temp = classes.get(index);
				classes.set(index, classes.get(index - 1));
				classes.set(index-1, temp);
				index --;
			}
			else {
				index ++;
			}
		}
		
		for (String className: classes) {
			CheckBox option = new CheckBox (className);
			classPanel.add(option);
		}

		ScrollPanel classFilter = new ScrollPanel(classPanel);
		classFilter.setSize("200px", "100px");
		
		allOptions.add(classFilter);
	}
	
	public void makeThesisTable (List<Thesis> theses, FlowPanel fp, VerticalPanel panel) {
		
		final List<Thesis> THESES = theses;
		
		// Create a CellTable.
	    CellTable<Thesis> table = new CellTable<Thesis>();

	    // Create title column.
//	    TextColumn<Thesis> titleColumn = new TextColumn<Thesis>() {
//	      @Override
//	      public String getValue(Thesis thesis) {
//	        return thesis.getTitle();
//	      }
//	    };

	    ClickableTextCell getPdf = new ClickableTextCell();
	    Column<Thesis,String> titleColumn = new Column<Thesis,String>(getPdf) {
	      public String getValue(Thesis thesis) {
	        return thesis.getTitle();
	      }
	    };
	    
	    titleColumn.setFieldUpdater(new FieldUpdater<Thesis, String>() {
	    	  @Override
	    	  public void update(int index, Thesis thesis, String value) {
	    		  setWindow(thesis.getURL());
	    	  }
	    	});
	    
	    // Make the title column sortable.
	    titleColumn.setSortable(true);

	    // Create author column.
	    TextColumn<Thesis> authorColumn = new TextColumn<Thesis>() {
	      @Override
	      public String getValue(Thesis thesis) {
	        return thesis.getAuthor();
	      }
	    };
	    
	    // Make the author column sortable.
	    authorColumn.setSortable(true);
	    
	 // Create semYear column.
	    TextColumn<Thesis> semYearColumn = new TextColumn<Thesis>() {
		      @Override
		      public String getValue(Thesis thesis) {
		    	String semYear = (thesis.getSemester().substring(0, 2).toUpperCase() + "-" + thesis.getYear());
		        return semYear;
		      }
		    };
		// Make the semYear column sortable.
		    semYearColumn.setSortable(true);

	    // Create className column.
	    TextColumn<Thesis> classNameColumn = new TextColumn<Thesis>() {
	      @Override
	      public String getValue(Thesis thesis) {
	        return thesis.getClassName();
	      }
	    };
	    
	    // Make the className column sortable.
	    classNameColumn.setSortable(true);
	    
	    ButtonCell infoButton = new ButtonCell();
	    Column<Thesis,String> info = new Column<Thesis,String>(infoButton) {
	      public String getValue(Thesis object) {
	        return "info";
	      }
	    };
	    
	    info.setFieldUpdater(new FieldUpdater<Thesis, String>() {
	    	  @Override
	    	  public void update(int index, Thesis thesis, String value) {
	    		  	moreInfoPopup.setWidget(moreInfoPanel(thesis));
					moreInfoPopup.setSize("400px", "400px");
					moreInfoPopup.center();
					moreInfoPopup.show();
	    	  }
	    	});
	    
	  //  Create infoButton column.
	 // ButtonCell.
//	    Column(new ButtonCell(), "Button", new GetValue<String>() {
//	      @Override
//	      public String getValue(Thesis thesis) {
//	        return "Info "; 
//	      }
//	    }, new FieldUpdater<Thesis, String>() {
//	      @Override
//	      public void update(int index, Thesis thesis, String value) {
//	        Window.alert("You clicked " + thesis.getTitle());
//	      }
//	    });
	    // Make the title column sortable.
//	    semYearColumn.setSortable(true);

	    // Create professor column.
	    TextColumn<Thesis> professorColumn = new TextColumn<Thesis>() {
	      @Override
	      public String getValue(Thesis thesis) {
	        return thesis.getProfessor();
	      }
	    };
	    
	    // Make the title column sortable.
	    professorColumn.setSortable(true);
	    
	 // Set the width of the table and put the table in fixed width mode.
	    table.setWidth("100%", true);
	    
	    // Set the width of each column.
	    table.setColumnWidth(titleColumn, 28.0, Unit.PCT);
	    table.setColumnWidth(authorColumn, 15.0, Unit.PCT);
	    table.setColumnWidth(semYearColumn, 10.0, Unit.PCT);
	    table.setColumnWidth(classNameColumn, 24.0, Unit.PCT);
	    table.setColumnWidth(professorColumn, 15.0, Unit.PCT);
	    table.setColumnWidth(info, 8.0, Unit.PCT);

	    

	    
	 // Add the columns.
	    table.addColumn(titleColumn, "Title");
	    table.addColumn(authorColumn, "Author");
	    table.addColumn(semYearColumn, "Semester-Year");
	    table.addColumn(classNameColumn, "Class Name");
	    table.addColumn(professorColumn, "Professor");
	    table.addColumn(info, "Info");
	    
	 // Create a data provider.
	    ListDataProvider<Thesis> dataProvider = new ListDataProvider<Thesis>();

	    // Connect the table to the data provider.
	    dataProvider.addDataDisplay(table);

	    // Add the data to the data provider, which automatically pushes it to the
	    // widget.
	    List<Thesis> list = dataProvider.getList();
	    for (Thesis thesis : THESES) {
	      list.add(thesis);
	    }
	    
	 // Add a ColumnSortEvent.ListHandler to connect sorting to the
	    // java.util.List.
	    ListHandler<Thesis> columnSortHandler = new ListHandler<Thesis>(list);
	    columnSortHandler.setComparator(titleColumn,
	        new Comparator<Thesis>() {
	          public int compare(Thesis o1, Thesis o2) {
	            if (o1 == o2) {
	              return 0;
	            }

	            // Compare the name columns.
	            if (o1 != null) {
	              return (o2 != null) ? o1.getTitle().compareTo(o2.getTitle()) : 1;
	            }
	            return -1;
	          }
	        });
	    table.addColumnSortHandler(columnSortHandler);

	    // We know that the data is sorted alphabetically by default.
	    table.getColumnSortList().push(titleColumn);
	    // Author sort
	    ListHandler<Thesis> authorColumnSortHandler = new ListHandler<Thesis>(list);
	    columnSortHandler.setComparator(authorColumn,
	        new Comparator<Thesis>() {
	          public int compare(Thesis o1, Thesis o2) {
	            if (o1 == o2) {
	              return 0;
	            }

	            // Compare the name columns.
	            if (o1 != null) {
	              return (o2 != null) ? o1.getAuthor().compareTo(o2.getAuthor()) : 1;
	            }
	            return -1;
	          }
	        });
	    table.addColumnSortHandler(authorColumnSortHandler);

	    // We know that the data is sorted alphabetically by default.
	    table.getColumnSortList().push(authorColumn);
	    
	    // Year sort
	    ListHandler<Thesis> yearColumnSortHandler = new ListHandler<Thesis>(list);
	    columnSortHandler.setComparator(semYearColumn,
	        new Comparator<Thesis>() {
	          public int compare(Thesis o1, Thesis o2) {
	            if (o1 == o2) {
	              return 0;
	            }

	            // Compare the name columns.
	            if (o1 != null) {
	              return (o2 != null) ? o1.getYear().compareTo(o2.getYear()) : 1;
	            }
	            return -1;
	          }
	        });
	    table.addColumnSortHandler(yearColumnSortHandler);

	    // We know that the data is sorted alphabetically by default.
	    table.getColumnSortList().push(semYearColumn);
	    
	    // ClassName sort
	    ListHandler<Thesis> classColumnSortHandler = new ListHandler<Thesis>(list);
	    columnSortHandler.setComparator(classNameColumn,
	        new Comparator<Thesis>() {
	          public int compare(Thesis o1, Thesis o2) {
	            if (o1 == o2) {
	              return 0;
	            }

	            // Compare the name columns.
	            if (o1 != null) {
	              return (o2 != null) ? o1.getClassName().compareTo(o2.getClassName()) : 1;
	            }
	            return -1;
	          }
	        });
	    table.addColumnSortHandler(classColumnSortHandler);

	    // We know that the data is sorted alphabetically by default.
	    table.getColumnSortList().push(classNameColumn);
	    // Add it to the root panel.
	    
	    // Year sort
	    ListHandler<Thesis> professorColumnSortHandler = new ListHandler<Thesis>(list);
	    columnSortHandler.setComparator(professorColumn,
	        new Comparator<Thesis>() {
	          public int compare(Thesis o1, Thesis o2) {
	            if (o1 == o2) {
	              return 0;
	            }

	            // Compare the name columns.
	            if (o1 != null) {
	              return (o2 != null) ? o1.getProfessor().compareTo(o2.getProfessor()) : 1;
	            }
	            return -1;
	          }
	        });
	    table.addColumnSortHandler(professorColumnSortHandler);

	    // We know that the data is sorted alphabetically by default.
	    table.getColumnSortList().push(professorColumn);
	    
	    // Add it to the flow panel.
	    
	 
	    
	    fp.add(table);
	  

	    
	}

//	public void makeThesisTable (List<Thesis> theses, FlowPanel fp, VerticalPanel panel) {
//		HorizontalPanel row = new HorizontalPanel();
//		row.setHeight("30px");
//		Label author = new Label("AUTHOR");
//		Label title = new Label ("TITLE");
//		Label year = new Label ("SEM/YEAR");
//		Label professor = new Label ("PROFESSOR");
//		Label className = new Label ("CLASS");
//		
//		VerticalPanel sortButtons = new VerticalPanel ();
//		sortButtons.setHeight("30px");
//		sortButtons.setWidth("30px");
//		
//		Button sortUp = new Button ("^");
//		sortUp.setHeight("15px");
//		sortUp.setWidth("30px");
//		Button sortDown = new Button ("v");
//		sortDown.setHeight("15px");
//		sortDown.setWidth("30px");
//		sortButtons.add(sortUp);
//		sortButtons.add(sortDown);
//		
//		row.add(title); title.setWidth("300px"); row.add(author); author.setWidth("200px"); 
//		row.add(year); year.setWidth("100px"); row.add(professor); professor.setWidth("200px");
//		row.add(className); className.setWidth("200px");
//		
//		fp.add(row);
//		
//		if (theses != null){
//			for (Thesis entry: theses) {
//				HorizontalPanel thesisRow = makeThesisEntryRow(entry);
//				fp.add(thesisRow);
//			}
//		}
//		Label footer = new Label("COPYRIGHT HERE");
//		footer.addStyleName("footer");
//		footer.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_CENTER);
//		fp.add(footer);
//	}
//	
//	public HorizontalPanel makeThesisEntryRow(final Thesis entry) {
//		HorizontalPanel row = new HorizontalPanel();
//		Anchor link = new Anchor(entry.getTitle(), entry.getURL());
//		link.setTarget("_blank");
//		link.setTitle("Download PDF");
//		
//		Label author = new Label(entry.getAuthor());
//		author.addStyleName("entryLabel");
//		Label year = new Label (entry.getSemester().substring(0, 2).toUpperCase() + "-" + entry.getYear());
//		Label professor = new Label (entry.getProfessor());
//		Label className = new Label (entry.getClassName());
//		
//		Button deleteButton = new Button ("DEL");
//		deleteButton.addClickHandler(new ClickHandler() {
//			@Override
//			public void onClick(ClickEvent event) {
//				controller.deleteThesisDataFromServer(entry);
//				viewWelcomePage();
//			}
//		});
//		
//		Button infoButton = new Button("Info"); 
//		infoButton.setText("Info");
//		infoButton.addClickHandler(new ClickHandler() {
//			@Override
//			public void onClick(ClickEvent event) {
//				moreInfoPopup.setWidget(moreInfoPanel(entry));
//				moreInfoPopup.setSize("400px", "400px");
//				moreInfoPopup.center();
//				moreInfoPopup.show();
//			}
//		});
//		
//
//		
//		Button editButton = new Button("Edit");
//		editButton.setText("Edit");
//		editButton.addClickHandler(new ClickHandler() {
//			@Override
//			public void onClick(ClickEvent event) {
//				viewEditThesisPage(entry);
//			}
//		});
//		
//		
//		row.add(link);
//		row.add(author);
//		author.setWidth("200px");
//		row.add(year);
//		year.setWidth("100px");
//		row.add(professor);
//		professor.setWidth("200px");
//		row.add(className);
//		className.setWidth("200px");
//		
//		row.add(infoButton);
//		row.add(deleteButton);
//		row.add(editButton);
//		return row;
//	}
	
	public VerticalPanel moreInfoPanel(Thesis entry) {
		VerticalPanel content = new VerticalPanel();
		
		Label author = new Label(entry.getAuthor());
		Label title = new Label (entry.getTitle());
		Label year = new Label (entry.getYear());
		Label professor = new Label (entry.getProfessor());
		Label className = new Label (entry.getClassName());
		Label textAbstract = new Label (entry.getTextAbstract());
		
		// Ok Button;
		Button okButton = new Button("Done");
		okButton.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				moreInfoPopup.hide();
			}
		});
		
		
		content.add(author);
		content.add(title);
		content.add(year);
		content.add(professor);
		content.add(className);
		content.add(textAbstract);
		
		content.add(okButton);
		
		return content;
		
	}
	
	public void setWindow(String url) {
		Window.open(url,"_blank","resizable,scrollbars,status");
	}
	public void sendErrorMessage(String msg) {
		Window.alert(msg);  
	}

}
