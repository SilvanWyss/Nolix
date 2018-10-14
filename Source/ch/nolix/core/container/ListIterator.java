//package declaration
package ch.nolix.core.container;

//Java import
import java.util.Iterator;

//own import
import ch.nolix.core.invalidStateException.UnexistingAttributeException;

//package-visible class
/**
 * @author Silvan Wyss
 * @month 2015-12
 * @lines 70
 * @param <E> The type of the elements of a {@link ListIterator}.
 */
final class ListIterator<E> implements Iterator<E> {
	
	//attribute
	private ListNode<E> nextNode;
	
	//constructor
	/**
	 * Creates a new {@link ListIterator} with the given first node.
	 * The given first node can be null.
	 * 
	 * @param firstNode
	 */
	public ListIterator(final ListNode<E> firstNode) {
		nextNode = firstNode;
	}
	
	//method
	/**
	 * @return a copy of the current {@link ListIterator}.
	 */
	public ListIterator<E> getCopy() {
		return new ListIterator<E>(nextNode);
	}

	//method
	/**
	 * @return true if the current {@link ListIterator} has a next element.
	 */
	public boolean hasNext() {
		return (nextNode != null);
	}

	//method
	/**
	 * @return the next element of the current {@link ListIterator}.
	 * @throws UnexistingAttributeException if the current {@link ListIterator} has no next element.
	 */
	public E next() {
		
		//Checks if the current list iterator has a next element.
		if (!hasNext()) {	
			throw new UnexistingAttributeException(this, "next element");
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
