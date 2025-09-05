package ch.nolix.systemapi.webgui.atomiccontrol.uploaderapi;

import ch.nolix.systemapi.webgui.main.IControl;

public interface IUploader extends IControl<IUploader, IUploaderStyle> {
  byte[] getFile();

  boolean hasFile();

  void internalSetFile(byte[] file);
}
