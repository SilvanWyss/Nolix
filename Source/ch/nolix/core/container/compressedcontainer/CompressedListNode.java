//package declaration
package ch.nolix.core.container.compressedcontainer;

import ch.nolix.core.errorcontrol.invalidargumentexception.ArgumentDoesNotHaveAttributeException;
import ch.nolix.core.errorcontrol.invalidargumentexception.ArgumentHasAttributeException;
import ch.nolix.core.errorcontrol.invalidargumentexception.ArgumentIsNullException;
import ch.nolix.core.errorcontrol.invalidargumentexception.NonPositiveArgumentException;
import ch.nolix.core.programatom.name.LowerCaseCatalogue;

//class
final class CompressedListNode<E> {
	
	//static method
	public static <E2> CompressedListNode<E2> forElement(final E2 element) {
		return new CompressedListNode<>(element, 1);
	}
	
	//attribute
	private final E element;
	
	//attribute
	private int elementCount;
	
	//optional attribute
	private CompressedListNode<E> nextNode;
	
	//constructor
	//For a better performance, this implementation does not use all comfortable methods.
	private CompressedListNode(final E element, final int elementCount) {
		
		if (element == null) {
			throw new ArgumentIsNullException(LowerCaseCatalogue.ELEMENT);
		}
		
		if (elementCount < 1) {
			throw new NonPositiveArgumentException(LowerCaseCatalogue.ELEMENT_COUNT, elementCount);
		}
		
		this.element = element;
		this.elementCount = elementCount;
	}
	
	//method
	public int getElementCount() {
		return elementCount;
	}
	
	//method
	public E getRefElement() {
		return element;
	}
	
	//method
	public CompressedListNode<E> getRefNextNode() {
		
		assertHasNextNode();
		
		return nextNode;
	}
	
	//method
	public boolean hasNextNode() {
		return (nextNode != null);
	}
	
	//method
	public void incrementElementCount() {
		elementCount++;
	}
	
	//method
	public void setNextNode(final CompressedListNode<E> nextNode) {
		
		assertDoesNotHaveNextNode();
		
		this.nextNode = nextNode;
	}
	
	//method
	private void assertDoesNotHaveNextNode() {
		if (hasNextNode()) {
			throw ArgumentHasAttributeException.forArgumentAndAttributeName(this, "next node");
		}
	}
	
	//method
	private void assertHasNextNode() {
		if (!hasNextNode()) {
			throw ArgumentDoesNotHaveAttributeException.forArgumentAndAttributeName(this, "next node");
		}
	}
}
