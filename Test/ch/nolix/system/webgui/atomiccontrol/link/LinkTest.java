package ch.nolix.system.webgui.atomiccontrol.link;

import ch.nolix.system.webgui.main.ControlTest;
import ch.nolix.systemapi.webguiapi.atomiccontrolapi.linkapi.ILink;

final class LinkTest extends ControlTest<ILink> {

  @Override
  protected Link createTestUnit() {
    return new Link();
  }
}
