/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.systemapi.webatomiccontrol.uploader;

import ch.nolix.systemapi.webgui.main.IControl;

/**
 * @author Silvan Wyss
 */
public interface IUploader extends IControl<IUploader, IUploaderStyle> {
  byte[] getFile();

  boolean hasFile();

  void internalSetFile(byte[] file);
}
