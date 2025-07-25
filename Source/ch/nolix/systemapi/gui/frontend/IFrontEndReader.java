package ch.nolix.systemapi.gui.frontend;

import java.util.Optional;

import ch.nolix.coreapi.container.base.IContainer;

public interface IFrontEndReader {

  IContainer<byte[]> getFilesFromClipboard();

  String getTextFromClipboard();

  Optional<byte[]> readFileToBytes();
}
