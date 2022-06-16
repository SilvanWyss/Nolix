//package declaration
package ch.nolix.core.container.compressedcontainer;

//Java imports
import java.util.Iterator;

import ch.nolix.core.container.IContainer;
import ch.nolix.core.errorcontrol.invalidargumentexception.ArgumentDoesNotHaveAttributeException;
import ch.nolix.core.errorcontrol.invalidargumentexception.EmptyArgumentException;
import ch.nolix.core.errorcontrol.invalidargumentexception.NonPositiveArgumentException;
import ch.nolix.core.programatom.name.LowerCaseCatalogue;

//class
public final class CompressedList<E> implements IContainer<E> {
	
	//attribute
	private int elementCount;
	
	//optional attribute
	private CompressedListNode<E> firstNode;
	
	//optional attribute
	private CompressedListNode<E> lastNode;
	
	//method
	public void addElement(final E element) {
		
		if (isEmpty()) {
			firstNode = CompressedListNode.forElement(element);
			lastNode = firstNode;
		} else {
			if (!lastNode.getRefElement().equals(element)) {
				final var nextNode = CompressedListNode.forElement(element);
				lastNode.setNextNode(nextNode);
				lastNode = nextNode;
			} else {
				lastNode.incrementElementCount();
			}
		}
		
		elementCount++;
	}
	
	//method
	@Override
	public int getElementCount() {
		return elementCount;
	}
	
	//method
	//For a better performance, this implementation does not use all comfortable methods.
	@Override
	public E getRefAt(final int index) {
		
		if (index < 1) {
			throw new NonPositiveArgumentException(LowerCaseCatalogue.INDEX, index);
		}
		
		if (isEmpty()) {
			throw new EmptyArgumentException(this);
		}
		
		var iteratorIndex = 1;
		var iteratorNode = firstNode;
		while (true) {
			
			iteratorIndex += iteratorNode.getElementCount();
			
			if (iteratorIndex > index) {
				return iteratorNode.getRefElement();
			}
			
			if (!iteratorNode.hasNextNode()) {
				break;
			}
			
			iteratorNode = iteratorNode.getRefNextNode();
		}
		
		throw new ArgumentDoesNotHaveAttributeException(this, "element at " + index);
	}
	
	//method
	@Override
	public Iterator<E> iterator() {
		
		if (isEmpty()) {
			return CompressedListIterator.forEmptyCompressedList();
		}
		
		return CompressedListIterator.forCompressedListWithFirstNode(firstNode);
	}
}
