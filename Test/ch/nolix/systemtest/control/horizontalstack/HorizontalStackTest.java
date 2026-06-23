/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.systemtest.control.horizontalstack;

import ch.nolix.system.control.horizontalstack.HorizontalStack;
import ch.nolix.systemapi.control.horizontalstack.IHorizontalStack;
import ch.nolix.systemtest.webgui.main.ControlTest;

/**
 * @author Silvan Wyss
 */
final class HorizontalStackTest extends ControlTest<IHorizontalStack> {
  /**
   * {@inheritDoc}
   */
  @Override
  protected IHorizontalStack createTestUnit() {
    return new HorizontalStack();
  }
}
