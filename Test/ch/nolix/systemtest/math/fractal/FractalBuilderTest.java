/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.systemtest.math.fractal;

import org.junit.jupiter.api.Test;

import ch.nolix.base.testing.standardtest.StandardTest;
import ch.nolix.system.math.bigdecimalmath.ClosedInterval;
import ch.nolix.system.math.fractal.FractalBuilder;

/**
 * @author Silvan Wyss
 */
final class FractalBuilderTest extends StandardTest {
  @Test
  void testCase_build() {
    // setup
    final var testUnit = new FractalBuilder();
    testUnit
      .setWidthInPixel(1_200)
      .setHeightInPixel(800)
      .setRealComponentInterval(-1.0, 1.0)
      .setImaginaryComponentInterval(0.5, 2.5)
      .setMaxIterationCount(150)
      .setDecimalPlaces(30);

    // execute
    final var result = testUnit.build();

    // verify
    expect(result.getWidthInPixel()).isEqualTo(1_200);
    expect(result.getHeightInPixel()).isEqualTo(800);
    expect(result.getRealComponentInterval())
      .isEqualTo(ClosedInterval.withMinAndMaxAndDecimalPlaceCount(-1.0, 1.0, 30));
    expect(result.getImaginaryComponentInterval())
      .isEqualTo(ClosedInterval.withMinAndMaxAndDecimalPlaceCount(0.5, 2.5, 30));
    expect(result.getMaxIterationCount()).isEqualTo(150);
    expect(result.getDecimalPlaces()).isEqualTo(30);
  }
}
