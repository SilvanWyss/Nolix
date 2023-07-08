//package declaration
package ch.nolix.tech.serverdashboardlogic;

import ch.nolix.core.container.linkedlist.LinkedList;
import ch.nolix.core.errorcontrol.validator.GlobalValidator;
import ch.nolix.core.programatom.name.LowerCaseCatalogue;
import ch.nolix.coreapi.containerapi.baseapi.IContainer;
import ch.nolix.system.application.main.Application;
import ch.nolix.system.application.main.BaseServer;
import ch.nolix.system.application.webapplication.WebClient;
import ch.nolix.techapi.serverdashboardlogicapi.IServerDashboardContext;
import ch.nolix.techapi.serverdashboardlogicapi.IWebApplicationSheet;

//class
public final class ServerDashboardContext implements IServerDashboardContext {
	
	//static method
	public static ServerDashboardContext forServer(final BaseServer server) {
		return new ServerDashboardContext(server);
	}
	
	//attribute
	private final BaseServer server;
	
	//constructor
	private ServerDashboardContext(final BaseServer server) {
		
		GlobalValidator.assertThat(server).thatIsNamed(LowerCaseCatalogue.SERVER).isNotNull();
		
		this.server = server;
	}
	
	//method
	@Override
	public IContainer<IWebApplicationSheet> getWebApplicationSheets() {
		return getOriWebApplications().to(WebApplicationSheet::forWebApplication);
	}
	
	//method
	private IContainer<Application<WebClient<?>, ?>> getOriWebApplications() {
		
		final var webApplications = new LinkedList<Application<WebClient<?>, ?>>();
		
		for (final var a : server.getOriApplications()) {
			if (isWebApplication(a)) {
				
				@SuppressWarnings("unchecked")
				final var webApplication = (Application<WebClient<?>, ?>)a;
				
				webApplications.addAtEnd(webApplication);
			}
		}
		
		return webApplications;
	}
	
	//method
	private boolean isWebApplication(final Application<?, ?> application) {
		return
		application != null
		&& application.getClientClass() == WebClient.class;
	}
}
