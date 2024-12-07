# Test the Date class

Implement a class `Date` with the interface shown below:

```java
class Date implements Comparable<Date> {

    public Date(int day, int month, int year) { ... }

    public static boolean isValidDate(int day, int month, int year) { ... }

    public static boolean isLeapYear(int year) { ... }

    public Date nextDate() { ... }

    public Date previousDate { ... }

    public int compareTo(Date other) { ... }

}
```

The constructor throws an exception if the three given integers do not form a valid date.

`isValidDate` returns `true` if the three integers form a valid year, otherwise `false`.

`isLeapYear` says if the given integer is a leap year.

`nextDate` returns a new `Date` instance representing the date of the following day.

`previousDate` returns a new `Date` instance representing the date of the previous day.

`compareTo` follows the `Comparable` convention:

* `date.compareTo(other)` returns a positive integer if `date` is posterior to `other`
* `date.compareTo(other)` returns a negative integer if `date` is anterior to `other`
* `date.compareTo(other)` returns `0` if `date` and `other` represent the same date.
* the method throws a `NullPointerException` if `other` is `null` 

Design and implement a test suite for this `Date` class.
You may use the test cases discussed in classes as a starting point. 
Also, feel free to add any extra method you may need to the `Date` class.


Use the following steps to design the test suite:

1. With the help of *Input Space Partitioning* design a set of initial test inputs for each method. Write below the characteristics and blocks you identified for each method. Specify which characteristics are common to more than one method.
2. Evaluate the statement coverage of the test cases designed in the previous step. If needed, add new test cases to increase the coverage. Describe below what you did in this step.
3. If you have in your code any predicate that uses more than two boolean operators check if the test cases written to far satisfy *Base Choice Coverage*. If needed add new test cases. Describe below how you evaluated the logic coverage and the new test cases you added.
4. Use PIT to evaluate the test suite you have so far. Describe below the mutation score and the live mutants. Add new test cases or refactor the existing ones to achieve a high mutation score.

Use the project in [tp3-date](../code/tp3-date) to complete this exercise.

## Answer

1. For `isLeapYear`, as leap years are :
    - years non divisible by 100 and divisible by 4...
    - or years divisible by 100 and 400. 
    
    We use the following characteristics :

    - Is year divisible by 4 ?
    - Is year divisible by 100 ?
    - Is year divisible by 400 ?

    Leading to the following blocks :

    | Characteristics     | 1 | 2 | 3 | 4 | 5 | 6 |
    |---------------------|---|---|---|---|---|---|
    | `year % 4 == 0` ?   | f | f | v | v | v | v |
    | `year % 100 == 0` ? | f | v | f | f | v | v |
    | `year % 400 == 0` ? | f | f | f | v | f | v |

    Several cases had been pruned : for example being divisble by 400 implies
    being divisible by 4, so any block requiring divisibility by 400 and not
    divsibilty by 4 is inconsistent. 

    For `nextDate`, as the most critical operation is to correctly change 
    months / years if necessary, by taking account of February 29,
    we use the following characteristics :

    - Is the input year a leap year ?
    - Is the input day a February 29 ?
    - Is the input day the last day of the month ?
    - Is the input month the last month of the day ?

    Leading to these blocks : 

    | Characteristics      | 1 | 2 | 3 | 4 | 5 | 6 | 7 | 8 | 9 |
    |----------------------|---|---|---|---|---|---|---|---|---|
    | leap year ?          | f | f | f | f | v | v | v | v | v |
    | 29 february ?        | f | f | f | f | f | f | f | f | v |
    | last day of month ?  | f | f | v | v | f | f | v | v | f |
    | last month of year ? | f | v | f | v | f | v | f | v | f |

    With the same reasonement, for `previousDate`, we use the following 
    characteristics :

    - Is the input year a leap year ?
    - Is the input day a February 29 ?
    - Is the input day the first day of the month ?
    - Is the input month the first month of the year ?

    Leading to these blocks :

    | Characteristics       | 1 | 2 | 3 | 4 | 5 | 6 | 7 | 8 | 9 |
    |-----------------------|---|---|---|---|---|---|---|---|---|
    | leap year ?           | f | f | f | f | v | v | v | v | v |
    | 29 february ?         | f | f | f | f | f | f | f | f | v |
    | first day of month ?  | f | f | v | v | f | f | v | v | f |
    | first month of year ? | f | v | f | v | f | v | f | v | f |

    For `isValidDate`, we use the following characteristics, as we need to take account 
    values ranges and leap years (especially the February 29) :

    - Is the input year a leap year ?
    - Is the given date a February 29 ?
    - Is the given day out of range ?
    - Is the given month out of range ?

    Leading to these blocks :

    | Characteristics      | 1 | 2 | 3 | 4 | 5 | 6 | 7 | 8 | 9 | 10 |
    |----------------------|---|---|---|---|---|---|---|---|---|----|
    | leap year ?          | f | f | f | f | f | v | v | v | v | v  |
    | 29 february ?        | f | f | f | f | v | f | f | f | f | v  |
    | out of range day ?   | f | f | v | v | v | f | f | v | v | f  |
    | out of range month ? | f | v | f | v | f | f | v | f | v | f  |

    Several blocks had been pruned, as they were inconsistent (for example, 
    we cannot be a 29 february during a leap year while being out of range).

    For `compareTo`, we can trivaially divide the input space :

    - Input year inferior to current year.
    - Input year equal to current year.
    - Input year superior to current year.

    As these characteristics are mutually exclusives, there is 3 test cases to
    implement.

2. For `isLeapYear` :

    ```java
    public static boolean isLeapYear(int year) {
        // Statement 1
        if (year % 100 == 0)
            // Statement 2
            return year % 400 == 0;
        // Statement 3
        return year % 4 == 0;
    }
    ```

    - Statement 1 executes statement 2  only if `year % 100 == 0`...
    - Statement 2 returns the result of `year % 400 == 0`...
    - ...otherwise statement 3 returns the result ```year % 4 == 0```.

    Each of these conditions are taken in account of the previous input 
    space partitioning.

    For `nextDate` :

    ```java
    private static int[] getMonthDayCount(int year) {
        // Statement 1
        return isLeapYear(year) ? NUMBER_OF_DAYS_LEAP : NUMBER_OF_DAYS_REGULAR;
    }

    public Date nextDate() {
        int newDay = day + 1;
        int newMonth = month;
        int newYear = year;

        // Statement 2
        if (newDay > getMonthDayCount(year)[month - 1]) {
            newDay = 1;
            newMonth++;
        }

        // Statement 3
        if (newMonth > 12) {
            newMonth = 1;
            newYear++;
        }

        return new Date(newDay, newMonth, newYear);
    }
    ```

    - Statement 1 returns conditionally the number of days of each month 
    following if the given year regular or leap. The only difference 
    between the arrays is that the second cell is equal to 28 and 29 
    respectively for regular years and leap years.
    - Statement 2 is executes its body only if the given date is the last 
    day of a month.
    - Statement 3 executes only its body when the given date is on 
    December 31. 

    These statements are covered by previously written tests.

    For `previousDate` :

    ```java
    private static int[] getMonthDayCount(int year) {
        // Statement 1
        return isLeapYear(year) ? NUMBER_OF_DAYS_LEAP : NUMBER_OF_DAYS_REGULAR;
    }

    public Date previousDate() {
        int newDay = day - 1;
        int newMonth = month;
        int newYear = year;

        // Statement 2
        if (newDay == 0) {
            newMonth--;

            if (newMonth != 0)
                // Statement 3
                newDay = getMonthDayCount(year)[newMonth - 1];
            else
                // Statement 4
                newDay = 31;
        }

        // Statement 5
        if (newMonth == 0) {
            newMonth = 12;
            newYear--;
        }

        return new Date(newDay, newMonth, newYear);
    }
    ```

    - Statement 1 still works identically.
    - Statement 2 executes its body if the date is the first day of a 
    month...
    - ...executing statement 3 or 4 depeending on if the date is the 1st 
    january, preventing an illegal access to the day counts array.
    - Statement 5 is also executed if the date is the 1st janaury.

    These statements are also covered by previously written test cases.

    For `isValidDate` :

    ```java
    public static boolean isValidDate(int day, int month, int year) {
        // Statement 1
        if (month < 1 || month > 12)
            return false;

        // Statement 2
        return day >= 1 && day <= getMonthDayCount(year)[month - 1];
    }
    ```

    - Statement 1 is executed if month number is out of range.
    - Statement 2 return `true` if the day number is in range ; this
    range can vary following the fact that the year is a leap year or not.

    These cases are covered by tests.

    For `compareTo` : 
    
    ```java
    @Override
    public int compareTo(Date other) {
        // Statement 1
        Objects.requireNonNull(other);

        // Statement 2
        if (other.year != year)
            return year < other.year ? -1 : 1;

        // Statement 3
        if (other.month != month)
            return month < other.month ? -1 : 1;

        // Statement 4
        if (other.day != day)
            return day < other.day ? -1 : 1;

        return 0;
    }
    ```

    - Statement 1 covers the possibility that `other` is `null`, throwing a 
    `NullPointerException`.
    - Statements 2, 3 and 4 cover each comparison case : inferior, equal or 
    superior.

    Statement 2, 3 and 4 are effectively covered by test cases ; however, null case had been forgotten during the previous step, and had been
    added to the test cases in this step.

    
