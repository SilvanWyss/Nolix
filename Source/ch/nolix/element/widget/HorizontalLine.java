//package declaration
package ch.nolix.element.widget;

//own import
import ch.nolix.element.GUI.Widget;

//class
/**
 * @author Silvan Wyss
 * @month 2015-12
 * @lines 60
 */
public final class HorizontalLine extends Line<HorizontalLine> {
	
	//constant
	public static final String TYPE_NAME = "HorizontalLine";
	
	//attribute
	private boolean isAskedForLength = false;
	
	//constructor
	/**
	 * Creates a new {@link HorizontalLine}.
	 */
	public HorizontalLine() {
		resetAndApplyDefaultConfiguration();
	}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	public int getHeightWhenNotCollapsed() {
		return getThickness();
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
		getParentWidget().getRefPaintableWidgets().getMaxIntOrDefaultValue(Widget::getWidth, DEFAULT_LENGTH);
		
		isAskedForLength = false;
		
		return length;
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
