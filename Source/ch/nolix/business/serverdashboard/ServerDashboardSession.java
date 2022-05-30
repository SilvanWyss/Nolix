//package declaration
package ch.nolix.business.serverdashboard;

//own imports
import ch.nolix.businessapi.serverdashboardaccessapi.IApplicationSheet;
import ch.nolix.businessapi.serverdashboardaccessapi.IServerDashboardAccess;
import ch.nolix.core.container.IContainer;
import ch.nolix.system.application.guiapplication.BackendGUIClientSession;
import ch.nolix.system.gui.containerwidget.ContainerRole;
import ch.nolix.system.gui.containerwidget.FloatContainer;
import ch.nolix.system.gui.widget.Widget;

//class
final class ServerDashboardSession extends BackendGUIClientSession<IServerDashboardAccess> {
	
	//method
	@Override
	protected void initialize() {
		getRefGUI()
		.setConfiguration(ServerDashboardLookCreator.INSTANCE.createServerDashboardLook())
		.pushLayerWithWidget(createApplicationSetWidget());
	}
	
	//method
	private Widget<?, ?> createApplicationSetWidget() {
		
		final var floatContainer = new FloatContainer().setRole(ContainerRole.MAIN_CONTENT_CONTAINER);
		
		for (final var as : getGUIApplicationSheets()) {
			floatContainer.addWidget(
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
