//package declaration
package ch.nolix.common.errorcontrol.invalidargumentexception;

//Java imports
import java.math.BigDecimal;
import java.math.BigInteger;

//class
/**
 * A {@link SmallerArgumentException} is a {@link InvalidArgumentException}
 * that is supposed to be thrown when a given argument is undesirably smaller than a given limit.
 * 
 * @author Silvan Wyss
 * @date 2016-03-01
 * @lines 200
 */
@SuppressWarnings("serial")
public final class SmallerArgumentException extends InvalidArgumentException {
	
	//static method
	/**
	 * @param limit
	 * @return an error predicate for the given limit.
	 * @throws IllegalArgumentException if the given limit is null.
	 */
	private static String createErrorPredicate(final BigDecimal limit) {
		
		//Asserts that the given limit is not null.
		if (limit == null) {
			throw new IllegalArgumentException("The given limit is null.");
		}
		
		return ("is smaller than " + limit);
	}
	
	//static method
	/**
	 * @param limit
	 * @return an error predicate for the given limit.
	 * @throws IllegalArgumentException if the given limit is null.
	 */
	private static String createErrorPredicate(final BigInteger limit) {
		
		//Asserts that the given limit is not null.
		if (limit == null) {
			throw new IllegalArgumentException("The given limit is null.");
		}
		
		return ("is smaller than " + limit);
	}
	
	//constructor
	/**
	 * Creates a new {@link SmallerArgumentException} for the given argument and limit.
	 * 
	 * @param argument
	 * @param limit
	 * @throws IllegalArgumentException if the given limit is null.
	 */
	public SmallerArgumentException(final BigDecimal argument, final BigDecimal limit) {
		
		//Calls constructor of the base class.
		super(argument,	createErrorPredicate(limit));
	}
	
	//constructor
	/**
	 * Creates a new {@link SmallerArgumentException} for the given argument and limit.
	 * 
	 * @param argument
	 * @param limit
	 * @throws IllegalArgumentException if the given limit is null.
	 */
	public SmallerArgumentException(final BigInteger argument, final BigInteger limit) {
		
		//Calls constructor of the base class.
		super(argument,	createErrorPredicate(limit));
	}
	
	//constructor
	/**
	 * Creates a new {@link SmallerArgumentException} for the given argument and limit.
	 * 
	 * @param argument
	 * @param limit
	 */
	public SmallerArgumentException(final double argument, final double limit) {
		
		//Calls constructor of the base class.
		super(argument, "is smaller than " + limit);
	}
	
	//constructor
	/**
	 * Creates a new {@link SmallerArgumentException} for the given argument and limit.
	 * 
	 * @param argument
	 * @param limit
	 */
	public SmallerArgumentException(final long argument, final long limit) {
		
		//Calls constructor of the base class.
		super(argument, "is smaller than " + limit);
	}
	
	//constructor
	/**
	 * Creates a new {@link SmallerArgumentException} for the given argument,
	 * that has the given argument name, and for the given limit.
	 * 
	 * @param argumentName
	 * @param argument
	 * @param limit
	 * @throws IllegalArgumentException if the given argument name is null.
	 * @throws IllegalArgumentException if the given argument name is blank.
	 * @throws IllegalArgumentException if the given limit is null.
	 */
	public SmallerArgumentException(
		final String argumentName,
		final BigDecimal argument,
		final BigDecimal limit
	) {
		//Calls constructor of the base class.
		super(
			argumentName,
			argument,
			"is smaller than " + limit
		);
	}
	
	//constructor
	/**
	 * Creates a new {@link SmallerArgumentException} for the given argument,
	 * that has the given argument name, and for the given limit.
	 * 
	 * @param argumentName
	 * @param argument
	 * @param limit
	 * @throws IllegalArgumentException if the given argument name is null.
	 * @throws IllegalArgumentException if the given argument name is blank.
	 * @throws IllegalArgumentException if the given limit is null.
	 */
	public SmallerArgumentException(
		final String argumentName,
		final BigInteger argument,
		final BigInteger limit
	) {
		//Calls constructor of the base class.
		super(
			argumentName,
			argument,
			"is smaller than " + limit
		);
	}
	
	//constructor
	/**
	 * Creates a new {@link SmallerArgumentException} for the given argument,
	 * that has the given argument name, and for the given limit.
	 * 
	 * @param argumentName
	 * @param argument
	 * @param limit
	 * @throws IllegalArgumentException if the given argument name is null.
	 * @throws IllegalArgumentException if the given argument name is blank.
	 */
	public SmallerArgumentException(
		final String argumentName,
		final double argument,
		final double limit
	) {
		//Calls constructor of the base class.
		super(
			argumentName,
			argument,
			"is smaller than " + limit
		);
	}
	
	//constructor
	/**
	 * Creates a new {@link SmallerArgumentException} for the given argument,
	 * that has the given argument name, and for the given limit.
	 * 
	 * @param argumentName
	 * @param argument
	 * @param limit
	 * @throws IllegalArgumentException if the given argument name is null.
	 * @throws IllegalArgumentException if the given argument name is blank.
	 */
	public SmallerArgumentException(
		final String argumentName,
		final long argument,
		final long limit
	) {
		//Calls constructor of the base class.
		super(
			argumentName,
			argument,
			"is smaller than " + limit
		);
	}
}
