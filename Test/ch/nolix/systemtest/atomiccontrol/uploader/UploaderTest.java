/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.systemtest.atomiccontrol.uploader;

import ch.nolix.system.atomiccontrol.uploader.Uploader;
import ch.nolix.systemapi.atomiccontrol.uploader.IUploader;
import ch.nolix.systemtest.webgui.main.ControlTest;

/**
 * @author Silvan Wyss
 */
final class UploaderTest extends ControlTest<IUploader> {
  /**
   * {@inheritDoc}
   */
  @Override
  protected Uploader createTestUnit() {
    return new Uploader();
  }
}
