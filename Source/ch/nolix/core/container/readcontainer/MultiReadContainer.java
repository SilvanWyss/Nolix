//package declaration
package ch.nolix.core.container.readcontainer;

//own imports
import ch.nolix.core.commontype.commontypeconstant.CharacterCatalogue;
import ch.nolix.core.container.base.Container;
import ch.nolix.core.container.base.Marker;
import ch.nolix.core.container.linkedlist.LinkedList;
import ch.nolix.core.errorcontrol.invalidargumentexception.ArgumentIsOutOfRangeException;
import ch.nolix.coreapi.containerapi.baseapi.CopyableIterator;
import ch.nolix.coreapi.containerapi.baseapi.IContainer;
import ch.nolix.coreapi.containerapi.listapi.ILinkedList;
import ch.nolix.coreapi.functionapi.genericfunctionapi.IElementTakerElementGetter;

//class
public final class MultiReadContainer<E> extends Container<E> {
	
	//attribute
	private final IterableReadContainer<IContainer<E>> containers;
	
	//static method
	public static <E2> MultiReadContainer<E2> forArray(
		final E2[] array,
		@SuppressWarnings("unchecked") final E2[]... arrays
	) {
		
		final var containers = new LinkedList<ArrayReadContainer<E2>>();
		
		containers.addAtEnd(ArrayReadContainer.forArray(array));
		
		for (final var a : arrays) {
			containers.addAtEnd(ArrayReadContainer.forArray(a));
		}
		
		return new MultiReadContainer<>(containers);
	}
	
	//static method
	@SafeVarargs
	public static <E2> MultiReadContainer<E2> forIterable(
		final Iterable<? extends E2> iterable,
		final Iterable<? extends E2>... iterables
	) {
		
		final var containers = new LinkedList<IterableReadContainer<E2>>();
		
		containers.addAtEnd(IterableReadContainer.forIterable(iterable));
		
		for (final var i : iterables) {
			containers.addAtEnd(IterableReadContainer.forIterable(i));
		}
		
		return new MultiReadContainer<>(containers);
	}
	
	//constructor
	public MultiReadContainer() {
		containers = new IterableReadContainer<>();
	}
	
	//constructor
	private MultiReadContainer(final IContainer<? extends IContainer<E>> container) {
		this.containers = IterableReadContainer.forIterable(container);
	}
	
	//method
	@Override
	public CopyableIterator<E> iterator() {
		return new MultiReadContainerIterator<>(containers);
	}
	
	//method
	@Override
	public int getElementCount() {
		return containers.getSumOfIntegers(IContainer::getElementCount).intValue();
	}
	
	//method
	@Override
	public E getStoredAt1BasedIndex(final int p1BasedIndex) {
		
		var i = 1;
		for (final var e : this) {
			
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
	@Override
	public E getStoredLast() {
		return containers.getStoredLast().getStoredLast();
	}
	
	//method
	@Override
	public boolean isMaterialized() {
		return false;
	}
	
	//method
	@Override
	public <C extends Comparable<C>> IContainer<E> toOrderedList(final IElementTakerElementGetter<E, C> norm) {
		return LinkedList.fromIterable(this).toOrderedList(norm);
	}
	
	//method
	@Override
	public String toString() {
		return toStringWithSeparator(CharacterCatalogue.COMMA);
	}
	
	//method
	@Override
	protected <E2> ILinkedList<E2> createEmptyMutableList(final Marker<E2> marker) {
		return new LinkedList<>();
	}
}
