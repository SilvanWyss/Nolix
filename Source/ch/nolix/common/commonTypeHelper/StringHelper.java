//package declaration
package ch.nolix.common.commonTypeHelper;

import ch.nolix.common.constant.CharacterCatalogue;
import ch.nolix.common.constant.VariableNameCatalogue;
import ch.nolix.common.invalidArgumentExceptions.InvalidArgumentException;
import ch.nolix.common.invalidArgumentExceptions.UnrepresentingArgumentException;
import ch.nolix.common.sequencer.Sequencer;
import ch.nolix.common.validator.Validator;

//class
/**
 * The {@link StringHelper} provides methods to handle {@link String}s.
 * Of the {@link StringHelper} an instance cannot be created.
 * 
 * @author Silvan Wyss
 * @month 2015-12
 * @lines 320
 */
public final class StringHelper {
		
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
		Validator.assertThat(string).isNotNull();
		
		//Asserts that the given length is not negative.
		Validator.assertThat(length).thatIsNamed("length").isNotNegative();
		
		//Asserts that the given string is not longer than the given length.
		if (string.length() > length) {
			throw new InvalidArgumentException(
				VariableNameCatalogue.STRING,
				string,
				"is longer than " + length + " with a length of " + string.length()
			);
		}
		
		final StringBuilder stringBuilder = new StringBuilder(string);
		
		Sequencer
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
		Validator.assertThat(tabulatorCount).thatIsNamed("tabulator count").isNotNegative();
		
		final var stringBuilder = new StringBuilder();
		
		for (int i = 1; i <= tabulatorCount; i++) {
			stringBuilder.append(CharacterCatalogue.TABULATOR);
		}
		
		return stringBuilder.toString();
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
	 * @return a new camel case {@link String} for the given string.
	 */
	public static String toCamelCase(final String string) {
		return new CamelCaseCreator().toCamelCase(string);
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
	 * @return the int the given string represents.
	 * @throws InvalidArgumentException if the given string does not represent a int.
	 */
	public static int toInt(final String string) {
		
		//Asserts that the given string is not null or empty.
		Validator.assertThat(string).isNotEmpty();
		
		int startIndex = 0;
		
		boolean negative = false;
		
		if (string.charAt(0) == CharacterCatalogue.MINUS) {
			startIndex++;
			negative = true;
		}
		
		boolean decimal = true;
		
		if (string.length() - startIndex > 2 && string.substring(startIndex, startIndex + 2).equals("0x")) {
			startIndex += 2;
			decimal = false;
		}
		
		if (string.length() - startIndex > 10) {
			throw
			new InvalidArgumentException(
				string,
				"does not represent an integer"
			);
		}
		
		if (negative) {
			
			if (decimal) {
				return -toIntFromNonNegativeDecimal(string.substring(startIndex));
			}
			
			return -toIntFromNonNegativeHexaDecimal(string.substring(startIndex));
		}
		else {
			if (decimal) {
				return toIntFromNonNegativeDecimal(string.substring(startIndex));
			}
			
			return toIntFromNonNegativeHexaDecimal(string.substring(startIndex));
		}
	}
	
	
	
	//static method
	/**
	 * @param string
	 * @return the non negative decimal int the given string represents.
	 * @throws InvalidArgumentException if the given string does not represent a non-negative decimal int.
	 */
	private static int toIntFromNonNegativeDecimal(final String string) {
		
		int number = 0;
		
		for (int i = 0; i < string.length(); i++) {
			
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
					throw
					new InvalidArgumentException(
						string,
						"does not represent a non-negative decimal int"
					);
			}
		}
		
		return number;
	}
	
	//static method
	/**
	 * @param string
	 * @return the non-negative hexadecimal int the given string represents.
	 * @throws Exception if the given string does not represent a non-negative hexadecimal int.
	 */
	private static int toIntFromNonNegativeHexaDecimal(final String string) {
		
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
					throw
					new InvalidArgumentException(
						string,
						"does not represents a non-negative hexadecimal decimal int"
					);
			}
		}
		
		return number;
	}
	
	//visibility-reducing constructor
	/**
	 * Avoids that an instance of the {@link StringHelper} can be created.
	 */
	private StringHelper() {}
}
