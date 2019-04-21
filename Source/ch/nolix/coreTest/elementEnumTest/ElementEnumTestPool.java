//package declaration
package ch.nolix.coreTest.elementEnumTest;

//own imports
import ch.nolix.core.testoid.TestPool;

//class
/**
 * @author Silvan Wyss
 * @month 2017-09
 * @lines 20
 */
public final class ElementEnumTestPool extends TestPool {

	//constructor
	/**
	 * Creates a new enum test pool.
	 */
	public ElementEnumTestPool() {
		addTestClass(
			DirectionTest.class,
			UniDirectionTest.class
		);
	}
}
