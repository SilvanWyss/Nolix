//package declaration
package ch.nolix.system.client;

//own imports
import ch.nolix.common.endPoint5.EndPoint;
import ch.nolix.common.node.Node;

//class
/**
 * A standard client is a normal client.
 * 
 * @author Silvan Wyss
 * @month 2015-12
 * @lines 150
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
		internal_connectTo(targetApplication);
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
		internal_connectTo(ip, port);
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
		internal_connectTo(ip, port, targetApplication);
	}
	
	//constructor
	/**
	 * Creates a new standard client that:
	 * -Connects to the given target application on the given port on the machine with the given ip.
	 * -Has the given initial session.
	 * 
	 * @param ip
	 * @param port
	 * @param targetApplication
	 * @param initialSession
	 */
	/*
	public StandardClient(
		final String ip,
		final int port,
		final String targetApplication,
		final Session<StandardClient> initialSession
	) {
		
		//Calls constructor of the base class.
		super(ip, port, targetApplication, initialSession);
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
		internal_setEndPoint(endPoint);
	}

	//constructor
	/**
	 * Creates a new standard client with the given duplex controller and initial session.
	 * 
	 * @param controller
	 * @param initialSession
	 * @throws ArgumentIsNullException if the given duplex controller is null.
	 * @throws ArgumentIsNullException if the given initial session is null.
	 */
	/*
	public StandardClient(final Controller controller, final Session<StandardClient> initialSession) {
		
		super(controller, initialSession);
	}
	
	//method
	/**
	 * @param request
	 * @return the data the given data method request requests from a data method of the other side of this standard client.
	 */
	public Node getData(final String dataMethodRequest) {
		
		//TODO
		return null;
		
		/*
		return
		internal_getDataFromCounterpart(SESSION_USER_DATA_METHOD_HEADER + "(" + dataMethodRequest + ")");
		*/
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
	
	//method
	/**
	 * Runs a run method on the other side of this standard client.
	 * 
	 * @param runMethodCommand
	 */
	public void run(final String runMethodCommand) {
		
		//TODO
		/*
		internal_runOnCounterpart(
			Protocol.SESSION_HEADER + "." + Protocol.RUN_METHOD_HEADER + "(" + runMethodCommand + ")"
		);
		*/
	}
}
