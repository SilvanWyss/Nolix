//package declaration
package ch.nolix.business.serverdashboard;

//own imports
import ch.nolix.businessapi.serverdashboardaccessapi.IApplicationSheet;
import ch.nolix.businessapi.serverdashboardaccessapi.IServerDashboardAccess;
import ch.nolix.coreapi.containerapi.mainapi.IContainer;
import ch.nolix.system.application.webapplication.BackendWebClientSession;
import ch.nolix.system.webgui.linearcontainer.VerticalStack;
import ch.nolix.systemapi.guiapi.containercontrolproperty.ContainerRole;
import ch.nolix.systemapi.webguiapi.mainapi.IControl;

//TODO: Adjust names.
//TODO: Create FloatContainer.
//class
final class ServerDashboardSession extends BackendWebClientSession<IServerDashboardAccess> {
	
	//method
	@Override
	protected void initialize() {
		getRefGUI()
		.setStyle(ServerDashboardStyleCreator.INSTANCE.createServerDashboardStyle())
		.pushLayerWithRootControl(createApplicationSetWidget());
	}
	
	//method
	private IControl<?, ?> createApplicationSetWidget() {
		
		final var floatContainer = new VerticalStack().setRole(ContainerRole.MAIN_CONTENT_CONTAINER);
		
		for (final var as : getGUIApplicationSheets()) {
			floatContainer.addControl(
				GUIApplicationWidgetFactory.INSTANCE.createGUIApplicationWidget(as)
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
