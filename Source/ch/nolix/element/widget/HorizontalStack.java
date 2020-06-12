//package declaration
package ch.nolix.element.widget;

//own imports
import ch.nolix.element.GUI.Widget;
import ch.nolix.element.containerWidget.Stack;

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
		resetAndApplyDefaultConfiguration();
	}
	
	//constructor
	/**
	 * Creates a new {@link HorizontalStack} with the given widgets.
	 * 
	 * @param widgets
	 * @throws ArgumentIsNullException if one of the given widgets is null.
	 */
	public <W extends Widget<?, ?>> HorizontalStack(final Iterable<W> widgets) {
		
		resetAndApplyDefaultConfiguration();
		
		addWidgets(widgets);
	}
	
	//constructor
	/**
	 * Creates a new {@link HorizontalStack} with the given widgets.
	 * 
	 * @param widgets
	 * @throws ArgumentIsNullException if one of the given widgets is null.
	 */
	public HorizontalStack(final Widget<?, ?>... widgets) {
		
		resetAndApplyDefaultConfiguration();
		
		addWidget(widgets);
	}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected int getContentAreaHeight() {
		
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
	protected int getContentAreaWidth() {
		
		int contentWidth = getChildWidgets().getSumByInt(w -> w.getWidth());
		
		//Handles the case that the current horizontal stack is not empty.
		if (containsAny()) {
			contentWidth += (getChildWidgets().getSize() - 1) * getElementMargin();
		}
		
		return contentWidth;
	}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void recalculateSelfStage2() {
				
		//Enumerates the content position of the current horizontal stack.
		switch (getContentPosition()) {
			case LeftTop:
			case Top:
			case RightTop:
				
				var x1 = 0;
				for (final var w : getChildWidgets()) {
					w.setPositionOnParent(x1, 0);
					x1 += w.getWidth() + getElementMargin();
				}
				
				break;
			case Left:
			case Center:
			case Right:
				
				final var contentAreaHeight2 = getContentAreaHeight();
				var x2 = 0;
				for (final var w: getChildWidgets()) {
					w.setPositionOnParent(x2, (contentAreaHeight2 - w.getHeight()) / 2);
					x2 += w.getWidth() + getElementMargin();
				}
				
				break;
			case LeftBottom:
			case Bottom:
			case RightBottom:
				
				final var contentAreaHeight3 = getContentAreaHeight();
				var x3 = 0;
				for (final var w : getChildWidgets()) {
					w.setPositionOnParent(x3, contentAreaHeight3 - w.getHeight());
					x3 += w.getWidth() + getElementMargin();
				}
				
				break;
		}
	}
}
