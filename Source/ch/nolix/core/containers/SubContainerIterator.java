//package declaration
package ch.nolix.core.containers;

//Java import
import java.util.Iterator;

//own imports
import ch.nolix.core.invalidArgumentException.ArgumentMissesAttributeException;
import ch.nolix.core.validator.Validator;

//package visible class
/**
 * @author Silvan Wyss
 * @month 2017-07
 * @lines 90
 * @param <E> The type of the elements of a list iterator.
 */
final class SubContainerIterator<E> implements Iterator<E> {
	
	//attributes
	private final int endIndex;
	private final Iterator<E> iterator;
	private int currentIndex;

	//constructor
	/**
	 * Creates a new sub container
	 * with the given container, start index and end index.
	 * 
	 * @param container
	 * @param startIndex
	 * @param endIndex
	 * @throws NullArgumentException if the given container is null.
	 * @throws NonPositiveArgumentexception if the given start index is not positive.
	 * @throws NonPositiveArgumentexception if the given end index is not positive.
	 */
	public SubContainerIterator(
		final Iterable<E> container,
		final int startIndex,
		final int endIndex) {
		
		Validator
		.suppose(container)
		.thatIsNamed("container")
		.isNotNull();
		
		Validator
		.suppose(startIndex)
		.thatIsNamed("start index")
		.isPositive();
		
		Validator
		.suppose(endIndex)
		.thatIsNamed("end index")
		.isPositive();
		
		Validator
		.suppose(endIndex)
		.thatIsNamed("end index")
		.isBiggerThanOrEquals(startIndex);
		
		this.endIndex = endIndex;
		
		currentIndex = startIndex;
		
		iterator = container.iterator();
		for (int i = 1; i < startIndex; i++) {
			iterator.next();
		}
	}

	//method
	/**
	 * @return true if this sub container iterator has a next element.
	 */
	@Override
	public boolean hasNext() {
		return (currentIndex <= endIndex);
	}

	//method
	/**
	 * @return the next element of this sub container iterator.
	 * @throws ArgumentMissesAttributeException if this sub container iterator does not have a next element.
	 */
	@Override
	public E next() {
		
		//Checks if this sub container iterator has a next element.
		if (!hasNext()) {
			throw new ArgumentMissesAttributeException(this, "next element");
		}
		
		currentIndex++;
		return iterator.next();
	}
}
