package ch.nolix.systemtest.webatomiccontrol.uploader;

import ch.nolix.system.webatomiccontrol.uploader.Uploader;
import ch.nolix.system.webatomiccontrol.uploader.UploaderHtmlBuilder;
import ch.nolix.systemapi.webatomiccontrol.uploader.IUploader;
import ch.nolix.systemtest.webgui.basecontroltool.ControlHtmlBuilderTest;

/**
 * @author Silvan Wyss
 */
final class UploaderHtmlBuilderTest extends ControlHtmlBuilderTest<UploaderHtmlBuilder, IUploader> {
  @Override
  protected IUploader createControl() {
    return new Uploader();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  protected UploaderHtmlBuilder createTestUnit() {
    return new UploaderHtmlBuilder();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  protected String getExpectedStringRepresentationOfCreatedHtmlElementForNewControl() {
    return "<input type=\"file\" multiple=\"none\" data-uploader=\"any\" />";
  }
}
