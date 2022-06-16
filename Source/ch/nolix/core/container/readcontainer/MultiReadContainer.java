//package declaration
package ch.nolix.core.container.readcontainer;

//Java imports
import java.util.Iterator;

import ch.nolix.core.container.IContainer;
import ch.nolix.core.container.LinkedList;
import ch.nolix.core.errorcontrol.invalidargumentexception.ArgumentDoesNotHaveAttributeException;
import ch.nolix.core.errorcontrol.validator.GlobalValidator;
import ch.nolix.core.programatom.name.LowerCaseCatalogue;

//class
final class MultiReadContainer<E> implements IContainer<E> {
	
	//attribute
	private final LinkedList<IContainer<E>> containers = new LinkedList<>();
	
	//constructor
	@SuppressWarnings("unchecked")
	public MultiReadContainer(final E[]... arrays) {
		for (final var a : arrays) {
			this.containers.addAtEnd(new ArrayReadContainer<E>(a));
		}
	}
	
	//constructor
	@SuppressWarnings("unchecked")
	public MultiReadContainer(final Iterable<E>... containers) {
		for (final var c : containers) {
			this.containers.addAtEnd(new IterableReadContainer<E>(c));
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
		return containers.getSumByInt(IContainer::getElementCount);
	}
	
	//method
	@Override
	public E getRefAt(final int index) {
		
		GlobalValidator.assertThat(index).thatIsNamed(LowerCaseCatalogue.INDEX).isPositive();
		
		var i = 1;
		for (final var e : this) {
			
			if (i == index) {
				return e;
			}
			
			i++;
		}
		
		throw new ArgumentDoesNotHaveAttributeException(this, "element at " + index);
	}
}
