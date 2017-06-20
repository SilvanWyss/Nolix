//package declaration
package ch.nolix.core.duplexController;

//own imports
import ch.nolix.core.basic.AbortableElement;
import ch.nolix.core.container.List;
import ch.nolix.core.invalidStateException.InvalidStateException;

//class
/**
 * A server manages duplex controller taker.
 * A server is abortable.
 * 
 * @author Silvan Wyss
 * @month 2017-06
 * @lines 80
 */
public class Server extends AbortableElement {
	
	//multiple attribute
	private final List<IDuplexControllerTaker> duplexControllerTaker
	= new List<IDuplexControllerTaker>();
	
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
	 * @param name
	 * @return true if this server contains a duplex controller taker with the given name.
	 */
	public final boolean containsDuplexControllerTaker(final String name) {
		return duplexControllerTaker.contains(dct -> dct.hasName(name));
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
	}
	
	//method
	/**
	 * Lets this server take the given duplex controller.
	 * 
	 * @param duplexController
	 * @throws UnexistingAttributeException if this server contains no duplex controller taker
	 * with the same name as the target of the given duplex controller.
	 */
	public final void takeDuplexController(final DuplexController duplexController, final String target) {
		duplexControllerTaker.getRefFirst(dct -> dct.hasName(target))
		.takeDuplexController(duplexController);
	}
	
	//method
	/**
	 * Lets this server note an abort.
	 */
	protected final void noteAbort() {}
}
