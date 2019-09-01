//package declaration
package ch.nolix.common.containers;

//Java import
import java.util.Iterator;

import ch.nolix.common.constants.VariableNameCatalogue;
import ch.nolix.common.invalidArgumentExceptions.ArgumentDoesNotHaveAttributeException;

//package-visible class
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
		
		if (rootIterator.hasNext()) {
			return true;
		}
		
		return false;
	}
	
	//method
	@Override
	public E next() {
		
		if (!hasNext()) {
			throw new ArgumentDoesNotHaveAttributeException(
				this,
				VariableNameCatalogue.NEXT_ELEMENT
			);
		}
		
		if (currentIterator == null || !currentIterator.hasNext()) {
			currentIterator = rootIterator.next().iterator();
		}
		
		return currentIterator.next();
	}
}
