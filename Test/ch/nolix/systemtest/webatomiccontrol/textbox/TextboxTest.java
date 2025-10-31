package ch.nolix.systemtest.webatomiccontrol.textbox;

import org.junit.jupiter.api.Test;

import ch.nolix.system.webatomiccontrol.textbox.Textbox;
import ch.nolix.systemapi.webatomiccontrol.textbox.ITextbox;
import ch.nolix.systemtest.webgui.main.ControlTest;

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
