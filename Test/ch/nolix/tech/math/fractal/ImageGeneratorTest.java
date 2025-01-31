package ch.nolix.tech.math.fractal;

import org.junit.jupiter.api.Test;

import ch.nolix.core.testing.standardtest.StandardTest;

final class ImageGeneratorTest extends StandardTest {

  @Test
  void testCase_getStoredImage() {

    //setup
    final var fractalBuilder = new FractalBuilder().setWidthInPixel(10).setHeightInPixel(5);
    final var fractal = fractalBuilder.build();
    final var testUnit = ImageGenerator.forFractal(fractal);
    testUnit.waitUntilIsFinished();

    //execution
    final var result = testUnit.getStoredImage();

    //verification
    expect(result.getWidth()).isEqualTo(10);
    expect(result.getHeight()).isEqualTo(5);
  }
}
