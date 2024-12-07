package fr.istic.vv;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DateTest {

    /* tests for isLeapYear */

    // input space partitioning tests

    /**
     * Test isLeapYear on a non-leap year, and ensure it returns false.
     */
    @Test
    @DisplayName("isLeapYear - basic non leap year")
    void testIsLeapYearNonLeapYear() {
        assertFalse(Date.isLeapYear(1983));
    }

    /**
     * Test isLeapYear on a non-leap year only divisible by 100, and ensure it returns false.
     */
    @Test
    @DisplayName("isLeapYear - non leap year only divisible by 100")
    void testIsLeapYearNonLeapYearDivOnly100() {
        assertFalse(Date.isLeapYear(1300));
    }

    /**
     * Test isLeapYear on a leap year only divisible by 4, and ensure it returns true.
     */
    @Test
    @DisplayName("isLeapYear - leap year only divisible by 4")
    void testIsLeapYearLeapYearDivOnly4() {
        assertTrue(Date.isLeapYear(2024));
    }

    /**
     * Test isLeapYear on a leap year only divisible by 400, and ensure it returns true.
     */
    @Test
    @DisplayName("isLeapYear - leap year divisible by 4 and 400")
    void testIsLeapYearLeapYearDiv400() {
        assertTrue(Date.isLeapYear(1200));
    }

    /**
     * Test isLeapYear on a non-leap year only divisible by 4 and 100, and ensure it returns false.
     */
    @Test
    @DisplayName("isLeapYear - non-leap year divisible by 4 and 100")
    void testIsLeapNonYearLeapYearDiv4And100() {
        assertFalse(Date.isLeapYear(300));
    }

    /* tests for nextDate */

    // input space partitioning tests

    /**
     * Create the 14/04/2001 date, get the next date, and ensure its the 15/04/2001.
     */
    @Test
    @DisplayName("nextDate - day in the middle of a month of non-leap year")
    void testNextDateMiddleMonthNonLeap() {
        Date next = new Date(14, 4, 2001).nextDate();

        assertEquals(15, next.getDay());
        assertEquals(4, next.getMonth());
        assertEquals(2001, next.getYear());
    }

    /**
     * Create the 25/12/2001 date, get the next date, and ensure it is the 25/12/2001.
     */
    @Test
    @DisplayName("nextDate - day in the middle of december of non-leap year")
    void testNextDateMiddleDecemberNonLeap() {
        Date next = new Date(24, 12, 2001).nextDate();

        assertEquals(25, next.getDay());
        assertEquals(12, next.getMonth());
        assertEquals(2001, next.getYear());
    }

    /**
     * Create the 31/03/2001 date, get the next date, and ensure it is the 01/04/2001.
     */
    @Test
    @DisplayName("nextDate - last day of march non-leap year")
    void testNextDateLastDayMarchNonLeap() {
        Date next = new Date(31, 3, 2001).nextDate();

        assertEquals(1, next.getDay());
        assertEquals(4, next.getMonth());
        assertEquals(2001, next.getYear());
    }

    /**
     * Create the 31/03/2001 date, get the next date, and ensure it is the 01/04/2001.
     */
    @Test
    @DisplayName("nextDate - last day of non-leap year")
    void testNextDateLastDayYearNonLeap() {
        Date next = new Date(31, 12, 2001).nextDate();

        assertEquals(1, next.getDay());
        assertEquals(1, next.getMonth());
        assertEquals(2002, next.getYear());
    }

    /**
     * Create the 14/04/2012 date, get the next date, and ensure its the 15/04/2012.
     */
    @Test
    @DisplayName("nextDate - day in the middle of a month of leap year")
    void testNextDateMiddleMonthLeap() {
        Date next = new Date(14, 4, 2012).nextDate();

        assertEquals(15, next.getDay());
        assertEquals(4, next.getMonth());
        assertEquals(2012, next.getYear());
    }

    /**
     * Create the 25/12/2012 date, get the next date, and ensure it is the 25/12/2012.
     */
    @Test
    @DisplayName("nextDate - day in the middle of december of leap year")
    void testNextDateMiddleDecemberLeap() {
        Date next = new Date(24, 12, 2012).nextDate();

        assertEquals(25, next.getDay());
        assertEquals(12, next.getMonth());
        assertEquals(2012, next.getYear());
    }

    /**
     * Create the 31/03/2012 date, get the next date, and ensure it is the 01/04/2012.
     */
    @Test
    @DisplayName("nextDate - last day of march leap year")
    void testNextDateLastDayMarchLeap() {
        Date next = new Date(31, 3, 2012).nextDate();

        assertEquals(1, next.getDay());
        assertEquals(4, next.getMonth());
        assertEquals(2012, next.getYear());
    }

    /**
     * Create the 31/03/2012 date, get the next date, and ensure it is the 01/04/2012.
     */
    @Test
    @DisplayName("nextDate - last day of leap year")
    void testNextDateLastDayYearLeap() {
        Date next = new Date(31, 12, 2012).nextDate();

        assertEquals(1, next.getDay());
        assertEquals(1, next.getMonth());
        assertEquals(2013, next.getYear());
    }

    /**
     * Create the 28/02/2012 date, get the next date, and ensure it is the 29/02/2012.
     */
    @Test
    @DisplayName("nextDate - 28 february leap year")
    void testNextDateLast28FebruaryLeap() {
        Date next = new Date(28, 2, 2012).nextDate();

        assertEquals(29, next.getDay());
        assertEquals(2, next.getMonth());
        assertEquals(2012, next.getYear());
    }

    /* tests for previousDate */

    // input space partitioning tests

    /**
     * Create the 14/04/2001 date, get the previous date, and ensure its the 13/04/2001.
     */
    @Test
    @DisplayName("previousDate - day in the middle of a month of non-leap year")
    void testPreviousDateMiddleMonthNonLeap() {
        Date previous = new Date(14, 4, 2001).previousDate();

        assertEquals(13, previous.getDay());
        assertEquals(4, previous.getMonth());
        assertEquals(2001, previous.getYear());
    }

    /**
     * Create the 24/12/2001 date, get the previous date, and ensure it is the 23/12/2001.
     */
    @Test
    @DisplayName("previousDate - day in the middle of december of non-leap year")
    void testPreviousDateMiddleDecemberNonLeap() {
        Date previous = new Date(24, 12, 2001).previousDate();

        assertEquals(23, previous.getDay());
        assertEquals(12, previous.getMonth());
        assertEquals(2001, previous.getYear());
    }

    /**
     * Create the 01/04/2001 date, get the previous date, and ensure it is the 31/03/2001.
     */
    @Test
    @DisplayName("previousDate - first day of april non-leap year")
    void testPreviousDateLastDayMarchNonLeap() {
        Date previous = new Date(1, 4, 2001).previousDate();

        assertEquals(31, previous.getDay());
        assertEquals(3, previous.getMonth());
        assertEquals(2001, previous.getYear());
    }

    /**
     * Create the 01/01/2002 date, get the previous date, and ensure it is the 31/12/2001.
     */
    @Test
    @DisplayName("previousDate - first day of non-leap year")
    void testPreviousDateLastDayYearNonLeap() {
        Date previous = new Date(1, 1, 2002).previousDate();

        assertEquals(31, previous.getDay());
        assertEquals(12, previous.getMonth());
        assertEquals(2001, previous.getYear());
    }

    /**
     * Create the 14/04/2012 date, get the previous date, and ensure it's the 13/04/2012.
     */
    @Test
    @DisplayName("previousDate - day in the middle of a month of leap year")
    void testPreviousDateMiddleMonthLeap() {
        Date previous = new Date(14, 4, 2012).previousDate();

        assertEquals(13, previous.getDay());
        assertEquals(4, previous.getMonth());
        assertEquals(2012, previous.getYear());
    }

    /**
     * Create the 24/12/2012 date, get the previous date, and ensure it is the 23/12/2012.
     */
    @Test
    @DisplayName("previousDate - day in the middle of december of leap year")
    void testPreviousDateMiddleDecemberLeap() {
        Date previous = new Date(24, 12, 2012).previousDate();

        assertEquals(23, previous.getDay());
        assertEquals(12, previous.getMonth());
        assertEquals(2012, previous.getYear());
    }

    /**
     * Create the 01/04/2012 date, get the previous date, and ensure it is the 31/03/2012.
     */
    @Test
    @DisplayName("previousDate - first day of april leap year")
    void testPreviousDateLastDayMarchLeap() {
        Date previous = new Date(1, 4, 2012).previousDate();

        assertEquals(31, previous.getDay());
        assertEquals(3, previous.getMonth());
        assertEquals(2012, previous.getYear());
    }

    /**
     * Create the 01/01/2012 date, get the previous date, and ensure it is the 31/12/2011.
     */
    @Test
    @DisplayName("previousDate - first day of leap year")
    void testPreviousDateLastDayYearLeap() {
        Date previous = new Date(1, 1, 2012).previousDate();

        assertEquals(31, previous.getDay());
        assertEquals(12, previous.getMonth());
        assertEquals(2011, previous.getYear());
    }

    /**
     * Create the 01/03/2012 date, get the next date, and ensure it is the 29/02/2012.
     */
    @Test
    @DisplayName("previousDate - 28 february leap year")
    void testPreviousDateLast28FebruaryLeap() {
        Date previous = new Date(1, 3, 2012).previousDate();

        assertEquals(29, previous.getDay());
        assertEquals(2, previous.getMonth());
        assertEquals(2012, previous.getYear());
    }

    /* tests for isValidDate */

}