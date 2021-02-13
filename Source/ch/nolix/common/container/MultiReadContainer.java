//package declaration
package ch.nolix.common.container;

//Java import
import java.util.Iterator;

//own imports
import ch.nolix.common.constant.LowerCaseCatalogue;
import ch.nolix.common.invalidargumentexception.ArgumentDoesNotHaveAttributeException;
import ch.nolix.common.validator.Validator;

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
		
		Validator.assertThat(index).thatIsNamed(LowerCaseCatalogue.INDEX).isPositive();
		
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
