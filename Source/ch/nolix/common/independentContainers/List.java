//package declaration
package ch.nolix.common.independentContainers;

//Java import
import java.util.Iterator;

//class
public final class List<E> implements Iterable<E> {
	
	//attribute
	private int elementCount = 0;
	
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
		}
		else {
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
		}
		else {
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
	/**
	 * @return a new {@link Iterator} for the current {@link List}.
	 */
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
		}
		else {
			beginNode = beginNode.getRefNextNodeOrNull();
		}
		
		elementCount--;
	}
	
	//method
	private void supposeIsNotEmpty() {
		if (isEmpty()) {
			throw new RuntimeException("The current list is empty.");
		}
	}
}
