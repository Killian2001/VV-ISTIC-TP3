package fr.istic.vv;

import java.util.ArrayDeque;
import java.util.Deque;

public class StringUtils {

    private StringUtils() {}

    /**
     * Indicates if a string is balanced, i.e. <code>{}</code>, <code>[]</code> and <code>()</code>
     * delimiters match each other, and are properly imbricated.
     * For example, the string :
     * <pre>{[][]}({})</pre>
     * is balanced, while the following strings :
     * <pre>][</pre> <pre>([)]</pre> <pre>{</pre> and <pre>{(}{}</pre>
     * are not.
     *
     * @param str The string to check
     * @return <code>true</code> if <code>str</code> is balanced, <code>false</code> otherwise.
     */
    public static boolean isBalanced(String str) {
        if (str == null)
            return true;    // true through emptiness

        Deque<Character> symbStack = new ArrayDeque<>();
        String opening = "{[(";
        String closing = "}])";

        for (char chr : str.toCharArray()) {
            int opIndex = opening.indexOf(chr);
            int clIndex = closing.indexOf(chr);

            // Opening character: push it on the stack.
            if (opIndex != -1)
                symbStack.push(chr);

            // Closing character...
            if (clIndex != -1) {
                // ...if the stack is empty then we do not recognize the word.
                if (symbStack.isEmpty())
                    return false;

                // ...if the char at the top of the stack does not match with the corresponding char
                // we do not recognize the word.
                if (symbStack.pop() != opening.charAt(clIndex))
                    return false;
            }
        }

        // word is recognized if the stack is empty.
        return symbStack.isEmpty();
    }

}
