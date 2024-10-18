package ch.nolix.systemapi.guiapi.frontendapi;

import java.util.Optional;

import ch.nolix.coreapi.containerapi.baseapi.IContainer;

public interface IFrontEndReader {

  IContainer<byte[]> getFilesFromClipboard();

  String getTextFromClipboard();

  Optional<byte[]> readFileToBytes();
}
