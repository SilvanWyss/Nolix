//package declaration
package ch.nolix.systemtest.webguitest.atomiccontroltest;

import ch.nolix.coreapi.testingapi.testapi.TestCase;
import ch.nolix.system.webgui.atomiccontrol.Textbox;
import ch.nolix.systemapi.webguiapi.atomiccontrolapi.ITextbox;
import ch.nolix.systemtest.webguitest.maintest.ControlTest;

//class
public final class TextboxTest extends ControlTest<ITextbox> {

  //method
  @TestCase
  public void testCase_emptyText() {

    //setup
    final var testUnit = new Textbox();
    testUnit.setText("Lorem ipsum");

    //execution
    testUnit.emptyText();

    //verification
    expect(testUnit.getText()).isEmpty();
  }

  //method
  @Override
  protected ITextbox createTestUnit() {
    return new Textbox();
  }
}
