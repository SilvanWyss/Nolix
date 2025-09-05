package ch.nolix.systemtest.webgui.atomiccontrol.link;

import ch.nolix.system.webgui.atomiccontrol.link.Link;
import ch.nolix.systemapi.webgui.atomiccontrol.linkapi.ILink;
import ch.nolix.systemtest.webgui.main.ControlTest;

final class LinkTest extends ControlTest<ILink> {
  @Override
  protected Link createTestUnit() {
    return new Link();
  }
}
