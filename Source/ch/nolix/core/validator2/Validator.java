//package declaration
package ch.nolix.core.validator2;

//Java import
import java.util.Vector;

//own imports
import ch.nolix.core.invalidArgumentException.FalseArgumentException;
import ch.nolix.core.invalidArgumentException.NullArgumentException;
import ch.nolix.core.invalidArgumentException.TrueArgumentException;

//class
/**
 * The validator provides functions to validate arguments.
 * Methods are called on an object, functions are not.
 * Of this class no instance can be created.
 * 
 * @author Silvan Wyss
 * @month 2016-11
 * @lines 270
 */
public final class Validator {
	
	//static method
	/**
	 * @param argument
	 * @return a new argument mediator for given argument.
	 */
	public static <A> GenericArgumentMediator<A> suppose(final A argument) {
		return new GenericArgumentMediator<A>(argument);
	}	
	
	//static method
	/**
	 * @param argument
	 * @return a new container mediator for the given argument.
	 */
	public static <A> ContainerMediator<A> suppose(final A[] argument) {
		return new ContainerMediator<A>(argument);
	}
	
	//static method
	/**
	 * @param argument
	 * @return a new double mediator for the given argument.
	 */
	public static DoubleMediator suppose(final double argument) {
		return new DoubleMediator(argument);
	}
	
	//static method
	/**
	 * @param argument
	 * @return a new container mediator for the given argument.
	 */
	public static ContainerMediator<Double> suppose(final double[] argument) {
		
		//Handles the case if the given argument is null.
		if (argument == null) {
			final Vector<Double> argumentVector = null;
			return new ContainerMediator<Double>(argumentVector);
		}
		
		//Handles the case if the given argument is not null.
		return new ContainerMediator<Double>(ArrayHelper.createIterable(argument));
	}
	
	//static method
	/**
	 * @param argument
	 * @return a new long mediator for the given argument.
	 */
	public static LongMediator suppose(final long argument) {
		return new LongMediator(argument);
	}
	
	//static method
	/**
	 * @param argument
	 * @return a new container mediator for the given argument.
	 */
	public static ContainerMediator<Long> suppose(final long[] argument) {
		
		//Handles the case if the given argument is null.
		if (argument == null) {
			final Vector<Long> argumentVector = null;
			return new ContainerMediator<Long>(argumentVector);
		}
		
		//Handles the case if the given argument is not null.
		return new ContainerMediator<Long>(ArrayHelper.createIterable(argument));
	}
	
	//static method
	/**
	 * @param argument
	 * @return a new string mediator for the given argument.
	 */
	public static StringMediator suppose(final String argument) {
		return new StringMediator(argument);
	}
	
	//static method
	/**
	 * @param argument
	 * @return a new container mediator for the given argument.
	 */
	public static ContainerMediator<String> suppose(final String[] argument) {
		return new ContainerMediator<String>(argument);
	}
	
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
	 * @throws NullArgumentException if the given arguments is null.
	 * @throws FalseArgumentException if one of the given arguments is false.
	 */
	public static void supposeThat(final boolean... arguments) {
		
		//Checks if the given arguments is not null.
		if (arguments == null) {
			throw new NullArgumentException("arguments");
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
	 * @throws NullArgumentException if the given arguments is null.
	 * @throws TrueArgumentException if one of the given arguments is true.
	 */
	public static void supposeThatNot(final boolean... arguments) {
		
		//Checks if the given arguments is not null.
		if (arguments == null) {
			throw new NullArgumentException("arguments");
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
	 * @throws NullArgumentException if the given arguments is null.
	 */
	public static MultiDoubleMediator supposeTheDoubles(final double... arguments) {		
		return new MultiDoubleMediator(arguments);
	}
	
	//static method
	/**
	 * @param arguments
	 * @return a new multi double mediator for the given arguments.
	 * @throws NullArgumentException if the given arguments is null.
	 */
	public static MultiDoubleMediator supposeTheDoubles(final Iterable<Double> arguments) {
		return new MultiDoubleMediator(arguments);
	}
	
	//static method
	/**
	 * @param arguments
	 * @return a new multi long mediator for the given arguments.
	 * @throws NullArgumentException if the given arguments is null.
	 */
	public static MultiLongMediator supposeTheLongs(final Iterable<Long> arguments) {
		return new MultiLongMediator(arguments);
	}
	
	//static method
	/**
	 * @param arguments
	 * @return a new multi long mediator for the given arguments.
	 * @throws NullArgumentException if the given arguments is null.
	 */
	public static MultiLongMediator supposeTheLongs(final long... arguments) {		
		return new MultiLongMediator(arguments);
	}
	
	//static method
	/**
	 * @param arguments
	 * @return a new multi argument mediator for the given arguments.
	 * @throws NullArgumentException if the given arguments is null.
	 */
	public static <E> MultiGenericArgumentMediator<E> supposeTheElements(final Iterable<E> arguments) {
		return new MultiGenericArgumentMediator<E>(arguments);
	}
	
	//static method
	/**
	 * @param arguments
	 * @return a new multi argument mediator for the given arguments.
	 * @throws NullArgumentException if the given arguments is null.
	 */
	public static <E> MultiGenericArgumentMediator<E> supposeTheElements(final E[] arguments) {
		return new MultiGenericArgumentMediator<E>(arguments);
	}
	
	//static method
	/**s
	 * @param arguments
	 * @return a new string container mediator for the given arguments.
	 * @throws NullArgumentException if the given arguments is null.
	 */
	public static MultiStringMediator supposeTheStrings(final Iterable<String> arguments) {
		return new MultiStringMediator(arguments);
	}
	
	//static method
	/**
	 * @param arguments
	 * @return a new multi string mediator for the given arguments.
	 * @throws NullArgumentException if the given arguments is null.
	 */
	public static MultiStringMediator supposeTheStrings(final String... arguments) {		
		return new MultiStringMediator(arguments);
	}
	
	//private constructor
	/**
	 * Avoids that an instance of this class can be created.
	 */
	private Validator() {}
}
