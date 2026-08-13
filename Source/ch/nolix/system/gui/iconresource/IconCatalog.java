/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.system.gui.iconresource;

import ch.nolix.base.foundation.resource.ResourcePathCatalog;
import ch.nolix.system.graphic.image.ImmutableImage;

/**
 * Of the {@link IconCatalog} an instance cannot be created.
 * 
 * @author Silvan Wyss
 */
public final class IconCatalog {
  public static final ImmutableImage NOLIX_ICON = ImmutableImage.fromResource(ResourcePathCatalog.NOLIX_ICON);

  /**
   * Prevents that an instance of the {@link IconCatalog} can be created.
   */
  private IconCatalog() {
  }
}
