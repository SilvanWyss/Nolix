//package declaration
package ch.nolix.common.validator;

//Java import
import java.math.BigDecimal;

import ch.nolix.common.independentContainers.List;
import ch.nolix.common.independentHelpers.ArrayHelper;
import ch.nolix.common.invalidArgumentExceptions.FalseArgumentException;
import ch.nolix.common.invalidArgumentExceptions.ArgumentIsNullException;
import ch.nolix.common.invalidArgumentExceptions.TrueArgumentException;

//class
/**
 * The validator provides functions to validate arguments.
 * Methods are called on objects, functions are called independently.
 * Of this class an instance cannot be created.
 * 
 * @author Silvan Wyss
 * @month 2016-11
 * @lines 330
 */
public final class Validator {
	
	//static method
	/**
	 * @param argument
	 * @return a new argument mediator for given argument.
	 */
	public static <A> ExtendedArgumentMediator<A> suppose(final A argument) {
		return new ExtendedArgumentMediator<>(argument);
	}
	
	//static method
	/**
	 * @param argument
	 * @return a new extended container mediator for the given argument.
	 */
	public static <A> ExtendedContainerMediator<A> suppose(final A[] argument) {
		return new ExtendedContainerMediator<>(argument);
	}
	
	//static method
	/**
	 * @param argument
	 * @return a new {@link ExtendedBigDecimalValidator} for the given argument.
	 */
	public static ExtendedBigDecimalValidator suppose(final BigDecimal argument) {
		return new ExtendedBigDecimalValidator(argument);
	}
	
	//static method
	/**
	 * @param argument
	 * @throws FalseArgumentException if the given argument is false.
	 */
	public static void suppose(final boolean argument) {
		
		//Checks if the given argument is true.
		if (!argument) {
			throw new FalseArgumentException(argument);
		}
	}
	
	//static method
	/** 
	 * @param arguments
	 * @throws ArgumentIsNullException if the given arguments is null.
	 * @throws FalseArgumentException if one of the given arguments is false.
	 */
	public static void suppose(final boolean... arguments) {
		
		//Checks if the given arguments is not null.
		if (arguments == null) {
			throw new ArgumentIsNullException("arguments");
		}
		
		//Iterates the given arguments.
		int i = 1;
		for (final boolean a: arguments) {
				
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
	 * @return a new {@link ExtendedTypeMediator} for the given argument.
	 */
	public static <T> ExtendedTypeMediator<T> suppose(final Class<T> argument) {
		return new ExtendedTypeMediator<>(argument);
	}
	
	//static method
	/**
	 * @param argument
	 * @return a new extended double mediator for the given argument.
	 */
	public static ExtendedDoubleMediator suppose(final double argument) {
		return new ExtendedDoubleMediator(argument);
	}
	
	//static method
	/**
	 * @param argument
	 * @return a new extended container mediator for the given argument.
	 */
	public static ExtendedContainerMediator<Double> suppose(final double[] argument) {
		
		//Handles the case that the given argument is null.
		if (argument == null) {
			final List<Double> argumentVector = null;
			return new ExtendedContainerMediator<>(argumentVector);
		}
		
		//Handles the case that the given argument is not null.
		return new ExtendedContainerMediator<>(ArrayHelper.createIterable(argument));
	}
	
	//static method
	/**
	 * @param argument
	 * @return a new extended long mediator for the given argument.
	 */
	public static ExtendedLongMediator suppose(final int argument) {
		return new ExtendedLongMediator(argument);
	}
	
	//static method
	/**
	 * @param argument
	 * @return a new extended container mediator for the given argument.
	 */
	public static ExtendedContainerMediator<Long> suppose(final int[] argument) {
		
		//Handles the case that the given argument is null.
		if (argument == null) {
			final List<Long> argumentVector = null;
			return new ExtendedContainerMediator<>(argumentVector);
		}
		
		//Handles the case that the given argument is not null.
		return new ExtendedContainerMediator<>(ArrayHelper.createIterable(argument));
	}
	
	//static method
	/**
	 * @param argument
	 * @return a new extended container mediator for the given argument.
	 */
	public static <A> ExtendedContainerMediator<A> suppose(final Iterable<A> argument) {
		return new ExtendedContainerMediator<>(argument);
	}
	
	//static method
	/**
	 * @param argument
	 * @return a new extended long mediator for the given argument.
	 */
	public static ExtendedLongMediator suppose(final long argument) {
		return new ExtendedLongMediator(argument);
	}
	
	//static method
	/**
	 * @param argument
	 * @return a new extended container mediator for the given argument.
	 */
	public static ExtendedContainerMediator<Long> suppose(final long[] argument) {
		
		//Handles the case that the given argument is null.
		if (argument == null) {
			final List<Long> argumentVector = null;
			return new ExtendedContainerMediator<>(argumentVector);
		}
		
		//Handles the case that the given argument is not null.
		return new ExtendedContainerMediator<>(ArrayHelper.createIterable(argument));
	}
	
	//static method
	/**
	 * @param argument
	 * @return a new extended string mediator for the given argument.
	 */
	public static ExtendedStringMediator suppose(final String argument) {
		return new ExtendedStringMediator(argument);
	}
	
	//static method
	/**
	 * @param argument
	 * @return a new extended container mediator for the given argument.
	 */
	public static ExtendedContainerMediator<String> suppose(final String[] argument) {
		return new ExtendedContainerMediator<>(argument);
	}
	
	//static method
	/**
	 * @param argument
	 * @throws TrueException if the given argument is true.
	 */
	public static void supposeNot(final boolean argument) {
		
		//Checks if the given argument is false.
		if (argument) {
			throw new TrueArgumentException(argument);
		}
	}
	
	//static method
	/** 
	 * @param arguments
	 * @throws ArgumentIsNullException if the given arguments is null.
	 * @throws TrueArgumentException if one of the given arguments is true.
	 */
	public static void supposeNot(final boolean... arguments) {
		
		//Checks if the given arguments is not null.
		if (arguments == null) {
			throw new ArgumentIsNullException("arguments");
		}
		
		//Iterates the given arguments.
		int i = 1;
		for (final boolean a : arguments) {
				
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
	 * @return a new multi double mediator for the given arguments.
	 * @throws ArgumentIsNullException if the given arguments is null.
	 */
	public static MultiDoubleMediator supposeTheDoubles(final double... arguments) {
		return new MultiDoubleMediator(arguments);
	}
	
	//static method
	/**
	 * @param arguments
	 * @return a new multi double mediator for the given arguments.
	 * @throws ArgumentIsNullException if the given arguments is null.
	 */
	public static MultiDoubleMediator supposeTheDoubles(final Iterable<Double> arguments) {
		return new MultiDoubleMediator(arguments);
	}
	
	//static method
	/**
	 * @param arguments
	 * @return a new multi argument mediator for the given arguments.
	 * @throws ArgumentIsNullException if the given arguments is null.
	 */
	public static <E> MultiArgumentMediator<E> supposeTheElements(final E[] arguments) {
		return new MultiArgumentMediator<>(arguments);
	}
	
	//static method
	/**
	 * @param arguments
	 * @return a new multi argument mediator for the given arguments.
	 * @throws ArgumentIsNullException if the given arguments is null.
	 */
	public static <E> MultiArgumentMediator<E> supposeTheElements(final Iterable<E> arguments) {
		return new MultiArgumentMediator<>(arguments);
	}
	
	//static method
	/**
	 * @param arguments
	 * @return a new multi long mediator for the given arguments.
	 * @throws ArgumentIsNullException if the given arguments is null.
	 */
	public static MultiLongMediator supposeTheLongs(final long... arguments) {
		return new MultiLongMediator(arguments);
	}
	
	//static method
	/**
	 * @param arguments
	 * @return a new multi long mediator for the given arguments.
	 * @throws ArgumentIsNullException if the given arguments is null.
	 */
	public static MultiLongMediator supposeTheLongs(final Iterable<Long> arguments) {
		return new MultiLongMediator(arguments);
	}
	
	//static method
	/**
	 * @param arguments
	 * @return a new multi string mediator for the given arguments.
	 * @throws ArgumentIsNullException if the given arguments is null.
	 */
	public static MultiStringMediator supposeTheStrings(final String... arguments) {
		return new MultiStringMediator(arguments);
	}
	
	//static method
	/**
	 * @param arguments
	 * @return a new string container mediator for the given arguments.
	 * @throws ArgumentIsNullException if the given arguments is null.
	 */
	public static MultiStringMediator supposeTheStrings(final Iterable<String> arguments) {
		return new MultiStringMediator(arguments);
	}
	
	//private constructor
	/**
	 * Avoids that an instance of this class can be created.
	 */
	private Validator() {}
}
