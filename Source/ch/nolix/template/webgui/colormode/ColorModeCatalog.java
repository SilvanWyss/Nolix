package ch.nolix.template.webgui.colormode;

import ch.nolix.system.style.model.Style;
import ch.nolix.systemapi.style.model.IStyle;

/**
 * @author Silvan Wyss
 */
public final class ColorModeCatalog {
  public static final IStyle DARK_MODE = //
  new Style()
    .withSubStyle(
      DarkModeSubStyleCatalog.LAYER_STYLE,
      DarkModeSubStyleCatalog.DIALOG_LAYER_STYLE,
      DarkModeSubStyleCatalog.CONTROL_STYLE,
      DarkModeSubStyleCatalog.DIALOG_CONTAINER_STYLE,
      DarkModeSubStyleCatalog.BUTTON_STYLE,
      DarkModeSubStyleCatalog.DROPDOWN_MENU_STYLE,
      DarkModeSubStyleCatalog.LEVEL1_HEADER_STYLE,
      DarkModeSubStyleCatalog.LINK_STYLE,
      DarkModeSubStyleCatalog.TEXT_BOX_STYLE,
      DarkModeSubStyleCatalog.TITLE_STYLE,
      DarkModeSubStyleCatalog.VALIDATION_LABEL_STYLE);

  public static final IStyle PARCHMENT_MODE = //
  new Style()
    .withSubStyle(
      ParchmentModeSubStyleCatalog.LAYER_STYLE,
      ParchmentModeSubStyleCatalog.DIALOG_LAYER_STYLE,
      ParchmentModeSubStyleCatalog.CONTROL_STYLE,
      ParchmentModeSubStyleCatalog.DIALOG_CONTAINER_STYLE,
      ParchmentModeSubStyleCatalog.BUTTON_STYLE,
      ParchmentModeSubStyleCatalog.DROPDOWN_MENU_STYLE,
      ParchmentModeSubStyleCatalog.IMAGE_CONTROL_STYLE,
      ParchmentModeSubStyleCatalog.LEVEL1_HEADER_STYLE,
      ParchmentModeSubStyleCatalog.LINK_STYLE,
      ParchmentModeSubStyleCatalog.TEXT_BOX_STYLE,
      ParchmentModeSubStyleCatalog.TITLE_STYLE,
      ParchmentModeSubStyleCatalog.VALIDATION_LABEL_STYLE);

  private ColorModeCatalog() {
  }
}
