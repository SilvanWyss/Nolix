//package declaration
package ch.nolix.element.widget;

import ch.nolix.element.gui.base.Widget;

//class
/**
 * @author Silvan Wyss
 * @date 2016-01-01
 * @lines 50
 */
public final class HorizontalLine extends Line<HorizontalLine> {
	
	//constant
	public static final String TYPE_NAME = "HorizontalLine";
	
	//attribute
	private boolean isAskedForLength;
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	public int getLength() {
		
		if (!belongsToWidget() || isAskedForLength) {
			return DEFAULT_LENGTH;
		}
		
		isAskedForLength = true;
		
		final var length =
		getParentWidget().getRefPaintableWidgets().getMaxIntOrDefaultValue(Widget::getWidth, DEFAULT_LENGTH);
		
		isAskedForLength = false;
		
		return length;
	}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected int getHeightWhenExpanded() {
		return getThickness();
	}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected int getWidthWhenExpanded() {
		return getLength();
	}
}
