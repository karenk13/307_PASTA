package logic;

import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;



public class LoginView 
{
	private final BorderPane view;
	 // Login Page Setup
    public LoginView()
    {
    	view = new BorderPane();
    	VBox loginBox = new VBox();
    	double loginWidth = 200;
    	double height = Main.screenSize.getHeight();
    	double width = Main.screenSize.getWidth();
    	
    	// User name Input
        TextField userInput = new TextField(Main.uString);
        userInput.setMaxWidth(Double.MAX_VALUE);

        // Password Input
        TextField passInput = new TextField(Main.pString);
        passInput.setMaxWidth(Double.MAX_VALUE);
            
        // Login Action
        Button loginButton = new Button("Log In");
        loginButton.setMaxWidth(Double.MAX_VALUE);
        loginButton.setOnAction(e -> Main.authenticate(userInput.getText(), passInput.getText()));
        
        // Sign Up Action
        Button signUpButton = new Button("Sign Up");
        signUpButton.setMaxWidth(Double.MAX_VALUE);
        signUpButton.setOnAction(e -> Main.window.setScene(Main.createUser));
        
        // Exit Button
        Button exitButton = new Button("Exit PASTA");
        exitButton.setMaxWidth(Double.MAX_VALUE);
        exitButton.setOnAction(e ->  System.exit(0));
    	
        loginBox.setSpacing(10);
    	loginBox.setMaxWidth(loginWidth);
        loginBox.getChildren().addAll(userInput, passInput, loginButton, signUpButton, exitButton);
        
        GridPane loginGrid = new GridPane();
        // Puts the login box in the middle-ish part of the screen
        loginGrid.setPadding(new Insets(height/2-100, width/2-100, height/2, width/2-75));
        loginGrid.setVgap(8);
        loginGrid.setHgap(10);
        loginGrid.getChildren().addAll(loginBox);
    	view.setCenter(loginGrid);
    }
    public Node getView()
    {
    	return view;
    }
}
