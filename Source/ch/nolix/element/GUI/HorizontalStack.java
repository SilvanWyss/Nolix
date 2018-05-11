/*
 * file:	HorizontalStack.java
 * author:	Silvan Wyss
 * month:	2015-12
 * lines:	80
 */

//package declaration
package ch.nolix.element.GUI;

//class
public final class HorizontalStack extends Stack<HorizontalStack> {
	
	//type name
	public static final String TYPE_NAME = "HorizontalStack";		
	
	//constructor
	/**
	 * Creates a new horizontal stack with default attributes.
	 */
	public HorizontalStack() {
		reset();
		approveProperties();
	}
	
	//constructor
	/**
	 * Creates a new horizontal stack with the given widgets.
	 * 
	 * @param widgets
	 * @throws NullArgumentException if one of the given widgets is null.
	 * @throws InvalidArgumentException
	 * if one of the given widgets belongs to another GUI than this horizontal stack.
	 */
	public HorizontalStack(final Widget<?, ?>... widgets) {
		
		//Calls other constructor.
		this();
		
		addWidget(widgets);
	}

	//method
	/**
	 * @return the current height of the content of this horizontal stack
	 */
	protected final int getContentAreaHeight() {
		
		if (isEmpty()) {
			return 0;
		}
		
		return getRefWidgets().getMaxInt(r -> r.getHeight());
	}
	
	//method
	/**
	 * @return the current width of the content of this horizontal stack
	 */
	protected final int getContentAreaWidth() {
		
		int contentWidth = 0;
		
		for (Widget<?, ?> r: getRefWidgets()) {
			contentWidth += r.getWidth();
		}
			
		if (containsAny()) {
			contentWidth += (getRefWidgets().getElementCount() - 1) * getActiveElementMargin();
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
	protected final void setPositionOnParent(final int relativeXPosition, final int relativeYPosition) {
		
		//Calls method of the base class.
		super.setPositionOnParent(relativeXPosition, relativeYPosition);
		
		//Enumerates the probable content orientations.
		switch (getContentPosition()) {
			case LeftTop:
			case Top:
			case RightTop:
				
				int x1 = 0;
				final int y1 = 0;
				for (Widget<?, ?> r: getRefWidgets()) {
					r.setPositionOnParent(x1, y1);
					x1 += r.getWidth() + getActiveElementMargin();
				}
				
				break;
			case Left:
			case Center:
			case Right:
				
				final int contentHeight2 = getContentAreaHeight();
				int x2 = 0;
				final int y2 = 0;
				for (Widget<?, ?> r: getRefWidgets()) {
					r.setPositionOnParent(x2, y2 + (contentHeight2 - r.getHeight()) / 2);
					x2 += r.getWidth() + getActiveElementMargin();
				}
				
				break;
			case LeftBottom:
			case Bottom:
			case RightBottom:
				
				final int contentHeight3 = getContentAreaHeight();
				int x3 = 0;
				final int y3 = 0;
				for (Widget<?, ?> r: getRefWidgets()) {
					r.setPositionOnParent(x3, y3 + contentHeight3 - r.getHeight());
					x3 += r.getWidth() + getActiveElementMargin();
				}
				break;
		}
	}
}
