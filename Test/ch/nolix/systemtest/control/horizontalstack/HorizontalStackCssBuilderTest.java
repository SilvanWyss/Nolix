/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.systemtest.control.horizontalstack;

import ch.nolix.system.control.horizontalstack.HorizontalStack;
import ch.nolix.system.control.horizontalstack.HorizontalStackCssBuilder;
import ch.nolix.systemapi.control.horizontalstack.IHorizontalStack;
import ch.nolix.systemtest.webgui.basecontroltool.ControlCssBuilderTest;

/**
 * @author Silvan Wyss
 */
final class HorizontalStackCssBuilderTest extends ControlCssBuilderTest<HorizontalStackCssBuilder, IHorizontalStack> {
  /**
   * {@inheritDoc}
   */
  @Override
  protected IHorizontalStack createControl() {
    return new HorizontalStack();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  protected HorizontalStackCssBuilder createTestUnit() {
    return new HorizontalStackCssBuilder();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  protected int getExpectedCssRuleCountForNewControl() {
    return 8;
  }
}
