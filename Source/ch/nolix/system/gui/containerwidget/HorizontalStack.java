//package declaration
package ch.nolix.system.gui.containerwidget;

//own imports
import ch.nolix.systemapi.guiapi.widgetguiapi.IWidget;

//class
/**
 * @author Silvan Wyss
 * @date 2016-01-01
 */
public final class HorizontalStack extends Stack<HorizontalStack> {
	
	//constructor
	/**
	 * Creates a new {@link HorizontalStack}.
	 */
	public HorizontalStack() {
		reset();
	}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected int getNaturalContentAreaHeight() {
		return getChildWidgets().getMaxIntOrZero(IWidget::getHeight);
	}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected int getNaturalContentAreaWidth() {
		
		final var childWidget = getChildWidgets();
		
		var contentWidth = childWidget.getSumByInt(IWidget::getWidth);
		
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
	protected void recalculateBorderWidget() {
				
		//Enumerates the content position of the current horizontal stack.
		switch (getContentPosition()) {
			case TOP_LEFT:
			case TOP:
			case TOP_RIGHT:
				
				var x1 = 0;
				for (final var w : getChildWidgets()) {
					w.setPositionOnParent(x1, 0);
					x1 += w.getWidth() + getElementMargin();
				}
				
				break;
			case LEFT:
			case CENTER:
			case RIGHT:
				
				final var contentAreaHeight = getNaturalContentAreaHeight();
				var x2 = 0;
				for (final var w: getChildWidgets()) {
					w.setPositionOnParent(x2, (contentAreaHeight - w.getHeight()) / 2);
					x2 += w.getWidth() + getElementMargin();
				}
				
				break;
			case BOTTOM_LEFT:
			case BOTTOM:
			case BOTTOM_RIGHT:
				
				final var contentAreaHeight2 = getNaturalContentAreaHeight();
				var x3 = 0;
				for (final var w : getChildWidgets()) {
					w.setPositionOnParent(x3, contentAreaHeight2 - w.getHeight());
					x3 += w.getWidth() + getElementMargin();
				}
				
				break;
		}
	}
}
