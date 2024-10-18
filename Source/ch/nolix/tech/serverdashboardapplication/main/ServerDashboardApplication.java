package ch.nolix.tech.serverdashboardapplication.main;

import ch.nolix.system.application.main.Application;
import ch.nolix.system.application.main.BaseServer;
import ch.nolix.system.application.webapplication.WebClient;
import ch.nolix.tech.serverdashboard.ServerDashboardContext;
import ch.nolix.tech.serverdashboardapplication.view.ServerDashboardSession;
import ch.nolix.techapi.serverdashboardapi.IServerDashboardContext;

public final class ServerDashboardApplication
extends Application<WebClient<IServerDashboardContext>, IServerDashboardContext> {

  public static final String APPLICATION_NAME = "Server Dashboard";

  private ServerDashboardApplication(final IServerDashboardContext serverDashboardContext) {
    super(serverDashboardContext);
  }

  public static ServerDashboardApplication forServer(final BaseServer<?> server) {
    return new ServerDashboardApplication(ServerDashboardContext.forServer(server));
  }

  @Override
  public String getApplicationName() {
    return APPLICATION_NAME;
  }

  @Override
  protected Class<?> getInitialSessionClass() {
    return ServerDashboardSession.class;
  }
}
