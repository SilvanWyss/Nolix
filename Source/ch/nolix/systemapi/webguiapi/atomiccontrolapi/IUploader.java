package ch.nolix.systemapi.webguiapi.atomiccontrolapi;

import ch.nolix.systemapi.webguiapi.mainapi.IControl;

public interface IUploader extends IControl<IUploader, IUploaderStyle> {

  byte[] getFile();

  boolean hasFile();

  void internalSetFile(byte[] file);
}
