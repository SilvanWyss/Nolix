//package declaration
package ch.nolix.core.errorcontrol.simplevalidator;

import ch.nolix.core.errorcontrol.invalidargumentexception.ArgumentIsNullException;
import ch.nolix.core.errorcontrol.invalidargumentexception.ArgumentIsOutOfRangeException;
import ch.nolix.core.errorcontrol.invalidargumentexception.ArgumentIsZeroException;
import ch.nolix.core.errorcontrol.invalidargumentexception.BiggerArgumentException;
import ch.nolix.core.errorcontrol.invalidargumentexception.EmptyArgumentException;
import ch.nolix.core.errorcontrol.invalidargumentexception.NegativeArgumentException;
import ch.nolix.core.errorcontrol.invalidargumentexception.NonPositiveArgumentException;
import ch.nolix.core.errorcontrol.invalidargumentexception.SmallerArgumentException;
import ch.nolix.core.errorcontrol.invalidargumentexception.UnequalArgumentException;

//class
/**
 * The {@link SimpleValidator} can validate values.
 * 
 * @author Silvan Wyss
 * @date 2016-01-01
 */
public final class SimpleValidator {
	
	//static method
	/**
	 * @param name
	 * @param string
	 * @throws ArgumentIsNullException if the given string, that has the given name, is null.
	 * @throws EmptyArgumentException if the given string, that has the given name, is null.
	 */
	public static void throwExceptionIfStringIsNullOrEmpty(final String name, String string) {
		
		throwExceptionIfValueIsNull(name, string);
		
		if (string.isEmpty()) {
			throw new EmptyArgumentException(name, string);
		}
	}
	
	//static method
	/**
	 * @param name
	 * @param value
	 * @param max
	 * @throws BiggerArgumentException if the given value, that has the given name, is bigger than the given max.
	 */
	public static void throwExceptionIfValueIsBigger(final String name, final double value, final double max) {
		if (value > max) {
			throw new BiggerArgumentException(name, value, max);
		}
	}
	
	//static method
	/**
	 * @param name
	 * @param value
	 * @param max
	 * @throws BiggerArgumentException if the given value, that has the given name, is bigger than the given max.
	 */
	public static void throwExceptionIfValueIsBigger(final String name, final int value, final int max) {
		if (value > max) {
			throw new BiggerArgumentException(name, value, max);
		}
	}
	
	//static method
	/**
	 * @param name
	 * @param value
	 * @param max
	 * @throws BiggerArgumentException if the given value, that has the given name, is bigger than the given max.
	 */
	public static void throwExceptionIfValueIsBigger(final String name, long value, final long max) {
		if (value > max) {
			throw new BiggerArgumentException(name, value, max);
		}
	}
	
	//static method
	/**
	 * @param name
	 * @param value
	 * @throws NegativeArgumentException if the given value, that has the given name, is negative.
	 */
	public static void throwExceptionIfValueIsNegative(final String name, final double value) {
		if (value < 0) {
			throw new NegativeArgumentException(name, value);
		}
	}
	
	//static method
	/**
	 * @param name
	 * @param value
	 * @throws NegativeArgumentException if the given value, that has the given name, is negative.
	 */
	public static void throwExceptionIfValueIsNegative(final String name, final int value) {
		if (value < 0) {
			throw new NegativeArgumentException(name, value);
		}
	}
	
	//static method
	/**
	 * @param name
	 * @param value
	 * @throws NegativeArgumentException if the given value, that has the given name, is negative.
	 */
	public static void throwExceptionIfValueIsNegative(final String name, final long value) {
		if (value < 0) {
			throw new NegativeArgumentException(name, value);
		}
	}
	
	//static method
	/**
	 * @param name
	 * @param value
	 * @param expectedValue
	 * @throws UnequalArgumentException
	 * if the given value, that has the given name, does not equal the given expectedValue.
	 */
	public static void throwExceptionIfValueIsNotEqual(
		final String name,
		final double value,
		final double expectedValue
	) {
		if (value != expectedValue) {
			throw new UnequalArgumentException(name, value, expectedValue);
		}
	}
	
	//static method
	/**
	 * @param name
	 * @param value
	 * @param expectedValue
	 * @throws UnequalArgumentException
	 * if the given value, that has the given name, does not equal the given expectedValue.
	 */
	public static void throwExceptionIfValueIsNotEqual(
		final String name,
		final int value,
		final int expectedValue
	) {
		if (value != expectedValue) {
			throw new UnequalArgumentException(name, value, expectedValue);
		}
	}
	
	//static method
	/**
	 * @param name
	 * @param value
	 * @param expectedValue
	 * @throws UnequalArgumentException
	 * if the given value, that has the given name, does not equal the given expectedValue.
	 */
	public static void throwExceptionIfValueIsNotEqual(
		final String name,
		final long value,
		final long expectedValue
	) {
		if (value != expectedValue) {
			throw new UnequalArgumentException(name, value, expectedValue);
		}
	}
	
	//static method
	/**
	 * @param name
	 * @param value
	 * @param min
	 * @param max
	 * @throws ArgumentIsOutOfRangeException if the given value, that has the given name, is not in the given range.
	 */
	public static void throwExceptionIfValueIsNotInRange(
		final String name,
		final double value,
		final double min,
		final double max
	) {
		if (value < min || value > max) {
			throw new ArgumentIsOutOfRangeException(name, value, min, max);
		}
	}
	
	//static method
	/**
	 * @param name
	 * @param value
	 * @param min
	 * @param max
	 * @throws ArgumentIsOutOfRangeException if the given value, that has the given name, is not in the given range.
	 */
	public static void throwExceptionIfValueIsNotInRange(
		final String name,
		final int value,
		final int min,
		final int max
	) {
		if (value < min || value > max) {
			throw new ArgumentIsOutOfRangeException(name, value, min, max);
		}
	}
	
	//static method
	/**
	 * @param name
	 * @param value
	 * @param min
	 * @param max
	 * @throws ArgumentIsOutOfRangeException if the given value, that has the given name, is not in the given range.
	 */
	public static void throwExceptionIfValueIsNotInRange(
		final String name,
		final long value,
		final long min,
		final long max
	) {
		if (value < min || value > max) {
			throw new ArgumentIsOutOfRangeException(name, value, min, max);
		}
	}
	
	//static method
	/**
	 * @param name
	 * @param value
	 * @throws NonPositiveArgumentException if the given value, that has the with given name, is not positive.
	 */
	public static void throwExceptionIfValueIsNotPositive(final String name, final double value) {
		if (value < 1) {
			throw new NonPositiveArgumentException(name, value);
		}
	}
	
	//static method
	/**
	 * @param name
	 * @param value
	 * @throws NonPositiveArgumentException if the given value, that has the with given name, is not positive.
	 */
	public static void throwExceptionIfValueIsNotPositive(final String name, final int value) {
		if (value < 1) {
			throw new NonPositiveArgumentException(name, value);
		}
	}
	
	//static method
	/**
	 * @param name
	 * @param value
	 * @throws NonPositiveArgumentException if the given value, that has the with given name, is not positive.
	 */
	public static void throwExceptionIfValueIsNotPositive(final String name, final long value) {
		if (value < 1) {
			throw new NonPositiveArgumentException(name, value);
		}
	}
	
	//static method
	/**
	 * @param name
	 * @param value
	 * @throws ArgumentIsNullException if the given value, that has the given name, is null.
	 */
	public static void throwExceptionIfValueIsNull(final String name, final Object value) {
		if (value == null) {
			throw new ArgumentIsNullException(name);
		}
	}
	
	//static method
	/**
	 * @param name
	 * @param value
	 * @param min
	 * @throws SmallerArgumentException if the given value, that has the given name, is smaller than the given min.
	 */
	public static void throwExceptionIfValueIsSmaller(final String name, final double value, final double min) {
		if (value < min) {
			throw new SmallerArgumentException(name, value, min);
		}
	}
	
	//static method
	/**
	 * @param name
	 * @param value
	 * @param min
	 * @throws SmallerArgumentException if the given value, that has the given name, is smaller than the given min.
	 */
	public static void throwExceptionIfValueIsSmaller(final String name, final int value, final int min) {
		if (value < min) {
			throw new SmallerArgumentException(name, value, min);
		}
	}
	
	//static method
	/**
	 * @param name
	 * @param value
	 * @param min
	 * @throws SmallerArgumentException if the given value, that has the given name, is smaller than the given min.
	 */
	public static void throwExceptionIfValueIsSmaller(final String name, final long value, final long min) {
		if (value < min) {
			throw new SmallerArgumentException(name, value, min);
		}
	}
	
	//static method
	/**
	 * @param name
	 * @param value
	 * @throws ArgumentIsZeroException if the given value, that has the given name, is 0.0.
	 */
	public static void throwExceptionIfValueIsZero(final String name, final double value) {
		if (value == 0) {
			throw new ArgumentIsZeroException(name, value);
		}
	}
	
	//static method
	/**
	 * @param name
	 * @param value
	 * @throws ArgumentIsZeroException if the given value, that has the given name, is 0.
	 */
	public static void throwExceptionIfValueIsZero(final String name, final int value) {
		if (value == 0) {
			throw new ArgumentIsZeroException(name, value);
		}
	}
	
	//static method
	/**
	 * @param name
	 * @param value
	 * @throws ArgumentIsZeroException if the given value, that has the given name, is 0.
	 */
	public static void throwExceptionIfValueIsZero(final String name, final long value) {
		if (value == 0) {
			throw new ArgumentIsZeroException(name, value);
		}
	}
	
	//constructor
	/**
	 * Prevents that an instance of the {@link SimpleValidator} can be created.
	 */
	private SimpleValidator() {}
}
