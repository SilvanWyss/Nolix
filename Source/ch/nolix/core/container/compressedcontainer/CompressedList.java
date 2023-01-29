//package declaration
package ch.nolix.core.container.compressedcontainer;

//Java imports
import java.util.Iterator;

//own imports
import ch.nolix.core.container.main.Container;
import ch.nolix.core.container.main.LinkedList;
import ch.nolix.core.container.main.Marker;
import ch.nolix.core.errorcontrol.invalidargumentexception.ArgumentDoesNotHaveAttributeException;
import ch.nolix.core.errorcontrol.invalidargumentexception.EmptyArgumentException;
import ch.nolix.core.errorcontrol.invalidargumentexception.NonPositiveArgumentException;
import ch.nolix.core.errorcontrol.validator.GlobalValidator;
import ch.nolix.core.programatom.name.LowerCaseCatalogue;
import ch.nolix.coreapi.containerapi.mainapi.IContainer;
import ch.nolix.coreapi.containerapi.mainapi.IMutableList;
import ch.nolix.coreapi.functionapi.genericfunctionapi.IElementTakerElementGetter;

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
	public E getRefAt1BasedIndex(final int p1BasedIndex) {
		
		if (p1BasedIndex < 1) {
			throw NonPositiveArgumentException.forArgumentNameAndArgument(LowerCaseCatalogue.INDEX, p1BasedIndex);
		}
		
		if (isEmpty()) {
			throw EmptyArgumentException.forArgument(this);
		}
		
		var iteratorIndex = 1;
		var iteratorNode = firstNode;
		while (true) {
			
			iteratorIndex += iteratorNode.getElementCount();
			
			if (iteratorIndex > p1BasedIndex) {
				return iteratorNode.getRefElement();
			}
			
			if (!iteratorNode.hasNextNode()) {
				break;
			}
			
			iteratorNode = iteratorNode.getRefNextNode();
		}
		
		throw ArgumentDoesNotHaveAttributeException.forArgumentAndAttributeName(this, "element at " + p1BasedIndex);
	}
	
	//method
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
	public <C extends Comparable<C>> IContainer<E> toOrderedList(final IElementTakerElementGetter<E, C> norm) {
		return LinkedList.fromIterable(this).toOrderedList(norm);
	}
	
	//method
	@Override
	protected <E2> IMutableList<E2> createEmptyMutableList(final Marker<E2> marker) {
		return new LinkedList<>();
	}
}
