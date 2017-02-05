/*
 * file:	VerticalStack.java
 * author:	Silvan Wyss
 * month:	2015-12
 * lines:	110
 */

//package declaration
package ch.nolix.element.dialog;

//own imports
import ch.nolix.common.container.List;
import ch.nolix.common.specification.Specification;

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
	public VerticalStack(List<Specification> attributes) {
		addOrChangeAttributes(attributes);
	}
		
	//method
	/**
	 * @return the current height of the content of this vertical stack
	 */
	protected final int getContentHeight() {
		
		int contentHeight = 0;
		
		for (Rectangle<?, ?> r: getRefRectangles()) {
			contentHeight += r.getHeightWhenNotCollapsed();
		}
				
		if (containsAny()) {
			contentHeight += (getRefRectangles().getSize() - 1) * getElementMargin();
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
	protected final void setRelativePosition(final int relativeXPosition, final int relativeYPosition) {
		
		//Calls method of the base class.
		super.setRelativePosition(relativeXPosition, relativeYPosition);
		
		switch (getContentOrientation()) {
			case LeftTop:
			case LeftBottom:
			case Left:	
				final int x = getContentXPosition();
				int y = getContentYPosition();
				for (Rectangle<?, ?> r: getRefShownRectangles()) {
					r.setRelativePosition(x, y);
					y += r.getHeight() + getElementMargin();
				}
				break;
			case Top:
			case Center:
			case Bottom:
				final int maxRectangleWidth2 = getRefShownRectangles().getMaxInt(r -> r.getWidth());
				final int x2 = getContentXPosition();
				int y2 = getContentYPosition();
				for (Rectangle<?, ?> r: getRefShownRectangles()) {			
					r.setRelativePosition((int)(x2 + 0.5 * (maxRectangleWidth2 - r.getWidth())), y2);
					y2 += r.getHeight() + getElementMargin();
				}
				break;
			case RightTop:
			case Right:
			case RightBottom:
				final int maxRectangleWidth3 = getRefShownRectangles().getMaxInt(r -> r.getWidth());
				final int x3 = getContentXPosition();
				int y3 = getContentYPosition();
				for (Rectangle<?, ?> r: getRefShownRectangles()) {
					r.setRelativePosition(x3 + maxRectangleWidth3 - r.getWidth(), y3);
					y3 += r.getHeight() + getElementMargin();
				}
		}
	}
}
