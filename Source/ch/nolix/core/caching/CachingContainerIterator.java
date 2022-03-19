//package declaration
package ch.nolix.core.caching;

//Java imports
import java.util.Iterator;

//own imports
import ch.nolix.core.container.pair.Pair;

//class
final class CachingContainerIterator<E> implements Iterator<E> {
	
	//attribute
	private final Iterator<Pair<String, E>> parentCachingContainerIterator;
	
	//constructor
	public CachingContainerIterator(final Iterator<Pair<String, E>> parentCachingContainerIterator) {
		this.parentCachingContainerIterator = parentCachingContainerIterator;
	}
	
	//method
	@Override
	public boolean hasNext() {
		return parentCachingContainerIterator.hasNext();
	}
	
	//method
	@Override
	public E next() {
		return parentCachingContainerIterator.next().getRefElement2();
	}
}
