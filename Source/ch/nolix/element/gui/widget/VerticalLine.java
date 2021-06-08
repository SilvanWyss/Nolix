//package declaration
package ch.nolix.element.gui.widget;

//class
/**
 * @author Silvan Wyss
 * @date 2016-01-01
 * @lines 60
 */
public final class VerticalLine extends Line<VerticalLine> {
	
	//attribute
	private boolean isAskedForLength;
	
	//constructor
	/**
	 * Creates a new {@link HorizontalLine}
	 */
	public VerticalLine() {
		reset();
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
		getParentWidget().getRefWidgetsForPainting().getMaxIntOrDefaultValue(Widget::getHeight, DEFAULT_LENGTH);
		
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
