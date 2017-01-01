/*
 * file:	SingleContainer.java
 * author:	Silvan Wyss
 * month:	2015
 * lines:	140
 */

//package declaration
package ch.nolix.element.dialog;

//own imports
import java.awt.Graphics;

import ch.nolix.common.container.List;
import ch.nolix.common.specification.Specification;

//class
public final class SingleContainer
	extends Container<SingleContainer, SimpleBorderableRectangleStructure> {
	
	//constants
	public final static String SIMPLE_CLASS_NAME = "SingleContainer";
	public final static String NONE = "NoSingleContainer";
	
	//attributes
	private Rectangle<?, ?> rectangle;
	
	//constructor
	/**
	 * Creates new single container that has default attributes.
	 */
	public SingleContainer() {
		
		//Calls constructor of the base class.
		super(
			new SimpleBorderableRectangleStructure(),
			new SimpleBorderableRectangleStructure(),
			new SimpleBorderableRectangleStructure()
		);
		
		rectangle = new Area();
		resetConfiguration();
	}
	
	//constructor
	/**
	 * Creates new single container that has the given attributes.
	 * @param attributes
	 * @throws Exception if the given attributes are not valid
	 */
	public SingleContainer(List<Specification> attributes) {
		
		//Calls other constructor.
		this();
		
		addOrChangeAttributes(attributes);
	}
	
	//method
	/**
	 * @return the attributes of this element
	 */
	public final List<Specification> getAttributes() {
		
		//Calls method of the base class.
		List<Specification> attributes = super.getAttributes();
		
		attributes.addAtEnd(getRefRectangle().getSpecification());
		return attributes;
	}
	
	//method
	/**
	 * @return the rectangle of this single container
	 */
	public final Rectangle<?, ?> getRefRectangle() {
		return rectangle;
	}
	
	//method
	/**
	 * @return the rectangles of this container
	 */
	public final List<Rectangle<?, ?>> getRefRectangles() {
		List<Rectangle<?, ?>> rectangles = new List<Rectangle<?, ?>>();
		rectangles.addAtEnd(getRefRectangle());
		return rectangles;
	}
	
	//method
	/**
	 * @return the rectangles of this single container that are shown
	 */
	public List<Rectangle<?, ?>> getRefShownRectangles() {
		return new List<Rectangle<?, ?>>().addAtEnd(getRefRectangle());
	}
	
	public final void addOrChangeAttribute(Specification attribute) {
		
		if (attribute.hasHeader() && getRefDialog().canCreateRectangle(attribute.getHeader())) {
			setRectangle(getRefDialog().createsAndAddsRectangle(attribute));
			return;
		}
		
		//Calls method of the base class.
		super.addOrChangeAttribute(attribute);
	}
	
	//method
	/**
	 * Sets the given rectangle as the rectangle of this single container.
	 * @param rectangle
	 * @throws Exception if the given rectangle does not belong to the dialog this single container belongs to
	 */
	public void setRectangle(Rectangle<?, ?> rectangle) {
		rectangle.setDialog(getRefDialog());
		this.rectangle = rectangle;
	}
	
	//method
	/**
	 * @return the height of the content of this borderable rectangle
	 */
	protected final int getContentHeight() {
		return getRefRectangle().getHeightWhenNotCollapsed();
	}
	
	//method
	/**
	 * @return the width of the content of this borderable rectangle
	 */
	protected final int getContentWidth() {
		return getRefRectangle().getWidth();
	}
	
	//method
	/**
	 * Sets the position of this rectangle.
	 * @param distanceFromLeftPanelBorder
	 * @param distanceFromTopPanelBorder
	 * @throws Exception if the given distance from left panel border is negative or the given distance from top panel border is negative
	 */
	protected final void setRelativePosition(int distanceFromLeftPanelBorder, int distanceFromTopPanelBorder) {
		
		//Calls method of the base class.
		super.setRelativePosition(distanceFromLeftPanelBorder, distanceFromTopPanelBorder);
		
		getRefRectangle().setRelativePosition(
				distanceFromLeftPanelBorder + getContentXPosition(),
				distanceFromTopPanelBorder + getContentYPosition()
		);
	}

	@Override
	protected void paintContent(
			SimpleBorderableRectangleStructure rectangleStructure,
			Graphics graphics) {
		// TODO Auto-generated method stub
		
	}
}
