//package declaration
package ch.nolix.system.gui.containerwidget;

//own imports
import ch.nolix.systemapi.guiapi.widgetguiapi.IWidget;

//class
/**
 * @author Silvan Wyss
 * @date 2016-01-01
 */
public final class VerticalStack extends Stack<VerticalStack> {
	
	//constructor
	/**
	 * Creates a new {@link VerticalStack}.
	 */
	public VerticalStack() {
		reset();
	}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected int getNaturalContentAreaHeight() {
		
		final var childWidgets = getChildWidgets();
		
		var contentHeight = childWidgets.getSumByInt(IWidget::getHeight);
			
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
	protected int getNaturalContentAreaWidth() {
		return getChildWidgets().getMaxIntOrZero(IWidget::getWidth);
	}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void recalculateBorderWidget() {
		
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
				
				final var contentAreaWidth = getNaturalContentAreaWidth();
				var y2 = 0;
				for (final var w : getChildWidgets()) {
					w.setPositionOnParent((contentAreaWidth - w.getWidth()) / 2, y2);
					y2 += w.getHeight() + getElementMargin();
				}
				
				break;
			case TOP_RIGHT:
			case RIGHT:
			case BOTTOM_RIGHT:
				
				final var contentAreaWidth2 = getNaturalContentAreaWidth();
				var y3 = 0;
				for (final var w : getChildWidgets()) {
					w.setPositionOnParent(contentAreaWidth2 - w.getWidth(), y3);
					y3 += w.getHeight() + getElementMargin();
				}
				
				break;
		}
	}
}
