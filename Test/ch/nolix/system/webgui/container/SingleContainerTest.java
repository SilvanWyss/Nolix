package ch.nolix.system.webgui.container;

import ch.nolix.system.webgui.basecontainer.ContainerTest;
import ch.nolix.systemapi.webguiapi.containerapi.ISingleContainer;

final class SingleContainerTest extends ContainerTest<ISingleContainer> {

  @Override
  protected ISingleContainer createTestUnit() {
    return new SingleContainer();
  }
}
