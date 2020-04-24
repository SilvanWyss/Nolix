//package declaration
package ch.nolix.common.closableElement;

import ch.nolix.common.container.LinkedList;
import ch.nolix.common.container.ReadContainer;
import ch.nolix.common.invalidArgumentException.InvalidArgumentException;
import ch.nolix.common.skillAPI.Closable;
import ch.nolix.common.validator.Validator;

//class
/**
 * @author Silvan Wyss
 * @month 2017-06
 * @lines 120
 */
final class CloseController implements Closable {
	
	//attribute
	private boolean closed = false;
	
	//multi-attribute
	private final LinkedList<ClosableElement> elements = new LinkedList<>();
	
	//constructor
	/**
	 * Creates a new {@link CloseController} with the given element.
	 * 
	 * @param element
	 * @throws ArgumentIsNullException if the given element is null.
	 */
	public CloseController(final ClosableElement element) {
		elements.addAtEnd(element);
	}
	
	//method
	/**
	 * Adds the given element to the current {@link CloseController}.
	 * 
	 * @param element
	 * @throws ArgumentIsNullException if the given element is null.
	 * @throws InvalidArgumentException if the current {@link CloseController} contains already the given element.
	 * @throws InvalidArgumentException if the current {@link CloseController} is already closed.
	 */
	public synchronized void addElement(final ClosableElement element) {
				
		//Asserts that the given element is not null.
		Validator.assertThat(element).thatIsNamed("element").isNotNull();
		
		//Asserts that the current CloseController is alive.
		supposeIsAlive();
		
		//Asserts that the current CloseController does not contain already the given element.
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
		
		//Asserts that the current CloseController is alive.
		supposeIsAlive();
		
		//Lets note all elements of the current CloseController run their probable pre-close action.
		elements.forEach(ClosableElement::runProbablePreCloseAction);
		
		//Lets note all elements of the current CloseController note the close.
		elements.forEach(ClosableElement::noteClose);
		
		//Sets the current CloseController as closed.
		closed = true;
	}
	
	//method
	/**
	 * @param element
	 * @return true if the current {@link CloseController} contains the given element.
	 */
	public boolean containsElement(final ClosableElement element) {
		return elements.contains(element);
	}
	
	//method
	/**
	 * @return the elements of the current {@link CloseController}.
	 */
	public ReadContainer<ClosableElement> getRefElements() {
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
		
		//Asserts that the current CloseController is not closed.
		if (isClosed()) {
			throw
			new InvalidArgumentException(
				this,
				"is closed"
			);
		}
	}
}
