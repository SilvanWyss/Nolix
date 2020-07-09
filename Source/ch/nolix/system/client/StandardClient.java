//package declaration
package ch.nolix.system.client;

//own import
import ch.nolix.common.endPoint5.EndPoint;

//class
/**
 * A standard client is a normal client.
 * 
 * @author Silvan Wyss
 * @month 2015-12
 * @lines 170
 */
public final class StandardClient extends Client<StandardClient> {
	
	//constructor
	/**
	 * Creates a new standard client that will connect to the given target application.
	 * 
	 * @param targetApplication
	 * @throws ArgumentIsNullException if the given target application is null.
	 */
	public StandardClient(final Application<StandardClient> targetApplication) {

		//Calls constructor of the base class.
		internalConnectTo(targetApplication);
	}
	
	//constructor
	/**
	 * Creates a new standard client that will connect to the arbitrary application
	 * on the given port on the machine with the given ip.
	 * 
	 * @param ip
	 * @param port
	 */
	public StandardClient(String ip, int port) {
		internalConnectTo(ip, port);
	}
	
	//constructor
	/**
	 * Creates a new {@link StandardClient} that will connect to the main application
	 * on the given port on the machine with the given ip.
	 * The {@link StandardClient} will have the given initialSession
	 * 
	 * @param ip
	 * @param port
	 * @param initialSession
	 * @throws ArgumentIsNullException if the given initialSession is null.
	 */
	public StandardClient(final String ip, final int port, final Session<StandardClient> initialSession) {
		internalConnectTo(ip, port);
		push(initialSession);
	}
	
	//constructor
	/**
	 * Creates a new standard client that will connect to the given target application on the given port on the machine with the given ip.
	 * 
	 * @param ip
	 * @param port
	 * @param targetApplication
	 * @throws ArgumentIsNullException if the given target application is null.
	 * @throws EmptyArgumentException if the given target application is empty.
	 */	
	public StandardClient(
		final String ip,
		final int port,
		final String targetApplication
	) {
		
		//Calls constructor of the base class.
		internalConnectTo(ip, port, targetApplication);
	}
	
	//constructor
	/**
	 * Creates a new standard client with the given duplex controller.
	 * 
	 * @param controller
	 * @throws NullArgumentException if the given duplex controller is null.
	 */
	public StandardClient(final EndPoint endPoint) {
		
		//Calls constructor of the base class.
		internalSetEndPoint(endPoint);
	}
	
	//method
	/**
	 * @return the current {@link Session} of the current {@link StandardClient}.
	 */
	public StandardClientSession getRefCurrentSession() {
		return (StandardClientSession)internalGetRefCurrentSession();
	}
	
	//method
	/**
	 * Resets this client.
	 * 
	 * @return this standard client.
	 */
	public StandardClient reset() {
		return this;
	}
}
