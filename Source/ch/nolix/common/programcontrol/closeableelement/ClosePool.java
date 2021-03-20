//package declaration
package ch.nolix.common.programcontrol.closeableelement;

//own imports
import ch.nolix.common.constant.LowerCaseCatalogue;
import ch.nolix.common.container.IContainer;
import ch.nolix.common.container.LinkedList;
import ch.nolix.common.errorcontrol.invalidargumentexception.ArgumentIsNullException;
import ch.nolix.common.errorcontrol.invalidargumentexception.ClosedArgumentException;
import ch.nolix.common.errorcontrol.invalidargumentexception.InvalidArgumentException;
import ch.nolix.common.errorcontrol.logger.Logger;
import ch.nolix.common.errorcontrol.validator.Validator;

//class
/**
 * @author Silvan Wyss
 * @date 2020-07-06
 * @lines 170
 */
final class ClosePool {
	
	//attributes
	private boolean closed;
	private boolean closing;
	
	//multi-attribute
	private final LinkedList<ICloseableElement> elements = new LinkedList<>();
	
	//constructor
	/**
	 * Creates a new {@link ClosePool} with the given element.
	 * 
	 * @param element
	 */
	public ClosePool(final ICloseableElement element) {
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
		Validator.assertThat(element).thatIsNamed(LowerCaseCatalogue.ELEMENT).isNotNull();
		
		//Asserts that the current ClosePool is open.
		assertIsOpen();
		
		//Asserts that the current ClosePool does not contain already the given element.
		assertDoesNotContain(element);
		
		//Create close dependencies to the given element.
		final var additionalElements = element.getRefCloseController().getRefCloseDependencies();
		additionalElements.forEach(ae -> ae.getRefCloseController().setParentClosePool(this));
		elements.addAtEnd(additionalElements);
	}
	
	//method
	/**
	 * Closes the current {@link ClosePool}.
	 */
	public void close() {
		
		//Handles the case that the current ClosePool is open and not closing.
		if (isOpen() && !isClosing()) {
			closeWhenOpenAndNotClosing();
		}
	}
	
	//method
	/**
	 * @return true if the current {@link ClosePool} is closed.
	 */
	public boolean isClosed() {
		return closed;
	}
	
	//method
	/**
	 * @return true if the current {@link ClosePool} is closing.
	 */
	public boolean isClosing() {
		return closing;
	}
	
	//method
	/**
	 * @return true if the current {@link ClosePool} is open.
	 */
	public boolean isOpen() {
		return !isClosed();
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
	private void assertDoesNotContain(final ICloseableElement element) {
		
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
		if (!isOpen()) {
			throw new ClosedArgumentException(this);
		}
	}
	
	//method
	/**
	 * Closes the given closeableElement catching any {@link Exception}.
	 * 
	 * @param closeableElement
	 */
	private void closeSafely(final ICloseableElement closeableElement) {
		try {
			closeableElement.noteClose();
		} catch (final Exception exception) {
			Logger.logError(exception);
		}
	}
	
	//method
	/**
	 * Closes the current {@link ClosePool} for the case when it is open.
	 */
	private void closeWhenOpenAndNotClosing() {
		
		//Sets the current CloseController as closing.
		closing = true;
		
		//Lets note all elements of the current CloseController the close.
		elements.forEachWithContinuing(this::closeSafely);
		
		//Sets the current CloseController as closed.
		closed = true;
		closing = false;
	}
}
