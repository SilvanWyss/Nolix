//package declaration
package ch.nolix.common.testing.test;

//class
/**
 * A {@link ObjectTest} is a test to check test units of a certain type.
 * 
 * @author Silvan Wyss
 * @date 2018-09-11
 * @lines 20
 * @param <OT> is the type of the test units of a {@link ObjectTest}.
 */
public abstract class ObjectTest<OT> extends Test {
	
	//method declaration
	/**
	 * @return a new test unit for the current {@link ObjectTest}.
	 */
	protected abstract OT createTestUnit();
}
