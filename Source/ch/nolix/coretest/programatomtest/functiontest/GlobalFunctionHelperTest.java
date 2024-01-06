//package declaration
package ch.nolix.coretest.programatomtest.functiontest;

//own imports
import ch.nolix.core.errorcontrol.invalidargumentexception.ArgumentIsNullException;
import ch.nolix.core.programatom.function.FunctionCatalogue;
import ch.nolix.core.programatom.function.GlobalFunctionHelper;
import ch.nolix.core.testing.test.Test;
import ch.nolix.coreapi.testingapi.testapi.TestCase;

//class
public final class GlobalFunctionHelperTest extends Test {

  //method
  @TestCase
  public void testCase_createNegatorForBooleanSupplier_whenTrueSupplierIsGiven() {

    //execution
    final var result = GlobalFunctionHelper.createNegatorForBooleanSupplier(FunctionCatalogue::getTrue);

    //verification
    expectNot(result.getAsBoolean());
  }

  //method
  @TestCase
  public void testCase_createNegatorForBooleanSupplier_whenFalseSupplierIsGiven() {

    //execution
    final var result = GlobalFunctionHelper.createNegatorForBooleanSupplier(FunctionCatalogue::getFalse);

    //verification
    expect(result.getAsBoolean());
  }

  //method
  @TestCase
  public void testCase_createNegatorForBooleanSupplier_whenTheGivenBooleanSupplierIsNull() {

    //execution & verification
    expectRunning(() -> GlobalFunctionHelper.createNegatorForBooleanSupplier(null))
      .throwsException()
      .ofType(ArgumentIsNullException.class)
      .withMessage("The given BooleanSupplier is null.");
  }
}
