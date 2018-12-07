//package declaration
package ch.nolix.element.GUI;

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
		resetConfiguration();
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
	 * @return the width of this line when it is not collapsed.
	 */
	@Override
	public int getWidthWhenNotCollapsed() {
		return getThickness();
	}
}
