//package declaration
package ch.nolix.core.testing.test;

//own imports
import ch.nolix.core.testing.basetest.TestCase;

//class
/**
 * A {@link ObjectTest} is a test to check test units of a certain type.
 * 
 * @author Silvan Wyss
 * @date 2018-09-11
 * @param <OT> is the type of the test units of a {@link ObjectTest}.
 */
public abstract class ObjectTest<OT> extends Test {
	
	//method
	@TestCase
	public void testCase_equals_whenEqualInstanceIsGiven() throws Exception {
		
		//setup
		final var testUnit = createTestUnit();
		final var object = createTestUnit();
		
		//execution
		final var result = testUnit.equals(object);
		
		//verification
		expect(result);
		
		//cleanup
		if (testUnit instanceof AutoCloseable autoCloseable) {
			autoCloseable.close();
		}
		if (object instanceof AutoCloseable autoCloseable) {
			autoCloseable.close();
		}
	}
	
	//method
	@TestCase
	public void testCase_equals_whenNullIsGiven() throws Exception {
		
		//setup
		final var testUnit = createTestUnit();
		
		//execution
		final var result = testUnit.equals(null);
		
		//verification
		expectNot(result);
		
		//cleanup
		if (testUnit instanceof AutoCloseable autoCloseable) {
			autoCloseable.close();
		}
	}
	
	//method
	@TestCase
	public void testCase_equals_whenSeflIsGiven() throws Exception {
		
		//setup
		final var testUnit = createTestUnit();
		
		//execution
		final var result = testUnit.equals(testUnit);
		
		//verification
		expect(result);
		
		//cleanup
		if (testUnit instanceof AutoCloseable autoCloseable) {
			autoCloseable.close();
		}
	}
	
	//method declaration
	/**
	 * @return a new test unit for the current {@link ObjectTest}.
	 */
	protected abstract OT createTestUnit();
}
