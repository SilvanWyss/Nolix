//package declaration
package ch.nolix.core.test2;

//abstract class
/**
 * A {@link ObjectTest} is a test for objects of a certain type.
 * 
 * @author Silvan Wyss
 * @month 2018-09
 * @lines 20
 * @param <TO> The type of the test objects of a {@link ObjectTest}.
 */
public abstract class ObjectTest<TO> extends Test {

	//abstract method
	/**
	 * @return a new test object for the current {@link ObjectTest}.
	 */
	protected abstract TO createTestObject();
}
