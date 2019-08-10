//package declaration
package ch.nolix.element.widgets;

import ch.nolix.element.GUI_API.Widget;

//class
/**
 * @author Silvan Wyss
 * @month 2015-12
 * @lines 30
 */
public final class VerticalLine extends Line<VerticalLine> {
	
	//type name
	public static final String TYPE_NAME = "VerticalLine";

	//constructor
	/**
	 * Creates a new vertical line.
	 */
	public VerticalLine() {
		resetAndApplyDefaultConfiguration();
	}
	
	//method
	/**
	 * @return the height of this line when it is not collapsed.
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
		
		if (!belongsToParent()) {
			return DEFAULT_LENGTH;
		}
		
		//TODO: Ask a safer condition.
		for (final var st : Thread.currentThread().getStackTrace()) {
			if (st.getFileName() == Line.TYPE_NAME && st.getMethodName() == "getLength") {
				return DEFAULT_LENGTH;
			}
		}
			
		return getParent().getRef().as(Widget.class).getHeight();
	}
	
	//method
	/**
	 * @return the width of this line when it is not collapsed.
	 */
	@Override
	public int getWidthWhenNotCollapsed() {
		return getThickness();
	}
}
