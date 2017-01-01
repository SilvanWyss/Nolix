/*
 * file:	CharacterHelper.java
 * author:	Silvan Wyss
 * month:	2015-12
 * lines:	90
 */

//package declaration
package ch.nolix.common.helper;

//own import
import ch.nolix.common.exception.InvalidArgumentException;

//class
/**
 * This class provides some methods to handle characters.
 */
public final class CharacterHelper {
	
	//characters
	public final static char APOSTROPH = '\'';
	public final static char BACK_SLASH = '\\';
	public final static char CLOSING_BRACKET = ')';
	public final static char CLOSING_CROCODILE_BRACKET = '>';
	public final static char CLOSING_SQUARE_BRACKET = ']';
	public final static char COMMA = ',';
	public final static char DOT = '.';
	public final static char MINUS = '-';
	public final static char NEW_LINE = '\n';
	public final static char OPENING_BRACKET = '(';
	public final static char OPENING_CROCODILE_BRACKET = '<';
	public final static char OPENING_SQUARE_BRACKET = '[';
	public final static char QUOTE = '\"';
	public final static char SLASH = '/';
	public final static char SPACE = ' ';
	public final static char TABULATOR = '\t';;
	public final static char UNDERSCORE = '_';
	
	//static method
	/**
	 * @param firstLetter
	 * @return the article of a word with the given first letter
	 * @throws Exception if the given first letter is not valid
	 */
	public static String getArticle(char firstLetter) {
		
		if (firstLetter > 90) {
			firstLetter -= 32;
		}
		
		switch (firstLetter) {
			case 'A':
				return "an";
			case 'E':
				return "an";
			case 'I':
				return "an";
			case 'O':
				return "an";
			case 'U':
				return "an";
		}
		
		if (firstLetter < 65 || firstLetter > 90) {
			throw new InvalidArgumentException("firstLetter", firstLetter);
		}
		
		return "a";
	}

	//static method
	/**
	 * @param character
	 * @return true if the given character is a digit
	 */
	public static boolean isDigit(char character) {		
		return (character >= 48 && character <= 57);
	}
	
	//static method
	/**
	 * @param character
	 * @return true if the given character is a hexadecimal digit
	 */
	public static boolean isHexadecimalDigit(char character) {
		return (isDigit(character) || (character >= 65 && character <= 70));
	}
	
	//private constructor
	/**
	 * Avoids that an instance of this class can be created.
	 */
	private CharacterHelper() {}
}
