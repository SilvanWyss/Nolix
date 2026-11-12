/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.basetest.math.stochastic;

import org.junit.jupiter.api.Test;

import ch.nolix.base.math.stochastic.ArModel;
import ch.nolix.base.testing.standardtest.StandardTest;

/**
 * @author Silvan Wyss
 */
final class ArModelTest extends StandardTest {
  @Test
  void testCase_constructor_1() {
    // execute
    final double[] inputValues = { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10 };
    final var lArModel = ArModel.withOrderAndInputValues(1, inputValues);

    // verify
    expect(lArModel.getForecast(1)).withMaxDeviation(0.01).isEqualTo(11.0);
    expect(lArModel.getForecast(2)).withMaxDeviation(0.01).isEqualTo(12.0);
    expect(lArModel.getForecast(3)).withMaxDeviation(0.01).isEqualTo(13.0);
    expect(lArModel.getForecast(4)).withMaxDeviation(0.01).isEqualTo(14.0);
  }

  @Test
  void testCase_constructor_2() {
    // execute
    final double[] inputValues = { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10 };
    final var lArModel = ArModel.withOrderAndInputValues(2, inputValues);

    // verify
    expect(lArModel.getForecast(1)).withMaxDeviation(0.01).isEqualTo(11.0);
    expect(lArModel.getForecast(2)).withMaxDeviation(0.01).isEqualTo(12.0);
    expect(lArModel.getForecast(3)).withMaxDeviation(0.01).isEqualTo(13.0);
    expect(lArModel.getForecast(4)).withMaxDeviation(0.01).isEqualTo(14.0);
  }

  @Test
  void testCase_constructor_3() {
    // execute
    final double[] inputValues = { 1, 2, 1, 2, 1, 2, 1, 2, 1, 2 };
    final var lArModel = ArModel.withOrderAndInputValues(2, inputValues);

    // verify
    expect(lArModel.getForecast(1)).withMaxDeviation(0.01).isEqualTo(1.0);
    expect(lArModel.getForecast(2)).withMaxDeviation(0.01).isEqualTo(2.0);
    expect(lArModel.getForecast(3)).withMaxDeviation(0.01).isEqualTo(1.0);
    expect(lArModel.getForecast(4)).withMaxDeviation(0.01).isEqualTo(2.0);
  }

  @Test
  void testCase_constructor_4() {
    // execute
    final double[] inputValues = { 1, 2, 1, 2, 1, 2, 1, 2, 1, 2 };
    final var lArModel = ArModel.withOrderAndInputValues(3, inputValues);

    // verify
    expect(lArModel.getForecast(1)).withMaxDeviation(0.01).isEqualTo(1.0);
    expect(lArModel.getForecast(2)).withMaxDeviation(0.01).isEqualTo(2.0);
    expect(lArModel.getForecast(3)).withMaxDeviation(0.01).isEqualTo(1.0);
    expect(lArModel.getForecast(4)).withMaxDeviation(0.01).isEqualTo(2.0);
  }

  @Test
  void testCase_constructor_5() {
    // execute
    final double[] inputValues = { 1, 2, 3, 1, 2, 3, 1, 2, 3, 1, 2, 3, 1, 2, 3 };
    final var lArModel = ArModel.withOrderAndInputValues(3, inputValues);

    // verify
    expect(lArModel.getForecast(1)).withMaxDeviation(0.01).isEqualTo(1.0);
    expect(lArModel.getForecast(2)).withMaxDeviation(0.01).isEqualTo(2.0);
    expect(lArModel.getForecast(3)).withMaxDeviation(0.01).isEqualTo(3.0);
    expect(lArModel.getForecast(4)).withMaxDeviation(0.01).isEqualTo(1.0);
    expect(lArModel.getForecast(5)).withMaxDeviation(0.01).isEqualTo(2.0);
    expect(lArModel.getForecast(6)).withMaxDeviation(0.01).isEqualTo(3.0);
  }

  @Test
  void testCase_constructor_6() {
    // exection
    final double[] inputValues = { 1, 2, 3, 1, 2, 3, 1, 2, 3, 1, 2, 3, 1, 2, 3 };
    final var lArModel = ArModel.withOrderAndInputValues(4, inputValues);

    // verify
    expect(lArModel.getForecast(1)).withMaxDeviation(0.01).isEqualTo(1.0);
    expect(lArModel.getForecast(2)).withMaxDeviation(0.01).isEqualTo(2.0);
    expect(lArModel.getForecast(3)).withMaxDeviation(0.01).isEqualTo(3.0);
    expect(lArModel.getForecast(4)).withMaxDeviation(0.01).isEqualTo(1.0);
    expect(lArModel.getForecast(5)).withMaxDeviation(0.01).isEqualTo(2.0);
    expect(lArModel.getForecast(6)).withMaxDeviation(0.01).isEqualTo(3.0);
  }
}
