package fr.istic.vv;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static fr.istic.vv.StringUtils.isBalanced;
import static org.junit.jupiter.api.Assertions.*;

class StringUtilsTest {

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
     * Pass a simple balanced string with characters to isBalanced, and ensure it returns true.
     * We use parentheses "()" for this test.
     */
    @Test
    @DisplayName("simple balanced - parentheses and chars")
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
     * Pass a simple balanced string to isBalanced with characters, and ensure it returns true.
     * We use brackets "[]" for this test.
     */
    @Test
    @DisplayName("simple balanced - brackets and chars")
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
     * Pass a simple balanced string with characters to isBalanced, and ensure it returns true.
     * We use braces "{}" for this test.
     */
    @Test
    @DisplayName("simple balanced - braces and chars")
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
     * Pass a balanced string to isBalanced, with only parentheses imbrications and characters,
     * and ensure it returns true.
     */
    @Test
    @DisplayName("balanced - only imbricated parentheses and chars")
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
     * Pass a balanced string to isBalanced, with only brackets imbrications and chars,
     * and ensure it returns true.
     */
    @Test
    @DisplayName("balanced - only imbricated brackets and chars")
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
     * Pass a balanced string to isBalanced, with only braces imbrications and chars,
     * and ensure it returns true.
     */
    @Test
    @DisplayName("balanced - only imbricated braces and chars")
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
     * Pass a balanced string to isBalanced, with various imbrications and chars,
     * and ensure it returns true.
     */
    @Test
    @DisplayName("balanced - various imbrications and chars")
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
     * Pass a balanced string to isBalanced, with a chain of various imbrications and characters,
     * and ensure it returns true.
     */
    @Test
    @DisplayName("balanced - succesive imbrications and chars")
    void testBalancedSuccessiveImbricationsAndChars() {
        assertTrue(isBalanced("()[a(ze)ty]{{uiop[()]}qsdf}[]{[gh()]}jkl{}mw"));
    }


}