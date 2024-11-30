package ch.nolix.application.serverdashboard.webgui.session;

import ch.nolix.application.serverdashboard.webgui.style.StyleCatalogue;
import ch.nolix.application.serverdashboard.webgui.webapplicationcomponent.WebApplicationComponent;
import ch.nolix.application.serverdashboard.webgui.webapplicationcomponent.WebApplicationController;
import ch.nolix.applicationapi.serverdashboardapi.contextapi.IServerDashboardContext;
import ch.nolix.applicationapi.serverdashboardapi.datamodelapi.IWebApplicationInfo;
import ch.nolix.coreapi.containerapi.baseapi.IContainer;
import ch.nolix.system.application.webapplication.WebClientSession;
import ch.nolix.system.webgui.atomiccontrol.Label;
import ch.nolix.system.webgui.linearcontainer.FloatContainer;
import ch.nolix.system.webgui.linearcontainer.VerticalStack;
import ch.nolix.systemapi.applicationapi.componentapi.IComponent;
import ch.nolix.systemapi.webguiapi.atomiccontrolapi.LabelRole;
import ch.nolix.systemapi.webguiapi.basecontainerapi.ContainerRole;

public final class ServerDashboardSession extends WebClientSession<IServerDashboardContext> {

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
      .setStyle(StyleCatalogue.STYLE);
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
