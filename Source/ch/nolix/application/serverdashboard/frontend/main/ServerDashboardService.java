package ch.nolix.application.serverdashboard.frontend.main;

import ch.nolix.application.serverdashboard.backend.datamodel.WebApplicationInfo;
import ch.nolix.application.serverdashboard.backend.tool.WebApplicationExtractor;
import ch.nolix.applicationapi.serverdashboardapi.backendapi.datamodelapi.IWebApplicationInfo;
import ch.nolix.applicationapi.serverdashboardapi.frontendapi.mainapi.IServerDashboardService;
import ch.nolix.core.errorcontrol.validator.GlobalValidator;
import ch.nolix.coreapi.containerapi.baseapi.IContainer;
import ch.nolix.coreapi.programatomapi.variableapi.LowerCaseVariableCatalog;
import ch.nolix.system.application.main.AbstractServer;
import ch.nolix.systemapi.applicationapi.mainapi.IApplication;

public final class ServerDashboardService implements IServerDashboardService {

  private static final WebApplicationExtractor WEB_APPLICATION_EXTRACTOR = new WebApplicationExtractor();

  private final AbstractServer<?> server;

  private ServerDashboardService(final AbstractServer<?> server) {

    GlobalValidator.assertThat(server).thatIsNamed(LowerCaseVariableCatalog.SERVER).isNotNull();

    this.server = server;
  }

  public static ServerDashboardService forServer(final AbstractServer<?> server) {
    return new ServerDashboardService(server);
  }

  @Override
  public IContainer<IWebApplicationInfo> getWebApplicationInfos() {
    return getStoredWebApplications().to(WebApplicationInfo::forWebApplication);
  }

  private IContainer<? extends IApplication<Object>> getStoredWebApplications() {
    return WEB_APPLICATION_EXTRACTOR.getStoredWebApplicationsOfServer(server);
  }
}
