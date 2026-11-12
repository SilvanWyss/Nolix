/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.systemtest.control.link;

import ch.nolix.system.control.link.Link;
import ch.nolix.system.control.link.LinkCssBuilder;
import ch.nolix.systemapi.control.link.ILink;
import ch.nolix.systemtest.webgui.basecontroltool.ControlCssBuilderTest;

/**
 * @author Silvan Wyss
 */
final class LinkCssBuilderTest extends ControlCssBuilderTest<LinkCssBuilder, ILink> {
  /**
   * {@inheritDoc}
   */
  @Override
  protected ILink createControl() {
    return new Link();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  protected LinkCssBuilder createTestUnit() {
    return new LinkCssBuilder();
  }
}
