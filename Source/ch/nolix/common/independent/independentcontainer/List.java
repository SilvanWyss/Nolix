//package declaration
package ch.nolix.common.independent.independentcontainer;

//Java imports
import java.util.Iterator;

import ch.nolix.common.errorcontrol.invalidargumentexception.EmptyArgumentException;
import ch.nolix.common.errorcontrol.invalidargumentexception.InvalidArgumentException;

//class
public final class List<E> implements Iterable<E> {
	
	//attribute
	private int elementCount;
	
	//optional attributes
	private ListNode<E> beginNode;
	private ListNode<E> endNode;
	
	//constructor
	public List() {}
	
	//constructor
	public List(final E element) {
		addAtBegin(element);
	}
	
	//constructor
	public List(final E[] elements) {
		for (final var e : elements) {
			addAtBegin(e);
		}
	}
	
	//method
	public void addAtBegin(final E element) {
		
		final ListNode<E> node = new ListNode<>(element);
		
		if (isEmpty()) {
			beginNode = node;
			endNode = node;
		} else {
			node.setNextNode(beginNode);
			beginNode = node;
		}
		
		elementCount++;
	}
	
	//method
	public void addAtEnd(final E element) {
		
		final var node = new ListNode<E>(element);
		
		if (isEmpty()) {
			beginNode = node;
			endNode = node;
		} else {
			endNode.setNextNode(node);
			endNode = node;
		}
		
		elementCount++;
	}
	
	//method
	public void clear() {
		beginNode = null;
		endNode = null;
		elementCount = 0;
	}
	
	//method
	public List<E> getCopy() {
		
		final var list = new List<E>();
		
		for (final var e : this) {
			list.addAtEnd(e);
		}
		
		return list;
	}
	
	//method
	public int getElementCount() {
		return elementCount;
	}
	
	//method
	public E getRefFirst() {
		
		supposeIsNotEmpty();
		
		return beginNode.getRefElement();
	}

	//method
	public boolean isEmpty() {
		return (beginNode == null);
	}
	
	//method
	@Override
	public Iterator<E> iterator() {
		return new ListIterator<>(beginNode);
	}
	
	//method
	public void removeFirst() {
		
		supposeIsNotEmpty();
		
		if (!beginNode.hasNextNode()) {
			beginNode = null;
			endNode = null;
		} else {
			beginNode = beginNode.getRefNextNodeOrNull();
		}
		
		elementCount--;
	}
	
	//method
	public void removeFirst(final E element) {
		
		if (isEmpty()) {
			throw new InvalidArgumentException(this, "does not contain the element '" + element + "'");
		}
		
		if (beginNode.contains(element)) {
			removeFirst();
			return;
		}
		
		var iteratorNode = beginNode;
		while (iteratorNode.hasNextNode()) {
			
			if (iteratorNode.getRefNextNodeOrNull().contains(element)) {
				
				if (!iteratorNode.getRefNextNodeOrNull().hasNextNode()) {
					iteratorNode.removeNextNode();
					endNode = iteratorNode;
				} else {
					iteratorNode.setNextNode(iteratorNode.getRefNextNodeOrNull().getRefNextNodeOrNull());
				}
				
				elementCount--;
				return;
			}
			
			iteratorNode = iteratorNode.getRefNextNodeOrNull();
		}
		
		throw new InvalidArgumentException(this, "does not contain the element '" + element + "'");
	}
	
	//method
	private void supposeIsNotEmpty() {
		if (isEmpty()) {
			throw new EmptyArgumentException(this);
		}
	}
}
