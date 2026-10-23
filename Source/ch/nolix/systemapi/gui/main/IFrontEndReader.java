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
  /**
   * @return the files from the clipboard of the front end
   */
  ExtendedIterable<byte[]> getFilesFromClipboard();

  /**
   * @return the text from the clipboard of the front end
   */
  String getTextFromClipboard();

  /**
   * Lets the user of the front end select a file.
   * 
   * @return a {@link Optional} with the bytes of the file the user has selected
   *         if the user has selected a file, an empty {@link Optional} otherwise
   */
  Optional<byte[]> readFileToBytes();
}
