package ch.nolix.system.webgui.atomiccontrol.textbox;

import org.junit.jupiter.api.Test;

import ch.nolix.system.webgui.main.ControlTest;
import ch.nolix.systemapi.webguiapi.atomiccontrolapi.textboxapi.ITextbox;

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
