//package declaration
package ch.nolix.common.endPoint2;

//own imports
import ch.nolix.common.basic.AbortableElement;
import ch.nolix.common.container.List;

//abstract class
/**
 * A server manages end point taker and takes end points.
 * A server is abortable.
 * 
 * @author Silvan Wyss
 * @month 2017-05
 * @lines 60
 */
public abstract class Server extends AbortableElement {
	
	//multiple attribute
	private final List<IEndPointTaker> endPointTaker = new List<IEndPointTaker>();
	
	//method
	/**
	 * Adds the given end point taker to this server.
	 * 
	 * @param endPointTaker
	 * @throws RuntimeException if this server contains end point taker with the same name as the given end point taker.
	 */
	public final void addEndPointTaker(final IEndPointTaker endPointTaker) {
		
		//Checks if this server contains already an end point taker with the same name as the given end point taker.
		if (containsEndPointTaker(endPointTaker.getName())) {
			throw new RuntimeException("Server contains an end point taker with the same name.");
		}
		
		this.endPointTaker.addAtEnd(endPointTaker);
	}
	
	//method
	/**
	 * @param name
	 * @return true if this server contains a end point taker with the given name.
	 */
	public final boolean containsEndPointTaker(final String name) {
		return endPointTaker.contains(ept -> ept.hasName(name));
	}
	
	//method
	/**
	 * Removes the end point taker with the given name of this server.
	 * 
	 * @param name
	 * @throws InvalidArgumentException if this server contains no end point taker with the given name.
	 */
	public final void removeEndPointTaker(final String name) {
		endPointTaker.removeFirst(ept -> ept.hasName(name));
	}
	
	//method
	/**
	 * Lets this server take the given incoming end point.
	 * 
	 * @param incomingEndPoint
	 */
	protected final void takeIncomingEndPoint(final EndPoint incomingEndPoint) {
		endPointTaker.getRefFirst(ept -> ept.hasName(incomingEndPoint.getTarget()))
		.takeEndPoint(incomingEndPoint);
	}
}
