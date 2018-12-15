//package declaration
package ch.nolix.coreTest.utilTest;

//own imports
import ch.nolix.core.testoid.TestPool;
import ch.nolix.elementTest.coreTest.TimeTest;

//class
/**
 * @author Silvan Wyss
 * @month 2017-02
 * @lines 20
 */
public final class UtilTestPool extends TestPool {

	//constructor
	/**
	 * Creates a new util test pool.
	 */
	public UtilTestPool() {
		addTestClass(TimeTest.class);
	}
}
