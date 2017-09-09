//package declaration
package ch.nolix.core.endPoint3;

//own imports
import ch.nolix.core.basic.ClosableElement;
import ch.nolix.core.container.List;
import ch.nolix.core.invalidStateException.InvalidStateException;
import ch.nolix.core.invalidStateException.UnexistingAttributeException;

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
extends ClosableElement {
	
	//optional attribute
	private IEndPointTaker defaultEndPointTaker;
	
	//multiple attribute
	private final List<IEndPointTaker> endPointTaker = new List<IEndPointTaker>();
	
	//method
	public final void addDefaultEndPointTaker(final IEndPointTaker defaultEndPointTaker) {
		
		if (hasDefaultEndPointTaker()) {
			throw new InvalidStateException(
				this,
				"has already a default end point taker"
			);
		}
		
		addEndPointTaker(defaultEndPointTaker);
		this.defaultEndPointTaker = defaultEndPointTaker;
	}

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
	public final boolean hasDefaultEndPointTaker() {
		return (defaultEndPointTaker != null);
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
		
		if (hasDefaultEndPointTaker() && getDefaultEndPointTaker().hasName(name)) {
			
		}
	}

	//method
	/**
	 * Lets this server take the given end point.
	 * 
	 * @param endPoint
	 */
	public final void takeEndPoint(final EndPoint endPoint) {
		
		if (!endPoint.hasTarget()) {
			getDefaultEndPointTaker().takeEndPoint(endPoint);
		}
		
		else {
			endPointTaker
			.getRefFirst(ept -> ept.hasName(endPoint.getTarget()))
			.takeEndPoint(endPoint);
		}
	}

	//method
	/**
	 * Lets this server note an abort.
	 */
	protected final void noteClosing() {}
	
	//method
	private IEndPointTaker getDefaultEndPointTaker() {
		
		if (!hasDefaultEndPointTaker()) {
			throw new UnexistingAttributeException(this, "default end point taker");
		}
		
		return defaultEndPointTaker;
	}
}
