//package declaration
package ch.nolix.element.FPNData;

//own imports
import ch.nolix.core.specification.Specification;
import ch.nolix.element.core.PositiveFloatingPointNumber;

//class
/**
 * A length is not mutable.
 * 
 * @author Silvan Wyss
 * @month 2017-11
 * @lines 40
 */
public final class Length extends PositiveFloatingPointNumber {
	
	//constant
	public static final String TYPE_NAME = "Length";
	
	//method
	/**
	 * @param specification
	 * @return a new length from the given specification.
	 */
	public static Length createFromSpecification(final Specification specification) {
		return new Length(specification.getOneAttributeAsInt());
	}

	//constructor
	/**
	 * Creates new length with a default value.
	 */
	public Length() {}
	
	//constructor
	/**
	 * Creates new length with the given value.
	 * 
	 * @param value
	 * @throws NonPositiveArgumentException if the given value is not positive.
	 */
	public Length(final double value) {
		
		//Calls constructor of the base class.
		super(value);
	}
}
