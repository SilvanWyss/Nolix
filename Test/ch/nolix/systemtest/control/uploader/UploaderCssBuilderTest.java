/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.systemtest.control.uploader;

import ch.nolix.system.control.uploader.Uploader;
import ch.nolix.system.control.uploader.UploaderCssBuilder;
import ch.nolix.systemapi.control.uploader.IUploader;
import ch.nolix.systemtest.webgui.basecontroltool.ControlCssBuilderTest;

/**
 * @author Silvan Wyss
 */
final class UploaderCssBuilderTest extends ControlCssBuilderTest<UploaderCssBuilder, IUploader> {
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
  protected UploaderCssBuilder createTestUnit() {
    return new UploaderCssBuilder();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  protected int getExpectedCssRuleCountForNewControl() {
    return 4;
  }
}
