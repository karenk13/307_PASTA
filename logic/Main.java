package logic;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.time.LocalDate;
import java.util.ArrayList;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.geometry.Insets;

public class Main extends Application implements EventHandler<ActionEvent>{

    static Stage window;
    static TableView<Assignment> assignmentManager;
    protected static Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    
    private static Button loginButton;
    
    protected static Scene login;
    protected static Scene home;
    protected static Scene createUser;
    protected static Scene addAssignment;
    protected static Scene calendar;
    protected static Scene scratchpad;
    protected static Scene settings;
    protected static Scene viewAssignment;
    
    protected static String pString = "Password";
    protected static String uString = "Username";
    protected static String cancelString = "Cancel";
    protected static String desString = "Description";
    protected static String dueString = "Due Date";
    protected static String aString = "Assignment Title";
    
    protected static User user; 
    protected static ArrayList<User> users;
    protected static AssignmentManager aM;
    private static ScratchPadManager scratchManager;
      
    @Override
    public  void start(Stage primaryStage) throws Exception{
        window = primaryStage;
        window.setTitle("PASTA");
        defaultUsers();
        aM = users.get(0).getAM();
       
        // Initialize Scenes
        loginScreen();
        currentAssignments();
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
    	users.add(new User(uString, pString));
    	users.add(new User("Test", "dummy"));
    	users.add(new User("Jon", "Scott"));
    	users.add(new User("Cole", "Grigsby"));
    	users.add(new User("admin", "admin"));
    	user = users.get(0);
    }
    
    protected static void currentAssignments()
    {
    	 
    	 TableColumn<Assignment, String> assignCol = new TableColumn<>("Current Assignments"); 	 
         assignCol.setMinWidth(screenSize.getWidth()/2-100);
         
         TableColumn<Assignment, String> nameCol = new TableColumn<> ("Name");
         nameCol.setCellValueFactory(new PropertyValueFactory<Assignment, String>("name"));
         nameCol.setMinWidth(250);
         
         TableColumn<Assignment, String> dueCol = new TableColumn<> ("Due");
         dueCol.setCellValueFactory(new PropertyValueFactory<Assignment, String>("due"));
         dueCol.setMinWidth(200);
         
         TableColumn<Assignment, String> pCol = new TableColumn<Assignment, String> ("Priority");
         pCol.setCellValueFactory(new PropertyValueFactory<Assignment, String>("priority"));
         pCol.setMinWidth(200);
            
         assignCol.getColumns().addAll(nameCol, dueCol, pCol);
        
         assignmentManager = new TableView<>();
         assignmentManager.fixedCellSizeProperty();
         assignmentManager.setFixedCellSize(30);
         assignmentManager.setMinHeight(screenSize.getHeight()-50);
         assignmentManager.setRowFactory( tv -> {
        	    TableRow<Assignment> row = new TableRow<>();
        	    row.setOnMouseClicked(event -> {
        	        if (! row.isEmpty() ) {
        	            viewAssignmentScreen(row.getItem());
        	        }
        	    });
        	    return row ;
        	});
         
         assignmentManager.setItems(aM.getAssignments());
         assignmentManager.getColumns().addAll(assignCol);
         
    }
    
    private static void newUser()
    {
    	NewUserView newUserView = new NewUserView();
    	
    	// Add Assignment Setup
        GridPane assignGrid = new GridPane();
        assignGrid.setPadding(new Insets(0,0,0,0));
        assignGrid.setVgap(8);
        assignGrid.setHgap(10);
		
		BorderPane root = new BorderPane();
		root.setCenter(newUserView.getView());
        assignGrid.getChildren().addAll(root);
        createUser = new Scene(assignGrid, screenSize.getWidth(), screenSize.getHeight());
    }
    
    private static void addAssignmentScreen(){
    	addAssignmentScreen(null);
    }
    
    protected static void addAssignmentScreen(LocalDate due)
    {
    	AddAssignmentView addView = new AddAssignmentView(due);
    	
    	// Add Assignment Setup
        GridPane assignGrid = new GridPane();
        assignGrid.setPadding(new Insets(0,0,0,0));
        assignGrid.setVgap(8);
        assignGrid.setHgap(10);
		
		BorderPane root = new BorderPane();
		root.setCenter(addView.getView());
        assignGrid.getChildren().addAll(root);
        addAssignment = new Scene(assignGrid, screenSize.getWidth(), screenSize.getHeight());
    }
    
    private static void calendarScreen()
    {
    	CalendarView calendarView = new CalendarView(aM.getAssignments()) ;
	
    	NavBar navBar = new NavBar();
    	// Calendar Page Setup
        GridPane calendarGrid = new GridPane();
        calendarGrid.setPadding(new Insets(0,0,0,0));
        calendarGrid.setVgap(8);
        calendarGrid.setHgap(10);
		
		BorderPane root = new BorderPane(calendarView.getView(), null, null, null, navBar);
        calendarGrid.getChildren().addAll(root);
        calendar = new Scene(calendarGrid, screenSize.getWidth(), screenSize.getHeight());
        
    }
    
    private static void scratchScreen()
    {
    	NavBar navBar = new NavBar();
    	TextArea textBox = new TextArea();
        Button saveButton = new Button("Save Note");
    	 // Scratch pad Page Setup
        GridPane scratchpadGrid = new GridPane();
        scratchpadGrid.setPadding(new Insets(0,0,0,0));
        scratchpadGrid.setVgap(8);
        scratchpadGrid.setHgap(10);
        scratchpadGrid.getChildren().addAll(navBar);
        scratchpadGrid.add(textBox, 1,0,1,1);
        scratchpadGrid.add(saveButton, 1,1,1,1);
        
        saveButton.setOnAction(e -> saveNote(textBox.getText()));
    	scratchpad = new Scene(scratchpadGrid, screenSize.getWidth(), screenSize.getHeight());
    }
    
    private static void saveNote(String text) {
    	//TODO Check if adding to notes correctly
    	scratchManager.addNote(text);
    	
	}

	private static void settingsScreen()
    {
    	NavBar navBar = new NavBar();
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
    	LoginView loginView = new LoginView();
    	
    	GridPane loginGrid = new GridPane();
        loginGrid.setPadding(new Insets(0,0,0,0));
        loginGrid.setVgap(8);
        loginGrid.setHgap(10);
 		
 		BorderPane root = new BorderPane();
 		root.setCenter(loginView.getView());
        loginGrid.getChildren().addAll(root);
        login = new Scene(loginGrid, screenSize.getWidth(), screenSize.getHeight());
    }

    // Home Page Setup
    private static void homeScreen()
    {
    	HomeView homeView = new HomeView();
    	
    	GridPane homeGrid = new GridPane();
        homeGrid.setPadding(new Insets(0,0,0,0));
        homeGrid.setVgap(8);
        homeGrid.setHgap(10);
 		
 		BorderPane root = new BorderPane();
 		root.setCenter(homeView.getView());
        homeGrid.getChildren().addAll(root);
        home = new Scene(homeGrid, screenSize.getWidth(), screenSize.getHeight());
    }
    
    // View Assignment Screen
    static void viewAssignmentScreen(Assignment toView)
    {
    	AssignmentView assignmentView = new AssignmentView(toView);
    	
    	GridPane assignmentGrid = new GridPane();
        assignmentGrid.setPadding(new Insets(0,0,0,0));
        assignmentGrid.setVgap(8);
        assignmentGrid.setHgap(10);
 		
 		BorderPane root = new BorderPane();
 		root.setCenter(assignmentView.getView());
        assignmentGrid.getChildren().addAll(root);
        viewAssignment = new Scene(assignmentGrid, screenSize.getWidth(), screenSize.getHeight());
    	window.setScene(viewAssignment);
    }
    
    public static void main(String[] args) {
        launch(args);
    }
    
   
    protected static void createAssignment(String nam, String des, String due, double priority)
    {
    	aM.addAssignment(nam,des,due,priority);
    	currentAssignments();
    	addAssignmentScreen();
    	window.setScene(home);
    }
    
    protected static void editAssignment(Assignment a)
    {
    	EditView editView = new EditView(a);

    	GridPane assignmentGrid = new GridPane();
        assignmentGrid.setPadding(new Insets(0,0,0,0));
        assignmentGrid.setVgap(8);
        assignmentGrid.setHgap(10);
 		
 		BorderPane root = new BorderPane();
 		root.setCenter(editView.getView());
        assignmentGrid.getChildren().addAll(root);
        
        Scene editAssignment = new Scene(assignmentGrid, screenSize.getWidth(),
        		screenSize.getHeight());
    	window.setScene(editAssignment);
    }
      
    protected static void deleteAssignment(Assignment a)
    {
    	aM.deleteAssignment(a);
    	window.setScene(home);
    }
    
    protected static void markComplete(Assignment a)
    {
    	aM.markComplete(a);
    	window.setScene(home);
    }
    
    protected static void authenticate(String username, String password)
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
