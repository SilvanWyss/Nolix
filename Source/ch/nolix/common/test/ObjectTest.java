//package declaration
package ch.nolix.common.test;

//class
/**
 * A {@link ObjectTest} is a test to check test units of a certain type.
 * 
 * @author Silvan Wyss
 * @month 2018-09
 * @lines 20
 * @param <OT> The type of the test units of a {@link ObjectTest}.
 */
public abstract class ObjectTest<OT> extends Test {
	
	//method declaration
	/**
	 * @return a new test unit for the current {@link ObjectTest}.
	 */
	protected abstract OT createTestUnit();
}
