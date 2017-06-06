package logic;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.time.LocalDate;
import java.util.ArrayList;

import javafx.application.Application;
import javafx.scene.layout.VBox;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.geometry.Insets;

@SuppressWarnings("deprecation")
public class Main extends Application implements EventHandler<ActionEvent>{

    static Stage window;
    static TableView<Assignment> assignmentManager;
    static TableView<String> scratchPadManager;
    protected static Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        
    protected static Scene login;
    protected static Scene home;
    protected static Scene createUser;
    protected static Scene addAssignment;
    protected static Scene calendar;
    protected static Scene scratchpad;
    protected static Scene settings;
    protected static Scene viewAssignment;
    protected static Scene account;
    protected static Scene email;
    protected static Scene password;
    
    protected static String pString = "Password";
    protected static String uString = "Username";
    protected static String cancelString = "Cancel";
    protected static String desString = "Description";
    protected static String dueString = "Due Date";
    protected static String aString = "Assignment Title";
    
    protected static User user; 
    protected static ArrayList<User> users;
    protected static AssignmentManager aM;
    private static ScratchPadManager sM;
      
    
    private static void setup(Stage prim, String sheet){
    	window=prim;
    	window.setTitle("PASTA");
        defaultUsers();
        aM = users.get(0).getAM();
        sM = users.get(0).getSM();
       
        // Initialize Scenes
        loginScreen();
        settingsScreen();
        currentAssignments();
        scratchScreen();
        calendarScreen();
        addAssignmentScreen();
        newUser();
        accountSettingsScreen();
        emailSettingsScreen();
        passSettingsScreen();

        login.getStylesheets().add(sheet);
        window.setScene(login);
        window.show();
    }
    
    @Override
    public void start(Stage primaryStage) throws Exception{
    	String styleSheet = getClass().getResource("theme.css").toExternalForm(); 
    	setup(primaryStage, styleSheet);
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
         
         TableColumn<Assignment, String> pCol = new TableColumn<> ("Priority");
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

    protected static void currentNotes() {
    	scratchPadManager = new TableView<>();
    	
    	TableColumn<String, String> notesCol = new TableColumn<>("Notes");
        notesCol.setMinWidth(screenSize.getWidth()/2-100);
    	
    	scratchPadManager.fixedCellSizeProperty();
    	scratchPadManager.setMinHeight(screenSize.getHeight() - 50);
    	scratchPadManager.setItems(sM.getNotes());
    	scratchPadManager.getColumns().addAll(notesCol);
    }

    private static VBox settingsBarButtons()
    {
        int buttonWidth = 350;
        VBox settingsBar;

        Button accountButton = new Button("Edit Account");
        accountButton.setOnAction(e -> window.setScene(account));
        accountButton.setMaxWidth(Double.MAX_VALUE);

        Button emailButton = new Button("Edit Email");
        emailButton.setOnAction(e -> window.setScene(email));
        emailButton.setMaxWidth(Double.MAX_VALUE);

        Button passwordButton = new Button("Edit Password");
        passwordButton.setOnAction(e -> window.setScene(password));
        passwordButton.setMaxWidth(Double.MAX_VALUE);


        // Populating the settings bar
        settingsBar = new VBox();
        settingsBar.setSpacing(10);
        settingsBar.setMinWidth(buttonWidth);
        settingsBar.setPadding(new Insets(20,20,10,10));
        settingsBar.getChildren().addAll(accountButton, emailButton, passwordButton);
        return settingsBar;
    }
    
    private static void newUser()
    {
    	NewUserView newUserView = new NewUserView();
    	
    	GridPane assignmentGrid = gridSetup(newUserView.getView());

        createUser = new Scene(assignmentGrid, screenSize.getWidth(), screenSize.getHeight());
    }
    
    private static void addAssignmentScreen(){
    	addAssignmentScreen(null);
    }
    
    protected static void addAssignmentScreen(LocalDate due)
    {
    	AddAssignmentView addView = new AddAssignmentView(due);
    	
        GridPane assignGrid = gridSetup(addView.getView());

        addAssignment = new Scene(assignGrid, screenSize.getWidth(), screenSize.getHeight());
    }
    
    private static void calendarScreen()
    {
    	CalendarView calendarView = new CalendarView(aM.getAssignments()) ;
	
    	NavBar navBar = new NavBar();
    	// Calendar Page Setup
        GridPane calendarGrid = grid();
		
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
        GridPane scratchpadGrid = grid();
        scratchpadGrid.getChildren().addAll(navBar);
        scratchpadGrid.add(textBox, 1,0,1,1);
        scratchpadGrid.add(saveButton, 1,1,1,1);
        
        saveButton.setOnAction(e -> {
        	sM.addNote(textBox.getText());
        	currentNotes();	
        });
    	scratchpad = new Scene(scratchpadGrid, screenSize.getWidth(), screenSize.getHeight());
    }


	private static void settingsScreen()
    {
        VBox navBar = new NavBar();
        VBox settingsBox = new VBox();
        double height = screenSize.getHeight();
        double width = screenSize.getWidth();
        settingsBox.setSpacing(10);
        settingsBox.getChildren().addAll(settingsBarButtons());
        BorderPane root = new BorderPane();
        root.setLeft(navBar);
        root.setCenter(settingsBox);
        settingsBox.setPadding(new Insets(height / 2 - 100, width / 2 - 100, height / 2, width / 2 - 275));
        settings = new Scene(root, width, height);
    }

    // Login Page Setup
    private static void loginScreen()
    {
    	LoginView loginView = new LoginView();

    	
    	GridPane loginGrid = gridSetup(loginView.getView());
        
        
        login = new Scene(loginGrid, screenSize.getWidth(), screenSize.getHeight());
    }

    // Home Page Setup
    private static void homeScreen()
    {
    	HomeView homeView = new HomeView();
    	
    	GridPane homeGrid = gridSetup(homeView.getView());

        home = new Scene(homeGrid, screenSize.getWidth(), screenSize.getHeight());
    }
    
    private static GridPane grid(){
    	GridPane grid = new GridPane();
        grid.setPadding(new Insets(0,0,0,0));
        grid.setVgap(8);
        grid.setHgap(10);
        return grid; 
    }
    
    private static GridPane gridSetup(Node v){

    	GridPane grid = grid();
 		
 		BorderPane root = new BorderPane();
 		root.setCenter(v);
        grid.getChildren().addAll(root);
        return grid;
    }
    
    // View Assignment Screen
    static void viewAssignmentScreen(Assignment toView)
    {
    	AssignmentView assignmentView = new AssignmentView(toView);
    	
    	GridPane assignmentGrid = gridSetup(assignmentView.getView());
    	
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

    	GridPane assignmentGrid = gridSetup(editView.getView());
        
        Scene editAssignment = new Scene(assignmentGrid, screenSize.getWidth(),
        		screenSize.getHeight());
    	window.setScene(editAssignment);
    }

    private static void accountSettingsScreen() {
        VBox navBar = new NavBar();
        VBox accountBox = new VBox();
        double buttonWidth = 350;

        double height = screenSize.getHeight();
        double width = screenSize.getWidth();

        Label userLabel = new Label();
        userLabel.setText("New Username: ");

        TextField userInput = new TextField("");
        userInput.setMaxWidth(buttonWidth);

        Label firstLabel = new Label();
        firstLabel.setText("First Name: ");

        TextField firstInput = new TextField("");
        firstInput.setMaxWidth(buttonWidth);

        Label lastLabel = new Label();
        lastLabel.setText("Last Name: ");

        TextField lastInput = new TextField("");
        lastInput.setMaxWidth(buttonWidth);

        Button saveButton = saveB(buttonWidth);

        Button backButton = backB(buttonWidth); 
        accountBox.setSpacing(10);
        accountBox.getChildren().addAll(userLabel, userInput, firstLabel, firstInput, lastLabel, lastInput, saveButton, backButton);

        BorderPane root = new BorderPane();

        root.setLeft(navBar);
        root.setCenter(accountBox);
        accountBox.setPadding(new Insets(height / 2 - 100, width / 2 - 175, height / 2, width / 2 - 275));
        account = new Scene(root, width, height);
    }

    private static void emailSettingsScreen() {
        VBox navBar = new NavBar();
        VBox emailBox = new VBox();
        double buttonWidth = 350;

        double height = screenSize.getHeight();
        double width = screenSize.getWidth();

        Label emailLabel = new Label();
        emailLabel.setText("Enter Current Email: ");

        TextField emailInput = new TextField("");

        Label newEmailLabel = new Label();
        newEmailLabel.setText("Enter New Email: ");

        TextField newEmailInput = new TextField("");
        newEmailInput.setMaxWidth(buttonWidth);

        Label confirmLabel = confirmL("Confirm New Email: ");

        TextField confirmInput = confirmT(buttonWidth);

        Button saveButton = saveB(buttonWidth);
        
        Button backButton = backB(buttonWidth);

        emailBox.setSpacing(10);
        emailBox.getChildren().addAll(emailLabel, emailInput, newEmailLabel, newEmailInput, confirmLabel, confirmInput, saveButton, backButton);

        BorderPane root = new BorderPane();
        root.setLeft(navBar);
        root.setCenter(emailBox);
        emailBox.setPadding(new Insets(height / 2 - 100, width / 2 - 175, height / 2.0D, width / 2 - 275));
        email = new Scene(root, width, height);
    }

    private static void passSettingsScreen() {
        VBox navBar = new NavBar();
        VBox passBox = new VBox();
        double buttonWidth = 350;

        double height = screenSize.getHeight();
        double width = screenSize.getWidth();

        Label passLabel = new Label();
        passLabel.setText("Enter Current Password: ");

        TextField passInput = new TextField("");
        passInput.setMaxWidth(buttonWidth);

        Label newPassLabel = new Label();
        newPassLabel.setText("Enter New Password: ");

        TextField newPassInput = new TextField("");
        newPassInput.setMaxWidth(buttonWidth);

        Label confirmLabel = confirmL("Confirm New Password: ");

        TextField confirmInput = confirmT(buttonWidth);

        Button saveButton = saveB(buttonWidth);
        
        Button backButton = backB(buttonWidth);

        passBox.setSpacing(10);
        passBox.getChildren().addAll(passLabel, passInput, newPassLabel, newPassInput, confirmLabel, confirmInput, saveButton, backButton);

        BorderPane root = new BorderPane();
        root.setLeft(navBar);
        root.setCenter(passBox);
        passBox.setPadding(new Insets(height / 2 - 100, width / 2 - 175, height / 2, width / 2 - 275));
        password = new Scene(root, width, height);
    }
    
    private static Label confirmL(String c) {
    	Label confirmLabel = new Label();
        confirmLabel.setText(c);
        return confirmLabel;
    }
    
    private static TextField confirmT(double buttonWidth){
    	
    
        TextField confirmInput = new TextField("");
        confirmInput.setMaxWidth(buttonWidth);
        return confirmInput;

    }
    
    private static Button saveB(double buttonWidth) {
    	
        Button saveButton = new Button("Save");
        saveButton.setMaxWidth(buttonWidth);
        saveButton.setOnAction(e -> window.setScene(settings));

        return saveButton;
        

    }
    private static Button backB(double buttonWidth){
        Button backButton = new Button("Back");

        backButton.setMaxWidth(buttonWidth);
        backButton.setOnAction(e -> window.setScene(settings));
        return backButton;
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
    			sM = user.getSM();
    			currentAssignments();
    			homeScreen();
    			window.setScene(home);
			}
    	}
    }
	@Override
	public void handle(ActionEvent event) {
		handle(event);
	}
}

