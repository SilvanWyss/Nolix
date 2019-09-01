//package declaration
package ch.nolix.element.core;

import ch.nolix.common.commonTypeHelpers.DoubleHelper;
import ch.nolix.common.containers.List;
import ch.nolix.common.node.Node;
import ch.nolix.element.base.Element;

//class
/**
 * A floating point number is not mutable.
 * 
 * @author Silvan Wyss
 * @month 2017-02
 * @lines 40
 */
public class FloatingPointNumber extends Element<FloatingPointNumber> {

	//attribute
	private final double value;
	
	//constructor
	/**
	 * Creates a new floating point number with the given value.
	 * 
	 * @param value
	 */
	public FloatingPointNumber(final double value) {
		this.value = value;
	}

	//method
	/**
	 * @return the attributes of this floating point number.
	 */
	@Override
	public final List<Node> getAttributes() {
		return new List<>(Node.fromString(DoubleHelper.toString(getValue())));
	}
	
	//method
	/**
	 * @return the value of this floating point number.
	 */
	public final double getValue() {
		return value;
	}
}
