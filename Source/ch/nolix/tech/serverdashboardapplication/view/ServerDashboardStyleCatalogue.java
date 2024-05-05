//package declaration
package ch.nolix.tech.serverdashboardapplication.view;

//own imports
import ch.nolix.systemapi.elementapi.styleapi.IStyle;

//class
public final class ServerDashboardStyleCatalogue {

  //constant
  public static final IStyle SERVER_DASHBOARD_STYLE = new ServerDashboardStyleCreator().createServerDashboardStyle();

  //constructor
  private ServerDashboardStyleCatalogue() {
  }
}
