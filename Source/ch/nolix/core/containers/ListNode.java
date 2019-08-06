//package declaration
package ch.nolix.core.containers;

//own imports
import ch.nolix.core.constants.VariableNameCatalogue;
import ch.nolix.core.functionAPI.IElementTakerBooleanGetter;
import ch.nolix.core.invalidArgumentException.ArgumentMissesAttributeException;
import ch.nolix.core.validator.Validator;

//package-visible class
/**
 * A {@link ListNode} contains an element.
 * A {@link ListNode} can have a next node.
 * 
 * @author Silvan Wyss
 * @month 2015-12
 * @lines 150
 * @param <E> The type of the element of a {@link ListNode}.
 */
final class ListNode<E> {

	//attribute
	private E element;
	
	//optional attribute
	private ListNode<E> nextNode;
	
	//constructor
	/**
	 * Creates a new {@link ListNode} with the given element.
	 * 
	 * @param element
	 * @throws NullArgumentException if the given element is null.
	 */
	public ListNode(final E element) {
		setElement(element);
	}
	
	//method
	/**
	 * @param selector
	 * @return true if the current {@link ListNode}
	 * contains an element the given selector selects.
	 */
	public boolean contains(final IElementTakerBooleanGetter<E> selector) {
		return selector.getOutput(getElement());
	}
	
	// method
	/**
	 * @param element
	 * @return true if the current {@link ListNode} contains the given element.
	 */
	public boolean contains(final Object element) {
		return (getElement() == element);
	}
	
	//method
	/**
	 * @return the element of the current {@link ListNode}.
	 */
	public E getElement() {
		return element;
	}
	
	//method
	/**
	 * @return the next node of the current {@link ListNode}.
	 * @throws UnexistringAttributeException
	 * if the current {@link ListNode} does not have a next node.
	 */
	public ListNode<E> getNextNode() {
		
		//Checks if the current list node has a next node.
		if (!hasNextNode()) {
			throw new ArgumentMissesAttributeException(this, "next node");
		}
		
		return nextNode;
	}
	
	//method
	/**
	 * @return true if the current {@link ListNode} has a next node.
	 */
	public boolean hasNextNode() {
		return (nextNode != null);
	}
	
	//method
	/**
	 * Removes the next node of the current {@link ListNode}.
	 */
	public void removeNextNode() {
		nextNode = null;
	}
	
	//method
	/**
	 * Sets the element of the current {@link ListNode}.
	 * 
	 * @param element
	 * @throws NullArgumentException if the given element is null.
	 */
	public void setElement(final E element) {
		
		//Checks if the given element is not null.
		Validator
		.suppose(element)
		.thatIsNamed(VariableNameCatalogue.ELEMENT)
		.isNotNull();
		
		//Sets the element of the current list node.
		this.element = element;
	}
	
	//method
	/**
	 * Sets the next node of the current {@link ListNode}.
	 * 
	 * @param nextNode
	 * @throws NullArgumentException if the given next node is null.
	 */
	public void setNextNode(final ListNode<E> nextNode) {
		
		//Checks if the given next node is not null.
		Validator.suppose(nextNode).thatIsNamed("next node").isNotNull();
		
		//Sets the next node of the current list node.
		this.nextNode = nextNode;
	}
	
	//method
	/**
	 * Swaps the element of the current {@link ListNode}
	 * with the element of the next node of the current {@link ListNode}.
	 * 
	 * @throws ArgumentMissesAttributeException
	 * if the current {@link ListNode} does not have a next node.
	 */
	public void swapElementWithNextNode() {
		
		//Checks if the current list node has a next node.
		if (!hasNextNode()) {
			throw new ArgumentMissesAttributeException(this, "next node");
		}
				
		final E element = nextNode.getElement();
		nextNode.setElement(getElement());
		setElement(element);
	}
}
