package org.napbad.scoremanager.util;

import java.util.*;
import java.math.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class RandomDataGeneratorTest {

    private int generatedScore;

    @BeforeEach
    public void setUp() {
        // No setup needed for this particular test case as generateRandomScore() method is stateless.
    }

    @Test
    public void generateRandomScore_ShouldReturnScoreBetweenZeroAndHundred() {
        for (int i = 0; i < 100; i++) { // Test multiple times to cover random nature of the method
            generatedScore = RandomDataGenerator.generateRandomScore();
            assertTrue(generatedScore >= 0 && generatedScore <= 100, "Score should be between 0 and 100");
        }
    }
}
