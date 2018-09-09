//package declaration
package ch.nolix.core.container;

import ch.nolix.core.functionAPI.IElementTakerBooleanGetter;
import ch.nolix.primitive.invalidStateException.UnexistingAttributeException;
import ch.nolix.primitive.validator2.Validator;

//package-visible class
/**
 * A list node contains an element that is an instance.
 * A list node can have a next node.
 * 
 * @author Silvan Wyss
 * @month 2015-12
 * @lines 140
 * @param <E> - The type of the element of a list node.
 */
final class ListNode<E> {

	//attribute
	private E element;
	
	//optional attribute
	private ListNode<E> nextNode;
	
	//constructor
	/**
	 * Creates a new list node with the given element.
	 * 
	 * @param element
	 * @throws NullArgumentException if the given element is not an instance.
	 */
	public ListNode(final E element) {		
		setElement(element);
	}
	
	//method
	/**
	 * @param selector
	 * @return true if this list node contains an element the given selector selects.
	 */
	public boolean contains(final IElementTakerBooleanGetter<E> selector) {
		return selector.getOutput(getElement());
	}
	
	// method
	/**
	 * @param element
	 * @return true if this list node contains the given element.
	 */
	public boolean contains(final Object element) {
		return (getElement() == element);
	}
	
	//method
	/**
	 * @return the element of this list node.
	 */
	public E getElement() {
		return element;
	}
	
	//method
	/**
	 * @return the next node of this list node.
	 * @throws UnexistringAttributeException if this list node has no next node.
	 */
	public ListNode<E> getNextNode() {
		
		//Checks if this list node has a next node.
		if (!hasNextNode()) {
			throw new UnexistingAttributeException(this, "next node");
		}
		
		return nextNode;
	}
	
	//method
	/**
	 * @return true if this list node has a next node.
	 */
	public boolean hasNextNode() {
		return (nextNode != null);
	}
	
	//method
	/**
	 * Removes the next node of this list node.
	 */
	public void removeNextNode() {
		nextNode = null;
	}
	
	//method
	/**
	 * Sets the element of this list node.
	 * 
	 * @param element
	 * @throws NullArgumentException if the given element is not an instance.
	 */
	public void setElement(final E element) {
		
		//Checks if the given element is an instance.
		Validator.suppose(element).thatIsNamed("element").isInstance();
		
		//Sets the element of this list node.
		this.element = element;
	}
	
	//method
	/**
	 * Sets the next node of this list node.
	 * 
	 * @param nextNode
	 * @throws NullArgumentException if the given next node is not an instance.
	 */
	public  void setNextNode(final ListNode<E> nextNode) {
		
		//Checks if the given next node is an instance.
		Validator.suppose(nextNode).thatIsNamed("next node").isInstance();
		
		//Sets the next node of this list node.
		this.nextNode = nextNode;
	}
	
	//method
	/**
	 * Swaps the element of this list node with the element of the next node of this list node.
	 * 
	 * @throws UnexistingAttributeException if this list node has no next node.
	 */
	public void swapElementWithNextNode() {
		
		//Checks if this list node has a next node.
		if (!hasNextNode()) {
			throw new UnexistingAttributeException(this, "next node");
		}
				
		final E element = nextNode.getElement();
		nextNode.setElement(getElement());
		setElement(element);
	}
}
