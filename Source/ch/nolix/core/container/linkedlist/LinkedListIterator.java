//package declaration
package ch.nolix.core.container.linkedlist;

//Java imports
import java.util.Iterator;
import java.util.NoSuchElementException;

import ch.nolix.core.errorcontrol.invalidargumentexception.ArgumentDoesNotHaveAttributeException;
import ch.nolix.core.programatom.name.LowerCaseCatalogue;

//class
/**
 * @author Silvan Wyss
 * @date 2016-01-01
 * @param <E> is the type of the elements of a {@link LinkedListIterator}.
 */
final class LinkedListIterator<E> implements Iterator<E> {
	
	//static method
	public static <E2> LinkedListIterator<E2> withFirstNodeOrNull(final LinkedListNode<E2> firstNode) {
		return new LinkedListIterator<>(firstNode);
	}
	
	//optional attribute
	private LinkedListNode<E> nextNode;
	
	//constructor
	/**
	 * Creates a new {@link LinkedListIterator} with the given firstNode. The given firstNode be null.
	 * 
	 * @param firstNode
	 */
	private LinkedListIterator(final LinkedListNode<E> firstNode) {
		nextNode = firstNode;
	}
	
	//method
	/**
	 * @return a copy of the current {@link LinkedListIterator}.
	 */
	public LinkedListIterator<E> getCopy() {
		return withFirstNodeOrNull(nextNode);
	}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean hasNext() {
		return (nextNode != null);
	}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	public E next() {
		
		assertHasNext();
		
		return nextWhenHasNext();
	}
	
	//method
	private void assertHasNext() throws NoSuchElementException {
		if (!hasNext()) {
			throw
			ArgumentDoesNotHaveAttributeException.forArgumentAndAttributeName(this, LowerCaseCatalogue.NEXT_ELEMENT)
			.toNoSuchElementException();
		}
	}
	
	//method
	private E nextWhenHasNext() {
		
		final var element = nextNode.getElement();
		
		if (nextNode.hasNextNode()) {
			nextNode = nextNode.getNextNode();
		} else {
			nextNode = null;
		}
		
		return element;
	}
}