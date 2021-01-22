//package declaration
package ch.nolix.element.widget;

//own import
import ch.nolix.element.gui.Widget;

//class
/**
 * @author Silvan Wyss
 * @date 2016-01-01
 * @lines 50
 */
public final class VerticalLine extends Line<VerticalLine> {
	
	//constant
	public static final String TYPE_NAME = "VerticalLine";
	
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
		getParentWidget().getRefPaintableWidgets().getMaxIntOrDefaultValue(Widget::getHeight, DEFAULT_LENGTH);
		
		isAskedForLength = false;
		
		return length;
	}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected int getHeightWhenExpanded() {
		return getLength();
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
