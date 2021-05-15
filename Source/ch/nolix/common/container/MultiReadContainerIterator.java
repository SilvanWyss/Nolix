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
	}
	
	//method
	@Override
	public boolean hasNext() {
		
		if (currentIterator != null && currentIterator.hasNext()) {
			return true;
		}
		
		return rootIterator.hasNext();
	}
	
	//method
	@Override
	public E next() {
		
		if (!hasNext()) {
			throw new ArgumentDoesNotHaveAttributeException(
				this,
				LowerCaseCatalogue.NEXT_ELEMENT
			);
		}
		
		if (currentIterator == null || !currentIterator.hasNext()) {
			currentIterator = rootIterator.next().iterator();
		}
		
		return currentIterator.next();
	}
}
