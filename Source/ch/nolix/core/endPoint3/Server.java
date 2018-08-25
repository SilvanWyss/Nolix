//package declaration
package ch.nolix.core.endPoint3;

import ch.nolix.core.bases.ClosableElement;
import ch.nolix.core.container.List;
import ch.nolix.core.skillInterfaces.Clearable;
import ch.nolix.primitive.invalidStateException.InvalidStateException;
import ch.nolix.primitive.invalidStateException.UnexistingAttributeException;

//abstract class
/**
 * A server manages end point taker.
 * A server is clearable and closable.
 * 
 * @author Silvan Wyss
 * @month 2017-05
 * @lines 160
 */
public class Server extends ClosableElement implements Clearable<Server> {
	
	//optional attribute
	private IEndPointTaker arbitraryEndPointTaker;
	
	//multiple attribute
	private final List<IEndPointTaker> endPointTaker = new List<IEndPointTaker>();
	
	//method
	/**
	 * Adds the given arbitrary end point taker to this server.
	 * An arbitrary end point taker takes all end points without or with any target.
	 * 
	 * @param arbitraryEndPointTaker
	 * @throws InvalidStateException if this server contains end point taker.
	 */
	public final void addArbitraryEndPointTaker(final IEndPointTaker arbitraryEndPointTaker) {
		
		//Checks if this server contains no end point taker.
		if (!isEmpty()) {
			throw new InvalidStateException(this, "contains already end point taker");
		}
		
		addEndPointTaker(arbitraryEndPointTaker);
		
		this.arbitraryEndPointTaker = arbitraryEndPointTaker;
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
	 * Removes all end point taker from this server.
	 * 
	 * @return this server.
	 */
	public final Server clear() {
		
		endPointTaker.clear();
		arbitraryEndPointTaker = null;
		
		return this;
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
	 * @return true if this server has an arbitrary end point taker.
	 */
	public final boolean hasArbitraryEndPointTaker() {
		return (arbitraryEndPointTaker != null);
	}
	
	//method
	/**
	 * @return true if this server contains no end point taker.
	 */
	public final boolean isEmpty() {
		return endPointTaker.isEmpty();
	}
	
	//method
	/**
	 * Removes the end point taker with the given name from this server.
	 * 
	 * @param name
	 * @throws InvalidArgumentException
	 * if this server contains no end point taker with the given name.
	 */
	public final void removeEndPointTaker(final String name) {
		
		endPointTaker.removeFirst(ept -> ept.hasName(name));
		
		//Handles the case that the concerning end point taker
		//is the arbitrary end point taker of this server.
		if (hasArbitraryEndPointTaker() && getArbitraryEndPointTaker().hasName(name)) {
			arbitraryEndPointTaker = null;
		}
	}
	
	//method
	/**
	 * Lets this server take the given end point.
	 * 
	 * @param endPoint
	 * @throws UnexistingAttributeException if
	 * this server has no arbitrary end point taker
	 * or contains no end point taker
	 * with the same name as the target of the given end point taker. 
	 */
	public final void takeEndPoint(final EndPoint endPoint) {
		
		//Handles the case that this server has no arbitrary end point taker.
		if (!hasArbitraryEndPointTaker()) {
			endPointTaker
			.getRefFirst(ept -> ept.hasName(endPoint.getTarget()))
			.takeEndPoint(endPoint);			
		}
		
		//Handles the case that this server has an arbitrary end point taker.
		else {
			getArbitraryEndPointTaker().takeEndPoint(endPoint);
		}
	}
	
	//method
	/**
	 * Lets this server note a closing.
	 */
	protected void noteClosing() {}
	
	//method
	/**
	 * @return the arbitrary end point taker of this server.
	 * @throws UnexistingAttribtueException
	 * if this server has no arbitrary end point taker.
	 */
	private IEndPointTaker getArbitraryEndPointTaker() {
		
		//Checks if this server has an arbitrary end point taker.
		if (!hasArbitraryEndPointTaker()) {
			throw new UnexistingAttributeException(	this,"arbitrary end point taker");
		}
		
		return arbitraryEndPointTaker;
	}
}
