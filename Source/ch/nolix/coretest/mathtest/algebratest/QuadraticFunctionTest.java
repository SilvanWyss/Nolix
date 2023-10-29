//package declaration
package ch.nolix.coretest.mathtest.algebratest;

//own imports
import ch.nolix.core.errorcontrol.invalidargumentexception.InvalidArgumentException;
import ch.nolix.core.math.algebra.QuadraticFunction;
import ch.nolix.core.testing.basetest.TestCase;
import ch.nolix.core.testing.test.Test;

//class
public final class QuadraticFunctionTest extends Test {

  //method
  @TestCase
  public void testCase_constructor() {

    //execution & verification
    expectRunning(() -> new QuadraticFunction(0.0, 0.0, 0.0))
      .throwsException()
      .ofType(InvalidArgumentException.class);
  }

  //method
  @TestCase
  public void testCase_hasMin() {

    //setup
    final var quadraticFunction = new QuadraticFunction(1.0, 0.0, 0.0);

    //execution & verification
    expect(quadraticFunction.hasMin());
  }

  //method
  @TestCase
  public void testCase_hasMin_2() {

    //setup
    final var quadraticFunction = new QuadraticFunction(-1.0, 0.0, 0.0);

    //execution & verification
    expectNot(quadraticFunction.hasMin());
  }
}
