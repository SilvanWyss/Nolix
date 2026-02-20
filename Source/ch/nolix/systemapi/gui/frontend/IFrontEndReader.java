/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.systemapi.gui.frontend;

import java.util.Optional;

import ch.nolix.baseapi.container.base.IContainer;

/**
 * @author Silvan Wyss
 */
public interface IFrontEndReader {
  IContainer<byte[]> getFilesFromClipboard();

  String getTextFromClipboard();

  Optional<byte[]> readFileToBytes();
}
