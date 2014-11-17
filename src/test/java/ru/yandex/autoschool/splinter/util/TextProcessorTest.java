package ru.yandex.autoschool.splinter.util;

import org.apache.commons.lang.StringUtils;
import org.junit.Test;
//import org.junit.runners.Parameterized;
//import org.junit.runners.Parameterized.Parameter;
//import org.junit.runners.Parameterized.Parameters;
import org.junit.runner.RunWith;
import org.junit.Assert;

import static junitparams.JUnitParamsRunner.$;
import junitparams.JUnitParamsRunner;
import junitparams.Parameters;

import ru.yandex.autoschool.splinter.utils.TextProcessor;

/**
 * @author Etki {@literal <etki@etki.name>}
 * @version %I%, %G%
 * @since 1.0
 */
@SuppressWarnings("UnusedDeclaration")
@RunWith(JUnitParamsRunner.class)
public class TextProcessorTest {
    private Object[] truncateByCharParameters() {
        return $(
                $(
                        "Quick brown fox jumps over a lazy dog",
                        12,
                        "Quick brown "
                ),
                $(
                        "Quicky",
                        0,
                        ""
                ),
                $(
                        "Quicky",
                        0,
                        ""
                ),
                $(
                        "Quicky",
                        5,
                        "Quick"
                ),
                $(
                        "Quicky",
                        6,
                        "Quicky"
                ),
                $(
                        "Quicky",
                        12,
                        "Quicky"
                ),
                $(
                        "Изподъездный гопник",
                        12,
                        "Изподъездный"
                )
        );
    }
    private Object[] truncateByWordParameters() {
        return $(
            $("Раз два три четыре пять", 9, "Раз два"),
            $("Раз два три четыре пять", 11, "Раз два три"),
            $("Раз два три четыре пять", 0, ""),
            $("Раз два   три четыре пять", 9, "Раз два"),
            $(
                "Раз два три четыре пять",
                23,
                "Раз два три четыре пять"
            ),
            $(
                "Эйяфьядлайёкюдль/эйяфьятлайокудль",
                10,
                "Эйяфьядлай"
            )
        );
    }
    private Object[] truncateByParagraphParameters() {
        String stringA = "Однажды, в студеную зимнюю пору \n\n Я из лесу вышел";
        String stringB = "Однажды, в студеную <br> зимнюю пору \n\n Я из лесу<p> вышел";
        String stringC = "Однажды, в студеную зимнюю  \n   \r\n пору Я из лесу вышел";
        return $(
            $(
                stringA,
                stringA.length() - 1,
                "Однажды, в студеную зимнюю пору"
            ),
            $(
                stringA,
                stringA.length(),
                stringA
            ),
            $(
                stringB,
                stringB.length() - 3,
                "Однажды, в студеную <br> зимнюю пору \n\n Я из лесу"
            ),
            $(
                stringC,
                stringC.length() - 3,
                "Однажды, в студеную зимнюю"
            ),
            $(
                "Однажды, в студеную зимнюю пору  Я из лесу вышел",
                20,
                "Однажды, в студеную"
            ),
            $(
                StringUtils.repeat("0", 20),
                10,
                StringUtils.repeat("0", 10)
            )
        );
    }
    private Object[] truncateByCharWithSuffixParameters() {
        return $(
            $(
                StringUtils.repeat("12 символов ", 3),
                36,
                "...",
                StringUtils.repeat("12 символов ", 3)
            ),
            $(
                StringUtils.repeat("12 символов ", 3),
                27,
                "...",
                StringUtils.repeat("12 символов ", 2) + "..."
            )
        );
    }
    private Object[] truncateByWordWithSuffixParameters() {
        String stringA = "Все не так уж важно";
        return $(
            $(
                stringA,
                stringA.length(),
                "...",
                stringA
            ),
            $(
                stringA,
                stringA.length() - 1,
                "...",
                "Все не так уж..."
            ),
            $(
                stringA,
                stringA.length() - 1,
                "",
                "Все не так уж"
            ),
            $(
                StringUtils.repeat("0", 20),
                10,
                "...",
                StringUtils.repeat("0", 7) + "..."
            )
        );
    }
    private Object[] truncateByParagraphWithSuffixParameters() {
        String paragraphA = "Lorem Ipsum \n\n A tragedy by The Internets";
        return $(
            $(
                paragraphA,
                paragraphA.length(),
                "...",
                paragraphA
            ),
            $(
                paragraphA,
                paragraphA.length() - 1,
                "...",
                "Lorem Ipsum..."
            ),
            $(
                paragraphA,
                8,
                "...",
                "Lorem..."
            ),
            $(
                paragraphA,
                7,
                "...",
                "Lore..."
            )
        );
    }
    private Object[] truncateByParagraphTokenWithSuffixParameters() {
        String subject = "Однажды в <br> <p> \n\r\n студеную зимнюю пору</p>";
        return $(
            $(
                subject,
                subject.length() - 10,
                TextProcessor.PARAGRAPH_TOKEN_BR_TAG,
                "...",
                "Однажды в..."
            ),
            $(
                subject,
                subject.length() - 10,
                TextProcessor.PARAGRAPH_TOKEN_P_TAG,
                "...",
                "Однажды в <br>..."
            ),
            $(
                subject,
                subject.length() - 10,
                TextProcessor.PARAGRAPH_TOKEN_DOUBLE_LINEFEED,
                "...",
                "Однажды в <br> <p>..."
            )
        );
    }
    private Object[] findLastRegexpOccurrenceParameters() {
        return $(
                $(
                    "this is a sha pi to",
                    "\\w{3}",
                    "this is a sha".length() - 3
                )
        );
    }
    private Object[] leftTrimParameters() {
        return $(
            $("test", "test"),
            $("   test  ", "test  ")
        );
    }
    private Object[] rightTrimParameters() {
        return $(
            $("test", "test"),
            $("   test  ", "   test")
        );
    }
    @Test
    @Parameters(method = "truncateByCharParameters")
    public void testTruncateByChar(
        String input,
        int maxLength,
        String expectedOutput
    ) {
        Assert.assertEquals(
            expectedOutput,
            TextProcessor.truncateByChar(input, maxLength)
        );
    }
    @Test
    @Parameters(method = "truncateByWordParameters")
    public void testTruncateByWord(
        String input,
        int maxLength,
        String expectedOutput
    ) {
        Assert.assertEquals(
            expectedOutput,
            TextProcessor.truncateByWord(input, maxLength)
        );
    }
    @Test
    @Parameters(method = "truncateByParagraphParameters")
    public void testTruncateByParagraph(
        String input,
        int maxLength,
        String expectedOutput
    ) {
        Assert.assertEquals(
            expectedOutput,
            TextProcessor.truncateByParagraph(input, maxLength)
        );
    }
    @Test
    @Parameters(method = "truncateByCharWithSuffixParameters")
    public void testTruncateByCharWithSuffix(
        String input,
        int maxLength,
        String suffix,
        String expectedOutput
    ) {
        Assert.assertEquals(
            expectedOutput,
            TextProcessor.truncateByChar(input, maxLength, suffix)
        );
    }
    @Test
    @Parameters(method = "truncateByWordWithSuffixParameters")
    public void testTruncateByWordWithSuffix(
        String input,
        int maxLength,
        String suffix,
        String expectedOutput
    ) {
        Assert.assertEquals(
            expectedOutput,
            TextProcessor.truncateByWord(input, maxLength, suffix)
        );
    }
    @Test
    @Parameters(method = "truncateByParagraphWithSuffixParameters")
    public void testTruncateByParagraphWithSuffix(
        String input,
        int maxLength,
        String suffix,
        String expectedOutput
    ) {
        Assert.assertEquals(
            expectedOutput,
            TextProcessor.truncateByParagraph(input, maxLength, suffix)
        );
    }
    @Test
    @Parameters(method =  "truncateByParagraphTokenWithSuffixParameters")
    public void testTruncateByParagraphTokenWithSuffix(
        String input,
        int maxLength,
        String token,
        String suffix,
        String expectedOutput
    ) {
        String actualOutput = TextProcessor.truncateByParagraph(
            input,
            maxLength,
            token,
            suffix
        );
        Assert.assertEquals(expectedOutput, actualOutput);
    }
    @Test
    @Parameters(method = "findLastRegexpOccurrenceParameters")
    public void testFindLastRegexpOccurrence(
        String subject,
        String regexp,
        int position
    ) {
        Assert.assertEquals(
            position,
            TextProcessor.findLastRegexpOccurrence(subject, regexp)
        );
    }
    @Test
    @Parameters(method = "leftTrimParameters")
    public void testLeftTrim(String string, String expectedOutput) {
        Assert.assertEquals(expectedOutput, TextProcessor.leftTrim(string));
        StringBuilder stringBuilder = new StringBuilder(string);
        Assert.assertEquals(
            expectedOutput,
            TextProcessor.leftTrim(stringBuilder).toString()
        );
    }
    @Test
    @Parameters(method = "rightTrimParameters")
    public void testRightTrim(String string, String expectedOutput) {
        Assert.assertEquals(expectedOutput, TextProcessor.rightTrim(string));
        StringBuilder stringBuilder = new StringBuilder(string);
        Assert.assertEquals(
            expectedOutput,
            TextProcessor.rightTrim(stringBuilder).toString()
        );
    }
}
