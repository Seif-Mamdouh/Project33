package rubank;

import java.util.Calendar;

/**
 * Class for handling dates in "mm/dd/yyyy" format.
 *
 * @author Seifeldeen Mohamed
 */
public class Date implements Comparable<Date> {

    /**
     * Index for month in String array of split date.
     */
    public static final int MONTH_INDEX = 0;

    /**
     * Index for day in String array of split date.
     */
    public static final int DAY_INDEX = 1;

    /**
     * Index for year in String array of split date.
     */
    public static final int YEAR_INDEX = 2;

    /**
     * First Month of a year.
     */
    public static final int JANUARY = 1;
    /**
     * Second Month of a year.
     */
    public static final int FEBRUARY = 2;
    /**
     * Third Month of a year.
     */
    public static final int MARCH = 3;
    /**
     * Fourth Month of a year.
     */
    public static final int APRIL = 4;
    /**
     * Fifth Month of a year.
     */
    public static final int MAY = 5;
    /**
     * Sixth Month of a year.
     */
    public static final int JUNE = 6;
    /**
     * Seventh Month of a year.
     */
    public static final int JULY = 7;
    /**
     * Eighth Month of a year.
     */
    public static final int AUGUST = 8;
    /**
     * Ninth Month of a year.
     */
    public static final int SEPTEMBER = 9;
    /**
     * Tenth Month of a year.
     */
    public static final int OCTOBER = 10;
    /**
     * Eleventh Month of a year.
     */
    public static final int NOVEMBER = 11;
    /**
     * Last Month of a year.
     */
    public static final int DECEMBER = 12;

    /**
     * Earliest year allowed in a valid Date Object.
     */
    public static final int FIRST_YEAR = 1900;
    /**
     * Number day each month starts on.
     */
    public static final int FIRST_DAY = 1;

    /**
     * Array for months with 31 days based on knuckle mnemonic.
     */
    public static final int[] KNUCKLE_MONTHS = {JANUARY, MARCH, MAY, JULY, AUGUST,
            OCTOBER, DECEMBER};
    /**
     * Days in a "knuckle month."
     */
    public static final int ODD_DAYS = 31;

    /**
     * Array for months with only 30 days based on knuckle mnemonic.
     */
    public static final int[] NON_KNUCKLE_MONTHS = {APRIL, JUNE, SEPTEMBER, NOVEMBER};

    /**
     * Days in a non "knuckle month."
     */
    public static final int EVEN_DAYS = 30;

    /**
     * Name for every fourth year.
     */
    public static final int QUADRENNIAL = 4;
    /**
     * Name for every 100th year.
     */
    public static final int CENTENNIAL = 100;
    /**
     * Name for every 400th year.
     */
    public static final int QUARTERCENTENNIAL = 400;
    /**
     * Count of days in February during a leap year.
     */
    public static final int LEAP_DAYS = 29;
    /**
     * Typical count of days in month of February.
     */
    public static final int FEBRUARY_DAYS = 28;

    /**
     * Constant defining months in year for calculating monthly interest.
     */
    public static final int MONTHS_IN_YEAR = 12;

    /**
     * Stores year of Date instance.
     */
    private int year;

    /**
     * Stores month of Date instance.
     */
    private int month;

    /**
     * Stores day of Date instance.
     */
    private int day;

    /**
     * Creates instance of Date from string in "mm/dd/yyyy" format.
     * @param date Input string to be broken into attributes of a Date object
     */
    public Date(String date) {
        String[] split_date = date.split("/");

        this.month = Integer.parseInt(split_date[MONTH_INDEX]);
        this.day = Integer.parseInt(split_date[DAY_INDEX]);
        this.year = Integer.parseInt(split_date[YEAR_INDEX  ]);
    }

    /**
     * Default constructor, with fields set to current day,month,year.
     */
    public Date() {
        Calendar today = Calendar.getInstance();

        this.day = today.get(Calendar.DAY_OF_MONTH);
        this.month = today.get(Calendar.MONTH) + 1;
        this.year = today.get(Calendar.YEAR);
    }

    /**
     * Getting the year of Date object.
     * @return year of the Date object
     */
    public int getYear() {
        return year;
    }

    /**
     * Getting the month of the Date object.
     * @return month based on constants defined
     */
    public int getMonth() {
        return month;
    }

    /**
     * Getting the day of the Date object.
     * @return day of the Date object
     */
    public int getDay() {
        return day;
    }

    /**
     * Checks that the instance of date is valid based on the number of days
     * in a given month, and the year.
     *
     * @return true if valid, false if invalid.
     */
    public boolean isValid() {
        if (year < FIRST_YEAR) return false;

        if (day < FIRST_DAY) return false;

        if (month < JANUARY || month > DECEMBER) return false;

        for (int month : KNUCKLE_MONTHS) {
            if ((this.month == month) && (day > ODD_DAYS)) return false;
        }

        for (int month : NON_KNUCKLE_MONTHS) {
            if ((this.month == month) && (day > EVEN_DAYS)) return false;
        }

        if (month == FEBRUARY) {
            if (isLeapYear() && day > LEAP_DAYS) return false;
            else if (!isLeapYear() && day > FEBRUARY_DAYS) return false;
        }

        return true;

    }

    /**
     * Checks whether instance is a leap year based on algorithm given in project
     * specification.
     *
     * @return true if leap year, false if not a leap year.
     */
    private boolean isLeapYear() {
        if (year % QUADRENNIAL == 0) {
            if (year % CENTENNIAL == 0) {
                if (year % QUARTERCENTENNIAL == 0) {
                    return true;
                } else {
                    return false;
                }
            } else {
                return true;
            }
        } else {
            return false;
        }
    }

    /**
     * Overrides compareTo method and compares 2 dates.
     * @param date Date object to be compared to the calling Date object
     * @return if calling date is before argument -1; same date 0; calling after arg 1
     */
    @Override
    public int compareTo(Date date) {
        final int BEFORE = -1;
        final int EQUAL = 0;
        final int AFTER = 1;

        if (this == date) return EQUAL;

        if (this.year < date.year) return BEFORE;
        if (this.year > date.year) return AFTER;

        if (this.month < date.month) return BEFORE;
        if (this.month > date.month) return AFTER;

        if (this.day < date.day) return BEFORE;
        if (this.day > date.day) return AFTER;

        return EQUAL;
    }

    /**
     * Overrides toString method.
     * @return date in "mm/dd/yyyy" format
     */
    @Override
    public String toString() {
        return month + "/" + day + "/" + year;
    }

    /**
     * Overrides equals method.
     * @param o A date object should be passed as an argument for proper running
     * @return true if equal, false if not
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Date date = (Date) o;
        return getYear() == date.getYear() && getMonth() == date.getMonth() && getDay() == date.getDay();
    }
}