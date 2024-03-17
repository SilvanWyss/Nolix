//package declaration
package ch.nolix.coretest.programatomtest.functiontest;

import org.junit.jupiter.api.Test;

//own imports
import ch.nolix.core.errorcontrol.invalidargumentexception.ArgumentIsNullException;
import ch.nolix.core.programatom.function.FunctionCatalogue;
import ch.nolix.core.programatom.function.GlobalFunctionTool;
import ch.nolix.core.testing.test.StandardTest;

//class
public final class GlobalFunctionToolTest extends StandardTest {

  //method
  @Test
  public void testCase_createNegatorForBooleanSupplier_whenTrueSupplierIsGiven() {

    //execution
    final var result = GlobalFunctionTool.createNegatorForBooleanSupplier(FunctionCatalogue::getTrue);

    //verification
    expectNot(result.getAsBoolean());
  }

  //method
  @Test
  public void testCase_createNegatorForBooleanSupplier_whenFalseSupplierIsGiven() {

    //execution
    final var result = GlobalFunctionTool.createNegatorForBooleanSupplier(FunctionCatalogue::getFalse);

    //verification
    expect(result.getAsBoolean());
  }

  //method
  @Test
  public void testCase_createNegatorForBooleanSupplier_whenTheGivenBooleanSupplierIsNull() {

    //execution & verification
    expectRunning(() -> GlobalFunctionTool.createNegatorForBooleanSupplier(null))
      .throwsException()
      .ofType(ArgumentIsNullException.class)
      .withMessage("The given BooleanSupplier is null.");
  }
}
