//package declaration
package ch.nolix.systemtest.webguitest.atomiccontroltest;

//own imports
import ch.nolix.coreapi.webapi.htmlapi.IHtmlElement;
import ch.nolix.system.webgui.atomiccontrol.ImageControl;
import ch.nolix.system.webgui.atomiccontrol.ImageControlHtmlBuilder;
import ch.nolix.systemapi.webguiapi.atomiccontrolapi.IImageControl;
import ch.nolix.systemtest.webguitest.basecontroltooltest.ControlHtmlBuilderTest;

//class
final class ImageControlHtmlBuilderTest extends ControlHtmlBuilderTest<ImageControlHtmlBuilder, IImageControl> {

  //method
  @Override
  protected IImageControl createControl() {
    return new ImageControl();
  }

  //method
  @Override
  protected ImageControlHtmlBuilder createTestUnit() {
    return new ImageControlHtmlBuilder();
  }

  //method
  @Override
  protected void expectSpecificPropertiesOnHtmlElementCreatedOfNewControl(final IHtmlElement htmlElement) {
    expect(htmlElement).hasStringRepresentation("<img />");
  }
}
