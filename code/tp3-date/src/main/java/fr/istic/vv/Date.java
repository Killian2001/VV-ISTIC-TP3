package fr.istic.vv;

import java.util.Objects;

/**
 * Represents a date of the gregorian calendar, made of a day number, a month number, and
 * a year number.
 */
class Date implements Comparable<Date> {

    private final int day;
    private final int month;
    private final int year;

    private static final int[] NUMBER_OF_DAYS_REGULAR = new int[] {
            31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31
    };


    private static final int[] NUMBER_OF_DAYS_LEAP = new int[] {
            31, 29, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31
    };

    /**
     * Constructor of the class.
     *
     * @param day   Day number.
     * @param month Month number.
     * @param year  Year number.
     */
    public Date(int day, int month, int year) {
        if (!isValidDate(day, month, year))
            throw new IllegalArgumentException(
                    String.format("error: date %d/%d/%d is not valid", day, month, year));

        this.day = day;
        this.month = month;
        this.year = year;
    }

    /**
     * Checks whether the given day, month and year numbers form a valid date, i.e.
     * <ul>
     *     <li>Month number is comprised between 1 and 12 (included)</li>
     *     <li>
     *         For the corresponding month, day number is included between 1 and the number of
     *         days of the month associated to the month number (with respect to leap years).
     *     </li>
     * </ul>
     *
     * @param day Day number of the date to check.
     * @param month Month number of the date to check.
     * @param year Year number of the date to check.
     *
     * @return <code>true</code> if <code>day</code>, <code>month</code> and <code>year</code>
     * form a valid date, <code>false</code> otherwise.
     */
    public static boolean isValidDate(int day, int month, int year) {
        if (month < 1 || month > 12)
            return false;

        int[] monthDayCount = isLeapYear(year) ? NUMBER_OF_DAYS_LEAP : NUMBER_OF_DAYS_REGULAR;
        return day >= 1 && day <= monthDayCount[month - 1];
    }

    /**
     * Check whether a given year is a leap year or not.
     * A <code>year</code> is a <em>leap year</em> if :
     *
     * <ul>
     *     <li><code>year % 100 == 0 && year % 400 == 0</code></li>
     *     <li><code>year % 4 == 0 && year % 100 != 0</code></li>
     * </ul>
     *
     * @param year Year number
     * @return <code>true</code> if <code>year</code> is a leap year.
     */
    public static boolean isLeapYear(int year) {
        if (year % 100 == 0)
            return year % 400 == 0;
        return year % 4 == 0;
    }

    /**
     * @return The date following this date.
     */
    public Date nextDate() {
        int newDay = day + 1;
        int newMonth = month;
        int newYear = year;

        // If next day is the 1st of the next month, change month.
        if (newDay > getMonthDayCount(year)[month - 1]) {
            newDay = 1;
            newMonth++;
        }

        // If the next month if january, change the year.
        if (newMonth > 12) {
            newMonth = 1;
            newYear++;
        }

        return new Date(newDay, newMonth, newYear);
    }

    /**
     * @return The date preceding this date.
     */
    public Date previousDate() {
        int newDay = day - 1;
        int newMonth = month;
        int newYear = year;

        // If the current day is the first, decrementing the month.
        if (newDay == 0) {
            newMonth--;

            // Avoid invalid access to the array.
            if (newMonth != 0)
                newDay = getMonthDayCount(year)[newMonth - 1];
            else
                newDay = 31;
        }

        // If the current month is january, changing year.
        if (newMonth == 0) {
            newMonth = 12;
            newYear--;
        }

        return new Date(newDay, newMonth, newYear);
    }

    @Override
    public int compareTo(Date other) {
        Objects.requireNonNull(other);

        if (other.year != year)
            return year < other.year ? -1 : 1;
        if (other.month != month)
            return month < other.month ? -1 : 1;
        if (other.day != day)
            return day < other.day ? -1 : 1;

        return 0;
    }

    /**
     * @param year Year number.
     * @return Return an array where the i-th element is the number of days of the (i + 1)-th month
     * of the year.
     */
    private static int[] getMonthDayCount(int year) {
        return isLeapYear(year) ? NUMBER_OF_DAYS_LEAP : NUMBER_OF_DAYS_REGULAR;
    }

    public int getDay() {
        return day;
    }

    public int getMonth() {
        return month;
    }

    public int getYear() {
        return year;
    }

}