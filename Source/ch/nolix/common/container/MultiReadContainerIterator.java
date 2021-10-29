//package declaration
package ch.nolix.common.container;

//Java imports
import java.util.Iterator;

//own imports
import ch.nolix.common.constant.LowerCaseCatalogue;
import ch.nolix.common.errorcontrol.invalidargumentexception.ArgumentDoesNotHaveAttributeException;

//class
final class MultiReadContainerIterator<E> implements Iterator<E> {
	
	//attribute
	private final Iterator<IContainer<E>> rootIterator;
	
	//optional attribute
	private Iterator<E> currentIterator;
	
	//constructor
	public MultiReadContainerIterator(final IContainer<IContainer<E>> containers) {
		
		rootIterator = containers.iterator();
		
		if (rootIterator.hasNext()) {
			currentIterator = rootIterator.next().iterator();
		}
	}
	
	//method
	@Override
	public boolean hasNext() {
		return (currentIterator != null && currentIterator.hasNext());
	}
	
	//method
	@Override
	public E next() {
		
		if (!hasNext()) {
			throw new ArgumentDoesNotHaveAttributeException(this, LowerCaseCatalogue.NEXT_ELEMENT);
		}
		
		final var element = currentIterator.next();
		
		if (!currentIterator.hasNext()) {
			currentIterator = rootIterator.hasNext() ? rootIterator.next().iterator() : null;
		}
		
		return element;
	}
}
