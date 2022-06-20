//package declaration
package ch.nolix.core.independent.independentcontainer;

//Java imports
import java.util.Iterator;

import ch.nolix.core.errorcontrol.invalidargumentexception.ArgumentDoesNotHaveAttributeException;
import ch.nolix.core.errorcontrol.invalidargumentexception.ArgumentIsNullException;
import ch.nolix.core.programatom.name.LowerCaseCatalogue;

//class
final class ArrayReadContainerIterator<E> implements Iterator<E> {

	//attributes
	private final E[] array;
	private int nextElementIndex;
	
	//method
	public ArrayReadContainerIterator(final E[] array) {
		
		//Asserts that the given array is not null.
		if (array == null) {
			throw ArgumentIsNullException.forArgumentName(LowerCaseCatalogue.ARRAY);
		}
		
		//Sets the array of the current array read container iterator.
		this.array = array;
	}
	
	//method
	@Override
	public boolean hasNext() {
		return (nextElementIndex < array.length);
	}

	//method
	@Override
	public E next() {
		
		//Asserts that the current array read container iterator has a next element.
		if (!hasNext()) {
			throw ArgumentDoesNotHaveAttributeException.forArgumentAndAttributeName(
				this,
				LowerCaseCatalogue.NEXT_ELEMENT
			);
		}
		
		final var element = array[nextElementIndex];
		nextElementIndex++;
		
		return element;
	}
}
