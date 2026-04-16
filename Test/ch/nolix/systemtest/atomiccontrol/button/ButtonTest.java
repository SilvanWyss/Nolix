/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.systemtest.atomiccontrol.button;

import ch.nolix.system.atomiccontrol.button.Button;
import ch.nolix.systemapi.atomiccontrol.button.IButton;
import ch.nolix.systemtest.webgui.main.ControlTest;

/**
 * @author Silvan Wyss
 */
final class ButtonTest extends ControlTest<IButton> {
  /**
   * {@inheritDoc}
   */
  @Override
  protected Button createTestUnit() {
    return new Button();
  }
}
