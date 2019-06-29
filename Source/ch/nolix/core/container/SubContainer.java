//package declaration
package ch.nolix.core.container;

//Java import
import java.util.Iterator;

//own imports
import ch.nolix.core.constants.CharacterCatalogue;
import ch.nolix.core.validator.Validator;

//class
/**
 * A sub container can iterate over a part of another container.
 * 
 * A sub container must not use the methods of the accessed container
 * except the iterator method.
 * The reason is that the accessed container can be a specialized container
 * that does not use its iterator in any of its declared or overwritten method.
 * 
 * @author Silvan Wyss
 * @month 2017-08
 * @lines 100
 * @param <E> The type of the elements of a sub container.
 */
public final class SubContainer<E> implements IContainer<E> {

	//attributes
	private final IContainer<E> container;
	private final int startIndex;
	private final int endIndex;
	
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
	 * @throws SmallerArgumentException if the given end index is smaller than the given start index.
	 */
	public SubContainer(
		final IContainer<E> container,
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
		.isNotSmallerThan(startIndex);
		
		Validator
		.suppose(endIndex)
		.thatIsNamed("end index")
		.isNotBiggerThan(container.getSize());
		
		this.container = container;
		this.startIndex = startIndex;
		this.endIndex = endIndex;
	}
	
	//method
	/**
	 * @return the number of elements of this sub container.
	 */
	@Override
	public int getSize() {
		return (endIndex - startIndex + 1);
	}

	//method
	/**
	 * @return a new iterator for this sub container.
	 */
	@Override
	public Iterator<E> iterator() {
		return
		new SubContainerIterator<>(
			container,
			startIndex,
			endIndex
		);
	}
	
	//method
	/**
	 * The complexity of this method is O(n) if this sub container contains n elements.
	 * 
	 * @return a string representation of this sub container.
	 */
	@Override
	public String toString() {
		return toString(CharacterCatalogue.COMMA);
	}
}
