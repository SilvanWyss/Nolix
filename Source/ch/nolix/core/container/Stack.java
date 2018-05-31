//package declaration
package ch.nolix.core.container;

//Java import
import java.util.Iterator;

//class
public final class Stack<E> implements IContainer<E> {

	//attribute
	private final List<E> list = new List<E>();
	
	//method
	public Stack<E> addAtEnd(final E element) {
		
		list.addAtEnd(element);
		
		return this;
	}

	//method
	public int getElementCount() {
		return list.getElementCount();
	}
	
	//method
	public E getRefLast() {
		return list.getRefLast();
	}
	
	//method
	public Iterator<E> iterator() {
		return list.iterator();
	}
	
	//method
	public E removeAndGetRefLast() {
		return list.removeAndGetRefLast();
	}
	
	//method
	public Stack<E> removeLast() {
		
		list.removeLast();
		
		return this;
	}
}
