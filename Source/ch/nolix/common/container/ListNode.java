/*
 * file:	ListNode.java
 * author:	Silvan Wyss
 * month:	2015-12
 * lines:	130
 */

//package declaration
package ch.nolix.common.container;

//own imports
import ch.nolix.common.exception.UnexistingAttributeException;
import ch.nolix.common.functional.IElementTakerBooleanGetter;
import ch.nolix.common.util.Validator;

//package-visible class
final class ListNode<E> {

	//attribute
	private E element;
	
	//optional attribute
	private ListNode<E> nextNode;
	
	//constructor
	/**
	 * Creates new list node with the given element.
	 * 
	 * @param element
	 * @throws Exception if the given element is null
	 */
	public ListNode(E element) {		
		setElement(element);
	}
	
	// method
	/**
	 * @param element
	 * @return true if this list node contains the given element
	 */
	public final boolean containsElement(E element) {
		return (getElement() == element);
	}
	
	//method
	/**
	 * @param selector
	 * @return true if this list node contains an element the given selector selects
	 */
	public final boolean containsElement(IElementTakerBooleanGetter<E> selector) {
		return selector.getOutput(getElement());
	}
	
	//method
	/**
	 * @return the element of this list node
	 */
	public final E getElement() {
		return element;
	}
	
	//method
	/**
	 * @return the element of the next node of this list node
	 * @throws Exception if this list node has no next node
	 */
	public final E getElementOfNextNode() {
		return getNextNode().getElement();
	}
	
	//method
	/**
	 * @return the next node of this list node
	 * @throws Exception if this list node has no next node
	 */
	public final ListNode<E> getNextNode() {
		
		if (!hasNextNode()) {
			throw new UnexistingAttributeException(this, "next node");
		}
		
		return nextNode;
	}
	
	//method
	/**
	 * @return true if this list node has a next node
	 */
	public final boolean hasNextNode() {
		return (nextNode != null);
	}
	
	//method
	/**
	 * Removes the next node of this list node.
	 */
	public final void removeNextNode() {
		nextNode = null;
	}
	
	//method
	/**
	 * Sets the element of this list node.
	 * 
	 * @param element
	 * @throws Exception if the given element is null
	 */
	public final void setElement(E element) {
		
		Validator.throwExceptionIfValueIsNull("element", element);
		
		this.element = element;
	}
	
	//method
	/**
	 * Sets the next node of this list node.
	 * 
	 * @param nextNode
	 */
	public final void setNextNode(ListNode<E> nextNode) {
		this.nextNode = nextNode;
	}
	
	//method
	/**
	 * Swaps the element of this list node with the element of the next node of this node.
	 * 
	 * @throws Exception if this node has no next node
	 */
	public final void swapElementWithNextNode() {
		E temp = getNextNode().getElement();
		getNextNode().setElement(getElement());
		setElement(temp);
	}
}
