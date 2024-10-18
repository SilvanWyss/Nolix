//package declaration
package ch.nolix.tech.serverdashboardapplication.view;

//own imports
import ch.nolix.coreapi.containerapi.baseapi.IContainer;
import ch.nolix.system.application.webapplication.WebClientSession;
import ch.nolix.system.webgui.atomiccontrol.Label;
import ch.nolix.system.webgui.linearcontainer.FloatContainer;
import ch.nolix.system.webgui.linearcontainer.VerticalStack;
import ch.nolix.systemapi.applicationapi.componentapi.IComponent;
import ch.nolix.systemapi.webguiapi.atomiccontrolapi.LabelRole;
import ch.nolix.systemapi.webguiapi.basecontainerapi.ContainerRole;
import ch.nolix.tech.serverdashboardapplication.webapplicationcomponent.WebApplicationComponent;
import ch.nolix.tech.serverdashboardapplication.webapplicationcomponent.WebApplicationController;
import ch.nolix.techapi.serverdashboardapi.IServerDashboardContext;
import ch.nolix.techapi.serverdashboardapi.IWebApplicationSheet;

//class
public final class ServerDashboardSession extends WebClientSession<IServerDashboardContext> {

  //method
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
      .setStyle(ServerDashboardStyleCatalogue.SERVER_DASHBOARD_STYLE);
  }

  //method
  private IContainer<IComponent> createApplicationComponents() {
    return getWebApplicationSheets()
      .to(was -> new WebApplicationComponent(new WebApplicationController(was), this));
  }

  //method
  private IContainer<IWebApplicationSheet> getWebApplicationSheets() {
    return getStoredApplicationContext()
      .getWebApplicationSheets()
      .getStoredOthers(
        as -> as.getApplicationInstanceTarget().getApplicationInstanceName().equals(getApplicationName()));
  }
}
