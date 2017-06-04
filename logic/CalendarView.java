package logic;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.time.format.TextStyle;
import java.time.temporal.ChronoUnit;
import java.time.temporal.WeekFields;
import java.util.Locale;

import javafx.beans.binding.Bindings;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.css.PseudoClass;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;


public class CalendarView {
    private final ObjectProperty<YearMonth> month = new SimpleObjectProperty<>();
    private final ObjectProperty<Locale> locale = new SimpleObjectProperty<>(Locale.getDefault());

	private final BorderPane view ;
    private final GridPane calendar ;
    private final ObservableList<Assignment> assignments;
    
    public CalendarView(YearMonth month, ObservableList<Assignment> aM) {
        view = new BorderPane();
        view.getStyleClass().add("calendar");
        calendar = new GridPane();
        calendar.getStyleClass().add("calendar-grid");

        Label header = new Label();
        header.setMaxWidth(Double.MAX_VALUE);
        header.getStyleClass().add("calendar-header");
        
        this.month.addListener((obs, oldMonth, newMonth) -> 
            changeDate());
        
        this.assignments = aM;
        
        assignments.addListener(new ListChangeListener<Assignment>(){

			@Override
			public void onChanged(Change c) {
				changeDate();
			}
        	
        });
       
		Button next = new Button(">");
		next.setOnAction(e -> this.nextMonth());

		Button previous = new Button("<");
		previous.setOnAction(e -> this.previousMonth());
		
		 view.setTop(header);
	     view.setCenter(calendar);
	     view.setLeft(previous);
	     view.setRight(next);
		
        view.getStylesheets().add(getClass().getResource("calendar.css").toExternalForm());
        
        setMonth(month);
        header.textProperty().bind(Bindings.createStringBinding(() -> 
            this.month.get().format(DateTimeFormatter.ofPattern("MMMM yyyy")), 
            this.month));
    }
    public CalendarView(ObservableList<Assignment> aM) {
        this(YearMonth.now(), aM) ;
    }
    public CalendarView() {
        this(YearMonth.now(), null) ;
    }
    
    public void nextMonth() {
        month.set(month.get().plusMonths(1));
    }
    
    public void previousMonth() {
        month.set(month.get().minusMonths(1));
    }
    public void setMonth(YearMonth month){
    	this.month.set(month);
    }
    
    private void changeDate() {
        calendar.getChildren().clear();
        
        WeekFields weekFields = WeekFields.of(locale.get());
        
        LocalDate first = month.get().atDay(1);
        
        int dayOfWeekOfFirst = first.get(weekFields.dayOfWeek()) ;
        
        // column headers:
        for (int dayOfWeek = 1 ; dayOfWeek <= 7 ; dayOfWeek++) {
            LocalDate date = first.minusDays((long)dayOfWeekOfFirst - dayOfWeek);
            DayOfWeek day = date.getDayOfWeek() ;
            Label label = new Label(day.getDisplayName(TextStyle.SHORT_STANDALONE, locale.get()));
            label.getStyleClass().add("calendar-day-header");
            GridPane.setHalignment(label, HPos.CENTER);
            calendar.add(label, dayOfWeek - 1, 0);
        }
        
        LocalDate firstDisplayedDate = first.minusDays((long)dayOfWeekOfFirst - 1);
        LocalDate last = month.get().atEndOfMonth() ;
        int dayOfWeekOfLast = last.get(weekFields.dayOfWeek());
        LocalDate lastDisplayedDate = last.plusDays(7 - (long)dayOfWeekOfLast);
        
        PseudoClass beforeMonth = PseudoClass.getPseudoClass("before-display-month");
        PseudoClass afterMonth = PseudoClass.getPseudoClass("after-display-month");
                
        for (LocalDate date = firstDisplayedDate ; ! date.isAfter(lastDisplayedDate) ; date = date.plusDays(1)) {
            Label label = new Label(String.valueOf(date.getDayOfMonth()));
            label.getStyleClass().add("calendar-cell");
            final HBox hbox = new HBox();
            hbox.setMinSize(100,100);
            BorderPane b = new BorderPane(); 
            b.setTop(label);
            GridPane g = new GridPane();
            int i = 0;
            // get a list of assignments due on each day 
            for (Assignment a: AssignmentManager.getAssignmentsOnDate(assignments, date)){
            	Label ass = new Label(a.getName());
            	ass.setOnMouseClicked(new EventHandler<MouseEvent>()
            	{
	                @Override
	                public void handle(MouseEvent t) {	                	
	                    // VIEW ASSIGNEMT 
	                }
            	});
            	g.addRow(i++, ass);
            }
            b.setCenter(g);

            hbox.getChildren().add(b);
            hbox.setStyle("-fx-border-color: black;");
            final LocalDate dt = date;  
            hbox.setOnMouseClicked(new EventHandler<MouseEvent>()
            {
                @Override
                public void handle(MouseEvent t) {
                    Main.addAssignmentScreen(dt); 
                    Main.window.setScene(Main.addAssignment);
                }
            });
            label.pseudoClassStateChanged(beforeMonth, date.isBefore(first));
            label.pseudoClassStateChanged(afterMonth, date.isAfter(last));

            GridPane.setHalignment(hbox, HPos.CENTER);
            
            int dayOfWeek = date.get(weekFields.dayOfWeek()) ;
            int daysSinceFirstDisplayed = (int) firstDisplayedDate.until(date, ChronoUnit.DAYS);
            int weeksSinceFirstDisplayed = daysSinceFirstDisplayed / 7 ;
            
            calendar.add(hbox, dayOfWeek - 1, weeksSinceFirstDisplayed + 1);
        }
    }
    

    public Node getView() {
        return view ;
    }

}
