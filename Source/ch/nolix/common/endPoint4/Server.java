//package declaration
package ch.nolix.common.endPoint4;

import ch.nolix.common.closableElement.ClosableElement;
import ch.nolix.common.containers.LinkedList;
import ch.nolix.common.invalidArgumentExceptions.InvalidArgumentException;

//class
/**
 * A server manages end point taker.
 * A server is abortable.
 * 
 * @author Silvan Wyss
 * @month 2017-05
 * @lines 80
 */
public class Server<M, R>
extends ClosableElement {
	
	//multi-attribute
	private final LinkedList<IEndPointTaker<M, R>> endPointTaker = new LinkedList<>();
	
	//method
	/**
	 * Adds the given end point taker to this server.
	 * 
	 * @param endPointTaker
	 * @throws InvalidArgumentException
	 * if this server contains an end point taker with the same name as the given end point taker.
	 */
	public void addEndPointTaker(final IEndPointTaker<M, R> endPointTaker) {
		
		if (containsEndPointTaker(endPointTaker.getName())) {
			throw new InvalidArgumentException(
				this,
				"contains an end point taker with the same name as the given end point taker"
			);
		}
		
		this.endPointTaker.addAtEnd(endPointTaker);
	}
	
	//method
	/**
	 * @param name
	 * @return true if this server contains an end point taker with the given name.
	 */
	public final boolean containsEndPointTaker(final String name) {
		return endPointTaker.contains(ept -> ept.hasName(name));
	}
	
	//method
	/**
	 * Removes the end point taker with the given name from this server.
	 * 
	 * @param name
	 * @throws InvalidArgumentException if this server does not contain an end point taker with the given name.
	 */
	public void removeEndPointTaker(final String name) {
		endPointTaker.removeFirst(ept -> ept.hasName(name));
	}
	
	//method
	/**
	 * Lets this server take the given end point.
	 * 
	 * @param endPoint
	 */
	public final void takeEndPoint(final EndPoint<M, R> endPoint) {
		endPointTaker
		.getRefFirst(ept -> ept.hasName(endPoint.getTarget()))
		.takeEndPoint(endPoint);
	}
	
	//method
	/**
	 * Lets this server note an abort.
	 */
	@Override
	protected void noteClose() {}
}
