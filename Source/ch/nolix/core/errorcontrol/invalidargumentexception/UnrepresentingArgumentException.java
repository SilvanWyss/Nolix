//package declaration
package ch.nolix.core.errorcontrol.invalidargumentexception;

//class
/**
 * A {@link UnrepresentingArgumentException} is a {@link InvalidArgumentException}
 * that is supposed to be thrown when a given argument does undesirable not represent an object of a given type.
 * 
 * @author Silvan Wyss
 * @date 2017-03-05
 * @lines 110
 */
@SuppressWarnings("serial")
public final class UnrepresentingArgumentException extends InvalidArgumentException {
	
	//static method
	/**
	 * @param noun
	 * @return a pronoun for the given noun.
	 * @throws IllegalArgumentException if the given noun is null or blank.
	 */
	private static String getPronoun(final String noun) {
		
		//Asserts that the given noun is not null.
		if (noun == null) {
			throw new IllegalArgumentException("The given noun is null.");
		}
		
		//Asserts that the given noun is not blank.
		if (noun.isBlank()) {
			throw new IllegalArgumentException("The given noun is blank.");
		}
		
		//Enumerates the first character of the given noun.
		switch (noun.charAt(0)) {
			case 'A':
			case 'a':
			case 'E':
			case 'e':
			case 'I':
			case 'i':
			case 'O':
			case 'o':
			case 'U':
			case 'u':
				return "an";
			default:
				return "a";
		}
	}
	
	//static method
	/**
	 * @param type
	 * @return a type name for the given type.
	 * @throws IllegalArgumentException if the given type is null.
	 */
	private static String getTypeName(final Class<?> type) {
		
		//Asserts that the given type is not null.
		if (type == null) {
			throw new IllegalArgumentException("The given type is null.");
		}
		
		return type.getSimpleName();
	}
	
	//static method
	/**
	 * @param type
	 * @return a type name with a suitable pronoun for the given type.
	 * @throws IllegalArgumentException if the given type is null.
	 */
	private static String getTypeNameWithPronoun(final Class<?> type) {
		
		final var safeTypeName = getTypeName(type);
		
		return (getPronoun(safeTypeName) + " " + safeTypeName);
	}
	
	//constructor
	/**
	 * Creates a new {@link UnrepresentingArgumentException} for the given argument and the given type.
	 * 
	 * @param argument
	 * @param type
	 * @throws IllegalArgumentException if the given type is null.
	 */
	public UnrepresentingArgumentException(final Object argument, final Class<?> type) {
		
		//Calls constructor of the base class.
		super(argument, "does not represent " + getTypeNameWithPronoun(type));
	}
	
	//constructor
	/**
	 * Creates a new {@link UnrepresentingArgumentException} for the given argumentName, argument and the given type.
	 * 
	 * @param argumentName
	 * @param argument
	 * @param type
	 * @throws IllegalArgumentException if the given argumentName is null.
	 * @throws IllegalArgumentException if the given argumentName is blank.
	 * @throws IllegalArgumentException if the given type is null.
	 */
	public UnrepresentingArgumentException(final String argumentName, final Object argument, final Class<?> type) {
		
		//Calls constructor of the base class.
		super(argumentName, argument, "does not represent " + getTypeNameWithPronoun(type));
	}
}
