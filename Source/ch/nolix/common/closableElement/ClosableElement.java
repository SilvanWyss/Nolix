//package declaration
package ch.nolix.common.closableElement;

//own imports
import ch.nolix.common.containers.ReadContainer;
import ch.nolix.common.invalidArgumentExceptions.ClosedArgumentException;
import ch.nolix.common.invalidArgumentExceptions.InvalidArgumentException;
import ch.nolix.common.skillAPI.Closable;
import ch.nolix.common.validator.Validator;

//class
/**
 * @author Silvan Wyss
 * @month 2016-05
 * @lines 120
 */
public abstract class ClosableElement implements Closable {
	
	//attribute
	private CloseController parentCloseController = new CloseController(this);
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final void close() {
		
		//Handles the case that the current ClosableElement is alive.
		if (isOpen()) {
			parentCloseController.close();
		}
	}
	
	//method
	/**
	 * @param element
	 * @return true if the current {@link ClosableElement}
	 * has a close dependency to the given element.
	 */
	public final boolean hasCloseDependencyTo(final ClosableElement element) {
		return parentCloseController.containsElement(element);
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
	 * Creates a close dependency
	 * between the current {@link ClosableElement} and the given element.
	 * 
	 * When a {@link ClosableElement} is closed
	 * all of its close dependencies will be closed too and vice versa.
	 * 
	 * @param element
	 * @throws InvalidArgumentException if the current {@link ClosableElement} is closed.
	 * @throws InvalidArgumentException
	 * if the current {@link ClosableElement} has already a close dependency to the given element.
	 */
	protected final void createCloseDependency(final ClosableElement element) {
		
		//Checks if the current ClosableElement is alive.
		supposeIsAlive();
		
		//Checks if the current ClosableElement
		//does not have already a close dependency to the given element.
		if (hasCloseDependencyTo(element)) {
			throw new InvalidArgumentException(
				this,
				"has already a close dependency to the given element"
			);
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
	 * @throws ClosedArgumentException if the current {@link ClosableElement} is closed.
	 */
	protected final void supposeIsAlive() {
		
		//Checks if the current ClosableElement is alive.
		if (isClosed()) {
			throw new ClosedArgumentException(this);
		}
	}
	
	//method
	/**
	 * @return the close dependencies of the current {@link ClosableElement}.
	 */
	final ReadContainer<ClosableElement> getRefCloseDependencies() {
		return parentCloseController.getRefElements();
	}
	
	//method.
	/**
	 * Sets the close controller the current {@link ClosableElement} will belong to.
	 * 
	 * @param parentCloseController
	 * @throws ArgumentIsNullException if the given parent close controller is null.
	 */
	final void setParentCloseController(final CloseController parentCloseController) {
		
		//Checks if the given parent close controller is not null.
		Validator
		.suppose(parentCloseController)
		.thatIsNamed("parent close controller")
		.isNotNull();
		
		//Sets the parent close controller of the current ClosableElement.
		this.parentCloseController = parentCloseController;
	}
}
