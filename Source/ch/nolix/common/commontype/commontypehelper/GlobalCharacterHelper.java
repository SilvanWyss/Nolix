//package declaration
package ch.nolix.common.commontype.commontypehelper;

//own imports
import ch.nolix.common.constant.LowerCaseCatalogue;
import ch.nolix.common.errorcontrol.invalidargumentexception.InvalidArgumentException;

//class
/**
 * This class provides methods to handle characters.
 * Of this class an instance cannot be created.
 * 
 * @author Silvan Wyss
 * @month 2015-12
 * @lines 90
 */
public final class GlobalCharacterHelper {
	
	//articles
	private static final String ARTICLE_A = "a";
	private static final String ARTICLE_AN = "an";
	
	//static method
	/**
	 * @param letter
	 * @return the article of a word that starts with the given letter.
	 * @throws InvalidArgumentException if the given letter is not valid.
	 */
	public static String getArticle(final char letter) {
		
		//Handles the case that the given letter is not valid.
		if (
			letter < 65
			|| (letter > 90 && letter < 97)
			|| letter > 122
		) {
			throw new InvalidArgumentException(LowerCaseCatalogue.LETTER, letter, "is not valid");
		}
		
		//Enumerates the given letter.
		switch (letter) {
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
	 * Avoids that an instance of this class can be created.
	 */
	private GlobalCharacterHelper() {}
}
