package logic;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

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

public class AddAssignmentView 
{
	private final BorderPane view;
	public AddAssignmentView(LocalDate due)
	{
		VBox newBox = new VBox();
    	NavBar navBar = new NavBar();
    	BorderPane root = new BorderPane();
    	root.setLeft(navBar);
    	root.setCenter(newBox);
    	// Assignment Detail Input
    	Label titleLabel = new Label(Main.aString);
        TextField assignTitle = new TextField(Main.aString);
        assignTitle.setMaxWidth(Double.MAX_VALUE);
        
    	Label descLabel = new Label(Main.desString);

        TextField description = new TextField(Main.desString);
        description.setMaxWidth(Double.MAX_VALUE);

    	Label dueLabel = new Label(Main.dueString);
    	DatePicker dueDate = new DatePicker(); 
    	if(due != null)
    		dueDate.setValue(due);
    	else 
    		dueDate.setValue(LocalDate.now());

    	Label priorityLabel = new Label("Priority (1-10)");        
    	
    	// Create a slider to get a number value
    	Slider pSlide = new Slider();
        pSlide.setMin(1);
        pSlide.setMax(10);
        pSlide.setValue(5);
        pSlide.setShowTickLabels(true);
        pSlide.setShowTickMarks(true);
        pSlide.setMajorTickUnit(1);
        pSlide.setMinorTickCount(0);
        pSlide.setSnapToTicks(true);
        pSlide.setMaxWidth(Double.MAX_VALUE);
      

        Button createButton = new Button("Create Assignment");
        createButton.setMaxWidth(Double.MAX_VALUE);
        createButton.setOnAction(e -> Main.createAssignment(assignTitle.getText(),
        		description.getText(), dueDate.getValue().format(DateTimeFormatter.ofPattern("MM/dd/yyyy")),
        		pSlide.getValue()));
        
        Button cancelButton = new Button(Main.cancelString);
        cancelButton.setMaxWidth(Double.MAX_VALUE);
        cancelButton.setOnAction(e -> Main.window.setScene(Main.home));
    	
        // The title text on top and its alignment
    	Label header = new Label("Add Assignment");
        header.setMaxWidth(Double.MAX_VALUE);
        header.setAlignment(Pos.CENTER);
        
        newBox.setSpacing(10);
        newBox.getChildren().addAll(header, titleLabel, assignTitle, descLabel, description, 
        		dueLabel, dueDate, priorityLabel, pSlide);
        newBox.getChildren().addAll(createButton, cancelButton);
        newBox.setPadding(new Insets(Main.screenSize.getHeight()/2-200,0,0,Main.screenSize.getWidth()/2-200));
    	
        // Add Assignment Setup
        GridPane addAssign = new GridPane();
        addAssign.setVgap(8);
        addAssign.setHgap(10);
        addAssign.getChildren().addAll(root);
        view = root;
	}
	
	public Node getView()
	{
		return view;
	}
}
