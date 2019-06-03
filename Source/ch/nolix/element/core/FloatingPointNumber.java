//package declaration
package ch.nolix.element.core;

//own imports
import ch.nolix.core.container.List;
import ch.nolix.core.documentNode.DocumentNode;
import ch.nolix.core.helper.DoubleHelper;

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
	public final List<DocumentNode> getAttributes() {
		return new List<DocumentNode>(DocumentNode.createFromString(DoubleHelper.toString(getValue())));
	}
	
	//method
	/**
	 * @return the value of this floating point number.
	 */
	public final double getValue() {
		return value;
	}
}
