/*
 * file:	AlphaServer.java
 * author:	Silvan Wyss
 * month:	2016-08
 * lines:	110
 */

//package declaration
package ch.nolix.system.application;

//own imports
import ch.nolix.common.container.List;
import ch.nolix.common.duplexController.DuplexController;
import ch.nolix.common.duplexController.DuplexControllerListener;
import ch.nolix.common.exception.UnsupportedMethodException;
import ch.nolix.common.module.CentralController;
import ch.nolix.common.module.Module;
import ch.nolix.common.specification.Statement;

//class
/**
 * An alpha server contains applications and listens to clients on a specific port.
 */
public final class ZetaServer extends Module {

	//multiple attribute
	private final List<Application<?>> applications = new List<Application<?>>();
	
	//constructor
	/**
	 * Creates new alpha server that belongs to the given central contorller and listens to clients on the given port.
	 * 
	 * @param centralController
	 * @param port
	 */
	public ZetaServer(final CentralController centralController, final int port) {
		
		//Calls constructor of the base class.
		super(centralController);
		
		new DuplexControllerListener(port, new ZetaServerDuplexControllerTaker(this));
	}
	
	//method
	/**
	 * Adds the given application to this alpha server.
	 * 
	 * @param application
	 * @throws Exception if:
	 * -The given application is null.
	 * -This alpha server contains already an other application with the same name as the given application
	 */
	public void addApplication(final Application<?> application) {
		
		if (containsApplication(application.getName())) {
			throw new RuntimeException("Server contains already an other application with the name '" + application.getName() + "'.");
		}
		
		applications.addAtEnd(application);
	}
	
	//method
	/**
	 * @param name
	 * @return true if this alpha server contains an application with the given name
	 */
	public boolean containsApplication(final String name) {
		return applications.contains(a -> a.hasName(name));
	}
	
	public List<Application<?>> getRefApplications() {
		return applications;
	}

	//method
	/**
	 * @throws UnsupportedMethodException
	 */
	public Object getRawReference(Statement request) {
		throw new UnsupportedMethodException(this, "get raw reference");
	}

	//method
	/**
	 * @throws UnsupportedMethodException
	 */
	public Object getRawData(Statement request) {
		throw new UnsupportedMethodException(this, "get raw data");
	}
	
	//method
	/**
	 * @param name
	 * @return the application with the given name from this alpha server
	 */
	public Application<?> getRefApplicationByName(String name) {
		return applications.getRefFirst(a -> a.hasName(name));
	}

	//method
	/**
	 * @throws UnsupportedMethodException
	 */
	public void run(Statement command) {
		throw new UnsupportedMethodException(this, "get raw reference");
	}
	
	//method
	/**
	 * Lets this alpha server take the given duplex controller.
	 * 
	 * @param duplexController
	 */
	void takeDuplexController(DuplexController duplexController) {
		getRefApplicationByName(duplexController.getData(ClientProtocol.TARGET_APPLICATION_REQUEST).toString())
		.createClient(duplexController);
	}
}
