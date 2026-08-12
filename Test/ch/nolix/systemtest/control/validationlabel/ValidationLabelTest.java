/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.systemtest.control.validationlabel;

import org.junit.jupiter.api.Test;

import ch.nolix.base.util.FunctionService;
import ch.nolix.system.control.button.Button;
import ch.nolix.system.control.validationlabel.ValidationLabel;
import ch.nolix.system.control.verticalstack.VerticalStack;
import ch.nolix.systemapi.control.validationlabel.IValidationLabel;
import ch.nolix.systemtest.webgui.main.ControlTest;

/**
 * @author Silvan Wyss
 */
final class ValidationLabelTest extends ControlTest<IValidationLabel> {
  @Test
  void testCase_letsClear() {
    // setup step 1: create a ValidationLabel and runs an action that produces an
    // error.
    final var testUnit = new ValidationLabel();
    final var actionButton = new Button().setLeftMouseButtonPressAction(FunctionService::throwException);
    new VerticalStack().addControls(testUnit, actionButton);
    actionButton.pressLeftMouseButton();

    // setup step 2: Prepares an action that does not produce an error.
    actionButton.setLeftMouseButtonPressAction(FunctionService::doNothing);

    // execute: Runs the action that does not produce an error.
    actionButton.pressLeftMouseButton();

    // verify
    expect(testUnit.isEmpty()).isTrue();
  }

  @Test
  void testCase_letsShowError() {
    // setup
    final var testUnit = new ValidationLabel();
    final var actionButton = new Button().setLeftMouseButtonPressAction(FunctionService::throwException);
    new VerticalStack().addControls(testUnit, actionButton);

    // setup verification
    expect(testUnit.isEmpty()).isTrue();

    // execute
    actionButton.pressLeftMouseButton();

    // verify
    expect(testUnit.containsAny()).isTrue();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  protected IValidationLabel createTestUnit() {
    return new ValidationLabel();
  }
}
