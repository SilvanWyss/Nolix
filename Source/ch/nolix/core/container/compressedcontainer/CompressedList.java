//package declaration
package ch.nolix.core.container.compressedcontainer;

//Java imports
import java.util.Iterator;

import ch.nolix.core.container.main.Container;
import ch.nolix.core.container.main.LinkedList;
import ch.nolix.core.errorcontrol.invalidargumentexception.ArgumentDoesNotHaveAttributeException;
import ch.nolix.core.errorcontrol.invalidargumentexception.EmptyArgumentException;
import ch.nolix.core.errorcontrol.invalidargumentexception.NonPositiveArgumentException;
import ch.nolix.core.errorcontrol.validator.GlobalValidator;
import ch.nolix.core.programatom.name.LowerCaseCatalogue;
import ch.nolix.coreapi.containerapi.mainapi.IContainer;
import ch.nolix.coreapi.functionuniversalapi.IElementTakerComparableGetter;

//class
public final class CompressedList<E> extends Container<E> {
	
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
			throw NonPositiveArgumentException.forArgumentNameAndArgument(LowerCaseCatalogue.INDEX, index);
		}
		
		if (isEmpty()) {
			throw EmptyArgumentException.forArgument(this);
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
		
		throw ArgumentDoesNotHaveAttributeException.forArgumentAndAttributeName(this, "element at " + index);
	}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	public E getRefLast() {
		
		GlobalValidator.assertThat(this).isNotEmpty();
		
		return lastNode.getRefElement();
	}
	
	//method
	@Override
	public Iterator<E> iterator() {
		
		if (isEmpty()) {
			return CompressedListIterator.forEmptyCompressedList();
		}
		
		return CompressedListIterator.forCompressedListWithFirstNode(firstNode);
	}
	
	//method
	@Override
	public <E2> IContainer<E> toOrderedList(final IElementTakerComparableGetter<E, E2> norm) {
		return LinkedList.fromIterable(this).toOrderedList(norm);
	}
}
