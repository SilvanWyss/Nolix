//package declaration
package ch.nolix.business.serverdashboardlogic;

import ch.nolix.businessapi.serverdashboardlogicapi.IApplicationSheet;
import ch.nolix.businessapi.serverdashboardlogicapi.IServerDashboardContext;
import ch.nolix.core.container.linkedlist.LinkedList;
import ch.nolix.core.errorcontrol.validator.GlobalValidator;
import ch.nolix.coreapi.containerapi.baseapi.IContainer;
import ch.nolix.system.application.main.Application;
import ch.nolix.system.application.main.BaseServer;
import ch.nolix.system.application.webapplication.WebClient;

//class
public final class ServerDashboardContext implements IServerDashboardContext {
	
	//static method
	public static ServerDashboardContext toServer(final BaseServer server) {
		return new ServerDashboardContext(server);
	}
	
	//attribute
	private final BaseServer server;
	
	//constructor
	private ServerDashboardContext(final BaseServer server) {
		
		GlobalValidator.assertThat(server).thatIsNamed("server").isNotNull();
		
		this.server = server;
	}
	
	//method
	@Override
	public IContainer<IApplicationSheet> getWebApplicationSheets() {
		return getOriGuiApplications().to(this::createApplicationSheetForWebApplication);
	}
	
	//method
	private boolean applicationIsForBackendGuiClients(final Application<?, ?> application) {
		return (application.getClientClass() == WebClient.class);
	}
	
	//method
	private IApplicationSheet createApplicationSheetForWebApplication(
		final Application<WebClient<?>, ?> webApplication
	) {
		return ApplicationSheet.forWebApplication(webApplication);
	}
	
	//method
	@SuppressWarnings("unchecked")
	private IContainer<Application<WebClient<?>, ?>> getOriGuiApplications() {
		
		final var guiApplications = new LinkedList<Application<WebClient<?>, ?>>();
		
		for (final var a : server.getOriApplications().getOriSelected(this::applicationIsForBackendGuiClients)) {
			guiApplications.addAtEnd((Application<WebClient<?>, ?>)a);
		}
		
		return guiApplications;
	}
}
