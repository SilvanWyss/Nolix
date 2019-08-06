//package declaration
package ch.nolix.core.optionalClosableElement;

import ch.nolix.core.containers.List;
import ch.nolix.core.containers.ReadContainer;
import ch.nolix.core.invalidArgumentException.InvalidArgumentException;
import ch.nolix.core.skillAPI.OptionalClosable;
import ch.nolix.core.validator.Validator;

//package-visible class
/**
 * @author Silvan Wyss
 * @month 2017-06
 * @lines 120
 */
final class CloseController implements OptionalClosable {
	
	//attribute
	private boolean closed = false;
	
	//multi-attribute
	private final List<OptionalClosableElement> elements = new List<>();
	
	//constructor
	/**
	 * Creates a new {@link CloseController} with the given element.
	 * 
	 * @param element
	 * @throws NullArgumentException if the given element is null.
	 */
	public CloseController(final OptionalClosableElement element) {
		elements.addAtEnd(element);
	}
	
	//method
	/**
	 * Adds the given element to the current {@link CloseController}.
	 * 
	 * @param element
	 * @throws NullArgumentException if the given element is null.
	 * @throws InvalidArgumentException if the current {@link CloseController} contains already the given element.
	 * @throws InvalidArgumentException if the current {@link CloseController} is already closed.
	 */
	public synchronized void addElement(final OptionalClosableElement element) {
				
		//Checks if the given element is not null.
		Validator.suppose(element).thatIsNamed("element").isNotNull();
		
		//Checks if the current CloseController is alive.
		supposeIsAlive();
		
		//Checks if the current CloseController does not contain already the given element.
		if (containsElement(element)) {
			throw new InvalidArgumentException(
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
		
		//Handles the case that the current CloseController is alive.
		if (isOpen()) {
			
			//Sets the current CloseController as closed.
			closed = true;
			
			//Lets note all elements of the current CloseController the close.
			elements.forEach(e -> e.noteClose());
		}
	}
	
	//method
	/**
	 * @param element
	 * @return true if the current {@link CloseController} contains the given element.
	 */
	public boolean containsElement(final OptionalClosableElement element) {
		return elements.contains(element);
	}
	
	//method
	/**
	 * @return the elements of the current {@link CloseController}.
	 */
	public ReadContainer<OptionalClosableElement> getRefElements() {
		return new ReadContainer<>(elements);
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
	 * @throws InvalidArgumentException if the current {@link CloseController} is closed.
	 */
	private void supposeIsAlive() {
		
		//Checks if the current CloseController is not closed.
		if (isClosed()) {
			throw
			new InvalidArgumentException(
				this,
				"is closed"
			);
		}
	}
}
