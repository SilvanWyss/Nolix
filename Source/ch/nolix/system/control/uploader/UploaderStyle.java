/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.system.control.uploader;

import ch.nolix.system.webgui.controlstyle.AbstractControlStyle;
import ch.nolix.systemapi.control.uploader.IUploaderStyle;

/**
 * @author Silvan Wyss
 */
public final class UploaderStyle extends AbstractControlStyle<IUploaderStyle> implements IUploaderStyle {
  public UploaderStyle() {
    initialize();
  }
}
