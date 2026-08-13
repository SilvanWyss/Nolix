/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.templatetest.math.sequence;

import org.junit.jupiter.api.Test;

import ch.nolix.base.testing.standardtest.StandardTest;
import ch.nolix.system.math.bigdecimalmath.ComplexNumber;
import ch.nolix.template.math.sequence.SequenceFactory;

/**
 * @author Silvan Wyss
 */
final class SequenceCreatorTest extends StandardTest {
  @Test
  void testCase_createMandelbrotSequenceForIncrement_whenIncrementIs0plus0i() {
    // execute
    final var result = SequenceFactory
      .createMandelbrotSequenceForIncrement(ComplexNumber.withRealComponentAndImaginaryComponent(0.0, 0.0));

    // verify
    expect(result.getValueAtOneBasedIndex(1)).isEqualTo(ComplexNumber.withRealComponentAndImaginaryComponent(0.0, 0.0));
    expect(result.getValueAtOneBasedIndex(2)).isEqualTo(ComplexNumber.withRealComponentAndImaginaryComponent(0.0, 0.0));
    expect(result.getValueAtOneBasedIndex(3)).isEqualTo(ComplexNumber.withRealComponentAndImaginaryComponent(0.0, 0.0));
    expect(result.getValueAtOneBasedIndex(4)).isEqualTo(ComplexNumber.withRealComponentAndImaginaryComponent(0.0, 0.0));
    expect(result.getValueAtOneBasedIndex(5)).isEqualTo(ComplexNumber.withRealComponentAndImaginaryComponent(0.0, 0.0));
  }

  @Test
  void testCase_createMandelbrotSequenceForIncrement_whenIncrementIs1plus0i() {
    // execute
    final var result = SequenceFactory
      .createMandelbrotSequenceForIncrement(ComplexNumber.withRealComponentAndImaginaryComponent(1.0, 0.0));

    // verify
    expect(result.getValueAtOneBasedIndex(1)).isEqualTo(ComplexNumber.withRealComponentAndImaginaryComponent(0.0, 0.0));
    expect(result.getValueAtOneBasedIndex(2)).isEqualTo(ComplexNumber.withRealComponentAndImaginaryComponent(1.0, 0.0));
    expect(result.getValueAtOneBasedIndex(3)).isEqualTo(ComplexNumber.withRealComponentAndImaginaryComponent(2.0, 0.0));
    expect(result.getValueAtOneBasedIndex(4)).isEqualTo(ComplexNumber.withRealComponentAndImaginaryComponent(5.0, 0.0));
    expect(result.getValueAtOneBasedIndex(5))
      .isEqualTo(ComplexNumber.withRealComponentAndImaginaryComponent(26.0, 0.0));
  }

  @Test
  void testCase_createMandelbrotSequenceForIncrement_whenIncrementIs1plus1i() {
    // execute
    final var result = SequenceFactory
      .createMandelbrotSequenceForIncrement(ComplexNumber.withRealComponentAndImaginaryComponent(1.0, 1.0));

    // verify
    expect(result.getValueAtOneBasedIndex(1)).isEqualTo(ComplexNumber.withRealComponentAndImaginaryComponent(0.0, 0.0));
    expect(result.getValueAtOneBasedIndex(2)).isEqualTo(ComplexNumber.withRealComponentAndImaginaryComponent(1.0, 1.0));
    expect(result.getValueAtOneBasedIndex(3)).isEqualTo(ComplexNumber.withRealComponentAndImaginaryComponent(1.0, 3.0));
    expect(result.getValueAtOneBasedIndex(4))
      .isEqualTo(ComplexNumber.withRealComponentAndImaginaryComponent(-7.0, 7.0));
    expect(result.getValueAtOneBasedIndex(5))
      .isEqualTo(ComplexNumber.withRealComponentAndImaginaryComponent(1.0, -97.0));
  }
}
