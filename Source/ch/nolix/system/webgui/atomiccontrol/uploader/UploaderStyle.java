package ch.nolix.system.webgui.atomiccontrol.uploader;

import ch.nolix.system.webgui.controlstyle.AbstractControlStyle;
import ch.nolix.systemapi.webgui.atomiccontrol.uploaderapi.IUploaderStyle;

public final class UploaderStyle extends AbstractControlStyle<IUploaderStyle> implements IUploaderStyle {

  public UploaderStyle() {
    initialize();
  }
}
