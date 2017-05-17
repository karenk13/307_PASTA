package logic;


public class Date
{
   private int year;
   private int month;
   private int day;
   private int hour;
   private int minute;
   private int second;
   // Months: 00 - January, 01 February, ... 11 December
   // Day: Assumes that the given day is a day in the specified month
   // Hour: Assumes the hour is less than 24, military time
   // Minute: Expects between 0-59
   // Second: Expects between 0-59
   public Date(int year, int month, int day, int hour, int minute, int second)
   {
      this.year = year;
      this.month = month;
      this.day = day;
      this.hour = hour;
      this.minute = minute;
      this.second = second;
   }
   
   public int getYear()
   {
      return year;
   }

   public int getMonth()
   {
      return month;
   }

   public int getDay()
   {
      return day;
   }

   public int getHour()
   {
      return hour;
   }

   public int getMinute()
   {
      return minute;
   }

   public int getSecond()
   {
      return second;
   }

   public void setYear(int newYear)
   {
      this.year = newYear;
   }

   public void setMonth(int newMonth)
   {
      this.month = newMonth;
   }

   public void setDay(int newDay)
   {
      this.month = newDay;
   }

   public void setHour(int newHour)
   {
      this.hour = newHour;
   }

   public void setMinute(int newMinute)
   {
      this.minute = newMinute;
   }

   public void setSecond(int newSecond)
   {
      this.second = newSecond;
   }
}
