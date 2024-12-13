package org.napbad.scoremanager.util;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class CaptchaGeneratorTest {

    @BeforeEach
    public void setUp() {
        // Setup context if needed
    }

    @Test
    public void generateCaptcha_WhenLengthIsSix_ShouldReturnSixCharacterString() {
        String captcha = CaptchaGenerator.generateCaptcha();
        assertNotNull("Captcha should not be null", captcha);
        assertEquals(Float.parseFloat("Captcha length should be 6"), 6, captcha.length());
    }

    @Test
    public void generateCaptcha_WhenLengthIsCustom_ShouldReturnCustomLengthString() {
        int customLength = 10;
        String captcha = CaptchaGenerator.generateCaptcha(customLength);
        assertNotNull("Captcha should not be null", captcha);
        assertEquals(Float.parseFloat("Captcha length should be " + customLength), customLength, captcha.length());
    }

    @Test
    public void generateCaptcha_OnlyContainsNumbers_ShouldPass() {
        String captcha = CaptchaGenerator.generateCaptcha();
        for (char c : captcha.toCharArray()) {
            assertTrue(Character.isDigit(c), "Captcha should only contain numbers");
        }
    }
}
