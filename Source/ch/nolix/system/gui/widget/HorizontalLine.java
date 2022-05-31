//package declaration
package ch.nolix.system.gui.widget;

import ch.nolix.systemapi.guiapi.widgetguiapi.IWidget;

//class
/**
 * @author Silvan Wyss
 * @date 2016-01-01
 */
public final class HorizontalLine extends Line<HorizontalLine> {
	
	//attribute
	private boolean isAskedForLength;
	
	//constructor
	/**
	 * Creates a new {@link HorizontalLine}
	 */
	public HorizontalLine() {
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
		getParentWidget().getRefWidgetsForPainting().getMaxIntOrDefaultValue(IWidget::getWidth, DEFAULT_LENGTH);
		
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
