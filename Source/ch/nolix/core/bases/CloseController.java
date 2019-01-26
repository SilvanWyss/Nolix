//package declaration
package ch.nolix.core.bases;

//own import
import ch.nolix.core.container.ReadContainer;
import ch.nolix.core.invalidStateException.InvalidStateException;
import ch.nolix.core.skillAPI.Closable;
import ch.nolix.core.validator2.Validator;
import ch.nolix.core.container.List;

//package-visible class
/**
 * @author Silvan Wyss
 * @month 2017-06
 * @lines 120
 */
final class CloseController implements Closable {
	
	//attribute
	private boolean closed = false;
	
	//multi-attribute
	private final List<ClosableElement> elements = new List<ClosableElement>();
	
	//constructor
	/**
	 * Creates a new {@link CoseController} with the given element.
	 * 
	 * @param element
	 * @throws NullArgumentException if the given element is null.
	 */
	public CloseController(final ClosableElement element) {
		elements.addAtEnd(element);
		element.setParentCloseController(this);
	}
	
	//method
	/**
	 * Adds the given element to the current {@link CoseController}.
	 * 
	 * @param element
	 * @throws NullArgumentException if the given element is null.
	 * @throws InvalidStateException if the current {@link CoseController} contains already the given element.
	 * @throws InvalidStateException if the current {@link CoseController} is already closed.
	 */
	public synchronized void addElement(final ClosableElement element) {
		
		//Checks if the current {@link CoseController} is alive.
		supposeBeingAlive();
		
		//Checks if the given element is not null.
		Validator.suppose(element).thatIsNamed("element").isNotNull();
		
		//Checks if the current {@link CoseController} does not contain already the given element.
		if (containsElement(element)) {
			throw new InvalidStateException(
				this,
				"contains already the given element"
			);
		}
		
		elements.addAtEnd(element.getRefCloseDependencies());
		elements.addAtEnd(element);
		elements.forEach(e -> e.setParentCloseController(this));
	}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void close() {
		
		//Handles the case that the current close controller is alive.
		if (isAlive()) {
			
			//Sets the current {@link CoseController} as closed.
			closed = true;
			
			//Lets note all elements of the current clsoe controller the close.
			elements.forEach(e -> e.noteClose());
		}
	}
	
	//method
	/**
	 * @param element
	 * @return true if the current {@link CoseController} contains the given element.
	 */
	public boolean containsElement(final ClosableElement element) {
		return elements.contains(element);
	}
	
	//method
	/**
	 * @return the elements of the current {@link CoseController}.
	 */
	public ReadContainer<ClosableElement> getRefElements() {
		return new ReadContainer<ClosableElement>(elements);
	}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean isClosed() {
		return closed;
	}
	
	//method
	/**
	 * @throws InvalidStateException if the current {@link CoseController} is closed.
	 */
	private void supposeBeingAlive() {

		//Checks if the current {@link CoseController} is alive.
		if (isClosed()) {
			throw
			new InvalidStateException(
				this,
				"is closed"
			);
		}
	}
}
