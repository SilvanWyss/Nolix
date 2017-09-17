//package declaration
package ch.nolix.core.endPoint4;

import ch.nolix.core.bases.ClosableElement;
import ch.nolix.core.container.List;
import ch.nolix.core.invalidStateException.InvalidStateException;

//abstract class
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
	
	//multiple attribute
	private final List<IEndPointTaker<M, R>> endPointTaker = new List<IEndPointTaker<M, R>>();
	
	//method
	/**
	 * Adds the given end point taker to this server.
	 * 
	 * @param endPointTaker
	 * @throws InvalidStateException
	 * if this server contains an end point taker with the same name as the given end point taker.
	 */
	public void addEndPointTaker(final IEndPointTaker<M, R> endPointTaker) {
		
		if (containsEndPointTaker(endPointTaker.getName())) {
			throw new InvalidStateException(
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
	 * @throws InvalidArgumentException if this server contains no end point taker with the given name.
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
	public final void takeEndPoint(final EndPoint<M, R> endPoint, final String target) {
		endPointTaker
		.getRefFirst(ept -> ept.hasName(endPoint.getTarget()))
		.takeEndPoint(endPoint);
	}
	
	//method
	/**
	 * Lets this server note an abort.
	 */
	protected void noteClosing() {}
}
