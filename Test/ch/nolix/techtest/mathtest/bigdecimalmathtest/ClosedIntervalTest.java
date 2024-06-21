//package declaration
package ch.nolix.techtest.mathtest.bigdecimalmathtest;

//Java imports
import java.math.BigDecimal;

//JUnit imports
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

//own imports
import ch.nolix.core.errorcontrol.invalidargumentexception.ArgumentIsNullException;
import ch.nolix.core.testing.standardtest.StandardTest;
import ch.nolix.tech.math.bigdecimalmath.ClosedInterval;

//class
final class ClosedIntervalTest extends StandardTest {

  //method
  @Test
  void testCase_containsValue_whenNullIsGiven() {

    //setup
    final var testUnit = new ClosedInterval(0.0, 1.0);

    //execution
    final var result = testUnit.containsValue(null);

    //verification
    expectNot(result);
  }

  //method
  @ParameterizedTest
  @CsvSource({
  "0.0, 1.0, 0.0", //value = min
  "0.0, 1.0, 1.0", //value = max
  "0.0, 1.0, 0.5", //value = mid point
  })
  void testCase_containsValue_whenContainsTheGivenValue(final double min, final double max, final double value) {

    //setup
    final var testUnit = new ClosedInterval(min, max);
    final var valueAsBigDecimal = BigDecimal.valueOf(value);

    //execution
    final var result = testUnit.containsValue(valueAsBigDecimal);

    //verification
    expect(result);
  }

  //method
  @ParameterizedTest
  @CsvSource({
  "0.0, 1.0, -1.0", //The given value is smaller than min.
  "0.0, 1.0, 2.0", //The given value is bigger than max.
  })
  void testCase_containsValue_whenDoesNotContainTheGivenValue(final double min, final double max, final double value) {

    //setup
    final var testUnit = new ClosedInterval(min, max);
    final var valueAsBigDecimal = BigDecimal.valueOf(value);

    //execution
    final var result = testUnit.containsValue(valueAsBigDecimal);

    //verification
    expectNot(result);
  }

  //method
  @Test
  void testCase_constructor_whenTheGivenMinIsNull() {

    //execution & verification
    expectRunning(() -> new ClosedInterval(null, BigDecimal.valueOf(1.0)))
      .throwsException()
      .ofType(ArgumentIsNullException.class)
      .withMessage("The given minimum is null.");
  }

  //method
  @Test
  void testCase_constructor_whenTheGivenMaxIsNull() {

    //execution & verification
    expectRunning(() -> new ClosedInterval(BigDecimal.valueOf(1.0), null))
      .throwsException()
      .ofType(ArgumentIsNullException.class)
      .withMessage("The given maximum is null.");
  }

  //method
  @Test
  void testCase_equals_whenNullIsGiven() {

    //setup
    final ClosedInterval nullCloedInterval = null;
    final var testUnit = new ClosedInterval(0.0, 1.0);

    //execution
    final var result = testUnit.equals(nullCloedInterval);

    //verification
    expectNot(result);
  }

  //method
  @Test
  void testCase_equals_whenUnequalClosedIntervalIsGiven() {

    //setup
    final var testUnit = new ClosedInterval(0.0, 1.0);

    //execution
    final var result = testUnit.equals(new ClosedInterval(-1.0, 0.0));

    //verification
    expectNot(result);
  }

  //method
  @Test
  void testCase_equals_whenEqualClosedIntervalIsGiven() {

    //setup
    final var testUnit = new ClosedInterval(0.0, 1.0);

    //execution
    final var result = testUnit.equals(new ClosedInterval(0.0, 1.0));

    //verification
    expect(result);
  }

  //method
  @Test
  void testCase_getHalfs_1A() {

    //parameter definition
    final var scale = 20;

    //setup
    final var testUnit = new ClosedInterval(-1.0, 1.0, scale);

    //execution
    final var result = testUnit.getHalfs();

    //verification
    expect(result.getStoredElement1().getMin()).isEqualTo(BigDecimal.valueOf(-1.0).setScale(scale));
    expect(result.getStoredElement1().getMax()).isEqualTo(BigDecimal.valueOf(0.0).setScale(scale));
    expect(result.getStoredElement2().getMin()).isEqualTo(BigDecimal.valueOf(0.0).setScale(scale));
    expect(result.getStoredElement2().getMax()).isEqualTo(BigDecimal.valueOf(1.0).setScale(scale));
  }

  //method
  @Test
  void testCase_getHalfs_1B() {

    //parameter definition
    final var scale = 20;

    //setup
    final var testUnit = new ClosedInterval(0.0, 1.0, scale);

    //execution
    final var result = testUnit.getHalfs();

    //verification
    expect(result.getStoredElement1().getMin()).isEqualTo(BigDecimal.valueOf(0.0).setScale(scale));
    expect(result.getStoredElement1().getMax()).isEqualTo(BigDecimal.valueOf(0.5).setScale(scale));
    expect(result.getStoredElement2().getMin()).isEqualTo(BigDecimal.valueOf(0.5).setScale(scale));
    expect(result.getStoredElement2().getMax()).isEqualTo(BigDecimal.valueOf(1.0).setScale(scale));
  }

  //method
  @Test
  void testCase_getHalfs_whenHasLength0() {

    //parameter definition
    final var scale = 20;

    //setup
    final var testUnit = new ClosedInterval(0.0, 0.0, scale);

    //execution
    final var result = testUnit.getHalfs();

    //verification
    expect(result.getStoredElement1().getMin()).isEqualTo(BigDecimal.valueOf(0.0).setScale(scale));
    expect(result.getStoredElement1().getMax()).isEqualTo(BigDecimal.valueOf(0.0).setScale(scale));
    expect(result.getStoredElement2().getMin()).isEqualTo(BigDecimal.valueOf(0.0).setScale(scale));
    expect(result.getStoredElement2().getMax()).isEqualTo(BigDecimal.valueOf(0.0).setScale(scale));
  }

  //method
  @Test
  void testCase_getLength_1A() {

    //parameter definition
    final var scale = 20;

    //setup
    final var testUnit = new ClosedInterval(-1.0, -1.0, scale);

    //execution
    final var result = testUnit.getLength();

    //verification
    expect(result).isEqualTo(BigDecimal.valueOf(0.0).setScale(scale));
  }

  //method
  @Test
  void testCase_getLength_1B() {

    //parameter definition
    final var scale = 20;

    //setup
    final var testUnit = new ClosedInterval(-1.0, 0.0, scale);

    //execution
    final var result = testUnit.getLength();

    //verification
    expect(result).isEqualTo(BigDecimal.valueOf(1.0).setScale(scale));
  }

  //method
  @Test
  void testCase_getLength_1C() {

    //parameter definition
    final var scale = 20;

    //setup
    final var testUnit = new ClosedInterval(-1.0, 1.0, scale);

    //execution
    final var result = testUnit.getLength();

    //verification
    expect(result).isEqualTo(BigDecimal.valueOf(2.0).setScale(scale));
  }

  //method
  @Test
  void testCase_getLength_whenHasLength0() {

    //parameter definition
    final var scale = 20;

    //setup
    final var testUnit = new ClosedInterval(0.0, 0.0, scale);

    //execution
    final var result = testUnit.getLength();

    //verification
    expect(result).isEqualTo(BigDecimal.valueOf(0.0).setScale(scale));
  }

  //method
  @Test
  void testCase_getMidpoint_1A() {

    //parameter definition
    final var scale = 20;

    //setup
    final var testUnit = new ClosedInterval(-1.0, 1.0, scale);

    //execution
    final var result = testUnit.getMidPoint();

    //verification
    expect(result).isEqualTo(BigDecimal.valueOf(0.0).setScale(scale));
  }

  //method
  @Test
  void testCase_getMidpoint_1B() {

    //parameter definition
    final var scale = 20;

    //setup
    final var testUnit = new ClosedInterval(0.0, 1.0, scale);

    //execution
    final var result = testUnit.getMidPoint();

    //verification
    expect(result).isEqualTo(BigDecimal.valueOf(0.5).setScale(scale));
  }

  //method
  @Test
  void testCase_getMidpoint_whenHasLength0() {

    //parameter definition
    final var scale = 20;

    //setup
    final var testUnit = new ClosedInterval(0.0, 0.0, scale);

    //execution
    final var result = testUnit.getMidPoint();

    //verification
    expect(result).isEqualTo(BigDecimal.valueOf(0.0).setScale(scale));
  }

  //method
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

    //setup
    final var closedInterval = new ClosedInterval(0.0, 1.0);
    final var testUnit = new ClosedInterval(min, max);

    //execution
    final var result = testUnit.intersectsWith(closedInterval);

    //verification
    expect(result == expectedResult);
  }

  //method
  @Test
  void testCase_toString() {

    //setup
    final var testUnit = new ClosedInterval(-1.0, 1.0, 5);

    //execution
    final var result = testUnit.toString();

    //verification
    expect(result).isEqualTo("[-1.00000, 1.00000]");
  }
}
