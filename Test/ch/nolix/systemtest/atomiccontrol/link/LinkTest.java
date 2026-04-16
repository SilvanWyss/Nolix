/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.systemtest.atomiccontrol.link;

import ch.nolix.system.atomiccontrol.link.Link;
import ch.nolix.systemapi.atomiccontrol.link.ILink;
import ch.nolix.systemtest.webgui.main.ControlTest;

/**
 * @author Silvan Wyss
 */
final class LinkTest extends ControlTest<ILink> {
  /**
   * {@inheritDoc}
   */
  @Override
  protected Link createTestUnit() {
    return new Link();
  }
}
