package ch.nolix.systemtest.webguitest.atomiccontroltest;

import ch.nolix.system.webgui.atomiccontrol.link.Link;
import ch.nolix.systemapi.webguiapi.atomiccontrolapi.linkapi.ILink;
import ch.nolix.systemtest.webguitest.maintest.ControlTest;

final class LinkTest extends ControlTest<ILink> {

  @Override
  protected Link createTestUnit() {
    return new Link();
  }
}
