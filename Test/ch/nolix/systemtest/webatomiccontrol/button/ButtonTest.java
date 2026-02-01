/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.systemtest.webatomiccontrol.button;

import ch.nolix.system.webatomiccontrol.button.Button;
import ch.nolix.systemapi.webatomiccontrol.button.IButton;
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
