package ch.nolix.systemtest.webgui.container.singlecontainer;

import ch.nolix.system.webgui.container.singlecontainer.SingleContainer;
import ch.nolix.system.webgui.container.singlecontainer.SingleContainerHtmlBuilder;
import ch.nolix.systemapi.webgui.container.singlecontainer.ISingleContainer;
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
