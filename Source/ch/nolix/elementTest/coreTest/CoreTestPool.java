//package declaration
package ch.nolix.elementTest.coreTest;

//own imports
import ch.nolix.core.testoid.TestPool;

//class
/**
 * @author Silvan Wyss
 * @month 2017-11
 * @lines 20
 */
public final class CoreTestPool extends TestPool {

	//constructor
	/**
	 * Creates a new core test pool.
	 */
	public CoreTestPool() {
		addTestClass(TimeTest.class);
	}
}
