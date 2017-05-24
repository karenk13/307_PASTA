package pasta;

import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.collections.FXCollections;
import javafx.scene.Scene;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.geometry.Insets;

public class Main extends Application implements EventHandler<ActionEvent>{

    Stage window;
    TableView<Assignments> assignment;

    Button loginButton, signUpButton, createSignUpButton, homeBackButton, calendarBackButton, scratchBackButton, settingsBackButton;
    Button assignmentButton, calendarButton, scratchpadButton, settingsButton;
    Scene login, home, create, addAssignment, calendar, scratchpad, settings;

    @Override
    public void start(Stage primaryStage) throws Exception{
        window = primaryStage;
        window.setTitle("PASTA");


        // Login Page Setup
        GridPane loginGrid = new GridPane();
        loginGrid.setPadding(new Insets(400, 100, 100, 370));
        loginGrid.setVgap(8);
        loginGrid.setHgap(10);

        // Sign Up Page Setup
        GridPane createGrid = new GridPane();
        createGrid.setPadding(new Insets(400, 100, 100, 370));
        createGrid.setVgap(8);
        createGrid.setHgap(10);

        // Home Page Setup
        GridPane homeGrid = new GridPane();
        homeGrid.setPadding(new Insets(100, 400, 100, 50));
        homeGrid.setVgap(8);
        homeGrid.setHgap(10);

        // Calendar Page Setup
        GridPane calendarGrid = new GridPane();
        createGrid.setPadding(new Insets(100, 400, 100, 50));
        createGrid.setVgap(8);
        createGrid.setHgap(10);

        // Scratchpad Page Setup
        GridPane scratchpadGrid = new GridPane();
        createGrid.setPadding(new Insets(400, 100, 100, 370));
        createGrid.setVgap(8);
        createGrid.setHgap(10);

        // Settings Page Setup
        GridPane settingsGrid = new GridPane();
        createGrid.setPadding(new Insets(400, 100, 100, 370));
        createGrid.setVgap(8);
        createGrid.setHgap(10);


        // Sign Up Input

        TextField fNameInput = new TextField("First Name");
        GridPane.setConstraints(fNameInput, 0, 0);

        TextField lNameInput = new TextField("Last Name");
        GridPane.setConstraints(lNameInput, 0,1);

        TextField userSignUpInput = new TextField("Username");
        GridPane.setConstraints(userSignUpInput, 0,2);

        TextField passSignUpInput = new TextField("Password");
        GridPane.setConstraints(passSignUpInput, 0, 3);

        TextField confirmPassInput = new TextField("Confirm Password");
        GridPane.setConstraints(confirmPassInput, 0,4);

        createSignUpButton = new Button("Create Account");
        GridPane.setConstraints(createSignUpButton, 0,5);
        createSignUpButton.setOnAction(e -> window.setScene(home));

        createGrid.getChildren().addAll(fNameInput, lNameInput,
                userSignUpInput, passSignUpInput, confirmPassInput, createSignUpButton);



        // Username Input
        TextField userInput = new TextField("Username");
        GridPane.setConstraints(userInput, 0,0);


        // Password Input
        TextField passInput = new TextField("Password");
        GridPane.setConstraints(passInput, 0,1);


        // Login Action
        loginButton = new Button("Log In");
        GridPane.setConstraints(loginButton,0,2);
        loginButton.setOnAction(e -> window.setScene(home));


        // Sign Up Action
        signUpButton = new Button("Sign Up");
        GridPane.setConstraints(signUpButton, 0,3);
        signUpButton.setOnAction(e -> window.setScene(create));

        loginGrid.getChildren().addAll(userInput, passInput, loginButton, signUpButton);



        // Back Button
        homeBackButton = new Button("Back");
        GridPane.setConstraints(homeBackButton, 0, 0);
        homeBackButton.setOnAction(e -> window.setScene(login));

        // Calendar Back Button
        calendarBackButton = new Button("Back");
        GridPane.setConstraints(calendarBackButton, 0, 0);
        calendarBackButton.setOnAction(e -> window.setScene(home));

        scratchBackButton = new Button("Back");
        GridPane.setConstraints(scratchBackButton, 0, 0);
        scratchBackButton.setOnAction(e -> window.setScene(home));

        settingsBackButton = new Button("Back");
        GridPane.setConstraints(settingsBackButton, 0, 0);
        settingsBackButton.setOnAction(e -> window.setScene(home));

        // Add Calendar
        calendarButton = new Button("Calendar");
        GridPane.setConstraints(calendarButton, 0, 2);
        calendarButton.setOnAction(e -> window.setScene(calendar));

        // Add Assignment
        assignmentButton = new Button("Add Assignment");
        GridPane.setConstraints(assignmentButton, 0, 4);
        assignmentButton.setOnAction(e -> window.setScene(addAssignment));

        //Add Scratchpad
        scratchpadButton = new Button("Scratchpad");
        GridPane.setConstraints(scratchpadButton, 0, 6);
        scratchpadButton.setOnAction(e -> window.setScene(scratchpad));


        // Add Settings
        settingsButton = new Button("Settings");
        GridPane.setConstraints(settingsButton, 0, 8);
        settingsButton.setOnAction(e -> window.setScene(settings));




        homeGrid.getChildren().addAll(assignmentButton, calendarButton, scratchpadButton, settingsButton, homeBackButton);
        calendarGrid.getChildren().addAll(calendarBackButton);
        scratchpadGrid.getChildren().addAll(scratchBackButton);
        settingsGrid.getChildren().addAll(settingsBackButton);


        // Assignment View
        TableColumn<Assignments, String> nameCol = new TableColumn<>("Assignment");
        nameCol.setMinWidth(200);
        nameCol.setCellValueFactory(new PropertyValueFactory<>("name"));

        TableColumn<Assignments, String> dueDateCol = new TableColumn<>("Due Date");
        dueDateCol.setMinWidth(200);
        dueDateCol.setCellValueFactory(new PropertyValueFactory<>("dueDate"));

        TableColumn<Assignments, Integer> priorityCol = new TableColumn<>("Priority");
        priorityCol.setMinWidth(200);
        priorityCol.setCellValueFactory(new PropertyValueFactory<>("priority"));

        assignment = new TableView<>();
        assignment.setItems(getAssignments());
        assignment.getColumns().addAll(nameCol, dueDateCol, priorityCol);

        VBox assignmentView = new VBox();
        assignmentView.getChildren().addAll(assignment);


        // Scenes
        login = new Scene(loginGrid, 1000, 1000);
        home = new Scene(homeGrid, 1000, 1000);
        create = new Scene(createGrid, 1000, 1000);
        calendar = new Scene(calendarGrid, 1000, 1000);
        addAssignment = new Scene(assignmentView, 1000, 1000);
        scratchpad = new Scene(scratchpadGrid, 1000, 1000);
        settings = new Scene(settingsGrid, 1000, 1000);


        window.setScene(login);
        window.show();

    }


    public ObservableList<Assignments> getAssignments()
    {
        ObservableList<Assignments> assignments = FXCollections.observableArrayList();
        assignments.add(new Assignments("Word Frequency", "5/23/2017", 100));
        assignments.add(new Assignments("Diagrams", "5/17/2017", 5));
        assignments.add(new Assignments("NGA", "6/1/2017", 1000));
        assignments.add(new Assignments("Math", "5/27/17", 16));

        return assignments;
    }

    public static void main(String[] args) {
        launch(args);
    }


    @Override
    public void handle(ActionEvent event) {
        if(event.getSource() == loginButton)
        {
            System.out.println("Button1");
        }
    }
}
