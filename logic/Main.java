package logic;
import javafx.geometry.Pos;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.geometry.Insets;

public class Main extends Application implements EventHandler<ActionEvent>{

    static Stage window;
    static TableView<Assignment> assignmentManager;
    static GridPane assignDisplay;
    static Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    
    private static Button loginButton;
    
    private static Scene login;
    private static Scene home;
    private static Scene createUser;
    protected static Scene addAssignment;
    private static Scene calendar;
    private static Scene scratchpad;
    private static Scene settings;
    private static Scene viewAssignment;
    
   
    private static User user; 
    private static ArrayList<User> users;
    private static AssignmentManager aM; 
    
    @Override
    public  void start(Stage primaryStage) throws Exception{
        window = primaryStage;
        window.setTitle("PASTA");
        defaultUsers();
        aM = users.get(0).getAM();
        
        
        // Initialize Scenes
        loginScreen();
        currentAssignments();
        assignmentScreen();
        scratchScreen();
        settingsScreen();
        calendarScreen();
        addAssignmentScreen();
        newUser();
      

        window.setScene(login);
        window.show();

    }
    
    private static void defaultUsers()
    {
    	users = new ArrayList<>();
    	users.add(new User("Username", "Password"));
    	users.add(new User("Test", "dummy"));
    	users.add(new User("Jon", "Scott"));
    	users.add(new User("Cole", "Grigsby"));
    	users.add(new User("admin", "admin"));
    	user = users.get(0);
    }
    
    private static void currentAssignments()
    {
    	
    	Assignment temp;
    	assignDisplay = new GridPane();
    	Label name = new Label("Name");
    	Label due = new Label("Due");
    	Label priority = new Label("Priority");
    	Label select = new Label("Select");
    	assignDisplay.setVgap(50);
    	assignDisplay.setHgap(100);
    	assignDisplay.add(name, 0, 0);
    	assignDisplay.add(due, 1, 0);
    	assignDisplay.add(priority, 2, 0);
    	assignDisplay.add(select, 3, 0);
    	 
    	
    	 for(int i = 0; i < aM.numAssignments(); i++)
    	 {
    		temp = aM.getAssignment(i);
    		assignDisplay.add(new Label(temp.getName()), 0, i);
    	 }
    	 
    	 TableColumn<Assignment, String> assignCol = new TableColumn<>("Current Assignments"); 	 
         assignCol.setMinWidth(screenSize.getWidth()/2-100);
         
         TableColumn<Assignment, String> nameCol = new TableColumn<> ("Name");
         nameCol.setCellValueFactory(new PropertyValueFactory<Assignment, String>("name"));
         nameCol.setMinWidth(250);
         
         TableColumn<Assignment, String> dueCol = new TableColumn<> ("Due");
         dueCol.setCellValueFactory(new PropertyValueFactory<Assignment, String>("due"));
         dueCol.setMinWidth(200);
         
         TableColumn<Assignment, Double> pCol = new TableColumn ("Priority");
         pCol.setCellValueFactory(new PropertyValueFactory<Assignment, Double>("priority"));
         pCol.setMinWidth(200);
            
         assignCol.getColumns().addAll(nameCol, dueCol, pCol);
        
         assignmentManager = new TableView<>();
         assignmentManager.setEditable(true);
         assignmentManager.fixedCellSizeProperty();
         assignmentManager.setFixedCellSize(30);
         assignmentManager.setMinHeight(screenSize.getHeight()-50);
         assignmentManager.setItems(aM.getAssignments());
         assignmentManager.getColumns().addAll(assignCol);
         
    }
    
    private static VBox navBarButtons()
    {
    	int buttonWidth = 150;
    	VBox navBar;
    	
    	// Logout Button
        Button logoutButton = new Button("Log Out");
        logoutButton.setOnAction(e -> logout(login));
        logoutButton.setMaxWidth(Double.MAX_VALUE);
    	
    	// Home Back Button
        Button homeButton = new Button("Home");
        homeButton.setOnAction(e -> goToHome(home));
        homeButton.setMaxWidth(Double.MAX_VALUE);

    	// Add Calendar
        Button calendarButton = new Button("Calendar");
        calendarButton.setOnAction(e -> window.setScene(calendar));
        calendarButton.setMaxWidth(Double.MAX_VALUE);
        
        // Add Assignment
        Button assignmentButton = new Button("New Assign");
        assignmentButton.setOnAction(e -> window.setScene(addAssignment));
        assignmentButton.setMaxWidth(Double.MAX_VALUE);

        //Add Scratch pad
        Button scratchpadButton = new Button("Scratchpad");
        scratchpadButton.setOnAction(e -> window.setScene(scratchpad));
        scratchpadButton.setMaxWidth(Double.MAX_VALUE);

        // Add Settings
        Button settingsButton = new Button("Settings");
        settingsButton.setOnAction(e -> window.setScene(settings));
        settingsButton.setMaxWidth(Double.MAX_VALUE);
        
        // Add Exit
        Button exitButton = new Button("Exit PASTA");
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
    	// create new user instance and set up AM 
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

        Button createSignUpButton = new Button("Create Account");
        createSignUpButton.setMaxWidth(Double.MAX_VALUE);
        createSignUpButton.setOnAction(e -> 
        {
        	users.add(new User(userSignUpInput.getText(),passSignUpInput.getText()));
        	window.setScene(login);
        });
        
        Button cancelButton = new Button("Cancel");
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
    	
    	// Assignment Detail Input
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
        
        Button cancelButton = new Button("Cancel");
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
        
        newBox.setPadding(new Insets(screenSize.getHeight()/2-200,0,0,screenSize.getWidth()/2-75));
    	// Add Assignment Setup
        GridPane addAssign = new GridPane();
        addAssign.setVgap(8);
        addAssign.setHgap(10);
        addAssign.getChildren().addAll(newBox, navBar);
    	addAssignment = new Scene(addAssign, screenSize.getWidth(), screenSize.getHeight());
    }
    
    private static void calendarScreen()
    {
    	CalendarView calendarView = new CalendarView(aM.getAssignments()) ;
	
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
        Button loginButton = new Button("Log In");
        loginButton.setMaxWidth(Double.MAX_VALUE);
        loginButton.setOnAction(e -> authenticate(userInput.getText(), passInput.getText()));
        
        // Sign Up Action
        Button signUpButton = new Button("Sign Up");
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
    	double buttonWidth = 150;
    	Button add = new Button("New Assignment");
    	add.setMinWidth(buttonWidth);
    	add.setOnAction(e -> window.setScene(addAssignment));
    	  	
    	VBox selectButtons = new VBox();
    	
        Button s1 = new Button("Select");
        s1.setMinWidth(buttonWidth);
        s1.setOnAction(e -> viewAssignmentScreen(aM.getAssignment(0)));
        
        Button s2 = new Button("Select");
        s2.setOnAction(e -> viewAssignmentScreen(aM.getAssignment(1)));
        s2.setMinWidth(buttonWidth);
        
        Button s3 = new Button("Select");
        s3.setMinWidth(buttonWidth);
        s3.setOnAction(e -> viewAssignmentScreen(aM.getAssignment(2)));
        
        Button s4 = new Button("Select");
        s4.setMinWidth(buttonWidth);
        s4.setOnAction(e -> viewAssignmentScreen(aM.getAssignment(3)));
        
        Button s5 = new Button("Select");
        s5.setMinWidth(buttonWidth);
        s5.setOnAction(e -> viewAssignmentScreen(aM.getAssignment(4)));
        
        Button s6 = new Button("Select");
        s6.setMinWidth(buttonWidth);
        s6.setOnAction(e -> viewAssignmentScreen(aM.getAssignment(5)));
        
        Button s7 = new Button("Select");
        s7.setMinWidth(buttonWidth);
        s7.setOnAction(e -> viewAssignmentScreen(aM.getAssignment(6)));
        
        Button s8 = new Button("Select");
        s8.setMinWidth(buttonWidth);
        s8.setOnAction(e -> viewAssignmentScreen(aM.getAssignment(7)));
        
        selectButtons.getChildren().addAll(add, s1,s2,s3,s4,s5,s6,s7,s8);
        selectButtons.setPadding(new Insets(25,0,0,0));
        selectButtons.setSpacing(3);
    	
    	//addBox.getChildren().addAll(sort, add);
    	//addBox.setPadding(new Insets(17,0,0,150));
    	VBox navBar = navBarButtons();
    	
    	// The title text on top and its alignment
    	Label header = new Label("Welcome to Home, " + user.getName());
        header.setMaxWidth(Double.MAX_VALUE);
        header.setAlignment(Pos.CENTER);
 
        GridPane homeGrid = new GridPane();
        homeGrid.setPadding(new Insets(0, 0, 0, 0));
        homeGrid.setVgap(8);
        homeGrid.setHgap(10);
        BorderPane root = new BorderPane();
        root.setLeft(navBar);
        //root.setTop(addBox);
        root.setRight(selectButtons);
        root.setCenter(assignmentManager);
        //homeVBox.setPadding(new Insets(20, 10, 10, 100));
       // homeVBox.getChildren().addAll(header, assignmentManager);
        //homeVBox.getChildren().addAll(header, assignDisplay);
        homeGrid.getChildren().addAll(root);
    	home = new Scene(homeGrid, screenSize.getWidth(), screenSize.getHeight());
    }
    
    // View Assignment Screen
    static void viewAssignmentScreen(Assignment toView)
    {
    	
    	VBox newBox = new VBox();
    	VBox navBar = navBarButtons();
    	
    	// Sign Up Input
    	Label titleLabel = new Label("Assignment Title");
        Label assignTitle = new Label(toView.getName());
        assignTitle.setMaxWidth(Double.MAX_VALUE);
        
    	Label descLabel = new Label("Description");
        Label description = new Label(toView.description());
        description.setMaxWidth(Double.MAX_VALUE);

    	Label dueLabel = new Label("Due Date");
    	
    	Label priorityLabel = new Label("Current Priority: " + toView.getPriority()); 
    	

        Button editButton = new Button("Modify Assignment");
        editButton.setMaxWidth(Double.MAX_VALUE);
        editButton.setOnAction(e -> editAssignment(toView));
        
        Button completeButton = new Button("Mark Complete");
        completeButton.setMaxWidth(Double.MAX_VALUE);
        completeButton.setOnAction(e -> markComplete(toView));
        
        Button deleteButton = new Button("Delete");
        deleteButton.setMaxWidth(Double.MAX_VALUE);
        deleteButton.setOnAction(e -> deleteAssignment(toView));
        
        // The title text on top and its alignment
    	Label header = new Label("View Assignment");
        header.setMaxWidth(Double.MAX_VALUE);
        header.setAlignment(Pos.CENTER);
        
        newBox.setSpacing(10);
        newBox.getChildren().addAll(header, titleLabel, assignTitle, descLabel, description, 
        		dueLabel, priorityLabel);
        newBox.getChildren().addAll(editButton, completeButton, deleteButton);
        
        newBox.setPadding(new Insets(screenSize.getHeight()/2-200,0,0,screenSize.getWidth()/2-75));
    	// Add Assignment Setup
        GridPane addAssign = new GridPane();
        addAssign.setVgap(8);
        addAssign.setHgap(10);
        addAssign.getChildren().addAll(newBox, navBar);
    	viewAssignment = new Scene(addAssign, screenSize.getWidth(), screenSize.getHeight());
    	window.setScene(viewAssignment);
    }

    public static void main(String[] args) {
        launch(args);
    }
    
   
    private static void createAssignment(String nam, String des, String due, double priority)
    {
    	aM.addAssignment(nam,des,due,priority);
    	currentAssignments();
    	addAssignmentScreen();
    	window.setScene(home);
    }
    private static void logout(Scene login)
    {
    	window.setScene(login);
    }
    
    private static void editAssignment(Assignment a)
    {
    	VBox newBox = new VBox();
    	VBox navBar = navBarButtons();
    	
    	// Assignment Detail Input
    	Label titleLabel = new Label("Assignment Title");
        TextField assignTitle = new TextField(a.getName());
        assignTitle.setMaxWidth(Double.MAX_VALUE);
        
    	Label descLabel = new Label("Description");

        TextField description = new TextField(a.description());
        description.setMaxWidth(Double.MAX_VALUE);

    	Label dueLabel = new Label("Due Date");
    	DatePicker dueDate = new DatePicker(); 
    	dueDate.setValue(LocalDate.now());

    	Label priorityLabel = new Label("Priority (1-10)");        
    	
    	// Create a slider to get a number value
    	Slider pSlide = new Slider();
        pSlide.setMin(1);
        pSlide.setMax(10);
        pSlide.setValue(a.getPriority());
        pSlide.setShowTickLabels(true);
        pSlide.setShowTickMarks(true);
        pSlide.setMajorTickUnit(1);
        pSlide.setMinorTickCount(0);
        pSlide.setSnapToTicks(true);
        pSlide.setMaxWidth(Double.MAX_VALUE);
      

        Button saveButton = new Button("Save");
        saveButton.setMaxWidth(Double.MAX_VALUE);
        saveButton.setOnAction(e -> {
        	createAssignment(assignTitle.getText(), description.getText(),
        			dueDate.getValue().format(DateTimeFormatter.ofPattern("MM/dd/yyyy")),
            		pSlide.getValue());
            deleteAssignment(a);
        });
        		
        
        Button cancelButton = new Button("Cancel");
        cancelButton.setMaxWidth(Double.MAX_VALUE);
        cancelButton.setOnAction(e -> window.setScene(viewAssignment));
    	
        // The title text on top and its alignment
    	Label header = new Label("Edit Assignment");
        header.setMaxWidth(Double.MAX_VALUE);
        header.setAlignment(Pos.CENTER);
        
        newBox.setSpacing(10);
        newBox.getChildren().addAll(header, titleLabel, assignTitle, descLabel, description, 
        		dueLabel, dueDate, priorityLabel, pSlide);
        newBox.getChildren().addAll(saveButton, cancelButton);
        
        newBox.setPadding(new Insets(screenSize.getHeight()/2-200,0,0,screenSize.getWidth()/2-75));
    	// Add Assignment Setup
        GridPane addAssign = new GridPane();
        addAssign.setVgap(8);
        addAssign.setHgap(10);
        addAssign.getChildren().addAll(newBox, navBar);
    	Scene editAssignment = new Scene(addAssign, screenSize.getWidth(), screenSize.getHeight());
    	window.setScene(editAssignment);
    }
    
    private static void goToHome(Scene home)
    {
    	currentAssignments();
    	window.setScene(home);
    	
    }
    
    
    private static void deleteAssignment(Assignment a)
    {
    	aM.deleteAssignment(a);
    	window.setScene(home);
    }
    
    private static void markComplete(Assignment a)
    {
    	aM.markComplete(a);
    	window.setScene(home);
    }
    
    private static void authenticate(String username, String password)
    {
    	for(User a: users)
    	{
    		if(a.getName().equals(username) && a.getPassword().equals(password))
			{
    			user = a;
    			aM = user.getAM(); 
    			currentAssignments();
    			homeScreen();
    			window.setScene(home);
			}
    	}
    }
    

    @Override
    public void handle(ActionEvent event) {
        if(event.getSource() == loginButton)
        {
            System.out.println("Button1");
        }
    }
}
