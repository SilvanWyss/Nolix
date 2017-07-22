/*
 * file:	VerticalStack.java
 * author:	Silvan Wyss
 * month:	2015-12
 * lines:	110
 */

//package declaration
package ch.nolix.element.GUI;

//own imports
import ch.nolix.core.container.List;
import ch.nolix.core.specification.StandardSpecification;

//class
public final class VerticalStack extends Stack<VerticalStack> {
	
	//constant
	public static final String SIMPLE_CLASS_NAME = "VerticalStack";
	
	//attribute header
	public static final String LAYOUT = "Layout";

	//constructor
	/**
	 * Creates new vertical stack with default values.
	 */
	public VerticalStack() {}
	
	//constructor
	/**
	 * Creates new vertical stack with the given attributes.
	 * 
	 * @param attributes
	 * @throws Exception if the given attributes are not valid
	 */
	public VerticalStack(List<StandardSpecification> attributes) {
		addOrChangeAttributes(attributes);
	}
	
	public VerticalStack(final Widget<?, ?>... rectangles) {
		
		for (final Widget<?, ?> r: rectangles) {
			addRectangle(r);
		}
	}
		
	//method
	/**
	 * @return the current height of the content of this vertical stack
	 */
	protected final int getContentHeight() {
		
		int contentHeight = 0;
		
		for (Widget<?, ?> r: getRefRectangles()) {
			contentHeight += r.getHeightWhenNotCollapsed();
		}
				
		if (containsAny()) {
			contentHeight += (getRefRectangles().getElementCount() - 1) * getElementMargin();
		}
		
		return contentHeight;
	}
	
	//method
	/**
	 * @return the current width of the content of this vertical stack
	 */
	protected final int getContentWidth() {
		
		if (isEmpty()) {
			return 0;
		}
		
		return getRefRectangles().getMaxInt(r -> r.getWidth());
	}
	
	//method
	/**
	 * Sets the relative position of this vertical stack.
	 * 
	 * @param relativeXPosition
	 * @param relativeYPosition
	 */
	protected final void setPositionOnContainer(final int relativeXPosition, final int relativeYPosition) {
		
		//Calls method of the base class.
		super.setPositionOnContainer(relativeXPosition, relativeYPosition);
		
		switch (getContentOrientation()) {
			case LeftTop:
			case LeftBottom:
			case Left:	
				final int x = getContentXPosition();
				int y = getContentYPosition();
				for (Widget<?, ?> r: getRefShownRectangles()) {
					r.setPositionOnContainer(x, y);
					y += r.getHeight() + getElementMargin();
				}
				break;
			case Top:
			case Center:
			case Bottom:
				final int maxRectangleWidth2 = getRefShownRectangles().getMaxInt(r -> r.getWidth());
				final int x2 = getContentXPosition();
				int y2 = getContentYPosition();
				for (Widget<?, ?> r: getRefShownRectangles()) {			
					r.setPositionOnContainer((int)(x2 + 0.5 * (maxRectangleWidth2 - r.getWidth())), y2);
					y2 += r.getHeight() + getElementMargin();
				}
				break;
			case RightTop:
			case Right:
			case RightBottom:
				final int maxRectangleWidth3 = getRefShownRectangles().getMaxInt(r -> r.getWidth());
				final int x3 = getContentXPosition();
				int y3 = getContentYPosition();
				for (Widget<?, ?> r: getRefShownRectangles()) {
					r.setPositionOnContainer(x3 + maxRectangleWidth3 - r.getWidth(), y3);
					y3 += r.getHeight() + getElementMargin();
				}
		}
	}
}
