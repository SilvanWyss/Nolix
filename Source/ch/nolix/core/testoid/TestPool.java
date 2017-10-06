//package declaration
package ch.nolix.core.testoid;

//Java import
import java.util.Vector;

//own imports
import ch.nolix.core.interfaces.Runnable;
import ch.nolix.core.invalidArgumentException.Argument;
import ch.nolix.core.invalidArgumentException.ErrorPredicate;
import ch.nolix.core.invalidArgumentException.InvalidArgumentException;
import ch.nolix.core.invalidArgumentException.NullArgumentException;

//class
/**
 * A test pool contains tests and other test pools and can execute them.
 * 
 * @author Silvan Wyss
 * @month 2016-01
 * @lines 140
 */
public abstract class TestPool implements Runnable {

	//multiple attribute
	private final Vector<Testoid> tests = new Vector<Testoid>();
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
	public final boolean containsTestRecursively(final Testoid test) {
		
		for (final Testoid t : tests) {
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
	protected final void addTest(final Testoid test) {
		
		//Checks if the given test is not null.
		if (test == null) {
			throw new NullArgumentException("test");
		}
		
		tests.add(test);
	}
	
	//method
	/**
	 * Adds the given tests to this test pool.
	 * 
	 * @param tests
	 * @throws NullArgumentException if one of the given test is null.
	 */
	protected final void addTest(Testoid... tests) {
		
		//Iterates the given tests.
		for (Testoid t: tests) {
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
		if (testPool == null) {
			throw new NullArgumentException("test pool");
		}
		
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
