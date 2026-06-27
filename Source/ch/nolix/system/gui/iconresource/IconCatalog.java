/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.system.gui.iconresource;

import ch.nolix.system.graphic.image.ImmutableImage;

/**
 * @author Silvan Wyss
 */
public final class IconCatalog {
  private static final String NOLIX_ICON_RESOURCE_PATH = "icon/nolix_icon.png";

  public static final ImmutableImage NOLIX_ICON = ImmutableImage.fromResource(NOLIX_ICON_RESOURCE_PATH);

  private IconCatalog() {
  }
}
