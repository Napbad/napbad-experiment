/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.napbad.scoresystem.util.CaptchaGenerator
 */
package org.napbad.scoremanager.util;

import java.util.Random;

/*
 * Exception performing whole class analysis ignored.
 */
public class CaptchaGenerator {
    private static final int LENGTH = 6;
    private static final char[] CHARACTERS = new char[]{'0', '1', '2', '3', '4', '5', '6', '7', '8', '9'};
    private static final Random RANDOM = new Random();

    public static String generateCaptcha() {
        return CaptchaGenerator.generateCaptcha((int)6);
    }

    public static String generateCaptcha(int length) {
        char[] captcha = new char[length];
        for (int i = 0; i < length; ++i) {
            captcha[i] = CHARACTERS[RANDOM.nextInt(CHARACTERS.length)];
        }
        return new String(captcha);
    }

    private CaptchaGenerator() {
    }
}

