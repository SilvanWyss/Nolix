//package declaration
package ch.nolix.coreapi.programcontrolapi.resourcecontrolapi;

import ch.nolix.coreapi.functionapi.requestuniversalapi.CloseStateRequestable;
//own imports
import ch.nolix.coreapi.markerapi.AllowDefaultMethodsAsDesignPattern;

//interface
/**
 * @author Silvan Wyss
 * @date 2020-07-05
 */
//own imports
@AllowDefaultMethodsAsDesignPattern
public interface GroupCloseable extends AutoCloseable, CloseStateRequestable {
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	default void close() {
		getRefCloseController().closeAll();
	}
	
	//method
	/**
	 * Creates a close dependency between the current {@link GroupCloseable} and the given element.
	 * 
	 * When a {@link GroupCloseable} is closed, all elements,
	 * the current {@link GroupCloseable} has a close dependency to,
	 * will be closed too and vice versa.
	 * 
	 * @param element
	 * @throws RuntimeException if the current {@link GroupCloseable} is already closed.
	 * @throws RuntimeException if
	 * the current {@link GroupCloseable} has already a close dependency to the given element.
	 */
	default void createCloseDependencyTo(final GroupCloseable element) {
		getRefCloseController().createCloseDependencyTo(element);
	}
	
	//method declaration
	/**
	 * @return the {@link ICloseController} of the current {@link GroupCloseable}.
	 */
	ICloseController getRefCloseController();
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	default boolean isClosed() {
		return getRefCloseController().hasClosed();
	}
	
	//method declaration
	/**
	 * Lets the current {@link GroupCloseable} note a close.
	 */
	void noteClose();
}
