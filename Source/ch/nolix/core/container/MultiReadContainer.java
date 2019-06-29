//package declaration
package ch.nolix.core.container;

//Java import
import java.util.Iterator;

//package-visible class
final class MultiReadContainer<E> implements IContainer<E> {
	
	//attribute
	private final List<IContainer<E>> containers = new List<>();
	
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
	public int getSize() {
		return containers.getSumByInt(c -> c.getSize());
	}
}
