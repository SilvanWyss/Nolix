package ch.nolix.template.webgui.style;

import ch.nolix.systemapi.elementapi.styleapi.IStyle;

public final class StyleCatalogue {

  public static final IStyle DARK_STYLE = new DarkStyleCreator().createDarkStyle();

  private StyleCatalogue() {
  }
}
