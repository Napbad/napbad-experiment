/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.napbad.scoresystem.util.RandomDataGenerator
 */
package org.napbad.scoremanager.util;

import java.util.Random;

public class RandomDataGenerator {
    private static final Random random = new Random();

    public static int generateRandomScore() {
        return random.nextInt(100);
    }
}

