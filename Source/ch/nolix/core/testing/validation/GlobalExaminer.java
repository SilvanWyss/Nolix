//package declaration
package ch.nolix.core.testing.validation;

//Java imports
import java.util.ArrayList;

import ch.nolix.core.testing.test.ClosureMediator;
//own imports
import ch.nolix.coreapi.functionapi.genericfunctionapi.IAction;

//class
public final class GlobalExaminer {
	
	//static method
	public static void expect(final boolean value) {
		if (!value) {
			takteExpectationError("True was expected, but false was received.");
		}
	}
	
	//static method
	public static void expect(final boolean value, final boolean... values) {
		
		if (values == null) {
			takteExpectationError("True values were expected, but null was received.");
		}
		
		if (!value) {
			takteExpectationError("True values were expected, but the 1th value is false.");
		}
		
		var index = 2;
		for (final var v: values) {
			
			if (!v) {
				takteExpectationError("True values were expected, but the " + index + "th value is false.");
			}
			
			index++;
		}
	}
	
	//static method
	public static ByteMediator expect(final byte value) {
		return new ByteMediator(GlobalExaminer::takteExpectationError, value);
	}
	
	//static method
	public static DoubleMediator expect(final double value) {
		return new DoubleMediator(GlobalExaminer::takteExpectationError, value);
	}
		
	//static method
	public static DoubleMediator expect(final Double value) {
		return new DoubleMediator(GlobalExaminer::takteExpectationError, value);
	}

	//static method
	public static LongMediator expect(final int value) {
		return new LongMediator(GlobalExaminer::takteExpectationError, value);
	}
	
	//static method
	public static ClosureMediator expect(final IAction closure) {
		return new ClosureMediator(GlobalExaminer::takteExpectationError, closure);
	}
	
	//static method
	public static LongMediator expect(final Integer value) {
		return new LongMediator(GlobalExaminer::takteExpectationError, value);
	}
	
	//static method
	public static <E> ContainerMediator<E> expect(final Iterable<E> container) {
		return new ContainerMediator<>(GlobalExaminer::takteExpectationError, container);
	}
	
	//static method
	public static LongMediator expect(final long value) {
		return new LongMediator(GlobalExaminer::takteExpectationError, value);
	}
	
	//static method
	public static LongMediator expect(final Long value) {
		return new LongMediator(GlobalExaminer::takteExpectationError, value);
	}
	
	//static method
	public static <V> ValueMediator<V> expect(final V value) {
		return new ValueMediator<>(GlobalExaminer::takteExpectationError, value);
	}
	
	//static method
	public static StringMediator expect(final String value) {
		return new StringMediator(GlobalExaminer::takteExpectationError, value);
	}
	
	//static method
	public static void expectNot(final boolean value) { //NOSONAR: This method validates the given value.
		if (value) {
			takteExpectationError("False was expected, but true was received.");
		}
	}
	
	//static method
	public static void expectNot(final boolean value, final boolean... values) {
		
		if (values == null) {
			takteExpectationError("False values were expected, but null was received.");
		}
		
		if (value) {
			takteExpectationError("False values were expected, but the 1th value is true.");
		}
		
		var index = 2;
		for (final var v: values) {
			
			if (v) {
				takteExpectationError("False values were expected, but the " + index + "th value is true.");
			}
			
			index++;
		}
	}
	
	//static method
	public static BitMediator expectTheBit(final boolean bit) {
		return new BitMediator(GlobalExaminer::takteExpectationError, bit);
	}
	
	//static method
	public static BitMediator expectTheBit(final int bit) {
		return new BitMediator(GlobalExaminer::takteExpectationError, bit);
	}
	
	//static method
	public static MultiDoubleMediator expectTheDoubles(final double... values) {
		return new MultiDoubleMediator(GlobalExaminer::takteExpectationError, values);
	}
	
	//static method
	public static MultiDoubleMediator expectTheDoubles(Iterable<Double> values) {
		return new MultiDoubleMediator(GlobalExaminer::takteExpectationError, values);
	}
	
	//static method
	public static MultiLongMediator expectTheInts(final int value, final int... values) {
		
		final var intArrayList = new ArrayList<Long>();
		intArrayList.add((long)value);
		for (final var v: values) {
			intArrayList.add((long)v);
		}
		
		return expectTheLongs(intArrayList);
	}
	
	//static method
	public static MultiLongMediator expectTheLongs(final Iterable<Long> values) {
		return new MultiLongMediator(GlobalExaminer::takteExpectationError, values);
	}
	
	//static method
	public static MultiLongMediator expectTheLongs(final long value, final long... values) {
		
		final var longArrayList = new ArrayList<Long>();
		longArrayList.add(value);
		for (final var v: values) {
			longArrayList.add(v);
		}
		
		return expectTheLongs(longArrayList);
	}
	
	//static method
	private static void takteExpectationError(final String expectationError) {
		throw ExpectationException.withErrorMessage(expectationError);
	}
	
	//constructor
	private GlobalExaminer() {}
}
