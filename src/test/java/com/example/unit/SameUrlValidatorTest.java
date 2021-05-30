package com.example.unit;

import com.example.application.url.UrlFinalizer;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

public class SameUrlValidatorTest {
    final static String NAVER = "https://www.naver.com/";

    public static Object[][] providedData() {
        return new Object[][]{
                {"naver.com", true},
                {"http://naver.com/", true},
                {"http://naver.com", true},
                {"http://www.naver.com", true},
                {"https://www.naver.com", false},// false cause of trailing slash
                {"https://www.naver.com?a=b", false}
        };
    }

    @ParameterizedTest
    @MethodSource("providedData")
    public void testSameUrlCheck(String originalUrl, boolean expectedEquality) throws Exception {
        Assertions.assertEquals(expectedEquality, UrlFinalizer.finalize(originalUrl).equals(NAVER));
    }
}
