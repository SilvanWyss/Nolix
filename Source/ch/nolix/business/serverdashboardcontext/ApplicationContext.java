//package declaration
package ch.nolix.business.serverdashboardcontext;

import ch.nolix.businessapi.serverdashboardcontextapi.IApplicationSheet;
import ch.nolix.businessapi.serverdashboardcontextapi.IApplicationContext;
import ch.nolix.core.container.main.LinkedList;
import ch.nolix.core.errorcontrol.validator.GlobalValidator;
import ch.nolix.coreapi.containerapi.mainapi.IContainer;
import ch.nolix.system.application.main.Application;
import ch.nolix.system.application.main.Server;
import ch.nolix.system.application.webapplication.BackendWebClient;

//class
public final class ApplicationContext implements IApplicationContext {
	
	//static method
	public static ApplicationContext toServer(final Server server) {
		return new ApplicationContext(server);
	}
	
	//attribute
	private final Server server;
	
	//constructor
	private ApplicationContext(final Server server) {
		
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
		return (application.getClientClass() == BackendWebClient.class);
	}
	
	//method
	private IApplicationSheet createApplicationSheetForGUIApplication(
		final Application<BackendWebClient<?>, ?> pGUIApplication
	) {
		return ApplicationSheet.forGUIApplicationOnServer(pGUIApplication, server);
	}
	
	//method
	@SuppressWarnings("unchecked")
	private IContainer<Application<BackendWebClient<?>, ?>> getRefGUIApplications() {
		
		final var lGUIApplications = new LinkedList<Application<BackendWebClient<?>, ?>>();
		
		for (final var a : server.getRefApplications().getRefSelected(this::applicationIsForBackendGUIClients)) {
			lGUIApplications.addAtEnd((Application<BackendWebClient<?>, ?>)a);
		}
		
		return lGUIApplications;
	}
}
