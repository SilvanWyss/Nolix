//package declaration
package ch.nolix.common.containers;

//Java import
import java.util.Iterator;

//class
public final class Stack<E> implements IContainer<E> {

	//attribute
	private final LinkedList<E> linkedList = new LinkedList<>();
	
	//method
	public Stack<E> addAtEnd(final E element) {
		
		linkedList.addAtEnd(element);
		
		return this;
	}

	//method
	@Override
	public int getSize() {
		return linkedList.getSize();
	}
	
	//method
	public E getRefLast() {
		return linkedList.getRefLast();
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
}
