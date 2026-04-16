/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.systemtest.containercontrol.singlecontainer;

import ch.nolix.system.containercontrol.singlecontainer.SingleContainer;
import ch.nolix.systemapi.containercontrol.singlecontainer.ISingleContainer;
import ch.nolix.systemtest.containercontrol.container.ContainerTest;

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
