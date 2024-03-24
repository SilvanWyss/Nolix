//package declaration
package ch.nolix.systemtest.webguitest.atomiccontroltest;

//JUnit imports
import org.junit.jupiter.api.Test;

//own imports
import ch.nolix.core.errorcontrol.exception.GeneralException;
import ch.nolix.core.programatom.function.FunctionCatalogue;
import ch.nolix.system.webgui.atomiccontrol.Button;
import ch.nolix.system.webgui.atomiccontrol.ValidationLabel;
import ch.nolix.system.webgui.linearcontainer.VerticalStack;
import ch.nolix.systemapi.webguiapi.atomiccontrolapi.IValidationLabel;
import ch.nolix.systemtest.webguitest.maintest.ControlTest;

//class
final class ValidationLabelTest extends ControlTest<IValidationLabel> {

  //method
  @Test
  void testCase_letsClear() {

    //setup part 1: Creates a ValidationLabel and runs an action that produces an
    //error.
    final var testUnit = new ValidationLabel();
    final var actionButton = new Button().setLeftMouseButtonPressAction(FunctionCatalogue::throwException);
    new VerticalStack().addControl(testUnit, actionButton);
    actionButton.pressLeftMouseButton();

    //setup part 2: Prepares an action that does not produce an error.
    actionButton.setLeftMouseButtonPressAction(FunctionCatalogue::doNothing);

    //execution: Runs the action that does not produce an error.
    actionButton.pressLeftMouseButton();

    //verification
    expect(testUnit.isEmpty());
  }

  //method
  @Test
  void testCase_letsShowError() {

    //setup
    final var testUnit = new ValidationLabel();
    final var actionButton = new Button().setLeftMouseButtonPressAction(FunctionCatalogue::throwException);
    new VerticalStack().addControl(testUnit, actionButton);

    //setup verification
    expect(testUnit.isEmpty());

    //execution
    actionButton.pressLeftMouseButton();

    //verification
    expect(testUnit.containsAny());
    expect(testUnit.getError()).isEqualTo(getExceptionOfFunctionsCatalogueThrowExceptionMethod());
  }

  //method
  @Override
  protected IValidationLabel createTestUnit() {
    return new ValidationLabel();
  }

  //method
  private Exception getExceptionOfFunctionsCatalogueThrowExceptionMethod() {

    try {
      FunctionCatalogue.throwException();
    } catch (final GeneralException exception) {
      return exception;
    }

    throw GeneralException
      .withErrorMessage("The throwException method of the FunctionCatalogue did not throw an Exception.");
  }
}
