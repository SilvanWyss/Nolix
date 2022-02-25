//package declaration
package ch.nolix.core.programcontrol.groupcloseable;

import ch.nolix.core.errorcontrol.invalidargumentexception.ArgumentIsNullException;
import ch.nolix.core.errorcontrol.invalidargumentexception.ClosedArgumentException;
import ch.nolix.core.errorcontrol.invalidargumentexception.InvalidArgumentException;
import ch.nolix.core.requestapi.CloseStateRequestable;

//interface
/**
 * @author Silvan Wyss
 * @date 2020-07-05
 */
public interface GroupCloseable extends AutoCloseable, CloseStateRequestable {
	
	//method
	/**
	 * Closes the current {@link GroupCloseable}.
	 */
	default void close() {
		getRefCloseController().internalClose();
	}
	
	//method
	/**
	 * Adds a close dependency between the current {@link GroupCloseable} and the given element.
	 * 
	 * When a {@link GroupCloseable} is closed all of its close dependencies will be closed too and vice versa.
	 * 
	 * @param element
	 * @throws ArgumentIsNullException if the given element is null.
	 * @throws ClosedArgumentException if the current {@link GroupCloseable} is closed.
	 * @throws InvalidArgumentException
	 * if the current {@link GroupCloseable} has already a close dependency to the given element.
	 */
	default void createCloseDependencyTo(final GroupCloseable element) {
		getRefCloseController().createCloseDependencyTo(element);
	}
	
	//method declaration
	/**
	 * @return the {@link CloseController} of the current {@link GroupCloseable}.
	 */
	CloseController getRefCloseController();
	
	//method
	/**
	 * @param element
	 * @return true if the current {@link GroupCloseable} has a close dependency to the given element.
	 */
	default boolean hasCloseDependencyTo(final GroupCloseable element) {
		return getRefCloseController().internalHasCloseDependencyTo(element);
	}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	default boolean isClosed() {
		return getRefCloseController().internalIsClosed();
	}
	
	//method declaration
	/**
	 * Lets the current {@link GroupCloseable} note a close.
	 */
	void noteClose();
}
