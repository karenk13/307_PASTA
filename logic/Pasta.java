package logic;

// The point of entry for the PASTA application
// This is basically the driver
public class Pasta
{
   public static void main(String[] args)
   {
      Date date = new Date(2017,04,26,15,45,30);
      String name = "Essay";
      long estTime = 10000;
      String desc = "CPE 300 Assignment 2";
      Assignment assignment = new Assignment(name, date, estTime, desc);
      assignment.workOnAssignment();
      assignment.pauseWorkOnAssignment();
   }
}
