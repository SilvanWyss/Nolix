package ch.nolix.template.webgui.style;

import ch.nolix.system.style.model.Style;
import ch.nolix.systemapi.style.model.IStyle;

public final class StyleCatalog {
  public static final IStyle DARK_STYLE = //
  new Style()
    .withSubStyle(
      DarkStyleSubStyleCatalog.LAYER_STYLE,
      DarkStyleSubStyleCatalog.DIALOG_LAYER_STYLE,
      DarkStyleSubStyleCatalog.CONTROL_STYLE,
      DarkStyleSubStyleCatalog.DIALOG_CONTAINER_STYLE,
      DarkStyleSubStyleCatalog.FOOTER_CONTAINER_STYLE,
      DarkStyleSubStyleCatalog.GRID_STYLE,
      DarkStyleSubStyleCatalog.HEADER_CONTAINER_STYLE,
      DarkStyleSubStyleCatalog.LINEAR_CONTAINER_STYLE,
      DarkStyleSubStyleCatalog.MAIN_CONTENT_CONTAINER_STYLE,
      DarkStyleSubStyleCatalog.OVERALL_CONTAINER_STYLE,
      DarkStyleSubStyleCatalog.BUTTON_STYLE,
      DarkStyleSubStyleCatalog.DROPDOWN_MENU_STYLE,
      DarkStyleSubStyleCatalog.LEVEL1_HEADER_STYLE,
      DarkStyleSubStyleCatalog.LINK_STYLE,
      DarkStyleSubStyleCatalog.TEXT_BOX_STYLE,
      DarkStyleSubStyleCatalog.TITLE_STYLE,
      DarkStyleSubStyleCatalog.VALIDATION_LABEL_STYLE);

  private StyleCatalog() {
  }
}
