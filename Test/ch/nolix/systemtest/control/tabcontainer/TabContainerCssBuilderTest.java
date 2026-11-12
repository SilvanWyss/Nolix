/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.systemtest.control.tabcontainer;

import ch.nolix.system.control.tabcontainer.TabContainer;
import ch.nolix.system.control.tabcontainer.TabContainerCssBuilder;
import ch.nolix.systemapi.control.tabcontainer.ITabContainer;
import ch.nolix.systemtest.webgui.basecontroltool.ControlCssBuilderTest;

/**
 * @author Silvan Wyss
 */
final class TabContainerCssBuilderTest extends ControlCssBuilderTest<TabContainerCssBuilder, ITabContainer> {
  /**
   * {@inheritDoc}
   */
  @Override
  protected ITabContainer createControl() {
    return new TabContainer();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  protected TabContainerCssBuilder createTestUnit() {
    return new TabContainerCssBuilder();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  protected int getExpectedCssRuleCountForNewControl() {
    return 4;
  }
}
