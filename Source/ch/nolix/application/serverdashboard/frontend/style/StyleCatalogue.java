package ch.nolix.application.serverdashboard.frontend.style;

import ch.nolix.system.element.style.Style;
import ch.nolix.systemapi.elementapi.styleapi.IStyle;

public final class StyleCatalogue {

  public static final IStyle SERVER_DASHBOARD_STYLE = //
  new Style()
    .withSubStyle(
      ServerDashboardStyleSubStyleCatalogue.LAYER_STYLE,
      ServerDashboardStyleSubStyleCatalogue.IMAGE_CONTROL_STYLE,
      ServerDashboardStyleSubStyleCatalogue.OVERALL_CONTAINER_STYLE,
      ServerDashboardStyleSubStyleCatalogue.MAIN_CONTENT_CONTAINER_STYLE,
      ServerDashboardStyleSubStyleCatalogue.TITLE_STYLE,
      ServerDashboardStyleSubStyleCatalogue.LEVEL_1_HEADER_STYLE);

  private StyleCatalogue() {
  }
}
