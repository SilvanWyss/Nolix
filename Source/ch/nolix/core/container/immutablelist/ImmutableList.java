//package declaration
package ch.nolix.core.container.immutablelist;

//Java imports
import java.util.Iterator;

//own imports
import ch.nolix.core.commontype.commontypeconstant.CharacterCatalogue;
import ch.nolix.core.container.arraycontrol.ArrayIterator;
import ch.nolix.core.container.base.Container;
import ch.nolix.core.container.base.Marker;
import ch.nolix.core.container.main.LinkedList;
import ch.nolix.core.container.readcontainer.ReadContainer;
import ch.nolix.core.errorcontrol.invalidargumentexception.ArgumentIsNullException;
import ch.nolix.core.errorcontrol.validator.GlobalValidator;
import ch.nolix.core.independent.independenthelper.IterableHelper;
import ch.nolix.coreapi.containerapi.baseapi.IContainer;
import ch.nolix.coreapi.containerapi.mainapi.IMutableList;
import ch.nolix.coreapi.functionapi.genericfunctionapi.IElementTakerElementGetter;

//class
/**
 * A {@link ImmutableList} is a {@link Container} that is not mutable.
 * 
 * @author Silvan Wyss
 * @date 2022-07-08
 * @param <E> is the type of the elements of a {@link ImmutableList}.
 */
public final class ImmutableList<E> extends Container<E> {
	
	//static method
	public static <E2> ImmutableList<E2> forArray(final E2[] array) {
		return new ImmutableList<>(array.clone());
	}
	
	//static method
	//For a better performance, this implementation does not use all comfortable methods.
	/**
	 * @param <E2>
	 * @param container
	 * @return a new {@link ImmutableList} with the elements from the given container.
	 * @throws ArgumentIsNullException if one of the elements of the given container is null.
	 */
	@SuppressWarnings("unchecked")
	public static <E2> ImmutableList<E2> forIterable(final Iterable<E2> container) {
		
		if (container instanceof ImmutableList) {
			return (ImmutableList<E2>)container;
		}
		
		final var elementCount = IterableHelper.getElementCount(container);
		final var elements = new Object[elementCount];
		var index = 0;
		for (final var e : container) {
			
			if (e == null) {
				throw ArgumentIsNullException.forArgumentName((index + 1) + "th element");
			}
			
			elements[index] = e;
			
			index++;
		}
		
		return new ImmutableList<>((E2[])elements);
	}
	
	//static method
	@SuppressWarnings("unchecked")
	public static <E2> ImmutableList<E2> withElement(final E2 element) {
		return new ImmutableList<>((E2[])new Object[] {element});
	}
	
	//static method
	@SuppressWarnings("unchecked")
	public static <E2> ImmutableList<E2> withElements(final E2... elements) {
		return new ImmutableList<>(elements.clone());
	}
	
	//multi-attribute
	private final E[] elements;
	
	//constructor
	/**
	 * Creates a new {@link ImmutableList} that is empty.
	 */
	@SuppressWarnings("unchecked")
	public ImmutableList() {
		elements = (E[])new Object[0];
	}
	
	//constructor
	/**
	 * Creates a new {@link ImmutableList} with the given elements.
	 * 
	 * @param elements
	 */
	private ImmutableList(final E[] elements) {
		this.elements = elements;
	}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	public int getElementCount() {
		return elements.length;
	}

	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	public E getRefAt1BasedIndex(final int p1BasedIndex) {
		
		GlobalValidator.assertThat(p1BasedIndex).thatIsNamed("1-based index").isBetween(1, getElementCount());
		
		return elements[p1BasedIndex - 1];
	}

	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	public E getRefLast() {
		return elements[getElementCount() - 1];
	}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	public Iterator<E> iterator() {
		return ArrayIterator.forArray(elements);
	}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	public <C extends Comparable<C>> IContainer<E> toOrderedList(final IElementTakerElementGetter<E, C> norm) {
		return ReadContainer.forIterable(this).toOrderedList(norm);
	}
	
	//method
	/**
	 * {@inheritDoc}
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
}
