package ch.nolix.template.webgui.style;

import ch.nolix.systemapi.style.model.IStyle;
import ch.nolix.template.webgui.colormode.ColorModeCatalog;
import ch.nolix.template.webgui.shapemode.ShapeModeCatalog;

public final class StyleCatalog {
  public static final IStyle DARK_EDGE_STYLE = ShapeModeCatalog.EDGE_STYLE.withStyle(ColorModeCatalog.DARK_MODE);

  public static final IStyle PARCHMENT_EDGE_STYLE = //
  ShapeModeCatalog.EDGE_STYLE.withStyle(ColorModeCatalog.PARCHMENT_MODE);

  private StyleCatalog() {
  }
}
