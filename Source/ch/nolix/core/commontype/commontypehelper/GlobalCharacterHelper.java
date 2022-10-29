//package declaration
package ch.nolix.core.commontype.commontypehelper;

//own imports
import ch.nolix.core.errorcontrol.invalidargumentexception.InvalidArgumentException;
import ch.nolix.core.programatom.name.LowerCaseCatalogue;

//class
/**
 * The {@link GlobalCharacterHelper} provides methods to handle characters.
 * Of the {@link GlobalCharacterHelper} an instance cannot be created.
 * 
 * @author Silvan Wyss
 * @date 2016-01-01
 */
public final class GlobalCharacterHelper {
	
	//constant
	private static final String ARTICLE_A = "a";
	
	//constant
	private static final String ARTICLE_AN = "an";
	
	//static method
	/**
	 * @param letter
	 * @return the article of a word that starts with the given letter.
	 * @throws InvalidArgumentException if the given letter is not valid.
	 */
	public static String getArticle(final char letter) {
		
		//Asserts that the given letter is valid.
		if (
			letter < 65
			|| (letter > 90 && letter < 97)
			|| letter > 122
		) {
			throw InvalidArgumentException.forArgumentNameAndArgument(LowerCaseCatalogue.LETTER, letter);
		}
		
		//Enumerates the given letter.
		switch (letter) {
			case
			'A',
			'a',
			'E',
			'e',
			'I',
			'i',
			'O',
			'o',
			'U',
			'u':
				return ARTICLE_AN;
			default:
				return ARTICLE_A;
		}	
	}

	//static method
	/**
	 * @param character
	 * @return true if the given character is a digit.
	 */
	public static boolean isDigit(final char character) {
		return (character >= 48 && character <= 57);
	}
	
	//static method
	/**
	 * @param character
	 * @return true if the given character is a hexadecimal digit.
	 */
	public static boolean isHexadecimalDigit(final char character) {
		return (isDigit(character) || (character >= 65 && character <= 70));
	}
	
	//constructor
	/**
	 * Prevents that an instance of the {@link GlobalCharacterHelper} can be created.
	 */
	private GlobalCharacterHelper() {}
}
