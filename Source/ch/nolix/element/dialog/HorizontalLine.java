/*
 * file:	HorizontalLine.java
 * author:	Silvan Wyss
 * month:	2015
 * lines:	70 
 */

//package declaration
package ch.nolix.element.dialog;

//own imports
import ch.nolix.common.container.List;
import ch.nolix.common.specification.Specification;

//class
public final class HorizontalLine extends Line<HorizontalLine> {

	//constants
	public static final String SIMPLE_CLASS_NAME = "HorizontalLine";
	public static final String NONE = "NoHorizontalLine";
	
	//constructor
	/**
	 * Creates new horizontal line that has default attributes.
	 */
	public HorizontalLine() {		
		resetConfiguration();
	}
	
	//constructor
	/**
	 * Creates new horizontal line that has the given attributes.
	 * @param attributes
	 * @throws Exception if the given attributes are not valid
	 */
	public HorizontalLine(List<Specification> attributes) {
		
		//Calls other constructor.
		this();
		
		addOrChangeAttributes(attributes);
	}
	
	//method
	/**
	 * @return the current height of this rectangle if it is not collapsed
	 */
	protected final int getHeightWhenNotCollapsed() {
		return getThickness();
	}
	
	//method
	/**
	 * @return the current width of this rectangle if it is not collapsed
	 */
	protected final int getWidthWhenNotCollapsed() {
		return getLength();
	}
}
