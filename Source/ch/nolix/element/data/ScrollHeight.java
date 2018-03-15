//package declaration
package ch.nolix.element.data;

//own imports
import ch.nolix.core.constants.VariableNameCatalogue;
import ch.nolix.core.specification.Specification;
import ch.nolix.element.core.PositiveInteger;
import ch.nolix.primitive.validator2.Validator;

//class
/**
 * A scroll height is not mutable.
 * 
 * @author Silvan Wyss
 * @month 2017-10
 * @lines 70
 */
public final class ScrollHeight extends PositiveInteger {
	
	//constant
	public static final String TYPE_NAME = "ScrollHeight";
	
	//limit value
	public static final int MIN_VALUE = 10;
	
	//default value
	public static final int DEFAULT_VALUE = 100;
	
	//static method
	/**
	 * @param specificaiton
	 * @return a new scroll height from the given specification.
	 * @throws InvalidArgumentException
	 * if the given specification is not valid.
	 */
	public static ScrollHeight createFromSpecification(
		final Specification specification
	) {
		return new ScrollHeight(specification.getOneAttributeAsInt());
	}
		
	//constructor
	/**
	 * Creates a new scroll height with a default value.
	 */
	public ScrollHeight() {
		
		//Calls other constructor.
		this(DEFAULT_VALUE);
	}
	
	//constructor
	/**
	 * Creates a new scroll height with the given value.
	 * 
	 * @param value
	 * @throws SmallerArgumentException
	 * if the given value is smaller than the minimal value.
	 */
	public ScrollHeight(final int value) {
		
		//Calls constructor of the base class.
		super(value);
		
		//Checks if the given value is not smaller than the minimal value.
		Validator
		.suppose(value)
		.thatIsNamed(VariableNameCatalogue.VALUE)
		.isNotSmallerThan(MIN_VALUE);
	}
}
