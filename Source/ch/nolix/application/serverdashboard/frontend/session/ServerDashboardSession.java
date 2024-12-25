package ch.nolix.application.serverdashboard.frontend.session;

import ch.nolix.application.serverdashboard.frontend.style.StyleCatalogue;
import ch.nolix.application.serverdashboard.frontend.webapplicationcomponent.WebApplicationComponent;
import ch.nolix.application.serverdashboard.frontend.webapplicationcomponent.WebApplicationController;
import ch.nolix.applicationapi.serverdashboardapi.backendapi.datamodelapi.IWebApplicationInfo;
import ch.nolix.applicationapi.serverdashboardapi.frontendapi.mainapi.IServerDashboardService;
import ch.nolix.coreapi.containerapi.baseapi.IContainer;
import ch.nolix.system.application.webapplication.WebClientSession;
import ch.nolix.system.webgui.atomiccontrol.Label;
import ch.nolix.system.webgui.linearcontainer.FloatContainer;
import ch.nolix.system.webgui.linearcontainer.VerticalStack;
import ch.nolix.systemapi.applicationapi.componentapi.IComponent;
import ch.nolix.systemapi.webguiapi.atomiccontrolapi.LabelRole;
import ch.nolix.systemapi.webguiapi.basecontainerapi.ContainerRole;

public final class ServerDashboardSession extends WebClientSession<IServerDashboardService> {

  @Override
  protected void initialize() {
    getStoredGui()
      .pushLayerWithRootControl(
        new VerticalStack()
          .setRole(ContainerRole.OVERALL_CONTAINER)
          .addControl(
            new Label()
              .setRole(LabelRole.TITLE)
              .setText(getApplicationName()),
            new FloatContainer()
              .setRole(ContainerRole.MAIN_CONTENT_CONTAINER)
              .addControls(createApplicationComponents())))
      .setStyle(StyleCatalogue.SERVER_DASHBOARD_STYLE);
  }

  private IContainer<IComponent> createApplicationComponents() {
    return getWebApplicationSheets()
      .to(was -> new WebApplicationComponent(new WebApplicationController(was), this));
  }

  private IContainer<IWebApplicationInfo> getWebApplicationSheets() {
    return getStoredApplicationContext()
      .getWebApplicationInfos()
      .getStoredOthers(
        as -> as.getApplicationInstanceTarget().getApplicationInstanceName().equals(getApplicationName()));
  }
}
