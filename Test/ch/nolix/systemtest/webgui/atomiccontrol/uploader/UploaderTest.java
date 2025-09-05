package ch.nolix.systemtest.webgui.atomiccontrol.uploader;

import ch.nolix.system.webgui.atomiccontrol.uploader.Uploader;
import ch.nolix.systemapi.webgui.atomiccontrol.uploaderapi.IUploader;
import ch.nolix.systemtest.webgui.main.ControlTest;

final class UploaderTest extends ControlTest<IUploader> {
  @Override
  protected Uploader createTestUnit() {
    return new Uploader();
  }
}
