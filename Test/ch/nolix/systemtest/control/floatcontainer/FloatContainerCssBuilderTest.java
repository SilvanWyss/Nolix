/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.systemtest.control.floatcontainer;

import ch.nolix.system.control.floatcontainer.FloatContainer;
import ch.nolix.system.control.floatcontainer.FloatContainerCssBuilder;
import ch.nolix.systemtest.webgui.basecontroltool.ControlCssBuilderTest;

/**
 * @author Silvan Wyss
 */
final class FloatContainerCssBuilderTest extends ControlCssBuilderTest<FloatContainerCssBuilder, FloatContainer> {
  /**
   * {@inheritDoc}
   */
  @Override
  protected FloatContainer createControl() {
    return new FloatContainer();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  protected FloatContainerCssBuilder createTestUnit() {
    return new FloatContainerCssBuilder();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  protected int getExpectedCssRuleCountForNewControl() {
    return 8;
  }
}
