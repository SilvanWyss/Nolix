//package declaration
package ch.nolix.core.independent.independentcontainer;

//Java imports
import java.util.Iterator;
import java.util.NoSuchElementException;

import ch.nolix.core.errorcontrol.invalidargumentexception.ArgumentDoesNotHaveAttributeException;
import ch.nolix.core.programatom.name.LowerCaseCatalogue;

//class
public final class ListIterator<E> implements Iterator<E> {
	
	//static method
	public static <E2> ListIterator<E2> forEmptyList() {
		return new ListIterator<>();
	}
	
	//static method
	public static <E2> ListIterator<E2> forStartNode(final ListNode<E2> startNode) {
		return new ListIterator<>(startNode);
	}
	
	//optional attribute
	private ListNode<E> nextNode;
	
	//constructor
	private ListIterator() {}
	
	//constructor
	private ListIterator(final ListNode<E> startNode) {
		this.nextNode = startNode;
	}
	
	//method
	@Override
	public boolean hasNext() {
		return (nextNode != null);
	}
	
	//method
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
	private E nextWhenHasNext() {
		
		final var element = nextNode.getRefElement();
		
		nextNode = nextNode.getRefNextNodeOrNull();
		
		return element;
	}
}
