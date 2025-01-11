package ch.nolix.application.serverdashboard.frontend.style;

import ch.nolix.system.element.style.Style;
import ch.nolix.systemapi.elementapi.styleapi.IStyle;

public final class StyleCatalog {

  public static final IStyle SERVER_DASHBOARD_STYLE = //
  new Style()
    .withSubStyle(
      ServerDashboardStyleSubStyleCatalog.LAYER_STYLE,
      ServerDashboardStyleSubStyleCatalog.IMAGE_CONTROL_STYLE,
      ServerDashboardStyleSubStyleCatalog.OVERALL_CONTAINER_STYLE,
      ServerDashboardStyleSubStyleCatalog.MAIN_CONTENT_CONTAINER_STYLE,
      ServerDashboardStyleSubStyleCatalog.TITLE_STYLE,
      ServerDashboardStyleSubStyleCatalog.LEVEL_1_HEADER_STYLE);

  private StyleCatalog() {
  }
}
