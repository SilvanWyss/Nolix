//package declaration
package ch.nolix.primitive.container;

//Java import
import java.util.Iterator;

//class
public final class List<E> implements Iterable<E> {
	
	//optional attributes
	private ListNode<E> beginNode;
	private ListNode<E> endNode;
	
	//constructor
	public List() {}
	
	//constructor
	public List(final E element) {
		addAtBegin(element);
	}
	
	//method
	public void addAtBegin(final E element) {
		
		final ListNode<E> node = new ListNode<E>(element);
		
		if (isEmpty()) {
			beginNode = node;
			endNode = node;
		}
		else {
			node.setNextNode(beginNode);
			beginNode = node;
		}
	}
	
	//method
	public void addAtEnd(final E element) {
		
		final ListNode<E> node = new ListNode<E>(element);
		
		if (isEmpty()) {
			beginNode = node;
			endNode = node;
		}
		else {
			endNode.setNextNode(node);
			endNode = node;
		}
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
	public void removeFirst() {
		
		supposeIsNotEmpty();
		
		if (!beginNode.hasNextNode()) {
			beginNode = null;
			endNode = null;
		}
		else {
			beginNode = beginNode.getRefNextNodeOrNull();
		}
	}

	//method
	/**
	 * @return a new {@link Iterator} for the current {@link List}.
	 */
	public Iterator<E> iterator() {
		return new ListIterator<E>(beginNode);
	}
	
	//method
	private void supposeIsNotEmpty() {
		if (isEmpty()) {
			throw new RuntimeException("The current list is empty.");
		}
	}
}
