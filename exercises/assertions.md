# On assertions

Answer the following questions:

1. The following assertion fails `assertTrue(3 * .4 == 1.2)`. Explain why and describe how this type of check should be done.

2. What is the difference between `assertEquals` and `assertSame`? Show scenarios where they produce the same result and scenarios where they do not produce the same result.

3. In classes we saw that `fail` is useful to mark code that should not be executed because an exception was expected before. Find other uses for `fail`. Explain the use case and add an example.

4. In JUnit 4, an exception was expected using the `@Test` annotation, while in JUnit 5 there is a special assertion method `assertThrows`. In your opinion, what are the advantages of this new way of checking expected exceptions?

## Answer

1. This assertion fails due to imprecisions related to floating numbers (a.k.a. IEEE-754 numbers). The result of the operation is actually `1.2000000000000002`. In order to test properly the value, we need to use the [`assertEquals(float expected, float actual, float delta)`](https://junit.org/junit5/docs/5.0.1/api/org/junit/jupiter/api/Assertions.html#assertEquals-float-float-float-) method: this method returns true if `actual == expected - delta || actual == expected + delta`, eliminating assertion fails due to IEEE-754 imprecisions. In our case, we may for example use `assertEquals(3 * .4, 1.2, 0.001)`, if such `0.001` imprecision is of course acceptable.

2. [`assertSame`](https://junit.org/junit5/docs/current/api/org.junit.jupiter.api/org/junit/jupiter/api/Assertions.html#assertSame(java.lang.Object,java.lang.Object)) verifies that two objects are identical, i.e. they have the same reference : `assertSame(obj1, obj2)` would perform the `obj1 == obj2` equality test.
    A case where `assertSame` and `assertObject` give same results is :
    
    ```java
    assertEquals("test", "test");  // succeeds
    assertSame("test", "test");    // succeeds
    ```
    
    That is because in a Java code, same `String` litterals share the same reference. In order to produce different references, we can create new strings using `new String()`,   leading to a case where `assertSame` and `assertEquals` does not produce the same result :
    
    ```java
    assertEquals(new String("test"), new String("test"));  // succeeds
    assertSame(new String("test"), new String("test"));    // fails
    ```
    
    because `new String("test")` produces each time a distinct object.

3.

4. The `assertThrows` method have as main advantage to look like a regular oracle function ; JUnit 4's `@Test(expected=...)` annotation have for main disadventage it indicates the expected exception at the top of the test case, without explicitely indicating what part of the code may throw an exception. `assertThrows` ensures that it is clear that an excepetion is expected for a given code, and enforces the comment -> initializing -> running -> oracle structure of tests.
