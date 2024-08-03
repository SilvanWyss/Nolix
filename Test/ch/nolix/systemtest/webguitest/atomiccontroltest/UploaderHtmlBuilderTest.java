//package declaration
package ch.nolix.systemtest.webguitest.atomiccontroltest;

//own imports
import ch.nolix.system.webgui.atomiccontrol.Uploader;
import ch.nolix.system.webgui.atomiccontrol.UploaderHtmlBuilder;
import ch.nolix.systemapi.webguiapi.atomiccontrolapi.IUploader;
import ch.nolix.systemtest.webguitest.basecontroltooltest.ControlHtmlBuilderTest;

//class
final class UploaderHtmlBuilderTest extends ControlHtmlBuilderTest<UploaderHtmlBuilder, IUploader> {

  //method
  @Override
  protected IUploader createControl() {
    return new Uploader();
  }

  //method
  @Override
  protected UploaderHtmlBuilder createTestUnit() {
    return new UploaderHtmlBuilder();
  }

  //method
  @Override
  protected String getExpectedStringRepresentationOfCreatedHtmlElementForNewControl() {
    return "<input type=\"file\" multiple=\"none\" data-uploader=\"any\" />";
  }
}
