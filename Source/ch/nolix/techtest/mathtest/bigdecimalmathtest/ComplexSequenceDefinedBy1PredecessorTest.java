//package declaration
package ch.nolix.techtest.mathtest.bigdecimalmathtest;

import ch.nolix.core.testing.basetest.TestCase;
import ch.nolix.core.testing.test.Test;
import ch.nolix.tech.math.bigdecimalmath.ComplexNumber;
import ch.nolix.tech.math.bigdecimalmath.ComplexSequenceDefinedBy1Predecessor;

//class
public final class ComplexSequenceDefinedBy1PredecessorTest extends Test {

  //method
  @TestCase
  public void test_getValueAt1BasedIndex() {

    //setup
    final var testUnit = new ComplexSequenceDefinedBy1Predecessor(
        new ComplexNumber(0.0, 0.0, 20),
        p -> p.getPower2().getSum(new ComplexNumber(1.0, 0.0)));

    //execution & verification
    expect(testUnit.getValueAt1BasedIndex(1)).isEqualTo(new ComplexNumber(0.0, 0.0, 20));
    expect(testUnit.getValueAt1BasedIndex(2)).isEqualTo(new ComplexNumber(1.0, 0.0, 20));
    expect(testUnit.getValueAt1BasedIndex(3)).isEqualTo(new ComplexNumber(2.0, 0.0, 20));
    expect(testUnit.getValueAt1BasedIndex(4)).isEqualTo(new ComplexNumber(5.0, 0.0, 20));
  }
}
