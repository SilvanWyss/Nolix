/*
 * file:	HorizontalStack.java
 * author:	Silvan Wyss
 * month:	2015-12
 * lines:	80
 */

//package declaration
package ch.nolix.element.dialog;

//own imports
import ch.nolix.common.container.List;
import ch.nolix.common.specification.Specification;

//class
public final class HorizontalStack extends Stack<HorizontalStack> {
	
	//constant
	public static final String SIMPLE_CLASS_NAME = "HorizontalStack";		
	
	//constructor
	/**
	 * Creates new horizontal stack with default attributes.
	 */
	public HorizontalStack() {}
	
	//constructor
	/**
	 * Creates new horizontal stack with the given attributes.
	 * 
	 * @param attributes
	 * @throws Exception if the given attributes are not valid
	 */
	public HorizontalStack(List<Specification> attributes) {	
		addOrChangeAttributes(attributes);
	}

	//method
	/**
	 * @return the current height of the content of this horizontal stack
	 */
	protected final int getContentHeight() {
		
		if (isEmpty()) {
			return 0;
		}
		
		return getRefRectangles().getMaxInt(r -> r.getHeight());
	}
	
	//method
	/**
	 * @return the current width of the content of this horizontal stack
	 */
	protected final int getContentWidth() {
		
		int contentWidth = 0;
		
		for (Widget<?, ?> r: getRefRectangles()) {
			contentWidth += r.getWidth();
		}
			
		if (containsAny()) {
			contentWidth += (getRefRectangles().getSize() - 1) * getElementMargin();
		}
		
		return contentWidth;
	}
	
	//method
	/**
	 * Sets the relative position of this horizontal stack.
	 * 
	 * @param relativeXPosition
	 * @param relativeYPosition
	 */
	protected final void setRelativePosition(final int relativeXPosition, final int relativeYPosition) {
		
		//Calls method of the base class.
		super.setRelativePosition(relativeXPosition, relativeYPosition);
		
		//Enumerates the probable content orientations.
		switch (getContentOrientation()) {
			case LeftTop:
			case Top:
			case RightTop:
				
				int x1 = getContentXPosition();
				final int y1 = getContentYPosition();
				for (Widget<?, ?> r: getRefRectangles()) {
					r.setRelativePosition(x1, y1);
					x1 += r.getWidth() + getElementMargin();
				}
				
				break;
			case Left:
			case Center:
			case Right:
				
				final int contentHeight2 = getContentHeight();
				int x2 = getContentXPosition();
				final int y2 = getContentYPosition();
				for (Widget<?, ?> r: getRefRectangles()) {
					r.setRelativePosition(x2, y2 + (contentHeight2 - r.getHeight()) / 2);
					x2 += r.getWidth() + getElementMargin();
				}
				
				break;
			case LeftBottom:
			case Bottom:
			case RightBottom:
				
				final int contentHeight3 = getContentHeight();
				int x3 = getContentXPosition();
				final int y3 = getContentYPosition();
				for (Widget<?, ?> r: getRefRectangles()) {
					r.setRelativePosition(x3, y3 + contentHeight3 - r.getHeight());
					x3 += r.getWidth() + getElementMargin();
				}
				break;
		}
	}
}
