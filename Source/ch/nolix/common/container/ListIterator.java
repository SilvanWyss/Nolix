//package declaration
package ch.nolix.common.container;

//Java import
import java.util.Iterator;

//own imports
import ch.nolix.common.exception.Argument;
import ch.nolix.common.exception.InvalidArgumentException;
import ch.nolix.common.exception.ErrorPredicate;

//package-visible class
/**
 * @author Silvan Wyss
 * @month 2015-12
 * @lines 70
 * @param <E> - The type of the elements of the list of a list iterator.
 */
final class ListIterator<E> implements Iterator<E> {
	
	//attribute
	private ListNode<E> nextNode;
	
	//constructor
	/**
	 * Creates new list iterator with the given first node.
	 * 
	 * @param firstNode
	 */
	ListIterator(final ListNode<E> firstNode) {
		nextNode = firstNode;
	}
	
	//method
	/**
	 * @return a new copy of this list iterator.
	 */
	public ListIterator<E> getCopy() {
		return new ListIterator<E>(nextNode);
	}

	//method
	/**
	 * @return true if this list iterator has a next element.
	 */
	public boolean hasNext() {
		return (nextNode != null);
	}

	//method
	/**
	 * @return the next element of this list iterator.
	 * @throws InvalidArgumentException if this list iterator has no next element.
	 */
	public E next() {
		
		//Checks if this list iterator has a next element.
		if (!hasNext()) {
			throw new InvalidArgumentException(
				new Argument(this),
				new ErrorPredicate("has no next element")
			);
		}
		
		final E element = nextNode.getElement();
		
		if (nextNode.hasNextNode()) {
			nextNode = nextNode.getNextNode();
		}
		else {
			nextNode = null;
		}
		
		return element;
	}
}
