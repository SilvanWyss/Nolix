//package declaration
package ch.nolix.common.closableElement;

//own imports
import ch.nolix.common.container.LinkedList;
import ch.nolix.common.container.ReadContainer;
import ch.nolix.common.invalidArgumentException.ClosedArgumentException;
import ch.nolix.common.invalidArgumentException.InvalidArgumentException;
import ch.nolix.common.skillAPI.Closeable;
import ch.nolix.common.state.Openness;
import ch.nolix.common.validator.Validator;

//class
/**
 * @author Silvan Wyss
 * @month 2017-06
 * @lines 120
 */
final class CloseController implements Closeable {
	
	//attribute
	private Openness state = Openness.OPEN;
	
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
		
		//Asserts that the current CloseController is open.
		supposeIsOpen();
		
		//Asserts that the current CloseController does not contain already the given element.
		if (containsElement(element)) {
			throw new InvalidArgumentException(this, "contains already the given element");
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
	public synchronized void close() {
		
		//Handles the case that the current CloseController is open.
		if (state == Openness.OPEN) {
			
			state = Openness.CLOSING;
			
			//Lets note all elements of the current CloseController run their probable pre-close action.
			elements.forEachWithContinuing(ClosableElement::runProbablePreCloseAction);
			
			//Lets note all elements of the current CloseController note the close.
			elements.forEachWithContinuing(ClosableElement::noteClose);
			
			//Sets the current CloseController as closed.
			state = Openness.CLOSED;
		}
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
		return (state == Openness.CLOSED);
	}
	
	//method
	/**
	 * @throws ClosedArgumentException if the current {@link CloseController} is closed.
	 */
	private void supposeIsOpen() {
		
		//Asserts that the current CloseController is open.
		if (isClosed()) {
			throw new ClosedArgumentException(this);
		}
	}
}
