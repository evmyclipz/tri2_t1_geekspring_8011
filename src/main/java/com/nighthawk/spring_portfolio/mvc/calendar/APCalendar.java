package com.nighthawk.spring_portfolio.mvc.calendar;

// Prototype Implementation

public class APCalendar {

    /** Returns true if year is a leap year and false otherwise.
     * isLeapYear(2019) returns False
     * isLeapYear(2016) returns True
     */          
    public static boolean isLeapYear(int year) {
        // implementation not shown
        if ((year % 4 == 0) && (year % 100 != 0)) {
            return true;
        }
        else if((year % 100 == 0) && (year % 400 == 0)) {
            return true;
        }
        else {
            return false;
        }
        
    }   
        
        
    /** Returns the value representing the day of the week 
     * 0 denotes Sunday, 
     * 1 denotes Monday, ..., 
     * 6 denotes Saturday. 
     * firstDayOfYear(2019) returns 2 for Tuesday.
    */
    public static int firstDayOfYear(int year) {
        // implementation not sho
        int yc = 0;
        if(year >= 1700 && year < 1800) {
            yc = 4;
        }
        else if (year >= 1800 && year < 1900) {
            yc = 2;
        }
        else if (year >= 1900 && year < 2000) {
            yc = 0;
        }
        else if (year >= 2000 && year < 2100) {
            yc = 6;
        }
        int x = year % 100;
        int y = x/4;
        int a = y + 2;
        if (isLeapYear(year)) {
            int c = a - 1;
            int sum = yc + c;
            int sum2 = sum + x;
            int num = sum2 % 7;
            return (num > 0)?num-1:6;
        }
        else {
            int sum = a + yc;
            int sum2 = sum + x;
            int num = sum2 % 7;
            return (num > 0)?num-1:6;
        }
    }


    /** Returns n, where month, day, and year specify the nth day of the year.
     * This method accounts for whether year is a leap year. 
     * dayOfYear(1, 1, 2019) return 1
     * dayOfYear(3, 1, 2017) returns 60, since 2017 is not a leap year
     * dayOfYear(3, 1, 2016) returns 61, since 2016 is a leap year. 
    */ 
    public static int dayOfYear(int month, int day, int year) {
        // implementation not shown
        int[] arr = {31,28,31,30,31,30,31,31,30,31,30,31};
        int[] arr2 = {31,29,31,30,31,30,31,31,30,31,30,31};
        if (isLeapYear(year)) {
            int x = 0;
            for(int i = 0; i < month-1; i++) {
                x+=arr2[i];
            }
            return (x + day);
        }
        else {
            int x = 0;
            for(int i = 0; i < month-1; i++) {
                x+=arr[i];
            }
            return (x + day);
        }
    }

    /** Returns the number of leap years between year1 and year2, inclusive.
     * Precondition: 0 <= year1 <= year2
    */ 
    public static int numberOfLeapYears(int year1, int year2) {
        // to be implemented in part (a)
        if(year1 >= 0 && year1 <= year2) {
            int count = 0;
            for(int i = year1;i <= year2; i++) {
                if(isLeapYear(i)) {
                    count++;
                }
            }
            return count;
        }
        return 0;
    }

    /** Returns the value representing the day of the week for the given date
     * Precondition: The date represented by month, day, year is a valid date.
    */
    public static int dayOfWeek(int month, int day, int year) { 
        // to be implemented in part (b)
        int x = firstDayOfYear(year);
        int y = dayOfYear(month, day, year) - 1;
        int sum = x + y;

        return (sum % 7);
    }

    /** Tester method */
    public static void main(String[] args) {
        // Private access modifiers
        System.out.println("firstDayOfYear: " + APCalendar.firstDayOfYear(2022));
        System.out.println("dayOfYear: " + APCalendar.dayOfYear(1, 1, 2022));

        // Public access modifiers
        System.out.println("isLeapYear: " + APCalendar.isLeapYear(2022));
        System.out.println("numberOfLeapYears: " + APCalendar.numberOfLeapYears(2000, 2022));
        System.out.println("dayOfWeek: " + APCalendar.dayOfWeek(1, 1, 2022));
    }

}