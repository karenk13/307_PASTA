package logic;

import java.util.Calendar;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Assignment implements Comparable<Assignment>
{
   private String name;
   private String description;
   private String category;
   private LocalDate dueDate;
   private Calendar createDate;
   private boolean complete;
   private boolean activeAssignment;
   private long timeSpentOnAssignment;
   private long startWorkAssignmentTime;
   private double priority; 
   
   // Constructors
   public Assignment(String name, LocalDate due, long estTime, String desc)
   {
	  this.name=name;
      createDate = Calendar.getInstance();
      dueDate=due; 
      complete = false;
      activeAssignment = false;
      timeSpentOnAssignment = 0;
      description = desc;
   }
   
   public Assignment(String n, String d, LocalDate du, double p)
   {
	   name=n;
	   description=d; 
	   dueDate=du;
	   priority=p; 
   }
   
   public void updateAssignment(String n, String d, LocalDate du, double p)
   {
	   name=n;
	   description=d; 
	   dueDate=du;
	   priority=p;  
   }
   
   @Override
   public int compareTo(Assignment a) 
   {
	   if(a.priority > priority)
	   {
		   return 1;
	   }
	   else if(a.priority < priority)
	   {
		   return -1;
	   }
	   return 0;
   }
   
   @Override
   public boolean equals(Object o){
	   if (o != null){
		   if (this.getClass() != o.getClass())
			    return false;
		   Assignment a = (Assignment)o;
		   return a.name.equals(this.name) && a.dueDate.equals(this.dueDate);
	   }
	   return false;
   }
   
   @Override
   public int hashCode() {
	   return (name+dueDate.toString()).hashCode();
   }
   

   // Accessor Methods
   public String getName()
   {
      return name;
   }

   public String getPriority()
   {
	   return Double.toString(priority);
   }
   public String getDue()
   {
	   DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMMM dd yyyy");
	   return dueDate.format(formatter);
   }

   public String description()
   {
      return description;
   }

   public String category()
   {
      return category;
   }

   public boolean isActive()
   {
      return activeAssignment;
   }
   
   public boolean isComplete()
   {
      return complete;
   }
   public LocalDate dueDate(){
	   return dueDate;
   }
   
   public long timeSpentOnAssignment()
   {
      return timeSpentOnAssignment;
   }
   // End Accessor Methods
   
   // Modifier Methods
   public void editName(String newName)
   {
      name = newName;
   }

   public void editDescription(String newDescription)
   {
      description = newDescription;
   }

   public void editCategory(String newCategory)
   {
      category = newCategory;
   }

   public void editDueDate(LocalDate newDueDate)
   {
      dueDate = newDueDate;
   }

   public void markComplete()
   {
      complete = true;
   }
   // End Modifier Methods

   public void workOnAssignment()
   {
      activeAssignment = true;
      Calendar cal = Calendar.getInstance();
      startWorkAssignmentTime = cal.getTimeInMillis();
   }

   public void pauseWorkOnAssignment()
   {
      activeAssignment = false;
      Calendar cal = Calendar.getInstance();
      timeSpentOnAssignment += Math.abs(startWorkAssignmentTime - cal.getTimeInMillis());
   }

   @Override
   public String toString()
   {
      return createDate.toString();
   }
}
