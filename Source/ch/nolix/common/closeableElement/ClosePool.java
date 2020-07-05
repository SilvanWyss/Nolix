//package declaration
package ch.nolix.common.closeableElement;

//own imports
import ch.nolix.common.constant.VariableNameCatalogue;
import ch.nolix.common.container.IContainer;
import ch.nolix.common.container.LinkedList;
import ch.nolix.common.invalidArgumentException.ClosedArgumentException;
import ch.nolix.common.invalidArgumentException.InvalidArgumentException;
import ch.nolix.common.state.Openness;
import ch.nolix.common.validator.Validator;

//class
/**
 * @author Silvan Wyss
 * @month 2020-07
 * @lines 150
 */
final class ClosePool {
	
	//attribute
	private Openness state = Openness.OPEN;
	
	//multi-attribute
	private final LinkedList<ICloseableElement> elements = new LinkedList<>();
	
	//constructor
	/**
	 * Creates a new {@link ClosePool} with the given element.
	 * 
	 * @param element
	 */
	public ClosePool(final ICloseableElement element) {
		
		//TODO: Handle elements under construction more suitable.
		//The given element is supposed to be under construction.
		
		elements.addAtEnd(element);
	}
	
	//method
	/**
	 * Adds the given element to the current {@link ClosePool}.
	 * 
	 * @param element
	 * @throws ArgumentIsNullException if the given element is null.
	 * @throws InvalidArgumentException if the current {@link ClosePool} is not open.
	 * @throws InvalidArgumentException if the current {@link ClosePool} contains already the given element.
	 */
	public void add(final ICloseableElement element) {
		
		//Asserts that the given element is not null.
		Validator.assertThat(element).thatIsNamed(VariableNameCatalogue.ELEMENT).isNotNull();
		
		//Asserts that the current ClosePool is open.
		assertIsOpen();
		
		//Asserts that the current ClosePool does not contain already the given element.
		assertDoesNotContainer(element);
		
		//Create close dependencies to the given element.
		final var additionalElements = getRefCloseDependenciesOf(element);
		additionalElements.forEach(ae -> ae.getRefCloseController().setParentCloseController(this));
		elements.addAtEnd(additionalElements);
	}
	
	//method
	/**
	 * Closes the current {@link ClosePool}.
	 */
	public void close() {
		
		//Handles the case that the current ClosePool is open.
		if (state == Openness.OPEN) {
			closeWhenOpen();
		}
	}
	
	//method
	/**
	 * @return true if the current {@link ClosePool} is closed.
	 */
	public boolean isClosed() {
		return state == Openness.CLOSED;
	}
	
	//method
	/**
	 * @param element
	 * @return if the current {@link ClosePool} contains the given element.
	 */
	public boolean contains(final ICloseableElement element) {
		return elements.contains(element);
	}
	
	//method
	/**
	 * @return the elements of the current {@link ClosePool}.
	 */
	public IContainer<ICloseableElement> getRefElements() {
		return elements;
	}
	
	//method
	/**
	 * @param element
	 * @throws InvalidArgumentException if the current {@link ClosePool} contains already the given element.
	 */
	private void assertDoesNotContainer(final ICloseableElement element) {
		
		//Asserts that the current ClosePool does not contain the given element.
		if (contains(element)) {
			throw new InvalidArgumentException(this, "contains already the given element");
		}
	}
	
	//method
	/**
	 * @throws ClosedArgumentException if the current {@link ClosePool} is closed.
	 */
	private void assertIsOpen() {
		
		//Asserts that the current ClosePool is open.
		if (state != Openness.OPEN) {
			throw new ClosedArgumentException(this);
		}
	}
	
	//method
	/**
	 * Closes the current {@link ClosePool} for the case when it is open.
	 */
	private void closeWhenOpen() {
		
		//Sets the current CloseController closing.
		state = Openness.CLOSING;
		
		//Lets note all elements of the current CloseController run their probable pre-close action.
		elements.forEachWithContinuing(e -> e.getRefCloseController().runProbablePreCloseAction());
					
		//Sets the current CloseController closed.
		state = Openness.CLOSED;
	}

	//method
	/**
	 * @param element
	 * @return the close dependencies of the given element.
	 */
	private LinkedList<ICloseableElement> getRefCloseDependenciesOf(final ICloseableElement element) {
		return new LinkedList<>(element.getRefCloseController().getRefCloseDependencies()).addAtEnd(element);
	}
}
