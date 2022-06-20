//package declaration
package ch.nolix.core.commontype.commontypehelper;

import ch.nolix.core.commontype.constant.CharacterCatalogue;
import ch.nolix.core.commontype.constant.StringCatalogue;
import ch.nolix.core.errorcontrol.invalidargumentexception.ArgumentIsNullException;
import ch.nolix.core.errorcontrol.invalidargumentexception.InvalidArgumentException;
import ch.nolix.core.errorcontrol.invalidargumentexception.NegativeArgumentException;
import ch.nolix.core.errorcontrol.invalidargumentexception.UnrepresentingArgumentException;
import ch.nolix.core.errorcontrol.validator.GlobalValidator;
import ch.nolix.core.programatom.name.LowerCaseCatalogue;
import ch.nolix.core.programcontrol.sequencer.GlobalSequencer;

//class
/**
 * The {@link GlobalStringHelper} provides methods to handle {@link String}s.
 * Of the {@link GlobalStringHelper} an instance cannot be created.
 * 
 * @author Silvan Wyss
 * @date 2016-01-01
 */
public final class GlobalStringHelper {
		
	//static method
	/**
	 * @param string
	 * @param length
	 * @return a new {@link String} that is the given string extended to the given length with spaces.
	 * @throws ArgumentIsNullException if the given string is null.
	 * @throws NegativeArgumentException if the given length is negative.
	 * @throws InvalidArgumentException if the given string is longer than the given length.
	 */
	public static String createStringWithLength(final String string, final int length) {
		
		//Asserts that the given string is not null.
		GlobalValidator.assertThat(string).isNotNull();
		
		//Asserts that the given length is not negative.
		GlobalValidator.assertThat(length).thatIsNamed("length").isNotNegative();
		
		//Asserts that the given string is not longer than the given length.
		if (string.length() > length) {
			throw new InvalidArgumentException(
				LowerCaseCatalogue.STRING,
				string,
				"is longer than " + length + " with a length of " + string.length()
			);
		}
		
		final StringBuilder stringBuilder = new StringBuilder(string);
		
		GlobalSequencer
		.forCount(length - string.length())
		.run(() -> stringBuilder.append(CharacterCatalogue.SPACE));
		
		return stringBuilder.toString();
	}
	
	//static method
	/**
	 * @param string
	 * @param n
	 * @return a new {@link String} that is like the given string without the last n characters.
	 */
	public static String createStringWithoutLastCharacters(final String string, final int n) {
		return string.substring(0, string.length() - n);
	}
	
	//static method
	/**
	 * @param tabulatorCount
	 * @return a new {@link String} consisting of as many tabulators as the given tabulatorCount says.
	 * @throws NegativeArgumentException if the given tabulatorCount is negative.
	 */
	public static String createTabulators(final int tabulatorCount) {
		
		//Asserts that the given tabulatorCount is not negative.
		GlobalValidator.assertThat(tabulatorCount).thatIsNamed("tabulator count").isNotNegative();
		
		final var stringBuilder = new StringBuilder();
		
		for (int i = 1; i <= tabulatorCount; i++) {
			stringBuilder.append(CharacterCatalogue.TABULATOR);
		}
		
		return stringBuilder.toString();
	}
	
	//static method
	//For a better performance, this implementation does not use all comfortable methods.
	/**
	 * @param string
	 * @return the given string in quotes
	 * @throws ArgumentIsNullException if the given string is null.
	 */
	public static String getInQuotes(final String string) {
		
		if (string == null) {
			throw ArgumentIsNullException.forArgumentName(LowerCaseCatalogue.STRING);
		}
		
		return ("'" + string + "'");
	}
	
	//static method
	/**
	 * @param string
	 * @return true if the given string is in lower case.
	 */
	public static boolean isLowerCase(final String string) {
		
		//Handles the case that the given string is null.
		if (string == null) {
			return false;
		}
		
		//Handles the case that the given string is not null.
		return isLowerCaseWhenNotNull(string);
	}
	
	//static method
	/**
	 * @param string
	 * @return true if the given string is in pascal case.
	 */
	public static boolean isPascalCase(final String string) {
		
		//Handles the case that the given string is null.
		if (string == null) {
			return false;
		}
		
		//Handles the case that the given string is not null.
		return isPascalCaseWhenNotNull(string);
	}
	
	//static method
	/**
	 * @param string
	 * @param prefix
	 * @return true if the given string starts with the given prefix ignoring case.
	 */
	public static boolean startsWithIgnoringCase(final String string, final String prefix) {
		return string.regionMatches(true, 0, prefix, 0, prefix.length());
	}
	
	//static method
	/**
	 * @param string
	 * @return the boolean the given string represents.
	 * @throws InvalidArgumentException if the given string does not represent a boolean.
	 */
	public static boolean toBoolean(final String string) {
		
		//Enumerates the given string.
		switch (string) {
			case "0":
			case "F":
			case "False":
			case "false":
				return false;
			case "1":
			case "T":
			case "True":
			case "true":
				return true;
			default:
				throw new UnrepresentingArgumentException(string, Boolean.class);
		}
	}
	
	//static method
	/**
	 * @param string
	 * @return the double the given string represents.
	 * @throws InvalidArgumentException if the given string does not represent a double.
	 */
	public static double toDouble(final String string) {
		
		if (!string.matches("\\d+.\\d+")) {
			throw new InvalidArgumentException(string, "does not represent a double");
		}
		
		return Double.valueOf(string);
	}
	
	//static method
	/**
	 * @param string
	 * @return a new {@link String} in pascal case for the given string.
	 */
	public static String toPascalCase(final String string) {
		return new PascalCaseCreator().toPascalCase(string);
	}
	
	//static method
	/**
	 * @param string
	 * @return the in the given string represents.
	 * @throws InvalidArgumentException if the given string does not represent a int.
	 */
	public static int toInt(final String string) {
		return (int)toLong(string);
	}

	//static method
	/**
	 * @param string
	 * @return the long the given string represents.
	 * @throws InvalidArgumentException if the given string does not represent a long.
	 */
	public static long toLong(final String string) {
		
		//Asserts that the given string is not null or empty.
		GlobalValidator.assertThat(string).isNotEmpty();
		
		var startIndex = 0;
		
		var negative = false;
		
		if (string.charAt(0) == CharacterCatalogue.MINUS) {
			startIndex++;
			negative = true;
		}
		
		var decimal = true;
		
		if (string.length() - startIndex > 2 && string.substring(startIndex, startIndex + 2).equals("0x")) {
			startIndex += 2;
			decimal = false;
		}
		
		if (string.length() - startIndex > 19) {
			throw new UnrepresentingArgumentException(string, Long.class);
		}
		
		if (negative) {
			
			if (decimal) {
				return -toLongFromNonNegativeDecimal(string.substring(startIndex));
			}
			
			return -toLongFromNonNegativeHexaDecimal(string.substring(startIndex));
		}
		
		if (decimal) {
			return toLongFromNonNegativeDecimal(string.substring(startIndex));
		}
		
		return toLongFromNonNegativeHexaDecimal(string.substring(startIndex));
	}
	
	//static method
	/**
	 * @param string
	 * @return a new {@link String} in capital snake case for the given string.
	 */
	public static String toUpperSnakeCase(final String string) {
		return new CapitalSnakeCaseCreator().toCapitalSnakeCase(string);
	}
	
	//static method
	/**
	 * @param string
	 * @return true if the given string is in lower case for the case that the given string is not null.
	 */
	private static boolean isLowerCaseWhenNotNull(final String string) {
		return !string.contains(StringCatalogue.UNDERSCORE) &&	string.equals(string.toLowerCase());
	}
	
	//static method
	/**
	 * @param string
	 * @return true if the given string is in pascal case for the case that the given string is not null.
	 */
	private static boolean isPascalCaseWhenNotNull(final String string) {
		return string.equals(toPascalCase(string));
	}
	
	//static method
	/**
	 * @param string
	 * @return the non negative decimal integer the given string represents.
	 * @throws InvalidArgumentException if the given string does not represent a non-negative decimal int.
	 */
	private static long toLongFromNonNegativeDecimal(final String string) {
		
		var number = 0L;
		
		for (var i = 0; i < string.length(); i++) {
			
			number *= 10;
			
			//Enumerates the current digit.
			switch (string.charAt(i)) {
				case '0':
					break;
				case '1':
					number += 1;
					break;
				case '2':
					number += 2;
					break;
				case '3':
					number += 3;
					break;
				case '4':
					number += 4;
					break;
				case '5':
					number += 5;
					break;
				case '6':
					number += 6;
					break;
				case '7':
					number += 7;
					break;
				case '8':
					number += 8;
					break;
				case '9':
					number += 9;
					break;
				default:
					throw new UnrepresentingArgumentException(string, Long.class);
			}
		}
		
		return number;
	}
	
	//static method
	/**
	 * @param string
	 * @return the non-negative hexadecimal int the given string represents.
	 * @throws InvalidArgumentException if the given string does not represent a non-negative hexadecimal int.
	 */
	private static long toLongFromNonNegativeHexaDecimal(final String string) {
		
		int number = 0;
		
		for (int i = 0; i < string.length(); i++) {
			
			number *= 16;
			
			//Enumerates the current digit.
			switch (string.charAt(i)) {
				case '0':
					break;
				case '1':
					number += 1;
					break;
				case '2':
					number += 2;
					break;
				case '3':
					number += 3;
					break;
				case '4':
					number += 4;
					break;
				case '5':
					number += 5;
					break;
				case '6':
					number += 6;
					break;
				case '7':
					number += 7;
					break;
				case '8':
					number += 8;
					break;
				case '9':
					number += 9;
					break;
				case 'A':
					number += 10;
					break;
				case 'B':
					number += 11;
					break;
				case 'C':
					number += 12;
					break;
				case 'D':
					number += 13;
					break;
				case 'E':
					number += 14;
					break;
				case 'F':
					number += 15;
					break;
				default:
					throw new UnrepresentingArgumentException(string, Long.class);
			}
		}
		
		return number;
	}
	
	//constructor
	/**
	 * Prevents that an instance of the {@link GlobalStringHelper} can be created.
	 */
	private GlobalStringHelper() {}
}
