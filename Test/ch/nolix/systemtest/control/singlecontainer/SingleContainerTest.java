/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.systemtest.control.singlecontainer;

import ch.nolix.system.control.singlecontainer.SingleContainer;
import ch.nolix.systemapi.control.singlecontainer.ISingleContainer;
import ch.nolix.systemtest.control.container.ContainerTest;

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
