//package declaration
package ch.nolix.core.programcontrol.groupcloseable;

//own imports
import ch.nolix.core.container.main.LinkedList;
import ch.nolix.core.errorcontrol.invalidargumentexception.ArgumentContainsElementException;
import ch.nolix.core.errorcontrol.logger.GlobalLogger;
import ch.nolix.coreapi.containerapi.mainapi.IContainer;
import ch.nolix.coreapi.programcontrolapi.processproperty.CloseState;
import ch.nolix.coreapi.programcontrolapi.resourcecontrolapi.GroupCloseable;
import ch.nolix.coreapi.programcontrolapi.resourcecontrolapi.IClosePool;

//TODO: Beautify
//class
/**
 * @author Silvan Wyss
 * @date 2020-07-06
 */
final class ClosePool implements IClosePool {
	
	//static method
	public static ClosePool forElement(final GroupCloseable element) {
		return new ClosePool(element);
	}
	
	//attribute
	private CloseState state = CloseState.OPEN;
	
	//multi-attribute
	private final LinkedList<GroupCloseable> elements = new LinkedList<>();
	
	//constructor
	public ClosePool(final GroupCloseable element) {
		addElement(element);
	}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void addElements(final IContainer<GroupCloseable> elements) {
		elements.forEach(this::addElement);
	}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void closeElementsIfStateIsOpen() {
		if (getState() == CloseState.OPEN) {
			closeElementsWhenStateIsOpen();
		}
	}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	public IContainer<GroupCloseable> getRefElements() {
		return elements;
	}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	public CloseState getState() {
		return state;
	}
	
	//method
	private void addElement(GroupCloseable element) {
		
		if (containsElement(element)) {
			throw ArgumentContainsElementException.forArgumentAndElement(this, element);
		}
		
		elements.addAtEnd(element);
	}
	
	//method
	/**
	 * Closes the elements of the current {@link IClosePool} for the case that
	 * the state of the current {@link IClosePool} is {@link CloseState#OPEN}.
	 */
	private void closeElementsWhenStateIsOpen() {
		
		state = CloseState.ON_CLOSING;
		
		for (final var e : elements) {
			letNoteClose(e);
		}
		
		state = CloseState.CLOSED;
	}
	
	//method
	/**
	 * @param element
	 * @return true if the current {@link ClosePool} contains the given element.
	 */
	private boolean containsElement(final GroupCloseable element) {
		return elements.contains(element);
	}
	
	//method
	/**
	 * Lets the given element note a close.
	 * 
	 * @param element
	 */
	private void letNoteClose(final GroupCloseable element) {
		try {
			element.noteClose();
		} catch (final Throwable exception) {
			GlobalLogger.logError(exception);
		}
	}
}
