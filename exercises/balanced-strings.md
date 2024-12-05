# Balanced strings

A string containing grouping symbols `{}[]()` is said to be balanced if every open symbol `{[(` has a matching closed symbol `)]}` and the substrings before, after and between each pair of symbols is also balanced. The empty string is considered as balanced.

For example: `{[][]}({})` is balanced, while `][`, `([)]`, `{`, `{(}{}` are not.

Implement the following method:

```java
public static boolean isBalanced(String str) {
    ...
}
```

`isBalanced` returns `true` if `str` is balanced according to the rules explained above. Otherwise, it returns `false`.

Use the coverage criteria studied in classes as follows:

1. Use input space partitioning to design an initial set of inputs. Explain below the characteristics and partition blocks you identified.
2. Evaluate the statement coverage of the test cases designed in the previous step. If needed, add new test cases to increase the coverage. Describe below what you did in this step.
3. If you have in your code any predicate that uses more than two boolean operators, check if the test cases written so far satisfy *Base Choice Coverage*. If needed, add new test cases. Describe below how you evaluated the logic coverage and the new test cases you added.
4. Use PIT to evaluate the test suite you have so far. Describe below the mutation score and the live mutants. Add new test cases or refactor the existing ones to achieve a high mutation score.

Write below the actions you took on each step and the results you obtained.
Use the project in [tp3-balanced-strings](../code/tp3-balanced-strings) to complete this exercise.

1. We can identify the following characteristics about the input :

    1. `str` is `null` (boolean characteristic)
    2. `str` is `empty`  (boolean characteristic)
    3. `str` is balanced (boolean characteristic).
    4. `str` contains other chars than grouping symbols (boolean characteristic).

    Characteristics 1 and 2 are simple to test and both need only one test case
    (a `null` string can only take the value `null`, and an empty string can take only the `""` value.)

    Characteristics 3 and 4 are more difficult to test : they admit both a supposedly infinite set
    of values; furthermore, we cannot split the set of balanced strings between a finite number
    of blocks, due to its inductive nature.

    We propose the following blocks over the input space of the function :

    | Characteristics                 | Block 1    | Block 2    | Block 3 | Block 4 | Block 5 | Block 6 |
    |---------------------------------|------------|------------|---------|---------|---------|---------|
    | `str` is `null`                 | true       | false      | false   | false   | false   | false   |
    | `str` is empty                  | irrelevant | true       | false   | false   | false   | false   |
    | `str` contains other characters | irrelevant | irrelevant | false   | false   | true    | true    |
    | `str` is balanced               | irrelevant | irrelevant | false   | true    | false   | true    |

    Block 1 and block 2 correspond to the basic testing of the characteristics 1 and 2 ; in such
    cases, it is irrelevant to work on all characterisitics, as there is no interest to check all 
    properties over `null` and `""`.

    Blocks 3 and 4 test values over balanced and unbalanced strings with only separator characters.

    Blocks 5 and 6 test values over balanced and unbalanced strings with all possible characters.

## Answer

