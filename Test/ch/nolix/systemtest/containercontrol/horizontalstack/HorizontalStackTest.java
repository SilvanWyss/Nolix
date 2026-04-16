/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.systemtest.containercontrol.horizontalstack;

import ch.nolix.system.containercontrol.horizontalstack.HorizontalStack;
import ch.nolix.systemapi.containercontrol.horizontalstack.IHorizontalStack;
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
