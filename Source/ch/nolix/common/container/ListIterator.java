/*
 * file:	ListIterator.java
 * author:	Silvan Wyss
 * month:	2015-12
 * lines:	50
 */

//package declaration
package ch.nolix.common.container;

//Java import
import java.util.Iterator;

//package-visible class
public final class ListIterator<E> implements Iterator<E> {
	
	//attribute
	private ListNode<E> nextNode;
	
	//constructor
	/**
	 * Creates new list iterator for the list with the given bottom node.
	 * 
	 * @param bottomNode
	 */
	public ListIterator(ListNode<E> bottomNode) {
		nextNode = bottomNode;
	}
	
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
	 * @return the next element of this list iterator
	 * @throws Exception if this list iterator has no next element
	 */
	public E next() {
		E element = nextNode.getElement();
		
		if (nextNode.hasNextNode()) {
			nextNode = nextNode.getNextNode();
		}
		else {
			nextNode = null;
		}
		
		return element;
	}
}
