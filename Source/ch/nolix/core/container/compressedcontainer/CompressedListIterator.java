//package declaration
package ch.nolix.core.container.compressedcontainer;

//Java imports
import java.util.Iterator;
import java.util.NoSuchElementException;

import ch.nolix.core.errorcontrol.invalidargumentexception.ArgumentDoesNotHaveAttributeException;
//own imports
import ch.nolix.core.errorcontrol.invalidargumentexception.ArgumentIsNullException;
import ch.nolix.core.programatom.name.LowerCaseCatalogue;

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
	
	//method
	private E nextWhenHasNext() {
		
		final var element = currentNode.getRefElement();
		
		moveForward();
		
		return element;
	}
}
