//package declaration
package ch.nolix.common.test;

//Java import
import java.util.ArrayList;

//own imports
import ch.nolix.common.baseTest.TestAccessor;
import ch.nolix.common.functionAPI.IFunction;
import ch.nolix.common.skillAPI.ApproximativeEqualing;

//class
/**
 * A test is a test that provides the fluent pattern for writing expectations.
 * 
 * @author Silvan Wyss
 * @month 2016-08
 * @lines 260
 */
public abstract class Test extends ch.nolix.common.baseTest.BaseTest {
	
	//method
	/**
	 * @param value
	 * @return a new approximative equaling mediator that belongs to this test and has the given value.
	 */
	protected final ApproximativeEqualingMediator expect(final ApproximativeEqualing value) {
		return new ApproximativeEqualingMediator(this, value);
	}
	
	//method
	/**
	 * Generates an error if the given value is false.
	 * 
	 * @param value
	 */
	protected final void expect(final boolean value) {
		
		//Handles the case that the given value is false.
		if (!value) {
			new TestAccessor(this).addExpectationError("True was expected, but false was received.");
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
			new TestAccessor(this).addExpectationError("True values were expected, but null was received.");
		}
		
		//Handles the case that the given values is not null.
		
			//Iterates the given values.
			int index = 1;
			for (final boolean b: values) {
				
				//Handles the case that the current value is false.
				if (!b) {
					new TestAccessor(this)
					.addExpectationError("True values were expected, but the " + index + "th value is false.");
				}
				
				//Increments index.
				index++;
			}
	}
	
	//method
	/**
	 * @param value
	 * @return a new double mediator that belongs to this test and is for the given value.
	 */
	protected final DoubleMediator expect(final double value) {
		return new DoubleMediator(this, value);
	}
		
	//method
	/**
	 * @param value
	 * @return a new double mediator that belongs to this test and is for the given value.
	 */
	protected final DoubleMediator expect(final Double value) {
		return new DoubleMediator(this, value);
	}

	//method
	/**
	 * @param value
	 * @return a new long mediator that belongs to this test and is for the given value.
	 */
	protected final LongMediator expect(final int value) {
		return new LongMediator(this, value);
	}
	
	//method
	/**
	 * @param value
	 * @return a new long mediator that belongs to this test and is for the given value.
	 */
	protected final LongMediator expect(final Integer value) {
		return new LongMediator(this, value);
	}
	
	//test case
	/**
	 * @param closure
	 * @return a new closure mediator that belongs to this test and is for the given closure.
	 */
	protected final ClosureMediator expect(final IFunction closure) {
		return new ClosureMediator(this, closure);
	}
	
	//method
	/**
	 * @param value
	 * @return a new long mediator that belongs to this test and is for the given value.
	 */
	protected final LongMediator expect(final long value) {
		return new LongMediator(this, value);
	}
	
	//method
	/**
	 * @param value
	 * @return a new long mediator that belongs to this test and is for the given value.
	 */
	protected final LongMediator expect(final Long value) {
		return new LongMediator(this, value);
	}
	
	//method
	/**
	 * @param value
	 * @return a new object mediator that belongs to this test and has the given value.
	 */
	protected final <V> ValueMediator<V> expect(final V value) {
		return new ValueMediator<>(this, value);
	}
	
	//method
	/**
	 * @param value
	 * @return a new string mediator that belongs to this nolix test and has the given value.
	 */
	protected final StringMediator expect(final String value) {
		return new StringMediator(this, value);
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
			new TestAccessor(this).addExpectationError("False was expected, but true was received.");
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
			new TestAccessor(this).addExpectationError("False values were expected, but null was received.");
		}
		
		//Handles the case that the given values is not null.
		
			//Iterates the given values.
			int index = 1;
			for (final boolean v: values) {
				
				//Handles the case that the current value is false.
				if (v) {
					new TestAccessor(this)
					.addExpectationError("False values were expected, but the " + index + "th value is true.");
				}
				
				//Increments index.
				index++;
			}
	}
	
	//method
	/**
	 * @param bit
	 * @return a new {@link BitMediator} that belongs to the current {@link Test} and is for the given bit.
	 */
	protected final BitMediator expectTheBit(final boolean bit) {
		return new BitMediator(this, bit);
	}
	
	//method
	/**
	 * @param bit
	 * @return a new {@link BitMediator} that belongs to the current {@link Test} and is for the given bit.
	 */
	protected final BitMediator expectTheBit(final int bit) {
		return new BitMediator(this, bit);
	}
	
	//method
	/**
	 * @param values
	 * @return a new multi double mediator that belongs to this test and is for the given values.
	 */
	protected final MultiDoubleMediator expectTheDoubles(final double... values) {
		return new MultiDoubleMediator(this, values);
	}
	
	//method
	/**
	 * @param values
	 * @return a new multi double mediator that belongs to this test and is for the given values.
	 */
	protected final MultiDoubleMediator expectTheDoubles(Iterable<Double> values) {
		return new MultiDoubleMediator(this, values);
	}
	
	//method
	/**
	 * @param values
	 * @return a new multi long mediator that belongs to this test and is for the given values.
	 */
	protected final MultiLongMediator expectTheInts(final int... values) {
		return new MultiLongMediator(this, values);
	}
	
	//method
	/**
	 * @param values
	 * @return a new long container mediator that belongs to this test and has the given values.
	 */
	protected final MultiLongMediator expectTheLongs(final Iterable<Long> values) {
		return new MultiLongMediator(this, values);
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
