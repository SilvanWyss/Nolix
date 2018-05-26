//package declaration
package ch.nolix.nolixTest;

import ch.nolix.coreTest.CoreTestPool;
import ch.nolix.elementTest.ElementTestPool;
import ch.nolix.primitive.testoid.TestPool;

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
