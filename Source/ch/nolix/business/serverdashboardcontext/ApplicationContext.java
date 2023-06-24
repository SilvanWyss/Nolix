//package declaration
package ch.nolix.business.serverdashboardcontext;

//own imports
import ch.nolix.businessapi.serverdashboardcontextapi.IApplicationContext;
import ch.nolix.businessapi.serverdashboardcontextapi.IApplicationSheet;
import ch.nolix.core.container.linkedlist.LinkedList;
import ch.nolix.core.errorcontrol.validator.GlobalValidator;
import ch.nolix.coreapi.containerapi.baseapi.IContainer;
import ch.nolix.system.application.main.Application;
import ch.nolix.system.application.main.BaseServer;
import ch.nolix.system.application.webapplication.WebClient;

//class
public final class ApplicationContext implements IApplicationContext {
	
	//static method
	public static ApplicationContext toServer(final BaseServer server) {
		return new ApplicationContext(server);
	}
	
	//attribute
	private final BaseServer server;
	
	//constructor
	private ApplicationContext(final BaseServer server) {
		
		GlobalValidator.assertThat(server).thatIsNamed("server").isNotNull();
		
		this.server = server;
	}
	
	//method
	@Override
	public IContainer<IApplicationSheet> getGuiApplicationSheets() {
		return getOriGuiApplications().to(this::createApplicationSheetForGuiApplication);
	}
	
	//method
	private boolean applicationIsForBackendGuiClients(final Application<?, ?> application) {
		return (application.getClientClass() == WebClient.class);
	}
	
	//method
	private IApplicationSheet createApplicationSheetForGuiApplication(
		final Application<WebClient<?>, ?> guiApplication
	) {
		return ApplicationSheet.forGuiApplicationOnServer(guiApplication, server);
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
