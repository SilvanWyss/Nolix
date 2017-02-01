/*
 * file:	StringHelper.java
 * author:	Silvan Wyss
 * month:	2015
 * lines:	250
 */

//package declaration
package ch.nolix.common.helper;

//own imports
import ch.nolix.common.constants.StringManager;
import ch.nolix.common.exception.Argument;
import ch.nolix.common.exception.InvalidArgumentException;
import ch.nolix.common.util.Validator;

//class
/**
 * This class provides some methods to handle strings.
 */
public final class StringHelper {
	
	//static method
	/**
	 * @param count
	 * @return new string consisting of as many tabulators as the given count
	 */
	public final static String createTabulators(int count) {
		
		String tabulators = StringManager.EMPTY_STRING;
		
		for (int i = 1; i <= count; i++) {
			tabulators += CharacterHelper.TABULATOR;
		}
		
		return tabulators;
	}
		
	//static method
	/**
	 * @param string
	 * @return true if the given string is a non empty string
	 */
	public final static boolean isNonEmptyString(String string) {
		return (string != null && string.length() > 0);
	}
	
	//static method
	/**
	 * @param string
	 * @return the boolean the given string represents
	 * @throws Exception if the given string does not represent a boolean
	 */
	public final static boolean toBoolean(String string) {
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
				throw new RuntimeException("The string '" + string + "' does not represent a boolean.");
		}
	}
	
	//static method
	/**
	 * @param string
	 * @return the integer the given string represents
	 * @throws Exception if the given string does not represent an integer
	 */
	public final static int toInteger(String string) {
		
		Validator.throwExceptionIfStringIsNullOrEmpty("string", string);
		
		int startIndex = 0;
		
		boolean negative = false;
		
		if (string.charAt(0) == CharacterHelper.MINUS) {
			startIndex++;
			negative = true;
		}
		
		boolean decimal = true;
		
		if (string.length() - startIndex > 2 && string.substring(startIndex, startIndex + 2).equals("0x")) {
			startIndex += 2;
			decimal = false;
		}
		
		if (string.length() - startIndex > 10) {
			throw new InvalidArgumentException(new Argument(string));
		}
		
		if (negative) {
			
			if (decimal) {
				return -toIntegerFromNonNegativeDecimal(string.substring(startIndex));
			}
			
			return -toIntegerFromNonNegativeHexaDecimal(string.substring(startIndex));
		}
		else {
			if (decimal) {
				return toIntegerFromNonNegativeDecimal(string.substring(startIndex));
			}
			
			return toIntegerFromNonNegativeHexaDecimal(string.substring(startIndex));
		}
	}

	//private constructor
	/**
	 * Avoids that an instance of this class can be created.
	 */
	private StringHelper() {}
	
	//static method
	/**
	 * @param string
	 * @return the non negative decimal integer the given string represents
	 * @throws Exception if the given string does not represent a negative decimal integer
	 */
	private final static int toIntegerFromNonNegativeDecimal(String string) {
		
		int number = 0;
		
		for (int i = 0; i < string.length(); i++) {
			
			number *= 10;
			
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
					throw new InvalidArgumentException(new Argument(string));
			}
		}
		
		return number;
	}
	
	//static method
	/**
	 * @param string
	 * @return the non-negative hexadecimal integer the given string represents
	 * @throws Exception if the given string does not represent a non-negative decimal integer
	 */
	private final static int toIntegerFromNonNegativeHexaDecimal(String string) {
		
		int number = 0;
		
		for (int i = 0; i < string.length(); i++) {
			
			number *= 16;
			
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
					throw new InvalidArgumentException(new Argument(string));
			}
		}
		
		return number;
	}
}
