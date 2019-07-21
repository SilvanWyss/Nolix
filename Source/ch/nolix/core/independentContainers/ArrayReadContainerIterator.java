//package declaration
package ch.nolix.core.independentContainers;

//Java import
import java.util.Iterator;

//own imports
import ch.nolix.core.constants.VariableNameCatalogue;
import ch.nolix.core.invalidArgumentException.NullArgumentException;
import ch.nolix.core.invalidArgumentException.ArgumentMissesAttributeException;

//package-visible class
final class ArrayReadContainerIterator<E> implements Iterator<E> {

	//attributes
	private final E[] array;
	private int nextElementIndex = 0;
	
	//method
	public ArrayReadContainerIterator(final E[] array) {
		
		//Checks if the given array is not null.
		if (array == null) {
			throw new NullArgumentException(VariableNameCatalogue.ARRAY);
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
		
		//Checks if the current array read container iterator has a next element.
		if (!hasNext()) {
			throw new ArgumentMissesAttributeException(
				this,
				VariableNameCatalogue.NEXT_ELEMENT
			);
		}
		
		final var element = array[nextElementIndex];
		nextElementIndex++;
		
		return element;
	}
}
