//package declaration
package ch.nolix.system.gui.iconresource;

//own imports
import ch.nolix.system.graphic.image.Image;

//class
public final class IconCatalogue {

  //constant
  private static final String NOLIX_ICON_RESOURCE_PATH = "ch/nolix/system/gui/iconresource/nolix_icon.png";

  //constant
  public static final Image NOLIX_ICON = Image.fromResource(NOLIX_ICON_RESOURCE_PATH);

  //constructor
  private IconCatalogue() {
  }
}
