//package declaration
package ch.nolix.nolixTest;

import ch.nolix.core.testoid.TestPool;
import ch.nolix.coreTest.CoreTestPool;
import ch.nolix.elementTest.ElementTestPool;

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
	 * Creates a new nolix test pool.
	 */
	public NolixTestPool() {
		addTestPool(
			new CoreTestPool(),
			new ElementTestPool()
		);
	}
}
