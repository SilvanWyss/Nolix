package ch.nolix.tech.math.fractal;

import org.junit.jupiter.api.Test;

import ch.nolix.core.testing.standardtest.StandardTest;
import ch.nolix.tech.math.bigdecimalmath.ClosedInterval;

final class FractalBuilderTest extends StandardTest {

  @Test
  void testCase_build() {

    //setup
    final var testUnit = new FractalBuilder();
    testUnit
      .setWidthInPixel(1_200)
      .setHeightInPixel(800)
      .setRealComponentInterval(-1.0, 1.0)
      .setImaginaryComponentInterval(0.5, 2.5)
      .setMaxIterationCount(150)
      .setDecimalPlaces(30);

    //execution
    final var result = testUnit.build();

    //verification
    expect(result.getWidthInPixel()).isEqualTo(1_200);
    expect(result.getHeightInPixel()).isEqualTo(800);
    expect(result.getRealComponentInterval()).isEqualTo(new ClosedInterval(-1.0, 1.0, 30));
    expect(result.getImaginaryComponentInterval()).isEqualTo(new ClosedInterval(0.5, 2.5, 30));
    expect(result.getMaxIterationCount()).isEqualTo(150);
    expect(result.getDecimalPlaces()).isEqualTo(30);
  }
}
