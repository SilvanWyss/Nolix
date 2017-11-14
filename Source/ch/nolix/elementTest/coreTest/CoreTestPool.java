//package declaration
package ch.nolix.elementTest.coreTest;

//own import
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
	 * Creates new core test pool.
	 */
	public CoreTestPool() {
		addTest(new TimeTest());
	}
}
