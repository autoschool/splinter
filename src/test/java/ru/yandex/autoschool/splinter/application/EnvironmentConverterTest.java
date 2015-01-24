package ru.yandex.autoschool.splinter.application;

import org.junit.Test;
import org.junit.runner.RunWith;

import static junitparams.JUnitParamsRunner.$;
import junitparams.JUnitParamsRunner;
import junitparams.Parameters;

import static org.junit.Assert.*;

/**
 * Stupidest test ever.
 */
@SuppressWarnings("UnusedDeclaration")
@RunWith(JUnitParamsRunner.class)
public class EnvironmentConverterTest {
    private Object[] stringValueParameters() {
        return $(
                $("production", Environment.PRODUCTION),
                $("prod", Environment.PRODUCTION),
                $("test", Environment.TESTING),
                $("testing", Environment.TESTING),
                $("development", Environment.DEVELOPMENT),
                $("dev", Environment.DEVELOPMENT),
                $("develop", Environment.DEVELOPMENT),
                $("devel", Environment.DEVELOPMENT)
        );
    }
    @SuppressWarnings("TestMethodWithIncorrectSignature")
    @Test
    @Parameters(method = "stringValueParameters")
    public void shouldConvertStringValues(String input, Environment expected) throws Exception {
        assertEquals(expected, EnvironmentConverter.convertFromString(input));
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowExceptionOnInvalidInput() {
        EnvironmentConverter.convertFromString("nonsense");
    }
}
