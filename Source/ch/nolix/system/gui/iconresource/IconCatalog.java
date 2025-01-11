package ch.nolix.system.gui.iconresource;

import ch.nolix.system.graphic.image.Image;

public final class IconCatalog {

  private static final String NOLIX_ICON_RESOURCE_PATH = "icon/nolix_icon.png";

  public static final Image NOLIX_ICON = Image.fromResource(NOLIX_ICON_RESOURCE_PATH);

  private IconCatalog() {
  }
}
