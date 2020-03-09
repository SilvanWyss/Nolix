//package declaration
package ch.nolix.common.baseTest;

//Java import
import java.lang.reflect.InvocationTargetException;

//own imports
import ch.nolix.common.constants.VariableNameCatalogue;
import ch.nolix.common.independentContainers.List;
import ch.nolix.common.invalidArgumentExceptions.InvalidArgumentException;
import ch.nolix.common.invalidArgumentExceptions.ArgumentIsNullException;
import ch.nolix.common.skillAPI.Runnable;
import ch.nolix.common.validator.Validator;

//class
/**
 * A test pool contains tests and other test pools and can execute them.
 * 
 * @author Silvan Wyss
 * @month 2016-01
 * @lines 180
 */
public abstract class TestPool implements Runnable {

	//multi-attribute
	private final List<Class<BaseTest>> testClasses = new List<>();
	private final List<TestPool> testPools = new List<>();
	
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
	public final boolean containsTestClassRecursively(final Class<BaseTest> testClass) {
		
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
	 * @return the name of the current {@link TestPool}.
	 */
	public final String getName() {
		return getClass().getName();
	}
	
	//method
	/**
	 * Executes the tests and the test pools of this test pool.
	 */
	@Override
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
	 * @return the test classes of the current {@link TestPool}.
	 */
	final List<Class<BaseTest>> getRefTestClasses() {
		return testClasses;
	}
	
	//method
	/**
	 * Adds the given test class to this test pool.
	 * 
	 * @param test
	 * @throws ArgumentIsNullException if the given test is null.
	 * @throws InvalidArgumentException if the given test class is not a testoid class.
	 */
	@SuppressWarnings("unchecked")
	protected final void addTestClass(final Class<?> testClass) {
		
		Validator
		.suppose(testClass)
		.thatIsNamed(VariableNameCatalogue.TEST_CLASS)
		.isNotAbstract();
		
		Validator
		.suppose(testClass)
		.thatIsNamed(VariableNameCatalogue.TEST_CLASS)
		.isSubClassOf(BaseTest.class);
		
		testClasses.addAtEnd((Class<BaseTest>)testClass);
	}
	
	//method
	/**
	 * Adds the given test classes to this test pool.
	 * 
	 * @param tests
	 * @throws ArgumentIsNullException if one of the given test is null.
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
	 * @throws ArgumentIsNullException if the given test pool is null.
	 * @throws InvalidArgumentException if the given test pool contains this test pool recursively.
	 */
	protected final void addTestPool(final TestPool testPool) {
		
		//Checks if the given test pool is not null.
		if (testPool == null) {
			throw new ArgumentIsNullException("test pool");
		}
		
		//Checks if the given test pool does not contain this test pool recursively.
		if (testPool.containsTestPoolRecursively(this)) {
			throw new InvalidArgumentException(
				testPool,
				"contains the current test pool recursively"
			);
		}
		
		testPools.addAtEnd(testPool);
	}
	
	//method
	/**
	 * Adds the given test pools to this test pool.
	 * 
	 * @param testPools
	 * @throws ArgumentIsNullException if one of the given test pools is null.
	 * @throws InvalidArgumentException if one of the given test pools contains this test pool recursively.
	 */
	protected final void addTestPool(final TestPool... testPools) {
		
		//Iterates the given text pools.
		for (TestPool tp: testPools) {
			addTestPool(tp);
		}
	}
}
