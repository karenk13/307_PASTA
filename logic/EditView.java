package logic;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import com.sun.prism.paint.Color;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

public class EditView 
{
	private final BorderPane view;

	public EditView(Assignment a)
	{
		VBox newBox = new VBox();
    	NavBar navBar = new NavBar();
    	
    	// Assignment Detail Input
        TextField assignTitle = new TextField(a.getName());
        assignTitle.setPromptText("Assignment Title");
        assignTitle.setMaxWidth(Double.MAX_VALUE);
        

        TextField description = new TextField(a.description());
        description.setPromptText("Description");
        description.setMaxWidth(Double.MAX_VALUE);

    	DatePicker dueDate = new DatePicker(); 
    	dueDate.setValue(LocalDate.now());

    	Label priorityLabel = new Label("Priority (1-10)"); 
    	priorityLabel.getStyleClass().add("a-label");

    	
    	// Create a slider to get a number value
    	Slider pSlide = new Slider();
        pSlide.setMin(1);
        pSlide.setMax(10);
        pSlide.setValue(Double.parseDouble(a.getPriority()));
        pSlide.setShowTickLabels(true);
        pSlide.setShowTickMarks(true);
        pSlide.setMajorTickUnit(1);
        pSlide.setMinorTickCount(0);
        pSlide.setSnapToTicks(true);
        pSlide.setMaxWidth(Double.MAX_VALUE);
      

        Button saveButton = new Button("Save");
        saveButton.setMaxWidth(Double.MAX_VALUE);
        saveButton.setOnAction(e -> {
        	Main.createAssignment(assignTitle.getText(), description.getText(),
        			dueDate.getValue().format(DateTimeFormatter.ofPattern("MM/dd/yyyy")),
            		pSlide.getValue());
            Main.deleteAssignment(a);
        });
        		
        
        Button cancelButton = new Button(Main.cancelString);
        cancelButton.setMaxWidth(Double.MAX_VALUE);
        cancelButton.setOnAction(e -> Main.window.setScene(Main.viewAssignment));
    	
        // The title text on top and its alignment
    	Label header = new Label("Edit Assignment");
        header.setMaxWidth(Double.MAX_VALUE);
        header.setAlignment(Pos.CENTER);
        
        newBox.setSpacing(10);
        newBox.setAlignment(Pos.CENTER);
        newBox.getChildren().addAll(header, assignTitle, description, 
        	 dueDate, priorityLabel, pSlide);
        newBox.getChildren().addAll(saveButton, cancelButton);
        
        newBox.setPadding(new Insets(Main.screenSize.getHeight()/2-200,0,0,Main.screenSize.getWidth()/2-75));
    	// Add Assignment Setup
        GridPane addAssign = new GridPane();
        addAssign.setVgap(80);
        addAssign.setHgap(100);
        addAssign.getChildren().addAll(newBox, navBar);
        newBox.getStylesheets().add(getClass().getResource("theme.css").toExternalForm());
        
        final BooleanProperty firstTime = new SimpleBooleanProperty(true); 
        assignTitle.focusedProperty().addListener((observable,  oldValue,  newValue) -> {
            if(newValue && firstTime.get()){
                assignTitle.requestFocus(); 
                firstTime.setValue(false); 
            }
        });
        
    	header.getStyleClass().add("a-header");
        view = new BorderPane();
        view.setCenter(addAssign);
        
	}
	
	public Node getView()
	{
	    view.getStylesheets().add(getClass().getResource("theme.css").toExternalForm());
	    view.getStyleClass().add("root");
		return view;   
	}
}
