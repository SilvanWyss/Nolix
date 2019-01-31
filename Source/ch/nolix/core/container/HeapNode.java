//package declaration
package ch.nolix.core.container;

//own imports
import ch.nolix.core.constants.VariableNameCatalogue;
import ch.nolix.core.invalidStateException.ArgumentMissesAttributeException;
import ch.nolix.core.validator2.Validator;

//package-visible class
/**
 * A {@link HeapNode} contains an element.
 * A {@link HeapNode} can have a next node, a left sub node and a right sub node.
 * 
 * @author Silvan Wyss
 * @month 2015-12
 * @lines 120
 * @param <E> The type of the element of a {@link HeapNode}.
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
	 * Creates a new {@link HeapNode} with the given element.
	 * 
	 * @param element
	 * @throws NullArgumentException if the given element is null.
	 */
	public HeapNode(final E element) {
		setElement(element);
	}
	
	//method
	/**
	 * @return the element of the current {@link HeapNode}.
	 */
	public E getRefElement() {
		return element;
	}
	
	//method
	/**
	 * @return the next node of the current {@link HeapNode}.
	 * @throws ArgumentMissesAttributeException if the current {@link HeapNode} does not have a next node.
	 */
	public HeapNode<E> getRefNextNode() {
		
		supposeHasNextNode();
		
		return nextNode;
	}
	
	//method
	/**
	 * @return true if the current {@link HeapNode} has a left sub node.
	 */
	public boolean hasLeftSubNode() {
		return (leftSubNode != null);
	}
	
	//method
	/**
	 * @return true if the current {@link HeapNode} has a left and right sub node.
	 */
	public boolean hasLeftAndRightSubNode() {
		return (rightSubNode != null);
	}
	
	//method
	/**
	 * @return true if the current {@link HeapNode} has a next node.
	 */
	public boolean hasNextNode() {
		return (nextNode != null);
	}
	
	//method
	/**
	 * Removes the next node of the current {@link HeapNode}.
	 */
	public void removeNextNode() {
		nextNode = null;
	}
	
	//method
	/**
	 * Removes the right sub node of the current {@link HeapNode}.
	 */
	public void removeRightSubNode() {
		rightSubNode = null;
	}
	
	//method
	/**
	 * Sets the element of the current {@link HeapNode}.
	 * 
	 * @param element
	 * @throws NullArgumentException if the given element is null.
	 */
	public void setElement(final E element) {
		
		//Checks if the given element is not null.
		Validator.suppose(element).thatIsNamed(VariableNameCatalogue.ELEMENT).isNotNull();
		
		//Sets the element of the current heap node.
		this.element = element;
	}
	
	//method
	/**
	 * @throws ArgumentMissesAttributeException if the current {@link HeapNode} does not have a next node.
	 */
	private void supposeHasNextNode() {
		if (!hasNextNode()) {
			throw new ArgumentMissesAttributeException(this, "next node");
		}
	}
}
