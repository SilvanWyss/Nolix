//package declaration
package ch.nolix.common.independentcontainer;

//own imports
import ch.nolix.common.constant.VariableNameCatalogue;
import ch.nolix.common.invalidargumentexception.ArgumentIsNullException;

//class
final class ListNode<E> {
	
	//attribute
	private final E element;
	
	//optional attribute
	private ListNode<E> nextNode;
	
	//constructor
	public ListNode(final E element) {
		
		if (element == null) {
			throw new ArgumentIsNullException(VariableNameCatalogue.ELEMENT);
		}
		
		this.element = element;
	}
	
	//method
	public boolean contains(final E element) {
		return (this.element == element);
	}
	
	//method
	public E getRefElement() {
		return element;
	}
	
	//method
	public ListNode<E> getRefNextNodeOrNull() {
		return nextNode;
	}
	
	//method
	public boolean hasNextNode() {
		return (nextNode != null);
	}
	
	//method
	public void removeNextNode() {
		nextNode = null;
	}
	
	//method
	public void setNextNode(final ListNode<E> nextNode) {
		
		if (nextNode == null) {
			throw new ArgumentIsNullException("next node");
		}
		
		this.nextNode = nextNode;
	}
}
