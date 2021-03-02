//package declaration
package ch.nolix.common.errorcontrol.validator;

//Java imports
import java.lang.reflect.Method;
import java.math.BigDecimal;

import ch.nolix.common.errorcontrol.invalidargumentexception.ArgumentIsNullException;
//own imports
import ch.nolix.common.independentcontainer.List;
import ch.nolix.common.independenthelper.ArrayHelper;

//class
/**
 * The validator provides functions to validate arguments.
 * Methods are called on objects, functions are called independently.
 * Of this class an instance cannot be created.
 * 
 * @author Silvan Wyss
 * @month 2016-11
 * @lines 260
 */
public final class Validator {
	
	//static method
	/**
	 * @param argument
	 * @param <A> is the type of the given argument.
	 * @return a new argument mediator for given argument.
	 */
	public static <A> ExtendedArgumentMediator<A> assertThat(final A argument) {
		return new ExtendedArgumentMediator<>(argument);
	}
	
	//static method
	/**
	 * @param argument
	 * @param <A> is the type of the elements of the given argument.
	 * @return a new extended container mediator for the given argument.
	 */
	public static <A> ExtendedContainerMediator<A> assertThat(final A[] argument) {
		return new ExtendedContainerMediator<>(argument);
	}
	
	//static method
	/**
	 * @param argument
	 * @return a new {@link ExtendedBigDecimalMediator} for the given argument.
	 */
	public static ExtendedBigDecimalMediator assertThat(final BigDecimal argument) {
		return new ExtendedBigDecimalMediator(argument);
	}
	
	//static method
	/**
	 * @param argument
	 * @return a new {@link ExtendedContainerMediator} for the given argument.
	 */
	public static ExtendedContainerMediator<Byte> assertThat(final byte[] argument) {
		return new ExtendedContainerMediator<>(ArrayHelper.createIterable(argument));
	}
	
	//static method
	/**
	 * @param argument 
	 * @param <T> is the type of the given argument
	 * @return a new {@link ExtendedTypeMediator} for the given argument.
	 */
	public static <T> ExtendedTypeMediator<T> assertThat(final Class<T> argument) {
		return new ExtendedTypeMediator<>(argument);
	}
	
	//static method
	/**
	 * @param argument
	 * @return a new extended double mediator for the given argument.
	 */
	public static ExtendedDoubleMediator assertThat(final double argument) {
		return new ExtendedDoubleMediator(argument);
	}
	
	//static method
	/**
	 * @param argument
	 * @return a new extended container mediator for the given argument.
	 */
	public static ExtendedContainerMediator<Double> assertThat(final double[] argument) {
		
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
	public static ExtendedLongMediator assertThat(final int argument) {
		return new ExtendedLongMediator(argument);
	}
	
	//static method
	/**
	 * @param argument
	 * @return a new extended container mediator for the given argument.
	 */
	public static ExtendedContainerMediator<Long> assertThat(final int[] argument) {
		
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
	 * @param <A> is the type of the elements of the given argument.
	 * @return a new extended container mediator for the given argument.
	 */
	public static <A> ExtendedContainerMediator<A> assertThat(final Iterable<A> argument) {
		return new ExtendedContainerMediator<>(argument);
	}
	
	//static method
	/**
	 * @param argument
	 * @return a new extended long mediator for the given argument.
	 */
	public static ExtendedLongMediator assertThat(final long argument) {
		return new ExtendedLongMediator(argument);
	}
	
	//static method
	/**
	 * @param argument
	 * @return a new extended container mediator for the given argument.
	 */
	public static ExtendedContainerMediator<Long> assertThat(final long[] argument) {
		
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
	 * @return a new {@link ExtendedMethodMediator} for the given argument.
	 */
	public static ExtendedMethodMediator assertThat(final Method argument) {
		return new ExtendedMethodMediator(argument);
	}
	
	//static method
	/**
	 * @param argument
	 * @return a new extended string mediator for the given argument.
	 */
	public static ExtendedStringMediator assertThat(final String argument) {
		return new ExtendedStringMediator(argument);
	}
	
	//static method
	/**
	 * @param argument
	 * @return a new extended container mediator for the given argument.
	 */
	public static ExtendedContainerMediator<String> assertThat(final String[] argument) {
		return new ExtendedContainerMediator<>(argument);
	}
	
	//static method
	/**
	 * @param arguments
	 * @return a new multi double mediator for the given arguments.
	 * @throws ArgumentIsNullException if the given arguments is null.
	 */
	public static MultiDoubleMediator assertThatTheDoubles(final double... arguments) {
		return new MultiDoubleMediator(arguments);
	}
	
	//static method
	/**
	 * @param arguments
	 * @return a new multi double mediator for the given arguments.
	 * @throws ArgumentIsNullException if the given arguments is null.
	 */
	public static MultiDoubleMediator assertThatTheDoubles(final Iterable<Double> arguments) {
		return new MultiDoubleMediator(arguments);
	}
	
	//static method
	/**
	 * @param arguments
	 * @param <E> is the type of the given arguments.
	 * @return a new multi argument mediator for the given arguments.
	 * @throws ArgumentIsNullException if the given arguments is null.
	 */
	public static <E> MultiArgumentMediator<E> assertThatTheElements(final E[] arguments) {
		return new MultiArgumentMediator<>(arguments);
	}
	
	//static method
	/**
	 * @param arguments
	 * @param <E> is the type of the given arguments.
	 * @return a new multi argument mediator for the given arguments.
	 * @throws ArgumentIsNullException if the given arguments is null.
	 */
	public static <E> MultiArgumentMediator<E> assertThatTheElements(final Iterable<E> arguments) {
		return new MultiArgumentMediator<>(arguments);
	}
	
	//static method
	/**
	 * @param arguments
	 * @return a new multi long mediator for the given arguments.
	 * @throws ArgumentIsNullException if the given arguments is null.
	 */
	public static MultiLongMediator assertThatTheLongs(final long... arguments) {
		return new MultiLongMediator(arguments);
	}
	
	//static method
	/**
	 * @param arguments
	 * @return a new multi long mediator for the given arguments.
	 * @throws ArgumentIsNullException if the given arguments is null.
	 */
	public static MultiLongMediator assertThatTheLongs(final Iterable<Long> arguments) {
		return new MultiLongMediator(arguments);
	}
	
	//static method
	/**
	 * @param arguments
	 * @return a new multi string mediator for the given arguments.
	 * @throws ArgumentIsNullException if the given arguments is null.
	 */
	public static MultiStringMediator assertThatTheStrings(final String... arguments) {
		return new MultiStringMediator(arguments);
	}
	
	//static method
	/**
	 * @param arguments
	 * @return a new string container mediator for the given arguments.
	 * @throws ArgumentIsNullException if the given arguments is null.
	 */
	public static MultiStringMediator assertThatTheStrings(final Iterable<String> arguments) {
		return new MultiStringMediator(arguments);
	}
	
	//visibility-reduced constructor
	/**
	 * Avoids that an instance of this class can be created.
	 */
	private Validator() {}
}
