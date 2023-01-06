package com.nighthawk.spring_portfolio.mvc.calendar;

/** Simple POJO 
 * Used to Interface with APCalendar
 * The toString method(s) prepares object for JSON serialization
 * Note... this is NOT an entity, just an abstraction
 */
class Year {
   private int year;
   private boolean isLeapYear;
   private int firstDayOfYear;
   private int dayYear;
   private int mm,dd;
   private int year1, year2;
   private int nolp;
   private int weekday;

   // zero argument constructor
   public Year() {} 

   /* year getter/setters */
   public int getYear() {
      return year;
   }

   public void setYear(int year) {
      this.year = year;
      this.setIsLeapYear(year);
      this.setfirstDayOfYear(year);
   }

   /* isLeapYear getter/setters */
   public boolean getIsLeapYear(int year) {
      return APCalendar.isLeapYear(year);
   }
   private void setIsLeapYear(int year) {  // this is private to avoid tampering
      this.isLeapYear = APCalendar.isLeapYear(year);
   }

   /* isLeapYearToString formatted to be mapped to JSON */
   public String isLeapYearToString(){
      return ( "{ \"year\": "  +this.year+  ", " + "\"isLeapYear\": "  +this.isLeapYear+ " }" );
   }	

   /* standard toString placeholder until class is extended */
   public String toString() { 
      return isLeapYearToString(); 
   }

   /* isLeapYear getter/setters */
   public int getfirstDayOfYear() {
      return firstDayOfYear;
   }
   private void setfirstDayOfYear(int year) {  // this is private to avoid tampering
      this.firstDayOfYear = APCalendar.firstDayOfYear(year);
   }

   public void dayOfYear(int month,int day,int yr)
   {
      this.mm=month;
      this.dd=day;
      this.year=yr;
      this.dayYear = APCalendar.dayOfYear(month,day,yr);
   }

   public void numberOfLeapYears(int yr1,int yr2) {
      this.year1 = yr1;
      this.year2 = yr2;
      this.nolp = APCalendar.numberOfLeapYears(yr1 ,yr2);
   }

   public void dayOfWeek(int month,int day,int yr)
   {
      this.mm=month;
      this.dd=day;
      this.year=yr;
      this.weekday = APCalendar.dayOfWeek(month,day,yr);
   }

   public String dayOfWeekToString(){
      return ("{ \"month\":"  +this.mm+
               ", \"day\":"  +this.dd+ 
               ", \"Year\":" +this.year+ 
               ", \"WeekDay is\":" +this.weekday+
               " }");
   }


   public String numberOfLeapYearsToString() {
      return ("{ \"Year1\":"  +this.year1+
               ", \"Year2\":"  +this.year2+ 
               ", \"No. of Leap Years are\":" +this.nolp+
               " }");
   }

   public String dayOfYearToString(){
      return ("{ \"month\":"  +this.mm+
               ", \"day\":"  +this.dd+ 
               ", \"Year\":" +this.year+ 
               ", \"Day of year is\":" +this.dayYear+
               " }");
   }

   /* iFirst day od year formatted to be mapped to JSON */
   public String firstDayOfYearToString(){
      return ( "{ \"year\": "  +this.year+
               ", \"First Day of year is\": "+this.firstDayOfYear+
               " }" );
   }	
   

   /* 
   public static void main(String[] args) {
      Year year = new Year();
      year.setYear(2012);
      System.out.println(year);
   }
   */
}