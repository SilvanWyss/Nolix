//package declaration
package ch.nolix.core.container;

//Java imports
import java.util.Iterator;

//own imports
import ch.nolix.core.errorcontrol.invalidargumentexception.ArgumentDoesNotHaveAttributeException;

//class
final class SingleContainerIterator<E> implements Iterator<E> {
	
	//attribute
	private E element;
	
	//constructor
	public SingleContainerIterator(final SingleContainer<E> parentSingleContainer) {
		if (parentSingleContainer.isEmpty()) {
			element = null;
		} else {
			element = parentSingleContainer.getRefElement();
		}
	}
	
	//method
	@Override
	public boolean hasNext() {
		return (element != null);
	}
	
	//method
	@Override
	public E next() {
		
		if (!hasNext()) {
			throw new ArgumentDoesNotHaveAttributeException(this, "next element");
		}
		
		final var temp = element;
		element = null;
		return temp;
	}
}
