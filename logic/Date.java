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
      setYear(year);
      setMonth(month);
      setDay(day);
      setHour(hour);
      setMinute(minute);
      setSecond(second);
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
      if(newYear > 0)
      {
         this.year = newYear;
      }
      else
      {
         throw new IllegalArgumentException("The year cannot be negative");
      }
   }

   public void setMonth(int newMonth)
   {
      if(newMonth > -1 && newMonth < 12)
      {
         this.month = newMonth;
      }
      else
      {
         throw new IllegalArgumentException("The month cannot be negative and must be less than 12");
      }
   }

   public void setDay(int newDay)
   {
      if(newDay > -1 && newDay < 32)
      {
         this.month = newDay;
      }
      else
      {
         throw new IllegalArgumentException("The day cannot be negative and must be less than 32");
      }
   }

   public void setHour(int newHour)
   {
      if(hour > -1 && hour < 24)
      {
         this.hour = newHour;
      }
      else
      {
         throw new IllegalArgumentException("The hour cannot be negative and must be less than 24");
      }
   }

   public void setMinute(int newMinute)
   {
      if(newMinute > -1 && newMinute < 60)
      {
         this.minute = newMinute;
      }
      else
      {
         throw new IllegalArgumentException("The minute cannot be negative and must be less than 60");
      }
   }

   public void setSecond(int newSecond)
   {
      if(newSecond > -1 && newSecond < 60)
      {
         this.second = newSecond;
      }
      else
      {
         throw new IllegalArgumentException("The second cannot be negative and must be less than 60");
      }
   }
}
