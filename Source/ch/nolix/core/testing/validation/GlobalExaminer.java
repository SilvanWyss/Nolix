//package declaration
package ch.nolix.core.testing.validation;

//Java imports
import java.util.ArrayList;

import ch.nolix.coreapi.functionuniversalapi.IAction;

//class
public final class GlobalExaminer {
	
	//static method
	public static void expect(final boolean value) {
		if (!value) {
			takteExpectationError("True was expected, but false was received.");
		}
	}
	
	//static method
	public static void expect(final boolean... values) {
		
		if (values == null) {
			takteExpectationError("True values were expected, but null was received.");
		}
		
		var index = 1;
		for (final var b: values) {
			
			if (!b) {
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
	public static void expectNot(final boolean value) {
		if (value) {
			takteExpectationError("False was expected, but true was received.");
		}
	}
	
	//static method
	public static void expectNot(final boolean... values) {
		
		if (values == null) {
			takteExpectationError("False values were expected, but null was received.");
		}
		
		var index = 1;
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
	public static MultiLongMediator expectTheInts(final int... values) {
		return new MultiLongMediator(GlobalExaminer::takteExpectationError, values);
	}
	
	//static method
	public static MultiLongMediator expectTheLongs(final Iterable<Long> values) {
		return new MultiLongMediator(GlobalExaminer::takteExpectationError, values);
	}
	
	//static method
	public static MultiLongMediator expectTheLongs(final long... values) {
		
		final var longArrayList = new ArrayList<Long>();
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
