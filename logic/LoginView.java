package logic;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
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

        Image image = new Image(Main.class.getResourceAsStream("pasta.png"));
        ImageView iv1 = new ImageView();
        iv1.setImage(image);
        iv1.setFitWidth(450);
        iv1.setPreserveRatio(true);
        iv1.setSmooth(true);
        iv1.setCache(true);

        HBox imageBox = new HBox();
        imageBox.getChildren().add(iv1);
        imageBox.setPadding(new Insets(-300,0,0,-130));

    	// User name Input
        TextField userInput = new TextField("");
        userInput.setPromptText("Username");
        userInput.setMaxWidth(Double.MAX_VALUE);

        // Password Input
        TextField passInput = new TextField("");
        passInput.setPromptText("Password");
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
        final BooleanProperty firstTime = new SimpleBooleanProperty(true); 
        userInput.focusedProperty().addListener((observable,  oldValue,  newValue) -> {
            if(newValue && firstTime.get()){
                loginBox.requestFocus(); 
                firstTime.setValue(false); 
            }
        });
        
        GridPane loginGrid = new GridPane();
        // Puts the login box in the middle-ish part of the screen
        loginGrid.setPadding(new Insets(height/2-100, width/2-100, height/2, width/2-75));
        loginGrid.setVgap(8);
        loginGrid.setHgap(10);
        loginGrid.getChildren().addAll(imageBox, loginBox);
    	view.setCenter(loginGrid);
    }
    public Node getView()
    {
    	return view;
    }
}
