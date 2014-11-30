package ru.yandex.autoschool.splinter.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author Etki {@literal <etki@etki.name>}
 * @version %I%, %G%
 * @since 1.0
 */
public class TextProcessor {
    public static final String PARAGRAPH_TOKEN_DOUBLE_LINEFEED = "\n\\s*\n";
    // Truncated so <br>, <br/> and <br /> would be equal.
    // In any way paragraph ending position should be considered as `token starting position - 1`
    public static final String PARAGRAPH_TOKEN_BR_TAG = "<br";
    public static final String PARAGRAPH_TOKEN_P_TAG = "<p";

    /**
     * Returns position of last occurrence of some text matched by regexp in
     * string.
     *
     * @param string Text to analyze.
     * @param regexp Regexp to use.
     * @return Position of last regexp match in provided string or -1 if not
     * found.
     */
    public static int findLastRegexpOccurrence(String string, String regexp) {
        int lastPosition = -1;
        Pattern pattern = Pattern.compile(regexp);
        Matcher matcher = pattern.matcher(string);
        while (matcher.find()) {
            lastPosition = matcher.start();
        }
        return lastPosition;
    }

    /**
     * Performs right-trim removing all whitespace characters from the end of
     * the string.
     *
     * @param string String to be trimmed.
     * @return Trimmed string
     */
    public static StringBuilder rightTrim(StringBuilder string) {
        int firstPosition = string.length();
        while (Character.isWhitespace(string.charAt(firstPosition - 1))) {
            firstPosition--;
        }
        if (firstPosition == string.length()) {
            return string;
        }
        StringBuilder clone = new StringBuilder(string.toString());
        return clone.delete(firstPosition, clone.length());
    }

    /**
     * Performs right-trim removing all whitespace characters from the end of
     * the string.
     *
     * @param string String to be trimmed.
     * @return Trimmed string
     */
    public static String rightTrim(String string) {
        int firstPosition = string.length();
        while (Character.isWhitespace(string.charAt(firstPosition - 1))) {
            firstPosition--;
        }
        if (firstPosition == string.length()) {
            return string;
        }
        return string.substring(0, firstPosition);
    }

    /**
     * Performs left-trim removing all whitespace characters from the beginning
     * of the string.
     *
     * @param string String to be trimmed.
     * @return Trimmed string
     */
    public static StringBuilder leftTrim(StringBuilder string) {
        int lastPosition = -1;
        while (Character.isWhitespace(string.charAt(lastPosition + 1))) {
            lastPosition++;
        }
        if (lastPosition == -1) {
            return string;
        }
        StringBuilder clone = new StringBuilder(string.toString());
        return clone.delete(0, lastPosition + 1);
    }

    /**
     * Performs left-trim removing all whitespace characters from the beginning
     * of the string.
     *
     * @param string String to be trimmed.
     * @return Trimmed string
     */
    public static String leftTrim(String string) {
        int lastPosition = -1;
        while (Character.isWhitespace(string.charAt(lastPosition + 1))) {
            lastPosition++;
        }
        return lastPosition == -1 ? string : string.substring(lastPosition + 1);
    }
    /**
     * Trims string from the right, leaving `maxLength` chars at max. Doesn't do
     * anything to strings smaller than `maxLength`.
     *
     * @param string    String that should be truncated.
     * @param maxLength Maximum amount of characters allowed.
     * @return Truncated string.
     */
    public static String truncateByChar(String string, int maxLength) {
        if (string.length() <= maxLength) {
            return string;
        }
        return string.substring(0, maxLength);
    }

    /**
     * Trims string from the right, leaving `maxLength` chars at max and
     * appending suffix to it. Suffix length is included in `maxLength` .
     * Doesn't do anything to strings smaller than `maxLength`.
     *
     * @param string    String that should be truncated.
     * @param maxLength Maximum amount of characters allowed.
     * @param suffix    String that should be appended to truncated string.
     * @return Truncated string.
     */
    public static String truncateByChar(
        String string,
        int maxLength,
        String suffix
    ) throws IllegalArgumentException {
        if (suffix.length() >= maxLength) {
            throw new IllegalArgumentException(
                "Suffix length can't be bigger or equal to maxLength"
            );
        }
        if (string.length() <= maxLength) {
            return string;
        } else {
            return truncateByChar(string, maxLength - suffix.length()) + suffix;
        }
    }

    /**
     * Trims string from the right by the end of the closest word, leaving
     * `maxLength` chars at max. If string consists of single word,
     * `truncateByChar()` is applied. Doesn't do anything to strings smaller
     * than `maxLength`.
     *
     * @param string    String that should be truncated.
     * @param maxLength Maximum amount of characters allowed.
     * @return Truncated string.
     */
    public static String truncateByWord(String string, int maxLength) {
        if (string.length() <= maxLength) {
            return string;
        }
        int i = maxLength;
        for (; i > 0; i--) {
            if (Character.isWhitespace(string.charAt(i))) {
                break;
            }
        }
        if (i > 0) {
            return rightTrim(string.substring(0, i));
        }
        return truncateByChar(string, maxLength);
    }

    /**
     * Trims string from the right by the end of the closest word, leaving
     * `maxLength` chars at max and appending suffix to it. Suffix length is
     * included in `maxLength`. If string consists of single word,
     * `truncateByChar()` is applied. Doesn't do anything to strings smaller
     * than `maxLength`.
     *
     * @param string    String that should be truncated.
     * @param maxLength Maximum amount of characters allowed.
     * @param suffix    String that should be appended to truncated string.
     * @return Truncated string.
     */
    public static String truncateByWord(
        String string,
        int maxLength,
        String suffix
    ) throws IllegalArgumentException {
        if (suffix.length() >= maxLength) {
            throw new IllegalArgumentException(
                "Suffix length can't be bigger or equal to maxLength"
            );
        }
        if (string.length() <= maxLength) {
            return string;
        }
        return truncateByWord(string, maxLength - suffix.length()) + suffix;
    }

    /**
     * Truncates string by the end of closest paragraph, leaving `maxLength`
     * characters at max. If no paragraph ending is found, falls back on
     * `truncateWord()`. Doesn't do anything to string smaller than maxLength.
     *
     * @param string    String that should be truncated.
     * @param maxLength Maximum amount of characters allowed.
     * @return Truncated string.
     */
    public static String truncateByParagraph(String string, int maxLength) {
        if (string.length() <= maxLength) {
            return string;
        }
        int positions[] = new int[3];
        String[] tokens = {
            PARAGRAPH_TOKEN_BR_TAG,
            PARAGRAPH_TOKEN_P_TAG,
            PARAGRAPH_TOKEN_DOUBLE_LINEFEED,
        };
        String chunk = truncateByChar(string, maxLength);
        for (int i = 0; i < 3; i++) {
            positions[i] = findLastRegexpOccurrence(chunk, tokens[i]);
        }
        int position = Math.max(
                positions[0],
                Math.max(positions[1], positions[2])
        );
        if (position > 0) {
            return rightTrim(string.substring(0, position));
        }
        return truncateByWord(string, maxLength);
    }
    /**
     * Trims string from the right by the end of the closest word, leaving
     * `maxLength` chars at max and
     * appending suffix to it. Suffix length is included in `maxLength` .
     * Doesn't do anything to strings smaller than `maxLength`.
     *
     * @param string    String that should be truncated.
     * @param maxLength Maximum amount of characters allowed.
     * @param suffix    String that should be appended to truncated string.
     * @return Truncated string.
     */
    public static String truncateByParagraph(
            String string,
            int maxLength,
            String suffix
    ) throws IllegalArgumentException {
        if (suffix.length() >= maxLength) {
            throw new IllegalArgumentException(
                "Suffix length can't be bigger or equal to maxLength"
            );
        }
        if (string.length() <= maxLength) {
            return string;
        }
        return truncateByParagraph(string, maxLength - suffix.length())
                + suffix;
    }

    /**
     * Trims string from the right by the end of the closest word, leaving
     * `maxLength` chars at max and
     * appending suffix to it. Suffix length is included in `maxLength` .
     * Doesn't do anything to strings smaller than `maxLength`.
     *
     * @param string    String that should be truncated.
     * @param maxLength Maximum amount of characters allowed.
     * @param suffix    String that should be appended to truncated string.
     * @return Truncated string.
     */
    public static String truncateByParagraph(
        String string,
        int maxLength,
        String token,
        String suffix
    ) throws IllegalArgumentException {
        if (suffix.length() >= maxLength) {
            throw new IllegalArgumentException(
                "Suffix length can't be bigger or equal to maxLength"
            );
        }
        if (token.length() >= maxLength) {
            throw new IllegalArgumentException(
                "Token  length can't be bigger or equal to maxLength"
            );
        }
        if (string.length() <= maxLength) {
            return string;
        }
        int length = maxLength - token.length() - suffix.length();
        int position = findLastRegexpOccurrence(
                truncateByChar(string, length),
                token
        );
        if (position > 0) {
            return rightTrim(string.substring(0, position)) + suffix;
        }
        return truncateByWord(string, maxLength, suffix);
    }
}
