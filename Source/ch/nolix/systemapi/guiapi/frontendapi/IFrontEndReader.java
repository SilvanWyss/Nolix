//package declaration
package ch.nolix.systemapi.guiapi.frontendapi;

import ch.nolix.coreapi.containerapi.baseapi.IContainer;
import ch.nolix.coreapi.containerapi.singlecontainerapi.ISingleContainer;

//interface
public interface IFrontEndReader {

  //method declaration
  IContainer<byte[]> getFilesFromClipboard();

  //method declaration
  String getTextFromClipboard();

  //method declaration
  ISingleContainer<byte[]> readFileToBytes();
}
