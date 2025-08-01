package ch.nolix.techtest.math.bigdecimalmath;

import org.junit.jupiter.api.Test;

import ch.nolix.core.testing.standardtest.StandardTest;
import ch.nolix.tech.math.bigdecimalmath.ComplexNumber;
import ch.nolix.tech.math.bigdecimalmath.ComplexSequenceDefinedBy2Predecessor;
import ch.nolix.techapi.math.bigdecimalmath.IComplexNumber;

final class ComplexSequenceDefinedBy2PredecessorTest extends StandardTest {

  @Test
  void test_getValueAtOneBasedIndex() {

    //setup
    final var testUnit = new ComplexSequenceDefinedBy2Predecessor(
      new ComplexNumber(1.0, 0.0, 20),
      new ComplexNumber(0.0, 2.0, 20),
      IComplexNumber::getSum);

    //execution & verification
    expect(testUnit.getValueAtOneBasedIndex(1)).isEqualTo(new ComplexNumber(1.0, 0.0, 20));
    expect(testUnit.getValueAtOneBasedIndex(2)).isEqualTo(new ComplexNumber(0.0, 2.0, 20));
    expect(testUnit.getValueAtOneBasedIndex(3)).isEqualTo(new ComplexNumber(1.0, 2.0, 20));
    expect(testUnit.getValueAtOneBasedIndex(4)).isEqualTo(new ComplexNumber(1.0, 4.0, 20));
    expect(testUnit.getValueAtOneBasedIndex(5)).isEqualTo(new ComplexNumber(2.0, 6.0, 20));
    expect(testUnit.getValueAtOneBasedIndex(6)).isEqualTo(new ComplexNumber(3.0, 10.0, 20));
    expect(testUnit.getValueAtOneBasedIndex(7)).isEqualTo(new ComplexNumber(5.0, 16.0, 20));
    expect(testUnit.getValueAtOneBasedIndex(8)).isEqualTo(new ComplexNumber(8.0, 26.0, 20));
  }
}
