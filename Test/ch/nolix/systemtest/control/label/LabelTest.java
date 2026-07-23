/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.systemtest.control.label;

import org.junit.jupiter.api.Test;

import ch.nolix.baseapi.errorcontrol.invalidargumentexception.ArgumentIsNullException;
import ch.nolix.system.control.label.Label;
import ch.nolix.systemapi.control.label.ILabel;
import ch.nolix.systemapi.control.label.LabelRole;
import ch.nolix.systemtest.webgui.main.ControlTest;

/**
 * @author Silvan Wyss
 */
final class LabelTest extends ControlTest<ILabel> {
  @Test
  void testCase_removeRole() {
    // setup
    final var testUnit = new Label();
    testUnit.setRole(LabelRole.TITLE);

    // setup verification
    expect(testUnit.hasRole()).isTrue();

    // execute
    testUnit.removeRole();

    // verify
    expect(testUnit.hasRole()).isFalse();
  }

  @Test
  void testCase_setRole() {
    // setup
    final var testUnit = new Label();

    // setup verification
    expect(testUnit.hasRole()).isFalse();

    // execute
    final var result = testUnit.setRole(LabelRole.TITLE);

    // verify
    expect(result).is(testUnit);
    expect(testUnit.getRole()).is(LabelRole.TITLE);
  }

  @Test
  void testCase_setText() {
    // setup
    final var testUnit = new Label();

    // setup verification
    expect(testUnit.getText()).isEqualTo("-");

    // execute
    testUnit.setText("Lorem Ipsum");

    // verify
    expect(testUnit.getText()).isEqualTo("Lorem Ipsum");
  }

  @Test
  void testCase_setText_whenGivenTextIsEmpty() {
    // setup
    final var testUnit = new Label();

    // execute
    testUnit.setText("");

    // verify
    expect(testUnit.getText()).isEqualTo("");
  }

  @Test
  void testCase_setText_whenGivenTextIsNull() {
    // setup
    final var testUnit = new Label();
    testUnit.setText("Lorem Ipsum");

    // execute & verify
    expectRunning(() -> testUnit.setText(null))
      .throwsException()
      .ofType(ArgumentIsNullException.class)
      .withMessage("The given text is null.");

    // verify
    expect(testUnit.getText()).isEqualTo("Lorem Ipsum");
  }

  /**
   * {@inheritDoc}
   */
  @Override
  protected Label createTestUnit() {
    return new Label();
  }
}
