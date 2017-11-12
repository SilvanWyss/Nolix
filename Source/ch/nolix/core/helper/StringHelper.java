//package declaration
package ch.nolix.core.helper;

//own imports
import ch.nolix.core.constants.CharacterCatalogue;
import ch.nolix.core.constants.StringCatalogue;
import ch.nolix.core.invalidArgumentException.Argument;
import ch.nolix.core.invalidArgumentException.ArgumentName;
import ch.nolix.core.invalidArgumentException.ErrorPredicate;
import ch.nolix.core.invalidArgumentException.InvalidArgumentException;
import ch.nolix.core.sequencer.Sequencer;
import ch.nolix.core.validator2.Validator;

//class
/**
 * This class provides methods to handle strings.
 * Of this class no instance can be created.
 * 
 * @author Silvan Wyss
 * @month 2015-12
 * @lines 310
 */
public final class StringHelper {
	
	//static method
	/**
	 * @param string
	 * @param length
	 * @return a new string that is the given string extended to the given length with spaces.
	 * @throws NullArgumentException if the given string is null.
	 * @throws NegativeArgumentException if the given length is negative.
	 * @throws InvalidArgumentException if the given string is longer than the given length.
	 */
	public static String createStringWithLength(final String string, final int length) {
		
		//Checks if the given string is not null.
		Validator.suppose(string).isNotNull();
		
		//Checks if the given length is not negative.
		Validator.suppose(length).thatIsNamed("length").isNotNegative();
		
		//Checks if the given string is not longer than the given length.
		if (string.length() > length) {
			throw new InvalidArgumentException(
				new ArgumentName("string"),
				new Argument(string),
				new ErrorPredicate("is longer than " + length + " with a length of " + string.length())
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
	 * @param tabulatorCount
	 * @return a new string consisting of as many tabulators as the given tabulator count says.
	 * @throws NegativeArgumentException if the given tabulator count is negative.
	 */
	public static String createTabulators(final int tabulatorCount) {
		
		//Checks if the given count is not negative.
		Validator.suppose(tabulatorCount).thatIsNamed("count").isNotNegative();
		
		String tabulators = StringCatalogue.EMPTY_STRING;
		
		for (int i = 1; i <= tabulatorCount; i++) {
			tabulators += CharacterCatalogue.TABULATOR;
		}
		
		return tabulators;
	}
	
	//static method
	/**
	 * @param string
	 * @return the boolean the given string represents.
	 * @throws InvalidArgumentException if the given string represents no boolean.
	 */
	public static boolean toBoolean(final String string) {
		
		//Enumerates the given string.
		switch (string) {
			case "0":
				return false;
			case "F":
				return false;
			case "False":
				return false;
			case "1":
				return true;
			case "T":
				return true;
			case "True":
				return true;
			default:
				throw new InvalidArgumentException(
					new Argument(string),
					new ErrorPredicate("represents no boolean")
				);
		}
	}
	
	//static method
	/**
	 * @param string
	 * @return the double the given string represents.
	 * @throws InvalidArgumentException if the given string represents no double.
	 */
	public static double toDouble(final String string) {
		
		if (!string.matches("\\d+.\\d+")) {
			throw new InvalidArgumentException(
				new Argument(string),
				new ErrorPredicate("represents no double")
			);
		}
		
		return Double.valueOf(string);
	}
	
	//static method
	/**
	 * @param string
	 * @return the int the given string represents.
	 * @throws InvalidArgumentException if the given string represents no int.
	 */
	public static int toInt(final String string) {
		
		//Checks if the given string is not null or empty.
		Validator.suppose(string).isNotEmpty();
		
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
			throw new InvalidArgumentException(
				new Argument(string),
				new ErrorPredicate("represents no int")
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
	 * @throws InvalidArgumentException if the given string represents no non-negative decimal int.
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
					throw new InvalidArgumentException(
						new Argument(string),
						new ErrorPredicate("represents no non-negative decimal int")
					);
			}
		}
		
		return number;
	}
	
	//static method
	/**
	 * @param string
	 * @return the non-negative hexadecimal int the given string represents.
	 * @throws Exception if the given string represents no non-negative hexadecimal int.
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
					throw new InvalidArgumentException(
						new Argument(string),
						new ErrorPredicate("represents no non-negative hexadecimal decimal int")
					);
			}
		}
		
		return number;
	}
	
	//private constructor
	/**
	 * Avoids that an instance of this class can be created.
	 */
	private StringHelper() {}
}
