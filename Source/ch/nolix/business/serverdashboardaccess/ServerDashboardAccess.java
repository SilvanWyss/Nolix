//package declaration
package ch.nolix.business.serverdashboardaccess;

//own imports
import ch.nolix.businessapi.serverdashboardaccessapi.IApplicationSheet;
import ch.nolix.businessapi.serverdashboardaccessapi.IServerDashboardAccess;
import ch.nolix.core.errorcontrol.validator.GlobalValidator;
import ch.nolix.coreapi.containerapi.IContainer;
import ch.nolix.system.application.guiapplication.BackendGUIClient;
import ch.nolix.system.application.main.Application;
import ch.nolix.system.application.main.Server;

//class
public final class ServerDashboardAccess implements IServerDashboardAccess {
	
	//static method
	public static ServerDashboardAccess toServer(final Server server) {
		return new ServerDashboardAccess(server);
	}
	
	//attribute
	private final Server server;
	
	//constructor
	private ServerDashboardAccess(final Server server) {
		
		GlobalValidator.assertThat(server).thatIsNamed(Server.class).isNotNull();
		
		this.server = server;
	}
	
	//method
	@Override
	public IContainer<IApplicationSheet> getGUIApplicationSheets() {
		return getRefGUIApplications().to(this::createApplicationSheetForGUIApplication);
	}
	
	//method
	private boolean applicationIsForBackendGUIClients(final Application<?, ?> application) {
		return (application.getClientClass() == BackendGUIClient.class);
	}
	
	//method
	private IApplicationSheet createApplicationSheetForGUIApplication(
		final Application<BackendGUIClient<?>, ?> pGUIApplication
	) {
		return ApplicationSheet.forGUIApplicationOnServer(pGUIApplication, server);
	}
	
	//method
	private IContainer<Application<BackendGUIClient<?>, ?>> getRefGUIApplications() {
		return
		server
		.getRefApplications()
		.getRefSelected(this::applicationIsForBackendGUIClients)
		.asContainerWithElementsOfEvaluatedType();
	}
}
