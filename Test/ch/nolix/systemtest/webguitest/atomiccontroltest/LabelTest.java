//package declaration
package ch.nolix.systemtest.webguitest.atomiccontroltest;

import org.junit.jupiter.api.Test;

//own imports
import ch.nolix.core.errorcontrol.invalidargumentexception.ArgumentIsNullException;
import ch.nolix.system.webgui.atomiccontrol.Label;
import ch.nolix.systemapi.webguiapi.atomiccontrolapi.ILabel;
import ch.nolix.systemapi.webguiapi.atomiccontrolapi.LabelRole;
import ch.nolix.systemtest.webguitest.maintest.ControlTest;

//class
final class LabelTest extends ControlTest<ILabel> {

  //method
  @Test
  void testCase_removeRole() {

    //setup
    final var testUnit = new Label();
    testUnit.setRole(LabelRole.TITLE);

    //setup verification
    expect(testUnit.hasRole());

    //execution
    testUnit.removeRole();

    //verification
    expectNot(testUnit.hasRole());
  }

  //method
  @Test
  void testCase_setRole() {

    //setup
    final var testUnit = new Label();

    //setup verification
    expectNot(testUnit.hasRole());

    //execution
    final var result = testUnit.setRole(LabelRole.TITLE);

    //verification
    expect(result).is(testUnit);
    expect(testUnit.getRole()).is(LabelRole.TITLE);
  }

  //method
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

  //method
  @Test
  void testCase_setText_whenGivenTextIsEmpty() {

    //setup
    final var testUnit = new Label();

    //execution
    testUnit.setText("");

    //verification
    expect(testUnit.getText()).isEqualTo("");
  }

  //method
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

  //method
  @Override
  protected Label createTestUnit() {
    return new Label();
  }
}
