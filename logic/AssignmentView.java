package logic;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

public class AssignmentView 
{
	private final BorderPane view;
	
	public AssignmentView(Assignment toView)
    {

		VBox newBox = new VBox();
    	NavBar navBar = new NavBar();
    	
    	String labelCSS = "a-label";
    	
    	// Sign Up Input
    	Label titleLabel = new Label(Main.aString + ":");
    	titleLabel.getStyleClass().add(labelCSS);
    	
        Label assignTitle = new Label(toView.getName());
        assignTitle.setMaxWidth(Double.MAX_VALUE);
        
    	Label descLabel = new Label(Main.desString + ":");
    	descLabel.getStyleClass().add(labelCSS);

        Label description = new Label(toView.description());
        description.setMaxWidth(Double.MAX_VALUE);

    	Label dueLabel = new Label(Main.dueString+":");
    	dueLabel.getStyleClass().add(labelCSS);

    	Label due = new Label(toView.getDue());
    	due.setMaxWidth(Double.MAX_VALUE);
    	
    	Label priorityLabel = new Label("Current Priority: " + toView.getPriority()); 
    	priorityLabel.getStyleClass().add(labelCSS);


        Button editButton = new Button("Modify Assignment");
        editButton.setMaxWidth(Double.MAX_VALUE);
        editButton.setOnAction(e -> Main.editAssignment(toView));
        
        Button completeButton = new Button("Mark Complete");
        completeButton.setMaxWidth(Double.MAX_VALUE);
        completeButton.setOnAction(e -> Main.markComplete(toView));
        
        Button deleteButton = new Button("Delete");
        deleteButton.setMaxWidth(Double.MAX_VALUE);
        deleteButton.setOnAction(e -> Main.deleteAssignment(toView));
        
        // The title text on top and its alignment
    	Label header = new Label("View Assignment");
    	header.getStyleClass().add("a-header");
        header.setMaxWidth(Double.MAX_VALUE);
        header.setAlignment(Pos.CENTER);
        
        newBox.setSpacing(10);
        newBox.getChildren().addAll(header, titleLabel, assignTitle, descLabel, description, 
        		dueLabel, due, priorityLabel);
        newBox.getChildren().addAll(editButton, completeButton, deleteButton);  
        newBox.setPadding(new Insets(Main.screenSize.getHeight()/2-200,0,0,Main.screenSize.getWidth()/2-75));
    
        // Add Assignment Setup
        GridPane addAssign = new GridPane();
        addAssign.setVgap(8);
        addAssign.setHgap(10);
        addAssign.getChildren().addAll(newBox, navBar);
        view = new BorderPane();
        view.getStylesheets().add(getClass().getResource("theme.css").toExternalForm());
        view.setCenter(addAssign);
    }
	
	public Node getView()
	{
		return view;
	}
}
