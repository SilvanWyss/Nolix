//package declaration
package ch.nolix.core.endPoint3;

//own imports
import ch.nolix.core.basic.AbortableElement;
import ch.nolix.core.invalidStateException.InvalidStateException;

//abstract class
/**
 * A server manages end point taker.
 * A server is abortable.
 * 
 * @author Silvan Wyss
 * @month 2017-05
 * @lines 70
 */
public class Server extends AbortableElement {
	
	//attribute
	private final ch.nolix.core.endPoint2.Server internalServer;
	
	Server(final ch.nolix.core.endPoint2.Server internalServer) {
		this.internalServer = internalServer;
	}
	
	public final void abort() {
		
		super.abort();
		
		internalServer.abort();
	}
	
	//method
	/**
	 * Adds the given end point taker to this server.
	 * 
	 * @param endPointTaker
	 * @throws InvalidStateException if this server contains an end point taker with the same name as the given end point taker.
	 */
	public final void addEndPointTaker(final IEndPointTaker endPointTaker) {
		
		internalServer.addEndPointTaker(
			new EndPointTaker(endPointTaker)	
		);	
	}
	
	//method
	/**
	 * @param name
	 * @return true if this server contains an end point taker with the given name.
	 */
	public final boolean containsEndPointTaker(final String name) {
		return internalServer.containsEndPointTaker(name);
	}
	
	//method
	/**
	 * Removes the end point taker with the given name of this server.
	 * 
	 * @param name
	 * @throws InvalidArgumentException if this server contains no end point taker with the given name.
	 */
	public final void removeEndPointTaker(final String name) {
		internalServer.removeEndPointTaker(name);
	}
	
	//method
	/**
	 * Lets this server take the given end point.
	 * 
	 * @param endPoint
	 */
	public final void takeEndPoint(final EndPoint endPoint) {
		internalServer.takeEndPoint(endPoint.getRefInternalEndPoint());
	}
	
	protected ch.nolix.core.endPoint2.Server getRefInternalServer() {
		return internalServer;
	}
}
