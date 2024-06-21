//package declaration
package ch.nolix.coretest.mathtest.maintest;

//JUnit imports
import org.junit.jupiter.api.Test;

//own imports
import ch.nolix.core.math.main.GlobalCalculator;
import ch.nolix.core.testing.standardtest.StandardTest;

//class
final class GlobalCalculatorTest extends StandardTest {

  //method
  @Test
  void testCase_getMax() {

    //execution
    final var min = GlobalCalculator.getMax(-2.0, -1.0, 0.0, 1.0, 2.0);

    //validation
    expect(min).isEqualTo(2.0);
  }

  //method
  @Test
  void testCase_getMin() {

    //execution
    final var min = GlobalCalculator.getMin(-2.0, -1.0, 0.0, 1.0, 2.0);

    //validation
    expect(min).isEqualTo(-2.0);
  }
}
