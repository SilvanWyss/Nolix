//package declaration
package ch.nolix.core.basic;

//own import
import ch.nolix.core.container.AccessorContainer;
import ch.nolix.core.container.List;
import ch.nolix.core.interfaces.Closable;
import ch.nolix.core.invalidStateException.InvalidStateException;
import ch.nolix.core.validator2.Validator;

//package-visible class
/**
 * @author Silvan Wyss
 * @month 2017-06
 * @lines 120
 */
final class CloseController implements Closable {

	//attribute
	private boolean closed = false;
	
	//multiple attribute
	private final List<ClosableElement> elements = new List<ClosableElement>();
	
	//constructor
	/**
	 * Creates new close controller with the given element.
	 * 
	 * @param element
	 * @throws NullArgumentException if the given element is null.
	 */
	public CloseController(final ClosableElement element) {
		elements.addAtEnd(element);
		element.setCloseController(this);
	}
	
	//method
	/**
	 * Adds the given element to this close controller.
	 * 
	 * @param element
	 * @throws NullArgumentException if the given element is null.
	 * @throws InvalidStateException if this close controller contains already the given element.
	 * @throws InvalidStateException if this close controller is already closed.
	 */
	public synchronized void addElement(final ClosableElement element) {
		
		//Checks if this close controller is alive.
		supposeBeingAlive();
		
		//Checks if the given element is not null.
		Validator.supposeThat(element).thatIsNamed("element").isNotNull();
		
		//Checks if this close controller does not contain already the given element.
		if (containsElement(element)) {
			throw new InvalidStateException(
				this,
				"contains already the given close dependency"
			);
		}
		
		elements.addAtEnd(element.getRefCloseDependencies());
		elements.addAtEnd(element);
		elements.forEach(e -> e.setCloseController(this));
	}
	
	//method
	/**
	 * Closes this close controller.
	 */
	public void close() {
		
		//Sets this close controller as closed.
		closed = true;
		
		//Lets note all element of this clsoe controller the closing.
		elements.forEach(e -> e.noteClosing());
	}
	
	//method
	/**
	 * @param element
	 * @return true if this close controller contains the given element.
	 */
	public boolean containsElement(final ClosableElement element) {
		return elements.contains(element);
	}
	
	//method
	/**
	 * @return the elements of this close controller.
	 */
	public AccessorContainer<ClosableElement> getRefElements() {
		return new AccessorContainer<ClosableElement>(elements);
	}
	
	//method
	/**
	 * @return true if the elements of this close controller are closed.
	 */
	public boolean isClosed() {
		return closed;
	}
	
	//method
	/**
	 * @throws InvalidStateException if this close controller is closed.
	 */
	private void supposeBeingAlive() {

		//Checks if this close controller is alive.
		if (isClosed()) {
			throw new InvalidStateException(
				this,
				"is closed"
			);
		}
	}
}
