package ch.nolix.systemtest.webguitest.atomiccontroltest;

import org.junit.jupiter.api.Test;

import ch.nolix.core.errorcontrol.exception.GeneralException;
import ch.nolix.core.programatom.function.GlobalFunctionService;
import ch.nolix.system.webgui.atomiccontrol.button.Button;
import ch.nolix.system.webgui.atomiccontrol.validationlabel.ValidationLabel;
import ch.nolix.system.webgui.linearcontainer.VerticalStack;
import ch.nolix.systemapi.webguiapi.atomiccontrolapi.validationlabelapi.IValidationLabel;
import ch.nolix.systemtest.webguitest.maintest.ControlTest;

final class ValidationLabelTest extends ControlTest<IValidationLabel> {

  @Test
  void testCase_letsClear() {

    //setup part 1: Creates a ValidationLabel and runs an action that produces an
    //error.
    final var testUnit = new ValidationLabel();
    final var actionButton = new Button().setLeftMouseButtonPressAction(GlobalFunctionService::throwException);
    new VerticalStack().addControl(testUnit, actionButton);
    actionButton.pressLeftMouseButton();

    //setup part 2: Prepares an action that does not produce an error.
    actionButton.setLeftMouseButtonPressAction(GlobalFunctionService::doNothing);

    //execution: Runs the action that does not produce an error.
    actionButton.pressLeftMouseButton();

    //verification
    expect(testUnit.isEmpty()).isTrue();
  }

  @Test
  void testCase_letsShowError() {

    //setup
    final var testUnit = new ValidationLabel();
    final var actionButton = new Button().setLeftMouseButtonPressAction(GlobalFunctionService::throwException);
    new VerticalStack().addControl(testUnit, actionButton);

    //setup verification
    expect(testUnit.isEmpty()).isTrue();

    //execution
    actionButton.pressLeftMouseButton();

    //verification
    expect(testUnit.containsAny()).isTrue();
    expect(testUnit.getError()).isEqualTo(getExceptionOfFunctionsCatalogThrowExceptionMethod());
  }

  @Override
  protected IValidationLabel createTestUnit() {
    return new ValidationLabel();
  }

  private Exception getExceptionOfFunctionsCatalogThrowExceptionMethod() {

    try {
      GlobalFunctionService.throwException();
    } catch (final GeneralException exception) {
      return exception;
    }

    throw GeneralException
      .withErrorMessage("The throwException method of the FunctionCatalog did not throw an Exception.");
  }
}
