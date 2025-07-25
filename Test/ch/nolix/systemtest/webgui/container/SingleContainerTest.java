package ch.nolix.systemtest.webgui.container;

import ch.nolix.system.webgui.container.SingleContainer;
import ch.nolix.systemapi.webgui.container.ISingleContainer;
import ch.nolix.systemtest.webgui.basecontainer.ContainerTest;

final class SingleContainerTest extends ContainerTest<ISingleContainer> {

  @Override
  protected ISingleContainer createTestUnit() {
    return new SingleContainer();
  }
}
