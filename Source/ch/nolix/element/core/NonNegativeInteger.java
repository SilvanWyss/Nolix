//package declaration
package ch.nolix.element.core;

//own imports
import ch.nolix.core.constants.VariableNameCatalogue;
import ch.nolix.core.documentNode.DocumentNodeoid;
import ch.nolix.core.invalidArgumentExceptions.NegativeArgumentException;
import ch.nolix.core.validator.Validator;

//class
/**
 * A {@link NonNegativeInteger} is an {@link Integer} that is not mutable.
 * A {@link NonNegativeInteger} is not mutable.
 * 
 * @author Silvan Wyss
 * @month 2015-12
 * @lines 60
 */
public class NonNegativeInteger extends Integer {
	
	//constant
	public static final String TYPE_NAME = "NonNegativeInteger";
	
	//default value
	public static final int DEFAULT_VALUE = 0;
	
	//constructor
	/**
	 * Creates a new {@link NonNegativeInteger}
	 * with the {@link NonNegativeInteger#DEFAULT_VALUE}.
	 */
	public NonNegativeInteger() {
		
		//Calls other constructor.
		this(DEFAULT_VALUE);
	}
	
	//constructor
	/**
	 * Creates a new {@link NonNegativeInteger} with the given value.
	 * 
	 * @param value
	 * @throws NegativeArgumentException if the given value is negative.
	 */
	public NonNegativeInteger(final int value) {
		
		//Calls constructor of the base class.
		super(value);
		
		//Checks if the given value is not negative.
		Validator
		.suppose(value)
		.thatIsNamed(VariableNameCatalogue.VALUE)
		.isNotNegative();
	}

	//static method
	/**
	 * @param specification
	 * @return a new {@link NonNegativeInteger} from the given specification.
	 * @throws InvalidArgumentException if the given specification is not valid.
	 */
	public static NonNegativeInteger createFromSpecification(
		final DocumentNodeoid specification
	) {
		return new NonNegativeInteger(specification.getOneAttributeAsInt());
	}
}
