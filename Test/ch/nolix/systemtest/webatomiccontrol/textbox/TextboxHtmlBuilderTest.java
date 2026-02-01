/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.systemtest.webatomiccontrol.textbox;

import ch.nolix.system.webatomiccontrol.textbox.Textbox;
import ch.nolix.system.webatomiccontrol.textbox.TextboxHtmlBuilder;
import ch.nolix.systemapi.webatomiccontrol.textbox.ITextbox;
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
