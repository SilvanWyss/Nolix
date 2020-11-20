//package declaration
package ch.nolix.element.widget;

//own imports
import ch.nolix.element.GUI.Widget;
import ch.nolix.element.containerWidget.Stack;

//class
/**
 * @author Silvan Wyss
 * @date 2016-01-01
 * @lines 90
 */
public final class VerticalStack extends Stack<VerticalStack> {
	
	//constant
	public static final String TYPE_NAME = "VerticalStack";
	
	//constructor
	/**
	 * Creates a new {@link VerticalStack}.
	 */
	public VerticalStack() {
		resetAndApplyDefaultConfiguration();
	}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected int getContentAreaHeight() {
		
		var contentHeight = getChildWidgets().getSumByInt(Widget::getHeight);
			
		//Handles the case that the current VerticalStack is not empty.
		if (containsAny()) {
			contentHeight += (getChildWidgets().getElementCount() - 1) * getElementMargin();
		}
		
		return contentHeight;
	}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected int getContentAreaWidth() {
		return (isEmpty() ? 0 : getChildWidgets().getMaxInt(Widget::getWidth));
	}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void recalculateSelfStage2() {
		
		//Enumerates the content position of the current VerticalStack.
		switch (getContentPosition()) {
			case LeftTop:
			case LeftBottom:
			case Left:
				
				var y1 = 0;
				for (final var w : getChildWidgets()) {
					w.setPositionOnParent(0, y1);
					y1 += w.getHeight() + getElementMargin();
				}
				
				break;
			case Top:
			case Center:
			case Bottom:
				
				final var contentAreaWidth2 = getContentAreaWidth();
				var y2 = 0;
				for (final var w : getChildWidgets()) {
					w.setPositionOnParent((contentAreaWidth2 - w.getWidth()) / 2, y2);
					y2 += w.getHeight() + getElementMargin();
				}
				
				break;
			case RightTop:
			case Right:
			case RightBottom:
				
				final var contentAreaWidth3 = getContentAreaWidth();
				var y3 = 0;
				for (final var w : getChildWidgets()) {
					w.setPositionOnParent(contentAreaWidth3 - w.getWidth(), y3);
					y3 += w.getHeight() + getElementMargin();
				}
				
				break;
		}
	}
}
