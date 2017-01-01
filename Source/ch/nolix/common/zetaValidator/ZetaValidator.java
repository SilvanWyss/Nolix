/*
 * file:	AlphaValidator.java
 * author:	Silvan Wyss
 * month:	2016-11
 * lines:	220
 */

//package declaration
package ch.nolix.common.zetaValidator;

//Java import
import java.util.Vector;

import ch.nolix.common.exception.FalseArgumentException;

//class
/**
 * This class provides some functions to validate arguments.
 */
public final class ZetaValidator {
	
	//static method
	/**
	 * @param argument		The argument that is supposed to be true.
	 * @throws RuntimeException if the given argument is false
	 */
	public static void supposeThat(final boolean argument) {
		
		//Checks the given argument.
		if (!argument) {
			throw new RuntimeException("The argument is false.");
		}
	}
	
	//static method
	/** 
	 * @param arguments		The arguments that are supposed to be true.
	 * @throws RuntimeException if one of the given arguments is false
	 */
	public static void supposeThat(final boolean... arguments) {
		
		//Iterates the given arguments.
		int i = 1;
		for (boolean a: arguments) {
						
			if (!a) {
				throw new FalseArgumentException(i + "th argument");
			}
			
			i++;
		}
	}
	
	//static method
	/**
	 * @param argument		The argument that is supposed to fulfil certain conditions.
	 * @return a new double mediator with the given argument
	 */
	public static DoubleMediator supposeThat(final double argument) {
		return new DoubleMediator(argument);
	}
	
	//static method
	/**
	 * @param arguments		The arguments that are supposed to fulfil certain conditions.
	 * @return a new double container mediator with the given arguments
	 */
	public static DoubleContainerMediator supposeThat(final double... arguments) {
		
		//Creates argument vector.
		final Vector<Double> argumentVector = new Vector<Double>();
		for (double a: arguments) {
			argumentVector.add(a);
		}
		
		return new DoubleContainerMediator(argumentVector);
	}

	//static method
	/**
	 * @param argument		The argument that is supposed to fulfil certain conditions.
	 * @return a new long mediator with the given argument
	 */
	public static LongMediator supposeThat(final int argument) {
		return new LongMediator(argument);
	}
	
	//static method
	/**
	 * @param arguments		The arguments that are supposed to fulfil certain conditions.
	 * @return a new long container mediator with the given arguments
	 */
	public static LongContainerMediator supposeThat(final int...arguments) {
		
		//Creates argument vector.
		final Vector<Long> argumentVector = new Vector<Long>();
		for (int a: arguments) {
			argumentVector.add(Long.valueOf(a));
		}
		
		return new LongContainerMediator(argumentVector);
	}
	
	//static method
	/**
	 * @param argument		The argument that is supposed to fulfil certain conditions.
	 * @return a new long mediator with the given argument
	 */
	public static LongMediator supposeThat(final long argument) {
		return new LongMediator(argument);
	}
	
	//static method
	/**
	 * @param argument		The argument that is supposed to fulfil certain conditions.
	 * @return a new string mediator with the given argument
	 */
	public static StringMediator supposeThat(final String argument) {
		return new StringMediator(argument);
	}
	
	//static method
	/**
	 * @param arguments		The arguments that are supposed to fulfil certain conditions.
	 * @return a new string container mediator with the given arguments
	 */
	public static StringContainerMediator supposeThat(final String... arguments) {
		
		//Creates argument vector.
		final Vector<String> argumentVector = new Vector<String>();
		for (String a: arguments) {
			argumentVector.add(a);
		}
		
		return new StringContainerMediator(argumentVector);
	}
	
	//static method
	/**
	 * @param argument		The argument that is supposed to fulfil certain conditions.
	 * @return a new object mediator with the given argument
	 */
	public static ObjectMediator supposeThat(final Object argument) {
		return new ObjectMediator(argument);
	}
	
	//static method
	/**
	 * @param arguments		The arguments that are supposed to fulfil certain conditions.
	 * @return a new object container mediator with the given arguments
	 */
	public static ObjectContainerMediator supposeThat2(final Object... arguments) {
		
		//Creates argument vector.
		final Vector<Object> argumentVector = new Vector<Object>();
		for (Object a: arguments) {
			argumentVector.add(a);
		}
		
		return new ObjectContainerMediator(argumentVector);
	}
	
	//static method
	/**
	 * @param arguments		The arguments that are supposed to fulfil certain conditions.
	 * @return a new object container mediator with the given arguments
	 */
	public static ObjectContainerMediator supposeThat(final Iterable<Object> arguments) {
		return new ObjectContainerMediator(arguments);
	}
	
	//static method
	/**
	 * @param argument		The argument that is supposed to be false.
	 * @throws RuntimeException if the given argument is true
	 */
	public static void supposeThatNot(final boolean argument) {
		
		//Checks the given argument.
		if (argument) {
			throw new RuntimeException("The argument is true.");
		}
	}
	
	//static method
	/** 
	 * @param arguments		The arguments that are supposed to be false.
	 * @throws RuntimeException if one of the given arguments is true
	 */
	public static void supposeThatNot(final boolean... arguments) {
		
		//Iterates the given arguments.
		for (boolean a: arguments) {
			supposeThatNot(a);
		}
	}
	
	//static method
	/**
	 * @param arguments		The arguments that are supposed to fulfil certain conditions.
	 * @return a new double container mediator with the given arguments
	 */
	public static DoubleContainerMediator supposeThatTheDoubles(final Iterable<Double> arguments) {
		return new DoubleContainerMediator(arguments);
	}
	
	//static method
	/**
	 * @param arguments		The arguments that are supposed to fulfil certain conditions.
	 * @return a new long container mediator with the given arguments
	 */
	public static LongContainerMediator supposeThatTheLongs(final Iterable<Long> arguments) {
		return new LongContainerMediator(arguments);
	}
	
	//static method
	/**s
	 * @param arguments		The arguments that are supposed to fulfil certain conditions.
	 * @return a new string container mediator with the given arguments
	 */
	public static StringContainerMediator supposeThatTheStrings(final Iterable<String> arguments) {
		return new StringContainerMediator(arguments);
	}
	
	//private constructor
	/**
	 * Avoids that an instance of this class can be created.
	 */
	private ZetaValidator() {}
}
