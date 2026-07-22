/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.techtest.math.bigdecimalmath;

import org.junit.jupiter.api.Test;

import ch.nolix.base.testing.standardtest.StandardTest;
import ch.nolix.tech.math.bigdecimalmath.ComplexNumber;
import ch.nolix.tech.math.bigdecimalmath.ComplexSequenceDefinedBy2Predecessor;
import ch.nolix.techapi.math.bigdecimalmath.IComplexNumber;

/**
 * @author Silvan Wyss
 */
final class ComplexSequenceDefinedBy2PredecessorTest extends StandardTest {
  @Test
  void test_getValueAtOneBasedIndex() {
    // setup
    final var testUnit = //
    ComplexSequenceDefinedBy2Predecessor.withFirstValueAndSecondValueAndNextValueSupplier(
      ComplexNumber.withRealComponentAndImaginaryComponentAndDecimalPlaceCount(1.0, 0.0, 20),
      ComplexNumber.withRealComponentAndImaginaryComponentAndDecimalPlaceCount(0.0, 2.0, 20),
      IComplexNumber::getSum);

   // execute & verification
    expect(testUnit.getValueAtOneBasedIndex(1))
      .isEqualTo(ComplexNumber.withRealComponentAndImaginaryComponentAndDecimalPlaceCount(1.0, 0.0, 20));
    expect(testUnit.getValueAtOneBasedIndex(2))
      .isEqualTo(ComplexNumber.withRealComponentAndImaginaryComponentAndDecimalPlaceCount(0.0, 2.0, 20));
    expect(testUnit.getValueAtOneBasedIndex(3))
      .isEqualTo(ComplexNumber.withRealComponentAndImaginaryComponentAndDecimalPlaceCount(1.0, 2.0, 20));
    expect(testUnit.getValueAtOneBasedIndex(4))
      .isEqualTo(ComplexNumber.withRealComponentAndImaginaryComponentAndDecimalPlaceCount(1.0, 4.0, 20));
    expect(testUnit.getValueAtOneBasedIndex(5))
      .isEqualTo(ComplexNumber.withRealComponentAndImaginaryComponentAndDecimalPlaceCount(2.0, 6.0, 20));
    expect(testUnit.getValueAtOneBasedIndex(6))
      .isEqualTo(ComplexNumber.withRealComponentAndImaginaryComponentAndDecimalPlaceCount(3.0, 10.0, 20));
    expect(testUnit.getValueAtOneBasedIndex(7))
      .isEqualTo(ComplexNumber.withRealComponentAndImaginaryComponentAndDecimalPlaceCount(5.0, 16.0, 20));
    expect(testUnit.getValueAtOneBasedIndex(8))
      .isEqualTo(ComplexNumber.withRealComponentAndImaginaryComponentAndDecimalPlaceCount(8.0, 26.0, 20));
  }
}
