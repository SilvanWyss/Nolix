/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.systemtest.control.verticalstack;

import ch.nolix.system.control.verticalstack.VerticalStack;
import ch.nolix.system.control.verticalstack.VerticalStackCssBuilder;
import ch.nolix.systemapi.control.verticalstack.IVerticalStack;
import ch.nolix.systemtest.webgui.basecontroltool.ControlCssBuilderTest;

/**
 * @author Silvan Wyss
 */
final class VerticalStackCssBuilderTest extends ControlCssBuilderTest<VerticalStackCssBuilder, IVerticalStack> {
  /**
   * {@inheritDoc}
   */
  @Override
  protected IVerticalStack createControl() {
    return new VerticalStack();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  protected VerticalStackCssBuilder createTestUnit() {
    return new VerticalStackCssBuilder();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  protected int getExpectedCssRuleCountForNewControl() {
    return 7;
  }
}
