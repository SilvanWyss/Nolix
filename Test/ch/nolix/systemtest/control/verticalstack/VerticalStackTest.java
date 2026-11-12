/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.systemtest.control.verticalstack;

import ch.nolix.system.control.verticalstack.VerticalStack;
import ch.nolix.systemapi.control.verticalstack.IVerticalStack;
import ch.nolix.systemtest.webgui.main.ControlTest;

/**
 * @author Silvan Wyss
 */
final class VerticalStackTest extends ControlTest<IVerticalStack> {
  /**
   * {@inheritDoc}
   */
  @Override
  protected IVerticalStack createTestUnit() {
    return new VerticalStack();
  }
}
