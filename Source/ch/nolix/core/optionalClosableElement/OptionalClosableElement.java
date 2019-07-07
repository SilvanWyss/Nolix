//package declaration
package ch.nolix.core.optionalClosableElement;

//own imports
import ch.nolix.core.container.ReadContainer;
import ch.nolix.core.invalidArgumentException.ClosedArgumentException;
import ch.nolix.core.invalidArgumentException.InvalidArgumentException;
import ch.nolix.core.skillAPI.OptionalClosable;
import ch.nolix.core.validator.Validator;

//abstract class
/**
 * @author Silvan Wyss
 * @month 2016-05
 * @lines 120
 */
public abstract class OptionalClosableElement implements OptionalClosable {
	
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
	 * @return true if the current {@link OptionalClosableElement}
	 * has a close dependency to the given element.
	 */
	public final boolean hasCloseDependencyTo(final OptionalClosableElement element) {
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
	 * between the current {@link OptionalClosableElement} and the given element.
	 * 
	 * When a {@link OptionalClosableElement} is closed
	 * all of its close dependencies will be closed too and vice versa.
	 * 
	 * @param element
	 * @throws InvalidArgumentException if the current {@link OptionalClosableElement} is closed.
	 * @throws InvalidArgumentException
	 * if the current {@link OptionalClosableElement} has already a close dependency to the given element.
	 */
	protected final void createCloseDependency(final OptionalClosableElement element) {
		
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
	
	//abstract method
	/**
	 * Lets the current {@link OptionalClosableElement} note a close.
	 */
	protected abstract void noteClose();
	
	//method
	/**
	 * @throws ClosedArgumentException if the current {@link OptionalClosableElement} is closed.
	 */
	protected final void supposeIsAlive() {
		
		//Checks if the current ClosableElement is alive.
		if (isClosed()) {
			throw new ClosedArgumentException(this);
		}
	}
	
	//package-visible method
	/**
	 * @return the close dependencies of the current {@link OptionalClosableElement}.
	 */
	final ReadContainer<OptionalClosableElement> getRefCloseDependencies() {
		return parentCloseController.getRefElements();
	}
	
	//package-visible method.
	/**
	 * Sets the close controller the current {@link OptionalClosableElement} will belong to.
	 * 
	 * @param parentCloseController
	 * @throws NullArgumentException if the given parent close controller is null.
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
