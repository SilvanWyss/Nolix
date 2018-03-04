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

import ch.nolix.core.container.ReadContainer;
import ch.nolix.core.container.List;
import ch.nolix.core.specification.Specification;
import ch.nolix.core.specification.StandardSpecification;

//class
public final class SingleContainer
extends Container<SingleContainer, SingleContainerStructure> {
	
	//type name
	public static final String TYPE_NAME = "SingleContainer";
	
	//attribute
	private Widget<?, ?> widget;
	
	//constructor
	/**
	 * Creates new single container that has default attributes.
	 */
	public SingleContainer() {
		
		widget = new Area();
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
	public final List<StandardSpecification> getAttributes() {
		
		//Calls method of the base class.
		List<StandardSpecification> attributes = super.getAttributes();
		
		attributes.addAtEnd(getRefRectangle().getSpecification());
		return attributes;
	}
	
	//method
	/**
	 * @return the rectangle of this single container
	 */
	public final Widget<?, ?> getRefRectangle() {
		return widget;
	}
	
	//method
	/**
	 * @return the rectangles of this single container that are shown
	 */
	public ReadContainer<Widget<?, ?>> getRefShownWidgets() {
		return getRefWidgets();
	}
	
	public final void addOrChangeAttribute(StandardSpecification attribute) {
		
		if (attribute.hasHeader() && getRefGUI().canCreateWidget(attribute.getHeader())) {
			setRectangle(getRefGUI().createWidget(attribute));
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
		rectangle.setGUI(getRefGUI());
		this.widget = rectangle;
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
	protected final void setPositionOnContainer(int distanceFromLeftPanelBorder, int distanceFromTopPanelBorder) {
		
		//Calls method of the base class.
		super.setPositionOnContainer(distanceFromLeftPanelBorder, distanceFromTopPanelBorder);
		
		getRefRectangle().setPositionOnContainer(
				distanceFromLeftPanelBorder + getContentXPosition(),
				distanceFromTopPanelBorder + getContentYPosition()
		);
	}
	
	protected void paintContent(
		SingleContainerStructure rectangleStructure,
		Graphics graphics) {
		
		widget.paintUsingPositionOnContainer(graphics);
	}

	protected SingleContainerStructure createWidgetStructure() {
		return new SingleContainerStructure();
	}
	public ReadContainer<Widget<?, ?>> getRefWidgets() {
		return new ReadContainer<Widget<?, ?>>();
	}
}
