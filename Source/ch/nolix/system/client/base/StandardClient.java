//package declaration
package ch.nolix.system.client.base;

//own imports
import ch.nolix.core.constant.LowerCaseCatalogue;
import ch.nolix.core.document.chainednode.ChainedNode;
import ch.nolix.core.document.node.Node;
import ch.nolix.core.errorcontrol.invalidargumentexception.ArgumentIsNullException;
import ch.nolix.core.errorcontrol.invalidargumentexception.EmptyArgumentException;
import ch.nolix.core.errorcontrol.invalidargumentexception.InvalidArgumentException;
import ch.nolix.core.net.endpoint3.EndPoint;

//TODO: Create BackendStandardClient and FrontendStandardClient.
//TODO: Create package for StandardClient.
//class
/**
 * A standard client is a normal client.
 * 
 * @author Silvan Wyss
 * @date 2016-01-01
 * @param <AC> is the type of the context of the parent {@link Application} of a {@link StandardClient}.
 */
public final class StandardClient<AC> extends BackendClient<StandardClient<AC>, AC> {
	
	//constructor
	/**
	 * Creates a new standard client that will connect to the given target application.
	 * 
	 * @param targetApplication
	 * @throws ArgumentIsNullException if the given target application is null.
	 */
	public StandardClient(final Application<StandardClient<?>, ?> targetApplication) {
		//TODO: Create BackendStandardClient and FrontendStandardClient.
		//internalConnectTo(targetApplication);
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
		//TODO: Create BackendStandardClient and FrontendStandardClient.
		//internalConnectTo(ip, port);
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
	public StandardClient(final String ip, final int port, final Session<StandardClient<?>, ?> initialSession) {
		//TODO: Create BackendStandardClient and FrontendStandardClient.
		//internalConnectTo(ip, port);
		//internalPush(initialSession);
	}
	
	//constructor
	/**
	 * Creates a new {@link StandardClient}
	 * that will connect to the given target application on the given port on the machine with the given ip.
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
		//TODO: Create BackendStandardClient and FrontendStandardClient.
		//internalConnectTo(ip, port, targetApplication);
	}
	
	//constructor
	/**
	 * Creates a new standard client with the given duplex controller.
	 * 
	 * @param endPoint
	 * @throws ArgumentIsNullException if the given endPoint is null.
	 */
	public StandardClient(final EndPoint endPoint) {
		
		//Calls constructor of the base class.
		internalSetEndPoint(endPoint);
	}
	
	//method
	/**
	 * Resets this client.
	 * 
	 * @return this standard client.
	 */
	public StandardClient<AC> reset() {
		return this;
	}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected Node getDataFromHere(final ChainedNode request) {
		throw new InvalidArgumentException(LowerCaseCatalogue.REQUEST, request, "is not valid");
	}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void runHere(final ChainedNode command) {
		throw new InvalidArgumentException(LowerCaseCatalogue.COMMAND, command, "is not valid");
	}
}
