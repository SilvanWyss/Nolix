/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.system.control.imagecontrol;

import ch.nolix.system.webgui.controlstyle.AbstractControlStyle;
import ch.nolix.systemapi.control.imagecontrol.IImageControlStyle;

/**
 * @author Silvan Wyss
 */
public final class ImageControlStyle extends AbstractControlStyle<IImageControlStyle> implements IImageControlStyle {
  public ImageControlStyle() {
    initialize();
  }
}
