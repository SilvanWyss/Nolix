package ch.nolix.systemtest.webguitest.atomiccontroltest;

import org.junit.jupiter.api.Test;

import ch.nolix.system.webgui.atomiccontrol.Textbox;
import ch.nolix.systemapi.webguiapi.atomiccontrolapi.ITextbox;
import ch.nolix.systemtest.webguitest.maintest.ControlTest;

final class TextboxTest extends ControlTest<ITextbox> {

  @Test
  void testCase_emptyText() {

    //setup
    final var testUnit = new Textbox();
    testUnit.setText("Lorem ipsum");

    //execution
    testUnit.emptyText();

    //verification
    expect(testUnit.getText()).isEmpty();
  }

  @Override
  protected ITextbox createTestUnit() {
    return new Textbox();
  }
}
