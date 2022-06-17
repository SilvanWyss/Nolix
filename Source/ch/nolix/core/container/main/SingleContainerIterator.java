//package declaration
package ch.nolix.core.container.main;

//Java imports
import java.util.Iterator;

//own imports
import ch.nolix.core.errorcontrol.invalidargumentexception.ArgumentDoesNotHaveAttributeException;

//class
final class SingleContainerIterator<E> implements Iterator<E> {
	
	//attribute
	private E element;
	
	//constructor
	public SingleContainerIterator(final E element) {
		this.element = element;
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
