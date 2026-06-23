/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.systemtest.control.textbox;

import ch.nolix.system.control.textbox.Textbox;
import ch.nolix.system.control.textbox.TextboxCssBuilder;
import ch.nolix.systemapi.control.textbox.ITextbox;
import ch.nolix.systemtest.webgui.basecontroltool.ControlCssBuilderTest;

/**
 * @author Silvan Wyss
 */
final class TextboxCssBuilderTest extends ControlCssBuilderTest<TextboxCssBuilder, ITextbox> {
  /**
   * {@inheritDoc}
   */
  @Override
  protected ITextbox createControl() {
    return new Textbox();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  protected TextboxCssBuilder createTestUnit() {
    return new TextboxCssBuilder();
  }
}
