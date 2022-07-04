//package declaration
package ch.nolix.core.programcontrol.groupcloseable;

import ch.nolix.core.errorcontrol.invalidargumentexception.ArgumentIsNullException;
import ch.nolix.core.errorcontrol.validator.GlobalValidator;
import ch.nolix.coreapi.containerapi.mainapi.IContainer;
import ch.nolix.coreapi.programcontrolapi.resourcecontrolapi.ICloseController;

//class
/**
 * @author Silvan Wyss
 * @date 2020-07-05
 */
public final class CloseController implements ICloseController {
	
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
	 * {@inheritDoc}
	 */
	@Override
	public void createCloseDependencyTo(GroupCloseable element) {
		parentClosePool.add(element);
	}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void closeAll() {
		parentClosePool.close();
	}
	
	//method
	/**
	 * @return the close dependencies of the current {@link CloseController}.
	 */
	IContainer<GroupCloseable> internalGetRefCloseDependencies() {
		return parentClosePool.getRefElements();
	}
	
	//method
	/**
	 * @return true if the current {@link CloseController} is closed.
	 */
	boolean internalIsClosed() {
		return parentClosePool.isClosed();
	}
	
	//method
	/**
	 * @param element
	 * @return true if the current {@link CloseController} has a close dependency to the given element.
	 */
	boolean internalHasCloseDependencyTo(final GroupCloseable element) {
		return parentClosePool.contains(element);
	}
	
	//method.
	/**
	 * Sets the {@link ClosePool} the current {@link CloseController} will belong to.
	 * 
	 * @param parentClosePool
	 * @throws ArgumentIsNullException if the given parentClosePool is null.
	 */
	void internalSetParentClosePool(final ClosePool parentClosePool) {
		
		//Asserts that the given parentClosePool is not null.
		GlobalValidator.assertThat(parentClosePool).thatIsNamed("parent ClosePool").isNotNull();
		
		//Sets the parentClosePool of the current CloseController.
		this.parentClosePool = parentClosePool;
	}
}
