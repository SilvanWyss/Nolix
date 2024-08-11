//package decl
package ch.nolix.coretest.mathtest.maintest;

//JUnit imports
import org.junit.jupiter.api.Test;

//own imports
import ch.nolix.core.math.basic.BasicCalculator;
import ch.nolix.core.testing.standardtest.StandardTest;

//class
final class BasicCalculatorTest extends StandardTest {

  //method
  @Test
  void testCase_getMax() {

    //setup
    final var testUnit = new BasicCalculator();

    //execution
    final var result = testUnit.getMax(-2.0, -1.0, 0.0, 1.0, 2.0);

    //validation
    expect(result).isEqualTo(2.0);
  }

  //method
  @Test
  void testCase_getMin() {

    //setup
    final var testUnit = new BasicCalculator();

    //execution
    final var result = testUnit.getMin(-2.0, -1.0, 0.0, 1.0, 2.0);

    //validation
    expect(result).isEqualTo(-2.0);
  }
}
