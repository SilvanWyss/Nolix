//package declaration
package ch.nolix.element.basic;

//own imports
import ch.nolix.core.container.List;
import ch.nolix.core.helper.DoubleHelper;
import ch.nolix.core.specification.StandardSpecification;

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
	public final List<StandardSpecification> getAttributes() {
		return new List<StandardSpecification>(new StandardSpecification(DoubleHelper.toString(getValue())));
	}
	
	//method
	/**
	 * @return the value of this floating point number.
	 */
	public final double getValue() {
		return value;
	}
}
