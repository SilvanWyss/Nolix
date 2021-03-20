//package declaration
package ch.nolix.common.programcontrol.closeableelement;

//own imports
import ch.nolix.common.errorcontrol.invalidargumentexception.ArgumentIsNullException;
import ch.nolix.common.errorcontrol.invalidargumentexception.ClosedArgumentException;
import ch.nolix.common.errorcontrol.invalidargumentexception.InvalidArgumentException;
import ch.nolix.common.skillapi.Closeable;

//interface
/**
 * @author Silvan Wyss
 * @date 2020-07-05
 * @lines 80
 */
public interface ICloseableElement extends Closeable {
	
	//method
	/**
	 * @throws ClosedArgumentException if the current {@link ICloseableElement} is closed.
	 */
	default void assertIsOpen() {
		
		//Asserts that the current ICloseableElement is open.
		if (isClosed()) {
			throw new ClosedArgumentException(this);
		}
	}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	default void close() {
		getRefCloseController().close();
	}
	
	//method
	/**
	 * Adds a close dependency between the current {@link ICloseableElement} and the given element.
	 * 
	 * When a {@link ICloseableElement} is closed all of its close dependencies will be closed too and vice versa.
	 * 
	 * @param element
	 * @throws ArgumentIsNullException if the given element is null.
	 * @throws ClosedArgumentException if the current {@link ICloseableElement} is closed.
	 * @throws InvalidArgumentException
	 * if the current {@link ICloseableElement} has already a close dependency to the given element.
	 */
	default void createCloseDependencyTo(final ICloseableElement element) {
		getRefCloseController().createCloseDependencyTo(element);
	}
	
	//method declaration
	/**
	 * @return the {@link CloseController} of the current {@link ICloseableElement}.
	 */
	CloseController getRefCloseController();
	
	//method
	/**
	 * @param element
	 * @return true if the current {@link ICloseableElement} has a close dependency to the given element.
	 */
	default boolean hasCloseDependencyTo(final ICloseableElement element) {
		return getRefCloseController().hasCloseDependencyTo(element);
	}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	default boolean isClosed() {
		return getRefCloseController().isClosed();
	}
	
	//method declaration
	/**
	 * Lets the current {@link ICloseableElement} note a close.
	 */
	void noteClose();
}
