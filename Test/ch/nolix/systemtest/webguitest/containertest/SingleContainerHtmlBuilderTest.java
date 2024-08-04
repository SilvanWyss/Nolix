//package declaration
package ch.nolix.systemtest.webguitest.containertest;

//own imports
import ch.nolix.system.webgui.container.SingleContainer;
import ch.nolix.system.webgui.container.SingleContainerHtmlBuilder;
import ch.nolix.systemapi.webguiapi.containerapi.ISingleContainer;
import ch.nolix.systemtest.webguitest.basecontroltooltest.ControlHtmlBuilderTest;

//class
final class SingleContainerHtmlBuilderTest
extends ControlHtmlBuilderTest<SingleContainerHtmlBuilder, ISingleContainer> {

  //method
  @Override
  protected ISingleContainer createControl() {
    return new SingleContainer();
  }

  //method
  @Override
  protected SingleContainerHtmlBuilder createTestUnit() {
    return new SingleContainerHtmlBuilder();
  }

  //method
  @Override
  protected String getExpectedStringRepresentationOfCreatedHtmlElementForNewControl() {
    return "<div />";
  }
}
