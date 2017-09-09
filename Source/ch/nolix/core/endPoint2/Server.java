//package declaration
package ch.nolix.core.endPoint2;

//own imports
import ch.nolix.core.basic.ClosableElement;
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
public class Server extends ClosableElement {
	
	//multiple attribute
	private final List<IEndPointTaker> endPointTaker = new List<IEndPointTaker>();
	
	//method
	/**
	 * Adds the given end point taker to this server.
	 * 
	 * @param endPointTaker
	 * @throws InvalidStateException if this server contains an end point taker
	 * with the same name the given end point taker has.
	 */
	public final void addEndPointTaker(final IEndPointTaker endPointTaker) {
		
		//Checks if this server contains an end point taker
		//with the same name the given end point taker has.
		if (containsEndPointTaker(endPointTaker.getName())) {
			throw new InvalidStateException(
				this,
				"contains an end point taker with the same name the given end point taker has"
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
	 * Lets this server take the given end point.
	 * 
	 * @param endPoint
	 * @throws UnexistingAttributeException if this server contains no end point taker
	 * with the same name as the target of the given end point taker. 
	 */
	public final void takeEndPoint(final EndPoint endPoint, final String target) {
		endPointTaker.getRefFirst(ept -> ept.hasName(target))
		.takeEndPoint(endPoint);
	}
	
	//method
	/**
	 * Lets this server note an abort.
	 */
	protected void noteClosing() {}
}
