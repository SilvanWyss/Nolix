/*
 * file:	AlphaHeapNode.java
 * author:	Silvan Wyss
 * month:	2015-12
 * lines:	100
 */

//package declaration
package ch.nolix.common.container;

//own import
import ch.nolix.common.util.Validator;

//package-visible class
final class HeapNode<E> {
	
	//attribute
	private E element;
	
	//optional attributes
	private HeapNode<E> nextNode;
	private HeapNode<E> leftSubNode;
	private HeapNode<E> rightSubNode;
	
	//constructor
	/**
	 * Creates new alpha heap node with the given element.
	 * 
	 * @param element
	 * @throws Exception if the given element is null
	 */
	public HeapNode(E element) {
		setElement(element);
	}
	
	//method
	/**
	 * @return the element of this alpha heap node
	 */
	public final E getElement() {
		return element;
	}
	
	//method
	/**
	 * @return the next node of this alpha heap node
	 */
	public final HeapNode<E> getNextNode() {
		return nextNode;
	}
	
	//method
	/**
	 * @return true if this alpha heap node has a next node
	 */
	public final boolean hasNextNode() {
		return (nextNode != null);
	}
	
	//method
	/**
	 * @return true if this alpha heap node has a left sub node
	 */
	public final boolean hasLeftSubNode() {
		return (leftSubNode != null);
	}
	
	//method
	/**
	 * @return true if this alpha heap node has a left and right sub node
	 */
	public final boolean hasLeftAndRightSubNode() {
		return (rightSubNode != null);
	}
	
	//method
	/**
	 * Removes the next node of this alpha heap node.
	 */
	public final void removeNextNode() {
		nextNode = null;
	}
	
	//method
	/**
	 * Removes the right sub node of this alpha heap node.
	 */
	public final void removeRightSubNode() {
		rightSubNode = null;
	}
	
	//method
	/**
	 * Sets the element of this alpha heap node.
	 * 
	 * @param element
	 * @throws Exception if the given element is null
	 */
	public final void setElement(E element) {
		
		Validator.throwExceptionIfValueIsNull("element", element);
		
		this.element = element;
	}
}
