//package declaration
package ch.nolix.core.testing.test;

//Java imports
import java.util.ArrayList;

//own imports
import ch.nolix.core.testing.validation.BitMediator;
import ch.nolix.core.testing.validation.ByteMediator;
import ch.nolix.core.testing.validation.ClosureMediator;
import ch.nolix.core.testing.validation.ContainerMediator;
import ch.nolix.core.testing.validation.DoubleMediator;
import ch.nolix.core.testing.validation.LongMediator;
import ch.nolix.core.testing.validation.MultiDoubleMediator;
import ch.nolix.core.testing.validation.MultiLongMediator;
import ch.nolix.core.testing.validation.StringMediator;
import ch.nolix.core.testing.validation.ValueMediator;
import ch.nolix.coreapi.functionapi.genericfunctionapi.IAction;

//class
/**
 * A test is a test that provides the fluent pattern for writing expectations.
 * 
 * @author Silvan Wyss
 * @date 2016-09-01
 */
public abstract class Test extends ch.nolix.core.testing.basetest.BaseTest {
	
	//method
	/**
	 * Generates an error if the given value is false.
	 * 
	 * @param value
	 */
	protected final void expect(final boolean value) {
		
		//Handles the case that the given value is false.
		if (!value) {
			addExpectationError("True was expected, but false was received.");
		}
	}
	
	//method
	/**
	 * Generates an error for all of the given values that are false.
	 * 
	 * @param values
	 */
	protected final void expect(final boolean... values) {
		
		//Handles the case that the given values is null.
		if (values == null) {
			addExpectationError("True values were expected, but null was received.");
		}
		
		//Handles the case that the given values is not null.
		
			//Iterates the given values.
			int index = 1;
			for (final boolean b: values) {
				
				//Handles the case that the current value is false.
				if (!b) {
					addExpectationError("True values were expected, but the " + index + "th value is false.");
				}
				
				//Increments index.
				index++;
			}
	}
	
	//method
	/**
	 * @param value
	 * @return a new {@link ByteMediator} for the given value.
	 */
	public final ByteMediator expect(final byte value) {
		return new ByteMediator(this::addExpectationError, value);
	}
	
	//method
	/**
	 * @param value
	 * @return a new double mediator that belongs to this test and is for the given value.
	 */
	protected final DoubleMediator expect(final double value) {
		return new DoubleMediator(this::addExpectationError, value);
	}
		
	//method
	/**
	 * @param value
	 * @return a new double mediator that belongs to this test and is for the given value.
	 */
	protected final DoubleMediator expect(final Double value) {
		return new DoubleMediator(this::addExpectationError, value);
	}

	//method
	/**
	 * @param value
	 * @return a new long mediator that belongs to this test and is for the given value.
	 */
	protected final LongMediator expect(final int value) {
		return new LongMediator(this::addExpectationError, value);
	}
	
	//method
	/**
	 * @param value
	 * @return a new long mediator that belongs to this test and is for the given value.
	 */
	protected final LongMediator expect(final Integer value) {
		return new LongMediator(this::addExpectationError, value);
	}
	
	//method
	/**
	 * @param container
	 * @param <E> is the type of the elements of the given container.
	 * @return a new container mediator that belongs to this test and is for the given container.
	 */
	protected final <E> ContainerMediator<E> expect(final Iterable<E> container) {
		return new ContainerMediator<>(this::addExpectationError, container);
	}
	
	//method
	/**
	 * @param value
	 * @return a new long mediator that belongs to this test and is for the given value.
	 */
	protected final LongMediator expect(final long value) {
		return new LongMediator(this::addExpectationError, value);
	}
	
	//method
	/**
	 * @param value
	 * @return a new long mediator that belongs to this test and is for the given value.
	 */
	protected final LongMediator expect(final Long value) {
		return new LongMediator(this::addExpectationError, value);
	}
	
	//method
	/**
	 * @param value
	 * @param <V> is the type of the given value.
	 * @return a new object mediator that belongs to this test and has the given value.
	 */
	protected final <V> ValueMediator<V> expect(final V value) {
		return new ValueMediator<>(this::addExpectationError, value);
	}
	
	//method
	/**
	 * @param value
	 * @return a new string mediator that belongs to this nolix test and has the given value.
	 */
	protected final StringMediator expect(final String value) {
		return new StringMediator(this::addExpectationError, value);
	}

	//method
	/**
	 * Generates an error if the given value is true.
	 * 
	 * @param value
	 */
	protected final void expectNot(final boolean value) {
		
		//Handles the case that the given value is true.
		if (value) {
			addExpectationError("False was expected, but true was received.");
		}
	}
	
	//method
	/**
	 * Generates an error for all of the given values that are true.
	 * 
	 * @param values
	 */
	protected final void expectNot(final boolean... values) {
		
		//Handles the case that the given values is null.
		if (values == null) {
			addExpectationError("False values were expected, but null was received.");
		}
		
		//Handles the case that the given values is not null.
		
			//Iterates the given values.
			int index = 1;
			for (final boolean v: values) {
				
				//Handles the case that the current value is false.
				if (v) {
					addExpectationError("False values were expected, but the " + index + "th value is true.");
				}
				
				//Increments index.
				index++;
			}
	}
	
	//method
	/**
	 * @param closure
	 * @return a new closure mediator that belongs to this test and is for the given closure.
	 */
	protected final ClosureMediator expectRunning(final IAction closure) {
		return new ClosureMediator(this::addExpectationError, closure);
	}
	
	//method
	/**
	 * @param bit
	 * @return a new {@link BitMediator} that belongs to the current {@link Test} and is for the given bit.
	 */
	protected final BitMediator expectTheBit(final boolean bit) {
		return new BitMediator(this::addExpectationError, bit);
	}
	
	//method
	/**
	 * @param bit
	 * @return a new {@link BitMediator} that belongs to the current {@link Test} and is for the given bit.
	 */
	protected final BitMediator expectTheBit(final int bit) {
		return new BitMediator(this::addExpectationError, bit);
	}
	
	//method
	/**
	 * @param values
	 * @return a new multi double mediator that belongs to this test and is for the given values.
	 */
	protected final MultiDoubleMediator expectTheDoubles(final double... values) {
		return new MultiDoubleMediator(this::addExpectationError, values);
	}
	
	//method
	/**
	 * @param values
	 * @return a new multi double mediator that belongs to this test and is for the given values.
	 */
	protected final MultiDoubleMediator expectTheDoubles(Iterable<Double> values) {
		return new MultiDoubleMediator(this::addExpectationError, values);
	}
	
	//method
	/**
	 * @param values
	 * @return a new multi long mediator that belongs to this test and is for the given values.
	 */
	protected final MultiLongMediator expectTheInts(final int... values) {
		return new MultiLongMediator(this::addExpectationError, values);
	}
	
	//method
	/**
	 * @param values
	 * @return a new long container mediator that belongs to this test and has the given values.
	 */
	protected final MultiLongMediator expectTheLongs(final Iterable<Long> values) {
		return new MultiLongMediator(this::addExpectationError, values);
	}
	
	//method
	/**
	 * @param values
	 * @return a new long container mediator that belongs to this test and has the given values.
	 */
	protected final MultiLongMediator expectTheLongs(final long... values) {
		
		//Creates long vector.
		final var longArrayList = new ArrayList<Long>();
		for (long v: values) {
			longArrayList.add(v);
		}
		
		return expectTheLongs(longArrayList);
	}
}
