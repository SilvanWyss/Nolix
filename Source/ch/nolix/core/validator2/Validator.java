//package declaration
package ch.nolix.core.validator2;

//Java import
import java.util.Vector;

//own import


import ch.nolix.core.invalidArgumentException.FalseArgumentException;
import ch.nolix.core.invalidArgumentException.TrueArgumentException;

//class
/**
 * This class provides some functions to validate arguments.
 * 
 * @author Silvan Wyss
 * @month 2016-11
 * @lines 220
 */
public final class Validator {
	
	//static method
	/**
	 * @param argument
	 * @throws FalseArgumentException if the given argument is false.
	 */
	public static void supposeThat(final boolean argument) {
		
		//Checks if the given argument is true.
		if (!argument) {
			throw new FalseArgumentException();
		}
	}
	
	//static method
	/** 
	 * @param arguments
	 * @throws FalseArgumentException if one of the given arguments is false.
	 */
	public static void supposeThat(final boolean... arguments) {
		
		//Iterates the given arguments.
		int i = 1;
		for (boolean a: arguments) {
				
			//Checks if the current argument is true.
			if (!a) {
				throw new FalseArgumentException(i + "th argument");
			}
			
			i++;
		}
	}
	
	//static method
	/**
	 * @param argument
	 * @return a new double mediator with the given argument.
	 */
	public static DoubleMediator supposeThat(final double argument) {
		return new DoubleMediator(argument);
	}
	
	//static method
	/**
	 * @param arguments
	 * @return a new double container mediator with the given arguments.
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
	 * @param argument
	 * @return a new long mediator with the given argument.
	 */
	public static LongMediator supposeThat(final int argument) {
		return new LongMediator(argument);
	}
	
	//static method
	/**
	 * @param arguments
	 * @return a new long container mediator with the given arguments.
	 */
	public static LongContainerMediator supposeThat(final int... arguments) {
		
		//Creates argument vector.
		final Vector<Long> argumentVector = new Vector<Long>();
		for (int a: arguments) {
			argumentVector.add(Long.valueOf(a));
		}
		
		return new LongContainerMediator(argumentVector);
	}
	
	//static method
	/**
	 * @param argument
	 * @return a new long mediator with the given argument.
	 */
	public static LongMediator supposeThat(final long argument) {
		return new LongMediator(argument);
	}
	
	//static method
	/**
	 * @param argument
	 * @return a new string mediator with the given argument.
	 */
	public static StringMediator supposeThat(final String argument) {
		return new StringMediator(argument);
	}
	
	//static method
	/**
	 * @param arguments
	 * @return a new string container mediator with the given arguments.
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
	 * @param argument
	 * @return a new object mediator with the given argument.
	 */
	public static ObjectMediator supposeThat(final Object argument) {
		return new ObjectMediator(argument);
	}
	
	//static method
	/**
	 * @param argument
	 * @throws TrueException if the given argument is true.
	 */
	public static void supposeThatNot(final boolean argument) {
		
		//Checks if the given argument is false.
		if (argument) {
			throw new TrueArgumentException();
		}
	}
	
	//static method
	/** 
	 * @param arguments
	 * @throws TrueArgumentException if one of the given arguments is true.
	 */
	public static void supposeThatNot(final boolean... arguments) {
		
		//Iterates the given arguments.
		int i = 1;
		for (boolean a: arguments) {
				
			//Checks if the current argument is false.
			if (a) {
				throw new TrueArgumentException(i + "th argument");
			}
			
			i++;
		}
	}
	
	//static method
	/**
	 * @param arguments
	 * @return a new double container mediator with the given arguments.
	 */
	public static DoubleContainerMediator supposeThatTheDoubles(final Iterable<Double> arguments) {
		return new DoubleContainerMediator(arguments);
	}
	
	//static method
	/**
	 * @param arguments
	 * @return a new long container mediator with the given arguments.
	 */
	public static LongContainerMediator supposeThatTheLongs(final Iterable<Long> arguments) {
		return new LongContainerMediator(arguments);
	}
	
	//static method
	/**
	 * @param arguments
	 * @return a new object container mediator with the given arguments.
	 */
	public static ArgumentContainerMediator supposeThatTheObjects(final Iterable<Object> arguments) {
		return new ArgumentContainerMediator(arguments);
	}
	
	//static method
	/**s
	 * @param arguments
	 * @return a new string container mediator with the given arguments.
	 */
	public static StringContainerMediator supposeThatTheStrings(final Iterable<String> arguments) {
		return new StringContainerMediator(arguments);
	}
	
	//private constructor
	/**
	 * Avoids that an instance of this class can be created.
	 */
	private Validator() {}
}
