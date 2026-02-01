/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.systemtest.webatomiccontrol.link;

import ch.nolix.system.webatomiccontrol.link.Link;
import ch.nolix.system.webatomiccontrol.link.LinkHtmlBuilder;
import ch.nolix.systemapi.webatomiccontrol.link.ILink;
import ch.nolix.systemtest.webgui.basecontroltool.ControlHtmlBuilderTest;

/**
 * @author Silvan Wyss
 */
final class LinkHtmlBuilderTest extends ControlHtmlBuilderTest<LinkHtmlBuilder, ILink> {
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
  protected LinkHtmlBuilder createTestUnit() {
    return new LinkHtmlBuilder();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  protected String getExpectedStringRepresentationOfCreatedHtmlElementForNewControl() {
    return "<a target=\"_blank\">?</a>";
  }
}
