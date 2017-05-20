//package declaration
package ch.nolix.system.application;

//own imports
import ch.nolix.core.centralController.CentralController;
import ch.nolix.core.centralController.Module;
import ch.nolix.core.invalidStateException.UnsupportedMethodException;
import ch.nolix.core.specification.Statement;

//class
/**
 * A zeta server is a module that is a server.
 * 
 * @author Silvan Wyss
 * @month 2016-08
 * @lines 90
 */
public final class ZetaServer extends Module {

	//attribute
	private final Server server;
	
	//constructor
	/**
	 * Creates new zeta server that:
	 * -Belongs to the given central controller.
	 * -Listens to clients on the given port.
	 * 
	 * @param centralController
	 * @param port
	 */
	public ZetaServer(final CentralController centralController, final int port) {
		
		//Calls constructor of the base class.
		super(centralController);
		
		//Creates the server of this zeta server.
		server = new Server(port);
	}
	
	//method
	/**
	 * Adds the given application to this zeta server.
	 * 
	 * @param application
	 * @throws NullArgumentException if the given application is null.
	 * @throws RuntimeException if this zeta server contains already an application with the same name as the given application.
	 */
	public void addApplication(final Application<?> application) {
		server.addApplication(application);
	}
	
	//method
	/**
	 * @return true if this zeta server contains an application with the given name.
	 */
	public boolean containsApplication(final String name) {
		return server.containsApplication(name);
	}

	//method
	/**
	 * @throws UnsupportedMethodException
	 */
	public Object getRawReference(final Statement request) {
		throw new UnsupportedMethodException(this, "get raw reference");
	}

	//method
	/**
	 * @throws UnsupportedMethodException
	 */
	public Object getRawData(final Statement request) {
		throw new UnsupportedMethodException(this, "get raw data");
	}
	
	//method
	/**
	 * @param name
	 * @return the application with the given name from this zeta server.
	 * @throws RuntimeException if this zeta server contains no application with the given name.
	 */
	public Application<?> getRefApplicationByName(final String name) {
		return server.getRefApplicationByName(name);
	}

	//method
	/**
	 * @throws UnsupportedMethodException
	 */
	public void run(final Statement command) {
		throw new UnsupportedMethodException(this, "run");
	}
}
