//package declaration
package ch.nolix.core.duplexController;

import ch.nolix.core.bases.ClosableElement;
import ch.nolix.core.container.List;
import ch.nolix.core.interfaces.Clearable;
import ch.nolix.core.invalidStateException.InvalidStateException;
import ch.nolix.core.invalidStateException.UnexistingAttributeException;

//class
/**
 * A server manages duplex controller taker.
 * A server is clearable and closable.
 * 
 * @author Silvan Wyss
 * @month 2017-06
 * @lines 160
 */
public class Server extends ClosableElement implements Clearable {
	
	//optional attribute
	IDuplexControllerTaker arbitraryDuplexControllerTaker;
	
	//multiple attribute
	private final List<IDuplexControllerTaker> duplexControllerTaker
	= new List<IDuplexControllerTaker>();
	
	//method
	/**
	 * Adds the given arbitrary duplex controller taker to this server.
	 * An arbitrary duplex controller taker takes all duplex controllers without or with any target.
	 * 
	 * @param arbitraryDuplexControllerTaker
	 * @throws InvalidStateException if this server contains duplex controller.
	 */
	public void addArbitraryDuplexControllerTaker(
		final IDuplexControllerTaker arbitraryDuplexControllerTaker
	) {
		
		//Checks if this server contains no duplex controller taker.
		if (containsAny()) {
			throw new InvalidStateException(this, "contains duplex controller");
		}
		
		addDuplexControllerTaker(arbitraryDuplexControllerTaker);
		
		this.arbitraryDuplexControllerTaker = arbitraryDuplexControllerTaker;
	}
	
	//method
	/**
	 * Adds the given duplex controller to this server.
	 * 
	 * @param duplexControllerTaker
	 * @throws InvalidStateException if this server contains a duplex controller
	 * with the same name the given duplex controller has
	 */
	public void addDuplexControllerTaker(final IDuplexControllerTaker duplexControllerTaker) {
		
		//Checks if this server contains a duplex controller taker
		//with the same name as the given duplex controller has.
		if (containsDuplexControllerTaker(duplexControllerTaker.getName())) {
			throw new InvalidStateException(
				this,
				"contains a duplex controller taker with the same name the given duplex controller taker has"
			);
		}
		
		this.duplexControllerTaker.addAtEnd(duplexControllerTaker);
	}
	
	//method
	/**
	 * Removes all duplex controller from this server.
	 */
	public final void clear() {
		duplexControllerTaker.clear();
		arbitraryDuplexControllerTaker = null;
	}
	
	//method
	/**
	 * @return true if this server contains one or several duplex controller taker.
	 */
	public final boolean containsAny() {
		return duplexControllerTaker.containsAny();
	}
	
	//method
	/**
	 * @param name
	 * @return true if this server contains a duplex controller taker with the given name.
	 */
	public final boolean containsDuplexControllerTaker(final String name) {
		return duplexControllerTaker.contains(dct -> dct.hasName(name));
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
	 * Removes the duplex controller taker with the given name from this server.
	 * 
	 * @param name
	 * @throws InvalidArgumentException
	 * if this server contains no duplex controller taker with the given name.
	 */
	public void removeDuplexControllerTaker(final String name) {
		
		duplexControllerTaker.removeFirst(dct -> dct.hasName(name));
		
		if (arbitraryDuplexControllerTaker != null && arbitraryDuplexControllerTaker.hasName(name)) {
			arbitraryDuplexControllerTaker = null;
		}
	}
	
	//method
	/**
	 * Lets this server take the given duplex controller.
	 * 
	 * @param duplexController
	 * @throws UnexistingAttributeException if
	 * this server has no arbitrary duplex controller taker
	 * or contains no duplex controller taker
	 * with the same name as the target of the given duplex controller.
	 */
	public final void takeDuplexController(final DuplexController duplexController) {
		
		//Handles the case if this server has no arbitrary duplex controller taker.
		if (!hasArbitraryDuplexControllerTaker()) {
			duplexControllerTaker
			.getRefFirst(dct -> dct.hasName(duplexController.getTarget()))
			.takeDuplexController(duplexController);
		}
		
		//Handles the case if this server has an arbitrary duplex controller taker.
		else {
			getArbitraryDuplexControllerTaker().takeDuplexController(duplexController);
		}
	}

	//method
	/**
	 * Lets this server note an abort.
	 */
	protected final void noteClosing() {}
	
	//method
	/**
	 * @return the arbitrary duplex controller taker of this server.
	 * @throws UnexistingAttributeException if this server has no arbitrary duplex controller taker.
	 */
	private IDuplexControllerTaker getArbitraryDuplexControllerTaker() {

		//Checks if this server has an arbitrary duplex controller taker.
		if (!hasArbitraryDuplexControllerTaker()) {
			throw new UnexistingAttributeException(this, "arbitrary duplex controller taker");
		}
		
		return arbitraryDuplexControllerTaker;
	}
}
