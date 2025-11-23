package ch.nolix.template.webgui.shapemode;

import ch.nolix.system.style.model.Style;
import ch.nolix.systemapi.style.model.IStyle;

public final class ShapeModeCatalog {
  public static final IStyle EDGE_STYLE = //
  new Style()
    .withSubStyle(
      EdgeModeSubStyleCatalog.DIALOG_LAYER_STYLE,
      EdgeModeSubStyleCatalog.DIALOG_CONTAINER_STYLE,
      EdgeModeSubStyleCatalog.FOOTER_CONTAINER_STYLE,
      EdgeModeSubStyleCatalog.GRID_STYLE,
      EdgeModeSubStyleCatalog.HEADER_CONTAINER_STYLE,
      EdgeModeSubStyleCatalog.LINEAR_CONTAINER_STYLE,
      EdgeModeSubStyleCatalog.MAIN_CONTENT_CONTAINER_STYLE,
      EdgeModeSubStyleCatalog.OVERALL_CONTAINER_STYLE,
      EdgeModeSubStyleCatalog.BUTTON_STYLE,
      EdgeModeSubStyleCatalog.DROPDOWN_MENU_STYLE,
      EdgeModeSubStyleCatalog.LEVEL1_HEADER_STYLE,
      EdgeModeSubStyleCatalog.TEXT_BOX_STYLE,
      EdgeModeSubStyleCatalog.TITLE_STYLE);

  private ShapeModeCatalog() {
  }
}
