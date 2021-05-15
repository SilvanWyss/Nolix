//package declaration
package ch.nolix.common.errorcontrol.invalidargumentexception;

//own imports
import ch.nolix.common.constant.CharacterCatalogue;

//class
/**
 * A {@link InvalidArgumentException} is a {@link RuntimeException}
 * that is supposed to be thrown when a given argument is not valid.
 * 
 * A {@link InvalidArgumentException} stores the name of the argument it was created for.
 * A {@link InvalidArgumentException} stores the argument it was created for.
 * 
 * The name of a {@link InvalidArgumentException} should be either:
 * -[A]ArgumentException
 * -Non[PA]ArgumentException
 * -Argument[P]Exception
 * Whereas [A] is an adjective, [PA] is a grammatically positive adjective and [P] is a predicate.
 * For example: NegativeArgumentException, NonPositiveArgumentException, ArgumentIsOutOfRangeException.
 * 
 * @author Silvan Wyss
 * @date 2016-12-01
 * @lines 200
 */
@SuppressWarnings("serial")
public class InvalidArgumentException extends RuntimeException {
	
	//constants
	private static final int MAX_OBJECT_NAME_LENGTH = 100;
	private static final String DEFAULT_ARGUMENT_NAME = "argument";
	private static final String DEFAULT_ERROR_PREDICATE = "is not valid";
	
	//static method
	/**
	 * @param object
	 * @return a valid {@link String} representation of the given object.
	 */
	protected static String createValidStringRepresentationInProbableQuotes(final Object object) {
		
		//Handles the case that the given object is null.
		if (object == null) {
			return " ";
		}
				
		//Gets the String representation of the object.
		final var string = object.toString();
		
		//Handles the case that the String representation is null or blank.
		if (string == null || string.isBlank()) {
			return " ";
		}
		
		//Handles the case that the length of the String representation is not bigger than the max object name length.
		if (string.length() <= MAX_OBJECT_NAME_LENGTH) {
			return (" '" + string + "' ");
		}
		
		//Handles the case that the length of the String representation is bigger than the max object name length.
		return (" '" + string.substring(0, MAX_OBJECT_NAME_LENGTH) + CharacterCatalogue.ELLIPSIS + "' ");
	}
	
	//static method
	/**
	 * @param argument
	 * @return a valid argument name for the given argument.
	 */
	private static String createValidArgumentName(final Object argument) {
		
		//Handles the case that the given argument is null.
		if (argument == null) {
			return DEFAULT_ARGUMENT_NAME;
		}
		
		//Handles the case that the given argument is not null.
		return argument.getClass().getSimpleName();
	}
	
	//static method
	/**
	 * @param argumentName
	 * @return the given argumentName if it is valid.
	 * @throws IllegalArgumentException if the given argumentName is null.
	 * @throws IllegalArgumentException if the given argumentName is blank.
	 */
	private static String validateAndGetArgumentName(final String argumentName) {
		
		//Asserts that the given argumentName is not null.
		if (argumentName == null) {
			throw new IllegalArgumentException("The given argument name is null.");
		}
		
		//Asserts that the given argumentName is not blank.
		if (argumentName.isBlank()) {
			throw new IllegalArgumentException("The given argument name is blank.");
		}
		
		return argumentName;
	}
	
	//static method
	/**
	 * @param errorPredicate
	 * @return the given errorPredicate if it is valid.
	 * @throws IllegalArgumentException if the given error predicate is null.
	 * @throws IllegalArgumentException if the given error predicate is blank.
	 */
	private static String validateAndGetErrorPredicate(final String errorPredicate) {
		
		//Asserts that the given errorPredicate is not null.
		if (errorPredicate == null) {
			throw new IllegalArgumentException("The given error predicate is null.");
		}
		
		//Asserts that the given errorPredicate is not blank.
		if (errorPredicate.isBlank()) {
			throw new IllegalArgumentException("The given error predicate is blank.");
		}
		
		return errorPredicate;
	}
	
	//attributes
	private final String argumentName;
	private final transient Object argument;
	private final String errorPredicate;
	
	//constructor
	/**
	 * Creates a new {@link InvalidArgumentException} for the given argument.
	 * 
	 * @param argument
	 */
	public InvalidArgumentException(final Object argument) {
		
		//Calls other constructor.
		this(createValidArgumentName(argument), argument, DEFAULT_ERROR_PREDICATE);
	}
	
	//constructor
	/**
	 * Creates a new {@link InvalidArgumentException} for the given argument and error predicate.
	 * 
	 * @param argument
	 * @param errorPredicate
	 * @throws IllegalArgumentException if the given errorPredicate is null.
	 * @throws IllegalArgumentException if the given errorPredicate is blank.
	 */
	public InvalidArgumentException(final Object argument, final String errorPredicate) {
		
		//Calls other constructor.
		this(createValidArgumentName(argument), argument, errorPredicate);
	}
		
	//constructor
	/**
	 * Creates a new {@link InvalidArgumentException} for the given argument, argumentName and errorPredicate.
	 * 
	 * @param argumentName
	 * @param argument
	 * @param errorPredicate
	 * @throws IllegalArgumentException if the given argumentName is null.
	 * @throws IllegalArgumentException if the given argumentName is blank.
	 * @throws IllegalArgumentException if the given errorPredicate is null.
	 * @throws IllegalArgumentException if the given errorPredicate is blank.
	 */
	public InvalidArgumentException(final String argumentName, final Object argument, final String errorPredicate) {
		super(
			"The given "
			+ validateAndGetArgumentName(argumentName)
			+ createValidStringRepresentationInProbableQuotes(argument)
			+ validateAndGetErrorPredicate(errorPredicate)
			+ "."
		);
		
		this.argumentName = argumentName;
		this.argument = argument;
		this.errorPredicate = errorPredicate;
	}
	
	//method
	/**
	 * @return the name of the argument of the current {@link InvalidArgumentException}.
	 */
	public final String getArgumentName() {
		return argumentName;
	}
	
	//method
	/**
	 * @return the error predicate of the current {@link InvalidArgumentException}.
	 */
	public final String getErrorPredicate() {
		return errorPredicate;
	}
	
	//method
	/**
	 * @return the argument of the current {@link InvalidArgumentException}.
	 */
	public final Object getRefArgument() {
		return argument;
	}
}
