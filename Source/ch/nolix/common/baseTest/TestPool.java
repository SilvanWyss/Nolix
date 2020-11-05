//package declaration
package ch.nolix.common.baseTest;

//own imports
import ch.nolix.common.constant.VariableNameCatalogue;
import ch.nolix.common.independentContainer.List;
import ch.nolix.common.invalidArgumentException.ArgumentIsNullException;
import ch.nolix.common.invalidArgumentException.InvalidArgumentException;
import ch.nolix.common.skillAPI.Runnable;

//class
/**
 * A {@link TestPool} contains either {@link BaseTest}s or other test {@link Class}s.
 * A {@link Test}Pool can run its test {@link Class}s recursively.
 * 
 * @author Silvan Wyss
 * @month 2016-01
 * @lines 220
 */
public abstract class TestPool implements Runnable {
	
	//multi-attributes
	private final List<TestPool> testPools = new List<>();
	private final List<Class<BaseTest>> testClasses = new List<>();
	
	//constructor
	/**
	 * Creates a new {@link TestPool}.
	 * 
	 * {@link TestPool} needs a default constructor is necessary,
	 * because its other constructors are ambiguous when they are called with empty varargs.
	 */
	public TestPool() {}
	
	//constructor
	/**
	 * Creates a new {@link TestPool} with the given test {@link Class}s.
	 * 
	 * @param testClasses
	 * @throws ArgumentIsNullException if one of the given testClasses is null.
	 * @throws InvalidArgumentException if one of the given testClasses is not a actually not a test {@link Class}.
	 * @throws InvalidArgumentException if one of the given testClasses is abstract.
	 * @throws InvalidArgumentException if one of the given testClasses does not contain a default constructor.
	 */
	public TestPool(final Class<?>... testClasses) {
		
		//Iterates the given testClasses.
		for (final var tc : testClasses) {
			addTestClass(tc);
		}
	}
	
	//constructor
	/**
	 * Creates a new {@link TestPool} with the given {@link TestPool}s.
	 * 
	 * @param testClasses
	 * @throws ArgumentIsNullException if one of the the given testPools is null.
	 */
	public TestPool(final TestPool... testPools) {
		
		//Iterates the given testPools.
		for (final var tp : testPools) {
			addTestPool(tp);
		}
	}
	
	//method
	/**
	 * @param testPool
	 * @return true if the current {@link TestPool} contains the given testPool recursively.
	 */
	public final boolean containsTestPoolRecursively(final TestPool testPool) {
		
		//Iterates the testPools of the current TestPool.
		for (final TestPool tp : testPools) {
			
			//Asserts that the current testPool is the given testPool or contains the given testPool recursively.
			if (tp == testPool || tp.containsTestPoolRecursively(testPool)) {
				return true;
			}
		}
		
		return false;
	}
	
	//method
	/**
	 * @param testClass
	 * @return true if the current {@link TestPool} contains the given testClass recursively.
	 */
	public final boolean containsTestClassRecursively(final Class<BaseTest> testClass) {
		
		//Iterates the testPools of the current TestPool.
		for (final var tp : testPools) {
			
			//Asserts that the current testPool contains the given testClass recursively.
			if (tp.containsTestClassRecursively(testClass)) {
				return true;
			}
		}
		
		//Iterates the testClasses of the current TestPool.
		for (final var tc : testClasses) {
			
			//Asserts that the current testClass is the given testClass.
			if (tc == testClass) {
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
	 * Runs the {@link BaseTest}s of the current {@link TestPool} recursively.
	 */
	@Override
	public final void run() {
		
		//Calls other method
		run(new StandardConsoleLinePrinter());
	}
	
	//method
	/**
	 * Runs the {@link BaseTest}s of the current {@link TestPool} recursively using the given linePrinter.
	 * 
	 * @param linePrinter
	 * @throws ArgumentIsNullException if the given linePrinter is null.
	 */
	public final void run(final ILinePrinter linePrinter) {
		new TestPoolRun(this, linePrinter).run();
	}
	
	//method
	/**
	 * @return the test {@link Class}s of the current {@link TestPool}.
	 */
	final List<Class<BaseTest>> getRefTestClasses() {
		return testClasses;
	}
	
	//method
	/**
	 * @return the test {@link TestPools}s of the current {@link TestPool}.
	 */
	final List<TestPool> getRefTestPools() {
		return testPools;
	}
	
	//method
	/**
	 * Adds the given testClass to the current {@link TestPool}.
	 * 
	 * @param testClass
	 * @throws ArgumentIsNullException if the given testClass is null.
	 * @throws InvalidArgumentException if the given testClass is not a actually not a test {@link Class}.
	 * @throws InvalidArgumentException if the given testClass is abstract.
	 * @throws InvalidArgumentException if the given testClass does not contain a default constructor.
	 */
	@SuppressWarnings("unchecked")
	private void addTestClass(final Class<?> testClass) {
		
		//Asserts that the given testClass is not null.
		if (testClass == null) {
			throw new ArgumentIsNullException(VariableNameCatalogue.TEST_CLASS);
		}
		
		//Asserts that the given testClass is a sub class of BaseTest.
		if (!ReflectionHelper.firstIsSubClassOfSecond(testClass, BaseTest.class)) {
			throw
			new InvalidArgumentException(
				VariableNameCatalogue.TEST_CLASS,
				testClass,
				"is not a sub class of " + BaseTest.class.getName()
			);
		}
		
		//Asserts that the given testClass is not abstract.
		if (ReflectionHelper.isAbstract(testClass)) {
			throw new InvalidArgumentException(VariableNameCatalogue.TEST_CLASS, testClass, "is abstract");
		}
		
		//Asserts that the given testClass has a default constructor.
		if (!ReflectionHelper.hasDefaultConstructor(testClass)) {
			throw
			new InvalidArgumentException(
				VariableNameCatalogue.TEST_CLASS,
				testClass,
				"does not have a default constructor"
			);
		}
		
		testClasses.addAtEnd((Class<BaseTest>)testClass);
	}
	
	//method
	/**
	 * Adds the given testPool to the current {@link TestPool}.
	 * 
	 * @param testPool
	 * @throws ArgumentIsNullException if the given testPool is null.
	 * @throws InvalidArgumentException if the given testPool contains the current {@link TestPool} recursively.
	 */
	private void addTestPool(final TestPool testPool) {
		
		//Asserts that the given testPool is not null.
		if (testPool == null) {
			throw new ArgumentIsNullException("test pool");
		}
		
		//Asserts that the given test pool does not contain the current TestPool recursively.
		if (testPool.containsTestPoolRecursively(this)) {
			throw new InvalidArgumentException(testPool, "contains recursively " + getName());
		}
		
		testPools.addAtEnd(testPool);
	}
}
