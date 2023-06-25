//package declaration
package ch.nolix.business.serverdashboardapplication;

import ch.nolix.businessapi.serverdashboardlogicapi.IApplicationSheet;
import ch.nolix.businessapi.serverdashboardlogicapi.IServerDashboardContext;
import ch.nolix.coreapi.containerapi.baseapi.IContainer;
import ch.nolix.system.application.webapplication.WebClientSession;
import ch.nolix.system.webgui.linearcontainer.FloatContainer;
import ch.nolix.systemapi.webguiapi.containerapi.ContainerRole;
import ch.nolix.systemapi.webguiapi.mainapi.IControl;

//class
final class ServerDashboardSession extends WebClientSession<IServerDashboardContext> {
	
	//static attribute
	private static final ServerDashboardStyleCreator SERVER_DASHBOARD_STYLE_CREATOR = new ServerDashboardStyleCreator();
	
	//method
	@Override
	protected void initialize() {
		getOriGui()
		.setStyle(SERVER_DASHBOARD_STYLE_CREATOR.createServerDashboardStyle())
		.pushLayerWithRootControl(createApplicationSetControl());
	}
	
	//method
	private IControl<?, ?> createApplicationSetControl() {
		
		final var floatContainer = new FloatContainer().setRole(ContainerRole.MAIN_CONTENT_CONTAINER);
		
		for (final var as : getGuiApplicationSheets()) {
			floatContainer.addControl(
				WebApplicationControlFactory.INSTANCE.createWebApplicationControl(as, getOriParentClient().getConnectionSecurityLevel())
			);
		}
		
		return floatContainer;
	}
	
	//method
	private IContainer<IApplicationSheet> getGuiApplicationSheets() {
		return
		getOriApplicationContext()
		.getGuiApplicationSheets()
		.getOriOther(
			as -> as.getApplicationInstanceTarget().getApplicationInstanceName().equals(getApplicationName())
		);
	}
}
