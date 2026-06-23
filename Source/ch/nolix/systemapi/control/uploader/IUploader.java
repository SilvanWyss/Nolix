/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.systemapi.control.uploader;

import ch.nolix.systemapi.webgui.main.Control;

/**
 * @author Silvan Wyss
 */
public interface IUploader extends Control<IUploader, IUploaderStyle> {
  byte[] getFile();

  boolean hasFile();

  void internalSetFile(byte[] file);
}
