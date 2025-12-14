package ch.nolix.template.webgui.style;

import ch.nolix.systemapi.style.model.IStyle;
import ch.nolix.template.webgui.colormode.ColorModeCatalog;
import ch.nolix.template.webgui.shapemode.ShapeModeCatalog;

public final class StyleCatalog {
  public static final IStyle DARK_EDGE_STYLE = ColorModeCatalog.DARK_MODE.withStyle(ShapeModeCatalog.EDGE_STYLE);

  public static final IStyle PARCHMENT_EDGE_STYLE = //
  ColorModeCatalog.PARCHMENT_MODE.withStyle(ShapeModeCatalog.EDGE_STYLE);

  private StyleCatalog() {
  }
}
