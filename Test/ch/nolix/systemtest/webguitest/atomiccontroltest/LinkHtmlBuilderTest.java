package ch.nolix.systemtest.webguitest.atomiccontroltest;

import ch.nolix.system.webgui.atomiccontrol.Link;
import ch.nolix.system.webgui.atomiccontrol.LinkHtmlBuilder;
import ch.nolix.systemapi.webguiapi.atomiccontrolapi.ILink;
import ch.nolix.systemtest.webguitest.basecontroltooltest.ControlHtmlBuilderTest;

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
