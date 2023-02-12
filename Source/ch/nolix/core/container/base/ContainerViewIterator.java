//package declaration
package ch.nolix.core.container.base;

//Java imports
import java.util.Iterator;

//own imports
import ch.nolix.core.errorcontrol.invalidargumentexception.ArgumentDoesNotHaveAttributeException;
import ch.nolix.core.errorcontrol.invalidargumentexception.ArgumentIsNullException;
import ch.nolix.core.errorcontrol.invalidargumentexception.NonPositiveArgumentException;
import ch.nolix.core.errorcontrol.validator.GlobalValidator;
import ch.nolix.core.programatom.name.LowerCaseCatalogue;

//class
/**
 * @author Silvan Wyss
 * @date 2017-08-27
 * @param <E> is the type of the elements of a {@link ContainerViewIterator}.
 */
final class ContainerViewIterator<E> implements Iterator<E> {
	
	//attributes
	private final int endIndex;
	private final Iterator<E> iterator;
	private int currentIndex;

	//constructor
	/**
	 * Creates a new {@link ContainerViewIterator} with the given container, startIndex and endIndex.
	 * 
	 * @param container
	 * @param startIndex
	 * @param endIndex
	 * @throws ArgumentIsNullException if the given container is null.
	 * @throws NonPositiveArgumentException if the given startIndex is not positive.
	 * @throws NonPositiveArgumentException if the given endIndex is not positive.
	 */
	public ContainerViewIterator(final Iterable<E> container, final int startIndex, final int endIndex) {
		
		GlobalValidator.assertThat(container).thatIsNamed(LowerCaseCatalogue.CONTAINER).isNotNull();
		GlobalValidator.assertThat(startIndex).thatIsNamed(LowerCaseCatalogue.START_INDEX).isPositive();
		GlobalValidator.assertThat(endIndex).thatIsNamed(LowerCaseCatalogue.END_INDEX).isPositive();
		GlobalValidator.assertThat(endIndex).thatIsNamed(LowerCaseCatalogue.END_INDEX).isBiggerThanOrEquals(startIndex);
		
		this.endIndex = endIndex;
		
		currentIndex = startIndex;
		
		iterator = container.iterator();
		for (var i = 1; i < startIndex; i++) {
			iterator.next();
		}
	}

	//method
	/**
	 * @return true if the current {@link ContainerViewIterator} has a next element.
	 */
	@Override
	public boolean hasNext() {
		return (currentIndex <= endIndex);
	}

	//method
	/**
	 * @return the next element of the current {@link ContainerViewIterator}.
	 * @throws ArgumentDoesNotHaveAttributeException
	 * if the current {@link ContainerViewIterator} does not have a next element.
	 */
	@Override
	public E next() {
		
		//Asserts that the current @link SubContainerIterator has a next element.
		if (!hasNext()) {
			throw ArgumentDoesNotHaveAttributeException.forArgumentAndAttributeName(this, LowerCaseCatalogue.NEXT_ELEMENT);
		}
		
		currentIndex++;
		return iterator.next();
	}
}