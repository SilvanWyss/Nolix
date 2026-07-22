/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.systemtest.control.textbox;

import org.junit.jupiter.api.Test;

import ch.nolix.system.control.textbox.Textbox;
import ch.nolix.systemapi.control.textbox.ITextbox;
import ch.nolix.systemtest.webgui.main.ControlTest;

/**
 * @author Silvan Wyss
 */
final class TextboxTest extends ControlTest<ITextbox> {
  @Test
  void testCase_emptyText() {
    // setup
    final var testUnit = new Textbox();
    testUnit.setText("Lorem ipsum");

   // execute
    testUnit.emptyText();

   // verify
    expect(testUnit.getText()).isEmpty();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  protected ITextbox createTestUnit() {
    return new Textbox();
  }
}
