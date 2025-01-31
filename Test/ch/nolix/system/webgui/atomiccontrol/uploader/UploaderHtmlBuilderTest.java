package ch.nolix.system.webgui.atomiccontrol.uploader;

import ch.nolix.system.webgui.basecontroltool.ControlHtmlBuilderTest;
import ch.nolix.systemapi.webguiapi.atomiccontrolapi.uploaderapi.IUploader;

final class UploaderHtmlBuilderTest extends ControlHtmlBuilderTest<UploaderHtmlBuilder, IUploader> {

  @Override
  protected IUploader createControl() {
    return new Uploader();
  }

  @Override
  protected UploaderHtmlBuilder createTestUnit() {
    return new UploaderHtmlBuilder();
  }

  @Override
  protected String getExpectedStringRepresentationOfCreatedHtmlElementForNewControl() {
    return "<input type=\"file\" multiple=\"none\" data-uploader=\"any\" />";
  }
}
