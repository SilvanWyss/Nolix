//package declaration
package ch.nolix.core.container.readcontainer;

//own imports
import ch.nolix.core.commontype.commontypeconstant.CharacterCatalogue;
import ch.nolix.core.container.base.Container;
import ch.nolix.core.container.base.Marker;
import ch.nolix.core.container.linkedlist.LinkedList;
import ch.nolix.core.errorcontrol.invalidargumentexception.ArgumentDoesNotHaveAttributeException;
import ch.nolix.core.errorcontrol.validator.GlobalValidator;
import ch.nolix.core.programatom.name.LowerCaseCatalogue;
import ch.nolix.coreapi.containerapi.baseapi.CopyableIterator;
import ch.nolix.coreapi.containerapi.baseapi.IContainer;
import ch.nolix.coreapi.containerapi.listapi.ILinkedList;
import ch.nolix.coreapi.functionapi.genericfunctionapi.IElementTakerElementGetter;

//class
public final class MultiReadContainer<E> extends Container<E> {
	
	//attribute
	private final LinkedList<IContainer<E>> containers = new LinkedList<>();
		
	//constructor
	public MultiReadContainer() {}
	
	//constructor
	@SuppressWarnings("unchecked")
	public MultiReadContainer(final E[]... arrays) {
		for (final var a : arrays) {
			this.containers.addAtEnd(new ArrayReadContainer<>(a));
		}
	}
	
	//constructor
	public MultiReadContainer(final IContainer<? extends IContainer<E>> containers) {
		for (final var c : containers) {
			this.containers.addAtEnd(c);
		}
	}
	
	//constructor
	@SuppressWarnings("unchecked")
	public MultiReadContainer(final Iterable<? extends E> container, final Iterable<? extends E>... containers) {
		
		this.containers.addAtEnd(new IterableReadContainer<>(container));
		
		for (final var c : containers) {
			this.containers.addAtEnd(new IterableReadContainer<>(c));
		}
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
		
		GlobalValidator.assertThat(p1BasedIndex).thatIsNamed(LowerCaseCatalogue.INDEX).isPositive();
		
		var i = 1;
		for (final var e : this) {
			
			if (i == p1BasedIndex) {
				return e;
			}
			
			i++;
		}
		
		throw ArgumentDoesNotHaveAttributeException.forArgumentAndAttributeName(this, "element at " + p1BasedIndex);
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
