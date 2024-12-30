package ch.nolix.systemtest.webguitest.atomiccontroltest;

import ch.nolix.system.webgui.atomiccontrol.uploader.Uploader;
import ch.nolix.systemapi.webguiapi.atomiccontrolapi.IUploader;
import ch.nolix.systemtest.webguitest.maintest.ControlTest;

final class UploaderTest extends ControlTest<IUploader> {

  @Override
  protected Uploader createTestUnit() {
    return new Uploader();
  }
}
