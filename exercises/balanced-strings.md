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

2. For the following implementation (annotated, see more detailed comments in the source code) :

    ```java
    public static boolean isBalanced(String str) {
        // Statement 1
        if (str == null)
            return true;

        Deque<Character> symbStack = new ArrayDeque<>();
        String opening = "{[(";
        String closing = "}])";

        // Statement 2.
        for (char chr : str.toCharArray()) {
            int opIndex = opening.indexOf(chr);
            int clIndex = closing.indexOf(chr);

            // Statement 3.
            if (opIndex != -1)
                symbStack.push(chr);

            // Statement 4.
            if (clIndex != -1) {
                // Statement 5.
                if (symbStack.isEmpty())
                    return false;

                // Statement 6.
                if (symbStack.pop() != opening.charAt(clIndex))
                    return false;
            }
        }

        // Statement 7.
        return symbStack.isEmpty();
    }
    ```

    - Statement 1 : `str == null` is `true` in block 1, `false` in other blocks.

    - Statement 2 : to execute at least one time the body of the loop, the string
    must be not empty : such condition is `true` in block 2, `false` in other 
    blocks ; the block 1 is irrelevant in this case.

    - Statement 3 : the condition is executed if the current char is an opening
    character. **Input space partition does not currently take account if
    there is opening symbols or not in the string**.
    
    - Statement 4 : the condition is executed if the current char is a closing
    character. **Input space partition does not currently take account if
    there is closing symbols or not in the string**.

    - Statement 5 : the block is executed only if the stack is empty. This is
    intended to happen if a closing character does not match any opening character.
    **This case is partially covered by the characteristic 4. However, this
    characteristic does not distinct a missing matching character to other cases
    of unbalanced strings.**

    - Statement 6 : the block is executed if an element corresponding to the 
    closing character is of the current symbol is executed. This is
    intended to happen if a closing character does match an opening character,
    but not the good openin.
    **This case is partially covered by the characteristic 4. However, this
    characteristic does not distinct a wrong matching character to other cases
    of unbalanced strings.**

    - Statement 7 : no unmatched closing character had been encountered, but
    the state of the stack indicates if there is still unmatched opening characters.
    `true` will be returned on blocks 4 and 6 ; however, **the characteristic 4 
    does not distinct an unmatching left opening matching character to other cases
    of unbalanced strings.**

    Following the previous evaluation of the statement coverage, we can add test cases that
    cover these situations :

    - `str` contains any opening symbols (noted `StrAnyOpening`)
    - `str` contains a closing character which does not match any opening character 
    (wrong closing character or not) (noted `StrClosingNoOpening`)
    - `str` contains a closing character which matchs a correct or wrong opening
    character (noted `StrClosingNoOpening`)
    - `str` contains an unmatched opening character (noted `StrClosingWrongOpening`).

We can use these criteria to work on unbalanced strings, and propose
a partition of unbalanced inputs :

| Characteristics          | 1 | 2 | 3 | 4 | 5 | 6 | 7 |
|--------------------------|---|---|---|---|---|---|---|
| `StrAnyOpening`          | f | f | v | v | v | v | v |
| `StrOpeningNoClosing`    | x | x | f | f | f | v | v |
| `StrClosingNoOpening`    | f | v | f | v | v | f | v |
| `StrClosingWrongOpening` | x | x | v | f | v | f | f |

Note that when `StrAnyOpening` is true, i.e. there is no opening character,
`StrOpeningNoClosing` (unmatched opening char) and `StrClosingWrongOpening`
(closing symbol with wrong opeing) are not relevant.

Some blocks had also been supressed while writing test cases, are some were
equivalent, or other where not possibles.

3. The code does not contain any predicate having at least 2 boolean
operators.

4. 

## Answer

