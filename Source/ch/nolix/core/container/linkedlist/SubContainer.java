//package declaration
package ch.nolix.core.container.linkedlist;

//Java imports
import java.util.Iterator;

//own imports
import ch.nolix.core.commontype.commontypeconstant.CharacterCatalogue;
import ch.nolix.core.container.base.Container;
import ch.nolix.core.container.base.Marker;
import ch.nolix.core.errorcontrol.invalidargumentexception.ArgumentIsNullException;
import ch.nolix.core.errorcontrol.invalidargumentexception.BiggerArgumentException;
import ch.nolix.core.errorcontrol.invalidargumentexception.NonPositiveArgumentException;
import ch.nolix.core.errorcontrol.invalidargumentexception.SmallerArgumentException;
import ch.nolix.core.errorcontrol.validator.GlobalValidator;
import ch.nolix.core.programatom.name.LowerCaseCatalogue;
import ch.nolix.coreapi.containerapi.baseapi.IContainer;
import ch.nolix.coreapi.containerapi.listapi.ILinkedList;
import ch.nolix.coreapi.functionapi.genericfunctionapi.IElementTakerElementGetter;

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
 * @param <E> is the type of the elements of a {@link SubContainer}.
 */
public final class SubContainer<E> extends Container<E> {

	//attributes
	private final Container<E> container;
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
	public SubContainer(final Container<E> container, final int startIndex, final int endIndex) {
		
		GlobalValidator.assertThat(container).thatIsNamed(LowerCaseCatalogue.CONTAINER).isNotNull();
		GlobalValidator.assertThat(startIndex).thatIsNamed(LowerCaseCatalogue.START_INDEX).isPositive();
		GlobalValidator.assertThat(endIndex).thatIsNamed(LowerCaseCatalogue.END_INDEX).isPositive();
		GlobalValidator.assertThat(endIndex).thatIsNamed(LowerCaseCatalogue.END_INDEX).isBiggerThanOrEquals(startIndex);
		
		GlobalValidator
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
	public E getRefAt1BasedIndex(final int p1BasedIndex) {
		
		GlobalValidator.assertThat(p1BasedIndex).thatIsNamed(LowerCaseCatalogue.INDEX).isPositive();
		GlobalValidator.assertThat(p1BasedIndex).thatIsNamed(LowerCaseCatalogue.INDEX).isNotBiggerThan(getElementCount());
		
		return container.getRefAt1BasedIndex(startIndex + p1BasedIndex - 1);
	}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	public E getRefLast() {
		return getRefAt1BasedIndex(getElementCount());
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
	public <C extends Comparable<C>> IContainer<E> toOrderedList(final IElementTakerElementGetter<E, C> norm) {
		return LinkedList.fromIterable(this).toOrderedList(norm);
	}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	public String toString() {
		return toStringWithSeparator(CharacterCatalogue.COMMA);
	}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected <E2> ILinkedList<E2> createEmptyMutableList(final Marker<E2> marker) {
		return new LinkedList<>();
	}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected IContainer<E> getSubContainerFromStartIndexToEndIndex(
		final int p1BasedStartIndex,
		final int p1BasedEndIndex
	) {
		return new SubContainer<>(this, p1BasedStartIndex, p1BasedEndIndex);
	}
}
