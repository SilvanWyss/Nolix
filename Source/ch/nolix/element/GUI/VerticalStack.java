//package declaration
package ch.nolix.element.GUI;

//class
/**
 * @author Silvan Wyss
 * @month 2015-12
 * @lines 130
 */
public final class VerticalStack extends Stack<VerticalStack> {
	
	//constant
	public static final String TYPE_NAME = "VerticalStack";

	//constructor
	/**
	 * Creates a new {@link VerticalStack}.
	 */
	public VerticalStack() {
		
		//Calls other constructor.
		this(true);
	}
	
	//constructor
	/**
	 * Creates a new {@link VerticalStack}.
	 */
	public VerticalStack(final boolean applyUsableConfiguration) {
		
		reset();
		approveProperties();
		
		if (applyUsableConfiguration) {
			applyUsableConfiguration();
		}
	}
	
	//constructor
	/**
	 * Creates a new  {@link VerticalStack} with the given widgets
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
	 * {@inheritDoc}
	 */
	protected int getContentAreaHeight() {
		
		int contentHeight = getRefWidgets().getSumByInt(w -> w.getHeight());
				
		//Handles the case that the current vertical stack is not empty.
		if (containsAny()) {
			contentHeight += (getRefWidgets().getElementCount() - 1) * getActiveElementMargin();
		}
		
		return contentHeight;
	}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	protected int getContentAreaWidth() {
		
		//Handles the case that the current vertical stack is empty.
		if (isEmpty()) {
			return 0;
		}
		
		//Handles the case that the current vertical stack is not empty.
		return getRefWidgets().getMaxInt(w -> w.getWidth());
	}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	protected void setPositionOnParent(
		final int xPositionOnParent,
		final int yPositionOnParent
	) {
		
		//Calls method of the base class.
		super.setPositionOnParent(xPositionOnParent, yPositionOnParent);
		
		//Enumerates the content position of the current vertical stack.
		switch (getContentPosition()) {
			case LeftTop:
			case LeftBottom:
			case Left:
				
				var y1 = 0;
				for (final var w : getRefWidgets()) {
					w.setPositionOnParent(0, y1);
					y1 += w.getHeight() + getActiveElementMargin();
				}
				
				break;
			case Top:
			case Center:
			case Bottom:
				
				final var contentAreaWidth2 = getContentAreaWidth();
				var y2 = 0;
				for (final var w : getRefWidgets()) {			
					w.setPositionOnParent((contentAreaWidth2 - w.getWidth()) / 2, y2);
					y2 += w.getHeight() + getActiveElementMargin();
				}
				
				break;
			case RightTop:
			case Right:
			case RightBottom:
				
				final var contentAreaWidth3 = getContentAreaWidth();
				var y3 = 0;
				for (final var w : getRefWidgets()) {
					w.setPositionOnParent(contentAreaWidth3 - w.getWidth(), y3);
					y3 += w.getHeight() + getActiveElementMargin();
				}
				
				break;
		}
	}
}
