package ch.nolix.systemtest.webguitest.atomiccontroltest;

import ch.nolix.system.webgui.atomiccontrol.uploader.Uploader;
import ch.nolix.system.webgui.atomiccontrol.uploader.UploaderHtmlBuilder;
import ch.nolix.systemapi.webguiapi.atomiccontrolapi.uploaderapi.IUploader;
import ch.nolix.systemtest.webguitest.basecontroltooltest.ControlHtmlBuilderTest;

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
