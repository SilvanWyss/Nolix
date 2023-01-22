//package declaration
package ch.nolix.business.serverdashboardapplication;

import ch.nolix.businessapi.serverdashboardcontextapi.IApplicationSheet;
import ch.nolix.businessapi.serverdashboardcontextapi.IApplicationContext;
import ch.nolix.coreapi.containerapi.mainapi.IContainer;
import ch.nolix.system.application.webapplication.BackendWebClientSession;
import ch.nolix.system.webgui.linearcontainer.FloatContainer;
import ch.nolix.systemapi.webguiapi.containerapi.ContainerRole;
import ch.nolix.systemapi.webguiapi.mainapi.IControl;

//class
final class ServerDashboardSession extends BackendWebClientSession<IApplicationContext> {
	
	//method
	@Override
	protected void initialize() {
		getRefGUI()
		.setStyle(ServerDashboardStyleCreator.INSTANCE.createServerDashboardStyle())
		.pushLayerWithRootControl(createApplicationSetControl());
	}
	
	//method
	private IControl<?, ?> createApplicationSetControl() {
		
		final var floatContainer = new FloatContainer().setRole(ContainerRole.MAIN_CONTENT_CONTAINER);
		
		for (final var as : getGUIApplicationSheets()) {
			floatContainer.addControl(
				WebApplicationControlFactory.INSTANCE.createWebApplicationControl(as)
			);
		}
		
		return floatContainer;
	}
	
	//method
	private IContainer<IApplicationSheet> getGUIApplicationSheets() {
		return
		getRefApplicationContext()
		.getGUIApplicationSheets()
		.getRefUnselected(as -> as.getApplicationName().equals(getApplicationName()));
	}
}
