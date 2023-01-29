//package declaration
package ch.nolix.core.container.readcontainer;

//Java imports
import java.util.Iterator;

//own imports
import ch.nolix.core.commontype.commontypeconstant.CharacterCatalogue;
import ch.nolix.core.container.base.Container;
import ch.nolix.core.container.base.Marker;
import ch.nolix.core.container.main.LinkedList;
import ch.nolix.core.container.main.SubContainer;
import ch.nolix.core.errorcontrol.invalidargumentexception.ArgumentIsNullException;
import ch.nolix.core.errorcontrol.invalidargumentexception.ArgumentIsOutOfRangeException;
import ch.nolix.core.errorcontrol.validator.GlobalValidator;
import ch.nolix.core.programatom.name.LowerCaseCatalogue;
import ch.nolix.coreapi.containerapi.baseapi.IContainer;
import ch.nolix.coreapi.containerapi.mainapi.IMutableList;
import ch.nolix.coreapi.functionapi.genericfunctionapi.IElementTakerElementGetter;

//class
/**
 * @author Silvan Wyss
 * @date 2017-11-26
 * @param <E> is the type of the elements of a {@link IterableReadContainer}.
 */
final class IterableReadContainer<E> extends Container<E> {
	
	//attribute
	private final Iterable<E> container;
	
	//constructor
	/**
	 * Creates a new {@link IterableReadContainer} for a new empty container.
	 */
	public IterableReadContainer() {
		
		//Calls other constructor.
		this(new LinkedList<E>());
	}
	
	//constructor
	/**
	 * Creates a new {@link IterableReadContainer} for the given container.
	 * 
	 * @param container
	 * @param <E2> is the type of the elements of the given container.
	 * @throws ArgumentIsNullException if the given container is null.
	 */
	@SuppressWarnings("unchecked")
	public <E2 extends E> IterableReadContainer(final Iterable<E2> container) {
		
		//Asserts that the given container is not null.
		GlobalValidator
		.assertThat(container)
		.thatIsNamed(LowerCaseCatalogue.CONTAINER)
		.isNotNull();
		
		//Sets the container of the current IterableReadContainer.
		this.container = (Iterable<E>)container;
	}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	public Iterator<E> iterator() {
		return container.iterator();
	}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	public int getElementCount() {
		
		var size = 0;
		
		//Iterates the current IterableReadContainer.
		final var iterator = container.iterator();
		while (iterator.hasNext()) {
			size++;
			iterator.next();
		}
		
		return size;
	}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	public E getRefAt1BasedIndex(final int p1BasedIndex) {
		
		//Iterates the current IterableReadContainer.
		var i = 1;
		for (final var e : this) {
			
			//Asserts that the current index is the given index.
			if (i == p1BasedIndex) {
				return e;
			}
			
			i++;
		}
		
		throw
		ArgumentIsOutOfRangeException.forArgumentNameAndArgumentAndRangeWithMinAndMax(
			"1-based index",
			p1BasedIndex,
			1,
			getElementCount()
		);
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
	public <C extends Comparable<C>> IContainer<E> toOrderedList(final IElementTakerElementGetter<E, C> norm) {
		return LinkedList.fromIterable(this).toOrderedList(norm);
	}
	
	//method
	/**
	 * The complexity of this implementation is O(n).
	 * if the current {@link IterableReadContainer} contains n elements.
	 * 
	 * @return a {@link String} representation of the current {@link IterableReadContainer}.
	 */
	@Override
	public String toString() {
		return toString(CharacterCatalogue.COMMA);
	}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected <E2> IMutableList<E2> createEmptyMutableList(final Marker<E2> marker) {
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
