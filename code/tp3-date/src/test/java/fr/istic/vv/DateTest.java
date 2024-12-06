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

    /* tests for previousDate */

    /* tests for isValidDate */

}