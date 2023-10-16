//package declaration
package ch.nolix.systemtest.webguitest.atomiccontroltest;

//own imports
import ch.nolix.system.webgui.atomiccontrol.Uploader;
import ch.nolix.systemapi.webguiapi.atomiccontrolapi.IUploader;
import ch.nolix.systemtest.webguitest.maintest.ControlTest;

//class
public final class UploaderTest extends ControlTest<IUploader> {

  //method
  @Override
  protected Uploader createTestUnit() {
    return new Uploader();
  }
}
