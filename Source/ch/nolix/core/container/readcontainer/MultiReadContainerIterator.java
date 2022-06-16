//package declaration
package ch.nolix.core.container.readcontainer;

//Java imports
import java.util.Iterator;

import ch.nolix.core.container.IContainer;
import ch.nolix.core.errorcontrol.invalidargumentexception.ArgumentDoesNotHaveAttributeException;
import ch.nolix.core.programatom.name.LowerCaseCatalogue;

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
			if (!rootIterator.hasNext()) {
				currentIterator = null;
			} else {
				currentIterator = rootIterator.next().iterator();
			}
		}
		
		return element;
	}
}
