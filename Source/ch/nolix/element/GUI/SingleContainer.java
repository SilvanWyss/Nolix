/*
 * file:	SingleContainer.java
 * author:	Silvan Wyss
 * month:	2015
 * lines:	140
 */

//package declaration
package ch.nolix.element.GUI;

//own imports
import ch.nolix.core.container.List;
import ch.nolix.core.specification.Specification;
import ch.nolix.core.specification.StandardSpecification;
import ch.nolix.element.painter.IPainter;

//class
public final class SingleContainer
extends Container<SingleContainer, SingleContainerLook> {
	
	//type name
	public static final String TYPE_NAME = "SingleContainer";
	
	//attribute
	private Widget<?, ?> widget;
	
	//constructor
	/**
	 * Creates a new single container that has default attributes.
	 */
	public SingleContainer() {
		
		widget = new Area();
		resetConfiguration();
	}
	
	//constructor
	/**
	 * Creates a new single container that has the given attributes.
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
	 * {@inheritDoc}
	 */
	public final List<StandardSpecification> getAttributes() {
		
		//Calls method of the base class.
		List<StandardSpecification> attributes = super.getAttributes();
		
		attributes.addAtEnd(getRefRectangle().getSpecification());
		return attributes;
	}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	public CursorIcon getContentAreaCursorIcon() {
		return getCursorIcon();
	}
	
	//method
	/**
	 * @return the rectangle of this single container
	 */
	public final Widget<?, ?> getRefRectangle() {
		return widget;
	}
	
	public final void addOrChangeAttribute(StandardSpecification attribute) {
		
		if (attribute.hasHeader() && GUI.canCreateWidget(attribute.getHeader())) {
			setRectangle(GUI.createWidget(attribute));
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
		setParentWidget(rectangle);
		this.widget = rectangle;
	}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	protected void applyUsableConfigurationWhenConfigurationIsReset() {}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	protected void fillUpWidgets(final List<Widget<?, ?>> list) {}
	
	//method
	/**
	 * @return the height of the content of this borderable rectangle
	 */
	protected final int getContentAreaHeight() {
		return getRefRectangle().getHeightWhenNotCollapsed();
	}
	
	//method
	/**
	 * @return the width of the content of this borderable rectangle
	 */
	protected final int getContentAreaWidth() {
		return getRefRectangle().getWidth();
	}
	
	//method
	/**
	 * Sets the position of this rectangle.
	 * @param distanceFromLeftPanelBorder
	 * @param distanceFromTopPanelBorder
	 * @throws Exception if the given distance from left panel border is negative or the given distance from top panel border is negative
	 */
	protected final void setPositionOnParent(int distanceFromLeftPanelBorder, int distanceFromTopPanelBorder) {
		
		//Calls method of the base class.
		super.setPositionOnParent(distanceFromLeftPanelBorder, distanceFromTopPanelBorder);
		
		getRefRectangle().setPositionOnParent(
				distanceFromLeftPanelBorder + getContentAreaXPosition(),
				distanceFromTopPanelBorder + getContentAreaYPosition()
		);
	}
	
	protected void paintContentArea(
		SingleContainerLook rectangleStructure,
		IPainter painter) {
		
		widget.paintUsingPositionOnParent(painter);
	}

	//method
	protected SingleContainerLook createWidgetLook() {
		return new SingleContainerLook();
	}
}
