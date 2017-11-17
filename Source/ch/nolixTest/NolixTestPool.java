//package declaration
package ch.nolixTest;

//own imports
import ch.nolix.core.testoid.TestPool;
import ch.nolix.elementTest.ElementTestPool;
import ch.nolix.elementTest.coreTest.CoreTestPool;

//class
/**
 * A Nolix test pool is not mutable.
 * 
 * @author Silvan Wyss
 * @month 2017-11
 * @lines 20
 */
public final class NolixTestPool extends TestPool {

	//constructor
	/**
	 * Creates new nolix test pool.
	 */
	public NolixTestPool() {
		addTestPool(
			new CoreTestPool(),
			new ElementTestPool()
		);
	}
}
