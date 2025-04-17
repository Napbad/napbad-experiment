package org.napbad.software_test;

import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class FakeBallFinderTest {

    @Test
    public void testFindFakeBallInFirstGroupAndUnequalSecondWeighing() {
        FakeBallFinder finder = new FakeBallFinder();
        int[] balls = new int[10];
        for (int i = 0; i < 10; i++) {
            balls[i] = 1;
        }
        balls[2] = 0;
        int result = finder.findFakeBall(balls);
        assertEquals(2, result);
    }

    @Test
    public void testFindFakeBallInSecondGroupAndUnequalSecondWeighing() {
        FakeBallFinder finder = new FakeBallFinder();
        int[] balls = new int[10];
        for (int i = 0; i < 10; i++) {
            balls[i] = 1;
        }
        balls[7] = 0;
        int result = finder.findFakeBall(balls);
        assertEquals(7, result);
    }

    @Test
    public void testFindFakeBallInFirstGroupAndEqualSecondWeighing() {
        FakeBallFinder finder = new FakeBallFinder();
        int[] balls = new int[10];
        for (int i = 0; i < 10; i++) {
            balls[i] = 1;
        }
        balls[4] = 0;
        int result = finder.findFakeBall(balls);
        assertEquals(4, result);
    }

    @Test
    public void testFindFakeBallInSecondGroupAndEqualSecondWeighing() {
        FakeBallFinder finder = new FakeBallFinder();
        int[] balls = new int[10];
        for (int i = 0; i < 10; i++) {
            balls[i] = 1;
        }
        balls[9] = 0;
        int result = finder.findFakeBall(balls);
        assertEquals(9, result);
    }
}    