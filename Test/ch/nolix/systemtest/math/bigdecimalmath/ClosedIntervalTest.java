/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.systemtest.math.bigdecimalmath;

import java.math.BigDecimal;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import ch.nolix.base.testing.standardtest.StandardTest;
import ch.nolix.baseapi.errorcontrol.invalidargumentexception.ArgumentIsNullException;
import ch.nolix.system.math.bigdecimalmath.ClosedInterval;

/**
 * @author Silvan Wyss
 */
final class ClosedIntervalTest extends StandardTest {
  @Test
  void testCase_containsValue_whenNullIsGiven() {
    // setup
    final var testUnit = ClosedInterval.withMinAndMax(0.0, 1.0);

    // execute
    final var result = testUnit.containsValue(null);

    // verify
    expect(result).isFalse();
  }

  @ParameterizedTest
  @CsvSource({
  "0.0, 1.0, 0.0", //value = min
  "0.0, 1.0, 1.0", //value = max
  "0.0, 1.0, 0.5", //value = mid point
  })
  void testCase_containsValue_whenContainsTheGivenValue(final double min, final double max, final double value) {
    // setup
    final var testUnit = ClosedInterval.withMinAndMax(min, max);
    final var valueAsBigDecimal = BigDecimal.valueOf(value);

    // execute
    final var result = testUnit.containsValue(valueAsBigDecimal);

    // verify
    expect(result).isTrue();
  }

  @ParameterizedTest
  @CsvSource({
  "0.0, 1.0, -1.0", // The given value is smaller than min.
  "0.0, 1.0, 2.0", // The given value is bigger than max.
  })
  void testCase_containsValue_whenDoesNotContainTheGivenValue(final double min, final double max, final double value) {
    // setup
    final var testUnit = ClosedInterval.withMinAndMax(min, max);
    final var valueAsBigDecimal = BigDecimal.valueOf(value);

    // execute
    final var result = testUnit.containsValue(valueAsBigDecimal);

    // verify
    expect(result).isFalse();
  }

  @Test
  void testCase_withMinAndMax_whenTheGivenMinIsNull() {
    // execute & verify
    expectRunning(() -> ClosedInterval.withMinAndMax(null, BigDecimal.valueOf(1.0)))
      .throwsException()
      .ofType(ArgumentIsNullException.class)
      .withMessage("The given minimum is null.");
  }

  @Test
  void testCase_withMinAndMax_whenTheGivenMaxIsNull() {
    // execute & verify
    expectRunning(() -> ClosedInterval.withMinAndMax(BigDecimal.valueOf(1.0), null))
      .throwsException()
      .ofType(ArgumentIsNullException.class)
      .withMessage("The given maximum is null.");
  }

  @Test
  void testCase_equals_whenNullIsGiven() {
    // setup
    final ClosedInterval nullCloedInterval = null;
    final var testUnit = ClosedInterval.withMinAndMax(0.0, 1.0);

    // execute
    final var result = testUnit.equals(nullCloedInterval);

    // verify
    expect(result).isFalse();
  }

  @Test
  void testCase_equals_whenUnequalClosedIntervalIsGiven() {
    // setup
    final var testUnit = ClosedInterval.withMinAndMax(0.0, 1.0);

    // execute
    final var result = testUnit.equals(ClosedInterval.withMinAndMax(-1.0, 0.0));

    // verify
    expect(result).isFalse();
  }

  @Test
  void testCase_equals_whenEqualClosedIntervalIsGiven() {
    // setup
    final var testUnit = ClosedInterval.withMinAndMax(0.0, 1.0);

    // execute
    final var result = testUnit.equals(ClosedInterval.withMinAndMax(0.0, 1.0));

    // verify
    expect(result).isTrue();
  }

  @Test
  void testCase_getHalfs_1A() {
    // define test parameters
    final var scale = 20;

    // setup
    final var testUnit = ClosedInterval.withMinAndMaxAndDecimalPlaceCount(-1.0, 1.0, scale);

    // execute
    final var result = testUnit.getHalfs();

    // verify
    expect(result.getStoredElement1().getMin()).isEqualTo(BigDecimal.valueOf(-1.0).setScale(scale));
    expect(result.getStoredElement1().getMax()).isEqualTo(BigDecimal.valueOf(0.0).setScale(scale));
    expect(result.getStoredElement2().getMin()).isEqualTo(BigDecimal.valueOf(0.0).setScale(scale));
    expect(result.getStoredElement2().getMax()).isEqualTo(BigDecimal.valueOf(1.0).setScale(scale));
  }

  @Test
  void testCase_getHalfs_1B() {
    // define test parameters
    final var scale = 20;

    // setup
    final var testUnit = ClosedInterval.withMinAndMaxAndDecimalPlaceCount(0.0, 1.0, scale);

    // execute
    final var result = testUnit.getHalfs();

    // verify
    expect(result.getStoredElement1().getMin()).isEqualTo(BigDecimal.valueOf(0.0).setScale(scale));
    expect(result.getStoredElement1().getMax()).isEqualTo(BigDecimal.valueOf(0.5).setScale(scale));
    expect(result.getStoredElement2().getMin()).isEqualTo(BigDecimal.valueOf(0.5).setScale(scale));
    expect(result.getStoredElement2().getMax()).isEqualTo(BigDecimal.valueOf(1.0).setScale(scale));
  }

  @Test
  void testCase_getHalfs_whenHasLength0() {
    // define test parameters
    final var scale = 20;

    // setup
    final var testUnit = ClosedInterval.withMinAndMaxAndDecimalPlaceCount(0.0, 0.0, scale);

    // execute
    final var result = testUnit.getHalfs();

    // verify
    expect(result.getStoredElement1().getMin()).isEqualTo(BigDecimal.valueOf(0.0).setScale(scale));
    expect(result.getStoredElement1().getMax()).isEqualTo(BigDecimal.valueOf(0.0).setScale(scale));
    expect(result.getStoredElement2().getMin()).isEqualTo(BigDecimal.valueOf(0.0).setScale(scale));
    expect(result.getStoredElement2().getMax()).isEqualTo(BigDecimal.valueOf(0.0).setScale(scale));
  }

  @Test
  void testCase_getLength_1A() {
    // define test parameters
    final var scale = 20;

    // setup
    final var testUnit = ClosedInterval.withMinAndMaxAndDecimalPlaceCount(-1.0, -1.0, scale);

    // execute
    final var result = testUnit.getLength();

    // verify
    expect(result).isEqualTo(BigDecimal.valueOf(0.0).setScale(scale));
  }

  @Test
  void testCase_getLength_1B() {
    // define test parameters
    final var scale = 20;

    // setup
    final var testUnit = ClosedInterval.withMinAndMaxAndDecimalPlaceCount(-1.0, 0.0, scale);

    // execute
    final var result = testUnit.getLength();

    // verify
    expect(result).isEqualTo(BigDecimal.valueOf(1.0).setScale(scale));
  }

  @Test
  void testCase_getLength_1C() {
    // define test parameters
    final var scale = 20;

    // setup
    final var testUnit = ClosedInterval.withMinAndMaxAndDecimalPlaceCount(-1.0, 1.0, scale);

    // execute
    final var result = testUnit.getLength();

    // verify
    expect(result).isEqualTo(BigDecimal.valueOf(2.0).setScale(scale));
  }

  @Test
  void testCase_getLength_whenHasLength0() {
    // define test parameters
    final var scale = 20;

    // setup
    final var testUnit = ClosedInterval.withMinAndMaxAndDecimalPlaceCount(0.0, 0.0, scale);

    // execute
    final var result = testUnit.getLength();

    // verify
    expect(result).isEqualTo(BigDecimal.valueOf(0.0).setScale(scale));
  }

  @Test
  void testCase_getMidpoint_1A() {
    // define test parameters
    final var scale = 20;

    // setup
    final var testUnit = ClosedInterval.withMinAndMaxAndDecimalPlaceCount(-1.0, 1.0, scale);

    // execute
    final var result = testUnit.getMidPoint();

    // verify
    expect(result).isEqualTo(BigDecimal.valueOf(0.0).setScale(scale));
  }

  @Test
  void testCase_getMidpoint_1B() {
    // define test parameters
    final var scale = 20;

    // setup
    final var testUnit = ClosedInterval.withMinAndMaxAndDecimalPlaceCount(0.0, 1.0, scale);

    // execute
    final var result = testUnit.getMidPoint();

    // verify
    expect(result).isEqualTo(BigDecimal.valueOf(0.5).setScale(scale));
  }

  @Test
  void testCase_getMidpoint_whenHasLength0() {
    // define test parameters
    final var scale = 20;

    // setup
    final var testUnit = ClosedInterval.withMinAndMaxAndDecimalPlaceCount(0.0, 0.0, scale);

    // execute
    final var result = testUnit.getMidPoint();

    // verify
    expect(result).isEqualTo(BigDecimal.valueOf(0.0).setScale(scale));
  }

  @ParameterizedTest
  @CsvSource({
  "-2.0, -1.0, false", //When the given ClosedInterval is before.
  "2.0, 3.0, false", //When the given ClosedInterval is after.
  "0.0, 1.0, true", //When the given ClosedInterval equals.
  "-1.0, 2.0, true", //When the given ClosedInterval encloses.
  "0.25, 0.75, true", //When the given ClosedInterval is enclosed.
  "-1.0, 0.25, true", //When the given ClosedInterval only intersects with the min.
  "0.75, 2.0, true", //When the given ClosedInterval only intersects with the max.
  })
  void testCase_intersectsWith(final double min, final double max, final boolean expectedResult) {
    // setup
    final var closedInterval = ClosedInterval.withMinAndMax(0.0, 1.0);
    final var testUnit = ClosedInterval.withMinAndMax(min, max);

    // execute
    final var result = testUnit.intersectsWithClosedInterval(closedInterval);

    // verify
    expect(result).is(expectedResult);
  }

  @Test
  void testCase_toString() {
    // setup
    final var testUnit = ClosedInterval.withMinAndMaxAndDecimalPlaceCount(-1.0, 1.0, 5);

    // execute
    final var result = testUnit.toString();

    // verify
    expect(result).isEqualTo("[-1.00000, 1.00000]");
  }
}
