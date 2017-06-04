package logic;
import javafx.geometry.Pos;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.collections.ObservableList;
import javafx.collections.FXCollections;
import javafx.scene.Scene;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
//import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;

public class Main extends Application implements EventHandler<ActionEvent>{

    static Stage window;
    static TableView<Assignments> assignment;
    static TableView<Assignments> assignmentManager;
    static Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    
    static Button loginButton, signUpButton, createSignUpButton, cancelButton, logoutButton, homeButton;
    static Button assignmentButton, calendarButton, scratchpadButton, settingsButton, exitButton;
    static Scene login, home, createUser, addAssignment, calendar, scratchpad, settings;
    static ObservableList<Assignments> assignments;
    
    private static User user; 
    private static AssignmentManager AM; 
    
    @Override
    public void start(Stage primaryStage) throws Exception{
        window = primaryStage;
        window.setTitle("PASTA");
        assignments = FXCollections.observableArrayList();
        getAssignments();
                        
        
        //TODO get AM from the user that signs in 
        user = new User("Test", "dummy");
        AM = user.getAM(); 

        // Assignment View
        /*TableColumn<Assignments, String> nameCol = new TableColumn<>("Assignment");
        nameCol.setMinWidth(200);
        nameCol.setCellValueFactory(new PropertyValueFactory<>("name"));

        TableColumn<Assignments, String> dueDateCol = new TableColumn<>("Due Date");
        dueDateCol.setMinWidth(200);
        dueDateCol.setCellValueFactory(new PropertyValueFactory<>("dueDate"));

        TableColumn<Assignments, Integer> priorityCol = new TableColumn<>("Priority");
        priorityCol.setMinWidth(200);
        priorityCol.setCellValueFactory(new PropertyValueFactory<>("priority"));

        assignment = new TableView<Assignments>();
        assignment.setItems(assignments);
        assignment.getColumns().addAll(nameCol, dueDateCol, priorityCol);*/
        

        // Initialize Scenes
        loginScreen();
        currentAssignments();
        homeScreen();
        assignmentScreen();
        scratchScreen();
        settingsScreen();
        calendarScreen();
        addAssignmentScreen();
        newUser();
        homeScreen();

        window.setScene(login);
        window.show();

    }
    
    private static void currentAssignments()
    {
    	 TableColumn<Assignments, String> col1 = new TableColumn<>("Current Assignments");
         TableColumn<Assignments, String> col2 = new TableColumn<>("Statistics");
         col1.setMinWidth(screenSize.getWidth()/2-100);
         col1.setCellValueFactory(new PropertyValueFactory<>("name"));
         col2.setMinWidth(screenSize.getWidth()/2-100);
         
         
         for (Assignment a: AM.getAssignments()){
        	 System.out.println(a.name());
         }
         
         assignmentManager = new TableView<Assignments>();
         assignmentManager.setMinHeight(screenSize.getHeight()-50);
         assignmentManager.setItems(assignments);
         assignmentManager.getColumns().addAll(col1);
    }
    
    private static VBox navBarButtons()
    {
    	int buttonWidth = 150;
    	VBox navBar;
    	
    	// Logout Button
        logoutButton = new Button("Log Out");
        logoutButton.setOnAction(e -> logout(login));
        logoutButton.setMaxWidth(Double.MAX_VALUE);
    	
    	// Home Back Button
        homeButton = new Button("Home");
        homeButton.setOnAction(e -> goToHome(home));
        homeButton.setMaxWidth(Double.MAX_VALUE);

    	// Add Calendar
        calendarButton = new Button("Calendar");
        calendarButton.setOnAction(e -> window.setScene(calendar));
        calendarButton.setMaxWidth(Double.MAX_VALUE);
        
        // Add Assignment
        assignmentButton = new Button("Assignments");
        assignmentButton.setOnAction(e -> window.setScene(addAssignment));
        assignmentButton.setMaxWidth(Double.MAX_VALUE);

        //Add Scratch pad
        scratchpadButton = new Button("Scratchpad");
        scratchpadButton.setOnAction(e -> window.setScene(scratchpad));
        scratchpadButton.setMaxWidth(Double.MAX_VALUE);

        // Add Settings
        settingsButton = new Button("Settings");
        settingsButton.setOnAction(e -> window.setScene(settings));
        settingsButton.setMaxWidth(Double.MAX_VALUE);
        
        // Add Exit
        exitButton = new Button("Exit PASTA");
        exitButton.setOnAction(e -> System.exit(0));
        exitButton.setMaxWidth(Double.MAX_VALUE);
        
        // Populating the nav bar
        navBar = new VBox();
    	navBar.setSpacing(10);
    	navBar.setMaxWidth(buttonWidth);
    	navBar.setPadding(new Insets(20,20,10,10));
    	navBar.getChildren().addAll(homeButton, calendarButton, assignmentButton);
    	navBar.getChildren().addAll(scratchpadButton, settingsButton,logoutButton, exitButton);	
    	return navBar;
    }
    
    private static void newUser()
    {
    	//TODO create new user instance and set up AM 
    	
    	
    	VBox newUserBox = new VBox();
    	double boxWidth = 200;
    	double height = screenSize.getHeight();
    	double width = screenSize.getWidth();
    	
    	GridPane newUse = new GridPane();
        newUse.setPadding(new Insets(0, 0, 0, 0));
        newUse.setVgap(8);
        newUse.setHgap(10);
    	
    	// Sign Up Input
        TextField fNameInput = new TextField("First Name");
        fNameInput.setMaxWidth(Double.MAX_VALUE);
        
        TextField lNameInput = new TextField("Last Name");
        lNameInput.setMaxWidth(Double.MAX_VALUE);

        TextField userSignUpInput = new TextField("Username");
        userSignUpInput.setMaxWidth(Double.MAX_VALUE);

        TextField passSignUpInput = new TextField("Password");
        passSignUpInput.setMaxWidth(Double.MAX_VALUE);

        TextField confirmPassInput = new TextField("Confirm Password");
        confirmPassInput.setMaxWidth(Double.MAX_VALUE);

        createSignUpButton = new Button("Create Account");
        createSignUpButton.setMaxWidth(Double.MAX_VALUE);
        createSignUpButton.setOnAction(e -> window.setScene(home));
        
        cancelButton = new Button("Cancel");
        cancelButton.setMaxWidth(Double.MAX_VALUE);
        cancelButton.setOnAction(e -> window.setScene(login));

        newUserBox.setSpacing(10);
        // Sets the width of the login box.
    	newUserBox.setMaxWidth(boxWidth);
    	// Adds text fields and buttons to the vbox
        newUserBox.getChildren().addAll(fNameInput, lNameInput, userSignUpInput, passSignUpInput);
        newUserBox.getChildren().addAll(confirmPassInput, createSignUpButton, cancelButton);
        newUse.getChildren().addAll(newUserBox);
        // Puts the login box in the middle-ish part of the screen
        newUse.setPadding(new Insets(height/2-100, width/2-100, height/2, width/2-75));
        createUser = new Scene(newUse, screenSize.getWidth(), screenSize.getHeight());
    }
    
    private static void addAssignmentScreen(){
    	addAssignmentScreen(null);
    }
    
    protected static void addAssignmentScreen(LocalDate due)
    {
    	VBox newBox = new VBox();
    	VBox navBar = navBarButtons();
    	
    	// Sign Up Input
    	Label titleLabel = new Label("Assignment Title");
        TextField assignTitle = new TextField("Assignment Title");
        assignTitle.setMaxWidth(Double.MAX_VALUE);
        
    	Label descLabel = new Label("Description");

        TextField description = new TextField("Description");
        description.setMaxWidth(Double.MAX_VALUE);

    	Label dueLabel = new Label("Due Date");
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
        createButton.setOnAction(e -> createAssignment(assignTitle.getText(),
        		description.getText(), dueDate.getValue().format(DateTimeFormatter.ofPattern("MM/dd/yyyy")),
        		pSlide.getValue()));
        
        cancelButton = new Button("Cancel");
        cancelButton.setMaxWidth(Double.MAX_VALUE);
        cancelButton.setOnAction(e -> window.setScene(home));
    	
        // The title text on top and its alignment
    	Label header = new Label("Add Assignment");
        header.setMaxWidth(Double.MAX_VALUE);
        header.setAlignment(Pos.CENTER);
        
        newBox.setSpacing(10);
        newBox.getChildren().addAll(header, titleLabel, assignTitle, descLabel, description, 
        		dueLabel, dueDate, priorityLabel, pSlide);
        newBox.getChildren().addAll(createButton, cancelButton);
        
    	// Add Assignment Setup
        GridPane addAssign = new GridPane();
        addAssign.setPadding(new Insets(screenSize.getHeight()/2-200,0,0,screenSize.getWidth()/2-75));
        addAssign.setVgap(8);
        addAssign.setHgap(10);
        addAssign.getChildren().addAll(newBox);
    	addAssignment = new Scene(addAssign, screenSize.getWidth(), screenSize.getHeight());
    }
    
    private static void calendarScreen()
    {
    	CalendarView calendarView = new CalendarView(AM) ;
	
    	VBox navBar = navBarButtons();
    	// Calendar Page Setup
        GridPane calendarGrid = new GridPane();
        calendarGrid.setPadding(new Insets(0,0,0,0));
        calendarGrid.setVgap(8);
        calendarGrid.setHgap(10);
		
		BorderPane root = new BorderPane(calendarView.getView(), null, null, null, navBar);
        calendarGrid.getChildren().addAll(root);
        calendar = new Scene(calendarGrid, screenSize.getWidth(), screenSize.getHeight());
        
    }
    
    private static void assignmentScreen()
    {
    	VBox navBar = navBarButtons();
        // Assignment View Setup
        GridPane assignmentView = new GridPane();
        assignmentView.setPadding(new Insets(0, 0, 0, 0));
        assignmentView.setVgap(8);
        assignmentView.setHgap(10);
        assignmentView.getChildren().addAll(navBar);
    	addAssignment = new Scene(assignmentView, screenSize.getWidth(), screenSize.getHeight());
    }

    private static void scratchScreen()
    {
    	VBox navBar = navBarButtons();
    	 // Scratch pad Page Setup
        GridPane scratchpadGrid = new GridPane();
        scratchpadGrid.setPadding(new Insets(0,0,0,0));
        scratchpadGrid.setVgap(8);
        scratchpadGrid.setHgap(10);
        scratchpadGrid.getChildren().addAll(navBar);
    	scratchpad = new Scene(scratchpadGrid, screenSize.getWidth(), screenSize.getHeight());
    }
    
    private static void settingsScreen()
    {
    	VBox navBar = navBarButtons();
        GridPane settingsGrid = new GridPane();
        settingsGrid.setPadding(new Insets(0,0,0,0));
        settingsGrid.setVgap(8);
        settingsGrid.setHgap(10);
        settingsGrid.getChildren().addAll(navBar);
    	settings = new Scene(settingsGrid, screenSize.getWidth(), screenSize.getHeight());
    }
    
    // Login Page Setup
    private static void loginScreen()
    {
    	VBox loginBox = new VBox();
    	double loginWidth = 200;
    	double height = screenSize.getHeight();
    	double width = screenSize.getWidth();
    	
    	// User name Input
        TextField userInput = new TextField("Username");
        userInput.setMaxWidth(Double.MAX_VALUE);

        // Password Input
        TextField passInput = new TextField("Password");
        passInput.setMaxWidth(Double.MAX_VALUE);
        
        // Login Action
        loginButton = new Button("Log In");
        loginButton.setMaxWidth(Double.MAX_VALUE);
        loginButton.setOnAction(e -> window.setScene(home));

        // Sign Up Action
        signUpButton = new Button("Sign Up");
        signUpButton.setMaxWidth(Double.MAX_VALUE);
        signUpButton.setOnAction(e -> window.setScene(createUser));
    	
        loginBox.setSpacing(10);
    	loginBox.setMaxWidth(loginWidth);
        loginBox.getChildren().addAll(userInput, passInput, loginButton, signUpButton);
        
        GridPane loginGrid = new GridPane();
        // Puts the login box in the middle-ish part of the screen
        loginGrid.setPadding(new Insets(height/2-100, width/2-100, height/2, width/2-75));
        loginGrid.setVgap(8);
        loginGrid.setHgap(10);
        loginGrid.getChildren().addAll(loginBox);
    	login = new Scene(loginGrid, width, height);
    }

    // Home Page Setup
    private static void homeScreen()
    {
    	Button add = new Button("New Assignment");
    	VBox addBox = new VBox();
    	add.setOnAction(e -> window.setScene(addAssignment));
    	
    	addBox.getChildren().addAll(add);
    	addBox.setPadding(new Insets(screenSize.getHeight()-100,0,0,screenSize.getWidth()/2-75));
    	VBox navBar = navBarButtons();
    	// The title text on top and its alignment
    	Label header = new Label("Welcome to Home Student!");
        header.setMaxWidth(Double.MAX_VALUE);
        header.setAlignment(Pos.CENTER);
 
        GridPane homeGrid = new GridPane();
        homeGrid.setPadding(new Insets(0, 0, 0, 0));
        homeGrid.setVgap(8);
        homeGrid.setHgap(10);
        VBox homeVBox = new VBox();
        homeVBox.setPadding(new Insets(0, 10, 10, 150));
        homeVBox.getChildren().addAll(header, assignmentManager);
        homeGrid.getChildren().addAll(homeVBox, addBox, navBar);
    	home = new Scene(homeGrid, screenSize.getWidth(), screenSize.getHeight());
    }

    public void getAssignments()
    {
        assignments.add(new Assignments("Word Frequency", "5/23/2017", 100));
        assignments.add(new Assignments("Diagrams", "5/17/2017", 5));
        assignments.add(new Assignments("NGA", "6/1/2017", 1000));
        assignments.add(new Assignments("Math", "5/27/17", 16));   
    }

    public static void main(String[] args) {
        launch(args);
    }
    
    private static void createAssignment(String nam, String des, String due, double priority)
    {
    	AM.addAssignment(nam,des,due,priority);
    	currentAssignments();
    	addAssignmentScreen();
    	window.setScene(home);
    }
    private static void logout(Scene login)
    {
    	window.setScene(login);
    }
    private static void goToHome(Scene home)
    {
    	//TableView<Assignments> assignment;
    	currentAssignments();
    	window.setScene(home);
    	
    }

    @Override
    public void handle(ActionEvent event) {
        if(event.getSource() == loginButton)
        {
            System.out.println("Button1");
        }
    }
}
