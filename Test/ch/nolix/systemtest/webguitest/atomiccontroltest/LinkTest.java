package ch.nolix.systemtest.webguitest.atomiccontroltest;

import ch.nolix.system.webgui.atomiccontrol.Link;
import ch.nolix.systemapi.webguiapi.atomiccontrolapi.ILink;
import ch.nolix.systemtest.webguitest.maintest.ControlTest;

final class LinkTest extends ControlTest<ILink> {

  @Override
  protected Link createTestUnit() {
    return new Link();
  }
}
