package ch.nolix.application.serverdashboard.webgui.style;

import ch.nolix.systemapi.elementapi.styleapi.IStyle;

public final class StyleCatalogue {

  public static final IStyle STYLE = new StyleCreator().createServerDashboardStyle();

  private StyleCatalogue() {
  }
}
