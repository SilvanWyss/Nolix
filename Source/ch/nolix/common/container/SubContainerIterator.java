//package declaration
package ch.nolix.common.container;

//Java import
import java.util.Iterator;

//own imports
import ch.nolix.common.constant.LowerCaseCatalogue;
import ch.nolix.common.errorcontrol.invalidargumentexception.ArgumentDoesNotHaveAttributeException;
import ch.nolix.common.errorcontrol.invalidargumentexception.ArgumentIsNullException;
import ch.nolix.common.errorcontrol.invalidargumentexception.NonPositiveArgumentException;
import ch.nolix.common.errorcontrol.validator.Validator;

//class
/**
 * @author Silvan Wyss
 * @date 2017-08-27
 * @lines 80
 * @param <E> is the type of the elements of a {@link SubContainerIterator}.
 */
final class SubContainerIterator<E> implements Iterator<E> {
	
	//attributes
	private final int endIndex;
	private final Iterator<E> iterator;
	private int currentIndex;

	//constructor
	/**
	 * Creates a new {@link SubContainerIterator} with the given container, startIndex and endIndex.
	 * 
	 * @param container
	 * @param startIndex
	 * @param endIndex
	 * @throws ArgumentIsNullException if the given container is null.
	 * @throws NonPositiveArgumentException if the given startIndex is not positive.
	 * @throws NonPositiveArgumentException if the given endIndex is not positive.
	 */
	public SubContainerIterator(final Iterable<E> container, final int startIndex, final int endIndex) {
		
		Validator.assertThat(container).thatIsNamed(LowerCaseCatalogue.CONTAINER).isNotNull();
		Validator.assertThat(startIndex).thatIsNamed(LowerCaseCatalogue.START_INDEX).isPositive();
		Validator.assertThat(endIndex).thatIsNamed(LowerCaseCatalogue.END_INDEX).isPositive();
		Validator.assertThat(endIndex).thatIsNamed(LowerCaseCatalogue.END_INDEX).isBiggerThanOrEquals(startIndex);
		
		this.endIndex = endIndex;
		
		currentIndex = startIndex;
		
		iterator = container.iterator();
		for (int i = 1; i < startIndex; i++) {
			iterator.next();
		}
	}

	//method
	/**
	 * @return true if the current {@link SubContainerIterator} has a next element.
	 */
	@Override
	public boolean hasNext() {
		return (currentIndex <= endIndex);
	}

	//method
	/**
	 * @return the next element of this {@link SubContainerIterator}.
	 * @throws ArgumentDoesNotHaveAttributeException
	 * if the current {@link SubContainerIterator} does not have a next element.
	 */
	@Override
	public E next() {
		
		//Asserts that the current @link SubContainerIterator has a next element.
		if (!hasNext()) {
			throw new ArgumentDoesNotHaveAttributeException(this, LowerCaseCatalogue.NEXT_ELEMENT);
		}
		
		currentIndex++;
		return iterator.next();
	}
}
