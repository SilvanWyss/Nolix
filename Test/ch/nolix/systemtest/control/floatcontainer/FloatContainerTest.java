/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.systemtest.control.floatcontainer;

import ch.nolix.system.control.floatcontainer.FloatContainer;
import ch.nolix.systemtest.webgui.main.ControlTest;

/**
 * @author Silvan Wyss
 */
final class FloatContainerTest extends ControlTest<FloatContainer> {
  /**
   * {@inheritDoc}
   */
  @Override
  protected FloatContainer createTestUnit() {
    return new FloatContainer();
  }
}
