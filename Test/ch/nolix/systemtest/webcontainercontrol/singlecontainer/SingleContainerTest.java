package ch.nolix.systemtest.webcontainercontrol.singlecontainer;

import ch.nolix.system.webcontainercontrol.singlecontainer.SingleContainer;
import ch.nolix.systemapi.webcontainercontrol.singlecontainer.ISingleContainer;
import ch.nolix.systemtest.webcontainercontrol.container.ContainerTest;

final class SingleContainerTest extends ContainerTest<ISingleContainer> {
  @Override
  protected ISingleContainer createTestUnit() {
    return new SingleContainer();
  }
}
