//package declaration
package ch.nolix.core.container;

//Java import
import java.util.Iterator;

//own imports
import ch.nolix.core.constants.VariableNameCatalogue;
import ch.nolix.core.invalidArgumentException.ArgumentMissesAttributeException;
import ch.nolix.core.validator.Validator;

//package-visible class
/**
 * A read array container iterator
 * is an iterator for a read array container.
 * 
 * @author Silvan Wyss
 * @month 2017-11
 * @lines 80
 * @param <E> The type of the elements of the read array container
 * a read array container iterator belongs to.
 */
final class ArrayReadContainerIterator<E> implements Iterator<E> {

	//attribute
	/**
	 * The array that belongs to the read array container
	 * this read array container iterator belongs to.
	 */
	final E[] array;
	
	//attribute
	int nextElementIndex = 0;
	
	//constructor
	/**
	 * Creates a new read array container iterator
	 * that belongs to the read array container
	 * the given array belongs to.
	 * 
	 * @param array
	 * @throws NullArgumentException if the given array is null.
	 */
	public ArrayReadContainerIterator(final E[] array) {
		
		//Checks if the given array is not null.
		Validator
		.suppose(array)
		.thatIsNamed(VariableNameCatalogue.ARRAY)
		.isNotNull();
		
		//Sets the array of hits read array container iterator.
		this.array = array;
	}
	
	//method
	/**
	 * @return true if this read array container iterator has a next element.
	 */
	@Override
	public boolean hasNext() {
		return (nextElementIndex < array.length);
	}

	//method
	/**
	 * @return the next element of this read array container iterator.
	 * @throws ArgumentMissesAttributeException
	 * if this read array container iterator does not have a next element.
	 */
	@Override
	public E next() {
		
		//Checks if this read array container iterator has a next element.
		if (!hasNext()) {
			throw new ArgumentMissesAttributeException(
				this,
				VariableNameCatalogue.NEXT_ELEMENT
			);
		}
		
		final E element = array[nextElementIndex];
		
		nextElementIndex++;
		
		return element;
	}
}
