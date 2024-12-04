# Detecting test smells with PMD

In folder [`pmd-documentation`](../pmd-documentation) you will find the documentation of a selection of PMD rules designed to catch test smells.
Identify which of the test smells discussed in classes are implemented by these rules.

Use one of the rules to detect a test smell in one of the following projects:

- [Apache Commons Collections](https://github.com/apache/commons-collections)
- [Apache Commons CLI](https://github.com/apache/commons-cli)
- [Apache Commons Math](https://github.com/apache/commons-math)
- [Apache Commons Lang](https://github.com/apache/commons-lang)

Discuss the test smell you found with the help of PMD and propose here an improvement.
Include the improved test code in this file.

## Answer

Smells discussed in classes were the following (in order, from [lecture slides](https://people.irisa.fr/Benoit.Combemale/pub/course/vv/4-dynamic-test-istic.pdf)):

- Piggybacking : accumulating a more than one assertion in a test case instead of dispatch each assertion in its own test case.
- Happy path : consists to write the test following an optimistic scenario, which may not happen in real world environment ; in this case, the test case risks to miss evidents bugs of the tested feature.
- Useless assert : writing asserts which will trivially succeeds, and brings nothing to the test.
- Hidden dependancy (mentionned on slides, infos from [here](https://github.com/abmessaoud/webali.github.io/blob/master/_posts/2017-12-05-Unit-Testing-Anti-Patterns.md)) : implicit use of a component, initialized unknonwingly to the programmer, which lead to difficultly explainable failures.

To prevent piggybacking, we can use the rule [`UnitTestContainsTooManyAsserts`](../pmd-documentation/UnitTestContainsTooManyAsserts.md), which allows PMD to warn the developper about multiple assertions in a test case.

Other anti-patterns, like useless asserts, happy paths and hidden depandancies, relies more on the semantics of the test case, preventing PMD to detect them. For the case of an happy path, PMD cannot infer why the writtten program follows a best-case scenario ; hidden dependancies also relies by definition on unclear dependancy relatations, which can't be detected by PMD.

We test the `UnitTestContainsTooManyAsserts` rule over Apache Commons Lang library using the command :

```bash
pmd check -d commons-lang/ -R category/java/bestpractices.xml/UnitTestContainsTooManyAsserts -r report_lang_tests
```

The [obtained report](report_lang_test) show multiples test cases containing serveral asserts. For example, the following result :

```
commons-lang\src\test\java\org\apache\commons\lang3\ArrayUtilsAddTest.java:35:	UnitTestContainsTooManyAsserts:	Unit tests should not contain more than 1 assert(s).
```

leads to this test case :

```java
@Test
public void testAddFirstBoolean() {
    boolean[] newArray;
    newArray = ArrayUtils.addFirst(null, false);
    assertArrayEquals(new boolean[] { false }, newArray);
    assertEquals(Boolean.TYPE, newArray.getClass().getComponentType());
    newArray = ArrayUtils.addFirst(null, true);
    assertArrayEquals(new boolean[] { true }, newArray);
    assertEquals(Boolean.TYPE, newArray.getClass().getComponentType());
    final boolean[] array1 = { true, false, true };
    newArray = ArrayUtils.addFirst(array1, false);
    assertArrayEquals(new boolean[] { false, true, false, true }, newArray);
    assertEquals(Boolean.TYPE, newArray.getClass().getComponentType());
}
```

which clearly uses piggybacking, as several asserts on serveral values are run in the same test case.
