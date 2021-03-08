//package declaration
package ch.nolix.common.independent.independentcontainer;

//Java import
import java.util.Iterator;

import ch.nolix.common.errorcontrol.invalidargumentexception.ArgumentDoesNotHaveAttributeException;

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
			throw new ArgumentDoesNotHaveAttributeException(this, "next element");
		}
		
		final E element = nextNode.getRefElement();
		
		nextNode = nextNode.getRefNextNodeOrNull();
		
		return element;
	}
}
