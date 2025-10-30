package ch.nolix.systemtest.webgui.container.singlecontainer;

import ch.nolix.system.webgui.container.singlecontainer.SingleContainer;
import ch.nolix.systemapi.webgui.container.singlecontainer.ISingleContainer;
import ch.nolix.systemtest.webgui.basecontainer.ContainerTest;

final class SingleContainerTest extends ContainerTest<ISingleContainer> {
  @Override
  protected ISingleContainer createTestUnit() {
    return new SingleContainer();
  }
}
