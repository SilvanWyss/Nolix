/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.systemtest.webcontainercontrol.singlecontainer;

import ch.nolix.system.webcontainercontrol.singlecontainer.SingleContainer;
import ch.nolix.systemapi.webcontainercontrol.singlecontainer.ISingleContainer;
import ch.nolix.systemtest.webcontainercontrol.container.ContainerTest;

/**
 * @author Silvan Wyss
 */
final class SingleContainerTest extends ContainerTest<ISingleContainer> {
  /**
   * {@inheritDoc}
   */
  @Override
  protected ISingleContainer createTestUnit() {
    return new SingleContainer();
  }
}
