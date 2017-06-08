package logic;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

public class NewUserView 
{
	private final BorderPane view;

	public NewUserView()
	{
		// create new user instance and set up AM 
    	VBox newUserBox = new VBox();
    	double boxWidth = 200;
    	double height = Main.screenSize.getHeight();
    	double width = Main.screenSize.getWidth();
    	
    	GridPane newUse = new GridPane();
        newUse.setPadding(new Insets(0, 0, 0, 0));
        newUse.setVgap(8);
        newUse.setHgap(10);
    	
    	// Sign Up Input
        TextField fNameInput = new TextField();
        fNameInput.setPromptText("First Name");
        fNameInput.setMaxWidth(Double.MAX_VALUE);
        
        TextField lNameInput = new TextField("");
        lNameInput.setPromptText("Last Name");
        lNameInput.setMaxWidth(Double.MAX_VALUE);

        TextField userSignUpInput = new TextField("");
        userSignUpInput.setPromptText("Username");
        userSignUpInput.setMaxWidth(Double.MAX_VALUE);

        TextField passSignUpInput = new TextField("");
        passSignUpInput.setPromptText("Password");
        passSignUpInput.setMaxWidth(Double.MAX_VALUE);

        TextField confirmPassInput = new TextField("");
        confirmPassInput.setPromptText("Confirm Password");
        confirmPassInput.setMaxWidth(Double.MAX_VALUE);

        Button createSignUpButton = new Button("Create Account");
        createSignUpButton.setMaxWidth(Double.MAX_VALUE);
        createSignUpButton.setOnAction(e -> 
        {
        	Main.users.add(new User(userSignUpInput.getText(),passSignUpInput.getText()));
        	Main.window.setScene(Main.login);
        });
        
        Button cancelButton = new Button(Main.cancelString);
        cancelButton.setMaxWidth(Double.MAX_VALUE);
        cancelButton.setOnAction(e -> Main.window.setScene(Main.login));

        newUserBox.setSpacing(10);
        // Sets the width of the login box.
    	newUserBox.setMaxWidth(boxWidth);
    	// Adds text fields and buttons to the vbox
        newUserBox.getChildren().addAll(fNameInput, lNameInput, userSignUpInput, passSignUpInput);
        newUserBox.getChildren().addAll(confirmPassInput, createSignUpButton, cancelButton);
        newUse.getChildren().addAll(newUserBox);
        // Puts the login box in the middle-ish part of the screen
        newUse.setPadding(new Insets(height/2-100, width/2-100, height/2, width/2-75));
        
        final BooleanProperty firstTime = new SimpleBooleanProperty(true); 
        fNameInput.focusedProperty().addListener((observable,  oldValue,  newValue) -> {
            if(newValue && firstTime.get()){
                newUserBox.requestFocus(); 
                firstTime.setValue(false); 
            }
        });
        
        view = new BorderPane();
        view.setCenter(newUse);
	}
	public Node getView()
	{
        view.getStylesheets().add(getClass().getResource("theme.css").toExternalForm());
		return view;   
	}
}
