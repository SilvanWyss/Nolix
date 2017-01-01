/*
 * file:	Stack.java
 * author:	Silvan Wyss
 * month:	2015-12
 * lines:	230
 */

//package declaration
package ch.nolix.element.dialog;

//own imports
import ch.nolix.common.container.List;
import ch.nolix.common.interfaces.Clearable;
import ch.nolix.common.specification.Specification;
import ch.nolix.element.data.Margin;

//class
public abstract class Stack<S extends Stack<S>> 
extends Container<S, BorderableRectangleStructure<?>>
implements Clearable<S> {
	
	//constant
	public final static String SIMPLE_CLASS_NAME = "Stack";
	
	//default value
	public final static int DEFAULT_ELEMENT_MARGIN = 10;
	
	//attribute header
	private final static String ELEMENT_MARGIN = "ElementMargin";
	
	//optional attribute
	private Margin elementMargin;
	
	//multiple attribute
	private List<Rectangle<?, ?>> rectangles = new List<Rectangle<?, ?>>();
	
	//constructor
	/**
	 * Creates new stack that has default attributes.
	 */
	public Stack() {
		
		//Calls constructor of the base class.
		super(
			new SimpleBorderableRectangleStructure(),
			new SimpleBorderableRectangleStructure(),
			new SimpleBorderableRectangleStructure()
		);
	}
	
	//method
	/**
	 * Adds the given rectangle to this stack.
	 * @param rectangle
	 * @throws Exception if the given rectangle already belongs to a dialog
	 */
	@SuppressWarnings("unchecked")
	public final S addRectangle(Rectangle<?, ?> rectangle) {
		
		if (belongsToDialog()) {
			rectangle.setDialog(getRefDialog());
		}
		
		rectangles.addAtEnd(rectangle);
		
		return (S)this;
	}
	
	
	//method
	/**
	 * Adds the given rectangles to this stack.
	 * 
	 * @param rectangles
	 * @return this stack
	 * @throws Exception if:
	 * -One of the given rectangles is not valid.
	 * -One of the given rectangles belongs already to a dialog
	 */
	@SuppressWarnings("unchecked")
	public final S addRectangle(Rectangle<?, ?>... rectangles) {
		
		//Iterates the given rectangles.
		for (Rectangle<?, ?> r: rectangles) {
			addRectangle(r);
		}
		
		return (S)this;
	}
	
	//method
	/**
	 * Removes the rectangles of this stack.
	 */
	@SuppressWarnings("unchecked")
	public final S clear() {
		
		rectangles.clear();
		
		return (S)this;
	}
	
	//method
	/**
	 * @return the attributes of this stack
	 */
	public List<Specification> getAttributes() {
		
		//Calls method of the base class.
		List<Specification> attributes = super.getAttributes();
		
		if (hasElementMargin()) {
			attributes.addAtEnd(new Specification(ELEMENT_MARGIN, elementMargin.getAttributes()));
		}
		
		getRefRectangles().forEach(r -> attributes.addAtEnd(r.getSpecification()));	
		
		return attributes;
	}
	
	//method
	/**
	 * @return the element margin of this stack
	 */
	public final int getElementMargin() {
		
		if (hasElementMargin()) {
			return elementMargin.getValue();
		}
		
		return 0;
	}
	
	//method
	/**
	 * @return the rectangles of this container
	 */
	public final List<Rectangle<?, ?>> getRefRectangles() {
		return rectangles;
	}
	
	//method
	/**
	 * @return the rectangles of this container that are shown
	 */
	public final List<Rectangle<?, ?>> getRefShownRectangles() {
		return getRefRectangles();
	}
	
	//method
	/**
	 * @return true if this stack container has an element margin
	 */
	public final boolean hasElementMargin() {
		return (elementMargin != null);
	}
	
	//method
	/**
	 * @return if this stack contains no rectangles
	 */
	public final boolean isEmpty() {
		return getRefRectangles().isEmpty();
	}
	
	//method
	/**
	 * Removes the element margin of this stack.
	 */
	public final void removeElementMargin() {
		elementMargin = null;
	}
	
	//method
	/**
	 * Resets the configuration of this stack.
	 */
	public final void resetConfiguration() {
		
		//Calls method of the base class.
		super.resetConfiguration();
		
		removeElementMargin();
	}
	
	//method
	/**
	 * Sets the given attribute to this stack.
	 * 
	 * @param attribute
	 * @throws Exception if the given attribute is not valid
	 */
	public void addOrChangeAttribute(Specification attribute) {
		
		if (getRefDialog().canCreateRectangle(attribute.getHeader())) {
			addRectangle(getRefDialog().createsAndAddsRectangle(attribute));
			return;
		}
		
		switch (attribute.getHeader()) {
			case ELEMENT_MARGIN:
				setElementMargin(attribute.getOneAttributeToInteger());
				break;		
			default:
				
				//Calls method of the base class.
				super.addOrChangeAttribute(attribute);
		}
	}
	
	//method
	/**
	 * Sets the element margin of this stack.
	 * 
	 * @param elementMargin
	 * @throws Exception if the given element margin is not positive
	 */
	public final void setElementMargin(int elementMargin) {
		this.elementMargin = new Margin(elementMargin);
	}
}
