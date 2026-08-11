/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.systemapi.gui.guiattribute;

import ch.nolix.systemapi.graphic.image.Image;

/**
 * A {@link IconHolder} has a icon.
 * 
 * @author Silvan Wyss
 */
public interface IconHolder {
  /**
   * @return the icon of the current {@link IconHolder}
   */
  Image getIcon();
}
