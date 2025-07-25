package ch.nolix.systemtest.webgui.container;

import ch.nolix.system.webgui.container.SingleContainer;
import ch.nolix.system.webgui.container.SingleContainerHtmlBuilder;
import ch.nolix.systemapi.webgui.container.ISingleContainer;
import ch.nolix.systemtest.webgui.basecontroltool.ControlHtmlBuilderTest;

final class SingleContainerHtmlBuilderTest
extends ControlHtmlBuilderTest<SingleContainerHtmlBuilder, ISingleContainer> {

  @Override
  protected ISingleContainer createControl() {
    return new SingleContainer();
  }

  @Override
  protected SingleContainerHtmlBuilder createTestUnit() {
    return new SingleContainerHtmlBuilder();
  }

  @Override
  protected String getExpectedStringRepresentationOfCreatedHtmlElementForNewControl() {
    return "<div />";
  }
}
