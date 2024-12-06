package fr.istic.vv;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static fr.istic.vv.StringUtils.isBalanced;
import static org.junit.jupiter.api.Assertions.*;

class StringUtilsTest {

    /* test cases obtained from initial input partition */

    /**
     * Pass a null string to isBalanced, and ensure it returns true (by vacuity).
     */
    @Test
    @DisplayName("null string")
    void testIsNull() {
        assertTrue(isBalanced(null));
    }

    /**
     * Pass an empty string to isBalanced, and ensure it returns true (by vacuity).
     */
    @Test
    @DisplayName("empty string")
    void testIsEmpty() {
        assertTrue(isBalanced(""));
    }

    /**
     * Pass string without grouping symbol to isBalanced, and ensure it returns
     * true
     */
    @Test
    @DisplayName("no grouping symbols")
    void testNoGroupingSymbols() {
        assertTrue(isBalanced("azerty"));
    }

    /**
     * Pass a simple balanced string to isBalanced, and ensure it returns true.
     * We use parentheses "()" for this test.
     */
    @Test
    @DisplayName("simple balanced - parentheses")
    void testBalancedSimpleParentheses() {
        assertTrue(isBalanced("()"));
    }

    /**
     * Pass a simple balanced string with letters to isBalanced, and ensure it returns true.
     * We use parentheses "()" for this test.
     */
    @Test
    @DisplayName("simple balanced - parentheses and letters")
    void testBalancedSimpleParenthesesAndChars() {
        assertTrue(isBalanced("()a"));
    }

    /**
     * Pass a simple balanced string to isBalanced, and ensure it returns true.
     * We use brackets "[]" for this test.
     */
    @Test
    @DisplayName("simple balanced - brackets")
    void testBalancedSimpleBrackets() {
        assertTrue(isBalanced("[]"));
    }

    /**
     * Pass a simple balanced string to isBalanced with letters, and ensure it returns true.
     * We use brackets "[]" for this test.
     */
    @Test
    @DisplayName("simple balanced - brackets and letters")
    void testBalancedSimpleBracketsAndChars() {
        assertTrue(isBalanced("[azerty]"));
    }

    /**
     * Pass a simple balanced string to isBalanced, and ensure it returns true.
     * We use braces "{}" for this test.
     */
    @Test
    @DisplayName("simple balanced - braces")
    void testBalancedSimpleBraces() {
        assertTrue(isBalanced("{}"));
    }

    /**
     * Pass a simple balanced string with letters to isBalanced, and ensure it returns true.
     * We use braces "{}" for this test.
     */
    @Test
    @DisplayName("simple balanced - braces and letters")
    void testBalancedSimpleBracesAndChars() {
        assertTrue(isBalanced("az{er}ty"));
    }

    /**
     * Pass a balanced string to isBalanced, with only parentheses imbrications,
     * and ensure it returns true.
     */
    @Test
    @DisplayName("balanced - only imbricated parentheses")
    void testBalancedImbricatedParentheses() {
        assertTrue(isBalanced("(((())))"));
    }

    /**
     * Pass a balanced string to isBalanced, with only parentheses imbrications and letters,
     * and ensure it returns true.
     */
    @Test
    @DisplayName("balanced - only imbricated parentheses and letters")
    void testBalancedImbricatedParenthesesChars() {
        assertTrue(isBalanced("(az(((er))))ty"));
    }

    /**
     * Pass a balanced string to isBalanced, with only brackets imbrications,
     * and ensure it returns true.
     */
    @Test
    @DisplayName("balanced - only imbricated brackets")
    void testBalancedImbricatedBrackets() {
        assertTrue(isBalanced("[[[[]]]]"));
    }

    /**
     * Pass a balanced string to isBalanced, with only brackets imbrications and letters,
     * and ensure it returns true.
     */
    @Test
    @DisplayName("balanced - only imbricated brackets and letters")
    void testBalancedImbricatedBracketsAndChars() {
        assertTrue(isBalanced("a[z[e[r[t]y]i]u]o"));
    }

    /**
     * Pass a balanced string to isBalanced, with only braces imbrications,
     * and ensure it returns true.
     */
    @Test
    @DisplayName("balanced - only imbricated braces")
    void testBalancedImbricatedBraces() {
        assertTrue(isBalanced("{{{{}}}}"));
    }

    /**
     * Pass a balanced string to isBalanced, with only braces imbrications and letters,
     * and ensure it returns true.
     */
    @Test
    @DisplayName("balanced - only imbricated braces and letters")
    void testBalancedImbricatedBracesAndChars() {
        assertTrue(isBalanced("{azer{{{ty}}}}"));
    }

    /**
     * Pass a balanced string to isBalanced, with various imbrications,
     * and ensure it returns true.
     */
    @Test
    @DisplayName("balanced - various imbrications")
    void testBalancedImbricatedVarious() {
        assertTrue(isBalanced("({[[()]]})"));
    }

    /**
     * Pass a balanced string to isBalanced, with various imbrications and letters,
     * and ensure it returns true.
     */
    @Test
    @DisplayName("balanced - various imbrications and letters")
    void testBalancedImbricatedVariousAndChars() {
        assertTrue(isBalanced("azer({[ty[(x)]]uiop})"));
    }

    /**
     * Pass a balanced string to isBalanced, with a chain of various imbrications,
     * and ensure it returns true.
     */
    @Test
    @DisplayName("balanced - succesive imbrications")
    void testBalancedSuccessiveImbrications() {
        assertTrue(isBalanced("()[()]{{[()]}}[]{[()]}{}"));
    }

    /**
     * Pass a balanced string to isBalanced, with a chain of various imbrications and letters,
     * and ensure it returns true.
     */
    @Test
    @DisplayName("balanced - succesive imbrications and letters")
    void testBalancedSuccessiveImbricationsAndChars() {
        assertTrue(isBalanced("()[a(ze)ty]{{uiop[()]}qsdf}[]{[gh()]}jkl{}mw"));
    }

    /* test cases added after statement coverage */

    /**
     * Pass an unbalanced string to isBalanced, with a closing char without an opening char
     * and ensure it returns false.
     */
    @Test
    @DisplayName("unbalanced - closing char with no opening char")
    void testUnbalancedNoOpening() {
        assertFalse(isBalanced(")"));
    }

    /**
     * Pass an unbalanced string to isBalanced, with a closing char without an opening char with
     * letters and it returns false.
     */
    @Test
    @DisplayName("unbalanced - closing char with no opening char and letters")
    void testUnbalancedNoOpeningAndChars() {
        assertFalse(isBalanced("az)er"));
    }

    /**
     * Pass an unbalanced string to isBalanced, with a closing char without an opening char
     * and ensure it returns false.
     */
    @Test
    @DisplayName("unbalanced - opening char with no closing char ")
    void testUnbalancedOpeningNoClosing() {
        assertFalse(isBalanced("[("));
    }

    /**
     * Pass an unbalanced string to isBalanced, with a closing char without an opening char,
     * with letters and ensure it returns false.
     */
    @Test
    @DisplayName("unbalanced - opening char with no closing char with letters")
    void testUnbalancedOpeningNoClosingAndChar() {
        assertFalse(isBalanced("[azre(t"));
    }

    /**
     * Pass an unbalanced string to isBalanced, with a closing char with a wrong opening char
     * and ensure it returns false.
     */
    @Test
    @DisplayName("unbalanced - closing char with wrong opening char ")
    void testUnbalancedClosingWrongOpening() {
        assertFalse(isBalanced("([))"));
    }

    /**
     * Pass an unbalanced string to isBalanced, with a closing char with a wrong opening char
     * with letters and ensure it returns false.
     */
    @Test
    @DisplayName("unbalanced - closing char with wrong opening char ")
    void testUnbalancedClosingWrongOpeningAndChar() {
        assertFalse(isBalanced("az([erty)ui)"));
    }

    /**
     * Pass an unbalanced string to isBalanced, with a closing char without opening char and closing
     * char with a wrong opening char and ensure it returns false.
     */
    @Test
    @DisplayName("unbalanced - closing char without opening char, closing char with wrong opening char ")
    void testUnbalancedWithoutOpeningWrongOpening() {
        assertFalse(isBalanced("(}]"));
    }

    /**
     * Pass an unbalanced string to isBalanced, with a closing char without opening char and closing
     * char with a wrong opening char with letters and ensure it returns false.
     */
    @Test
    @DisplayName("unbalanced - closing char without opening char, closing char with wrong opening char ")
    void testUnbalancedWithoutOpeningWrongOpeningAndChars() {
        assertFalse(isBalanced("a(zert}]y"));
    }

    /**
     * Pass an unbalanced string to isBalanced with multiple imbrications, and
     * ensure it returns false.
     */
    @Test
    @DisplayName("unbalanced - multiple imbrications")
    void testUnbalancedMultipleImbrications() {
        assertFalse(isBalanced("{{({)]]}}"));
    }

    /**
     * Pass an unbalanced string to isBalanced with multiple imbrications, and
     * ensure it returns false.
     */
    @Test
    @DisplayName("unbalanced - multiple imbrications with letters")
    void testUnbalancedMultipleImbricationsAndChars() {
        assertFalse(isBalanced("{az{er(ttt{)yuio]]}ppp}"));
    }

    /**
     * Pass an unbalanced string to isBalanced with a series of imbrications, and
     * ensure it returns false.
     */
    @Test
    @DisplayName("unbalanced - succesive imbrications")
    void testUnbalancedSuccessiveImbrications() {
        assertFalse(isBalanced("{{()}}()[{}][(]}(]"));
    }

    /**
     * Pass an unbalanced string to isBalanced with a series of imbrications with letters, and
     * ensure it returns false.
     */
    @Test
    @DisplayName("unbalanced - succesive imbrications with letters")
    void testUnbalancedSuccessiveImbricationsAndChars() {
        assertFalse(isBalanced("azerr{yuio{()}sdgfh}()[f{}]fvbdfn[(]f}(]"));
    }
}