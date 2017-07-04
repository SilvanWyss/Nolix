//package declaration
package ch.nolix.core.helper;

//own imports
import ch.nolix.core.invalidArgumentException.Argument;
import ch.nolix.core.invalidArgumentException.ArgumentName;
import ch.nolix.core.invalidArgumentException.InvalidArgumentException;

//class
/**
 * This class provides some methods to handle characters.
 * Of this class no instance can be created.
 * 
 * @author Silvan Wyss
 * @month 2015-12
 * @lines 90
 */
public final class CharacterHelper {
	
	//static method
	/**
	 * @param letter
	 * @return the article of a word that starts with the given letter.
	 * @throws InvalidArgumentException if the given letter is not valid.
	 */
	public static String getArticle(final char letter) {
		
		//Handles the case if the given letter is not valid.
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
		
		//Handles the case if the given letter is a vowel.
		switch (letter) {
			case 'A':
				return "an";
			case 'a':
				return "an";
			case 'E':
				return "an";
			case 'e':
				return "an";
			case 'I':
				return "an";
			case 'i':
				return "an";
			case 'O':
				return "an";
			case 'o':
				return "an";
			case 'U':
				return "an";
			case 'u':
				return "an";
		}
		
		//Handles the case if the given letter is a consonant.
		return "a";
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
