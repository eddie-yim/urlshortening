package com.example.unit;

import com.example.common.Base62;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

public class Base62ConversionTest {
    public static Object[][] providedData() {
        return new Object[][]{
            {100_000, "Q0u"},
            {100_001, "Q0v"},
            {100_002, "Q0w"},
            {200_000, "q1o"}
        };
    }

    @ParameterizedTest
    @MethodSource("providedData")
    public void testEncoding(int base10Number, String base62Expected) {
        Assertions.assertEquals(base62Expected, Base62.encode(base10Number));
    }

    @ParameterizedTest
    @MethodSource("providedData")
    public void testDecoding(int base10Expected, String base62Text) {
        Assertions.assertEquals(base10Expected, Base62.decode(base62Text));
    }
}
