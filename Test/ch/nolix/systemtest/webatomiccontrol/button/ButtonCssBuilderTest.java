/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.systemtest.webatomiccontrol.button;

import ch.nolix.system.webatomiccontrol.button.Button;
import ch.nolix.system.webatomiccontrol.button.ButtonCssBuilder;
import ch.nolix.systemapi.webatomiccontrol.button.IButton;
import ch.nolix.systemtest.webgui.basecontroltool.ControlCssBuilderTest;

/**
 * @author Silvan Wyss
 */
final class ButtonCssBuilderTest extends ControlCssBuilderTest<ButtonCssBuilder, IButton> {
  @Override
  protected IButton createControl() {
    return new Button();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  protected ButtonCssBuilder createTestUnit() {
    return new ButtonCssBuilder();
  }
}
