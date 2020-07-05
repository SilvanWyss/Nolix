//package declaration
package ch.nolix.common.closeableElement;

//own imports
import ch.nolix.common.invalidArgumentException.ArgumentIsNullException;
import ch.nolix.common.invalidArgumentException.ClosedArgumentException;
import ch.nolix.common.invalidArgumentException.InvalidArgumentException;
import ch.nolix.common.functionAPI.IAction;
import ch.nolix.common.skillAPI.Closeable;

//interface
/**
 * @author Silvan Wyss
 * @month 2020-07
 * @lines 90
 */
public interface ICloseableElement extends Closeable {
	
	//method
	/**
	 * @throws ClosedArgumentException if the current {@link ICloseableElement} is closed.
	 */
	public default void assertIsOpen() {
		
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
	public default void close() {
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
	 * if the current {@link CloseableElement} has already a close dependency to the given element.
	 */
	public default void createCloseDependencyTo(final ICloseableElement element) {
		getRefCloseController().createCloseDependencyTo(element);
	}
	
	//method declaration
	/**
	 * @return the {@link CloseController} of the current {@link ICloseableElement}.
	 */
	public abstract CloseController getRefCloseController();
	
	//method
	/**
	 * @param element
	 * @return true if the current {@link ICloseableElement} has a close dependency to the given element.
	 */
	public default boolean hasCloseDependencyTo(final ICloseableElement element) {
		return getRefCloseController().hasCloseDependencyTo(element);
	}
	
	//method
	/**
	 * @return true if the current {@link ICloseableElement} has a pre-close action.
	 */
	public default boolean hasPreCloseAction() {
		return getRefCloseController().hasPreCloseAction();
	}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	public default boolean isClosed() {
		return getRefCloseController().isClosed();
	}
	
	//method
	/**
	 * Sets the pre-close action of the current {@link ICloseableElement}.
	 * 
	 * @param preCloseAction
	 * @throws ArgumentIsNullException if the given preCloseAction is null.
	 * @throws ClosedArgumentException if the current {@link ICloseableElement} is closed.
	 */
	public default void setPreCloseAction(final IAction preCloseAction) {
		getRefCloseController().setPreCloseAction(preCloseAction);
	}
}
