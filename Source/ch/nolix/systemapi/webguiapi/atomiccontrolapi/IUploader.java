//package declaration
package ch.nolix.systemapi.webguiapi.atomiccontrolapi;

//own imports
import ch.nolix.systemapi.webguiapi.mainapi.IControl;

//interface
public interface IUploader extends IControl<IUploader, IUploaderStyle> {

  // method declaration
  byte[] getFile();

  // method declaration
  boolean hasFile();

  // method declaration
  void technicalSetFile(byte[] file);
}
