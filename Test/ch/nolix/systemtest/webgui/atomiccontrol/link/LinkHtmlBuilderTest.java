package ch.nolix.systemtest.webgui.atomiccontrol.link;

import ch.nolix.system.webgui.atomiccontrol.link.Link;
import ch.nolix.system.webgui.atomiccontrol.link.LinkHtmlBuilder;
import ch.nolix.systemapi.webgui.atomiccontrol.linkapi.ILink;
import ch.nolix.systemtest.webgui.basecontroltool.ControlHtmlBuilderTest;

final class LinkHtmlBuilderTest extends ControlHtmlBuilderTest<LinkHtmlBuilder, ILink> {
  @Override
  protected ILink createControl() {
    return new Link();
  }

  @Override
  protected LinkHtmlBuilder createTestUnit() {
    return new LinkHtmlBuilder();
  }

  @Override
  protected String getExpectedStringRepresentationOfCreatedHtmlElementForNewControl() {
    return "<a target=\"_blank\">?</a>";
  }
}
