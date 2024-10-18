package ch.nolix.systemtest.webguitest.containertest;

import ch.nolix.system.webgui.container.SingleContainer;
import ch.nolix.systemapi.webguiapi.containerapi.ISingleContainer;

final class SingleContainerTest extends ContainerTest<ISingleContainer> {

  @Override
  protected ISingleContainer createTestUnit() {
    return new SingleContainer();
  }
}
