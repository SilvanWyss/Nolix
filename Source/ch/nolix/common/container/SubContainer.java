//package declaration
package ch.nolix.common.container;

//Java import
import java.util.Iterator;

//own imports
import ch.nolix.common.constant.CharacterCatalogue;
import ch.nolix.common.constant.LowerCaseCatalogue;
import ch.nolix.common.errorcontrol.invalidargumentexception.ArgumentIsNullException;
import ch.nolix.common.errorcontrol.invalidargumentexception.BiggerArgumentException;
import ch.nolix.common.errorcontrol.invalidargumentexception.NonPositiveArgumentException;
import ch.nolix.common.errorcontrol.invalidargumentexception.SmallerArgumentException;
import ch.nolix.common.errorcontrol.validator.Validator;

//class
/**
 * A {@link SubContainer} can iterate over a part of another container.
 * 
 * A {@link SubContainer} must not use the methods of the accessed container except the iterator method.
 * The reason is that the accessed container can be a specialized container
 * that does not use its iterator in any of its declared or overwritten method.
 * 
 * @author Silvan Wyss
 * @date 2017-08-27
 * @lines 110
 * @param <E> is the type of the elements of a {@link SubContainer}.
 */
public final class SubContainer<E> implements IContainer<E> {

	//attributes
	private final IContainer<E> container;
	private final int startIndex;
	private final int endIndex;
	
	//constructor
	/**
	 * Creates a new {@link SubContainer} with the given container, startIndex and endIndex.
	 * 
	 * @param container
	 * @param startIndex
	 * @param endIndex
	 * @throws ArgumentIsNullException if the given container is null.
	 * @throws NonPositiveArgumentException if the given startIndex is not positive.
	 * @throws NonPositiveArgumentException if the given endIndex is not positive.
	 * @throws SmallerArgumentException if the given endIndex is smaller than the given startIndex.
	 * @throws BiggerArgumentException
	 * if the given endIndex is bigger than the number of elements of the given container.
	 */
	public SubContainer(final IContainer<E> container, final int startIndex, final int endIndex) {
		
		Validator.assertThat(container).thatIsNamed(LowerCaseCatalogue.CONTAINER).isNotNull();
		Validator.assertThat(startIndex).thatIsNamed(LowerCaseCatalogue.START_INDEX).isPositive();
		Validator.assertThat(endIndex).thatIsNamed(LowerCaseCatalogue.END_INDEX).isPositive();
		Validator.assertThat(endIndex).thatIsNamed(LowerCaseCatalogue.END_INDEX).isBiggerThanOrEquals(startIndex);
		
		Validator
		.assertThat(endIndex)
		.thatIsNamed(LowerCaseCatalogue.END_INDEX)
		.isNotBiggerThan(container.getElementCount());
		
		this.container = container;
		this.startIndex = startIndex;
		this.endIndex = endIndex;
	}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	public int getElementCount() {
		return (endIndex - startIndex + 1);
	}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	public E getRefAt(final int index) {
		
		Validator.assertThat(index).thatIsNamed(LowerCaseCatalogue.INDEX).isPositive();
		Validator.assertThat(index).thatIsNamed(LowerCaseCatalogue.INDEX).isNotBiggerThan(getElementCount());
		
		return container.getRefAt(startIndex + index - 1);
	}
	
	//method
	/**
	 * {@inheritDoc}
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
	 * {@inheritDoc}
	 */
	@Override
	public String toString() {
		return toString(CharacterCatalogue.COMMA);
	}
}
