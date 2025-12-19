package ch.nolix.systemtest.webatomiccontrol.link;

import ch.nolix.system.webatomiccontrol.link.Link;
import ch.nolix.systemapi.webatomiccontrol.link.ILink;
import ch.nolix.systemtest.webgui.main.ControlTest;

/**
 * @author Silvan Wyss
 */
final class LinkTest extends ControlTest<ILink> {
  @Override
  protected Link createTestUnit() {
    return new Link();
  }
}
