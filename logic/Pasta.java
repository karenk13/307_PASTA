package logic;

// The point of entry for the PASTA application
// This is basically the driver
public class Pasta
{
   public static void main(String[] args)
   {
      Assignment assignment = new Assignment(new Date(2017,04,26,15,45,30));
      assignment.workOnAssignment();
      assignment.pauseWorkOnAssignment();
      System.out.println("Time Spent: " + assignment.timeSpentOnAssignment());
   }
}
