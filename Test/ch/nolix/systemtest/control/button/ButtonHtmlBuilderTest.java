/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.systemtest.control.button;

import ch.nolix.system.control.button.Button;
import ch.nolix.system.control.button.ButtonHtmlBuilder;
import ch.nolix.systemapi.control.button.IButton;
import ch.nolix.systemtest.webgui.basecontroltool.ControlHtmlBuilderTest;

/**
 * @author Silvan Wyss
 */
final class ButtonHtmlBuilderTest extends ControlHtmlBuilderTest<ButtonHtmlBuilder, IButton> {
  /**
   * {@inheritDoc}
   */
  @Override
  protected IButton createControl() {
    return new Button();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  protected ButtonHtmlBuilder createTestUnit() {
    return new ButtonHtmlBuilder();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  protected String getExpectedStringRepresentationOfCreatedHtmlElementForNewControl() {
    return "<button>-</button>";
  }
}
