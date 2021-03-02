//package declaration
package ch.nolix.common.errorcontrol.invalidargumentexception;

//Java imports
import java.math.BigDecimal;
import java.math.BigInteger;

//class
/**
 * A {@link NegativeArgumentException} is a {@link InvalidArgumentException}
 * that is supposed to be thrown when a given argument is undesirably negative.
 * 
 * @author Silvan Wyss
 * @month 2016-02
 * @lines 130
 */
@SuppressWarnings("serial")
public final class NegativeArgumentException extends InvalidArgumentException {
	
	//constant
	private static final String ERROR_PREDICATE = "is negative";
	
	//constructor
	/**
	 * Creates a new {@link NegativeArgumentException} for the given argument.
	 * 
	 * @param argument
	 */
	public NegativeArgumentException(final BigDecimal argument) {
		
		//Calls constructor of the base class.
		super(argument,ERROR_PREDICATE);
	}
	
	//constructor
	/**
	 * Creates a new {@link NegativeArgumentException} for the given argument.
	 * 
	 * @param argument
	 */
	public NegativeArgumentException(final BigInteger argument) {
		
		//Calls constructor of the base class.
		super(argument,ERROR_PREDICATE);
	}
	
	//constructor
	/**
	 * Creates a new {@link NegativeArgumentException} for the given argument.
	 * 
	 * @param argument
	 */
	public NegativeArgumentException(final double argument) {
		
		//Calls constructor of the base class.
		super(argument,ERROR_PREDICATE);
	}
	
	//constructor
	/**
	 * Creates a new {@link NegativeArgumentException} for the given argument.
	 * 
	 * @param argument
	 */
	public NegativeArgumentException(final long argument) {
		
		//Calls constructor of the base class.
		super(argument, ERROR_PREDICATE);
	}
	
	//constructor
	/**
	 * Creates a new {@link NegativeArgumentException}
	 * for the given argument that has the given argument name.
	 * 
	 * @param argumentName
	 * @param argument
	 * @throws IllegalArgumentException if the given argument name is null.
	 * @throws IllegalArgumentException if the given argument name is blank.
	 */
	public NegativeArgumentException(final String argumentName, final BigDecimal argument) {
		
		//Calls constructor of the base class.
		super(argumentName, argument, ERROR_PREDICATE);
	}
	
	//constructor
	/**
	 * Creates a new {@link NegativeArgumentException}
	 * for the given argument that has the given argument name.
	 * 
	 * @param argumentName
	 * @param argument
	 * @throws IllegalArgumentException if the given argument name is null.
	 * @throws IllegalArgumentException if the given argument name is blank.
	 */
	public NegativeArgumentException(final String argumentName, final BigInteger argument) {
		
		//Calls constructor of the base class.
		super(argumentName, argument, ERROR_PREDICATE);
	}
	
	//constructor
	/**
	 * Creates a new {@link NegativeArgumentException}
	 * for the given argument that has the given argument name.
	 * 
	 * @param argumentName
	 * @param argument
	 * @throws IllegalArgumentException if the given argument name is null.
	 * @throws IllegalArgumentException if the given argument name is blank.
	 */
	public NegativeArgumentException(final String argumentName, final double argument) {
		
		//Calls constructor of the base class.
		super(argumentName, argument, ERROR_PREDICATE);
	}
	
	//constructor
	/**
	 * Creates a new {@link NegativeArgumentException}
	 * for the given argument that has the given argument name.
	 * 
	 * @param argumentName
	 * @param argument
	 * @throws IllegalArgumentException if the given argument name is null.
	 * @throws IllegalArgumentException if the given argument name is blank.
	 */
	public NegativeArgumentException(final String argumentName, final long argument) {
		
		//Calls constructor of the base class.
		super(argumentName, argument, ERROR_PREDICATE);
	}
}
