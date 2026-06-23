/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.systemtest.control.link;

import ch.nolix.system.control.link.Link;
import ch.nolix.system.control.link.LinkHtmlBuilder;
import ch.nolix.systemapi.control.link.ILink;
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
