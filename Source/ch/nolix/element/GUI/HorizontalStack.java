//package declaration
package ch.nolix.element.GUI;

//class
/**
 * @author Silvan Wyss
 * @month 2015-12
 * @lines 120
 */
public final class HorizontalStack extends Stack<HorizontalStack> {
	
	//constant
	public static final String TYPE_NAME = "HorizontalStack";		
	
	//constructor
	/**
	 * Creates a new {@link HorizontalStack}.
	 */
	public HorizontalStack() {
		reset();
		approveProperties();
		applyUsableConfiguration();
	}
	
	//constructor
	/**
	 * Creates a new {@link HorizontalStack} with the given widgets.
	 * 
	 * @param widgets
	 * @throws NullArgumentException if one of the given widgets is null.
	 * @throws InvalidArgumentException
	 * if one of the given widgets belongs to another GUI than the current {@link HorizontalStack}.
	 */
	public HorizontalStack(final Widget<?, ?>... widgets) {
		
		//Calls other constructor.
		this();
		
		addWidget(widgets);
	}

	//method
	/**
	 * {@inheritDoc}
	 */
	protected final int getContentAreaHeight() {
		
		//Handles the case that the current horizontal stack is empty.
		if (isEmpty()) {
			return 0;
		}
		
		//Handles the case that the current horizontal stack is not empty.
		return getRefWidgets().getMaxInt(r -> r.getHeight());
	}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	protected final int getContentAreaWidth() {
		
		int contentWidth = getRefWidgets().getSumByInt(w -> w.getWidth());
		
		//Handles the case that the current horizontal stack is not empty.
		if (containsAny()) {
			contentWidth += (getRefWidgets().getElementCount() - 1) * getActiveElementMargin();
		}
		
		return contentWidth;
	}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	protected final void setPositionOnParent(
		final int xPositionOnParent,
		final int yPositionOnParent
	) {
		
		//Calls method of the base class.
		super.setPositionOnParent(xPositionOnParent, yPositionOnParent);
		
		//Enumerates the content position of the current horizontal stack.
		switch (getContentPosition()) {
			case LeftTop:
			case Top:
			case RightTop:
				
				int x1 = 0;
				final int y1 = 0;
				for (final var w : getRefWidgets()) {
					w.setPositionOnParent(x1, y1);
					x1 += w.getWidth() + getActiveElementMargin();
				}
				
				break;
			case Left:
			case Center:
			case Right:
				
				final int contentAreaHeight2 = getContentAreaHeight();
				int x2 = 0;
				final int y2 = 0;
				for (final var w: getRefWidgets()) {
					w.setPositionOnParent(x2, y2 + (contentAreaHeight2 - w.getHeight()) / 2);
					x2 += w.getWidth() + getActiveElementMargin();
				}
				
				break;
			case LeftBottom:
			case Bottom:
			case RightBottom:
				
				final int contentAreaHeight3 = getContentAreaHeight();
				int x3 = 0;
				final int y3 = 0;
				for (final var w : getRefWidgets()) {
					w.setPositionOnParent(x3, y3 + contentAreaHeight3 - w.getHeight());
					x3 += w.getWidth() + getActiveElementMargin();
				}
				
				break;
		}
	}
}
