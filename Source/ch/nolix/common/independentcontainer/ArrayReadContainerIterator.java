//package declaration
package ch.nolix.common.independentcontainer;

//Java import
import java.util.Iterator;

import ch.nolix.common.constant.VariableNameCatalogue;
import ch.nolix.common.invalidargumentexception.ArgumentDoesNotHaveAttributeException;
import ch.nolix.common.invalidargumentexception.ArgumentIsNullException;

//class
final class ArrayReadContainerIterator<E> implements Iterator<E> {

	//attributes
	private final E[] array;
	private int nextElementIndex = 0;
	
	//method
	public ArrayReadContainerIterator(final E[] array) {
		
		//Asserts that the given array is not null.
		if (array == null) {
			throw new ArgumentIsNullException(VariableNameCatalogue.ARRAY);
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
			throw new ArgumentDoesNotHaveAttributeException(
				this,
				VariableNameCatalogue.NEXT_ELEMENT
			);
		}
		
		final var element = array[nextElementIndex];
		nextElementIndex++;
		
		return element;
	}
}
