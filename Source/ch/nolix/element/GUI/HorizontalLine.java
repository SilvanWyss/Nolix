//package declaration
package ch.nolix.element.GUI;

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
	 * @return the width of this line when it is not collapsed.
	 */
	@Override
	public final int getWidthWhenNotCollapsed() {
		return getLength();
	}
}
