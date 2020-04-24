//package declaration
package ch.nolix.common.container;

//Java import
import java.util.Iterator;

//own import
import ch.nolix.common.invalidArgumentExceptions.ArgumentDoesNotHaveAttributeException;

//class
final class SingleContainerIterator<E> implements Iterator<E> {
	
	//attribute
	private E element;
	
	//constructor
	public SingleContainerIterator(final SingleContainer<E> parentSingleContainer) {
		element = parentSingleContainer.isEmpty() ? null : parentSingleContainer.getRefElement();
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
