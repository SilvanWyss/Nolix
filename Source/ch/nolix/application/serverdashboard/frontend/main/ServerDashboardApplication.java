package ch.nolix.application.serverdashboard.frontend.main;

import ch.nolix.application.serverdashboard.frontend.session.ServerDashboardSession;
import ch.nolix.applicationapi.serverdashboardapi.frontendapi.mainapi.IServerDashboardService;
import ch.nolix.system.application.main.Application;
import ch.nolix.system.application.main.BaseServer;
import ch.nolix.system.application.webapplication.WebClient;

public final class ServerDashboardApplication
extends Application<WebClient<IServerDashboardService>, IServerDashboardService> {

  public static final String APPLICATION_NAME = "Server Dashboard";

  private ServerDashboardApplication(final IServerDashboardService serverDashboardService) {
    super(serverDashboardService);
  }

  public static ServerDashboardApplication forServer(final BaseServer<?> server) {
    return new ServerDashboardApplication(ServerDashboardService.forServer(server));
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
