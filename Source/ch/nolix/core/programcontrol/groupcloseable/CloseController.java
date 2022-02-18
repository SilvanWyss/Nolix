//package declaration
package ch.nolix.core.programcontrol.groupcloseable;

import ch.nolix.core.container.IContainer;
import ch.nolix.core.errorcontrol.invalidargumentexception.ArgumentIsNullException;
import ch.nolix.core.errorcontrol.invalidargumentexception.ClosedArgumentException;
import ch.nolix.core.errorcontrol.invalidargumentexception.InvalidArgumentException;
import ch.nolix.core.errorcontrol.validator.Validator;

//class
/**
 * @author Silvan Wyss
 * @date 2020-07-05
 */
public final class CloseController {
	
	//attribute
	private ClosePool parentClosePool;
	
	//constructor
	/**
	 * Creates a new {@link CloseController} for the given element.
	 * 
	 * @param element
	 * @throws ArgumentIsNullException if the given element is null.
	 */
	public CloseController(final GroupCloseable element) {
		parentClosePool = new ClosePool(element);
	}
	
	//method
	/**
	 * Closes the current {@link CloseController}.
	 */
	void close() {
		parentClosePool.close();
	}
	
	//method
	/**
	 * Adds a close dependency between the current {@link CloseController} and the given element.
	 * 
	 * When a {@link CloseController} is closed all of its close dependencies will be closed too and vice versa.
	 * 
	 * @param element
	 * @throws ArgumentIsNullException if the given element is null.
	 * @throws ClosedArgumentException if the current {@link CloseController} is closed.
	 * @throws InvalidArgumentException
	 * if the current {@link CloseController} has already a close dependency to the given element.
	 */
	void createCloseDependencyTo(GroupCloseable element) {
		parentClosePool.add(element);
	}
	
	//method
	/**
	 * @return the close dependencies of the current {@link CloseController}.
	 */
	IContainer<GroupCloseable> getRefCloseDependencies() {
		return parentClosePool.getRefElements();
	}
	
	//method
	/**
	 * @return true if the current {@link CloseController} is closed.
	 */
	boolean isClosed() {
		return parentClosePool.isClosed();
	}
	
	//method
	/**
	 * @param element
	 * @return true if the current {@link CloseController} has a close dependency to the given element.
	 */
	boolean hasCloseDependencyTo(final GroupCloseable element) {
		return parentClosePool.contains(element);
	}
	
	//method.
	/**
	 * Sets the {@link ClosePool} the current {@link CloseController} will belong to.
	 * 
	 * @param parentClosePool
	 * @throws ArgumentIsNullException if the given parentClosePool is null.
	 */
	void setParentClosePool(final ClosePool parentClosePool) {
		
		//Asserts that the given parentClosePool is not null.
		Validator.assertThat(parentClosePool).thatIsNamed("parent ClosePool").isNotNull();
		
		//Sets the parentClosePool of the current CloseController.
		this.parentClosePool = parentClosePool;
	}
}
