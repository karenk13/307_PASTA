package logic;

import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;

public class NavBar extends VBox
{
	public NavBar()
	{
		int buttonWidth = 150;
    	
    	// Logout Button
        Button logoutButton = new Button("Log Out");
        logoutButton.setOnAction(e -> Main.window.setScene(Main.login));
        logoutButton.setMaxWidth(Double.MAX_VALUE);
    	
    	// Home Back Button
        Button homeButton = new Button("Home");
        homeButton.setOnAction(e -> 
        {
        	Main.currentAssignments();
        	Main.window.setScene(Main.home);	
        });
        homeButton.setMaxWidth(Double.MAX_VALUE);

    	// Add Calendar
        Button calendarButton = new Button("Calendar");
        calendarButton.setOnAction(e -> Main.window.setScene(Main.calendar));
        calendarButton.setMaxWidth(Double.MAX_VALUE);
        
        // Add Assignment
        Button assignmentButton = new Button("New Assign");
        assignmentButton.setOnAction(e ->Main.window.setScene(Main.addAssignment));
        assignmentButton.setMaxWidth(Double.MAX_VALUE);
        
      

        //Add Scratch pad
        Button scratchpadButton = new Button("Scratchpad");
        scratchpadButton.setOnAction(e -> Main.window.setScene(Main.scratchpad));
        scratchpadButton.setMaxWidth(Double.MAX_VALUE);

        // Add Settings
        Button settingsButton = new Button("Settings");
        settingsButton.setOnAction(e ->Main.window.setScene(Main.settings));
        settingsButton.setMaxWidth(Double.MAX_VALUE);
        
        // Add Exit
        Button exitButton = new Button("Exit PASTA");
        exitButton.setOnAction(e -> System.exit(0));
        exitButton.setMaxWidth(Double.MAX_VALUE);
        
        // Populating the nav bar
        this.setSpacing(10);
    	this.setMaxWidth(buttonWidth);
    	this.setPadding(new Insets(20,20,10,10));
    	this.getChildren().addAll(homeButton, calendarButton, assignmentButton);
    	this.getChildren().addAll(scratchpadButton, settingsButton,logoutButton, exitButton);	
	}	
}
