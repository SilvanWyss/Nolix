//package declaration
package ch.nolix.core.bases;

//own import
import ch.nolix.core.container.ReadContainer;
import ch.nolix.core.skillInterfaces.Closable;
import ch.nolix.core.container.List;
import ch.nolix.primitive.invalidStateException.InvalidStateException;
import ch.nolix.primitive.validator2.Validator;

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
	 * Creates a new close controller with the given element.
	 * 
	 * @param element
	 * @throws NullArgumentException if the given element is not an instance.
	 */
	public CloseController(final ClosableElement element) {
		elements.addAtEnd(element);
		element.setParentCloseController(this);
	}
	
	//method
	/**
	 * Adds the given element to this close controller.
	 * 
	 * @param element
	 * @throws NullArgumentException if the given element is not an instance.
	 * @throws InvalidStateException if this close controller contains already the given element.
	 * @throws InvalidStateException if this close controller is already closed.
	 */
	public synchronized void addElement(final ClosableElement element) {
		
		//Checks if this close controller is alive.
		supposeBeingAlive();
		
		//Checks if the given element is an instance.
		Validator.suppose(element).thatIsNamed("element").isInstance();
		
		//Checks if this close controller does not contain already the given element.
		if (containsElement(element)) {
			throw new InvalidStateException(
				this,
				"contains already the given close dependency"
			);
		}
		
		elements.addAtEnd(element.getRefCloseDependencies());
		elements.addAtEnd(element);
		elements.forEach(e -> e.setParentCloseController(this));
	}
	
	//method
	/**
	 * Closes this close controller.
	 */
	public void close() {	
		if (isAlive()) {	
			
			//Sets this close controller as closed.
			closed = true;
			
			//Lets note all element of this clsoe controller the closing.
			elements.forEach(e -> e.noteClose());
		}
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
	public ReadContainer<ClosableElement> getRefElements() {
		return new ReadContainer<ClosableElement>(elements);
	}
	
	//method
	/**
	 * @return true if this close controller is closed.
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
