//package declaration
package ch.nolix.core.container;

//Java import
import java.util.Iterator;

//own imports
import ch.nolix.core.constants.VariableNameCatalogue;
import ch.nolix.primitive.invalidStateException.UnexistingAttributeException;
import ch.nolix.primitive.validator2.Validator;

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
final class ReadArrayContainerIterator<E> implements Iterator<E> {

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
	 * @throws NullArgumentException if the given array is not an instance.
	 */
	public ReadArrayContainerIterator(final E[] array) {
		
		//Checks if the given array is an instance.
		Validator
		.suppose(array)
		.thatIsNamed(VariableNameCatalogue.ARRAY)
		.isInstance();
		
		//Sets the array of hits read array container iterator.
		this.array = array;
	}
	
	//method
	/**
	 * @return true if this read array container iterator has a next element.
	 */
	public boolean hasNext() {
		return (nextElementIndex < array.length);
	}

	//method
	/**
	 * @return the next element of this read array container iterator.
	 * @throws UnexistingAttributeException
	 * if this read array container iterator has no next element.
	 */
	public E next() {
		
		//Checks if this read array container iterator has a next element.
		if (!hasNext()) {
			throw new UnexistingAttributeException(
				this,
				VariableNameCatalogue.NEXT_ELEMENT
			);
		}
		
		final E element = array[nextElementIndex];		
		
		nextElementIndex++;		
		
		return element;
	}
}
