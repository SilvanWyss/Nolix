//package declaration
package ch.nolix.core.test;

//Java import
import java.util.Vector;


//own imports
import ch.nolix.core.interfaces.Executable;
import ch.nolix.core.invalidArgumentException.Argument;
import ch.nolix.core.invalidArgumentException.ErrorPredicate;
import ch.nolix.core.invalidArgumentException.InvalidArgumentException;
import ch.nolix.core.validator2.Validator;

//class
/**
 * A test pool contains tests and other test pools and can execute them.
 * 
 * @author Silvan Wyss
 * @month 2016-01
 * @lines 150
 */
public abstract class TestPool implements Executable {

	//multiple attribute
	private final Vector<Test> tests = new Vector<Test>();
	private final Vector<TestPool> testPools = new Vector<TestPool>();
	
	//method
	/**
	 * @param testPool
	 * @return true if this test pool contains the given test pool recursively.
	 */
	public final boolean containsTestPoolRecursively(final TestPool testPool) {
		
		for (final TestPool tp : testPools) {			
			if (tp.containsTestPoolRecursively(testPool)) {
				return true;
			}
		}
			
		return false;
	}
	
	//method
	/**
	 * @param test
	 * @return true if this test pool contains the given test recursively.
	 */
	public final boolean containsTestRecursively(final Test test) {
		
		for (final Test t : tests) {
			if (t == test) {
				return true;
			}
		}
		
		for (final TestPool tp : testPools) {
			if (tp.containsTestRecursively(test)) {
				return true;
			}
		}
		
		return false;
	}
	
	//method
	/**
	 * Executes the tests and the test pools of this test pool.
	 */
	public final void run() {
		tests.forEach(t -> t.run());
		testPools.forEach(tp -> tp.run());
	}
	
	//method
	/**
	 * Adds the given test to this test pool.
	 * 
	 * @param test
	 * @throws NullArgumentException if the given test is null.
	 */
	protected final void addTest(final Test test) {
		
		//Checks if the given test is not null.
		Validator.supposeThat(test).thatIsNamed("test").isNotNull();
		
		tests.add(test);
	}
	
	//method
	/**
	 * Adds the given tests to this test pool.
	 * 
	 * @param tests
	 * @throws NullArgumentException if one of the given test is null.
	 */
	protected final void addTest(Test... tests) {
		
		//Iterates the given tests.
		for (Test t: tests) {
			addTest(t);
		}
	}
	
	//method
	/**
	 * Adds the given test pool to this test pool.
	 * 
	 * @param testPool
	 * @throws NullArgumentException if the given test pool is null.
	 * @throws InvalidArgumentException if the given test pool contains this test pool recursively.
	 */
	protected final void addTestPool(final TestPool testPool) {
		
		//Checks if the given test pool is not null.
		Validator.supposeThat(testPool).thatIsNamed("test pool").isNotNull();
		
		//Checks if the given test pool does not contain this test pool recursively.
		if (testPool.containsTestPoolRecursively(this)) {
			throw new InvalidArgumentException(
				new Argument(testPool),
				new ErrorPredicate(" contains the test pool recursively")
			);
		}
		
		testPools.add(testPool);
	}
	
	//method
	/**
	 * Adds the given test pools to this test pool.
	 * 
	 * @param testPools
	 * @throws NullArgumentException if one of the given test pools is null.
	 * @throws InvalidArgumentException if one of the given test pools contains this test pool recursively.
	 */
	protected final void addTestPool(final TestPool... testPools) {
		
		//Iterates the given text pools.
		for (TestPool tp: testPools) {
			addTestPool(tp);
		}
	}
}
