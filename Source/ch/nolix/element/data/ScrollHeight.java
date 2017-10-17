//package declaration
package ch.nolix.element.data;

//own imports
import ch.nolix.core.validator2.Validator;
import ch.nolix.element.core.PositiveInteger;

//class
/**
 * A scroll height is not mutable.
 * 
 * @author Silvan Wyss
 * @month 2017-10
 * @lines 50
 */
public final class ScrollHeight extends PositiveInteger {
	
	//constant
	public static final String TYPE_NAME = "ScrollHeight";
	
	//limit
	public static final int MIN_VALUE = 10;
	
	//default value
	public static final int DEFAULT_VALUE = 100;
		
	//constructor
	/**
	 * Creates new scroll height with a default value.
	 */
	public ScrollHeight() {
		
		//Calls other constructor.
		this(DEFAULT_VALUE);
	}
	
	//constructor
	/**
	 * Creates new scroll height with the given value.
	 * 
	 * @param value
	 * @throws SmallerArgumentException
	 * if the given value is smaller than the minimal value.
	 */
	public ScrollHeight(final int value) {
		
		//Calls constructor of the base class.
		super(value);
		
		//Checks if the given value is not smaller than the minimal value.
		Validator.suppose(value).thatIsNamed("value").isNotSmallerThan(MIN_VALUE);
	}
}
