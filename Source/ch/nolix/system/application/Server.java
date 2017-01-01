/*
 * file:	Server.java
 * author:	Silvan Wyss
 * month:	2016-10
 * lines:	80
 */

//package declaration
package ch.nolix.system.application;

//own imports
import ch.nolix.common.container.List;
import ch.nolix.common.duplexController.DuplexController;
import ch.nolix.common.duplexController.DuplexControllerListener;

//class
/**
 * A server contains applications and listens to clients on a specific port.
 */
public final class Server {

	//attribute
	private final DuplexControllerListener duplexControllerListener;
	
	//multiple attribute
	private final List<Application<?>> applications = new List<Application<?>>();
	
	/**
	 * Creates new server that listens to clients on the given port.
	 * 
	 * @param port
	 */
	public Server(final int port) {	
		duplexControllerListener = new DuplexControllerListener(port, new ServerDuplexControllerTaker(this));
	}
	
	public Server(final int port, Application<?>... applications) {
		
		this(port);
		
		addApplication(applications);
	}
	
	//method
	/**
	 * Adds the given application to this server.
	 * 
	 * @param application
	 * @throws Exception if:
	 * -The given application is null.
	 * -This server contains already an other application with the same name as the given application.
	 */
	public final void addApplication(final Application<?> application) {
		
		if (containsApplication(application.getName())) {
			throw new RuntimeException("Server contains already an other application with the name '" + application.getName() + "'.");
		}
		
		applications.addAtEnd(application);
	}
	
	public final void addApplication(final Application<?>... applications) {
		for (Application<?> a: applications) {
			addApplication(a);
		}
	}
	
	//method
	/**
	 * @param name
	 * @return true if this server contains an application with the given name
	 */
	public final boolean containsApplication(final String name) {
		return applications.contains(a -> a.hasName(name));
	}
	
	//method
	/**
	 * @param name
	 * @return the application with the given name from this server
	 * @throws Exception if this server contains no application with the given name
	 */
	public final Application<?> getRefApplicationByName(String name) {
		return applications.getRefFirst(a -> a.hasName(name));
	}
	
	public void stop_() {
		duplexControllerListener.abort();
	}
	
	//package-visible method
	/**
	 * Lets this server take the given duplex controller.
	 * 
	 * @param duplexController
	 */
	void takeDuplexController(DuplexController duplexController) {
		getRefApplicationByName(duplexController.getData(ClientProtocol.TARGET_APPLICATION_REQUEST).toString())
		.createClient(duplexController);
	}
}
