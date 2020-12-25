//package declaration
package ch.nolix.element.containerwidget;

//own import
import ch.nolix.element.gui.Widget;

//class
/**
 * @author Silvan Wyss
 * @date 2016-01-01
 * @lines 90
 */
public final class VerticalStack extends Stack<VerticalStack> {
	
	//constant
	public static final String TYPE_NAME = "VerticalStack";
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected int getContentAreaHeight() {
		
		final var childWidgets = getChildWidgets();
		
		var contentHeight = childWidgets.getSumByInt(Widget::getHeight);
			
		//Handles the case that the current VerticalStack is not empty.
		if (containsAny()) {
			contentHeight += (childWidgets.getElementCount() - 1) * getElementMargin();
		}
		
		return contentHeight;
	}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected int getContentAreaWidth() {
		return getChildWidgets().getMaxIntOrZero(Widget::getWidth);
	}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void recalculateSelfStage2() {
		
		//Enumerates the content position of the current VerticalStack.
		switch (getContentPosition()) {
			case TOP_LEFT:
			case BOTTOM_LEFT:
			case LEFT:
				
				var y1 = 0;
				for (final var w : getChildWidgets()) {
					w.setPositionOnParent(0, y1);
					y1 += w.getHeight() + getElementMargin();
				}
				
				break;
			case TOP:
			case CENTER:
			case BOTTOM:
				
				final var contentAreaWidth = getContentAreaWidth();
				var y2 = 0;
				for (final var w : getChildWidgets()) {
					w.setPositionOnParent((contentAreaWidth - w.getWidth()) / 2, y2);
					y2 += w.getHeight() + getElementMargin();
				}
				
				break;
			case TOP_RIGHT:
			case RIGHT:
			case BOTTOM_RIGHT:
				
				final var contentAreaWidth2 = getContentAreaWidth();
				var y3 = 0;
				for (final var w : getChildWidgets()) {
					w.setPositionOnParent(contentAreaWidth2 - w.getWidth(), y3);
					y3 += w.getHeight() + getElementMargin();
				}
				
				break;
		}
	}
}
