//package declaration
package ch.nolix.core.bases;

//own imports
import ch.nolix.core.container.ReadContainer;
import ch.nolix.core.interfaces.Closable;
import ch.nolix.primitive.invalidStateException.ClosedStateException;
import ch.nolix.primitive.invalidStateException.InvalidStateException;
import ch.nolix.primitive.validator2.Validator;

//abstract class
/**
 * @author Silvan Wyss
 * @month 2016-05
 * @lines 120
 */
public abstract class ClosableElement implements Closable {

	//attribute
	private CloseController closeController = new CloseController(this);
	
	//method
	/**
	 * Closes this closable element.
	 */
	public final void close() {
		if (isAlive()) {
			closeController.close();
		}
	}
	
	//method
	/**
	 * @param element
	 * @return true if this closable element has a close dependency to the given element.
	 */
	public final boolean hasCloseDependencyTo(final ClosableElement element) {
		return closeController.containsElement(element);
	}
	
	//method
	/**
	 * @return true if this closable element isclosed.
	 */
	public final boolean isClosed() {
		return closeController.isClosed();
	}
	
	//method
	/**
	 * Creates a close dependency between this closable element and the given element.
	 * When a closable element is closed all of its close dependencies will be closed too
	 * and vice versa.
	 * 
	 * @param element
	 * @throws InvalidStateException if this closable element is closed.
	 * @throws InvalidStateException
	 * if this closable element has already the given close dependency.
	 */
	protected final synchronized void createCloseDependency(final ClosableElement element) {
		
		//Checks if this closable element is alive.
		supposeBeingAlive();
		
		//Checks if this closable element has already the given close dependency.
		if (hasCloseDependencyTo(element)) {
			throw new InvalidStateException(
				this,
				"has already the given close dependency"
			);
		}
		
		closeController.addElement(element);
	}
	
	//abstract method
	/**
	 * Lets this closable element note a closing.
	 */
	protected abstract void noteClosing();
	
	//method
	/**
	 * @throws ClosedStateException if this closable element is closed.
	 */
	protected final void supposeBeingAlive() {

		//Checks if this closable element is alive.
		if (isClosed()) {
			throw new ClosedStateException(this);
		}
	}
	
	//package-visible method
	/**
	 * @return the close dependencies of this closable element.
	 */
	ReadContainer<ClosableElement> getRefCloseDependencies() {
		return closeController.getRefElements();
	}

	//package-visible method.
	/**
	 * Sets the close controller this closable element belongs to.
	 * 
	 * @param closeController
	 * @throws NullArgumentException if the given close controller is null.
	 */
	void setCloseController(final CloseController closeController) {
		
		//Checks if the given close controller is not null.
		Validator
		.suppose(closeController)
		.thatIsOfType(CloseController.class)
		.isNotNull();
		
		//Sets the close controller of this closable element.
		this.closeController = closeController;
	}
}
