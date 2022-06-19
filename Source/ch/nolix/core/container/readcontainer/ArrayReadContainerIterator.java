//package declaration
package ch.nolix.core.container.readcontainer;

//Java imports
import java.util.Iterator;

import ch.nolix.core.errorcontrol.invalidargumentexception.ArgumentDoesNotHaveAttributeException;
import ch.nolix.core.errorcontrol.invalidargumentexception.ArgumentIsNullException;
import ch.nolix.core.errorcontrol.validator.GlobalValidator;
import ch.nolix.core.programatom.name.LowerCaseCatalogue;

//class
/**
 * A {@link ArrayReadContainerIterator} is an iterator for a {@link ArrayReadContainer}.
 * 
 * @author Silvan Wyss
 * @date 2017-11-26
 * @param <E> is the type of the elements of the {@link ArrayReadContainer}
 * a {@link ArrayReadContainerIterator} belongs to.
 */
final class ArrayReadContainerIterator<E> implements Iterator<E> {
	
	//attribute
	/**
	 * The array that belongs to the {@link ArrayReadContainer}
	 * the current {@link ArrayReadContainerIterator} iterator belongs to.
	 */
	private final E[] array;
	
	//attribute
	private int nextElementIndex;
	
	//constructor
	/**
	 * Creates a new {@link ArrayReadContainerIterator}
	 * that will belong to the {@link ArrayReadContainer}
	 * the given array belongs to.
	 * 
	 * @param array
	 * @throws ArgumentIsNullException if the given array is null.
	 */
	public ArrayReadContainerIterator(final E[] array) {
		
		//Asserts that the given array is not null.
		GlobalValidator
		.assertThat(array)
		.thatIsNamed(LowerCaseCatalogue.ARRAY)
		.isNotNull();
		
		//Sets the array of the current {@link ArrayReadContainerIterator}.
		this.array = array;
	}
	
	//method
	/**
	 * @return true if the current {@link ArrayReadContainerIterator} has a next element.
	 */
	@Override
	public boolean hasNext() {
		return (nextElementIndex < array.length);
	}
	
	//method
	/**
	 * @return the next element of the current {@link ArrayReadContainerIterator}.
	 * @throws ArgumentDoesNotHaveAttributeException
	 * if the current {@link ArrayReadContainerIterator} iterator does not have a next element.
	 */
	@Override
	public E next() {
		
		//Asserts that the current ArrayReadContainerIterator has a next element.
		//For a better performance, this implementation does not use all comfortable methods.
		if (nextElementIndex >= array.length) {
			throw ArgumentDoesNotHaveAttributeException.forArgumentAndAttributeName(this, LowerCaseCatalogue.NEXT_ELEMENT);
		}
		
		final var element = array[nextElementIndex];
		
		nextElementIndex++;
		
		return element;
	}
}
