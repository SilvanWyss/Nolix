//package declaration
package ch.nolix.core.duplexController;

//own imports
import ch.nolix.core.endPoint3.NetEndPoint;
import ch.nolix.core.invalidStateException.InvalidStateException;

//class
/**
 * A net server is a server that listens to net duplex controllers on a specific port.
 * 
 * @author Silvan Wyss
 * @month 2016-10
 * @lines 80
 */
public final class NetServer extends Server {
	
	//attribute
	private ch.nolix.core.endPoint3.NetServer internalNetServer;
	
	//constructor
	/**
	 * Creates new net server with the given port.
	 * 
	 * @param port
	 */
	public NetServer(final int port) {
		
		//Creates the internal server of this net server.
		internalNetServer = new ch.nolix.core.endPoint3.NetServer(port);
		
		//Creates an abort dependency between this net server and its internal net server.
		createAbortDependency(internalNetServer);
	}
	
	//method
	/**
	 * Adds the given duplex controller to this net server.
	 * 
	 * @param duplexControllerTaker
	 * @throws InvalidStateException if this net server contains a duplex controller
	 * with the same name the given duplex controller has
	 */
	public void addDuplexControllerTaker(final IDuplexControllerTaker duplexControllerTaker) {
		
		//Calls method of the base class.
		super.addDuplexControllerTaker(duplexControllerTaker);
		
		internalNetServer.addEndPointTaker(new EndPointTaker(duplexControllerTaker));
	}
	
	//method
	/**
	 * Removes the duplex controller taker with the given name from this net server.
	 * 
	 * @param name
	 * @throws InvalidArgumentException
	 * if this net server contains no duplex controller taker with the given name.
	 */
	public void removeDuplexControllerTaker(final String name) {
		
		//Calls method of the base class.
		super.removeDuplexControllerTaker(name);
		
		internalNetServer.removeEndPointTaker(name);
	}

	//package-visible method
	/**
	 * Lets this net server take the given net end point.
	 * 
	 * @param netEndPoint
	 * @throws UnexistringAttributeException if this net server
	 * contains no duplex controller with the same name as the target of the given net end point.
	 */
	void takeNetEndPoint(final NetEndPoint netEndPoint) {
		final NetDuplexController netDuplexController = new NetDuplexController(netEndPoint);
		takeDuplexController(netDuplexController, netDuplexController.getTarget());
	}
}
