package ch.nolix.systemtest.webatomiccontrol.validationlabel;

import org.junit.jupiter.api.Test;

import ch.nolix.core.errorcontrol.generalexception.GeneralException;
import ch.nolix.core.misc.function.FunctionService;
import ch.nolix.system.webatomiccontrol.button.Button;
import ch.nolix.system.webatomiccontrol.validationlabel.ValidationLabel;
import ch.nolix.system.webgui.linearcontainer.VerticalStack;
import ch.nolix.systemapi.webatomiccontrol.validationlabel.IValidationLabel;
import ch.nolix.systemtest.webgui.main.ControlTest;

final class ValidationLabelTest extends ControlTest<IValidationLabel> {
  @Test
  void testCase_letsClear() {
    //setup part 1: Creates a ValidationLabel and runs an action that produces an
    //error.
    final var testUnit = new ValidationLabel();
    final var actionButton = new Button().setLeftMouseButtonPressAction(FunctionService::throwException);
    new VerticalStack().addControl(testUnit, actionButton);
    actionButton.pressLeftMouseButton();

    //setup part 2: Prepares an action that does not produce an error.
    actionButton.setLeftMouseButtonPressAction(FunctionService::doNothing);

    //execution: Runs the action that does not produce an error.
    actionButton.pressLeftMouseButton();

    //verification
    expect(testUnit.isEmpty()).isTrue();
  }

  @Test
  void testCase_letsShowError() {
    //setup
    final var testUnit = new ValidationLabel();
    final var actionButton = new Button().setLeftMouseButtonPressAction(FunctionService::throwException);
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
      FunctionService.throwException();
    } catch (final GeneralException exception) {
      return exception;
    }

    throw GeneralException
      .withErrorMessage("The throwException method of the FunctionCatalog did not throw an Exception.");
  }
}
