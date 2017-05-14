/*
 * file:	SingleContainer.java
 * author:	Silvan Wyss
 * month:	2015
 * lines:	140
 */

//package declaration
package ch.nolix.element.GUI;

//own imports
import java.awt.Graphics;

import ch.nolix.core.container.List;
import ch.nolix.core.specification.Specification;

//class
public final class SingleContainer
extends Container<SingleContainerStructure, SingleContainer> {
	
	//constants
	public static final String SIMPLE_CLASS_NAME = "SingleContainer";
	public static final String NONE = "NoSingleContainer";
	
	//attributes
	private Widget<?, ?> rectangle;
	
	//constructor
	/**
	 * Creates new single container that has default attributes.
	 */
	public SingleContainer() {
		
		//Calls constructor of the base class.
		super(
			new SingleContainerStructure(),
			new SingleContainerStructure(),
			new SingleContainerStructure()
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
	public final Widget<?, ?> getRefRectangle() {
		return rectangle;
	}
	
	//method
	/**
	 * @return the rectangles of this container
	 */
	public final List<Widget<?, ?>> getRefRectangles() {
		List<Widget<?, ?>> rectangles = new List<Widget<?, ?>>();
		rectangles.addAtEnd(getRefRectangle());
		return rectangles;
	}
	
	//method
	/**
	 * @return the rectangles of this single container that are shown
	 */
	public List<Widget<?, ?>> getRefShownRectangles() {
		return new List<Widget<?, ?>>().addAtEnd(getRefRectangle());
	}
	
	public final void addOrChangeAttribute(Specification attribute) {
		
		if (attribute.hasHeader() && getRefDialog().canCreateWidget(attribute.getHeader())) {
			setRectangle(getRefDialog().createAndAddWidget(attribute));
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
	public void setRectangle(Widget<?, ?> rectangle) {
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
			SingleContainerStructure rectangleStructure,
			Graphics graphics) {
		// TODO Auto-generated method stub
		
	}
}
