//package declaration
package ch.nolix.element.GUI;

//class
/**
 * @author Silvan Wyss
 * @month 2015-12
 * @lines 120
 */
public final class VerticalStack extends Stack<VerticalStack> {
	
	//type name
	public static final String TYPE_NAME = "VerticalStack";

	//constructor
	/**
	 * Creates a new vertical stack with default values.
	 */
	public VerticalStack() {
		reset();
		approveProperties();
	}
	
	//constructor
	/**
	 * Creates a new vertical stack with the given widgets
	 * 
	 * @param widgets
	 * @throws NullArgumentException if one of the given widgets is null.
	 */
	public VerticalStack(final Widget<?, ?>... widgets) {
		
		//Calls other constructor.
		this();
		
		addWidget(widgets);
	}
		
	//method
	/**
	 * @return the height of the content of this vertical stack.
	 */
	protected int getContentHeight() {
		
		int contentHeight = getRefWidgets().getSumByInt(w -> w.getHeight());
				
		//Handles the case that this vertical stack contains any widget.
		if (containsAny()) {
			contentHeight
			+= (getRefWidgets().getElementCount() - 1) * getActiveElementMargin();
		}
		
		return contentHeight;
	}
	
	//method
	/**
	 * @return the width of the content of this vertical stack.
	 */
	protected int getContentWidth() {
		
		//Handles the case that this vertical stack contains no widget.
		if (isEmpty()) {
			return 0;
		}
		
		//Handles the case that this vertical stack contains at least 1 widget.
		return getRefWidgets().getMaxInt(w -> w.getWidth());
	}
	
	//method
	/**
	 * Sets the relative position of this vertical stack.
	 * 
	 * @param relativeXPosition
	 * @param relativeYPosition
	 */
	protected void setPositionOnContainer(
		final int relativeXPosition,
		final int relativeYPosition
	) {
		
		//Calls method of the base class.
		super.setPositionOnContainer(relativeXPosition, relativeYPosition);
		
		//Enumerates the content position of this vertical stack.
		switch (getContentPosition()) {
			case LeftTop:
			case LeftBottom:
			case Left:	
				final int x = getContentXPosition();
				int y = getContentYPosition();
				for (Widget<?, ?> w: getRefShownWidgets()) {
					w.setPositionOnContainer(x, y);
					y += w.getHeight() + getActiveElementMargin();
				}
				break;
			case Top:
			case Center:
			case Bottom:
				final int maxRectangleWidth2 = getRefShownWidgets().getMaxInt(r -> r.getWidth());
				final int x2 = getContentXPosition();
				int y2 = getContentYPosition();
				for (Widget<?, ?> w: getRefShownWidgets()) {			
					w.setPositionOnContainer((int)(x2 + 0.5 * (maxRectangleWidth2 - w.getWidth())), y2);
					y2 += w.getHeight() + getActiveElementMargin();
				}
				break;
			case RightTop:
			case Right:
			case RightBottom:
				final int maxRectangleWidth3 = getRefShownWidgets().getMaxInt(r -> r.getWidth());
				final int x3 = getContentXPosition();
				int y3 = getContentYPosition();
				for (Widget<?, ?> w: getRefShownWidgets()) {
					w.setPositionOnContainer(x3 + maxRectangleWidth3 - w.getWidth(), y3);
					y3 += w.getHeight() + getActiveElementMargin();
				}
		}
	}
}
