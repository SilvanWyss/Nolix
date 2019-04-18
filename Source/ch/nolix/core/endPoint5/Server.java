//package declaration
package ch.nolix.core.endPoint5;

import ch.nolix.core.closeController.ClosableElement;
import ch.nolix.core.container.List;
import ch.nolix.core.invalidArgumentException.InvalidArgumentException;
import ch.nolix.core.invalidArgumentException.ArgumentMissesAttributeException;
import ch.nolix.core.skillAPI.Clearable;

//class
/**
 * A server manages duplex controller taker.
 * A server is clearable and closable.
 * 
 * @author Silvan Wyss
 * @month 2017-06
 * @lines 160
 */
public class Server extends ClosableElement implements Clearable<Server> {
	
	//optional attribute
	IEndPointTaker arbitraryDuplexControllerTaker;
	
	//multiple attribute
	private final List<IEndPointTaker> endPointTaker
	= new List<IEndPointTaker>();
	
	//method
	/**
	 * Adds the given arbitrary duplex controller taker to this server.
	 * An arbitrary duplex controller taker takes all duplex controllers without or with any target.
	 * 
	 * @param arbitraryDuplexControllerTaker
	 * @throws InvalidArgumentException if this server contains duplex controller.
	 */
	public void addArbitraryDuplexControllerTaker(
		final IEndPointTaker arbitraryDuplexControllerTaker
	) {
		
		//Checks if this server does not contain a duplex controller taker.
		if (containsAny()) {
			throw new InvalidArgumentException(this, "contains duplex controller");
		}
		
		addDuplexControllerTaker(arbitraryDuplexControllerTaker);
		
		this.arbitraryDuplexControllerTaker = arbitraryDuplexControllerTaker;
	}
	
	//method
	/**
	 * Adds the given duplex controller to this server.
	 * 
	 * @param endPointTaker
	 * @throws InvalidArgumentException if this server contains a duplex controller
	 * with the same name the given duplex controller has
	 */
	public void addDuplexControllerTaker(final IEndPointTaker endPointTaker) {
		
		//Checks if this server contains a duplex controller taker
		//with the same name as the given duplex controller has.
		if (containsDuplexControllerTaker(endPointTaker.getName())) {
			throw new InvalidArgumentException(
				this,
				"contains a duplex controller taker with the same name the given duplex controller taker has"
			);
		}
		
		this.endPointTaker.addAtEnd(endPointTaker);
	}
	
	//method
	/**
	 * Removes all duplex controller from this server.
	 * 
	 * @return this server.
	 */
	@Override
	public final Server clear() {
		
		endPointTaker.clear();
		arbitraryDuplexControllerTaker = null;
		
		return this;
	}
	
	//method
	/**
	 * @param name
	 * @return true if this server contains a duplex controller taker with the given name.
	 */
	public final boolean containsDuplexControllerTaker(final String name) {
		return endPointTaker.contains(dct -> dct.hasName(name));
	}
	
	//method
	/**
	 * @return true if this server has an arbitrary duplex controller taker.
	 */
	public boolean hasArbitraryDuplexControllerTaker() {
		return (arbitraryDuplexControllerTaker != null);
	}
	
	//method
	/**
	 * @return true if this server does not contain a duplex controller taker.
	 */
	@Override
	public final boolean isEmpty() {
		return endPointTaker.isEmpty();
	}
	
	//method
	/**
	 * Removes the duplex controller taker with the given name from this server.
	 * 
	 * @param name
	 * @throws InvalidArgumentException
	 * if this server does not contain a duplex controller taker with the given name.
	 */
	public void removeDuplexControllerTaker(final String name) {
		
		endPointTaker.removeFirst(dct -> dct.hasName(name));
		
		if (arbitraryDuplexControllerTaker != null && arbitraryDuplexControllerTaker.hasName(name)) {
			arbitraryDuplexControllerTaker = null;
		}
	}
	
	//method
	/**
	 * Lets this server take the given duplex controller.
	 * 
	 * @param endPoint
	 * @throws ArgumentMissesAttributeException if
	 * this server does not have an arbitrary duplex controller taker
	 * or does not contain a duplex controller taker
	 * with the same name as the target of the given duplex controller.
	 */
	public final void takeDuplexController(final EndPoint endPoint) {
		
		//Handles the case that this server does not have an arbitrary duplex controller taker.
		if (!hasArbitraryDuplexControllerTaker()) {
			endPointTaker
			.getRefFirst(dct -> dct.hasName(endPoint.getTarget()))
			.takeDuplexController(endPoint);
		}
		
		//Handles the case that this server has an arbitrary duplex controller taker.
		else {
			getArbitraryDuplexControllerTaker().takeDuplexController(endPoint);
		}
	}

	//method
	/**
	 * Lets this server note an abort.
	 */
	@Override
	protected final void noteClose() {}
	
	//method
	/**
	 * @return the arbitrary duplex controller taker of this server.
	 * @throws ArgumentMissesAttributeException if this server does not have an arbitrary duplex controller taker.
	 */
	private IEndPointTaker getArbitraryDuplexControllerTaker() {

		//Checks if this server has an arbitrary duplex controller taker.
		if (!hasArbitraryDuplexControllerTaker()) {
			throw new ArgumentMissesAttributeException(this, "arbitrary duplex controller taker");
		}
		
		return arbitraryDuplexControllerTaker;
	}
}
