package ch.nolix.tech.serverdashboardapplication.view;

import ch.nolix.systemapi.elementapi.styleapi.IStyle;

public final class ServerDashboardStyleCatalogue {

  public static final IStyle SERVER_DASHBOARD_STYLE = new ServerDashboardStyleCreator().createServerDashboardStyle();

  private ServerDashboardStyleCatalogue() {
  }
}
