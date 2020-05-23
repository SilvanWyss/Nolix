//package declaration
package ch.nolix.system.client;

//own imports
import ch.nolix.common.constant.PortCatalogue;
import ch.nolix.common.endPoint5.EndPoint;
import ch.nolix.common.localComputer.LocalComputer;

//class
/**
 * A {@link NetServer} is a {@link Server} that listens to net {@link Client}s on a specific port.
 * 
 * @author Silvan Wyss
 * @month 2017-09
 * @lines 140
 */
public final class NetServer extends Server {
	
	//constant
	public static final int DEFAULT_PORT = PortCatalogue.DE_FACTO_HTTP_PORT;
	
	//attribute
	private ch.nolix.common.endPoint5.NetServer internalNetServer;
	
	//constructor
	/**
	 * Creates a new {@link NetServer} that will listen to net {@link Client}s on the default port.
	 * 
	 * @param port
	 * @throws ArgumentIsOutOfRangeException if the given port is not in [0,65535].
	 */
	public NetServer() {
		
		//Calls other constructor.
		this(DEFAULT_PORT);
	}
	
	//constructor
	/**
	 * Creates a new {@link NetServer} that will listen to net {@link Client}s on the default port.
	 * The {@link NetServer} will have the given mainApplication.
	 * 
	 * @param mainApplication.
	 * @throws ArgumentIsNullException if the given mainApplication is null.
	 */
	public NetServer(final Application<?> mainApplication) {
		
		//Calls other constructor.
		this();
		
		addMainApplication(mainApplication);
	}
	
	//constructor
	/**
	 * Creates a new {@link NetServer} that will listen to net {@link Client}s on the given port.
	 * 
	 * @param port
	 * @throws ArgumentIsOutOfRangeException if the given port is not in [0,65535].
	 */
	public NetServer(final int port) {
		
		//Creates the internalNetServer of the current NetServer.
		internalNetServer =
		new ch.nolix.common.endPoint5.NetServer(
			port, 
			new NetServerHTTPMessage(LocalComputer.getLANIP(), port).toString()
		);
		
		internalNetServer.addMainEndPointTaker(new NetServerSubDuplexControllerTaker(this));
		
		//Creates a close dependency between the current NetServer and its internalNetServer.
		createCloseDependency(internalNetServer);
	}
	
	//constructor
	/**
	 * Creates a new {@link NetServer} that will listen to net {@link Client}s on the given port.
	 * The {@link NetServer} will have the given mainApplication.
	 * 
	 * @param port
	 * @param mainApplication.
	 * @throws ArgumentIsOutOfRangeException if the given port is not in [0,65535].
	 * @throws ArgumentIsNullException if the given mainApplication is null.
	 */
	public NetServer(final int port, final Application<?> mainApplication) {
		
		//Calls other constructor.
		this(port);
		
		addMainApplication(mainApplication);
	}
	
	//constructor
	/**
	 * Creates a new {@link NetServer} that will listen to net {@link Client}s on the default port.
	 * The {@link NetServer} will have a main application with the given name, clientClass and initialSessionClass.
	 * 
	 * @param name
	 * @param clientClass
	 * @param initialSessionClass
	 * @throws ArgumentIsNullException if the given name is null.
	 * @throws InvalidArgumentException if the given name is blank.
	 * @throws ArgumentIsNullException if the given clientClass is null.
	 * @throws ArgumentIsNullException if the given initialSessionClass is null.
	 */
	public <C extends Client<C>> NetServer(
		final String name,
		final Class<C> clientClass,
		final Class<?> initialSessionClass
	) {
		
		//Calls other constructor.
		this(new Application<C>(name, clientClass, initialSessionClass));
	}
	
	//method
	/**
	 * @return the port of the current {@link NetServer}.
	 */
	public int getPort() {
		return internalNetServer.getPort();
	}
	
	//method
	/**
	 * Lets the current {@link NetServer} take the given endPoint.
	 * 
	 * @param endPoint
	 */
	void takeEndPoint(final EndPoint endPoint) {
		
		//Handles the case that the given endPoint does not have a target.
		if (!endPoint.hasTarget()) {
			getRefMainApplication().takeEndPoint(endPoint);
		}
		
		//Handles the case that the given endPoint has a target.
		else {
			getRefApplicationByName(endPoint.getTarget()).takeEndPoint(endPoint);
		}
	}
}
