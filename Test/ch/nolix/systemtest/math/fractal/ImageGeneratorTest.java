/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.systemtest.math.fractal;

import org.junit.jupiter.api.Test;

import ch.nolix.base.testing.standardtest.StandardTest;
import ch.nolix.system.math.fractal.FractalBuilder;
import ch.nolix.system.math.fractal.ImageGenerator;

/**
 * @author Silvan Wyss
 */
final class ImageGeneratorTest extends StandardTest {
  @Test
  void testCase_getStoredImage() {
    // setup
    final var fractalBuilder = new FractalBuilder().setWidthInPixel(10).setHeightInPixel(5);
    final var fractal = fractalBuilder.build();
    final var testUnit = ImageGenerator.forFractal(fractal);
    testUnit.waitUntilIsFinished();

    // execute
    final var result = testUnit.getStoredImage();

    // verify
    expect(result.getWidth()).isEqualTo(10);
    expect(result.getHeight()).isEqualTo(5);
  }
}
