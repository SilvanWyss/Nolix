//package declaration
package ch.nolix.element.widgets;

import ch.nolix.element.GUI.Widget;

//class
/**
 * @author Silvan Wyss
 * @month 2015-12
 * @lines 30
 */
public final class HorizontalLine extends Line<HorizontalLine> {

	//type name
	public static final String TYPE_NAME = "HorizontalLine";
	
	//constructor
	/**
	 * Creates a new horizontal line.
	 */
	public HorizontalLine() {
		resetAndApplyDefaultConfiguration();
	}
	
	//method
	/**
	 * @return the height of this line when it is not collapsed.
	 */
	@Override
	public final int getHeightWhenNotCollapsed() {
		return getThickness();
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
			
		return getParent().getRef().as(Widget.class).getWidth();
	}
	
	//method
	/**
	 * @return the width of this line when it is not collapsed.
	 */
	@Override
	public final int getWidthWhenNotCollapsed() {
		return getLength();
	}
}
