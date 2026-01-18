package ch.nolix.systemtest.webatomiccontrol.label;

import org.junit.jupiter.api.Test;

import ch.nolix.core.errorcontrol.invalidargumentexception.ArgumentIsNullException;
import ch.nolix.system.webatomiccontrol.label.Label;
import ch.nolix.systemapi.webatomiccontrol.label.ILabel;
import ch.nolix.systemapi.webatomiccontrol.label.LabelRole;
import ch.nolix.systemtest.webgui.main.ControlTest;

/**
 * @author Silvan Wyss
 */
final class LabelTest extends ControlTest<ILabel> {
  @Test
  void testCase_removeRole() {
    //setup
    final var testUnit = new Label();
    testUnit.setRole(LabelRole.TITLE);

    //setup verification
    expect(testUnit.hasRole()).isTrue();

    //execution
    testUnit.removeRole();

    //verification
    expect(testUnit.hasRole()).isFalse();
  }

  @Test
  void testCase_setRole() {
    //setup
    final var testUnit = new Label();

    //setup verification
    expect(testUnit.hasRole()).isFalse();

    //execution
    final var result = testUnit.setRole(LabelRole.TITLE);

    //verification
    expect(result).is(testUnit);
    expect(testUnit.getRole()).is(LabelRole.TITLE);
  }

  @Test
  void testCase_setText() {
    //setup
    final var testUnit = new Label();

    //setup verification
    expect(testUnit.getText()).isEqualTo("-");

    //execution
    testUnit.setText("Lorem Ipsum");

    //verification
    expect(testUnit.getText()).isEqualTo("Lorem Ipsum");
  }

  @Test
  void testCase_setText_whenGivenTextIsEmpty() {
    //setup
    final var testUnit = new Label();

    //execution
    testUnit.setText("");

    //verification
    expect(testUnit.getText()).isEqualTo("");
  }

  @Test
  void testCase_setText_whenGivenTextIsNull() {
    //setup
    final var testUnit = new Label();
    testUnit.setText("Lorem Ipsum");

    //execution & verification
    expectRunning(() -> testUnit.setText(null))
      .throwsException()
      .ofType(ArgumentIsNullException.class)
      .withMessage("The given text is null.");

    //verification
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
