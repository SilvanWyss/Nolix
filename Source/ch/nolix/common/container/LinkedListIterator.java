//package declaration
package ch.nolix.common.container;

//Java import
import java.util.Iterator;

//own imports
import ch.nolix.common.invalidargumentexception.ArgumentDoesNotHaveAttributeException;

//class
/**
 * @author Silvan Wyss
 * @month 2015-12
 * @lines 70
 * @param <E> is the type of the elements of a {@link LinkedListIterator}.
 */
final class LinkedListIterator<E> implements Iterator<E> {
	
	//attribute
	private LinkedListNode<E> nextNode;
	
	//constructor
	/**
	 * Creates a new {@link LinkedListIterator} with the given first node.
	 * The given first node can be null.
	 * 
	 * @param firstNode
	 */
	public LinkedListIterator(final LinkedListNode<E> firstNode) {
		nextNode = firstNode;
	}
	
	//method
	/**
	 * @return a copy of the current {@link LinkedListIterator}.
	 */
	public LinkedListIterator<E> getCopy() {
		return new LinkedListIterator<>(nextNode);
	}

	//method
	/**
	 * @return true if the current {@link LinkedListIterator} has a next element.
	 */
	@Override
	public boolean hasNext() {
		return (nextNode != null);
	}

	//method
	/**
	 * @return the next element of the current {@link LinkedListIterator}.
	 * @throws ArgumentDoesNotHaveAttributeException if the current {@link LinkedListIterator} does not have a next element.
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
		} else {
			nextNode = null;
		}
		
		return element;
	}
}
