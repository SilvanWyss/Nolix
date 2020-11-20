//package declaration
package ch.nolix.common.container;

//own imports
import ch.nolix.common.constant.VariableNameCatalogue;
import ch.nolix.common.functionAPI.IElementTakerBooleanGetter;
import ch.nolix.common.invalidArgumentException.ArgumentDoesNotHaveAttributeException;
import ch.nolix.common.validator.Validator;

//class
/**
 * A {@link LinkedListNode} contains an element.
 * A {@link LinkedListNode} can have a next node.
 * 
 * @author Silvan Wyss
 * @month 2015-12
 * @lines 150
 * @param <E> The type of the element of a {@link LinkedListNode}.
 */
final class LinkedListNode<E> {

	//attribute
	private E element;
	
	//optional attribute
	private LinkedListNode<E> nextNode;
	
	//constructor
	/**
	 * Creates a new {@link LinkedListNode} with the given element.
	 * 
	 * @param element
	 * @throws ArgumentIsNullException if the given element is null.
	 */
	public LinkedListNode(final E element) {
		setElement(element);
	}
	
	//method
	/**
	 * @param selector
	 * @return true if the current {@link LinkedListNode}
	 * contains an element the given selector selects.
	 */
	public boolean contains(final IElementTakerBooleanGetter<E> selector) {
		return selector.getOutput(getElement());
	}
	
	// method
	/**
	 * @param element
	 * @return true if the current {@link LinkedListNode} contains the given element.
	 */
	public boolean contains(final Object element) {
		return (getElement() == element);
	}
	
	//method
	/**
	 * @return the element of the current {@link LinkedListNode}.
	 */
	public E getElement() {
		return element;
	}
	
	//method
	/**
	 * @return the next node of the current {@link LinkedListNode}.
	 * @throws UnexistringAttributeException
	 * if the current {@link LinkedListNode} does not have a next node.
	 */
	public LinkedListNode<E> getNextNode() {
		
		//Asserts that the current list node has a next node.
		if (!hasNextNode()) {
			throw new ArgumentDoesNotHaveAttributeException(this, "next node");
		}
		
		return nextNode;
	}
	
	//method
	/**
	 * @return true if the current {@link LinkedListNode} has a next node.
	 */
	public boolean hasNextNode() {
		return (nextNode != null);
	}
	
	//method
	/**
	 * Removes the next node of the current {@link LinkedListNode}.
	 */
	public void removeNextNode() {
		nextNode = null;
	}
	
	//method
	/**
	 * Sets the element of the current {@link LinkedListNode}.
	 * 
	 * @param element
	 * @throws ArgumentIsNullException if the given element is null.
	 */
	public void setElement(final E element) {
		
		//Asserts that the given element is not null.
		Validator
		.assertThat(element)
		.thatIsNamed(VariableNameCatalogue.ELEMENT)
		.isNotNull();
		
		//Sets the element of the current list node.
		this.element = element;
	}
	
	//method
	/**
	 * Sets the next node of the current {@link LinkedListNode}.
	 * 
	 * @param nextNode
	 * @throws ArgumentIsNullException if the given next node is null.
	 */
	public void setNextNode(final LinkedListNode<E> nextNode) {
		
		//Asserts that the given next node is not null.
		Validator.assertThat(nextNode).thatIsNamed("next node").isNotNull();
		
		//Sets the next node of the current list node.
		this.nextNode = nextNode;
	}
	
	//method
	/**
	 * Swaps the element of the current {@link LinkedListNode}
	 * with the element of the next node of the current {@link LinkedListNode}.
	 * 
	 * @throws ArgumentDoesNotHaveAttributeException
	 * if the current {@link LinkedListNode} does not have a next node.
	 */
	public void swapElementWithNextNode() {
		
		//Asserts that the current list node has a next node.
		if (!hasNextNode()) {
			throw new ArgumentDoesNotHaveAttributeException(this, "next node");
		}
				
		final E lElement = nextNode.getElement();
		nextNode.setElement(getElement());
		setElement(lElement);
	}
}
