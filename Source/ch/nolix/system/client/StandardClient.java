//package declaration
package ch.nolix.system.client;

//own imports
import ch.nolix.core.duplexController.DuplexController;
import ch.nolix.core.specification.Specification;

//class
/**
 * A standard client is a normal client.
 * 
 * @author Silvan Wyss
 * @month 2015-12
 * @lines 80
 */
public final class StandardClient extends Client<StandardClient> {
	
	//constructor
	/**
	 * Creates new standard client that will connect to the given target application.
	 * 
	 * @param targetApplication
	 * @throws NullArgumentException if the given target application is null.
	 */
	public StandardClient(final Application<StandardClient> targetApplication) {

		//Calls constructor of the base class.
		super(targetApplication);
	}
	
	//constructor
	/**
	 * Creates new standard client that will connect to the given target application on the given port on the machine with the given ip.
	 * 
	 * @param ip
	 * @param port
	 * @param targetApplication
	 * @throws NullArgumentException if the given target application is null.
	 * @throws EmptyArgumentException if the given target application is empty.
	 */	
	public StandardClient(
		final String ip,
		final int port,
		final String targetApplication
	) {
		
		//Calls constructor of the base class.
		super(ip, port, targetApplication);
	}
	
	//constructor
	/**
	 * Creates new standard client that:
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
	 * Creates new standard client with the given duplex controller.
	 * 
	 * @param controller
	 * @throws NullArgumentException if the given duplex controller is null.
	 */
	public StandardClient(final DuplexController duplexController) {
				
		//Calls constructor of the base class.
		super(duplexController);
	}
	
	//constructor
	/**
	 * Creates new standard client with the given duplex controller and initial session.
	 * 
	 * @param controller
	 * @param initialSession
	 * @throws NullArgumentException if the given duplex controller is null.
	 * @throws NullArgumentException if the given initial session is null.
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
	public Specification getData(final String dataMethodRequest) {
		return internal_getRefDuplexController().getData(DATA_METHOD_REQUEST + "(" + dataMethodRequest + ")");
	}
	
	//method
	/**
	 * Runs a run method on the other side of this standard client.
	 * 
	 * @param runMethodCommand
	 */
	public void run(final String runMethodCommand) {
		internal_getRefDuplexController().run(Client.INVOKE_RUN_METHOD_COMMAND + "(" + runMethodCommand + ")");
	}
	
	public void setSession(Session<StandardClient> session) {
		internal_setSessionAndInitializeSession(session);
	}

	//method
	/**
	 * Finishes the initialization of the session of this standard client.
	 */
	protected void internal_finishSessionInitialization() {}
}
