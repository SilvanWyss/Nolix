//package declaration
package ch.nolix.core.container;

//own imports
import ch.nolix.core.invalidStateException.UnexistingAttributeException;
import ch.nolix.core.validator2.Validator;

//package-visible class
/**
 * A heap node contains an element.
 * A heap node can have a next node, a left sub node and a right sub node.
 * 
 * @author Silvan Wyss
 * @month 2015-12
 * @lines 110
 * @param <E> - The type of the element of a heap node.
 */
final class HeapNode<E> {
	
	//attribute
	private E element;
	
	//optional attributes
	private HeapNode<E> nextNode;
	private HeapNode<E> leftSubNode;
	private HeapNode<E> rightSubNode;
	
	//constructor
	/**
	 * Creates new heap node with the given element.
	 * 
	 * @param element
	 * @throws NullArgumentException if the given element is null.
	 */
	public HeapNode(final E element) {
		setElement(element);
	}
	
	//method
	/**
	 * @return the element of this heap node.
	 */
	public E getElement() {
		return element;
	}
	
	//method
	/**
	 * @return the next node of this heap node.
	 * @throws UnexistingAttributeException if this heap node has no next node.
	 */
	public HeapNode<E> getNextNode() {
		
		//Checks if this heap node has a next node.
		if (!hasNextNode()) {
			throw new UnexistingAttributeException(this, "next node");
		}
		
		return nextNode;
	}
	
	//method
	/**
	 * @return true if this heap node has a left sub node.
	 */
	public boolean hasLeftSubNode() {
		return (leftSubNode != null);
	}
	
	//method
	/**
	 * @return true if this heap node has a left and right sub node.
	 */
	public boolean hasLeftAndRightSubNode() {
		return (rightSubNode != null);
	}
	
	//method
	/**
	 * @return true if this heap node has a next node.
	 */
	public boolean hasNextNode() {
		return (nextNode != null);
	}
	
	//method
	/**
	 * Removes the next node of this heap node.
	 */
	public void removeNextNode() {
		nextNode = null;
	}
	
	//method
	/**
	 * Removes the right sub node of this heap node.
	 */
	public void removeRightSubNode() {
		rightSubNode = null;
	}
	
	//method
	/**
	 * Sets the element of this heap node.
	 * 
	 * @param element
	 * @throws NullArgumentException if the given element is null.
	 */
	public void setElement(final E element) {
		
		//Checks if the given element is not null.
		Validator.supposeThat(element).thatIsNamed("element").isNotNull();
		
		this.element = element;
	}
}
