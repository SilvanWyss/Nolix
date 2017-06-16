//package declaration
package ch.nolix.core.endPoint3;

//own imports
import ch.nolix.core.basic.AbortableElement;
import ch.nolix.core.container.List;
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
public class Server
extends AbortableElement {
	
	//multiple attribute
	private final List<IEndPointTaker> endPointTaker = new List<IEndPointTaker>();
	
	//method
	/**
	 * Adds the given end point taker to this server.
	 * 
	 * @param endPointTaker
	 * @throws InvalidStateException
	 * if this server contains an end point taker with the same name as the given end point taker.
	 */
	public void addEndPointTaker(final IEndPointTaker endPointTaker) {
		
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
	public final void takeEndPoint(final EndPoint endPoint) {
		endPointTaker
		.getRefFirst(ept -> ept.hasName(endPoint.getTarget()))
		.takeEndPoint(endPoint);
	}

	@Override
	protected void noteAbort() {}
}
