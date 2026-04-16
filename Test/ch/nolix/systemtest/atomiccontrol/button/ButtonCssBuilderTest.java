/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.systemtest.atomiccontrol.button;

import ch.nolix.system.atomiccontrol.button.Button;
import ch.nolix.system.atomiccontrol.button.ButtonCssBuilder;
import ch.nolix.systemapi.atomiccontrol.button.IButton;
import ch.nolix.systemtest.webgui.basecontroltool.ControlCssBuilderTest;

/**
 * @author Silvan Wyss
 */
final class ButtonCssBuilderTest extends ControlCssBuilderTest<ButtonCssBuilder, IButton> {
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
  protected ButtonCssBuilder createTestUnit() {
    return new ButtonCssBuilder();
  }
}
