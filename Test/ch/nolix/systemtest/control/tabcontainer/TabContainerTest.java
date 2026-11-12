/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.systemtest.control.tabcontainer;

import ch.nolix.system.control.tabcontainer.TabContainer;
import ch.nolix.systemapi.control.tabcontainer.ITabContainer;
import ch.nolix.systemtest.control.container.ContainerTest;

/**
 * @author Silvan Wyss
 */
final class TabContainerTest extends ContainerTest<ITabContainer> {
  /**
   * {@inheritDoc}
   */
  @Override
  protected ITabContainer createTestUnit() {
    return new TabContainer();
  }
}
