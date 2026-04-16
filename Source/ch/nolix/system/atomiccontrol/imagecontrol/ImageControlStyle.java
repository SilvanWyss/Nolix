/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.system.atomiccontrol.imagecontrol;

import ch.nolix.system.webgui.controlstyle.AbstractControlStyle;
import ch.nolix.systemapi.atomiccontrol.imagecontrol.IImageControlStyle;

/**
 * @author Silvan Wyss
 */
public final class ImageControlStyle extends AbstractControlStyle<IImageControlStyle> implements IImageControlStyle {
  public ImageControlStyle() {
    initialize();
  }
}
