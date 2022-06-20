//package declaration
package ch.nolix.core.container.compressedcontainer;

//Java imports
import java.util.Iterator;

//own imports
import ch.nolix.core.errorcontrol.invalidargumentexception.ArgumentDoesNotHaveAttributeException;
import ch.nolix.core.errorcontrol.invalidargumentexception.ArgumentIsNullException;

//class
final class CompressedListIterator<E> implements Iterator<E> {
	
	//static method
	public static <E2> CompressedListIterator<E2> forCompressedListWithFirstNode(
		final CompressedListNode<E2> firstNode
	) {
		return new CompressedListIterator<>(firstNode);
	}
	
	//static method
	public static <E2> CompressedListIterator<E2> forEmptyCompressedList() {
		return new CompressedListIterator<>();
	}
	
	//optional attribute
	private CompressedListNode<E> currentNode;
	
	//optional attribute
	private int currentNodeIndex;
	
	//constructor
	private CompressedListIterator() {
		currentNode = null;
		currentNodeIndex = -1;
	}
	
	//constructor
	//For a better performance, this implementation does not use all comfortable methods.
	private CompressedListIterator(final CompressedListNode<E> firstNode) {
		
		if (firstNode == null) {
			throw ArgumentIsNullException.forArgumentName("first node");
		}
		
		currentNode = firstNode;
		currentNodeIndex = 1;
	}
	
	//method
	@Override
	public boolean hasNext() {
		return (currentNode != null);
	}
	
	@Override
	public E next() {
		
		assertHasNext();
		
		final var element = currentNode.getRefElement();
		
		moveForward();
		
		return element;
	}
	
	//method
	private void assertHasNext() {
		if (!hasNext()) {
			throw ArgumentDoesNotHaveAttributeException.forArgumentAndAttributeName(this, "next element");
		}
	}
	
	//method
	private void moveForward() {
		if (currentNodeIndex < currentNode.getElementCount()) {
			currentNodeIndex++;
		} else {
			moveForwardCurrentNode();
		}
	}
	
	//method
	private void moveForwardCurrentNode() {
		if (currentNode.hasNextNode()) {
			
			currentNode = currentNode.getRefNextNode();
			
			currentNodeIndex = 1;
		} else {
			
			currentNode = null;
			
			currentNodeIndex = -1;
		}
	}
}
