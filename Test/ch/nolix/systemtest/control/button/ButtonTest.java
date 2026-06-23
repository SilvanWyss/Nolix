/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.systemtest.control.button;

import ch.nolix.system.control.button.Button;
import ch.nolix.systemapi.control.button.IButton;
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
