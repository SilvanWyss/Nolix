/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.systemapi.gui.main;

import java.util.Optional;

import ch.nolix.baseapi.datastructure.extendediterable.ExtendedIterable;

/**
 * @author Silvan Wyss
 */
public interface IFrontEndReader {
  ExtendedIterable<byte[]> getFilesFromClipboard();

  String getTextFromClipboard();

  Optional<byte[]> readFileToBytes();
}
