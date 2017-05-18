package logic;

import java.util.concurrent.TimeUnit;
import java.util.Calendar;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
public class Assignment
{
   private String name;
   private String description;
   private String category;
   private Calendar dueDate;
   private Calendar createDate;
   private DateFormat dateFormat;
   private boolean complete;
   private boolean activeAssignment;
   private long timeSpentOnAssignment;
   private long estimatedTime;
   private long startWorkAssignmentTime;
   
   // Constructors
   public Assignment(String name, Date due, long estTime, String desc)
   {
      createDate = Calendar.getInstance();
      dueDate = Calendar.getInstance();
      dueDate.set(due.getYear(), due.getMonth(), due.getDay(),due.getHour(), due.getMinute(), due.getSecond());
      dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
      complete = false;
      activeAssignment = false;
      timeSpentOnAssignment = 0;
      estimatedTime = estTime;
      description = desc;
   }

   // Accessor Methods
   public String name()
   {
      return name;
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

   public void editDueDate(Calendar newDueDate)
   {
      dueDate = newDueDate;
   }

   public void markComplete()
   {
      complete = true;
   }
   // End Modifier Methods

   private Calendar dateToCalendar(Date date)
   {
      dueDate.set(2018,10,16, 23, 56);
      Calendar cal = Calendar.getInstance();
      cal.set(date.getYear(), date.getMonth(), date.getDay(), date.getHour(), date.getMinute(), date.getSecond());
      return cal;
   }
   
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

   private long daysTillDue()
   {
      Calendar currentDate = Calendar.getInstance();
      long current = currentDate.getTimeInMillis();
      long due = dueDate.getTimeInMillis();
      return TimeUnit.MILLISECONDS.toDays(Math.abs(due - current));
   }

   private long hoursTillDue()
   {
      Calendar currentDate = Calendar.getInstance();
      long current = currentDate.getTimeInMillis();
      long due = dueDate.getTimeInMillis();
      return TimeUnit.MILLISECONDS.toHours(Math.abs(due - current)) % 24;
   }

   private long minutesTillDue()
   {
      Calendar currentDate = Calendar.getInstance();
      long current = currentDate.getTimeInMillis();
      long due = dueDate.getTimeInMillis();
      return TimeUnit.MILLISECONDS.toMinutes(Math.abs(due - current)) % 60;
   }

   private long secondsTillDue()
   {
      Calendar currentDate = Calendar.getInstance();
      long current = currentDate.getTimeInMillis();
      long due = dueDate.getTimeInMillis();
      return TimeUnit.MILLISECONDS.toSeconds(Math.abs(due - current)) % 60;
   }

   private String timeTillDue()
   {
      long days = daysTillDue();
      long hours = hoursTillDue();
      long minutes = minutesTillDue();
      long seconds = secondsTillDue();
      return "Days: " + days + " Hours: " + hours + " Minutes: " + minutes + " Seconds: " + seconds;
   }
   
   
   public String toString()
   {
      return createDate.toString();
   }

}
