//package declaration
package ch.nolix.core.test2;

//Java import
import java.util.Vector;




//own imports

import ch.nolix.core.functional.IRunner;
import ch.nolix.core.interfaces.ApproximativeEqualing;
import ch.nolix.core.test.Accessor;
import ch.nolix.core.test.Test;

//abstract class
/**
 * A zeta test is a test that provides the fluent pattern for writing expectations.
 * 
 * @author Silvan Wyss
 * @month 2016-08
 * @lines 150
 */
public abstract class ZetaTest extends Test {
		
	//method
	/**
	 * Generates an error if the given value is false.
	 * 
	 * @param value
	 */
	protected final void expectThat(final boolean value) {
		if (!value) {
			new Accessor(this).addCurrentTestMethodError("True was expected, but false was received.");
		}
	}
	
	//method
	/**
	 * Generates an error for all of the given values that are false.
	 * 
	 * @param values
	 */
	protected final void expectThat(final boolean... values) {
		for (boolean b: values) {
			expectThat(b);
		}
	}

	//method
	/**
	 * Generates an error if the given value is true.
	 * 
	 * @param value
	 */
	protected final void expectThatNot(final boolean value) {
		if (value) {
			new Accessor(this).addCurrentTestMethodError("False was expected, but true was received.");
		}
	}
	
	//method
	/**
	 * Generates an error for all of the given values that are true.
	 * 
	 * @param values
	 */
	protected final void expectThatNot(final boolean... values) {
		for (boolean v: values) {
			expectThatNot(v);
		}
	}
	
	//method
	/**
	 * @param value
	 * @return a new double mediator that belongs to this zeta test and has the given value.
	 */
	protected final DoubleMediator expectThat(final double value) {
		return new DoubleMediator(this, value);
	}

	//method
	/**
	 * @param value
	 * @return a new long mediator that belongs to this zeta test and has the given value.
	 */
	protected final LongMediator expectThat(final int value) {
		return new LongMediator(this, value);
	}
	
	//method
	/**
	 * @param value
	 * @return a new long mediator that belongs to this zeta test and has the given value.
	 */
	protected final LongMediator expectThat(final long value) {
		return new LongMediator(this, value);
	}
	
	//method
	/**
	 * @param values
	 * @return a new long container mediator that belongs to this zeta test and has the given values.
	 */
	protected final LongContainerMediator expectThat(final long... values) {
		
		//Creates long vector.
		final Vector<Long> longVectors = new Vector<Long>();
		for (long v: values) {
			longVectors.add(v);
		}
		
		return expectThat(longVectors);
	}
	
	//method
	/**
	 * @param value
	 * @return a new approximative equaling mediator that belongs to this zeta test and has the given value.
	 */
	protected final ApproximativeEqualingMediator expectThat(final ApproximativeEqualing value) {
		return new ApproximativeEqualingMediator(this, value);
	}
	
	//test method
	/**
	 * @param closure
	 * @return a new closure mediator that belongs to this zeta test and has the given closure.
	 */
	protected final ClosureMediator expectThat(final IRunner closure) {
		return new ClosureMediator(this, closure);
	}
	
	//method
	/**
	 * @param values
	 * @return a new long container mediator that belongs to this zeta test and has the given values.
	 */
	protected final LongContainerMediator expectThat(final Iterable<Long> values) {
		return new LongContainerMediator(this, values);
	}
	
	//method
	/**
	 * @param value
	 * @return a new object mediator that belongs to this zeta test and has the given value.
	 */
	protected final ObjectMediator expectThat(final Object value) {
		return new ObjectMediator(this, value);
	}
	
	//method
	/**
	 * @param value
	 * @return a new string mediator that belongs to this nolix test and has the given value.
	 */
	protected final StringMediator expectThat(final String value) {
		return new StringMediator(this, value);
	}
}
