package ch.nolix.system.webgui.atomiccontrol.uploader;

import ch.nolix.system.webgui.main.ControlTest;
import ch.nolix.systemapi.webguiapi.atomiccontrolapi.uploaderapi.IUploader;

final class UploaderTest extends ControlTest<IUploader> {

  @Override
  protected Uploader createTestUnit() {
    return new Uploader();
  }
}
