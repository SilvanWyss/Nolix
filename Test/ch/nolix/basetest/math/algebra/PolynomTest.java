/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.basetest.math.algebra;

import org.junit.jupiter.api.Test;

import ch.nolix.base.math.algebra.Polynom;
import ch.nolix.base.testing.standardtest.StandardTest;

/**
 * @author Silvan Wyss
 */
final class PolynomTest extends StandardTest {
  @Test
  void testCase_equals_whenTheGivenObjectIsNull() {
    // setup
    final var testUnit = Polynom.withCoefficients(1.0, 2.0, 3.0, 4.0, 5.0, 6.0);

    // execute
    final var result = testUnit.equals(null);

    // verify
    expect(result).isFalse();
  }

  @Test
  void testCase_equals_whenTheGivenObjectDoesNotEqual_1A() {
    // setup
    final var testUnit = Polynom.withCoefficients(1.0, 2.0, 3.0, 4.0, 5.0, 6.0);
    final var object = Polynom.withCoefficients(1.0, 2.0, 3.0, 4.0, 5.0);

    // execute
    final var result = testUnit.equals(object);

    // verify
    expect(result).isFalse();
  }

  @Test
  void testCase_equals_whenTheGivenObjectDoesNotEqual_1B() {
    // setup
    final var testUnit = Polynom.withCoefficients(1.0, 2.0, 3.0, 4.0, 5.0, 6.0);
    final var object = Polynom.withCoefficients(1.0, 2.0, 3.0, 4.0, 5.0, 7.0);

    // execute
    final var result = testUnit.equals(object);

    // verify
    expect(result).isFalse();
  }

  @Test
  void testCase_equals_whenTheGivenObjectEquals() {
    // setup
    final var testUnit = Polynom.withCoefficients(1.0, 2.0, 3.0, 4.0, 5.0, 6.0);
    final var object = Polynom.withCoefficients(1.0, 2.0, 3.0, 4.0, 5.0, 6.0);

    // execute
    final var result = testUnit.equals(object);

    // verify
    expect(result).isTrue();
  }

  @Test
  void testCase_getDerived_1A() {
    // setup
    final var testUnit = Polynom.withCoefficients(3.0, 0.0, 0.0);

    // setup verification
    expect(testUnit).hasStringRepresentation("x->3.0x^2");

    // execute
    final var result = testUnit.getDerived();

    // verify
    expect(result).hasStringRepresentation("x->6.0x");
  }

  @Test
  void testCase_getDerived_1B() {
    // setup
    final var testUnit = Polynom.withCoefficients(3.0, 3.0, 3.0);

    // setup verification
    expect(testUnit).hasStringRepresentation("x->3.0x^2+3.0x+3.0");

    // execute
    final var result = testUnit.getDerived();

    // verify
    expect(result).hasStringRepresentation("x->6.0x+3.0");
  }

  @Test
  void testCase_getDerived_1C() {
    // setup
    final var testUnit = Polynom.withCoefficients(3.0, 2.0, 1.0);

    // setup verification
    expect(testUnit).hasStringRepresentation("x->3.0x^2+2.0x+1.0");

    // execute
    final var result = testUnit.getDerived();

    // verify
    expect(result).hasStringRepresentation("x->6.0x+2.0");
  }

  @Test
  void testCase_getIntegrated_1A() {
    // setup
    final var testUnit = Polynom.withCoefficients(3.0, 0.0, 0.0);

    // setup verification
    expect(testUnit).hasStringRepresentation("x->3.0x^2");

    // execute
    final var result = testUnit.getIntegrated();

    // verify
    expect(result).hasStringRepresentation("x->x^3");
  }

  @Test
  void testCase_getIntegrated_1B() {
    // setup
    final var testUnit = Polynom.withCoefficients(3.0, 2.0, 1.0);

    // setup verification
    expect(testUnit).hasStringRepresentation("x->3.0x^2+2.0x+1.0");

    // execute
    final var result = testUnit.getIntegrated();

    // verify
    expect(result).hasStringRepresentation("x->x^3+x^2+1.0x");
  }

  @Test
  void testCase_toString_whenIsEmpty() {
    // setup
    final var testUnit = Polynom.EMPTY_POLYNOM;

    // execute
    final var result = testUnit.toString();

    // verify
    expect(result).isEqualTo("x->0.0");
  }

  @Test
  void testCase_toString_whenThereIsGiven1Coefficient() {
    // setup
    final var testUnit = Polynom.withCoefficients(1.0);

    // execute
    final var result = testUnit.toString();

    // execute
    expect(result).isEqualTo("x->1.0");
  }

  @Test
  void testCase_toString_whenThereAreGiven2Coefficients() {
    // setup
    final var testUnit = Polynom.withCoefficients(2.0, 1.0);

    // execute
    final var result = testUnit.toString();

    // execute
    expect(result).isEqualTo("x->2.0x+1.0");
  }

  @Test
  void testCase_toString_whenThereAreGiven3Coefficients() {
    // setup
    final var testUnit = Polynom.withCoefficients(3.0, 2.0, 1.0);

    // execute
    final var result = testUnit.toString();

    // execute
    expect(result).isEqualTo("x->3.0x^2+2.0x+1.0");
  }

  @Test
  void testCase_withCoefficient_whenThereIsGiven1Coefficient() {
    // execute
    final var result = Polynom.withCoefficients(1.0);

    // verify
    expect(result.getDegree()).isEqualTo(0);
    expect(result.getCoefficientForDegree(0)).isEqualTo(1.0);
  }

  @Test
  void testCase_withCoefficient_whenThereAreGiven2Coefficients() {
    // execute
    final var result = Polynom.withCoefficients(2.0, 1.0);

    // verify
    expect(result.getDegree()).isEqualTo(1);
    expect(result.getCoefficientForDegree(0)).isEqualTo(1.0);
    expect(result.getCoefficientForDegree(1)).isEqualTo(2.0);
  }

  @Test
  void testCase_withCoefficient_whenThereAreGiven3Coefficients() {
    // execute
    final var result = Polynom.withCoefficients(3.0, 2.0, 1.0);

    // verify
    expect(result.getDegree()).isEqualTo(2);
    expect(result.getCoefficientForDegree(0)).isEqualTo(1.0);
    expect(result.getCoefficientForDegree(1)).isEqualTo(2.0);
    expect(result.getCoefficientForDegree(2)).isEqualTo(3.0);
  }
}
