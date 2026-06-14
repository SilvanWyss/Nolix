/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.systemapi.gui.frontend;

import java.util.Optional;

import ch.nolix.baseapi.container.wellordercontainer.IWellOrderContainer;

/**
 * @author Silvan Wyss
 */
public interface IFrontEndReader {
  IWellOrderContainer<byte[]> getFilesFromClipboard();

  String getTextFromClipboard();

  Optional<byte[]> readFileToBytes();
}
