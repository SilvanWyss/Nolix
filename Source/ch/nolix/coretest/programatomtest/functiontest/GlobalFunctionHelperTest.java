//package declaration
package ch.nolix.coretest.programatomtest.functiontest;

//own imports
import ch.nolix.core.errorcontrol.invalidargumentexception.ArgumentIsNullException;
import ch.nolix.core.programatom.function.FunctionCatalogue;
import ch.nolix.core.programatom.function.GlobalFunctionHelper;
import ch.nolix.core.testing.basetest.TestCase;
import ch.nolix.core.testing.test.Test;

//class
public final class GlobalFunctionHelperTest extends Test {

  // method
  @TestCase
  public void testCase_createNegatorFor_whenTrueFunctionIsGiven() {

    // execution
    final var result = GlobalFunctionHelper.createNegatorFor(FunctionCatalogue::getTrue);

    // verification
    expectNot(result.getOutput());
  }

  // method
  @TestCase
  public void testCase_createNegatorFor_whenFalseFunctionIsGiven() {

    // execution
    final var result = GlobalFunctionHelper.createNegatorFor(FunctionCatalogue::getFalse);

    // verification
    expect(result.getOutput());
  }

  // method
  @TestCase
  public void testCase_createNegatorFor_whenNullIsGiven() {

    // execution & verification
    expectRunning(() -> GlobalFunctionHelper.createNegatorFor(null))
        .throwsException()
        .ofType(ArgumentIsNullException.class)
        .withMessage("The given condition is null.");
  }
}
