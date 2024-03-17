//package declaration
package ch.nolix.techtest.mathtest.fractaltest;

import org.junit.jupiter.api.Test;

//own imports
import ch.nolix.core.testing.test.StandardTest;
import ch.nolix.tech.math.fractal.FractalBuilder;
import ch.nolix.tech.math.fractal.ImageGenerator;

//class
public final class ImageGeneratorTest extends StandardTest {

  //method
  @Test
  public void testCase_getStoredImage() {

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
