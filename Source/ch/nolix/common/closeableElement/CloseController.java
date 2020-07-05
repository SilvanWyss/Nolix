//package declaration
package ch.nolix.common.closeableElement;

//own imports
import ch.nolix.common.container.IContainer;
import ch.nolix.common.functionAPI.IAction;
import ch.nolix.common.invalidArgumentException.ArgumentIsNullException;
import ch.nolix.common.invalidArgumentException.ClosedArgumentException;
import ch.nolix.common.invalidArgumentException.InvalidArgumentException;
import ch.nolix.common.validator.Validator;

//class
/**
 * @author Silvan Wyss
 * @month 2020-07
 * @lines 150
 */
public final class CloseController {
	
	//attribute
	private ClosePool parentClosePool;
	
	//optional attribute
	private IAction preCloseAction;
	
	//constructor
	/**
	 * Creates a new {@link CloseController} for the given element.
	 * 
	 * @param element
	 * @throws ArgumentIsNullException if the given element is null.
	 */
	public CloseController(final ICloseableElement element) {
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
	void createCloseDependencyTo(ICloseableElement element) {
		parentClosePool.add(element);
	}
	
	//method
	/**
	 * @return the close dependencies of the current {@link ClosableController}.
	 */
	IContainer<ICloseableElement> getRefCloseDependencies() {
		return parentClosePool.getRefElements();
	}
	
	//method
	/**
	 * @return true if the current {@link CloseController} has a pre-close action.
	 */
	boolean hasPreCloseAction() {
		return (preCloseAction != null);
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
	boolean hasCloseDependencyTo(final ICloseableElement element) {
		return parentClosePool.contains(element);
	}
	
	//method
	/**
	 * Runs the pre-close action of the current {@link CloseController}
	 * if the current {@link CloseController} has a pre-close action.
	 */
	final void runProbablePreCloseAction() {
		
		//Handles the case that the current CloseController has a pre-close action.
		if (hasPreCloseAction()) {
			preCloseAction.run();
		}
	}
	
	//method.
	/**
	 * Sets the {@link ClosePool} the current {@link ClosableController} will belong to.
	 * 
	 * @param parentClosePool
	 * @throws ArgumentIsNullException if the given parentClosePool is null.
	 */
	final void setParentCloseController(final ClosePool parentClosePool) {
		
		//Asserts that the given parentClosePool is not null.
		Validator.assertThat(parentClosePool).thatIsNamed("parent ClosePool").isNotNull();
		
		//Sets the parentClosePool of the current CloseController.
		this.parentClosePool = parentClosePool;
	}

	//method
	/**
	 * Sets the pre-close action of the current {@link CloseController}.
	 * 
	 * @param preCloseAction
	 * @throws ArgumentIsNullException if the given preCloseAction is null.
	 * @throws ClosedArgumentException if the current {@link CloseController} is closed.
	 */
	void setPreCloseAction(final IAction preCloseAction) {
		
		//Asserts that the given preCloseAction is not null.
		Validator.assertThat(preCloseAction).thatIsNamed("pre-close action").isNotNull();
		
		//Asserts that the current CloseController is open.
		assertIsOpen();
		
		//Sets the preCloseAction of the current CloseController.
		this.preCloseAction = preCloseAction;
	}
	
	//method
	/**
	 * @throws ClosedArgumentException if the current {@link CloseController} is closed.
	 */
	private void assertIsOpen() {
		
		//Asserts that the current CloseController is open.
		if (isClosed()) {
			throw new ClosedArgumentException(this);
		}
	}
}
