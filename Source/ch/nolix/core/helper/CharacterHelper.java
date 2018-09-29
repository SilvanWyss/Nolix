//package declaration
package ch.nolix.core.helper;

import ch.nolix.core.invalidArgumentException.Argument;
import ch.nolix.core.invalidArgumentException.ArgumentName;
import ch.nolix.core.invalidArgumentException.InvalidArgumentException;

//class
/**
 * This class provides methods to handle characters.
 * Of this class no instance can be created.
 * 
 * @author Silvan Wyss
 * @month 2015-12
 * @lines 90
 */
public final class CharacterHelper {
	
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
			throw new InvalidArgumentException(
				new ArgumentName("letter"),
				new Argument(letter)
			);
		}
		
		//Handles the case that the given letter is a vowel.
		switch (letter) {
			case 'A':
				return ARTICLE_AN;
			case 'a':
				return ARTICLE_AN;
			case 'E':
				return ARTICLE_AN;
			case 'e':
				return ARTICLE_AN;
			case 'I':
				return ARTICLE_AN;
			case 'i':
				return ARTICLE_AN;
			case 'O':
				return ARTICLE_AN;
			case 'o':
				return ARTICLE_AN;
			case 'U':
				return ARTICLE_AN;
			case 'u':
				return ARTICLE_AN;
		}
		
		//Handles the case that the given letter is a consonant.
		return ARTICLE_A;
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
	
	//private constructor
	/**
	 * Avoids that an instance of this class can be created.
	 */
	private CharacterHelper() {}
}
