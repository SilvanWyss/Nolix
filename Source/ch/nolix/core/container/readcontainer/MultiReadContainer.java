//package declaration
package ch.nolix.core.container.readcontainer;

//Java imports
import java.util.Iterator;

//own imports
import ch.nolix.core.container.main.Container;
import ch.nolix.core.container.main.LinkedList;
import ch.nolix.core.errorcontrol.invalidargumentexception.ArgumentDoesNotHaveAttributeException;
import ch.nolix.core.errorcontrol.validator.GlobalValidator;
import ch.nolix.core.programatom.name.LowerCaseCatalogue;
import ch.nolix.coreapi.containerapi.mainapi.IContainer;
import ch.nolix.coreapi.functionapi.genericfunctionapi.IElementTakerComparableGetter;

//class
final class MultiReadContainer<E> extends Container<E> {
	
	//attribute
	private final LinkedList<Container<E>> containers = new LinkedList<>();
	
	//constructor
	@SuppressWarnings("unchecked")
	public MultiReadContainer(final E[]... arrays) {
		for (final var a : arrays) {
			this.containers.addAtEnd(new ArrayReadContainer<>(a));
		}
	}
	
	//constructor
	@SuppressWarnings("unchecked")
	public MultiReadContainer(final Iterable<E>... containers) {
		for (final var c : containers) {
			this.containers.addAtEnd(new IterableReadContainer<>(c));
		}
	}
	
	//method
	@Override
	public Iterator<E> iterator() {
		return new MultiReadContainerIterator<>(containers);
	}
	
	//method
	@Override
	public int getElementCount() {
		return containers.getSumByInt(Container::getElementCount);
	}
	
	//method
	@Override
	public E getRefAt1BasedIndex(final int p1BasedIndex) {
		
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
	/**
	 * {@inheritDoc}
	 */
	@Override
	public E getRefLast() {
		return containers.getRefLast().getRefLast();
	}
	
	//method
	@Override
	public <E2> IContainer<E> toOrderedList(final IElementTakerComparableGetter<E, E2> norm) {
		return LinkedList.fromIterable(this).toOrderedList(norm);
	}
}
