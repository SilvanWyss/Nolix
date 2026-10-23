/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.systemapi.control.uploader;

import ch.nolix.systemapi.webgui.main.Control;

/**
 * @author Silvan Wyss
 */
public interface IUploader extends Control<IUploader, IUploaderStyle> {
  /**
   * @return the file of the current {@link IUploader}
   * @throws RuntimeException if the current {@link IUploader} does not have a
   *                          file
   */
  byte[] getFile();

  /**
   * @return true if the current {@link IUploader} has a file, false otherwise
   */
  boolean hasFile();

  /**
   * Sets the given file to the current {@link IUploader}.
   * 
   * @param file
   */
  void internalSetFile(byte[] file);
}
