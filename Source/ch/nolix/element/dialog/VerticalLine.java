/*
 * file:	VerticalLine.java
 * author:	Silvan Wyss
 * month:	2015
 * lines:	50 
 */

//package declaration
package ch.nolix.element.dialog;

//own imports
import ch.nolix.common.container.List;
import ch.nolix.common.specification.Specification;

//class
public final class VerticalLine extends Line<VerticalLine> {
	
	//constant
	public final static String SIMPLE_CLASS_NAME = "VerticalLine";

	//constructor
	/**
	 * Creates new vertical line that has default attributes.
	 */
	public VerticalLine() {		
		resetConfiguration();
	}
	
	//constructor
	/**
	 * Creates new vertical that has the given attributes.
	 * @param attributes
	 * @throws Exception if the given attributes are not valid
	 */
	public VerticalLine(List<Specification> attributes) {
		
		//Calls other constructor.
		this();
		
		addOrChangeAttributes(attributes);
	}
	
	//method
	/**
	 * @return the current height of this rectangle if it is not collapsed
	 */
	protected final int getHeightWhenNotCollapsed() {
		return getLength();
	}
	
	//method
	/**
	 * @return the current width of this rectangle if it is not collapsed
	 */
	protected final int getWidthWhenNotCollapsed() {
		return getThickness();
	}
}
