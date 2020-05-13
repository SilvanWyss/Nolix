//package declaration
package ch.nolix.common.container;

//Java import
import java.util.Iterator;

import ch.nolix.common.invalidArgumentException.ArgumentDoesNotHaveAttributeException;

//class
/**
 * @author Silvan Wyss
 * @month 2015-12
 * @lines 70
 * @param <E> The type of the elements of a {@link ListIterator}.
 */
final class ListIterator<E> implements Iterator<E> {
	
	//attribute
	private LinkedListNode<E> nextNode;
	
	//constructor
	/**
	 * Creates a new {@link ListIterator} with the given first node.
	 * The given first node can be null.
	 * 
	 * @param firstNode
	 */
	public ListIterator(final LinkedListNode<E> firstNode) {
		nextNode = firstNode;
	}
	
	//method
	/**
	 * @return a copy of the current {@link ListIterator}.
	 */
	public ListIterator<E> getCopy() {
		return new ListIterator<>(nextNode);
	}

	//method
	/**
	 * @return true if the current {@link ListIterator} has a next element.
	 */
	@Override
	public boolean hasNext() {
		return (nextNode != null);
	}

	//method
	/**
	 * @return the next element of the current {@link ListIterator}.
	 * @throws ArgumentDoesNotHaveAttributeException if the current {@link ListIterator} does not have a next element.
	 */
	@Override
	public E next() {
		
		//Asserts that the current list iterator has a next element.
		if (!hasNext()) {
			throw new ArgumentDoesNotHaveAttributeException(this, "next element");
		}
		
		final var element = nextNode.getElement();
		
		if (nextNode.hasNextNode()) {
			nextNode = nextNode.getNextNode();
		}
		else {
			nextNode = null;
		}
		
		return element;
	}
}
