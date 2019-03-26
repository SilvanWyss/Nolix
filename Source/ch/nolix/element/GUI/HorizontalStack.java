//package declaration
package ch.nolix.element.GUI;

//class
/**
 * @author Silvan Wyss
 * @month 2015-12
 * @lines 130
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
		applyDefaultConfiguration();
	}
	
	//constructor
	/**
	 * Creates a new {@link HorizontalStack} with the given widgets.
	 * 
	 * @param widgets
	 * @throws NullArgumentException if one of the given widgets is null.
	 */
	public <W extends Widget<?, ?>> HorizontalStack(final Iterable<W> widgets) {
		
		//Calls other constructor.
		this();
		
		addWidgets(widgets);
	}
	
	//constructor
	/**
	 * Creates a new {@link HorizontalStack} with the given widgets.
	 * 
	 * @param widgets
	 * @throws NullArgumentException if one of the given widgets is null.
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
	@Override
	protected final int getContentAreaHeight() {
		
		//Handles the case that the current horizontal stack is empty.
		if (isEmpty()) {
			return 0;
		}
		
		//Handles the case that the current horizontal stack is not empty.
		return getChildWidgets().getMaxByInt(r -> r.getHeight());
	}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected final int getContentAreaWidth() {
		
		int contentWidth = getChildWidgets().getSumByInt(w -> w.getWidth());
		
		//Handles the case that the current horizontal stack is not empty.
		if (containsAny()) {
			contentWidth += (getChildWidgets().getSize() - 1) * getActiveElementMargin();
		}
		
		return contentWidth;
	}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
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
				
				var x1 = 0;
				for (final var w : getChildWidgets()) {
					w.setPositionOnParent(x1, 0);
					x1 += w.getWidth() + getActiveElementMargin();
				}
				
				break;
			case Left:
			case Center:
			case Right:
				
				final var contentAreaHeight2 = getContentAreaHeight();
				var x2 = 0;
				for (final var w: getChildWidgets()) {
					w.setPositionOnParent(x2, (contentAreaHeight2 - w.getHeight()) / 2);
					x2 += w.getWidth() + getActiveElementMargin();
				}
				
				break;
			case LeftBottom:
			case Bottom:
			case RightBottom:
				
				final var contentAreaHeight3 = getContentAreaHeight();
				var x3 = 0;
				for (final var w : getChildWidgets()) {
					w.setPositionOnParent(x3, contentAreaHeight3 - w.getHeight());
					x3 += w.getWidth() + getActiveElementMargin();
				}
				
				break;
		}
	}
}
