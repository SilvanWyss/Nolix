//package declaration
package ch.nolix.core.container;

//Java imports
import java.util.Iterator;

import ch.nolix.core.containerapi.IContainer;
import ch.nolix.core.functionuniversalapi.IElementTakerComparableGetter;

//class
public final class Stack<E> extends Container<E> {

	//attribute
	private final LinkedList<E> linkedList = new LinkedList<>();
	
	//method
	public Stack<E> addAtEnd(final E element) {
		
		linkedList.addAtEnd(element);
		
		return this;
	}

	//method
	@Override
	public int getElementCount() {
		return linkedList.getElementCount();
	}
	
	//method
	@Override
	public E getRefAt(final int index) {
		return linkedList.getRefAt(index);
	}
	
	//method
	@Override
	public Iterator<E> iterator() {
		return linkedList.iterator();
	}
	
	//method
	public E removeAndGetRefLast() {
		return linkedList.removeAndGetRefLast();
	}
	
	//method
	public Stack<E> removeLast() {
		
		linkedList.removeLast();
		
		return this;
	}
	
	//method
	@Override
	public <E2> IContainer<E> toOrderedList(final IElementTakerComparableGetter<E, E2> norm) {
		return LinkedList.fromIterable(this).toOrderedList(norm);
	}
}
