//package declaration
package ch.nolix.core.programcontrol.groupcloseable;

//own imports
import ch.nolix.core.errorcontrol.invalidargumentexception.ArgumentIsNullException;
import ch.nolix.core.errorcontrol.validator.GlobalValidator;
import ch.nolix.coreapi.containerapi.mainapi.IContainer;
import ch.nolix.coreapi.programcontrolapi.processproperty.CloseState;
import ch.nolix.coreapi.programcontrolapi.resourcecontrolapi.GroupCloseable;
import ch.nolix.coreapi.programcontrolapi.resourcecontrolapi.ICloseController;

//TODO: Beautify.
//class
/**
 * @author Silvan Wyss
 * @date 2020-07-05
 */
public final class CloseController implements ICloseController {
	
	//static method
	/**
	 * @param element
	 * @return a new {@link CloseController} for the given element.
	 * @throws ArgumentIsNullException if the given element is null.
	 */
	public static CloseController forElement(final GroupCloseable element) {
		return new CloseController(element);
	}
	
	//attribute
	private ClosePool parentClosePool;
	
	//constructor
	/**
	 * Creates a new {@link CloseController} for the given element.
	 * 
	 * @param element
	 * @throws ArgumentIsNullException if the given element is null.
	 */
	private CloseController(final GroupCloseable element) {
		parentClosePool = new ClosePool(element);
	}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void createCloseDependencyTo(final GroupCloseable element) {
		
		final var elementsToAdd = element.getRefCloseController().getRefElements();
		
		for (final var e : elementsToAdd) {
			((CloseController)e.getRefCloseController()).internalSetParentClosePool(parentClosePool);
		}
		
		parentClosePool.addElements(elementsToAdd);
	}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void closeAll() {
		parentClosePool.closeElementsIfStateIsOpen();
	}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	public IContainer<GroupCloseable> getRefElements() {
		return parentClosePool.getRefElements();
	}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean hasClosed() {
		return (parentClosePool.getState() == CloseState.CLOSED);
	}
	
	//method
	/**
	 * @param element
	 * @return true if the current {@link CloseController} has a close dependency to the given element.
	 */
	boolean internalHasCloseDependencyTo(final GroupCloseable element) {
		return parentClosePool.getRefElements().contains(element);
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
