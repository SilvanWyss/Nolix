/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.systemtest.atomiccontrol.uploader;

import ch.nolix.system.atomiccontrol.uploader.Uploader;
import ch.nolix.system.atomiccontrol.uploader.UploaderHtmlBuilder;
import ch.nolix.systemapi.atomiccontrol.uploader.IUploader;
import ch.nolix.systemtest.webgui.basecontroltool.ControlHtmlBuilderTest;

/**
 * @author Silvan Wyss
 */
final class UploaderHtmlBuilderTest extends ControlHtmlBuilderTest<UploaderHtmlBuilder, IUploader> {
  /**
   * {@inheritDoc}
   */
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
