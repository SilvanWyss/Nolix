package ch.nolix.systemtest.webatomiccontrol.uploader;

import ch.nolix.system.webatomiccontrol.uploader.Uploader;
import ch.nolix.systemapi.webatomiccontrol.uploader.IUploader;
import ch.nolix.systemtest.webgui.main.ControlTest;

final class UploaderTest extends ControlTest<IUploader> {
  @Override
  protected Uploader createTestUnit() {
    return new Uploader();
  }
}
