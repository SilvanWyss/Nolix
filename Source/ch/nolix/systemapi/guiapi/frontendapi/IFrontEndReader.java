//package declaration
package ch.nolix.systemapi.guiapi.frontendapi;

//Java imports
import java.util.Optional;

//own imports
import ch.nolix.coreapi.containerapi.baseapi.IContainer;

//interface
public interface IFrontEndReader {

  //method declaration
  IContainer<byte[]> getFilesFromClipboard();

  //method declaration
  String getTextFromClipboard();

  //method declaration
  Optional<byte[]> readFileToBytes();
}
