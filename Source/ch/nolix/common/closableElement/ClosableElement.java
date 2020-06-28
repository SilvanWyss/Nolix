//package declaration
package ch.nolix.common.closableElement;

//own imports
import ch.nolix.common.container.ReadContainer;
import ch.nolix.common.functionAPI.IAction;
import ch.nolix.common.invalidArgumentException.ClosedArgumentException;
import ch.nolix.common.invalidArgumentException.InvalidArgumentException;
import ch.nolix.common.skillAPI.Closable;
import ch.nolix.common.validator.Validator;

//class
/**
 * @author Silvan Wyss
 * @month 2016-05
 * @lines 150
 */
public abstract class ClosableElement implements Closable {
	
	//attribute
	private CloseController parentCloseController = new CloseController(this);
	
	//optional attribute
	private IAction preCloseAction;
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final void close() {
		parentCloseController.close();
	}
	
	//method
	/**
	 * @param element
	 * @return true if the current {@link ClosableElement} has a close dependency to the given element.
	 */
	public final boolean hasCloseDependencyTo(final ClosableElement element) {
		return parentCloseController.containsElement(element);
	}
	
	//method
	/**
	 * @return true if the current {@link ClosableElement} has a pre-close action.
	 */
	public final boolean hasPreCloseAction() {
		return (preCloseAction != null);
	}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final boolean isClosed() {
		return parentCloseController.isClosed();
	}
	
	//method
	/**
	 * Sets the pre-close action of the current {@link ClosableElement}.
	 * 
	 * @param preCloseAction
	 * @throws ArgumentIsNullException if the given preCloseAction is null.
	 */
	public final void setPreCloseAction(final IAction preCloseAction) {
		
		//Asserts that the given pre-close action is not null.
		Validator.assertThat(preCloseAction).thatIsNamed("pre-close action").isNotNull();
		
		//Sets the pre-close action of the current ClosableElement.
		this.preCloseAction = preCloseAction;
	}
	
	//method
	/**
	 * @throws ClosedArgumentException if the current {@link ClosableElement} is closed.
	 */
	protected final void assertIsOpen() {
		
		//Asserts that the current ClosableElement is open.
		if (isClosed()) {
			throw new ClosedArgumentException(this);
		}
	}
	
	//method
	/**
	 * Creates a close dependency
	 * between the current {@link ClosableElement} and the given element.
	 * 
	 * When a {@link ClosableElement} is closed
	 * all of its close dependencies will be closed too and vice versa.
	 * 
	 * @param element
	 * @throws ClosedArgumentException if the current {@link ClosableElement} is closed.
	 * @throws InvalidArgumentException
	 * if the current {@link ClosableElement} has already a close dependency to the given element.
	 */
	protected final void createCloseDependency(final ClosableElement element) {
		
		//Asserts that the current ClosableElement is open.
		assertIsOpen();
		
		//Asserts that the current ClosableElement does not have already a close dependency to the given element.
		if (hasCloseDependencyTo(element)) {
			throw new InvalidArgumentException(this, "has already a close dependency to the given element");
		}
		
		parentCloseController.addElement(element);
	}
	
	//method declaration
	/**
	 * Lets the current {@link ClosableElement} note a close.
	 */
	protected abstract void noteClose();
	
	//method
	/**
	 * @return the close dependencies of the current {@link ClosableElement}.
	 */
	final ReadContainer<ClosableElement> getRefCloseDependencies() {
		return parentCloseController.getRefElements();
	}
	
	//method
	/**
	 * Runs the pre-close action of the current {@link ClosableElement} if it has one.
	 */
	final void runProbablePreCloseAction() {
		if (hasPreCloseAction()) {
			preCloseAction.run();
		}
	}
	
	//method.
	/**
	 * Sets the {@link CloseController} the current {@link ClosableElement} will belong to.
	 * 
	 * @param parentCloseController
	 * @throws ArgumentIsNullException if the given parentCloseController is null.
	 */
	final void setParentCloseController(final CloseController parentCloseController) {
		
		//Asserts that the given parentCloseController is not null.
		Validator.assertThat(parentCloseController).thatIsNamed("parent close controller").isNotNull();
		
		//Sets the parentCloseController of the current ClosableElement.
		this.parentCloseController = parentCloseController;
	}
}
