//package declaration
package ch.nolix.common.containers;

//Java import
import java.util.Iterator;

//class
public final class Stack<E> implements IContainer<E> {

	//attribute
	private final List<E> list = new List<>();
	
	//method
	public Stack<E> addAtEnd(final E element) {
		
		list.addAtEnd(element);
		
		return this;
	}

	//method
	@Override
	public int getSize() {
		return list.getSize();
	}
	
	//method
	public E getRefLast() {
		return list.getRefLast();
	}
	
	//method
	@Override
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
