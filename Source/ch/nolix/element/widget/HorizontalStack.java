//package declaration
package ch.nolix.element.widget;

//own imports
import ch.nolix.element.GUI.Widget;
import ch.nolix.element.containerWidget.Stack;

//class
/**
 * @author Silvan Wyss
 * @date 2016-01-01
 * @lines 100
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
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected int getContentAreaHeight() {
		return getChildWidgets().getMaxIntOrZero(Widget::getHeight);
	}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected int getContentAreaWidth() {
		
		final var childWidget = getChildWidgets();
		
		var contentWidth = childWidget.getSumByInt(Widget::getWidth);
		
		//Handles the case that the current horizontal stack is not empty.
		if (containsAny()) {
			contentWidth += (childWidget.getElementCount() - 1) * getElementMargin();
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
