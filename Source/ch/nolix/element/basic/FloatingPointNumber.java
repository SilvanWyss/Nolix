//package declaration
package ch.nolix.element.basic;

//own imports
import ch.nolix.common.container.List;
import ch.nolix.common.helper.DoubleHelper;
import ch.nolix.common.specification.Specification;

//class
/**
 * A floating point number is not mutable.
 * 
 * @author Silvan Wyss
 * @month 2017-02
 * @lines 40
 */
public class FloatingPointNumber extends Element {

	//attribute
	private final double value;
	
	//constructor
	/**
	 * Creates new floating point number with the given value.
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
	public final List<Specification> getAttributes() {
		return new List<Specification>(new Specification(DoubleHelper.toString(getValue())));
	}
	
	//method
	/**
	 * @return the value of this floating point number.
	 */
	public final double getValue() {
		return value;
	}
}
