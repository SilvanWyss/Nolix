//package declaration
package ch.nolix.primitive.testoid;

//Java imports
import java.lang.reflect.InvocationTargetException;
import java.util.Vector;

//own imports
import ch.nolix.core.skillInterfaces.Runnable;
import ch.nolix.primitive.container.List;
import ch.nolix.primitive.invalidArgumentException.Argument;
import ch.nolix.primitive.invalidArgumentException.ErrorPredicate;
import ch.nolix.primitive.invalidArgumentException.InvalidArgumentException;
import ch.nolix.primitive.invalidArgumentException.NullArgumentException;

//class
/**
 * A test pool contains tests and other test pools and can execute them.
 * 
 * @author Silvan Wyss
 * @month 2016-01
 * @lines 160
 */
public abstract class TestPool implements Runnable {

	//multiple attribute
	private final List<Class<Testoid>> testClasses = new List<Class<Testoid>>();
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
	 * @param testClass
	 * @return true if this test pool contains the given test class recursively.
	 */
	public final boolean containsTestClassRecursively(final Class<Testoid> testClass) {
		
		for (final var tc : testClasses) {
			if (tc == testClass) {
				return true;
			}
		}
		
		for (final var tp : testPools) {
			if (tp.containsTestClassRecursively(testClass)) {
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
		for (final var tc : testClasses ) {
			try {
				tc.getDeclaredConstructor().newInstance().run();
			}
			catch (final 
				InstantiationException
				| IllegalAccessException
				| IllegalArgumentException
				| InvocationTargetException
				| NoSuchMethodException
				| SecurityException exception
			) {
				throw new RuntimeException(exception);
			}
		}
		testPools.forEach(tp -> tp.run());
	}
	
	//method
	/**
	 * Adds the given test class to this test pool.
	 * 
	 * @param test
	 * @throws NullArgumentException if the given test is not an instance.
	 */
	@SuppressWarnings("unchecked")
	protected final void addTestClass(final Class<?> testClass) {
		
		//Checks if the given test class is an instance.
		if (testClass == null) {
			throw new NullArgumentException("test class");
		}
		
		//TODO: Check if test class is a testoid class.
		testClasses.addAtEnd((Class<Testoid>)testClass);
	}
	
	//method
	/**
	 * Adds the given test classes to this test pool.
	 * 
	 * @param tests
	 * @throws NullArgumentException if one of the given test is not an instance.
	 */
	protected final void addTestClass(final Class<?>... testClasses) {
		
		//Iterates the given test classes.
		for (final var tc : testClasses) {
			addTestClass(tc);
		}
	}
	
	//method
	/**
	 * Adds the given test pool to this test pool.
	 * 
	 * @param testPool
	 * @throws NullArgumentException if the given test pool is not an instance.
	 * @throws InvalidArgumentException if the given test pool contains this test pool recursively.
	 */
	protected final void addTestPool(final TestPool testPool) {
		
		//Checks if the given test pool is an instance.
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
	 * @throws NullArgumentException if one of the given test pools is not an instance.
	 * @throws InvalidArgumentException if one of the given test pools contains this test pool recursively.
	 */
	protected final void addTestPool(final TestPool... testPools) {
		
		//Iterates the given text pools.
		for (TestPool tp: testPools) {
			addTestPool(tp);
		}
	}
}
