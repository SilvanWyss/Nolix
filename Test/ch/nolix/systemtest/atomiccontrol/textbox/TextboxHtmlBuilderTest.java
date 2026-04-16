/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.systemtest.atomiccontrol.textbox;

import ch.nolix.system.atomiccontrol.textbox.Textbox;
import ch.nolix.system.atomiccontrol.textbox.TextboxHtmlBuilder;
import ch.nolix.systemapi.atomiccontrol.textbox.ITextbox;
import ch.nolix.systemtest.webgui.basecontroltool.ControlHtmlBuilderTest;

/**
 * @author Silvan Wyss
 */
final class TextboxHtmlBuilderTest extends ControlHtmlBuilderTest<TextboxHtmlBuilder, ITextbox> {
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
  protected TextboxHtmlBuilder createTestUnit() {
    return new TextboxHtmlBuilder();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  protected String getExpectedStringRepresentationOfCreatedHtmlElementForNewControl() {
    return "<input value=\"\" />";
  }
}
