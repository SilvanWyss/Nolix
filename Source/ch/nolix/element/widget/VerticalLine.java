//package declaration
package ch.nolix.element.widget;

import ch.nolix.element.gui.Widget;

//class
/**
 * @author Silvan Wyss
 * @month 2015-12
 * @lines 60
 */
public final class VerticalLine extends Line<VerticalLine> {
	
	//constant
	public static final String TYPE_NAME = "VerticalLine";
	
	//attribute
	private boolean isAskedForLength;
	
	//constructor
	/**
	 * Creates a new {@link VerticalLine}.
	 */
	public VerticalLine() {
		resetAndApplyDefaultConfiguration();
	}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	public int getHeightWhenNotCollapsed() {
		return getLength();
	}
	
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
		getParentWidget().getRefPaintableWidgets().getMaxIntOrDefaultValue(Widget::getHeight, DEFAULT_LENGTH);
		
		isAskedForLength = false;
		
		return length;
	}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected int getWidthWhenExpanded() {
		return getThickness();
	}
}
