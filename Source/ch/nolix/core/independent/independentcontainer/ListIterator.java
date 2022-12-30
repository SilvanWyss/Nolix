//package declaration
package ch.nolix.core.independent.independentcontainer;

//Java imports
import java.util.Iterator;

//own imports
import ch.nolix.core.errorcontrol.invalidargumentexception.ArgumentDoesNotHaveAttributeException;

//class
public final class ListIterator<E> implements Iterator<E> {

	//attribute
	private ListNode<E> nextNode;
	
	//constructor
	public ListIterator(final ListNode<E> nextNode) {
		this.nextNode = nextNode;
	}
	
	//method
	@Override
	public boolean hasNext() {
		return (nextNode != null);
	}

	//method
	@Override
	public E next() {
		
		if (!hasNext()) {
			throw ArgumentDoesNotHaveAttributeException.forArgumentAndAttributeName(this, "next element");
		}
		
		final var element = nextNode.getRefElement();
		
		nextNode = nextNode.getRefNextNodeOrNull();
		
		return element;
	}
}
