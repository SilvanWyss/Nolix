//package declaration
package ch.nolix.coreTest.enumTest;

//own import
import ch.nolix.core.baseTest.TestPool;

//class
/**
 * @author Silvan Wyss
 * @month 2017-09
 * @lines 20
 */
public final class EnumTestPool extends TestPool {

	//constructor
	/**
	 * Creates new enum test pool.
	 */
	public EnumTestPool() {
		addTest(
			new DirectionTest(),
			new UniDirectionTest()
		);
	}
}
