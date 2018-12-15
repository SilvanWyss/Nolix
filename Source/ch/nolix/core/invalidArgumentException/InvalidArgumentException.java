//package declaration
package ch.nolix.core.invalidArgumentException;

//own imports
import ch.nolix.core.constants.CharacterCatalogue;
import ch.nolix.core.logger.Logger;

/**
 * A {@link InvalidArgumentException} is a {@link RuntimeException}
 * that is intended to be thrown when an argument is not valid.
 * 
 * A {@link InvalidArgumentException} stores the name of the argument it was created for.
 * A {@link InvalidArgumentException} stores the argument it was created for.
 * 
 * @author Silvan Wyss
 * @month 2016-11
 * @lines 210
 */
@SuppressWarnings("serial")
public class InvalidArgumentException extends RuntimeException {
	
	//constant
	public static final int MAX_ARGUMENT_NAME_LENGTH = 100;
	
	//constants
	private static final String DEFAULT_ARGUMENT_NAME = "argument";
	private static final String DEFAULT_ERROR_PREDICATE = "is not valid";
	
	//static method
	/**
	 * @return a safe argument name for the given argument name.
	 * @throws RuntimeException if the given argument name is null.
	 * @throws RuntimeException if the given argument name is blank.
	 */
	private static String createSafeArgumentName(final String argumentName) {
		
		//Checks if the given argument name is not null.
		if (argumentName == null) {
			throw new RuntimeException("The given argument name is null.");
		}
		
		//Checks if the given argument name is not blank.
		if (argumentName.isBlank()) {
			throw new RuntimeException("The given argument name is blank.");
		}
		
		return argumentName;
	}
	
	//static method
	/**
	 * The given argument can be null.
	 * 
	 * @return a safe argument name for the given argument.
	 */
	private static String createSafeArgumentName2(final Object argument) {
		
		//Handles the case that the given argument is null.
		if (argument == null) {
			return DEFAULT_ARGUMENT_NAME;
		}
		
		//Handles the case that the given argument is not null.
		return argument.getClass().getSimpleName();
	}
	
	//static method
	/**
	 * The given argument can be null.
	 * 
	 * @return a safe string representation of the given argument.
	 */
	private static String createSafeArgumentString(final Object argument) {
		
		//Handles the case that the given argument is null.
		if (argument == null) {
			return " ";
		}
		
		//Handles the case that the given argument is not null.
			//Gets the string representation of the given argument.
			final var string = argument.toString();
			
			/*
			 * Handles the case that the length of the string representation is not bigger
			 * than the maximum argument name length.
			 */
			if (string.length() <= MAX_ARGUMENT_NAME_LENGTH) {
				return " '" + string + "' ";
			}
			
			/*
			 * Handles the case that the length of the string representation is bigger
			 * than the maximum argument name length.
			 */
			return " '" + string.substring(0, 99) + CharacterCatalogue.ELLIPSIS + "' ";
	}
	
	//static method
	/**
	 * @param errorPredicate
	 * @return a safe error predicate for the given error predicate.
	 * @throws RuntimeException if the given error predicate is null.
	 * @throws RuntimeException if the given error predicate is blank.
	 */
	private static String createSafeErrorPredicate(final String errorPredicate) {
		
		//Checks if the given error predicate is not null.
		if (errorPredicate == null) {
			throw new RuntimeException("The given error predicate is null.");
		}
		
		//Checks if the given error predicate is not blank.
		if (errorPredicate.isBlank()) {
			throw new RuntimeException("The given error predicate is blank.");
		}
		
		return errorPredicate;
	}
	
	//attributes
	private final String argumentName;
	private final Object argument;
	private final String errorPredicate;
	
	//constructor
	/**
	 * Creates a new {@link InvalidArgumentException} for the given argument.
	 * 
	 * @param argument
	 */
	public InvalidArgumentException(final Object argument) {
		
		//Calls other constructor.
		this(createSafeArgumentName2(argument), argument, DEFAULT_ERROR_PREDICATE);
	}
	
	//constructor
	/**
	 * Creates a new {@link InvalidArgumentException}
	 * for the given argument and error predicate.
	 * 
	 * @param argument
	 * @param errorPredicate
	 * @throws RuntimeException if the given error predicate is null.
	 * @throws RuntimeException if the given error predicate is blank.
	 */
	public InvalidArgumentException(final Object argument, final String errorPredicate) {
		
		//Calls other constructor.
		this(createSafeArgumentName2(argument), argument, errorPredicate);
	}
		
	//constructor
	/**
	 * Creates a new {@link InvalidArgumentException}
	 * for the given argument, argument name and error predicate.
	 * 
	 * @param argument
	 * @param argumentName
	 * @param errorPredicate
	 * @throws RuntimeException if the given argument name is null.
	 * @throws RuntimeException if the given argument name is blank.
	 * @throws RuntimeException if the given error predicate is null.
	 * @throws RuntimeException if the given error predicate is blank.
	 */
	public InvalidArgumentException(
		final String argumentName,
		final Object argument,
		final String errorPredicate
	) {
		super(
			"The given "
			+ createSafeArgumentName(argumentName)
			+ createSafeArgumentString(argument)
			+ createSafeErrorPredicate(errorPredicate)
			+ "."
		);
		
		this.argumentName = argumentName;
		this.argument = argument;
		this.errorPredicate = errorPredicate;
		
		Logger.logError(getMessage());
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
