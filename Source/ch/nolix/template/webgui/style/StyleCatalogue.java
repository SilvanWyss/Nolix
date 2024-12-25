package ch.nolix.template.webgui.style;

import ch.nolix.system.element.style.Style;
import ch.nolix.systemapi.elementapi.styleapi.IStyle;

public final class StyleCatalogue {

  public static final IStyle DARK_STYLE = //
  new Style()
    .withSubStyle(
      DarkStyleSubStyleCatalogue.LAYER_STYLE,
      DarkStyleSubStyleCatalogue.DIALOG_LAYER_STYLE,
      DarkStyleSubStyleCatalogue.CONTROL_STYLE,
      DarkStyleSubStyleCatalogue.DIALOG_CONTAINER_STYLE,
      DarkStyleSubStyleCatalogue.FOOTER_CONTAINER_STYLE,
      DarkStyleSubStyleCatalogue.GRID_STYLE,
      DarkStyleSubStyleCatalogue.HEADER_CONTAINER_STYLE,
      DarkStyleSubStyleCatalogue.LINEAR_CONTAINER_STYLE,
      DarkStyleSubStyleCatalogue.MAIN_CONTENT_CONTAINER_STYLE,
      DarkStyleSubStyleCatalogue.OVERALL_CONTAINER_STYLE,
      DarkStyleSubStyleCatalogue.BUTTON_STYLE,
      DarkStyleSubStyleCatalogue.DROPDOWN_MENU_STYLE,
      DarkStyleSubStyleCatalogue.LEVEL1_HEADER_STYLE,
      DarkStyleSubStyleCatalogue.LINK_STYLE,
      DarkStyleSubStyleCatalogue.TEXT_BOX_STYLE,
      DarkStyleSubStyleCatalogue.TITLE_STYLE,
      DarkStyleSubStyleCatalogue.VALIDATION_LABEL_STYLE);

  private StyleCatalogue() {
  }
}
