//package declaration
package ch.nolix.core.primitiveContainer;

//Java import
import java.util.Iterator;

//class
public final class ListIterator<E> implements Iterator<E> {

	//attribute
	private ListNode<E> nextNode;
	
	//constructor
	public ListIterator(final ListNode<E> nextNode) {
		this.nextNode = nextNode;
	}
	
	//method
	@Override
	public boolean hasNext() {
		return (nextNode != null);
	}

	//method
	@Override
	public E next() {
		
		if (!hasNext()) {
			throw new RuntimeException("The current iterator has no next element.");
		}
		
		final E element = nextNode.getRefElement();
		
		nextNode = nextNode.getRefNextNodeOrNull();
		
		return element;
	}
}
